<template>
  <div>
    <el-breadcrumb :separator-icon="ArrowRight" style="margin-bottom: 20px;">
      <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
      <el-breadcrumb-item>
        <a href="/choose">Choose Institution</a>
      </el-breadcrumb-item>
      <el-breadcrumb-item>Institution Comparison</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider content-position="center">Comparison Result</el-divider>
    <el-row :gutter="20" class="comparison-section">
      <el-col :span="14">
        <div class="card radar-card">
          <div class="echarts" id="radarChart"></div>
        </div>
      </el-col>
      <el-col :span="10">
        <div class="vertical-institutions">
          <el-col
              v-for="institution in institutions"
              :key="institution.id"
              class="mb-4"
          >
            <div class="card institution-card" :class="{ 'highest-score': institution.isHighest }">
              <div class="d-flex justify-content-between align-items-center">
                <div class="name-rating">
                  <h2>{{ institution.name }} <el-tag type="info">{{ institution.rating }}</el-tag></h2>
                  <div class="full-name">{{ institution.fullName }}</div>
                </div>
                <el-progress type="circle" :percentage="institution.score">
                  <template #default="{ percentage }">
                    <span class="percentage-value">{{ percentage }}
                      <div v-if="institution.isHighest"><el-tag type="success">Highest</el-tag></div>
                    </span>
                  </template>
                </el-progress>
              </div>
            </div>
          </el-col>
        </div>
      </el-col>
    </el-row>

    <div class="card" >
      <div class="block text-center" style="margin-left: 20px" >
        <el-carousel indicator-position="none" autoplay="false">
          <el-carousel-item>
            <div class="echarts" id="StackedLineChartSociety" style="height: 100%; width: 100%;"></div>
          </el-carousel-item>
          <el-carousel-item>
            <div class="echarts" id="StackedLineChartEconomy" style="height: 100%; width: 100%;"></div>
          </el-carousel-item>
          <el-carousel-item>
            <div class="echarts" id="StackedLineChartEnvironment" style="height: 100%; width: 100%;"></div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>

    <el-divider content-position="center">Detail Analysis</el-divider>
    <el-row :gutter="20" class="detail-analysis">
      <el-col
          v-for="institution in institutions"
          :key="institution.id"
          :xs="24"
          :sm="institutionColSpan"
          :md="institutionColSpan"
          :lg="institutionColSpan"
          :xl="institutionColSpan"
          class="mb-4"
      >
        <div v-for="type in ['Society', 'Economy', 'Environment']" :key="type">
          <h3>{{ type }}</h3>
          <div class="card" style="margin-bottom: 20px;">
            <template v-for="category in institution.categories" :key="category.id">
              <div v-if="category.type === type">
                <h4>{{ category.name }}</h4>
                <template v-for="impact_area in category.impact_areas" :key="impact_area.id">
                  <el-col :data="impact_area.list" class="mb-4">
                    <el-card shadow="hover">
                      {{ impact_area.name }} {{ impact_area.point }}
                      <el-progress :percentage="((impact_area.point / impact_area.total_point) * 100).toFixed(2)">
                        <span>{{ impact_area.total_point }}</span>
                      </el-progress>
                      <el-collapse v-model="activeNames" @change="handleChange">
                        <el-collapse-item title="Credit Indicators table" :name="impact_area.name">
                          <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%;">
                            <el-table-column prop="credit" label="Credit" width="160" />
                            <el-table-column prop="indicator" label="Indicator" />
                            <el-table-column prop="point" label="Point" width="80" sortable />
                          </el-table>
                        </el-collapse-item>
                      </el-collapse>
                    </el-card>
                  </el-col>
                </template>
              </div>
            </template>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request'; // 确保导入你的请求工具
import * as echarts from "echarts";
import {
  BGList,
  CampusList,
  CPList,
  CurriculumList, ECList, FDList,
  ILList,
  InvestmentList,
  PublicList, PWList,
  ResearchList,
  SEList, TransportationList,
  WWList
} from '../../data';
import {ArrowRight} from "@element-plus/icons-vue";

