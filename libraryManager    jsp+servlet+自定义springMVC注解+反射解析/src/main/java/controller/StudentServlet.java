package  controller;

import annotation.Controller;
import annotation.RequestMapping;
import entity.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;
import utils.ExecResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@WebServlet(name="StudentServlet",value="/StudentServlet")*/
@Controller(value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
     StudentService studentService = new StudentServiceImpl();

  /*  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req , resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String method = req.getParameter("method");

        try {
            Method reqMethod = this.getClass().getDeclaredMethod(method, new Class[]{HttpServletRequest.class , HttpServletResponse.class});
            reqMethod.invoke(this , req , resp);
        } catch (Exception e) {
            req.setAttribute("errorMsg", "您的请求参数不合法");
            req.getRequestDispatcher("/WEB-INF/page/error/errorPage.jsp").forward(req, resp);
        }
    }*/

@RequestMapping(name = "doLogin",value = "/doLogin")
    public  void doLogin(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        String loginName = req.getParameter("loginName");
        String pwd = req.getParameter("pwd");
        Student stu = studentService.studentLogin(loginName, pwd);//从数据库中根据loginName和pwd去查有没有对应的student
        ExecResult result = new ExecResult();
        if(stu == null){
            result.setSuccess(false);
            result.setMsg("登录帐号或密码错误!");
        }else{
            result.setSuccess(true);
            result.setMsg("登录成功");
            req.getSession().setAttribute("LOGIN_STUDENT", stu);
        }
        resp.getOutputStream().write(result.toJson().getBytes("utf-8"));
    }
    /**
    * @Description 查出所有的学生信息
    * @Param
    * @Return
    */
   /* public void studentList(HttpServletRequest req , HttpServletResponse resp)  throws ServletException, IOException {

        List<Student> sutdentList = studentService.queryStudentList();
        req.setAttribute("studentList", sutdentList);
        req.getRequestDispatcher("/WEB-INF/page/student/main1.jsp").forward(req, resp);
    }*/

/**
* @Description
* @Param
* @Return
*/
@RequestMapping(name = "toMainPage",value = "/toMainPage")
    public void  toMainPage(HttpServletRequest req , HttpServletResponse resp){

    }



    /**
    * @Description 删除某个学生
    * @Param
    * @Return
    */
    @RequestMapping(name = "deleteStudent",value = "/deleteStudent")
    public void deleteStudent(HttpServletRequest req , HttpServletResponse resp){
        String id = req.getParameter("id");
        int i = studentService.deleteStudent(Integer.valueOf(id));
        ExecResult result = new ExecResult();
        if(i>0){
            result.setSuccess(true);
            result.setMsg("删除成功");
        }else{
            result.setSuccess(false);
            result.setMsg("删除失败！");
        }
        try {
            resp.getOutputStream().write(result.toJson().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
* @Description  跳转到addStudent.jsp
* @Param
* @Return
*/
@RequestMapping(name = "toAddStudent",value = "/toAddStudent")
    public void toAddStudent(HttpServletRequest request , HttpServletResponse response) {
        try {
            System.out.print("我要跳转到add页面了");
            request.getRequestDispatcher("/WEB-INF/page/student/addStudent.jsp").forward(request, response);
        } catch (Exception e) {
       e.printStackTrace();
        }
    }
    /**
    * @Description  退出登录
    * @Param
    * @Return
    */
    @RequestMapping(name = "logout",value = "/logout")
    public void logout(HttpServletRequest request , HttpServletResponse response){
        request.getSession().removeAttribute("LOGIN_STUDENT");
        ExecResult result = new ExecResult();
        result.setSuccess(true);
        result.setMsg("退出成功");
        try {
            response.getOutputStream().write(result.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  /**
  * @Description 跳转到登录页面
  * @Param
  * @Return
  */
  @RequestMapping(name = "toRegisterPage",value = "/toRegisterPage")
    public void toRegisterPage(HttpServletRequest request , HttpServletResponse response){
          try {
              request.getRequestDispatcher("/WEB-INF/page/student/register.jsp").forward(request,response);
          }catch (Exception e){
              e.printStackTrace();
          }
    }
/**
* @Description  用户注册
* @Param
* @Return
*/
@RequestMapping(name = "register",value = "/register")
public void register(HttpServletRequest request , HttpServletResponse response){
    String loginName = request.getParameter("loginName");
    String name = request.getParameter("name");
    String password = request.getParameter("pwd");
    String sex = request.getParameter("sex");
    String age = request.getParameter("age");
    Student student = new Student();
    student.setAge(Integer.valueOf(age));
    student.setLoginName(loginName);
    student.setName(name);
    student.setPwd(password);
    student.setSex(Integer.parseInt(sex));
    Boolean aBoolean = studentService.addStudent(student);
    ExecResult result = new ExecResult();
    if(aBoolean){
        result.setSuccess(true);
        result.setMsg("注册成功");
    }else{
        result.setSuccess(false);
        result.setMsg("注册失败！");
    }
    try {
        response.getOutputStream().write(result.toJson().getBytes("utf-8"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
* @Description 跳转到index.jsp页面
* @Param
* @Return
*/
@RequestMapping(name = "toIndexPage",value = "/toIndexPage")
public  void toIndexPage(HttpServletRequest request , HttpServletResponse response){
    try {
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    } catch (ServletException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   /* *//**
     * 新增学生
     *//*
    public void addStudent(HttpServletRequest request , HttpServletResponse response , JspWriter out) throws java.io.IOException {
        String userName = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String loginName = request.getParameter("loginName");
        Connection connection = DbUtil.getConnection();
           studentService.
    }*/
}
