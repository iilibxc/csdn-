package utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {

    public static String read(){
        String packagePath ="";
        //定义一个xml文档解析器
        SAXReader saxReader = new SAXReader();

        try {
            //获取一个document的对象
            Document document  = saxReader.read(ReadXml.class.getResourceAsStream("/myServletMapping.xml"));
            //获取xml文档的跟元素
            Element rootElement = document.getRootElement();
            //获取标签元素
            Element parents = rootElement.element("controller");
            //获取子元素标签
            Element children = parents.element("controller-class");
            //获取标签中的属性内容
            packagePath= children.attributeValue("package-scan");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return packagePath;
    }

}
