package cn.edu.lingnan.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class XMLHandler extends DefaultHandler {
    private StringBuffer buffer = new StringBuffer();
    private Map<String,String> map = new HashMap<>();
    public Map<String,String> getMap() //只给get方法，只读
    {
        return map;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //清空buffer，初始化
        buffer.delete(0,buffer.length());//清空buffer
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //存储键值对
        map.put(qName.toLowerCase(),buffer.toString());//qName.toLowerCase()转换为小写

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //将读取到的文本存到字符串中
        buffer.append(ch,start,length);
    }
}
