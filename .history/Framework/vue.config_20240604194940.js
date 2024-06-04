const { defineConfig } = require('@vue/cli-service')
const { config } = require('vue/types/umd')
module.exports = defineConfig({
  transpileDependencies: true
})
chainWebpack: config 