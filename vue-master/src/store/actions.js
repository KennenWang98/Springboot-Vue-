import {INIT_SCHOOL_LIST, UPDATE_SCHOOL_LIST, SET_USER_INFORMATION, INIT_STUDENT_LIST, ADD_ONE_STUDENT,
  UPDATE_CLASS, INIT_QUESTION, ADD_HOMEWORK_CONTENT, CLEAR_HOMEWORK_CONTENT, DELETE_HOMEWORK_CONTENT, SET_CLASSROOM, CHANGE_PUB_QUESTION_CHECKED, CHANGE_TEA_QUESTION_CHECKED,
  CLEAR_QUESTION_CHECKED, DELETE_TEA_QUESTION, INIT_ASSIGNED_HOMEWORK, SET_HOMEWORK_DETAIL, DELETE_ASSIGNED_HOMEWORK, SET_IDENTITY, CLEAR_STU_ANSWER,
  SET_STU_ANSWER, DELETE_STU_ANSWER, SET_ANSWER, SET_ANSWER_AND_SOLUTION, ADD_QUESION, SET_STUDENT_RANK, SET_MICROSPHERE_PICTURES, SET_NEW_MICROSPHERE_PICTURES} from './mutation-types'
import {urlUtils} from '../utils/urlUtils'
import {$Message} from '../../static/iview/base/index'
import dateFormate from '../utils/dateFormate'
export default {
  // 初始化用户信息
  init_school_list ({commit}, schoolList) {
    commit(INIT_SCHOOL_LIST, {schoolList})
  },
  updateSchoolList ({commit}, school) {
    commit(UPDATE_SCHOOL_LIST, {school})
  },
  set_user_information ({commit}, userInformation) {
    commit(SET_USER_INFORMATION, {userInformation})
    commit(SET_IDENTITY)
  },
  // 教师的学生管理
  initStudentList: ({commit}, classId) => {
    wx.request({
      url: urlUtils + '/class/students',
      data: {'classId': classId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          const studentList = data.data
          commit(INIT_STUDENT_LIST, {studentList})
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  addStudent ({commit}, {classId, phoneNumber}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/student/add',
      data: {'classId': classId, 'phoneNumber': phoneNumber},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '添加成功',
            type: 'success'
          })
          const studentList = data.data
          commit(INIT_STUDENT_LIST, {studentList})
        } else {
          $Message({
            content: '添加失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '添加失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  addOneStudent ({commit}, {classId, phoneNumber}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/student/add-one',
      data: {'classId': classId, 'phoneNumber': phoneNumber},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '添加成功',
            type: 'success'
          })
          const student = data.data
          commit(ADD_ONE_STUDENT, {student})
        } else {
          $Message({
            content: '添加失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '添加失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  deleteStudent ({commit}, {classId, phoneNumber}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/student/delete',
      data: {'classId': classId, 'phoneNumber': phoneNumber},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '删除成功',
            type: 'success'
          })
          const studentList = data.data
          commit(INIT_STUDENT_LIST, {studentList})
        } else {
          $Message({
            content: '删除失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '删除失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 更新班级信息
  updateClass ({commit}, {classroom, classIndex, schoolIndex}) {
    wx.request({
      url: urlUtils + '/class/change',
      data: JSON.stringify(classroom),
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          wx.navigateBack()
          commit(UPDATE_CLASS, {classroom, classIndex, schoolIndex})
        } else {
          $Message({
            content: data.msg,
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '修改失败，请稍后再试',
          type: 'error'
        })
      }
    })
  },
  studentAddClass ({commit}, {classQrCode, phoneNumber}) {
    wx.showLoading({
      title: '请稍后...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/student/join',
      data: {'classQrCode': classQrCode, 'phoneNumber': phoneNumber},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '加入成功',
            type: 'success'
          })
          const schoolList = data.data
          commit(INIT_SCHOOL_LIST, {schoolList})
        } else {
          $Message({
            content: data.msg,
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '添加失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 题库部分
  initQuestion ({commit}, {teaId, classId, isView, isJump}) {
    wx.showLoading({
      title: '请稍后...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/get-release-ques',
      data: {'teacherId': teaId, 'clazzId': classId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          const teaQuestion = data.data.teaQuestionVOs
          const pubQuestion = data.data.pubVOs
          commit(INIT_QUESTION, {teaQuestion, pubQuestion, isView, isJump})
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  addHomeworkContent ({commit}, homeworkContent) {
    commit(ADD_HOMEWORK_CONTENT, {homeworkContent})
  },
  deleteHomeworkContent ({commit}, homeworkContent) {
    commit(DELETE_HOMEWORK_CONTENT, {homeworkContent})
  },
  clearHomeworkContent ({commit}) {
    commit(CLEAR_HOMEWORK_CONTENT)
  },
  // 发布作业
  releaseHomework ({commit}, {completeHomeworkContent, teaId, classId}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/release-homework',
      data: JSON.stringify(completeHomeworkContent),
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '发布成功',
            type: 'success'
          })
          commit(CLEAR_HOMEWORK_CONTENT)
          commit(CLEAR_QUESTION_CHECKED)
          const assignedHomework = data.data
          commit(INIT_ASSIGNED_HOMEWORK, {assignedHomework})
        } else {
          $Message({
            content: data.msg,
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '发布失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 一些id
  setClassroom ({commit}, classroom) {
    commit(SET_CLASSROOM, {classroom})
  },
  // 替换题库的选中状态
  changeQuestionChecked ({commit}, aQuestion) {
    if (aQuestion.flag === 1) {
      commit(CHANGE_TEA_QUESTION_CHECKED, {aQuestion})
    } else {
      commit(CHANGE_PUB_QUESTION_CHECKED, {aQuestion})
    }
  },
  // 教师删除题库中的题
  deleteTeaQuestion ({commit}, {quesType, quesAllId}) {
    wx.showLoading({
      title: '删除中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/tea-question/delete-teacher-question',
      data: {'quesId': quesAllId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '删除成功',
            type: 'success'
          })
          commit(DELETE_TEA_QUESTION, {quesType, quesAllId})
        } else {
          $Message({
            content: data.msg ? data.msg : '删除失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '删除失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 教师获取已经发布的作业
  initAssignedHomework ({commit}, {teaId, classId}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/get-class-homework',
      data: {'teaId': teaId, 'classId': classId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          const assignedHomework = data.data
          commit(INIT_ASSIGNED_HOMEWORK, {assignedHomework})
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 某次作业的详情
  setHomeworkDetail ({commit}, {chId, status}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/get-homework-ques',
      data: {'chId': chId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          const homeworkDetail = data.data
          if (homeworkDetail.length > 0) {
            commit(SET_HOMEWORK_DETAIL, {homeworkDetail})
            if (status === '教师查看') {
              console.log(status)
              wx.navigateTo({url: '/pages/homeworkDetail/main?disabled=true'})
            } else if (status === '去作答') {
              wx.navigateTo({url: '/pages/studentHomeworkDetail/main?disabled=false'})
            }
          }
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  deleteAssignedHomework ({commit}, chId) {
    wx.showLoading({
      title: '删除中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/delete-homework',
      data: {'chId': chId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '删除成功',
            type: 'success'
          })
          commit(DELETE_ASSIGNED_HOMEWORK, {chId})
        } else {
          $Message({
            content: data.msg ? data.msg : '删除失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '删除失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 学生的作业操作
  setStuAnswer ({commit}, {quesType, objects, hcId}) {
    const stuAnswer = {'hcId': hcId, 'quesType': quesType, 'objects': objects}
    commit(SET_STU_ANSWER, {stuAnswer})
  },
  deleteStuAnswer ({commit}, stuAnswer) {
    commit(DELETE_STU_ANSWER, {stuAnswer})
  },
  clearStuAnswer ({commit}) {
    commit(CLEAR_STU_ANSWER)
  },
  submitAnswers ({commit}, {stuAnswerContentVOs, answer, stuPhoneNumber}) {
    wx.showLoading({
      title: '提交中...',
      mask: true
    })
    const answers = {stuAnswerContentVOs, answer, stuPhoneNumber}
    wx.request({
      url: urlUtils + '/homework/upload-stu-answer',
      data: JSON.stringify(answers),
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '提交成功',
            type: 'success'
          })
          const answerAndSolution = data.data
          commit(SET_ANSWER_AND_SOLUTION, {answerAndSolution})
          commit(CLEAR_STU_ANSWER)
          wx.navigateBack()
        } else {
          $Message({
            content: data.msg ? data.msg : '提交失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '提交失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  setAnswer ({commit}, chId) {
    commit(SET_ANSWER, {chId})
  },
  addProblem ({commit}, addProblem) {
    wx.showLoading({
      title: '添加中...',
      mask: true
    })
    let url = ''
    switch (addProblem.teacherQuestions.teaQuesType) {
      case '单选题':
        url = '/tea-question/upload-teacher-choice'
        break
      case '多选题':
        url = '/tea-question/upload-teacher-choice'
        break
      case '判断题':
        url = '/tea-question/upload-teacher-judge'
        break
      case '填空题':
        url = '/tea-question/upload-teacher-fill'
        break
    }
    wx.request({
      url: urlUtils + url,
      data: JSON.stringify(addProblem),
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          $Message({
            content: '添加成功',
            type: 'success'
          })
          const question = data.data
          commit(ADD_QUESION, {question})
        } else {
          $Message({
            content: data.msg ? data.msg : '添加失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '添加失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 学生排名
  getStudentRank ({commit}, {classId, chId}) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/student-list-with-percentage',
      data: {'classId': classId, 'chId': chId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          const studentRank = data.data
          commit(SET_STUDENT_RANK, {studentRank})
          wx.navigateTo({
            url: '/pages/stuList/main'
          })
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  getAllStudentRank ({commit}, classId) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/homework/get-student-list-with-total-point',
      data: {'classId': classId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        console.log(data)
        if (data.code === 200) {
          const studentRank = data.data
          commit(SET_STUDENT_RANK, {studentRank})
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  // 微圈
  setMicrospherePictures ({commit}, classId) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: urlUtils + '/moment/class',
      data: {'classId': classId},
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: response => {
        const {data} = response
        if (data.code === 200) {
          const microspherePictures = data.data
          console.log(data)
          commit(SET_MICROSPHERE_PICTURES, {microspherePictures})
        } else {
          $Message({
            content: data.msg ? data.msg : '获取失败，请稍后再试',
            type: 'error'
          })
        }
      },
      fail: () => {
        $Message({
          content: '获取失败，请稍后再试',
          type: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  },
  setNewMicrospherePictures ({commit}, {fileList, classId, phoneNumber}) {
    const date = dateFormate.dateFormat('YYYY-mm-dd HH:MM:SS', new Date())
    wx.showLoading({
      title: '上传中...',
      mask: true
    })
    for (let i = 0; i < fileList.length; i++) {
      let path = fileList[i].url
      wx.uploadFile({
        url: urlUtils + '/moment/upload', // 仅为示例，非真实的接口地址
        filePath: path,
        name: 'file',
        header: { 'Content-Type': 'multipart/form-data' },
        formData: { 'classId': classId, 'phoneNumber': phoneNumber, 'time': date },
        success: res => {
          wx.request({
            url: urlUtils + '/moment/query',
            data: {'classId': classId, 'time': date, 'phoneNumber': phoneNumber},
            method: 'GET',
            header: {
              'content-type': 'application/json'
            },
            success: response => {
              const {data} = response
              if (data.code === 200) {
                wx.showToast({
                  title: '上传成功'
                })
                const newMicrospherePictures = data.data
                commit(SET_NEW_MICROSPHERE_PICTURES, {newMicrospherePictures})
              } else {
                wx.showToast({
                  title: '上传失败，请稍后再试',
                  type: 'warning'
                })
              }
            },
            fail: () => {
              wx.showToast({
                title: '上传失败，请稍后再试',
                type: 'warning'
              })
            },
            complete: () => {
              wx.hideLoading()
            }
          })
        },
        fail: res => {
          wx.showToast({
            title: '上传失败，请稍后再试',
            type: 'warning'
          })
        },
        complete: () => {
          wx.hideLoading()
        }
      })
    }
  }
}
