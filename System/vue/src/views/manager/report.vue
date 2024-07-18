<template>
  <div class="container">
    <el-breadcrumb :separator-icon="ArrowRight">
      <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
      <el-breadcrumb-item>
        <a href="/list">Institution List</a>
      </el-breadcrumb-item>
      <el-breadcrumb-item>Institution Report</el-breadcrumb-item>
    </el-breadcrumb>
    

    <h1 class="center">{{ data.name }} </h1>
    <h2 class="center">{{ data.fullName }}</h2>
    <div class="button-group center">
      <el-button v-if="isAdmin" type="primary" @click="handleEdit">Point Edit</el-button>
      <el-button type="primary" @click="navigateToGenerateReport">Generate Report</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <el-descriptions direction="vertical" :column="4" :size="size" border>
        <el-descriptions-item label="Rating">
          <el-tag type="info">{{ data.rating }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Score">{{ data.score }}</el-descriptions-item>
        <el-descriptions-item label="STARS link">
          <a :href="data.link" target="_blank">{{ data.link }}</a>
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <h3>Society</h3>
    <div class="card" style="margin-bottom: 10px">
      <el-collapse v-model="activeNames" @change="handleChange">
        <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id">
          <div v-if="category.type === 'Society'">
            <h4>{{ category.name }}</h4>
            <template v-if="category.impact_areas && category.impact_areas.length" v-for="impact_area in category.impact_areas" :key="impact_area.id">
              <el-collapse-item :title="getImpactAreaTitle(impact_area.name, category.impact_areas)" :name="impact_area.id">
                <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%">
                  <el-table-column prop="credit" label="Credit" width="150" />
                  <el-table-column prop="indicator" label="Indicator" />
                  <el-table-column prop="point" label="Point" width="80" sortable />
                </el-table>
              </el-collapse-item>
            </template>
          </div>
        </template>
      </el-collapse>
    </div>

    <h3>Economy</h3>
    <div class="card" style="margin-bottom: 10px">
      <el-collapse v-model="activeNames" @change="handleChange">
        <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id">
          <div v-if="category.type === 'Economy'">
            <h4>{{ category.name }}</h4>
            <template v-if="category.impact_areas && category.impact_areas.length" v-for="impact_area in category.impact_areas" :key="impact_area.id">
              <el-collapse-item :title="getImpactAreaTitle(impact_area.name, category.impact_areas)" :name="impact_area.id">
                <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%">
                  <el-table-column prop="credit" label="Credit" width="150" />
                  <el-table-column prop="indicator" label="Indicator" />
                  <el-table-column prop="point" label="Point" width="80" sortable />
                </el-table>
              </el-collapse-item>
            </template>
          </div>
        </template>
      </el-collapse>
    </div>

    <h3>Environment</h3>
    <div class="card" style="margin-bottom: 10px">
      <el-collapse v-model="activeNames" @change="handleChange">
        <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id">
          <div v-if="category.type === 'Environment'">
            <h4>{{ category.name }}</h4>
            <template v-if="category.impact_areas && category.impact_areas.length" v-for="impact_area in category.impact_areas" :key="impact_area.id">
              <el-collapse-item :title="getImpactAreaTitle(impact_area.name, category.impact_areas)" :name="impact_area.id">
                <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%">
                  <el-table-column prop="credit" label="Credit" width="150" />
                  <el-table-column prop="indicator" label="Indicator" />
                  <el-table-column prop="point" label="Point" width="80" sortable />
                </el-table>
              </el-collapse-item>
            </template>
          </div>
        </template>
      </el-collapse>
    </div>
  </div>
  <el-dialog title="Points Edit" width="60%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
    <el-form :model="data.form" label-width="auto" label-position="left" style="padding-right: 50px" status-icon>
      <div v-for="category in data.categories" :key="category.id">
        <h3>{{ category.name }}</h3>
        <div v-for="impact_area in category.impact_areas" :key="impact_area.id">
          <el-form-item :label="impact_area.name + ' (' + impact_area.total_point + ')' " :prop="`score_${impact_area.id}`">
            <el-input
                v-model="impact_area.point"
                autocomplete="off"
                @input="validatePoint(impact_area)"
            />
          </el-form-item>
        </div>
      </div>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">Save</el-button>
        <el-button @click="data.formVisible = false">Cancel</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import request from "../../utils/request";
import { reactive, onMounted, ref, computed, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { ArrowRight } from '@element-plus/icons-vue';
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

const route = useRoute();
const id = route.params.id;

const data = reactive({
  name: '',
  fullName: '',
  score: null,
  rating: '',
  link: '',
  tableData: [],
  categories: [] as any[],
  form: {},
  formVisible: false // 不展示新增弹窗
});

// Load data function
const load = () => {
  request.get(`/list/institution/${id}`)
      .then((res) => {
        data.name = res.data?.name;
        data.fullName = res.data?.fullName;
        data.rating = res.data?.rating;
        data.link = res.data?.link;
        data.score = res.data?.score;
        data.categories = res.data?.categories;
      })
      .catch((error) => {
        console.error('Error loading data:', error);
      });
};

const router = useRouter();
const navigateToGenerateReport = () => {
  router.push({ name: 'generate', params: { id } });
};

// 编辑表单
const handleEdit = () => {
  data.formVisible = true;
  // 将已有数据填充到表单中，并初始化 scores 对象
};

const validatePoint = (impact_area) => {
  let inputValue = impact_area.point.trim(); // 去除首尾空格

  // 如果输入为空，则将 point 设置为 0
  if (inputValue === '') {
    impact_area.point = 0;
    return;
  }

  // 检查输入中是否存在小数点
  const hasDecimalPoint = inputValue.includes('.');

  // 先移除非数字和小数点以外的字符
  inputValue = inputValue.replace(/[^\d.]/g, '');

  // 如果输入只包含小数点，或者小数点后面没有数字，则不进行 parseFloat
  if (inputValue === '.' || inputValue.endsWith('.')) {
    impact_area.point = inputValue;
    return;
  }

  // 使用 parseFloat 解析输入的数字
  const parsedValue = parseFloat(inputValue);

  // 如果无法解析为有效数字，则设置 point 为 0
  if (isNaN(parsedValue) || parsedValue < 0) {
    impact_area.point = 0;
  } else if (parsedValue > impact_area.total_point) {
    impact_area.point = impact_area.total_point;
    ElMessage.warning(`The point cannot exceed ${impact_area.total_point}`);
  } else {
    impact_area.point = parsedValue;
  }
};

// 保存数据到后台
const save = () => {
  // 提交更新后的数据
  request.put(`/list/updatePoint`, data)
      .then(res => {
        load(); // 重新获取数据
        data.formVisible = false; // 关闭弹窗
        ElMessage.success("保存成功");
      }).catch(error => {
    ElMessage.error("保存失败");
    console.error('Error saving data:', error);
  });
};

// Collapse panel
const activeNames = ref(['1']);

const handleChange = (val: string | string[]) => {
  console.log(val);
};

// List
const size = ref('default');

const getImpactAreaTitle = (categoryName: string, impactAreas: any[]) => {
  const impactArea = impactAreas.find(area => area.name === categoryName);
  if (impactArea) {
    return `${categoryName} ${impactArea.point}/${impactArea.total_point}`;
  }
  return categoryName;
};

onMounted(load);

const getImpactAreaList = (impactAreaName: string) => {
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

// 判断是否是管理员
const isAdmin = computed(() => {
  const user = JSON.parse(localStorage.getItem('user'));
  return user && user.role === 'ADMIN';
});

</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.center {
  text-align: center;
}

.button-group {
  margin-bottom: 20px;
}

.card {
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  background-color: #fff;
}

.inner-card {
  padding: 10px;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.demo-collapse {
  margin-bottom: 10px;
}
</style>
