import {INIT_SCHOOL_LIST, UPDATE_SCHOOL_LIST, SET_USER_INFORMATION, INIT_STUDENT_LIST, ADD_ONE_STUDENT, UPDATE_CLASS, INIT_QUESTION,
  CLEAR_HOMEWORK_CONTENT, ADD_HOMEWORK_CONTENT, DELETE_HOMEWORK_CONTENT, SET_CLASSROOM, CHANGE_PUB_QUESTION_CHECKED, CHANGE_TEA_QUESTION_CHECKED,
  CLEAR_QUESTION_CHECKED, DELETE_TEA_QUESTION, INIT_ASSIGNED_HOMEWORK, SET_HOMEWORK_DETAIL, DELETE_ASSIGNED_HOMEWORK, SET_IDENTITY, CLEAR_STU_ANSWER, DELETE_STU_ANSWER, SET_STU_ANSWER,
  SET_ANSWER, SET_ANSWER_AND_SOLUTION, ADD_QUESION, SET_STUDENT_RANK, SET_NEW_MICROSPHERE_PICTURES, SET_MICROSPHERE_PICTURES} from './mutation-types'
export default {
  // 教师学校列表
  [INIT_SCHOOL_LIST] (state, {schoolList}) {
    state.schoolList = schoolList
  },
  [UPDATE_SCHOOL_LIST] (state, {school}) {
    state.schoolList = school
  },
  // 用户信息
  [SET_USER_INFORMATION] (state, {userInformation}) {
    state.userInformation = userInformation
  },
  // 教师的班级成员
  [INIT_STUDENT_LIST] (state, {studentList}) {
    state.studentList = studentList
  },
  [ADD_ONE_STUDENT] (state, {student}) {
    state.studentList.unshift(student)
  },
  // 更新班级信息
  [UPDATE_CLASS] (state, {classroom, classIndex, schoolIndex}) {
    let classList = state.schoolList[schoolIndex].classList.splice(classIndex, 1, classroom)
    let schoolList = []
    state.schoolList.forEach((school, index) => {
      if (index === schoolIndex) {
        schoolList.push(state.schoolList.splice(schoolIndex, 1, classList))
      } else {
        schoolList.push(school)
      }
    })
    state.schoolList = schoolList
  },
  // 题库
  [INIT_QUESTION] (state, {teaQuestion, pubQuestion, isView, isJump}) {
    state.teaQuestion = teaQuestion
    state.pubQuestion = pubQuestion
    let teaSingleChoice = []
    let teaMultipleChoice = []
    let teaJudge = []
    let teaFillBlank = []
    let teaWrite = []
    let pubSingleChoice = []
    let pubMultipleChoice = []
    let pubJudge = []
    let pubFillBlank = []
    let pubWrite = []
    if (teaQuestion.length > 0) {
      teaQuestion.forEach((question, index) => {
        switch (question.quesType) {
          case '单选题':
            teaSingleChoice.push(question)
            break
          case '多选题':
            teaMultipleChoice.push(question)
            break
          case '填空题':
            teaFillBlank.push(question)
            break
          case '判断题':
            teaJudge.push(question)
            break
          case '作文题':
            teaWrite.push(question)
        }
      })
    }
    if (pubQuestion.length > 0) {
      pubQuestion.forEach((question, index) => {
        switch (question.quesType) {
          case '单选题':
            pubSingleChoice.push(question)
            break
          case '多选题':
            pubMultipleChoice.push(question)
            break
          case '填空题':
            pubFillBlank.push(question)
            break
          case '判断题':
            pubJudge.push(question)
            break
          case '作文题':
            pubWrite.push(question)
        }
      })
    }
    state.teaSingleChoice = teaSingleChoice
    state.teaMultipleChoice = teaMultipleChoice
    state.teaJudge = teaJudge
    state.teaFillBlank = teaFillBlank
    state.teaWrite = teaWrite
    state.pubSingleChoice = pubSingleChoice
    state.pubMultipleChoice = pubMultipleChoice
    state.pubJudge = pubJudge
    state.pubFillBlank = pubFillBlank
    state.pubWrite = pubWrite
    if (isJump) {
      wx.navigateTo({
        url: '/pages/questionBank/main?showSelect=' + isView
      })
    }
  },
  // 发布作业
  [ADD_HOMEWORK_CONTENT] (state, {homeworkContent}) {
    state.homeworkContentVOs.push(homeworkContent)
  },
  [CLEAR_HOMEWORK_CONTENT] (state) {
    state.homeworkContentVOs = []
  },
  [DELETE_HOMEWORK_CONTENT] (state, {homeworkContent}) {
    if (state.homeworkContentVOs.length > 0) {
      let arr = state.homeworkContentVOs.filter((cur) => {
        return !(cur.flag === homeworkContent.flag && cur.quesId === homeworkContent.quesId)
      })
      state.homeworkContentVOs = arr
    }
  },
  // 一些id
  [SET_CLASSROOM] (state, {classroom}) {
    state.classroom = classroom
  },
  [CHANGE_TEA_QUESTION_CHECKED] (state, {aQuestion}) {
    state.teaQuestion.forEach((cur, index) => {
      if (cur.flag === aQuestion.flag && cur.quesAllId === aQuestion.quesAllId) {
        state.teaQuestion.splice(index, 1, aQuestion)
        let teaSingleChoice = []
        let teaMultipleChoice = []
        let teaJudge = []
        let teaFillBlank = []
        let teaWrite = []
        state.teaQuestion.forEach((question, index) => {
          switch (question.quesType) {
            case '单选题':
              teaSingleChoice.push(question)
              break
            case '多选题':
              teaMultipleChoice.push(question)
              break
            case '填空题':
              teaFillBlank.push(question)
              break
            case '判断题':
              teaJudge.push(question)
              break
            case '作文题':
              teaWrite.push(question)
          }
        })
        state.teaSingleChoice = teaSingleChoice
        state.teaMultipleChoice = teaMultipleChoice
        state.teaJudge = teaJudge
        state.teaFillBlank = teaFillBlank
        state.teaWrite = teaWrite
      }
    })
  },
  [CHANGE_PUB_QUESTION_CHECKED] (state, {aQuestion}) {
    state.pubQuestion.forEach((cur, index) => {
      if (cur.flag === aQuestion.flag && cur.quesAllId === aQuestion.quesAllId) {
        state.pubQuestion.splice(index, 1, aQuestion)
        let pubSingleChoice = []
        let pubMultipleChoice = []
        let pubJudge = []
        let pubFillBlank = []
        let pubWrite = []
        state.pubQuestion.forEach((question, index) => {
          switch (question.quesType) {
            case '单选题':
              pubSingleChoice.push(question)
              break
            case '多选题':
              pubMultipleChoice.push(question)
              break
            case '填空题':
              pubFillBlank.push(question)
              break
            case '判断题':
              pubJudge.push(question)
              break
            case '作文题':
              pubWrite.push(question)
          }
        })
        state.pubSingleChoice = pubSingleChoice
        state.pubMultipleChoice = pubMultipleChoice
        state.pubJudge = pubJudge
        state.pubFillBlank = pubFillBlank
        state.pubWrite = pubWrite
      }
    })
  },
  [CLEAR_QUESTION_CHECKED] (state) {
    let arrTea = state.teaQuestion.map((cur) => {
      cur.checked = false
      return cur
    })
    let arrPub = state.pubQuestion.map((cur) => {
      cur.checked = false
      return cur
    })
    state.teaQuestion = arrTea
    state.pubQuestion = arrPub
    let teaSingleChoice = []
    let teaMultipleChoice = []
    let teaJudge = []
    let teaFillBlank = []
    let teaWrite = []
    let pubSingleChoice = []
    let pubMultipleChoice = []
    let pubJudge = []
    let pubFillBlank = []
    let pubWrite = []
    if (arrTea.length > 0) {
      arrTea.forEach((question, index) => {
        switch (question.quesType) {
          case '单选题':
            teaSingleChoice.push(question)
            break
          case '多选题':
            teaMultipleChoice.push(question)
            break
          case '填空题':
            teaFillBlank.push(question)
            break
          case '判断题':
            teaJudge.push(question)
            break
          case '作文题':
            teaWrite.push(question)
        }
      })
    }
    if (arrPub.length > 0) {
      arrPub.forEach((question, index) => {
        switch (question.quesType) {
          case '单选题':
            pubSingleChoice.push(question)
            break
          case '多选题':
            pubMultipleChoice.push(question)
            break
          case '填空题':
            pubFillBlank.push(question)
            break
          case '判断题':
            pubJudge.push(question)
            break
          case '作文题':
            pubWrite.push(question)
        }
      })
    }
    state.teaSingleChoice = teaSingleChoice
    state.teaMultipleChoice = teaMultipleChoice
    state.teaJudge = teaJudge
    state.teaFillBlank = teaFillBlank
    state.teaWrite = teaWrite
    state.pubSingleChoice = pubSingleChoice
    state.pubMultipleChoice = pubMultipleChoice
    state.pubJudge = pubJudge
    state.pubFillBlank = pubFillBlank
    state.pubWrite = pubWrite
  },
  [DELETE_TEA_QUESTION] (state, {quesType, quesAllId}) {
    let teaQuestion = state.teaQuestion
    teaQuestion.forEach((cur, index) => {
      if (cur.quesAllId === quesAllId) {
        state.teaQuestion.splice(index, 1)
      }
    })
    switch (quesType) {
      case '单选题':
        let teaSingleChoice = state.teaSingleChoice
        teaSingleChoice.forEach((cur, index) => {
          if (cur.quesAllId === quesAllId) {
            state.teaSingleChoice.splice(index, 1)
          }
        })
        break
      case '多选题':
        let teaMultipleChoice = state.teaMultipleChoice
        teaMultipleChoice.forEach((cur, index) => {
          if (cur.quesAllId === quesAllId) {
            state.teaMultipleChoice.splice(index, 1)
          }
        })
        break
      case '填空题':
        let teaFillBlank = state.teaFillBlank
        teaFillBlank.forEach((cur, index) => {
          if (cur.quesAllId === quesAllId) {
            state.teaFillBlank.splice(index, 1)
          }
        })
        break
      case '判断题':
        let teaJudge = state.teaJudge
        teaJudge.forEach((cur, index) => {
          if (cur.quesAllId === quesAllId) {
            state.teaJudge.splice(index, 1)
          }
        })
        break
      case '作文题':
        let teaWrite = state.teaWrite
        teaWrite.forEach((cur, index) => {
          if (cur.quesAllId === quesAllId) {
            state.teaWrite.splice(index, 1)
          }
        })
        break
    }
  },
  // 教师获取已经发布的作业
  [INIT_ASSIGNED_HOMEWORK] (state, {assignedHomework}) {
    state.assignedHomework = assignedHomework
  },
  // 某次作业的详情
  [SET_HOMEWORK_DETAIL] (state, {homeworkDetail}) {
    state.homeworkDetail = homeworkDetail
  },
  // 删除某次作业
  [DELETE_ASSIGNED_HOMEWORK] (state, {chId}) {
    const assignedHomework = state.assignedHomework
    assignedHomework.forEach((cur, index) => {
      if (cur.classHomework.chId === chId) {
        state.assignedHomework.splice(index, 1)
      }
    })
  },
  // 初始化身份
  [SET_IDENTITY] (state) {
    const isTeacher = (this.state.userInformation.identity === 'teacher')
    state.isTeacher = isTeacher
  },
  // 学生作业设置
  [SET_STU_ANSWER] (state, {stuAnswer}) {
    const stuAnswers = state.stuAnswer
    stuAnswers.forEach((cur, index) => {
      if (cur.hcId === stuAnswer.hcId) {
        state.stuAnswer.splice(index, 1)
      }
    })
    state.stuAnswer.unshift(stuAnswer)
  },
  [DELETE_STU_ANSWER] (state, {stuAnswer}) {
    const stuAnswers = state.stuAnswer
    stuAnswers.forEach((cur, index) => {
      if (cur.hcId === stuAnswer.hcId) {
        state.stuAnswer.splice(index, 1)
      }
    })
  },
  [CLEAR_STU_ANSWER] (state) {
    state.stuAnswer = []
  },
  [SET_ANSWER] (state, {chId}) {
    state.answer.chId = chId
  },
  [SET_ANSWER_AND_SOLUTION] (state, {answerAndSolution}) {
    state.answerAndSolution = answerAndSolution
  },
  [ADD_QUESION] (state, {question}) {
    state.teaQuestion.unshift(question)
    switch (question.quesType) {
      case '单选题':
        state.teaSingleChoice.unshift(question)
        break
      case '多选题':
        state.teaMultipleChoice.unshift(question)
        break
      case '填空题':
        state.teaFillBlank.unshift(question)
        break
      case '判断题':
        state.teaJudge.unshift(question)
        break
      case '作文题':
        state.teaWrite.unshift(question)
    }
  },
  // 学生排名
  [SET_STUDENT_RANK] (state, {studentRank}) {
    state.studentRank = studentRank
  },
  // 微圈
  [SET_MICROSPHERE_PICTURES] (state, {microspherePictures}) {
    state.microspherePictures = microspherePictures
  },
  [SET_NEW_MICROSPHERE_PICTURES] (state, {newMicrospherePictures}) {
    const microspherePictures = this.state.microspherePictures
    let haveOld = false
    microspherePictures.forEach((cur, index) => {
      if (cur.name === newMicrospherePictures.name && cur.time === newMicrospherePictures.time) {
        state.microspherePictures.splice(index, 1, newMicrospherePictures)
        haveOld = true
      }
    })
    if (!haveOld) {
      state.microspherePictures.unshift(newMicrospherePictures)
    }
    console.log(state.microspherePictures)
  }
}
