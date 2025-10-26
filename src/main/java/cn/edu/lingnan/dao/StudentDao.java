package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Student;

import java.util.List;

public interface StudentDao {
    //第一个方法：功能：实现登录
    //输入参数：用户名，密码
    //输出参数：如果登录成功，返回true，否则返回false
    public Student queryStudentByNameAndPassword(String _sname, String _spassword);
    //第二个方法：功能：查询所有学生记录
    //输入参数：无
    //输出参数：学生对象的集合list
    public List<Student> queryAllStudentAll(int log);


    /**
     * JDBC 的接口 PreparedStatement 表示预编译的sql语句的对象
     * sql语句被预编译并且存储在PreparedStatement 对象中，以便于使用该对象多次的执行sql语句
     * PreparedStatement中的sql语句往往式不完整的，带多个问好表示缺省的参数，用相应的setter方法
     * (setInt、setString...)z指定与输入参数的以定义sql类型兼容的类型
     * */
    //第三个方法：功能：按学生id进行精确查询
     public Student queryStudentById(String _sid);

     //第四个方法：功能：按学生姓名进行模糊查询
     //返回多个对象，使用List集合
     public List<Student> queryStudentByName(String _sname);

     //第五个方法：
     //功能：新增学生记录
     //输入参数：学生对象
     //输出参数：int 执行成功的记录数，一般为1； 失败：2种情况：-1、直接报错，可以设置失败为0
     public int insertStudent(Student _student);

     //删除
     public int deleteStudentById(String _sid);

     //修改密码
     public int editStudentPassword(String _sid, String _NewPassword);

     //修改姓名
     public int editStudentName(String _sid, String _NewName);

     //修改权限
      public int editStudentFlag(String _sid, int _NewFlag);

     public int updateStudent(Student _student);
}
