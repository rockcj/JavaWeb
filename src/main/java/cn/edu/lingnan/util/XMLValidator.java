package cn.edu.lingnan.util;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    //用xsd去验证xml的有效性
    public static boolean validate(String xmlPath ,String xsdPath){
        boolean flag = false;
        if (xmlPath == null || xsdPath == null || xmlPath.isEmpty() || xsdPath.isEmpty()) {
            throw new IllegalArgumentException("XML路径或XSD路径不能为空");
        }
        File xmlFile = new File(xmlPath);
        File xsdFile = new File(xsdPath);

        if (!xmlFile.exists()) {
            throw new IllegalArgumentException("XML文件不存在: " + xmlPath);
        }
        if (!xsdFile.exists()) {
            throw new IllegalArgumentException("XSD文件不存在: " + xsdPath);
        }
        try {
            //1.创建模式工厂SchemaFactory
            String address = "http://www.w3.org/2001/XMLSchema";
            SchemaFactory factory = SchemaFactory.newInstance(address);
            //2.xsd文件创建Schema对象
            Schema schema = factory.newSchema(xsdFile);
            //3.由模式创建校验器Validator
            Validator validator = schema.newValidator();
            //4.校验xml文件
            StreamSource source = new StreamSource(xmlFile);
            validator.validate(source);//没有返回值
            flag = true;
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
//    public static void main(String[] args){
//        String xmlPath = "src\\main\\resources\\database.conf.xml";
//        String xsdPath = "src\\main\\resources\\database.conf.xsd";
//        boolean flag = XMLValidator.validate(xmlPath, xsdPath);
//        System.out.println(flag);
//    }
}

