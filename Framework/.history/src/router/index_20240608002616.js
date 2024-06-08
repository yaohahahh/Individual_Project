import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Scoring from '../views/Scoring.vue'
import Login from '../views/Login.vue'
import { component } from 'vue/types/umd'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/Scoring',
    name: 'Scoring',
    component: Scoring
  },
  {
    path: '/login',
    name: 'login',
    component
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