export default {
  name: 'Comparison',
  computed: {
    ArrowRight() {
      return ArrowRight
    }
  },
  setup() {
    const route = useRoute();
    const institutions = ref([]);

    const categories = ref(['Academics', 'Engagement', 'Operations','Planning & Administration', 'Innovation & Leadership']);

    const fetchInstitutionData = async (id) => {
      try {
        const response = await request.get(`/list/institution/${id}`);
        return response.data;
      } catch (error) {
        console.error(`Error fetching institution data for id ${id}:`, error);
        return null;
      }
    };

    const loadInstitutions = async () => {
      const ids = route.query.ids ? route.query.ids.split(',') : [];
      const promises = ids.map(id => fetchInstitutionData(id));
      const results = await Promise.all(promises);
      institutions.value = results.filter(result => result !== null);

      let highestScore = Math.max(...institutions.value.map(inst => inst.score));
      institutions.value.forEach(inst => {
        inst.isHighest = inst.score === highestScore;
      });

      initRadarChart();
      initStackedLineChart();
    };

    const institutionColSpan = computed(() => {
      const count = institutions.value.length;
      if (count === 1) return 24;
      if (count === 2) return 12;
      return 8;
    });

    const calculateCategoryScores = (categories) => {
      return categories.map(category => {
        const totalPoints = category.impact_areas.reduce((sum, area) => sum + area.point, 0);
        return Number(totalPoints.toPrecision(10)); // 确保精度
      });
    };

    const initRadarChart = ( ) => {
      const chartDom = document.getElementById('radarChart');
      if (!chartDom) {
        console.error("Radar chart DOM element not found");
        return;
      }
      const myChart = echarts.init(chartDom);

      const seriesData = institutions.value.map(inst => {
        const categoryScores = calculateCategoryScores(inst.categories);
        return {
          value: [inst.score, ...categoryScores],
          name: inst.name,
          label: {
            show: true,
            formatter: function (params) {
              return params.value;
            }
          },
          areaStyle: inst.isHighest ? {
            color: new echarts.graphic.RadialGradient(0.1, 0.6, 1, [
              { color: 'rgba(255, 145, 124, 0.1)', offset: 0 },
              { color: 'rgba(255, 145, 124, 0.9)', offset: 1 }
            ])
          } : undefined
        };
      });

      const option = {
        color: ['#67F9D8', '#FFE434', '#FF917C'],
        title: {
          text: ''
        },
        legend: {},
        radar: {
          indicator: [
            { text: 'TotalScore', max: 100 },
            { text: 'Academics', max: 58 },
            { text: 'Engagement', max: 41 },
            { text: 'Operations', max: 71 },
            { text: 'Planning & Administration', max: 108 },
            { text: 'Innovation & Leadership', max: 4 }
          ],
          radius: 120,
          axisLine: {
            lineStyle: {
              color: 'rgba(211, 253, 250, 0.8)'
            }
          },
          splitLine: {
            lineStyle: {
              color: 'rgba(211, 253, 250, 0.8)'
            }
          },
          axisName: {
            color: '#fff',
            backgroundColor: '#666',
            borderRadius: 3,
            padding: [3, 5]
          }
        },
        series: [
          {
            type: 'radar',
            data: seriesData
          }
        ]
      };

      myChart.setOption(option);
    };

    const extractImpactAreas = (type) => {
      const areas = [];
      institutions.value.forEach(inst => {
        inst.categories.forEach(category => {
          if (category.type === type) {
            category.impact_areas.forEach(area => {
              if (!areas.find(a => a.name === area.name)) {
                areas.push({
                  name: area.name,
                  total_point: area.total_point
                });
              }
            });
          }
        });
      });
      return areas;
    };

    const initStackedLineChart = (type, chartId) => {
      const chartDom = document.getElementById(chartId);
      if (!chartDom) {
        console.error(`Stacked Line chart DOM element ${chartId} not found`);
        return;
      }
      const myChart = echarts.init(chartDom);

      const impactAreas = extractImpactAreas(type);
      const xAxisData = impactAreas.map(area => area.name);
      const yAxisMax = Math.max(...impactAreas.map(area => area.total_point));

      const seriesData = institutions.value.map(inst => {
        const points = xAxisData.map(name => {
          let point = 0;
          inst.categories.forEach(category => {
            if (category.type === type) {
              category.impact_areas.forEach(area => {
                if (area.name === name) {
                  point = area.point;
                }
              });
            }
          });
          return point;
        });

        return {
          name: inst.name,
          type: 'line',
          data: points
        };
      });

      const option = {
        title: {
          text: `${type}`
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: institutions.value.map(inst => inst.name)
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: xAxisData
        },
        yAxis: {
          type: 'value',
          max: yAxisMax
        },
        series: seriesData
      };

      myChart.setOption(option);
    };

    const initCharts = () => {
      initStackedLineChart('Society', 'StackedLineChartSociety');
      initStackedLineChart('Economy', 'StackedLineChartEconomy');
      initStackedLineChart('Environment', 'StackedLineChartEnvironment');
    };

    onMounted(() => {
      loadInstitutions().then(() => {
        initCharts();
      });
    });

    const getImpactAreaList = (impactAreaName) => {
      switch (impactAreaName) {
        case 'Curriculum':
          return CurriculumList;
        case 'Research':
          return ResearchList;
        case 'Campus Engagement':
          return CampusList;
        case 'Public Engagement':
          return PublicList;
        case 'Coordination & Planning':
          return CPList;
        case 'Investment':
          return InvestmentList;
        case 'Social Equity':
          return SEList;
        case 'Wellbeing & Work':
          return WWList;
        case 'Innovation & Leadership':
          return ILList;
        case 'Buildings & Grounds':
          return BGList;
        case 'Energy & Climate':
          return ECList;
        case 'Food & Dining':
          return FDList;
        case 'Procurement & Waste':
          return PWList;
        case 'Transportation':
          return TransportationList;
        default:
          return [];
      }
    };

    const handleChange = (val) => {
      console.log(val);
    };

    return {
      getImpactAreaList,
      institutions,
      categories,
      institutionColSpan,
      handleChange
    };
  }
};


</script>

<style scoped>

.comparison-section {
  margin-bottom: 40px;
}

.card {
  background: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.radar-card {
  height: 400px;
}

.vertical-institutions {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.echarts {
  min-height: 350px;
}

.percentage-value {
  font-size: 16px;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.highest-score {
  border: 2px solid #dcede9;
  background-color: #f0f9f8;
}

.mb-4 {
  margin-bottom: 20px !important;
}

.d-flex {
  display: flex;
}

.justify-content-between {
  justify-content: space-between;
}

.align-items-center {
  align-items: center;
}

.text-center {
  text-align: center;
}

.carousel-card {
  padding: 20px;
  margin-bottom: 40px;
}

.detail-analysis {
  margin-top: 20px;
}
</style>
