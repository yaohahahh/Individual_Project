import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Manager',
      component: () => import('@/views/Manager.vue'),
      redirect: '/home',
      children: [
        { path: 'home', name: 'Home', component: () => import('@/views/manager/Home.vue')},
        { path: 'course', name: 'Course', component: () => import('@/views/manager/Course.vue')},
        { path: 'student', name: 'Student', component: () => import('@/views/manager/Student.vue')},
        { path:'choose', name: 'choose', component: () => import('@/views/manager/choose.vue') },
        { path: 'comparison', name: 'comparison', component: () => import('@/views/manager/comparison.vue') },
        { path:'list', name: 'list', component: () => import('@/views/manager/list.vue') },
        { path: '/report/:id', name: 'report', component: () => import('@/views/manager/report.vue') },
        { path: '/generate/:id', name: 'generate', component: () => import('@/views/manager/generate.vue') },
      ]
    },
    { path: '/login', name: 'login', component: () => import('@/views/Login.vue'), },
    { path: '/register', name: 'register', component: () => import('@/views/Register.vue'), },
  ]
})

export default router
