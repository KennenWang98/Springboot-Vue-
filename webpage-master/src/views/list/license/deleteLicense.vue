<template>
  <div>
    <el-table
      :data="tableData3"
      height="700px"
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
  <el-form ref="sizeForm" :model="sizeForm" label-width="180px" size="mini">
    <el-form-item label="请输入要删除的识别码编号">
      <el-input v-model="sizeForm.licenseId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click="onSubmit">立即删除</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import { requestDeleteLicense} from "../../../api/api";

  export default {
    data() {
      return {
        tableData3: [],
        sizeForm: {
          licenseId: '',
          delivery: false,
          desc: ''
        }
      };
    },
    methods: {
      onSubmit() {
        if (confirm("确定要删除该许可码吗？")) {
          this.$refs.sizeForm.validate((valid) => {
            if (valid) {
              let deleteLicenseParams = {licenseId: this.sizeForm.licenseId};
              requestDeleteLicense(deleteLicenseParams).then(data => {
                console.log(data);
                let code = data.data.code;
                let msg = data.data.msg;
                if (code !== 200) {
                  this.$message({
                    message: msg,
                  });
                }else if(code === 200){
                  alert('删除成功，请刷新界面');
                }
              });
            } else {
              console.log('error submit!!');
              return false;
            }
          });
          console.log('submit!');
        }
      }
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
