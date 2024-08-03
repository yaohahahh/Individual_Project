<template>
  <div class="home-container">
    <div class="card">
    <div class="banner" text="center">
      <div class="banner-desc">
        <div>Hi, <span>{{ user.name }}</span> Welcome to </div>
        <h1>Sustainability Assessment System</h1>
        <h3>This platform is designed to help higher education institutions assess and enhance their sustainability performance. </h3>
        <p>Use the tools to promote sustainable practices and drive positive change within your institution.</p>
        <div v-if="isAdmin">  As an administrator, you have full access to all features, enabling you to manage data, analyze sustainability metrics, and generate comprehensive reports.</div>
      </div>
    </div>
      </div>
      <div class="card">
    <div class="cards" >
      <div class="card-item" style="width: 450px; height: auto;" @click="goToComparison">
        <div class="card-content">
          <h3>Comparison</h3>
          <h5> Identify strengths, weaknesses, and opportunities for improvement in sustainability practices of your institution.</h5>
          <p> Visualizing key metrics across environmental, social, and economic dimensions. </p>
          <p> Make informed decisions to foster a more sustainable future for higher education!</p>
        </div>
      </div>
      <div class="card-item" style="width: 450px; height: auto;" @click="goToReport">
        <div class="card-content">
          <h3>Report</h3>
          <h5>Generate detailed sustainability reports for your institution. </h5>
          <p>Include comprehensive analyses and visual representations of your sustainability data, covering various dimensions and indicators. </p>
          <p> Utilize this feature to document achievements and to highlight areas for further improvement. </p>

        </div>
      </div>
    </div>

        </div>

    <div class="card">
    <img src="@/assets/imgs/theme-index-green.png" alt="banner" class="mobile-banner">
 </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import {computed, reactive} from "vue";

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
