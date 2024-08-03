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
  max-width: 1800px;
  padding: 60px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  text-align: center;
}

.sub-title {
  font-size: 24px;
  margin-bottom: 30px;
  font-weight: bold;
}

.input-wrapper {
  margin-bottom: 20px;
}

.inline-input {
  width: 100%;
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 30px;
}

.action-button {
  font-size: 16px;
  padding: 12px 24px;
  border-radius: 5px;
  color: #fff;
  border: none;
}

</style>

<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import request from '@/utils/request';

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
        alert('Please select at least one institution');
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


