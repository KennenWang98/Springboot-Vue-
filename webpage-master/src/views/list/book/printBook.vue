<template>
  <el-table
    :data="tableData3"
    height="800px"
    border
    style="width: 831px">
    <el-table-column
      prop="bookId"
      label="教材编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="bookRank"
      label="教材等级"
      width="100">
    </el-table-column>
    <el-table-column
      prop="subjectId"
      label="学科编号"
      width="280">
    </el-table-column>
    <el-table-column
      prop="bookName"
      label="教材名称"
      width="200">
    </el-table-column>
    <el-table-column
      prop="bookIcon"
      label="教材图片"
      width="150">
      <template  slot-scope="scope" >
        <img :src=scope.row.bookIcon min-width="70" height="70" />
      </template>
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
      axios.get('/new/book/print')
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
