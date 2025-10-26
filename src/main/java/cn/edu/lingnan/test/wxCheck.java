package cn.edu.lingnan.test;

import com.mysql.cj.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//@WebServlet("/")
public class wxCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("你好微信");
        System.out.println("时间:"+ new Date());
        System.out.println("IP:"+req.getRemoteAddr());
        System.out.println("请求URL:"+ req.getRequestURL());

        //防止请求参数为空
        if (req.getQueryString() == null) {
            System.out.println("请求参数为空");
            resp.getWriter().write("校验失败");
        }
        System.out.println("参数:"+ req.getQueryString());

        req.setCharacterEncoding("UTF-8");// *解决中文乱码
        resp.setCharacterEncoding("UTF-8");

        // 获取请求参数
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        System.out.println(signature + "\n" + timestamp + "\n" + nonce + "\n" + echostr);
        // 调用校验方法
        String result = check(signature, timestamp, nonce, echostr);

        // 设置响应内容类型
        resp.setContentType("text/plain;charset=UTF-8");

        // 将结果返回给请求方
        if (!result.isEmpty()) {
            resp.getWriter().write(result);
        } else {
            resp.getWriter().write("校验失败");
        }
    }


    private String check(String signature,
                         String timestamp,
                         String nonce,
                         String echostr)
    {
        // 检查参数是否为空
        if (signature == null || timestamp == null || nonce == null) {
            System.out.println("参数为空，无法进行校验");
            return null;
        }
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String token =  "javazy";
        List<String> list = Arrays.asList(token, timestamp, nonce);//进入list集合,方便排序
        //排序
        Collections.sort(list);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String s:list) {
            sb.append(s);
        }
        //加密
        //使用MessageDigest工具类进行sha1加密
        try {
            MessageDigest instance = MessageDigest.getInstance("sha1");
            //使用sha1进行加密，获得字节数组
            byte[] digest = instance.digest(sb.toString().getBytes());
            //注意微信发过来的是16进制的字符串，需要将字节数组转为16进制
            StringBuilder sum = new StringBuilder();
            for (byte b:digest) {
                //因为15=00001111，所以与15进行与运算，前4位丢弃，后4位保留
                sum.append(Integer.toHexString(b>>4&15));//先获取高4位
                sum.append(Integer.toHexString(b&15));//再获取低4位
            }
            //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if (!StringUtils.isNullOrEmpty(signature)&&signature.equals(sum.toString())) {
                return echostr;
            }
            System.out.println("signature" + signature);
            System.out.println("sum" + sum);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
