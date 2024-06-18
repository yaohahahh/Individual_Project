<template>
  <div class="login-container" >
    <div style="width: 350px" class="login-box">
      <div style="font-weight: bold; font-size: 24px; text-align: center; margin-bottom: 30px" > Register</div>
      <el-form :model="data.form" ref="formRef" :rules="rules" >
        <el-form-item prop="username">
          <el-input prefix-icon="User" v-model="data.form.username" placeholder="Please enter Username" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input show-password prefix-icon="Lock" v-model="data.form.password" placeholder="Please enter Password" />
        </el-form-item>
        <el-form-item prop="role">
          <el-select style="width: 100%" v-model="data.form.role" placeholder="Please select a role">
            <el-option value="ADMIN" label="Admin" ></el-option>
            <el-option value="STUDENT" label="Student" ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="register">Register</el-button>
        </el-form-item>
      </el-form>
      <div style="margin-top: 30px; text-align: center" >
        Register Successfully! Please <a href="/login"> login here</a>.
      </div>
    </div>
  </div>
</template>

<script setup>
  import {reactive, ref} from "vue";
  import request from '@/utils/request'
  import {ElMessage} from "element-plus";
  import router from '@/router';

  const data=reactive({
    form: {
      role: ''
    }
  })

  //输入校验
  const rules = reactive({
    username:[
      { required: true, message: 'Please enter Username', trigger: 'blur' },
    ],
    password:[
      { required: true, message: 'Please enter Password', trigger: 'blur' },
    ],
  })

  //表单校验
  const formRef = ref()

  const register = () => {
    formRef.value.validate((valid) => {
      if(valid) {
        request.post('/register',data.form).then(res => {
          localStorage.setItem('role', data.form.role)
          console.log(data.form.role)
          if (res.code === '200') {
            ElMessage.success('Register Successfully')
            router.push('/login');
          } else {
            ElMessage.error(res.msg)
          }
        })
      }
    })

  }




</script>

<style scoped>
.login-container {
  min-height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("@/assets/imgs/login-background.jpg");
  background-size: cover;
}
.login-box{
  background-color: #ffffff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 30px;
}

</style>

