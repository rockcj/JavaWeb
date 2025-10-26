package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;

import java.util.Scanner;

public class ItemController {
     ItemService itemService = new ItemServiceImpMysql();
      public void editItemController()
      {
          System.out.println("所有item信息如下:");
          System.out.println(itemService.queryAllItem());
          Scanner sc = new Scanner(System.in);
          System.out.print("输入要修改的 iid: ");
          String iid = sc.next();
          System.out.println("要修改的item信息如下:");
          System.out.println(itemService.queryItemById(iid));
          System.out.print("新名称iname: ");
          String newName = sc.next();
          System.out.print("新标志位iflaag: ");
          int newFlag = sc.nextInt();
          itemService.editItem(iid, newName, newFlag);
      }
}
