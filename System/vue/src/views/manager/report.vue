<template>
  <div>
    <h1>{{ data.name }} </h1>
    <h2>{{data.full_name}}</h2>
    <el-button type="primary" @click="handleEdit(data.categories)">Point Edit</el-button>


    <div class="card" style="margin-bottom: 10px">
      <el-descriptions direction="vertical" :column="4" :size="size" border>
        <el-descriptions-item label="Rating">
          <el-tag type="info">{{ data.rating }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Score">{{ data.score }}</el-descriptions-item>
        <el-descriptions-item label="STARS link">
        </el-descriptions-item>

      </el-descriptions>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <div class="card" style="margin-bottom: 10px">
        <h3>Society</h3>
        <div class="demo-collapse">
          <el-collapse v-model="activeNames" @change="handleChange" >
            <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id" >
              <div v-if="category.type === 'Society'">
              <el-collapse-item :title="category.name">
                <template v-if="category.impact_areas && category.impact_areas.length" v-for="impact_area in category.impact_areas" :key="impact_area.id">
                  <el-collapse-item :title="getImpactAreaTitle(impact_area.name, category.impact_areas)" :name="category.id">
                    <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%">
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
        <h3>Economy</h3>
        <div class="demo-collapse">
          <el-collapse v-model="activeNames" @change="handleChange" >
            <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id" >
              <div v-if="category.type === 'Economy'">
                <el-collapse-item :title="category.name">
                  <template v-if="category.impact_areas && category.impact_areas.length" v-for="impact_area in category.impact_areas" :key="impact_area.id">
                    <el-collapse-item :title="getImpactAreaTitle(impact_area.name, category.impact_areas)" :name="category.id">
                      <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%">
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
        <h3>Environment</h3>
        <div class="demo-collapse">
          <el-collapse v-model="activeNames" @change="handleChange" >
            <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id" >
              <div v-if="category.type === 'Environment'">
                <el-collapse-item :title="category.name">
                  <template v-if="category.impact_areas && category.impact_areas.length" v-for="impact_area in category.impact_areas" :key="impact_area.id">
                    <el-collapse-item :title="getImpactAreaTitle(impact_area.name, category.impact_areas)" :name="category.id">
                      <el-table :data="getImpactAreaList(impact_area.name)" stripe style="width: 100%">
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
      </div>
    </div>

    <el-dialog>
      <el-form :model="data.form" label-width="100px" label-position="right" style="padding-right: 50px">
        <div v-for="category in data.categories" :key="category.id">
          <h3>{{ category.name }}</h3>
          <div v-for="impact_area in category.impact_areas" :key="impact_area.id">
            <el-form-item :label="impact_area.name" :prop="`score_${impact_area.id}`">
              <el-input v-model="data.form.scores[impact_area.id]" autocomplete="off" />
            </el-form-item>
          </div>
        </div>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="save">Save</el-button>
            <el-button @click="data.formVisible = false ">Cancel</el-button>
          </span>
      </template>
    </el-dialog>


  </div>
</template>

<script setup lang="ts">
import request from "../../utils/request";
import { reactive, onMounted, ref, computed, watch } from 'vue';
import { useRoute } from 'vue-router';
import {
  CampusList,
  CPList,
  CurriculumList,
  ILList,
  InvestmentList,
  PublicList,
  ResearchList,
  SEList,
  WWList
} from '../../data';


const route = useRoute();
const id = route.params.id;

const data = reactive({
  name: '',
  full_name: '',
  version: null,
  total_version: null,
  score: null,
  rating: '',
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
        data.full_name = res.data?.full_name;

        data.rating = res.data?.rating;
        data.score = res.data?.score;
        data.categories = res.data?.categories;

      })
      .catch((error) => {
        console.error('Error loading data:', error);
      });
};

// 编辑表单
const handleEdit = (row) => {
  data.form = { ...row }; // 将已有数据填充到表单中，并初始化 scores 对象
  data.formVisible = true;
};
// 保存数据到后台
const save = () => {
  // 提交基本信息并创建新institution
  request.put(`/list/update/${id}`)
      .then(res => {
    if (res.code === '200') {
      load(); // 重新获取数据
      data.formVisible = false; // 关闭弹窗
      ElMessage.success("保存成功");
    } else {
      ElMessage.error("Please fill all");
    }
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

    default:
      return [];
  }
};


</script>
