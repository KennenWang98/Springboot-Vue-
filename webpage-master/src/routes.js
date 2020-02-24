import Login from './views/Login.vue'
import Home from './views/Home.vue'
import Homepage from './views/list/homepage.vue'
import Table1 from './views/list/license/printLicense.vue'
import Table2 from './views/list/license/createLicense.vue'
import Table3 from './views/list/license/changeLicense.vue'
import Table4 from './views/list/book/printBook.vue'
import Table5 from './views/list/book/createBook.vue'
import Table6 from './views/list/book/deleteBook.vue'
import Table7 from './views/list/clazz/printClass.vue'
import Table8 from './views/list/clazz/createClass.vue'
import Table9 from './views/list/clazz/changeClass.vue'
import Table10 from './views/list/teacher/printTeacher.vue'
import Table12 from './views/list/teacher/changeTeacher.vue'
import Table13 from './views/list/school/printSchool.vue'
import Table14 from './views/list/school/createSchool.vue'
import Table15 from './views/list/school/changeSchool.vue'
import Table16 from './views/list/license/deleteLicense'
import Table17 from './views/list/book/changeBook'
import Table18 from './views/list/clazz/deleteClass'
import Table19 from './views/list/teacher/deleteTeacher'
import Table20 from './views/list/school/deleteSchool.vue'
import Table21 from './views/list/subject/printSubject.vue'
import Table22 from './views/list/subject/createSubject.vue'
import Table23 from './views/list/subject/changeSubject.vue'
import Table24 from './views/list/subject/deleteSubject.vue'
import Table25 from './views/list/school/searchTeacher.vue'
import Table26 from './views/list/clazz/searchBook.vue'
import Table27 from './views/list/clazz/searchStudent.vue'
import Table28 from './views/list/student/printStudent.vue'

let routes = [
  {
    path: '/',
    component: Home,
    name: '',
    leaf: true,
    children: [
      { path: '/searchTeacher', component: Table25, name: 'searchTeacher' },
    ],
    hidden: true
  },
  {
    path: '/',
    component: Home,
    name: '',
    leaf: true,
    children: [
      { path: '/searchStudent', component: Table27, name: 'searchStudent' },
    ],
    hidden: true
  },
  {
    path: '/',
    component: Home,
    name: '',
    leaf: true,
    children: [
      { path: '/searchBook', component: Table26, name: 'searchBook' },
    ],
    hidden: true
  },
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/',
        component: Home,
        name: '',
        leaf: true,//只有一个节点
        iconCls: 'el-icon-menu', //图标样式class
        children: [
            { path: '/homepage', component: Homepage, name: '首页' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '识别码管理',
        // leaf: true,//只有一个节点
        iconCls: 'el-icon-message', //图标样式class
        children: [
            { path: '/table1', component: Table1, name: '查看识别码' },
            { path: '/table2', component: Table2, name: '生成识别码' },
            { path: '/table3', component: Table3, name: '修改识别码状态' },
            { path: '/table16', component: Table16, name: '删除识别码' },
        ]
    },
  {
    path: '/',
    component: Home,
    name: '教材管理',
    // leaf: true,//只有一个节点
    iconCls: 'el-icon-message', //图标样式class
    children: [
      { path: '/table4', component: Table4, name: '查看教材' },
      { path: '/table5', component: Table5, name: '添加教材' },
      { path: '/table17', component: Table17, name: '修改教材信息'},
      { path: '/table6', component: Table6, name: '删除教材' },
    ]
  },
  {
    path: '/',
    component: Home,
    name: '班级管理',
    // leaf: true,//只有一个节点
    iconCls: 'el-icon-message', //图标样式class
    children: [
      { path: '/table7', component: Table7, name: '查看班级' },
      { path: '/table8', component: Table8, name: '创建班级' },
      { path: '/table9', component: Table9, name: '修改班级信息' },
      { path: '/table18', component: Table18, name: '删除班级' },
    ]
  },
  {
    path: '/',
    component: Home,
    name: '教师管理',
    // leaf: true,//只有一个节点
    iconCls: 'el-icon-message', //图标样式class
    children: [
      { path: '/table10', component: Table10, name: '查看教师' },
      { path: '/table12', component: Table12, name: '修改教师信息' },
      { path: '/table19', component: Table19, name: '删除教师' },
    ]
  },
  {
    path: '/',
    component: Home,
    name: '学校管理',
    // leaf: true,//只有一个节点
    iconCls: 'el-icon-message', //图标样式class
    children: [
      { path: '/table13', component: Table13, name: '查看学校' },
      { path: '/table14', component: Table14, name: '添加学校' },
      { path: '/table15', component: Table15, name: '修改学校信息' },
      { path: '/table20', component: Table20, name: '删除学校' },
    ]
  },
  {
    path: '/',
    component: Home,
    name: '学科管理',
    // leaf: true,//只有一个节点
    iconCls: 'el-icon-message', //图标样式class
    children: [
      { path: '/table21', component: Table21, name: '查看学科' },
      { path: '/table22', component: Table22, name: '添加学科' },
      { path: '/table23', component: Table23, name: '修改学科信息' },
      { path: '/table24', component: Table24, name: '删除学科' },
    ]
  },
  {
    path: '/',
    component: Home,
    name: '学生管理',
    // leaf: true,//只有一个节点
    iconCls: 'el-icon-message', //图标样式class
    children: [
      { path: '/table28', component: Table28, name: '查看学生' },
    ]
  },
];

export default routes;
