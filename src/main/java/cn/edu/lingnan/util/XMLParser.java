package cn.edu.lingnan.util;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


//解析器
public class XMLParser{
    //要给出xml文件，既解析对象
    public static Map<String,String> parser(String xmlPath)
    {
        Map<String,String> map = null;
        try {
            //1.实例化一个SAXParser对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2.通过factory获得SAXParser对象，即SAX解析器
            SAXParser parser = factory.newSAXParser();
            //3.saxParser对象调用parse方法进行解析
            File file = new File(xmlPath);//创建事件原件
            XMLHandler handler = new XMLHandler();//创建事件处理器
            parser.parse(file,handler);
            map = handler.getMap();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    //从InputStream解析XML，避免路径编码问题
    public static Map<String,String> parser(InputStream inputStream)
    {
        Map<String,String> map = null;
        try {
            //1.实例化一个SAXParser对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2.通过factory获得SAXParser对象，即SAX解析器
            SAXParser parser = factory.newSAXParser();
            //3.saxParser对象调用parse方法进行解析
            XMLHandler handler = new XMLHandler();//创建事件处理器
            parser.parse(inputStream,handler);
            map = handler.getMap();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("无法读取XML配置文件", e);
        } finally {
            //关闭输入流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //忽略关闭异常
                }
            }
        }

        return map;
    }
//    public static void main(String[] args){
//        String xmlPath = "src\\main\\resources\\database.conf.xml";
//        Map<String, String> map = XMLParser.parser(xmlPath);
//        System.out.println(map.get("driver"));
//        System.out.println(map.get("url"));
//        System.out.println(map.get("user"));
//        System.out.println(map.get("password"));
//    }
}
