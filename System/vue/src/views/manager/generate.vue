<template>
  <div class="report-container">
    <el-breadcrumb :separator-icon="ArrowRight">

      <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
      <el-breadcrumb-item>
        <a href="/list">Institution List</a>
      </el-breadcrumb-item>
      <el-breadcrumb-item>
        <a @click.prevent="goBack">Institution Report</a>
      </el-breadcrumb-item>
      <el-breadcrumb-item>Generate Report</el-breadcrumb-item>
    </el-breadcrumb>

    <h1 class="center">Sustainability Assessment Report</h1>
    <div class="card" style="margin-bottom: 10px">
      <el-descriptions direction="vertical" :column="4" border>
        <el-descriptions-item label="Name">{{ data.name }} </el-descriptions-item>
        <el-descriptions-item label="Full Name">{{ data.fullName }} </el-descriptions-item>
        <el-descriptions-item label="Rating">
          <el-tag type="info">{{ data.rating }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Score">{{ data.score }}</el-descriptions-item>
      </el-descriptions>
    </div>

    <el-row :gutter="10">
      <el-col :span="8" v-for="type in reportDimensions" :key="type.name">
        <el-card class="radar-card">
          <h3>{{ type.name }}</h3>
          <div class="circle-container" style=" display: flex; justify-content: space-around; align-items: center;">
            <div class="circle green">
              <span>{{ countImpactAreas(type, 'high') }}</span>
            </div>
            <div class="circle yellow">
              <span>{{ countImpactAreas(type, 'medium') }}</span>
            </div>
            <div class="circle red">
              <span>{{ countImpactAreas(type, 'low') }}</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <div v-for="type in reportDimensions" :key="type.name" class="report-section">
      <h3>{{ type.name }}</h3>
      <el-row :gutter="20">
        <el-col v-for="level in ['high', 'medium', 'low']" :key="level">
          <el-card>
            <h4>
              <el-icon :size="24">
                <component :is="getLevelIcon(level)" />
              </el-icon>
              {{ capitalizeFirstLetter(level) }} Level
            </h4>
            <div v-for="area in type[level]" :key="area.name">
              <el-card shadow="hover" class="mini-card">
                <div class="mini-card-header">
                  {{ area.name }} <span>{{ area.point }}</span>
                </div>
                <el-progress
                    :text-inside="true"
                    :stroke-width="20"
                    :percentage="((area.point / area.total_point) * 100).toFixed(2)"
                    :status="getLevelStatus(level)"
                />
                <el-collapse @change="handleChange">
                  <el-collapse-item title="Credit Indicators table" :name="area.name">
                    <el-table :data="getImpactAreaList(area.name)" stripe style="width: 100%;">
                      <el-table-column prop="credit" label="Credit" width="160"/>
                      <el-table-column prop="indicator" label="Indicator"/>
                      <el-table-column prop="point" label="Point" width="80" sortable/>
                    </el-table>
                  </el-collapse-item>
                </el-collapse>
              </el-card>
            </div>
            <div v-if="type[level].length === 0" class="no-items">
              Well done! You've completed all tasks in this level.
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <el-button type="primary" @click="downloadPDF">Download Report as PDF</el-button>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { useRoute, useRouter } from 'vue-router';
import request from "../../utils/request";
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { ArrowRight } from '@element-plus/icons-vue';
import { Check, Star, CircleClose } from '@element-plus/icons-vue';
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
} from "../../data";

const data = reactive({
  name: '',
  fullName: '',
  rating: '',
  score: '',
  categories: [] as any[]
});

const reportDimensions = ref([
  { name: 'Society', high: [], medium: [], low: [] },
  { name: 'Economy', high: [], medium: [], low: [] },
  { name: 'Environment', high: [], medium: [], low: [] }
]);

const route = useRoute();
const router = useRouter();
const id = route.params.id;


const handleChange = (val) => {
  console.log(val);
};

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

const generateReport = () => {
  request.get(`/list/institution/${id}`)
      .then((res) => {
        data.name = res.data?.name;
        data.fullName = res.data?.fullName;
        data.rating = res.data?.rating;
        data.score = res.data?.score;
        data.categories = res.data?.categories;

        reportDimensions.value.forEach(dimension => {
          dimension.high = [];
          dimension.medium = [];
          dimension.low = [];

          data.categories.forEach(category => {
            if (category.type === dimension.name) {
              category.impact_areas.forEach(impact_area => {
                dimension[getLevel(impact_area)].push(impact_area);
              });
            }
          });
        });

        ElMessage.success("Report generated successfully");
      })
      .catch((error) => {
        console.error('Error generating report:', error);
        ElMessage.error("Failed to generate report");
      });
};

const getLevel = (impact_area) => {
  const percentage = (impact_area.point / impact_area.total_point) * 100;
  if (percentage >= 70) {
    return 'high';
  } else if (percentage >= 40) {
    return 'medium';
  } else {
    return 'low';
  }
};

const capitalizeFirstLetter = (string) => {
  return string.charAt(0).toUpperCase() + string.slice(1);
};

const getLevelIcon = (level) => {
  switch (level) {
    case 'high':
      return Check;
    case 'medium':
      return Star;
    case 'low':
      return CircleClose;
  }
};

const getLevelStatus = (level) => {
  switch (level) {
    case 'high':
      return 'success';
    case 'medium':
      return 'warning';
    case 'low':
      return 'exception';
  }
};

const countImpactAreas = (type, level) => {
  return type[level].length;
};

generateReport();

const goBack = () => {
  window.history.back();window.history.back();
}

const downloadPDF = () => {
  const reportElement = document.querySelector('.report-container');
  if (reportElement) {
    html2canvas(reportElement).then(canvas => {
      const imgData = canvas.toDataURL('image/png');
      const pdf = new jsPDF();
      pdf.addImage(imgData, 'PNG', 10, 10, 180, 160);
      pdf.save('Sustainability_Report.pdf');
    }).catch(error => {
      console.error('Error generating PDF:', error);
      ElMessage.error("Failed to download PDF");
    });
  }
};
</script>

<style scoped>
.report-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.center {
  text-align: center;
}

.report-section {
  margin-bottom: 30px;
}

.el-card {
  padding: 10px;
  cursor: pointer;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 10px;
}

.no-items {
  margin-top: 10px;
  color: #888;
  text-align: center;
}

.el-progress {
  margin-left: 10px;
}

.highest-score-card {
  margin-bottom: 10px;
}

.mini-card {
  padding: 10px;
}

.mini-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.circle {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px 0;
  color: #fff;
  font-weight: bold;
}

.green {
  background-color: lightgreen;
}

.yellow {
  background-color: lightyellow;
  color: black;
}

.red {
  background-color: lightcoral;
}
</style>
