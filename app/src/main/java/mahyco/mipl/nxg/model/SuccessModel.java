package mahyco.mipl.nxg.model;

public class SuccessModel {

        String status;//": "Success",
            String Comment;//": "Success",

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public boolean isResultFlag() {
        return ResultFlag;
    }

    public void setResultFlag(boolean resultFlag) {
        ResultFlag = resultFlag;
    }

    int Code;//": 0,
            boolean ResultFlag;//": true


}
