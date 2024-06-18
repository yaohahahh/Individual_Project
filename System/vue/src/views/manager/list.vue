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
      <el-table stripe :data="filteredData">
        <el-table-column label="Institution Name" prop="name" >
          <template #default="scope">
            <router-link :to="{ name: 'report', params: { id: scope.row.id } }">
              {{ scope.row.name }}
            </router-link>
          </template>
        </el-table-column>
        <el-table-column label="Score" prop="score"></el-table-column>
        <el-table-column label="Rating" prop="rating"></el-table-column>
        <el-table-column label="operation" align="center" width="160">
          <!--
          <template v-slot="scope">
            <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
          </template>-->
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination background layout="prev, pager, next" :total="data.total"
                     @current-change="handleCurrentChange"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"/>
    </div>

    <!-- add写成一个新页面
    <div style="margin-bottom: 10px">
      <el-button type="primary" @click="handleAdd">Add</el-button>
    </div>

    <el-dialog title="信息" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="100px" label-position="right" style="padding-right: 50px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="编号" prop="no">
          <el-input v-model="data.form.no" autocomplete="off" />
        </el-form-item>
        <el-form-item label="描述" prop="descr">
          <el-input v-model="data.form.descr" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课时" prop="times">
          <el-input v-model="data.form.times" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </span>
      </template>
    </el-dialog> -->

  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive, ref, computed, onMounted} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";


request.get('/').then(res => {
  console.log(res)
})

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

// 搜索处理函数
const handleSearch = () => {
  data.pageNum = 1; // 搜索时重置页码
  load(); // 重新加载数据
};

//当翻页的时候重新加载数据
const handleCurrentChange = () => {
  load()
}

const reset = () => {
  data.name=''
  load()
}

//打开新增按钮的弹窗
const handleAdd = () => {
  data.form = {} //清空数据
  data.formVisible = true //打开弹窗
}

//保存数据到后台
const save = () => {
  request.request({
    //通过到底id是不是已存在来判断update/add
    url: data.form.id ? '/course/update' : '/course/add',
    method: data.form.id ? 'PUT' : 'POST',
    data: data.form
  }).then(res => {
    if (res.code === '200'){
      load() //重新获取数据
      data.formVisible = false //关闭弹窗
      ElMessage.success("Save Successfully")
    } else {
      ElMessage.error(res.msg)
    }
  })
}

//有参数row
const handleEdit = (row) => {
  //深拷贝
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

const handleDelete = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {
    request.delete(`/course/delete/${id}`).then(res =>{
      if (res.code === '200'){
        load() //重新获取数据
        data.formVisible = false //关闭弹窗
        ElMessage.success("Save Successfully")
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(res=>{})
}
</script>

