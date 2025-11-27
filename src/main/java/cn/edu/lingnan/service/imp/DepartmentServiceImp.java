package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.DepartmentMapper;
import cn.edu.lingnan.pojo.Department;
import cn.edu.lingnan.service.DepartmentService;
import cn.edu.lingnan.util.MyBatisUtil;

import java.util.List;

public class DepartmentServiceImp implements DepartmentService {

    @Override
    public List<Department> queryAllDepartment() {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentMapper.queryAllDepartment();
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public Department queryDepartmentById(String deptId) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentMapper.queryDepartmentById(deptId);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Department> queryDepartmentByName(String deptName) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentMapper.queryDepartmentByName(deptName);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public Department queryDepartmentByCode(String deptCode) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentMapper.queryDepartmentByCode(deptCode);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int insertDepartment(Department department) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            // 检查院系是否已存在
            Department existingDept = departmentMapper.queryDepartmentById(department.getDeptId());
            if (existingDept != null) {
                System.out.println("院系已存在");
                return 0;
            }

            // 检查院系编码是否已存在
            Department existingDeptByCode = departmentMapper.queryDepartmentByCode(department.getDeptCode());
            if (existingDeptByCode != null) {
                System.out.println("院系编码已存在");
                return 0;
            }

            int result = departmentMapper.insertDepartment(department);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("插入院系成功");
            } else {
                sqlSession.rollback();
                System.out.println("插入院系失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("插入院系时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int deleteDepartmentById(String deptId) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            // 先查询院系是否存在
            Department department = departmentMapper.queryDepartmentById(deptId);
            if (department == null) {
                System.out.println("院系不存在");
                return 0;
            }

            int result = departmentMapper.deleteDepartmentById(deptId);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除院系成功");
            } else {
                sqlSession.rollback();
                System.out.println("删除院系失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除院系时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int updateDepartment(Department department) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            // 先查询院系是否存在
            Department existingDept = departmentMapper.queryDepartmentById(department.getDeptId());
            if (existingDept == null) {
                System.out.println("院系不存在");
                return 0;
            }

            int result = departmentMapper.updateDepartment(department);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("更新院系成功");
            } else {
                sqlSession.rollback();
                System.out.println("更新院系失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("更新院系时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editDepartmentFlag(String deptId, int dflag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            // 先查询院系是否存在
            Department department = departmentMapper.queryDepartmentById(deptId);
            if (department == null) {
                System.out.println("院系不存在");
                return 0;
            }

            int result = departmentMapper.editDepartmentFlag(deptId, dflag);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("修改院系状态成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改院系状态时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Department> queryDepartmentByFlag(int dflag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentMapper.queryDepartmentByFlag(dflag);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }
}