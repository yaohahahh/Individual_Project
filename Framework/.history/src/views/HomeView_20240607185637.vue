<template>
  <div>
    <el-container>
      <!--Header-->
      <el-header>

      </el-header>
      <el-container>
        <!--Aside-->
        <el-aside width="200px">
          <div> aside
            
          </div>

        </el-aside>

        <!--Main-->
        <el-main>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'HomeView',

}
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 60px;
    height: 60px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>