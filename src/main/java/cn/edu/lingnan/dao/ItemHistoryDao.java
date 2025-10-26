package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Item;

public interface ItemHistoryDao {
    public int insertItemHistory(Item _item);
    Item queryItemById(String _iid);
}
