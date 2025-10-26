package cn.edu.lingnan.dao;

import cn.edu.lingnan.dao.imp.ItemDaoImpIMysql;
import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import org.junit.Test;

import java.util.Scanner;

public class ItemDaoTest {
    ItemDao itemDao = new ItemDaoImpIMysql();
    ItemService itemService = new ItemServiceImpMysql();
    @Test
    public void queryAllitemAll(){
        System.out.println(itemDao.queryAllItem());
    }

    @Test
    public void queryitemById(){
        System.out.println(itemDao.queryItemById("1"));
    }

    @Test
    public void queryitemByName(){
        System.out.println(itemDao.queryItemByName("文"));
    }

    @Test
    public void insertitem(){
        Item item = new Item("6","科学",1);
        itemService.insertItem(item);
    }

    @Test
    public void edititem(){
        System.out.println(itemDao.queryItemById("6"));
        itemService.editItem("6","新物理",2);
        System.out.println(itemDao.queryItemById("6"));
    }

    @Test
    public void deleteitem(){
        itemService.deleteItemById("3");
    }

}
