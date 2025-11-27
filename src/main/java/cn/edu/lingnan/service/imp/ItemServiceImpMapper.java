package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.*;
import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.util.MyBatisUtil;

import java.util.List;

public class ItemServiceImpMapper implements ItemService {

    @Override
    public List<Item> queryAllItem() {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            return itemMapper.queryAllItem();
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public Item queryItemById(String _iid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            return itemMapper.queryItemById(_iid);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Item> queryItemByName(String _iname) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            return itemMapper.queryItemByName(_iname);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public void insertItem(Item _item) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);

            // 检查项目是否已存在
            Item existingItem = itemMapper.queryItemById(_item.getIid());
            if (existingItem != null) {
                System.out.println("项目已存在");
                return;
            }

            int result = itemMapper.insertItem(_item);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("插入项目成功");
            } else {
                sqlSession.rollback();
                System.out.println("插入项目失败");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("插入项目时发生错误: " + e.getMessage());
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public void deleteItemById(String _iid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            ItemHistoryMapper itemHistoryMapper = sqlSession.getMapper(ItemHistoryMapper.class);
            StudentHistoryMapper studentHistoryMapper = sqlSession.getMapper(StudentHistoryMapper.class);

            // 先查询项目是否存在
            Item item = itemMapper.queryItemById(_iid);
            if (item == null) {
                System.out.println("项目不存在");
                return;
            }

            // 保存项目到历史表
            itemHistoryMapper.insertItemHistory(item);

            // 查询所有与该项目相关的学生
            List<Student> students = jobMapper.queryJobByiId(_iid)
                    .stream()
                    .map(job -> {
                        var session = MyBatisUtil.getSqlSession();
                        try {
                            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
                            return studentMapper.queryStudentById(job.getSid());
                        } finally {
                            MyBatisUtil.closeSession(session);
                        }
                    })
                    .toList();

            // 将相关学生保存到历史表
            for (Student student : students) {
                if (student != null) {
                    studentHistoryMapper.insertStudent(student);
                }
            }

            // 删除相关的job记录
            jobMapper.deleteJobByiId(_iid);

            // 删除项目
            int result = itemMapper.deleteItemById(_iid);

            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除项目成功");
            } else {
                sqlSession.rollback();
                System.out.println("删除项目失败");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除项目时发生错误: " + e.getMessage());
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editItem(String _iid, String _iname, int _iflag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);

            // 先查询项目是否存在
            Item item = itemMapper.queryItemById(_iid);
            if (item == null) {
                System.out.println("项目不存在");
                return 0;
            }

            // 更新项目信息
            item.setIname(_iname);
            item.setIflag(_iflag);
            int result = itemMapper.editItem(item);

            if (result > 0) {
                sqlSession.commit();
                System.out.println("更新项目成功");
            } else {
                sqlSession.rollback();
                System.out.println("更新项目失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("更新项目时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }
}