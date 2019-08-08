package entity;

public class BookInfo extends Book {
    int lendNum;//借书本数，借了多少本该书，比如借了5本《java程序设计》
    int studentId;//借书人的id

    public int getLendNum() {
        return lendNum;
    }

    public void setLendNum(int lendNum) {
        this.lendNum = lendNum;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


}
