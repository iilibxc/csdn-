package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;
import entity.BookInfo;
import entity.Pager;
import entity.Student;
import service.BookService;
import utils.DbUtil;

import java.sql.Connection;
import java.util.List;

public class BookServiceImpl implements BookService {
       BookDao bookDao=new BookDaoImpl();
     @Override
    public List<Book> queryBookList(Pager pager) {
        Connection connection = DbUtil.getConnection();
        return   bookDao.queryBooks(connection,pager);
    }

    @Override
    public int queryAllBookCount() {
        Connection connection = DbUtil.getConnection();
        return bookDao.queryAllBookNum(connection);
    }

    @Override
    public List<BookInfo> queryMyBooks(Student student) {
        Connection connection = DbUtil.getConnection();
     return   bookDao.queryMyBooks(connection,student);
    }

   /* @Override
    public int queryMyBooksCount( Student student) {
        Connection connection = DbUtil.getConnection();
       return  bookDao.queryMyBooksCount(connection,student);
    }*/
    /* public List<BookInfo> queryMyBooks(Student student, int){
        Connection connection = DbUtil.getConnection();
          return bookDao.queryMyBooks(connection, student);
    }*/

    @Override
    public Boolean lendBook(Student student, int bookId) {
        Connection connection = DbUtil.getConnection();
        Boolean aBoolean = bookDao.updateBookNumByBookId(connection, bookId,-1);
        Boolean bBoolean = bookDao.addStudentBookInfo(connection, student, bookId);
        if(aBoolean&bBoolean){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean returnBooks(Student student, int bookId, int returnNum) {
        Connection connection = DbUtil.getConnection();

        Boolean aBoolean = bookDao.updateBookNumByBookId(connection, bookId, returnNum);
        Boolean bBoolean=bookDao.reduceStudentBookInfo(connection,student,bookId,returnNum);
        if(aBoolean&&bBoolean){
            return  true;
        }else {
            return false;
        }
    }

    @Override
    public List<BookInfo> checkBookDetail(int bookId) {
        Connection connection = DbUtil.getConnection();
        return bookDao.checkBookDetail(connection,bookId);
    }
}
