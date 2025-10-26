package cn.edu.lingnan.controller;

import cn.edu.lingnan.service.StudentService;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;

import java.util.Scanner;

public class StudentController {
   StudentService student = new StudentServiceImpMysql();
   public void editStudentPassword()
   {
       Scanner sc = new Scanner(System.in);
       System.out.print("请输入学生id：");
       String sid =sc.next();
       while(true){
           System.out.print("请输入旧密码：");
           String oldPassword = sc.next();
           System.out.print("请输入新密码：");
           String newPassword = sc.next();
           System.out.print("请确认新密码：");
           String newPassword2 = sc.next();
           if(newPassword.equals(newPassword2)&&student.editStudentPassword(sid,oldPassword,newPassword)!=0)
           {
               return;
           }
           else
           {
               System.out.println("两次密码不一致！请重新输入");
               while(true){
                   System.out.println("是否放弃修改？Y/N: ");
                   char c = sc.next().charAt(0);
                   if(c=='Y'||c=='y')
                   {
                       return;
                   }
                   else if(c=='N'||c=='n')
                   {
                       break;
                   }
                   else{
                       System.out.println("输入错误！请重新输入");
                   }
               }
           }
       }
   }

   public void editStudentName()
   {
       Scanner sc = new Scanner(System.in);
       System.out.print("请输入学生id：");
       String sid =sc.next();
       System.out.print("请输入新名字：");
       String newName = sc.next();
       student.editStudentName(sid,newName);
   }
}
