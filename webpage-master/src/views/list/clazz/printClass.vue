<template>
  <el-table
    :data="tableData3"
    height="800px"
    border
    style="width: 1267px">
    <el-table-column
      prop="classId"
      label="班级编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="className"
      label="班级名称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="schoolId"
      label="学校编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="teaId"
      label="教师编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="code"
      label="识别码"
      width="200">
    </el-table-column>
    <el-table-column
      prop="classIcon"
      label="班级图片"
      width="100">
      <template  slot-scope="scope" >
        <img :src=scope.row.classIcon min-width="70" height="70" />
      </template>
    </el-table-column>
    <el-table-column
      prop="classQrCode"
      label="班级二维码"
      width="150">
    </el-table-column>
    <el-table-column
      prop="classText"
      label="班级简介"
      width="150">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="班级教材"
      width="120">
      <template slot-scope="scope">
        <el-button
          @click.native.prevent="searchBook(scope.row)"
          type="text"
          size="small">
          查看班级教材
        </el-button>
      </template>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="班级学生"
      width="120">
      <template slot-scope="scope">
        <el-button
          @click.native.prevent="searchStudent(scope.row)"
          type="text"
          size="small">
          查看班级学生
        </el-button>
      </template>
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
      searchBook(row){
        this.$router.push({path:'/searchBook',
          query: {classId:row.classId}})
      },
      searchStudent(row){
        this.$router.push({path:'/searchStudent',
          query: {classId:row.classId}})
      },
    },
    mounted:function(){
      let _this = this
      axios.get('/new/class/print')
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
