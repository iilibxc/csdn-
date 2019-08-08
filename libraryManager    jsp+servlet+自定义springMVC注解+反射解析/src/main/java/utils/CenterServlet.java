package utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet("/")
public class CenterServlet extends HttpServlet {
    private Map<String, Object> mapController;
    private Map<String, Map<String, Method>> mapMethod;
    /**
     * 初始化
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("初始化");
        ServletUtil servletUtil = new ServletUtil();
        servletUtil.initController();
        //获取mapController
        this.mapController = servletUtil.getMapController();
        //获取mapMethod
        this.mapMethod = servletUtil.getMapMethod();



    }
/**
* @Description  处理所有的客户端请求
* @Param
* @Return
*/
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("charset=UTF-8");
        String requestURI = req.getRequestURI();
        if(requestURI.equals("/favicon.ico")||requestURI.contains("/static/")) {

        }else if(requestURI.contains("/common/jquery.min.js")){
            req.getRequestDispatcher("/common/jquery.min.js").forward(req,resp);

        }
        else {
            //去掉第一个斜杠
          /*  int i = requestURI.indexOf("/", 8);*/
            requestURI = requestURI.substring(1);
            System.out.println("-----------------------------------------");
            start(requestURI, req, resp);
        }
    }


    private void start(String url, HttpServletRequest req, HttpServletResponse resp) {
        String[] reUrl = url.split("/");
        Set<String> setKey = this.mapMethod.keySet();//取到扫描到的所有controller类
        Iterator<String> ita = setKey.iterator();
        //特殊情况
        int length = 1;
        if (length == reUrl.length) {
            String methodName = "/" + reUrl[0].trim();
            //获取controller
            Object controller = this.mapController.get("");
            Map<String, Method> map = this.mapMethod.get(controller.getClass().getName());
            Method method = map.get(methodName);
            System.out.println(method.getName());
            try {
                //调用法法
                method.invoke(controller, req, resp);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Object controller = this.mapController.get("/" + reUrl[0]);//得到url访问的controller值
            Map<String, Method> map = this.mapMethod.get(controller.getClass().getName());
            if (map == null) {
                System.out.println("map 为null");
            }
            String methodUrl;
            //获取方法
            if(reUrl[1].contains("&")) {
                 methodUrl = reUrl[1].substring(0, reUrl[1].indexOf("&"));
                if(reUrl[1].substring(reUrl[1].indexOf("&"))!=""){
                    String[] methodParam=reUrl[1].substring(reUrl[1].indexOf("&")).split("=");
                    req.setAttribute(methodParam[0].substring(1),methodParam[1]);}
            }else {
                methodUrl=reUrl[1];
            }

            Method method = map.get("/" +methodUrl);
            System.out.println(method.getName());
            try {
                method.invoke(controller, req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
