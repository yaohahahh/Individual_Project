<template>
  <div class="container">
    <div class="search-section">
      <el-input
          placeholder="Search for Institution Name"
          v-model="searchQuery"
          clearable
          @input="filterData"
      ></el-input>
      <el-button v-if="isAdmin" type="primary" @click="handleAdd" class="add-button">Add Institution</el-button>
    </div>

    <div class="card">
      <el-table stripe :data="filteredData">
        <el-table-column label="Institution" prop="name" width="120" sortable>
          <template #default="{ row }">
            <el-link @click="goToReport(row.id)">{{ row.name }}</el-link>
          </template>
        </el-table-column>
        <el-table-column label="Full Name" prop="fullName" sortable></el-table-column>
        <el-table-column label="Score" prop="score" width="100" sortable></el-table-column>
        <el-table-column
            label="Rating"
            prop="rating"
            width="100"
            :filters="[
                { text: 'Platinum', value: 'Platinum' },
                { text: 'Gold', value: 'Gold' },
                { text: 'Silver', value: 'Silver' },
                { text: 'Bronze', value: 'Bronze' }
          ]"
            :filter-method="filterTag"
            filter-placement="bottom-end"
        >
          <template #default="scope">
            <el-tag
                :class="getTagClass(scope.row.rating)"
                disable-transitions
            >
              {{ scope.row.rating }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column v-if="isAdmin" label="Operation" align="center" width="200">
          <template v-slot="scope">
            <div class="operation-buttons">
              <el-button type="primary" @click="handleEdit(scope.row)">Edit</el-button>
              <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
        title="Institution Information List"
        width="40%"
        v-model="data.formVisible"
        :close-on-click-modal="false"
        destroy-on-close
    >
      <el-form :model="data.form" :rules="rules" ref="dataForm" label-width="auto" label-position="left" status-icon>
        <el-form-item label="Name" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Full Name" prop="fullName">
          <el-input v-model="data.form.fullName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Score" prop="score">
          <el-input v-model="data.form.score" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Rating" prop="rating">
          <el-select v-model="data.form.rating" placeholder="please select its rating">
            <el-option label="Platinum" value="Platinum" />
            <el-option label="Gold" value="Gold" />
            <el-option label="Silver" value="Silver" />
            <el-option label="Bronze" value="Bronze" />
          </el-select>
        </el-form-item>
        <el-form-item label="STARS Link" prop="link">
          <el-input v-model="data.form.link" autocomplete="off" />
        </el-form-item>
          <el-form-item>
            <div>
              Question about information? Please check on
              <el-link href="https://reports.aashe.org/institutions/participants-and-reports/" target="_blank">STARS</el-link>
            </div>
          </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitForm">Save</el-button>
          <el-button @click="data.formVisible = false">Cancel</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import request from "@/utils/request";
import { reactive, ref, computed, onMounted } from "vue";
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter();

// 响应式数据
const data = reactive({
  name: '',
  fullName: '',
  score: '',
  rating: '',
  link: '',
  tableData: [],
  formVisible: false,
  form: {}
});

// 表单验证规则
const rules = {
  name: [{ required: true, message: 'Please input the name', trigger: 'blur' }],
  fullName: [{ required: true, message: 'Please input the full name', trigger: 'blur' }],
  score: [{ required: true, message: 'Please input the score', trigger: 'blur' }],
  rating: [{ required: true, message: 'Please select a rating', trigger: 'change' }],
  link: [{ message: 'Please input the STARS link', trigger: 'blur' }]
};

// 搜索输入框的引用
const searchQuery = ref('');
// 表单的引用
const dataForm = ref(null);

const load = () => {
  request.get('/list/selectAll', {
    params: {
      name: data.name
    }
  }).then(res => {
    console.log(res);
    data.tableData = res;
  }).catch(error => {
    console.error('Error loading data:', error);
  });
};
// 组件挂载时加载数据
onMounted(load);

// 计算属性，用于过滤数据
const filteredData = computed(() => {
  const query = searchQuery.value.toLowerCase();
  return data.tableData.filter(item => item.fullName.toLowerCase().includes(query));
});

// 搜索处理函数
const filterData = () => {
  data.fullName = searchQuery.value;
  load();
};

// 跳转到报告页面
const goToReport = (id) => {
  router.push({name: 'report', params: {id}});
};

// 打开新增弹窗
const handleAdd = () => {
  data.form = {};
  data.formVisible = true;
};

// 编辑表单
const handleEdit = (row) => {
  data.form = {...row};
  data.formVisible = true;
};

// 删除数据
const handleDelete = (id) => {
  request.delete(`/list/delete/${id}`).then(() => {
    load();
    ElMessage.success("Delete successfully");
  }).catch(error => {
    ElMessage.error("Delete failed");
    console.error('Error deleting data:', error);
  });
};

// 保存数据
const save = () => {
  request.request({
    url: data.form.id ? '/list/update' : '/list/add',
    method: data.form.id ? 'PUT' : 'POST',
    data: data.form
  }).then(res => {
    if (res.code === '200') {
      load();
      data.formVisible = false;
      ElMessage.success("Save Successfully! Please go to Report page to edit points.");
    } else {
      ElMessage.error("Please fill all fields");
    }
  }).catch(error => {
    ElMessage.error("Save failed");
    console.error('Error saving data:', error);
  });
};

// 过滤标签
const filterTag = (value, row) => {
  return row.rating === value;
};

// 获取标签类型
const getTagClass = (rating) => {
  switch (rating) {
    case 'Gold':
      return 'gold-tag';
    case 'Silver':
      return 'silver-tag';
    case 'Bronze':
      return 'bronze-tag';
    case 'Platinum':
      return 'platinum-tag';
    default:
      return '';
  }
};

const submitForm = () => {
  if (dataForm.value) {
    dataForm.value.validate((valid) => {
      if (valid) {
        save(); // 保存数据
      } else {
        console.log('Validation failed');
        return false;
      }
    });
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
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  box-sizing: border-box;
}

.search-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.add-button {
  margin-left: 10px;
}

.card {
  flex: 1;
  margin-bottom: 20px;
  background-image: url("@/assets/imgs/theme-index-green.png");
  background-size: contain;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
}

.operation-buttons {
  display: flex;
  justify-content: space-around;
  gap: 10px;
}

/* 自定义标签颜色 */
.gold-tag {
  background-color: #FFFACD;
  color: #000;
}

.silver-tag {
  background-color: #C0C0C0;
  color: #fff;
}

.bronze-tag {
  background-color: #CD7F32;
  color: #fff;
}

.platinum-tag {
  background-color: #E5E4E2;
  color: #000;
}
</style>
