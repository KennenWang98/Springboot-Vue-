<template>
  <div>
    <div>
      <i-cell-group>
        <wux-button block clear type="royal" @click="scanQrcode">加入班级</wux-button>
        <van-divider contentPosition="center" customStyle="color: #1989fa;border-color: #1989fa;font-size: 18px;">课程</van-divider>
      </i-cell-group>
      <van-collapse :value="schoolActiveName" @change="onSchoolChange" accordion>
        <div v-for="(school, schoolIndex) in schoolList" :key="schoolIndex">
          <van-collapse-item :title="school.schoolName" :name="school.schoolId">
            <student-class-card :class-list="school.classList" :school-index="schoolIndex"></student-class-card>
          </van-collapse-item>
        </div>
      </van-collapse>
    </div>
  </div>
</template>
<script>
  import StudentClassCard from './StudentClassCard'
  import {mapState} from 'vuex'
  export default {
    components: {
      StudentClassCard
    },
    data () {
      return {
        schoolActiveName: '',
        teaId: 1,
        index: 1
      }
    },
    computed: {
      ...mapState(['schoolList', 'userInformation'])
    },
    methods: {
      onSchoolChange (event) {
        this.schoolActiveName = event.mp.detail
      },
      scanQrcode () {
        // 允许从相机和相册扫码
        wx.scanCode({
          success: res => {
            let phoneNumber = this.userInformation.phoneNumber
            let classQrCode = res.result
            console.log(classQrCode)
            this.$store.dispatch('studentAddClass', {classQrCode, phoneNumber})
          },
          fail: res => {
            wx.showToast({
              title: '加入失败',
              icon: 'none',
              duration: 2000
            })
          }
        })
      }
    }

  }
</script>
<style scope>
</style>
