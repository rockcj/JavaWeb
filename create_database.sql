-- 创建数据库
CREATE DATABASE IF NOT EXISTS javaweb_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE javaweb_db;

-- 创建学生表
CREATE TABLE IF NOT EXISTS student (
    sid VARCHAR(20) PRIMARY KEY COMMENT '学生ID',
    sname VARCHAR(50) NOT NULL COMMENT '学生姓名',
    spassword VARCHAR(50) NOT NULL COMMENT '学生密码',
    sright INT DEFAULT 0 COMMENT '学生权限(0=普通用户,1=管理员)',
    stflag INT DEFAULT 0 COMMENT '状态标志(0=待审核,1=审核通过)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

-- 创建项目表
CREATE TABLE IF NOT EXISTS item (
    iid VARCHAR(20) PRIMARY KEY COMMENT '项目ID',
    iname VARCHAR(100) NOT NULL COMMENT '项目名称',
    iflag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目信息表';

-- 创建职位表
CREATE TABLE IF NOT EXISTS job (
    sid VARCHAR(20) NOT NULL COMMENT '学生ID',
    iid VARCHAR(20) NOT NULL COMMENT '项目ID',
    job VARCHAR(100) COMMENT '职位名称',
    scflag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
    PRIMARY KEY (sid, iid),
    FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE CASCADE,
    FOREIGN KEY (iid) REFERENCES item(iid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职位信息表';

-- 创建学生历史表
CREATE TABLE IF NOT EXISTS studenthistory (
    sid VARCHAR(20) COMMENT '学生ID',
    sname VARCHAR(50) COMMENT '学生姓名',
    spassword VARCHAR(50) COMMENT '学生密码',
    sright INT COMMENT '学生权限',
    stflag INT COMMENT '状态标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生历史记录表';

-- 创建项目历史表
CREATE TABLE IF NOT EXISTS itemhistory (
    iid VARCHAR(20) COMMENT '项目ID',
    iname VARCHAR(100) COMMENT '项目名称',
    iflag INT COMMENT '状态标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目历史记录表';

-- 创建职位历史表
CREATE TABLE IF NOT EXISTS jobhistory (
    sid VARCHAR(20) COMMENT '学生ID',
    iid VARCHAR(20) COMMENT '项目ID',
    job VARCHAR(100) COMMENT '职位名称',
    scflag INT COMMENT '状态标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职位历史记录表';

-- 插入测试数据（可选）
-- INSERT INTO student (sid, sname, spassword, sright, stflag) VALUES
-- ('001', 'admin', 'admin', 1, 1),
-- ('002', 'user1', '123456', 0, 1);

