<template>
  <div>

    <div class="card" style="margin-bottom: 10px;">
      <el-input style="width: 300px; margin-right: 10px" v-model="data.name" placeholder="请输入关键字查询" prefix-icon="Search" />
      <el-input style="width: 300px; margin-right: 10px" v-model="data.no" placeholder="请输入no查询" prefix-icon="Search" />
      <el-button type="primary" @click="load" >查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
      <el-table stripe :data="data.tableData">
        <el-table-column label="名称" prop="name"></el-table-column>
        <el-table-column label="描述" prop="descr"></el-table-column>
        <el-table-column label="课时" prop="times"></el-table-column>
        <el-table-column label="操作" align="center" width="160">
          <template v-slot="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination background layout="prev, pager, next" :total="data.total"
                     @current-change="handleCurrentChange"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum"/>
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
    </el-dialog>

  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive} from "vue";
import {ElMessageBox} from "element-plus";
import {ElMessage} from "element-plus";


request.get('/').then(res => {
  console.log(res)
})

const data = reactive({
  name:'',
  no:'',
  descr:'',
  times:'',
  total:0,
  tableData: [ ],
  pageNum: 1,
  pageSize: 6,
  formVisible:false, //不展示新增弹窗
  form:{}
})

const load = () => {
  request.get('/course/selectPage',{
    params: {
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      name: data.name,
      no: data.no
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data?.total || 0
  })
}
//获取后台数据
load()
//当翻页的时候重新加载数据
const handleCurrentChange = () => {
  load()
}

const reset = () => {
  data.name=''
  data.no=''
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