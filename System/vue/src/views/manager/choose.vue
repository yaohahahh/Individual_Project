<template>
  <div class="container" >
    <el-row class="demo-autocomplete" type="flex" justify="center" align="middle">
      <el-col :span="24" class="content">
        <div class="sub-title">Institutions Comparison ( Up to 3 )</div>
        <div v-for="(input, index) in data.inputs" :key="index" class="input-wrapper">
          <el-autocomplete
              class="inline-input"
              v-model="input.value"
              :fetch-suggestions="querySearch"
              placeholder="Search for Institutions You want to Compare"
              @select="handleSelect(index, $event)"
          ></el-autocomplete>
        </div>
        <div class="button-group">
          <el-button type="primary" @click="navigateToComparison" class="action-button"><el-icon><Check /></el-icon></el-button>
          <el-button type="primary" @click="addInput" class="action-button" circle><el-icon><Plus /></el-icon></el-button>
          <el-button type="primary" @click="removeInput" class="action-button" circle><el-icon><Minus /></el-icon></el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url("@/assets/imgs/theme-index-green.png");
  background-size: contain;
}

.content {
  width: 100%;
  max-width: 1800px; /* 增加宽度 */
  padding: 60px; /* 增加内边距 */
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px; /* 添加圆角 */
  text-align: center;
}

.sub-title {
  font-size: 24px; /* 增加字体大小 */
  margin-bottom: 30px; /* 增加底部外边距 */
  font-weight: bold;
}

.input-wrapper {
  margin-bottom: 20px; /* 增加底部外边距 */
}

.inline-input {
  width: 100%;
}

.button-group {
  display: flex;
  justify-content: center; /* 将按钮组居中 */
  gap: 20px; /* 增加按钮之间的间距 */
  margin-top: 30px; /* 增加顶部外边距 */
}

.action-button {
  font-size: 16px; /* 增加按钮的字体大小 */
  padding: 12px 24px; /* 增加按钮的内边距 */
  border-radius: 5px; /* 添加圆角 */
  color: #fff; /* 按钮文本颜色 */
  border: none; /* 移除按钮边框 */
}


</style>



<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import request from '@/utils/request'; // 确保导入你的请求工具

export default {
  setup() {
    const router = useRouter();

    const data = reactive({
      inputs: [{value: '', id: null}]
    });

    const querySearch = (queryString, cb) => {
      if (queryString) {
        request.get('/list/search', {params: {query: queryString}})
            .then(response => {
              console.log("Response data:", response); // Debugging line
              if (response) {
                if (Array.isArray(response)) {
                  const suggestions = response.map(item => ({
                    value: `${item.fullName} (${item.name})`,
                    id: item.id,
                    name: item.name,
                    fullName: item.fullName
                  }));
                  console.log("Mapped suggestions:", suggestions); // Debugging line
                  cb(suggestions);
                } else {
                  console.log("Response data is not an array:", response.data); // Debugging line
                  cb([]);
                }
              } else {
                console.log("No data in response:", response); // Debugging line
                cb([]);
              }
            })
            .catch(error => {
              console.error('Error fetching institutions:', error);
              cb([]);
            });
      } else {
        cb([]);
      }
    };

    const handleSelect = (index, item) => {
      console.log("Selected item:", item); // Debugging line
      data.inputs[index].value = item.value;
      data.inputs[index].id = item.id;
    };

    const navigateToComparison = () => {
      const selectedIds = data.inputs.map(input => input.id).filter(id => id !== null);

      if (selectedIds.length > 0) {
        router.push({
          name: 'comparison',
          query: {ids: selectedIds.join(',')}
        });
      } else {
        alert('请至少选择一个 institution');
      }
    };

    const addInput = () => {
      if (data.inputs.length < 3) {
        data.inputs.push({value: '', id: null});
      }
    };

    const removeInput = () => {
      if (data.inputs.length > 1) {
        data.inputs.pop();
      }
    };

    return {
      data,
      querySearch,
      handleSelect,
      navigateToComparison,
      addInput,
      removeInput
    };
  }
};
</script>


