<template>
  <div class="report">
    <h1>{{ institutionName }} Report - {{ reportDate }}</h1>

    <!-- 树状表格展示五大类数据 -->
    <div class="tree-table">
      <table>
        <tr>
          <th>Category</th>
          <th>Value</th>
        </tr>
        <!-- 使用递归组件展示树状数据 -->
        <TreeTableRow v-for="item in treeData" :key="item.id" :item="item" />
      </table>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request';

const route = useRoute();
const institutionId = route.params.id;
const reportDate = route.params.date; // 从路由中获取报告日期参数

const institutionName = reactive('');

const treeData = reactive([]);

// 加载机构信息和报告五大类数据的函数
const loadInstitutionAndTreeData = () => {
  // 根据 institutionId 和 reportDate 获取机构信息和报告五大类数据
  request.get(`/list/institution/${institutionId}/report/${reportDate}/tree`).then(res => {
    if (res.data) {
      institutionName.value = res.data.institutionName;
      treeData.push(...res.data.treeData);
    } else {
      console.error('No data found for institution report with id:', institutionId, 'and date:', reportDate);
    }
  }).catch(error => {
    console.error('Error loading institution report data:', error);
  });
};

// 在组件挂载时调用以获取机构信息和报告五大类数据
onMounted(loadInstitutionAndTreeData);
</script>

<style scoped>
.report {
  margin: 20px;
}

.tree-table {
  margin-top: 20px;
}

.tree-table table {
  width: 100%;
  border-collapse: collapse;
}

.tree-table th, .tree-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.tree-table th {
  background-color: #f2f2f2;
}
</style>
