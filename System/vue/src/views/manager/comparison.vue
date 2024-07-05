<template>
  <div>
    <el-row :gutter="10">
      <el-col
          v-for="institution in institutions"
          :key="institution.id"
          :xs="24"
          :sm="institutionColSpan"
          :md="institutionColSpan"
          :lg="institutionColSpan"
          :xl="institutionColSpan"
      >
        <div class="card" :class="{ 'highest-score': institution.isHighest }" style="margin-bottom: 10px">
          <el-progress type="circle" :percentage="institution.score"></el-progress>
          <div>{{ institution.name }}</div>
          <img :src="institution.image" alt="Institution Image" />
          <el-divider></el-divider>
          <el-descriptions direction="vertical" :column="4" :size="size" border>
            <el-descriptions-item label="Rating">
              <el-tag type="info">{{ institution.rating }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Score">{{ institution.score }}</el-descriptions-item>
            <el-descriptions-item label="Submitted Date">{{ institution.submitted_date }}</el-descriptions-item>
          </el-descriptions>
        </div>
        <el-divider content-position="center">Radar</el-divider>
        <div class="card" style="margin-bottom: 10px">
          雷达图 总分+3个大类+5个小类 9个指标
          <div class="block text-center" m="t-4">
            <span class="demonstration">右边显示表格 总分+大类4个块就行</span>
            <el-carousel trigger="click" height="150px">
              <el-carousel-item v-for="item in 4" :key="item">
                <h3 class="small justify-center" text="2xl">{{ item }}</h3>
              </el-carousel-item>
            </el-carousel>
          </div>
        </div>
        <el-divider></el-divider>
        <div v-for="category in categories" :key="category" class="category-section">
          <h3>{{ category }}</h3>
          <div class="card" style="margin-bottom: 10px">
            <h4>Curriculum
              <el-progress :text-inside="true" :stroke-width="26" :percentage="70"></el-progress>
              每个小类有小卡片展示各自的名字和指标
            </h4>
            <el-col :span="11">
              <el-card shadow="hover">
                表格里的数据做成小卡片
                credit 小圆圈写point
                描述indicator 下面的进度条是分值/小类总分
                <el-progress :percentage="50"></el-progress>
              </el-card>
            </el-col>
          </div>
        </div>



        <div class="demo-collapse">
          <el-collapse v-model="activeNames" @change="handleChange">
            <template v-for="category in institution.categories" :key="category.id">
              <div>
                <el-collapse-item :title="category.type" :name="category.type">
                  <template v-for="impact_area in category.impact_areas" :key="impact_area.id">
                    <el-collapse-item :title="impact_area.name" :name="impact_area.id">
                      <el-table :data="impact_area.list" stripe style="width: 100%">
                        <el-table-column prop="credit" label="Credit" width="160" />
                        <el-table-column prop="indicator" label="Indicator" />
                        <el-table-column prop="point" label="Point" width="60" />
                      </el-table>
                    </el-collapse-item>
                  </template>
                </el-collapse-item>
              </div>
            </template>
          </el-collapse>
        </div>


      </el-col>
    </el-row>

  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request'; // 确保导入你的请求工具

export default {
  name: 'Comparison',
  setup() {
    const route = useRoute();
    const institutions = ref([]);
    const categories = ref(['Society']); // 根据需要调整类别

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

      // 找到得分最高的机构
      let highestScore = Math.max(...institutions.value.map(inst => inst.score));
      institutions.value.forEach(inst => {
        inst.isHighest = inst.score === highestScore;
      });
    };

    const institutionColSpan = computed(() => {
      const count = institutions.value.length;
      if (count === 1) return 24;
      if (count === 2) return 12;
      return 8;
    });

    onMounted(loadInstitutions);

    return {
      institutions,
      categories,
      institutionColSpan,
    };
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.category-section {
  margin-bottom: 20px;
}

.highest-score {
  border: 2px solid green;
}
</style>
