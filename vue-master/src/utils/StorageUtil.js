const USER = 'user'
const STUANSWERCONTENTVOS = 'stuAnswerContentVOs'
export default {
  setUser (user) {
    wx.setStorage({
      key: USER,
      data: user
    })
  },
  getUser () {
    return wx.getStorageSync(USER) || '{}'
  },
  setStuAnswerContentVOs (stuAnswerContentVO) {
    let history = wx.getStorageSync(STUANSWERCONTENTVOS)
    let stuAnswerContentVOs = []
    if (history.length > 0) {
      stuAnswerContentVOs = history.filter((cur, index) => {
        return cur.hcId !== stuAnswerContentVO.hcId
      })
    }
    stuAnswerContentVOs.unshift(stuAnswerContentVO)
    console.log('stuAnswerContentVOs', stuAnswerContentVOs)
    wx.setStorage({
      key: STUANSWERCONTENTVOS,
      data: stuAnswerContentVOs
    })
  },
  getStuAnswerContentVOs () {
    return wx.getStorageSync(STUANSWERCONTENTVOS) || '[]'
  }
}
