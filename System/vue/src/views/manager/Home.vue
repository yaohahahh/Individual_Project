<template>
  <div class="home-container">
    <div class="card">
    <div class="banner" text="center">
      <div class="banner-desc">
        <div>Hi, <span>{{ user.name }}</span> Welcome to </div>
        <h1>Sustainability Assessment System</h1>
        <p>Introduction</p>
      </div>
    </div>
      </div>
      <div class="card">
    <div class="cards" >
      <div class="card-item" style="width: 350px; height: 300px;" @click="goToComparison">
        <div class="card-content">
          <h3>Comparison</h3>
          <p>Introduction</p>
        </div>
      </div>
      <div class="card-item" style="width: 350px; height: 300px;" @click="goToReport">
        <div class="card-content">
          <el-icon :size="50" >
            <Check />
          </el-icon>
          <h3>Report</h3>
          <p>Introduction</p>
          <p v-if="isAdmin"> admin的特殊功能</p>
        </div>
      </div>

    </div>

        </div>
    <div class="card" v-if="!isLoggedIn">
      <div class="card-item" @click="goToLogin">
        <div class="card-content">
          <el-icon :size="24" >
            <Check />
          </el-icon>
          <h3>Login / Register</h3>
          <p>Introduction</p>
        </div>

      </div>
    </div>

    <div class="card">
    <img src="@/assets/imgs/theme-index-green.png" alt="banner" class="mobile-banner">
 </div>
  </div>
</template>

<script setup>
import request from "@/utils/request";
import { useRouter } from 'vue-router';
import {computed, reactive} from "vue";
import {Check} from "@element-plus/icons-vue";

const user = JSON.parse(localStorage.getItem('user') || '{}');
const router = useRouter();

const goToComparison = () => {
  router.push('/choose');
};

const goToReport = () => {
  router.push('/list');
};

const goToLogin = () => {
  router.push('/login');
};


const isLoggedIn = computed(() => !user);
const isAdmin = computed(() => user?.role === 'ADMIN');

</script>

<style scoped>
.home-container {
  padding: 20px;
}

.card {
  line-height: 30px;
  margin-bottom: 20px;
}

.banner {
  text-align: center;
  margin-bottom: 20px;
}

.banner-desc h1 {
  font-size: 2.5em;
  margin-bottom: 10px;
}

.banner-desc p {
  font-size: 1.2em;
}

.cards {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.card-item {

  border: 1px solid #e0e0e0;
  border-radius: 8px;
  overflow: hidden;
  transition: box-shadow 0.3s, transform 0.3s;
  cursor: pointer;
}

.card-item:hover {
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  transform: translateY(-5px);
  background-color: #dcede9;
}

.card-image img {
  width: 100%;
  height: auto;
}

.card-content {
  padding: 20px;
  text-align: center;
}

.card-content h3 {
  font-size: 1.5em;
  margin-bottom: 10px;
}

.card-content p {
  font-size: 1em;
  color: #666;
}

.mobile-banner {
  margin-top: 10px;
  display: inline-block;
  max-width: 100%;
  border-style: none;
}
</style>
