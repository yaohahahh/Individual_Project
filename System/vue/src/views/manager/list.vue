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
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">Add Institution</el-button>
      </div>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <el-table stripe :data="filteredData">
        <el-table-column label="Institution Name" prop="name">
          <template #default="{ row }">
            <el-link @click="goToReport(row.id)">{{ row.name }}</el-link>
          </template>
        </el-table-column>
        <el-table-column label="Score" prop="score"></el-table-column>
        <el-table-column label="Rating" prop="rating"></el-table-column>
        <el-table-column label="Operation" align="center" width="160">
          <template v-slot="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">Edit</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination background layout="prev, pager, next" :total="data.total"
                     @current-change="handleCurrentChange"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"/>
    </div>

    <el-dialog title="Institution Information" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="auto" label-position="left" style="padding-right: 50px" status-icon>

        <el-form-item label="Name" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Full_Name" prop="full_name">
          <el-input v-model="data.form.full_name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Score" prop="score">
          <el-input v-model="data.form.score" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Rating" prop="rating">
          <el-select v-model="data.form.rating" placeholder="please select its rating">
            <el-option label="Gold" value="Gold" />
            <el-option label="Silver" value="Silver" />
            <el-option label="Bronze" value="Bronze" />
            <el-option label="Platinum" value="Platinum" />
          </el-select>
        </el-form-item>

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

<script setup>
import request from "@/utils/request";
import { reactive, ref, computed, onMounted } from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();

const data = reactive({
  name: '',
  full_name: '',
  score: '',
  rating: '',
  total: 0,
  tableData: [],
  pageNum: 1,
  pageSize: 6,
  formVisible: false, // 不展示新增弹窗
  form: {
  }
});



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

// 打开新增按钮的弹窗
const handleAdd = () => {
  data.form = {  }; // 清空数据并初始化 scores 对象
  data.formVisible = true; // 打开弹窗
};

// 编辑表单
const handleEdit = (row) => {
  data.form = { ...row }; // 将已有数据填充到表单中，并初始化 scores 对象
  data.formVisible = true;
};

// 删除数据
const handleDelete = (id) => {
  request.delete(`/list/delete/${id}`).then(() => {
    load(); // 重新加载数据
    ElMessage.success("删除成功");
  }).catch(error => {
    ElMessage.error("删除失败");
    console.error('Error deleting data:', error);
  });
};


// 保存数据到后台
const save = () => {
  // 提交基本信息并创建新institution
  request.request({
    //通过到底id是不是已存在来判断update/add
    url: data.form.id ? '/list/update' : '/list/add',
    method: data.form.id ? 'PUT' : 'POST',
    data: data.form
  }).then(res => {
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
</script>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
}
</style>
