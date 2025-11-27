package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {

    /**
     * 查询所有项目记录
     * @return 项目对象列表
     */
    @Select("SELECT * FROM item")
    List<Item> queryAllItem();

    /**
     * 按项目ID精确查询
     * @param _iid 项目ID
     * @return Item对象
     */
    @Select("SELECT * FROM item WHERE iid = #{_iid}")
    Item queryItemById(@Param("_iid") String _iid);

    /**
     * 按项目名称模糊查询
     * @param _iname 项目名称（支持模糊匹配）
     * @return 项目对象列表
     */
    @Select("SELECT * FROM item WHERE iname LIKE CONCAT('%', #{_iname}, '%')")
    List<Item> queryItemByName(@Param("_iname") String _iname);

    /**
     * 新增项目记录
     * @param _item 项目对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO item(iid, iname, iflag) VALUES(#{_item.iid}, #{_item.iname}, #{_item.iflag})")
    int insertItem(@Param("_item") Item _item);

    /**
     * 根据ID删除项目记录
     * @param _iid 项目ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM item WHERE iid = #{_iid}")
    int deleteItemById(@Param("_iid") String _iid);

    /**
     * 修改项目信息
     * @param _item 项目对象
     * @return 影响的记录数
     */
    @Update("UPDATE item SET iname = #{_item.iname}, iflag = #{_item.iflag} WHERE iid = #{_item.iid}")
    int editItem(@Param("_item") Item _item);
}