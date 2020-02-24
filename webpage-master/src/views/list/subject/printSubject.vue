<template>
  <el-table
    :data="tableData3"
    height="800px"
    border
    style="width: 251px">
    <el-table-column
      prop="subjectId"
      label="学科编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="subjectName"
      label="学科名称"
      width="150">
    </el-table-column>
  </el-table>
</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        tableData3: []
      }
    },
    methods:{

    },
    mounted:function(){
      let _this = this
      axios.get('/new/subject/print')
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
