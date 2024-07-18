<template>
  <div>
    <div style="height: 60px; background-color: #fff; display: flex; align-items: center; border-bottom: 1px solid #ddd">
      <div style="flex: 1">
        <div style="padding-left: 20px; display: flex; align-items: center">
          <img src="@/assets/imgs/logo.png" alt="" style="width: 40px">
          <div style="font-weight: bold; font-size: 24px; margin-left: 5px">Sustainability Assessment System</div>
        </div>
      </div>
    </div>

    <div style="display: flex">
      <div class="header-menu">
        <el-menu router mode="horizontal"
                 :default-active="$route.path"
                 :default-openeds="['/home', '2']"
                 background-color="#ffffff"
                 text-color="#333"
                 active-text-color="#11A983"
        >
          <el-menu-item index="/home">
            <el-icon><HomeFilled /></el-icon>
            <span>Home</span>
          </el-menu-item>
          <el-menu-item index="/choose">
            <el-icon><PieChart /></el-icon>
            <span>Comparison</span>
          </el-menu-item>
          <el-menu-item index="/list">
            <el-icon><Memo /></el-icon>
            <span>Report</span>
          </el-menu-item>
          <el-menu-item v-if="!isLoggedIn" index="/login">
            <el-icon><User /></el-icon>
            <span>Login</span>
          </el-menu-item>
          <el-menu-item v-if="isLoggedIn" index="login" @click="logout">
            <el-icon><User /></el-icon>
            <span>LogOut</span>
          </el-menu-item>
        </el-menu>
      </div>

      <div style="flex: 1; width: 0; background-color: #f8f8ff; padding: 10px">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';

const $route = useRoute();
const router = useRouter();

const state = reactive({
  user: JSON.parse(localStorage.getItem('user'))
});

const isLoggedIn = computed(() => !!state.user);
const isAdmin = computed(() => state.user?.role === 'ADMIN');

const logout = () => {
  localStorage.removeItem('user');
  state.user = null;
  router.push('/home');
};
</script>

<style scoped>
.el-menu-item.is-active {
  background-color: #dcede9 !important;
}
.el-menu-item:hover {
  color: #11A983;
}
.header-menu {
  position: absolute;
  top: 0;
  right: 0;
  width: 300px; /* 设置菜单栏的宽度 */
  height: 60px; /* 设置菜单栏的高度 */
  display: flex;
  justify-content: flex-end; /* 让菜单右对齐 */
  align-items: center; /* 垂直居中对齐 */
  background-color: #f0f0f0; /* 设置菜单栏的背景颜色 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 可选：添加阴影效果 */
  z-index: 1000; /* 设置一个较高的 z-index 以确保菜单显示在其他内容之上 */
}
</style>
