-- 创建所有必要的表
-- 注意：有些表可能已经存在，如果出现表已存在的错误可以忽略

-- 1. 院系表(Department)
CREATE TABLE IF NOT EXISTS department (
    dept_id VARCHAR(20) PRIMARY KEY COMMENT '院系ID',
    dept_name VARCHAR(100) NOT NULL COMMENT '院系名称',
    dept_code VARCHAR(20) UNIQUE NOT NULL COMMENT '院系编码',
    dept_head VARCHAR(50) COMMENT '院系负责人',
    dept_phone VARCHAR(20) COMMENT '联系电话',
    dept_email VARCHAR(100) COMMENT '邮箱',
    dept_desc TEXT COMMENT '院系描述',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    dflag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='院系信息表';

-- 2. 课程表(Course)
CREATE TABLE IF NOT EXISTS course (
    course_id VARCHAR(20) PRIMARY KEY COMMENT '课程ID',
    course_name VARCHAR(100) NOT NULL COMMENT '课程名称',
    course_code VARCHAR(20) UNIQUE NOT NULL COMMENT '课程编码',
    dept_id VARCHAR(20) COMMENT '所属院系ID',
    credit DECIMAL(4,1) DEFAULT 0.0 COMMENT '学分',
    course_hours INT DEFAULT 0 COMMENT '学时',
    course_type VARCHAR(20) DEFAULT '必修' COMMENT '课程类型(必修/选修)',
    teacher_name VARCHAR(50) COMMENT '任课教师',
    semester VARCHAR(20) COMMENT '学期',
    course_desc TEXT COMMENT '课程描述',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    cflag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
    FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程信息表';

-- 3. 教师表(Teacher)
CREATE TABLE IF NOT EXISTS teacher (
    teacher_id VARCHAR(20) PRIMARY KEY COMMENT '教师ID',
    teacher_name VARCHAR(50) NOT NULL COMMENT '教师姓名',
    gender VARCHAR(10) COMMENT '性别',
    age INT COMMENT '年龄',
    phone VARCHAR(20) COMMENT '电话',
    email VARCHAR(100) COMMENT '邮箱',
    dept_id VARCHAR(20) COMMENT '所属院系ID',
    title VARCHAR(20) COMMENT '职称',
    tflag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
    FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师信息表';

-- 4. 成绩表(Score)
CREATE TABLE IF NOT EXISTS score (
    score_id VARCHAR(20) PRIMARY KEY COMMENT '成绩ID',
    student_id VARCHAR(20) NOT NULL COMMENT '学生ID',
    course_id VARCHAR(20) NOT NULL COMMENT '课程ID',
    score DECIMAL(5,2) COMMENT '成绩',
    semester VARCHAR(20) COMMENT '学期',
    exam_type VARCHAR(20) COMMENT '考试类型(期中/期末/平时)',
    flag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生成绩表';

-- 5. 课程表(TimeTable)
CREATE TABLE IF NOT EXISTS timetable (
    timetable_id VARCHAR(20) PRIMARY KEY COMMENT '课表ID',
    course_id VARCHAR(20) NOT NULL COMMENT '课程ID',
    teacher_id VARCHAR(20) NOT NULL COMMENT '教师ID',
    classroom_id VARCHAR(20) COMMENT '教室ID',
    day_of_week VARCHAR(10) COMMENT '星期几',
    time_slot VARCHAR(20) COMMENT '时间段',
    semester VARCHAR(20) COMMENT '学期',
    tflag INT DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表信息';

-- 插入一些示例数据（如果表为空）
INSERT IGNORE INTO department (dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc) VALUES
('D001', '计算机科学与技术学院', 'CS', '张教授', '020-12345678', 'cs@lingnan.edu.cn', '培养计算机科学与技术专业人才'),
('D002', '软件工程学院', 'SE', '李教授', '020-87654321', 'se@lingnan.edu.cn', '培养软件开发与应用人才'),
('D003', '数据科学学院', 'DS', '王教授', '020-11223344', 'ds@lingnan.edu.cn', '培养数据科学与大数据技术人才');

INSERT IGNORE INTO course (course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc) VALUES
('C001', 'Java程序设计', 'CS101', 'D001', 3.0, 64, '必修', '陈老师', '2024春季', '学习Java语言基础和面向对象编程'),
('C002', '数据库原理', 'CS201', 'D001', 3.5, 72, '必修', '林老师', '2024春季', '学习关系数据库理论和SQL编程'),
('C003', 'Web开发技术', 'SE102', 'D002', 3.0, 64, '选修', '黄老师', '2024秋季', '学习Web前后端开发技术'),
('C004', '数据结构与算法', 'CS103', 'D001', 4.0, 80, '必修', '张老师', '2024春季', '学习基本数据结构和算法设计'),
('C005', '机器学习基础', 'DS201', 'D003', 3.0, 60, '选修', '刘老师', '2024秋季', '学习机器学习基本算法和应用');

INSERT IGNORE INTO teacher (teacher_id, teacher_name, gender, age, phone, email, dept_id, title) VALUES
('T001', '陈老师', '男', 35, '13800138001', 'chen@lingnan.edu.cn', 'D001', '副教授'),
('T002', '林老师', '女', 40, '13800138002', 'lin@lingnan.edu.cn', 'D001', '教授'),
('T003', '黄老师', '男', 38, '13800138003', 'huang@lingnan.edu.cn', 'D002', '副教授'),
('T004', '张老师', '女', 45, '13800138004', 'zhang@lingnan.edu.cn', 'D001', '教授'),
('T005', '刘老师', '男', 42, '13800138005', 'liu@lingnan.edu.cn', 'D003', '副教授');