package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Item;

import java.util.List;

public interface ItemService {
    //查询所有学生记录
    //输入参数：无
    //输出参数：item对象的集合list
    public List<Item> queryAllItem();


    //按课程id进行精确查询
    public Item queryItemById(String _iid);

    //按学生姓名进行模糊查询
    //返回多个对象，使用List集合
    public List<Item> queryItemByName(String _iname);

    public void insertItem(Item _item);

    //删除item记录
    public void deleteItemById(String _iid);

    public int editItem(String _iid, String _iname, int _iflag);
}
