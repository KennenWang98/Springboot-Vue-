<template>
  <el-table
    :data="tableData3"
    height="151px"
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
  import {requestSearchBook} from "../../../api/api";

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
      let searchBookParams = { classId: classId1};
      requestSearchBook(searchBookParams).then(data => {
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
