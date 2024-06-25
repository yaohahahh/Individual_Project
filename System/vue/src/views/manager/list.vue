<template>
  <div>
    <div style="margin-bottom: 20px;">
      <el-input
          placeholder="Search for Institution Name"
          v-model="searchQuery"
          clearable
          @input="filterData"
      >
      </el-input>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <el-table stripe :data="data.tableData">
        <el-table-column label="Institution Name" prop="name">
          <template #default="{ row }">
            <el-link @click="goToReport(row.id)">{{ row.name }}</el-link>
          </template>
        </el-table-column>
        <el-table-column label="Score" prop="score"></el-table-column>
        <el-table-column label="Rating" prop="rating"></el-table-column>
        <el-table-column label="Operation" align="center" width="160"></el-table-column>
      </el-table>

    </div>

    <div class="card">
      <el-pagination background layout="prev, pager, next" :total="data.total"
                     @current-change="handleCurrentChange"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"/>
    </div>


  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive, ref, computed, onMounted} from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();

const data = reactive({
  name:'',
  score:'',
  rating:'',
  total:0,
  tableData: [ ],
  pageNum: 1,
  pageSize: 6,
  formVisible:false, //不展示新增弹窗
  form:{}
})
// 用于搜索的输入框绑定的变量
const searchQuery = ref('');

// 加载数据的函数
const load = () => {
  request.get('/list/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res => {
    console.log(res);
    data.tableData = res.data?.list || [];
    data.total = res.data?.total || 0;
  }).catch(error => {
    console.error('Error loading data:', error);
  });
};

const goToReport = (id) => {
  router.push({ name: 'report', params: { id } });
};

// 在组件挂载时调用以获取数据
onMounted(load);
// 计算属性用于过滤数据
const filteredData = computed(() => {
  const query = searchQuery.value.toLowerCase();
  return data.tableData.filter(item => item.name.toLowerCase().includes(query));
});

// 搜索处理函数
const filterData = () => {
  // 搜索时更新 data.name，然后重新加载数据
  data.name = searchQuery.value;
  load();
};

</script>

