<template>
  <el-table
    :data="tableData3"
    height="800px"
    border
    style="width: 1191px">
    <el-table-column
      prop="schoolId"
      label="学校编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="schoolName"
      label="学校名称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="classNum"
      label="班级数量"
      width="100">
    </el-table-column>
    <el-table-column
      prop="studentNum"
      label="学生数量"
      width="100">
    </el-table-column>
    <el-table-column
      prop="teacherNum"
      label="教师数量"
      width="100">
    </el-table-column>
    <el-table-column
      prop="codeNum"
      label="许可码数量"
      width="100">
    </el-table-column>
    <el-table-column
      prop="principal"
      label="校长"
      width="100">
    </el-table-column>
    <el-table-column
      prop="schoolText"
      label="学校简介"
      width="200">
    </el-table-column>
    <el-table-column
      prop="schoolIcon"
      label="学校图片"
      width="150">
      <template  slot-scope="scope" >
        <img :src=scope.row.schoolIcon min-width="70" height="70" />
      </template>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="120">
      <template slot-scope="scope">
        <el-button
          @click.native.prevent="searchTeacher(scope.row)"
          type="text"
          size="small">
          查看教师列表
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
      searchTeacher(row){
        this.$router.push({path:'/searchTeacher',
          query: {schoolId:row.schoolId}})
      },
    },
    mounted:function(){
      let _this = this
      axios.get('/new/school/print')
        .then(function(res){
          console.log(res);
          _this.tableData3 = res.data.data
        })
        .catch(function(error) {
          console.log(error);
        })
    },
  }
</script>

