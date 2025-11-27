package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Department;

import java.util.List;

public interface DepartmentService {

    /**
     * 查询所有院系记录
     * @return 院系对象列表
     */
    List<Department> queryAllDepartment();

    /**
     * 根据院系ID精确查询
     * @param deptId 院系ID
     * @return Department对象
     */
    Department queryDepartmentById(String deptId);

    /**
     * 根据院系名称模糊查询
     * @param deptName 院系名称（支持模糊匹配）
     * @return 院系对象列表
     */
    List<Department> queryDepartmentByName(String deptName);

    /**
     * 根据院系编码查询
     * @param deptCode 院系编码
     * @return Department对象
     */
    Department queryDepartmentByCode(String deptCode);

    /**
     * 新增院系记录
     * @param department 院系对象
     * @return 影响的记录数
     */
    int insertDepartment(Department department);

    /**
     * 删除院系记录
     * @param deptId 院系ID
     * @return 影响的记录数
     */
    int deleteDepartmentById(String deptId);

    /**
     * 更新院系信息
     * @param department 院系对象
     * @return 影响的记录数
     */
    int updateDepartment(Department department);

    /**
     * 修改院系状态标志
     * @param deptId 院系ID
     * @param dflag 新状态值
     * @return 影响的记录数
     */
    int editDepartmentFlag(String deptId, int dflag);

    /**
     * 按状态查询院系
     * @param dflag 状态标志
     * @return 院系对象列表
     */
    List<Department> queryDepartmentByFlag(int dflag);
}