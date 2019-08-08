package utils;

import annotation.Controller;
import annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServletUtil {
    //键为controller注解里的value值，value为这个controller实例对象
    private Map<String, Object> mapController = new HashMap<>();
    //键为该Method所属的controller的controller名字，value是一个map对象，用于存储该controller下的所有method，其中RequestMapping上的value值作为键，方法作为值
    private Map<String, Map<String, Method>> mapMethod = new HashMap<>();

    public void initController() {
        // ServletUtils.class.getClassLoader().getResource("").getPath()获取项目的根目录
        //获取扫描包的目录
        String packagePath = ReadXml.read();
//        File rootFile = new File(ServletUtils.class.getClassLoader().getResource("").getPath() + "controller");
        File rootFile =  new File(ServletUtil.class.getClassLoader().getResource("").getPath() + packagePath);
        File[] files = rootFile.listFiles();//得到该包下的所有文件
        for (File f : files) {
            //获取类名称
            String str = f.getName().substring(0, f.getName().lastIndexOf("."));
            System.out.println("str----->" + str);
            try {
                Class<?> clazz = Class.forName("controller." + str);
                System.out.println(clazz.getName());
                //创建这个类
                Object controllerObject = clazz.newInstance();
                System.out.println("11111->>>" + controllerObject);
                //获取注解
                Controller controllerAnnotation = clazz.getAnnotation(Controller.class);
                //获取controller注解上的值
                String controllerValue = controllerAnnotation.value();
                this.mapController.put(controllerValue, controllerObject);
                //RequestMapping上的value值作为键，方法作为值
                HashMap<String, Method> map = new HashMap<>(100);
                //获取方法
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    //获取方法上的注解RequestMapping
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if (controllerAnnotation != null) {
                        if (methodAnnotation != null) {
                            //获取requestmapping注解上的value值（这个值可以为空）
                            String methodValue = methodAnnotation.value();
                            //获取注解上的方法名称
                            String methodName = methodAnnotation.name();
                            //获取方法
                            Method reaMethod = clazz.getDeclaredMethod(methodName, new Class[]{HttpServletRequest.class, HttpServletResponse.class});
                            System.out.println("reaMethod-->" + reaMethod);
                            map.put(methodValue, reaMethod);
                            //添加到map中去
                            this.mapMethod.put(controllerObject.getClass().getName(), map);
                            System.out.println("hhh-->" + controllerObject.getClass().getName());
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("mapController11"+mapController.size());
        System.out.println("mapMethod11"+mapMethod.size());
    }

    public Map<String, Object> getMapController() {
        return mapController;
    }

    public Map<String, Map<String, Method>> getMapMethod() {
        return mapMethod;
    }
}
