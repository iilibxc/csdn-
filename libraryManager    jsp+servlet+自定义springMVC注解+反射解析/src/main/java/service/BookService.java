package service;

import entity.Book;
import entity.BookInfo;
import entity.Pager;
import entity.Student;

import java.util.List;

public interface BookService {
    public List<Book>  queryBookList(Pager pager);
    public  List<BookInfo>  queryMyBooks(Student student);
    public  int queryAllBookCount();
    public Boolean lendBook(Student student,int bookId);
    public Boolean returnBooks(Student student,int bookId,int returnNum);
    public List<BookInfo> checkBookDetail(int bookId);
  }
