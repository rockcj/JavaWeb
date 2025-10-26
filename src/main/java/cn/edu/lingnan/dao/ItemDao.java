package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Item;

import java.util.List;

public interface ItemDao {
    //查询所有学生记录
    //输入参数：无
    //输出参数：item对象的集合list
    public List<Item> queryAllItem();


    //按课程id进行精确查询
    public Item queryItemById(String _iid);

    //按学生姓名进行模糊查询
    //返回多个对象，使用List集合
    public List<Item> queryItemByName(String _iname);

    public int insertItem(Item _item);

    //删除item记录
    public int deleteItemById(String _iid);

    //修改item记录
    public int editItem(Item _item);
}
