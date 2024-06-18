<template>
  <div>
    <h1>{{ institution.name }}</h1>
    <div class="card" style="margin-bottom: 10px">
      <el-table :data="versions">
        <el-table-column label="Version" prop="version">
          <template #default="{ row }">
            <router-link :to="{ name: 'report', params: { id: institution.id, version: row.version } }">{{ row.version }}</router-link>
          </template>
        </el-table-column>
        <el-table-column label="Rating" prop="rating"></el-table-column>
        <el-table-column label="Score" prop="score"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request';

const route = useRoute();
const id = route.params.id;

const institution = reactive({
  id: id,
  name: '',
  versions: [],
});

const versions = reactive([]);

// 加载机构详情和版本的函数
const loadInstitution = () => {
  request.get(`/list/institution/${id}`).then(res => {
    if (res.data && Array.isArray(res.data.versions)) {
      Object.assign(institution, res.data);
      versions.push(...res.data.versions.map(v => ({
        version: v.version,
        rating: v.rating,
        score: v.score,
      })));
    } else {
      console.error('No valid data found for institution with id:', id);
    }
  }).catch(error => {
    console.error('Error loading institution data:', error);
  });
};

// 在组件挂载时调用以获取机构详情
onMounted(loadInstitution);
</script>

<style scoped>
/* 添加一些基本样式 */
</style>
