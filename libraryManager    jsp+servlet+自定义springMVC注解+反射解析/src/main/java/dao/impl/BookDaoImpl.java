package dao.impl;

import dao.BookDao;
import entity.Book;
import entity.BookInfo;
import entity.Pager;
import entity.Student;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> queryBooks(Connection con ,Pager pager) {
        String sql = "select * from book limit ?,?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,(pager.getCurrentPage()-1)*pager.getRowPerPage());
            preparedStatement.setInt(2,pager.getRowPerPage());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> books = new ArrayList<Book>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookName(resultSet.getString("bookName"));
                book.setBookNumber(resultSet.getInt("bookNumber"));
                books.add(book);
            }
            DbUtil.close(con,preparedStatement,resultSet);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book queryBookByBookId(Connection con, int bookId) {
        String sql = "select * from book where id=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(bookId);
                book.setBookName(resultSet.getString("bookName"));
                DbUtil.close(con,preparedStatement,resultSet);
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return  null;
    }

    @Override
    public int queryAllBookNum(Connection con) {
        String sql="select count(*) rec  from book";
       try {
           PreparedStatement preparedStatement = con.prepareStatement(sql);
           ResultSet rs = preparedStatement.executeQuery();
             int rowCount=0;
           while (rs.next()) {
               rowCount = rs.getInt("rec");
               System.out.print("rowCount:"+rowCount);
           }
           return  rowCount;

       }catch (Exception e){
           e.printStackTrace();
       }
        return  0;
    }


     @Override
    public List<BookInfo> queryMyBooks(Connection con, Student student) {
        int studentId = student.getId();
        String  sql="select * from  student_book where  student_book.studentId=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<BookInfo> bookInfos = new ArrayList<>();
            while (resultSet.next()){
                int bookId = resultSet.getInt("bookId");
                Book book = queryBookByBookId(con, bookId);
                int lendNum = resultSet.getInt("lendNum");
                BookInfo bookInfo = new BookInfo();
                bookInfo.setId(bookId);
                bookInfo.setBookName(book.getBookName());
                bookInfo.setLendNum(lendNum);
                bookInfos.add(bookInfo);
            }
            DbUtil.close(con,preparedStatement,resultSet);
            return bookInfos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public Boolean updateBookNumByBookId(Connection con, int bookId,int num) {
        String sql="update book set bookNumber=(bookNumber+?) where id=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,num);
            preparedStatement.setInt(2,bookId);
            int i = preparedStatement.executeUpdate();
            DbUtil.close(con,preparedStatement,null);
            if(i>0)return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean addStudentBookInfo(Connection con, Student student, int bookId) {
            String sql="select * from student_book where studentId=? and bookId=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setInt(2,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){//如果数据库里面不存在该条借书条目,那就向数据库插入该条目
                String sql2="insert into student_book(studentId,bookId,lendNum) values(?,?,1)";
                 preparedStatement = con.prepareStatement(sql2);
                preparedStatement.setInt(1,student.getId());
                preparedStatement.setInt(2,bookId);
                int i = preparedStatement.executeUpdate();
                DbUtil.close(con,preparedStatement,null);
                if(i>0)return true;
            }else{
                String sql3="update student_book set lendNum=(lendNum+1) where studentId=? and bookId=?";
                preparedStatement = con.prepareStatement(sql3);
                preparedStatement.setInt(1,student.getId());
                preparedStatement.setInt(2,bookId);
                int i = preparedStatement.executeUpdate();
                DbUtil.close(con,preparedStatement,null);
                if(i>0)return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean reduceStudentBookInfo(Connection con, Student student, int bookId, int bookNum) {
        PreparedStatement preparedStatement;
        String sql="update student_book set lendNum=(lendNum-?) where studentId=? and bookId=?";
        try {
             preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,bookNum);
            preparedStatement.setInt(2,student.getId());
            preparedStatement.setInt(3,bookId);
            int i = preparedStatement.executeUpdate();
           sql="select lendNum from student_book  where studentId=? and bookId=?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1,student.getId());
                preparedStatement.setInt(2,bookId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                if(rs.getInt("lendNum")<=0){
                    deleteStudentBookInfo(con,student,bookId);
                }
            DbUtil.close(con,preparedStatement,rs);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
        }
   return  false;
    }


    @Override
    public List<BookInfo> checkBookDetail(Connection con, int bookId) {
        String sql="select studentId,lendNum from student_book where bookId=? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<BookInfo> bookInfos = new ArrayList<>();
            while (resultSet.next()){
                BookInfo bookInfo = new BookInfo();
                int studentId = resultSet.getInt("studentId");
                int lendNum = resultSet.getInt("lendNum");
                bookInfo.setLendNum(lendNum);
                bookInfo.setStudentId(studentId);
                bookInfos.add(bookInfo);
            }
            DbUtil.close(con,preparedStatement,resultSet);
            return bookInfos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteStudentBookInfo(Connection con, Student student, int bookId){
        String sql="delete from student_book where studentId=? and bookId=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setInt(2,bookId);
            int i = preparedStatement.executeUpdate();
            if(i>0)return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
           return  false;
    }
}
