<template>
  <div>
    <stu-tap @onTapChange="onTapChange"></stu-tap>
    <add-select v-if="(currentComponent === 'tab1')"></add-select>
    <add-fill-blank v-if="(currentComponent === 'tab2')"></add-fill-blank>
    <add-jugement v-if="(currentComponent === 'tab3')"></add-jugement>
    <i-message id="message"></i-message>
  </div>
</template>

<script>
  import StuTap from '../../components/StuTap'
  import AddSelect from '../../components/problemsCom/AddSelect'
  import AddFillBlank from '../../components/problemsCom/AddFillBlank'
  import addJugement from '../../components/problemsCom/addJugement'
  export default {
    components: {
      AddSelect,
      AddFillBlank,
      addJugement,
      StuTap
    },
    data () {
      return {
        currentComponent: 'tab1'
      }
    },
    methods: {
      onChange (e) {
        console.log('onChange', e)
        this.current = e.mp.detail.current
      },
      onTapChange (value) { /// tab栏的函数
        this.currentComponent = value
        console.log(this.currentComponent)
      }
    }
  }
</script>

<style scoped>

</style>
