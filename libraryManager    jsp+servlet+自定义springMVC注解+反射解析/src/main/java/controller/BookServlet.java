package controller;

import annotation.Controller;
import annotation.RequestMapping;
import entity.Book;
import entity.BookInfo;
import entity.Pager;
import entity.Student;
import service.BookService;
import service.StudentService;
import service.impl.BookServiceImpl;
import service.impl.StudentServiceImpl;
import utils.EmptyUtils;
import utils.ExecResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller(value = "/BookServlet")
public class BookServlet  extends HttpServlet {

 BookService bookService=new BookServiceImpl();
 StudentService studentService=new StudentServiceImpl();
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        try {
            Method reqMethod = this.getClass().getDeclaredMethod(method, new Class[]{HttpServletRequest.class , HttpServletResponse.class});
            reqMethod.invoke(this , req , resp);
        } catch (Exception e) {
            req.setAttribute("errorMsg", "您的请求参数不合法");
            req.getRequestDispatcher("/WEB-INF/page/error/errorPage.jsp").forward(req, resp);
        }
    }*/

    /**
    * @Description 获得所有的图书,然后跳转到main.jsp
    * @Param
    * @Return
    */
    @RequestMapping(name ="toMainPage" ,value = "/toMainPage" )
    public void toMainPage(HttpServletRequest req, HttpServletResponse resp) {
        String currentPageStr = (String) req.getAttribute("currentPage");
        int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
        //获取页大小
      //  String pageSizeStr = req.getParameter("pageSize");
        int rowCount = bookService.queryAllBookCount();
        Pager pager = new Pager(rowCount, 5, currentPage);
        List<Book> books = bookService.queryBookList(pager);
        req.setAttribute("pager",pager);
        Object login_student = req.getSession().getAttribute("LOGIN_STUDENT");
        Student student=(Student) login_student;
        List<BookInfo> bookInfos = bookService.queryMyBooks(student);
        try {
            req.setAttribute("bookList",books);
            req.setAttribute("myBookInfos",bookInfos);
            req.getRequestDispatcher("/WEB-INF/page/student/main.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
    * @Description  借书
    * @Param
    * @Return
    */
    @RequestMapping(name = "lendBook",value ="/lendBook")
    public void  lendBook(HttpServletRequest req, HttpServletResponse resp){
        String bookId = req.getParameter("id");
        Object login_student = req.getSession().getAttribute("LOGIN_STUDENT");
        Student student=(Student)login_student;
        Boolean aBoolean = bookService.lendBook(student, Integer.valueOf(bookId));
        ExecResult result = new ExecResult();
        if(aBoolean){
            result.setSuccess(true);
            result.setMsg("借书成功！");
        }else{
            result.setSuccess(false);
            result.setMsg("借书失败！");
        }
        try {
            resp.getOutputStream().write(result.toJson().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    * @Description 还书
    * @Param
    * @Return
    */
    @RequestMapping(name = "returnBooks",value = "/returnBooks")
    public  void returnBooks(HttpServletRequest req, HttpServletResponse resp){
        Object login_student = req.getSession().getAttribute("LOGIN_STUDENT");
        Student student=(Student)login_student;
        String bookId = req.getParameter("bookId");
        String returnNum = req.getParameter("returnNum");
        Boolean aBoolean = bookService.returnBooks(student, Integer.valueOf(bookId), Integer.valueOf(returnNum));
        ExecResult result = new ExecResult();
        if(aBoolean){
            result.setSuccess(true);
            result.setMsg("还书成功！");
        }else{
            result.setSuccess(false);
            result.setMsg("还书失败！");
        }
        try {
            resp.getOutputStream().write(result.toJson().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
* @Description 查看借书详情
* @Param
* @Return
*/
@RequestMapping(name ="checkBookDetail",value = "/checkBookDetail")
    public void checkBookDetail(HttpServletRequest req, HttpServletResponse resp){
        String bookId = req.getParameter("bookId");
        List<BookInfo> bookInfos = bookService.checkBookDetail(Integer.valueOf(bookId));
        StringBuffer json=new StringBuffer();
        if(bookInfos.size()==0){
            json.append("该书暂时没有其他人借！");
        }else {
            for (BookInfo bookInfo : bookInfos) {
                String name = studentService.queryStudentById(bookInfo.getStudentId()).getName();
                json.append(name + "借了" + bookInfo.getLendNum() + "本" + "     ");
                /* json.append(bookInfo.get)*/
            }
        }
       System.out.print("json是"+json.toString());
        try {
            resp.getOutputStream().write(json.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
