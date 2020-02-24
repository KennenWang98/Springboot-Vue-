<template>
  <el-table
    :data="tableData3"
    height="800px"
    border
    style="width: 1011px">
    <el-table-column
      prop="licenseId"
      label="识别码编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="code"
      label="识别码"
      width="280">
    </el-table-column>
    <el-table-column
      prop="activeTime"
      label="激活日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="remainTime"
      label="到期时间"
      width="150">
    </el-table-column>
    <el-table-column
      prop="bookId"
      label="教材编号"
      width="150">
    </el-table-column>
    <el-table-column
      prop="isUsed"
      label="使用状态"
      width="150">
    </el-table-column>
  </el-table>
</template>

<script>
import axios from 'axios'

  export default {
    data() {
      return {
        tableData3: [],
      }
    },
    methods:{

    },
    mounted:function(){
      let _this = this
      axios.get('/new/code/print')
        .then(function(res){
          console.log(res);
          _this.tableData3 = res.data.data
      })
        .catch(function(error) {
          console.log(error);
        })
    }
  }
</script>
