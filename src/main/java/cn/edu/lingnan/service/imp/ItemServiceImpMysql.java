package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.dao.*;
import cn.edu.lingnan.dao.imp.*;
import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.ItemService;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpMysql implements ItemService {
    ItemDao itemDao = new ItemDaoImpIMysql();
    StudentDao student = new StudentDaoImpIMysql();
    JobDao job = new JobDaoImpIMysql();

    StudentHistoryDao studentHistory = new StudentHistoryDaoImpIMysql();
    JobHistoryDao jobHistory = new JobHistoryDaoImpIMysql();
    ItemHistoryDao itemHistory = new ItemHistoryDaoImpIMysql();

    @Override
    public List<Item> queryAllItem() {
        return itemDao.queryAllItem();
    }

    @Override
    public Item queryItemById(String _iid) {
        return itemDao.queryItemById(_iid);
    }

    @Override
    public List<Item> queryItemByName(String _iname) {
        return itemDao.queryItemByName(_iname);
    }

    @Override
    public void insertItem(Item _item) {
        if(itemDao.queryItemById(_item.getIid())==null)
        {
            itemDao.insertItem(_item);
            System.out.println("插入成功");
        }else {
            System.out.println("该项目已存在，插入失败");
        }
    }

    @Override
    public void deleteItemById(String _iid) {
        //如果被删除的项目所对应的学生是只选了这一个项目的，则删除该学生
        //1.先用iid在job表中查询对应的sid，然后存在list中
        //2.再遍历sid，在job表中查询对应的记录，如果为1，则说明该同学只选了这个项目，应删除该学生
        //3.将该sid存在list中              //相当于查2遍表
        //4.先删除job表中的记录
        //5.再删除item表中iid对应的的记录
        //6.删除student表中list中所有sid对应的记录(即只选了这一个项目的学生)
        if(_iid==null||_iid.equals(""))
        {
            System.out.println("删除失败，项目不能为空");
        }
        else if(itemDao.queryItemById(_iid)==null)
        {
            System.out.println("该项目不存在，删除失败");
        }
        else {
            List<Job> list = job.queryJobBysId(_iid);
            List<String> ls = new ArrayList<>();//用于暂时存储
            for (Job value : list)
            {
                String sid = value.getSid();
                if (job.queryJobByiId(sid).size() == 1) //遍历iid，在job表中查询对应的记录，如果为1，则说明只有该同学选，应删除该课程
                {
                    ls.add(sid);
                }
            }
            job.deleteJobByiId(_iid);//确保 score 表中没有依赖该记录的数据
            if(itemHistory.queryItemById(_iid)==null){
                itemHistory.insertItemHistory(itemDao.queryItemById(_iid));
            }
            if(list.size()!=0){
                for (String sid : ls) {
                    if(studentHistory.queryStudentById(sid)==null)//确保历史表中没有该记录
                    {
                        studentHistory.insertStudent(student.queryStudentById(sid));//存放学生
                    }
                    student.deleteStudentById(sid);//删除只选了这一个项目的学生
                }
            }
            for (Job j : list) {
                jobHistory.insertScoreHistory(j);
            }
        }
        itemDao.deleteItemById(_iid);
        System.out.println("删除成功");
    }

    @Override
    public int editItem(String _iid, String _iname, int _iflag) 
    {
        //id是不允许用户修改的
        Item item = new Item(_iid, _iname, _iflag);
        int flag=0;
        //修改
        if (itemDao.queryItemById(_iid) != null) //保证对象存在
        {
            flag = itemDao.editItem(item);
            System.out.println("修改成功");
        }
        else {
            System.out.println("输入参数错误/id不存在");
        }
        return flag;
    }
}
