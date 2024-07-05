<template>
  <div>
    <el-row class="demo-autocomplete">
      <el-col :span="12">
        <div class="sub-title">输入想要比较的institution</div>
        <div v-for="(input, index) in data.inputs" :key="index" style="margin-bottom: 10px;">
          <el-autocomplete
              class="inline-input"
              v-model="input.value"
              :fetch-suggestions="querySearch"
              placeholder="请输入内容"
              @select="handleSelect(index, $event)"
          ></el-autocomplete>
        </div>
        <el-button type="primary" @click="navigateToComparison" icon="el-icon-check">VS</el-button>
        <el-button type="primary" @click="addInput" icon="el-icon-plus" circle>+</el-button>
        <el-button type="primary" @click="removeInput" icon="el-icon-minus" circle>-</el-button>
      </el-col>
    </el-row>
  </div>
</template>

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
                    value: item.name || item.full_name,
                    id: item.id,
                    name: item.name,
                    full_name: item.full_name
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

<style scoped>
.inline-input {
  width: 100%;
}
</style>
