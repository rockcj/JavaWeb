package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    /**
     * 查询所有院系记录
     * @return 院系对象列表
     */
    @Select("SELECT dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, create_time, update_time, dflag FROM department")
    List<Department> queryAllDepartment();

    /**
     * 根据院系ID精确查询
     * @param deptId 院系ID
     * @return Department对象
     */
    @Select("SELECT dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, create_time, update_time, dflag FROM department WHERE dept_id = #{deptId}")
    Department queryDepartmentById(@Param("deptId") String deptId);

    /**
     * 根据院系名称模糊查询
     * @param deptName 院系名称（支持模糊匹配）
     * @return 院系对象列表
     */
    @Select("SELECT dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, create_time, update_time, dflag FROM department WHERE dept_name LIKE CONCAT('%', #{deptName}, '%')")
    List<Department> queryDepartmentByName(@Param("deptName") String deptName);

    /**
     * 根据院系编码查询
     * @param deptCode 院系编码
     * @return Department对象
     */
    @Select("SELECT dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, create_time, update_time, dflag FROM department WHERE dept_code = #{deptCode}")
    Department queryDepartmentByCode(@Param("deptCode") String deptCode);

    /**
     * 新增院系记录
     * @param department 院系对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO department(dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, dflag) VALUES(#{department.deptId}, #{department.deptName}, #{department.deptCode}, #{department.deptHead}, #{department.deptPhone}, #{department.deptEmail}, #{department.deptDesc}, #{department.dflag})")
    int insertDepartment(@Param("department") Department department);

    /**
     * 删除院系记录
     * @param deptId 院系ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM department WHERE dept_id = #{deptId}")
    int deleteDepartmentById(@Param("deptId") String deptId);

    /**
     * 更新院系信息（使用XML映射文件实现复杂更新）
     * @param department 院系对象
     * @return 影响的记录数
     */
    int updateDepartment(@Param("department") Department department);

    /**
     * 修改院系状态标志
     * @param deptId 院系ID
     * @param dflag 新状态值
     * @return 影响的记录数
     */
    @Update("UPDATE department SET dflag = #{dflag} WHERE dept_id = #{deptId}")
    int editDepartmentFlag(@Param("deptId") String deptId, @Param("dflag") Integer dflag);

    /**
     * 按状态查询院系
     * @param dflag 状态标志
     * @return 院系对象列表
     */
    @Select("SELECT dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, create_time, update_time, dflag FROM department WHERE dflag = #{dflag}")
    List<Department> queryDepartmentByFlag(@Param("dflag") Integer dflag);
}