package dao;

import entity.Book;
import entity.BookInfo;
import entity.Pager;
import entity.Student;

import java.sql.Connection;
import java.util.List;

public interface BookDao {
    public List<Book>  queryBooks(Connection con, Pager pager);
    public int queryAllBookNum(Connection con);//查询有多少本不同的书
    public List<BookInfo>   queryMyBooks(Connection con , Student student);
    public Book queryBookByBookId(Connection con,int bookId);
    public Boolean updateBookNumByBookId(Connection con,int bookId,int num);
    public Boolean  addStudentBookInfo(Connection con,Student student,int bookId);
    public  Boolean reduceStudentBookInfo(Connection  con,Student student,int bookId,int bookNum);
    public List<BookInfo> checkBookDetail(Connection con,int bookId);
 }
