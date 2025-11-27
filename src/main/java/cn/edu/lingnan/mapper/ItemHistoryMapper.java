package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Item;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ItemHistoryMapper {

    /**
     * 将项目信息存入历史表
     * @param _item 项目对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO itemhistory(iid, iname, iflag) VALUES(#{_item.iid}, #{_item.iname}, #{_item.iflag})")
    int insertItemHistory(@Param("_item") Item _item);

    /**
     * 从历史表查询项目信息
     * @param _iid 项目ID
     * @return Item对象
     */
    @Select("SELECT * FROM itemhistory WHERE iid = #{_iid}")
    Item queryItemById(@Param("_iid") String _iid);
}