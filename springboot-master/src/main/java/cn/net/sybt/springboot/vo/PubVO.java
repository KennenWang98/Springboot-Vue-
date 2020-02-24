package cn.net.sybt.springboot.vo;

public class PubVO<Q, S> extends QuestionVO<Q, S> {
    private Integer quesAllId;
    private Integer bookId;

    @Override
    public String toString() {
        return "PubVO{" +
                "pubQuesId=" + quesAllId +
                ", bookId=" + bookId +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PubVO pubVO = (PubVO) o;

        if (quesAllId != null ? !quesAllId.equals(pubVO.quesAllId) : pubVO.quesAllId != null) return false;
        return bookId != null ? bookId.equals(pubVO.bookId) : pubVO.bookId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (quesAllId != null ? quesAllId.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        return result;
    }

    public Integer getQuesAllId() {
        return quesAllId;
    }

    public void setQuesAllId(Integer quesAllId) {
        this.quesAllId = quesAllId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
