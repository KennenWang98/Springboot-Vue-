<template>
  <div>
    <div>
      <i-cell-group>
        <i-cell title="添加班级" is-link url='/pages/createClass/main'></i-cell>
        <van-divider contentPosition="center" customStyle="color: #1989fa;border-color: #1989fa;font-size: 18px;">课程</van-divider>
      </i-cell-group>
      <van-collapse :value="schoolActiveName" @change="onSchoolChange" accordion>
        <div v-for="(school, schoolIndex) in schoolList" :key="schoolIndex">
          <van-collapse-item :title="school.schoolName" :name="school.schoolId">
            <class-card :class-list="school.classList" :school-index="schoolIndex"></class-card>
          </van-collapse-item>
        </div>
      </van-collapse>
    </div>
  </div>
</template>
<script>
import CreateClass from '../pages/createClass/CreateClass'
import ClassCard from './classroom/ClassCard'
import {mapState} from 'vuex'
export default {
  components: {
    CreateClass,
    ClassCard
  },
  data () {
    return {
      schoolActiveName: '',
      teaId: 1,
      index: 1
    }
  },
  computed: {
    ...mapState(['schoolList'])
  },
  methods: {
    onSchoolChange (event) {
      this.schoolActiveName = event.mp.detail
    }
  }

}
</script>
<style scope>
</style>
