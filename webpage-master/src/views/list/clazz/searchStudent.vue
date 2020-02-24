<template>
  <el-table
    :data="tableData3"
    height="800px"
    border
    style="width: 651px">
    <el-table-column
      prop="stuId"
      label="学生编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="stuName"
      label="学生姓名"
      width="100">
    </el-table-column>
    <el-table-column
      prop="phoneNumber"
      label="联系方式"
      width="200">
    </el-table-column>
    <el-table-column
      prop="gender"
      label="学生性别"
      width="100">
    </el-table-column>
    <el-table-column
      prop="stuIcon"
      label="学生照片"
      width="150">
      <template  slot-scope="scope" >
        <img :src=scope.row.stuIcon min-width="70" height="70" />
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import { requestSearchStudents} from "../../../api/api";

  export default {
    data() {
      return {
        tableData3: []
      }
    },
    methods:{

    },
    mounted:function(){
      let classId1 = this.$route.query.classId
      //console.log(schoolId1)
      let searchStudentParams = { classId: classId1};
      requestSearchStudents(searchStudentParams).then(data => {
        //console.log(data);
        let code = data.data.code;
        let msg = data.data.msg;
        this.tableData3 = data.data.data;
        if (code !== 200) {
          this.$message({
            message: msg,
          });
        }
      });
    }
  }
</script>
