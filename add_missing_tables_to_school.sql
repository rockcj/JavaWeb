-- 为school数据库添加缺失的表
-- 注意：执行此脚本前请确保已使用school数据库
USE school;

-- 1. 教师表(Teacher) - 如果不存在则创建
CREATE TABLE IF NOT EXISTS `teacher` (
    `teacher_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师ID',
    `teacher_name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师姓名',
    `gender` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
    `age` INT(0) NULL DEFAULT NULL COMMENT '年龄',
    `phone` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
    `email` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
    `dept_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属院系ID',
    `title` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职称',
    `tflag` INT(0) NULL DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
    PRIMARY KEY (`teacher_id`) USING BTREE,
    INDEX `dept_id`(`dept_id`) USING BTREE,
    CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;

-- 2. 成绩表(Score) - 如果不存在则创建
CREATE TABLE IF NOT EXISTS `score` (
    `score_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '成绩ID',
    `student_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生ID',
    `course_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
    `score` DECIMAL(5, 2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '成绩',
    `semester` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学期',
    `exam_type` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试类型(期中/期末/平时)',
    `flag` INT(0) NULL DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
    PRIMARY KEY (`score_id`) USING BTREE,
    INDEX `student_id`(`student_id`) USING BTREE,
    INDEX `course_id`(`course_id`) USING BTREE,
    CONSTRAINT `score_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `score_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生成绩表' ROW_FORMAT = Dynamic;

-- 3. 课程表(TimeTable) - 如果不存在则创建
CREATE TABLE IF NOT EXISTS `timetable` (
    `timetable_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课表ID',
    `course_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
    `teacher_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师ID',
    `classroom_id` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教室ID',
    `day_of_week` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '星期几',
    `time_slot` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '时间段',
    `semester` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学期',
    `tflag` INT(0) NULL DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
    PRIMARY KEY (`timetable_id`) USING BTREE,
    INDEX `course_id`(`course_id`) USING BTREE,
    INDEX `teacher_id`(`teacher_id`) USING BTREE,
    CONSTRAINT `timetable_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `timetable_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程表信息' ROW_FORMAT = Dynamic;

-- 插入一些示例数据（可选）
-- INSERT IGNORE INTO teacher (teacher_id, teacher_name, gender, age, phone, email, dept_id, title) VALUES
-- ('T001', '陈老师', '男', 35, '13800138001', 'chen@lingnan.edu.cn', 'D001', '副教授'),
-- ('T002', '林老师', '女', 40, '13800138002', 'lin@lingnan.edu.cn', 'D001', '教授'),
-- ('T003', '黄老师', '男', 38, '13800138003', 'huang@lingnan.edu.cn', 'D002', '副教授');

