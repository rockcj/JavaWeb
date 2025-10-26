package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.dao.*;
import cn.edu.lingnan.dao.imp.*;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpMysql implements StudentService {
    //简单逻辑直接返回
    StudentDao student = new StudentDaoImpIMysql();
    JobDao job = new JobDaoImpIMysql();
    ItemDao item = new ItemDaoImpIMysql();

    StudentHistoryDao studentHistory = new StudentHistoryDaoImpIMysql();
    JobHistoryDao jobHistory = new JobHistoryDaoImpIMysql();
    ItemHistoryDao itemHistory = new ItemHistoryDaoImpIMysql();
    @Override
    public Student queryStudentByNameAndPassword(String _sname, String _spassword) {
        if(_sname==null||_spassword==null)
        {
            System.out.println("查询失败，用户名或密码不能为空");
            return null;
        }if (!_sname.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$")) {
            System.out.println("输入包含非法字符，请勿尝试SQL注入！");
            return null;
        }
        return student.queryStudentByNameAndPassword(_sname, _spassword);
    }

    @Override
    public List<Student> queryAllStudentAll() {
        return student.queryAllStudentAll(2);
    }
    @Override
    public List<Student> queryAllStudentAll(int log) {
        return student.queryAllStudentAll(log);
    }

    @Override
    public Student queryStudentById(String _sid) {
        return student.queryStudentById(_sid);
    }

    @Override
    public List<Student> queryStudentByName(String _sname) {
        return student.queryStudentByName(_sname);
    }


    //复杂逻辑先作判断
    @Override
    public int insertStudent(Student _student) {
        if(_student==null)
        {
            System.out.println("插入失败，学生信息不能为空");
            return 0;
        }
        if(student.queryStudentById(_student.getSid())!=null)
        {
            System.out.println("插入失败，不能重复插入");
            return 0;
        }
        return student.insertStudent(_student);
    }

    @Override
    public int insertHistoryStudent(Student _student) {
        if(_student==null)
        {
            System.out.println("插入失败，学生历史信息不能为空");
            return 0;
        }
        if(studentHistory.queryStudentById(_student.getSid())!=null)
        {
            System.out.println("插入失败，不能重复插入");
            return 0;
        }
        return studentHistory.insertStudent(_student);
    }

    //因为iid和sid有外键约束，删除会触发外键约束，所以失败
    //父表：student
    //子表：score
    //如果子表中有相关记录，则不允许删除或更新父表中的记录。
    //在删除或更新 student 表之前，需要确保 score 表中没有依赖该记录的数据
    @Override
    public int deleteStudentById(String _sid) {
        if(_sid==null||_sid.equals(""))
        {
            System.out.println("删除失败，学号不能为空");
            return 0;
        }

        //如果被删除学生所选的课程中，存在只有该学生的记录，则删除该课程
        //1.先用sid在job表中查询对应的iid，然后存在list中
        //2.再遍历iid，在job表中查询对应的记录，如果为1，则说明只有该同学选，应删除该课程
        //3.将该课程iid存在list中
        //4.先删除job表中的记录
        //5.再删除student表中sid对应的的记录
        //6.删除item表中list中所有iid对应的记录
        if(student.queryStudentById(_sid)==null)
        {
            System.out.println("删除失败，学号不存在");
            return 0;
        }
        else {
            List<Job> list = job.queryJobBysId(_sid);
            List<String> ls = new ArrayList<>();//用于暂时存储
            for (Job value : list)
            {
                String iid = value.getIid();
                if (job.queryJobByiId(iid).size() == 1) //遍历iid，在job表中查询对应的记录，如果为1，则说明只有该同学选，应删除该课程
                {
                    ls.add(iid);
                }
            }
            job.deleteJobBysId(_sid);//确保 score 表中没有依赖该记录的数据
            if(studentHistory.queryStudentById(_sid)==null)//确保历史表中没有该记录
            {
                studentHistory.insertStudent(student.queryStudentById(_sid));//存放学生
            }
            if (list.size()!=0){
                for (String iid : ls) {
                    //存储历史记录，先存学生和课程，再存分数
                    if(itemHistory.queryItemById(iid)==null){
                        itemHistory.insertItemHistory(item.queryItemById(iid));
                    }
                    item.deleteItemById(iid);//删除只有该学生记录的课程
                }
            }
            for (Job j : list) {
                jobHistory.insertScoreHistory(j);
            }
        }
        System.out.println("删除成功");
        return student.deleteStudentById(_sid);
    }

    @Override
    public int editStudentPassword(String _sid, String _OldPassword, String _NewPassword) {
        //要判断用户输入的旧密码是否正确，正确才可以进行修改
        int flag=0;
        if(_sid==null||_OldPassword==null||_NewPassword==null)
        {
            System.out.println("输入参数不能为空");
            return 0;
        }
        else if(_OldPassword.equals( student.queryStudentById(_sid).getSpassword()))
        {
            flag = student.editStudentPassword(_sid,_NewPassword);
            System.out.println("修改成功");
        }
        else {
            System.out.println("密码错误,修改失败");
        }
        return flag;
    }

    @Override
    public int editStudentName(String _sid, String _NewName) {
        if(_sid==null||_NewName==null)
        {
            System.out.println("输入参数不能为空");
            return 0;
        }
        if(student.editStudentName(_sid, _NewName)==1)
        {
            System.out.println("修改成功");
            return 1;
        }else{
            System.out.println("修改失败,无该学生对象");
            return 0;
        }
    }

    @Override
    public int editStudentFlag(String _sid, int _NewFlag) {
        return student.editStudentFlag(_sid, _NewFlag);
    }

    @Override
    public int updateStudent(Student _student) {
        return student.updateStudent(_student);
    }
}
