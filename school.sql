/*
 Navicat Premium Data Transfer

 Source Server         : 15
 Source Server Type    : MySQL
 Source Server Version : 80038
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 80038
 File Encoding         : 65001

 Date: 30/11/2025 22:27:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `course_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `course_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程编码',
  `dept_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属院系ID',
  `credit` decimal(4, 1) NULL DEFAULT 0.0 COMMENT '学分',
  `course_hours` int(0) NULL DEFAULT 0 COMMENT '学时',
  `course_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '必修' COMMENT '课程类型(必修/选修)',
  `teacher_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任课教师',
  `semester` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学期',
  `course_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '课程描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `cflag` int(0) NULL DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
  PRIMARY KEY (`course_id`) USING BTREE,
  UNIQUE INDEX `course_code`(`course_code`) USING BTREE,
  INDEX `dept_id`(`dept_id`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('C001', 'Java程序设计', 'CS101', 'D001', 3.0, 64, '必修', '陈老师', '2024春季', '学习Java语言基础和面向对象编程', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);
INSERT INTO `course` VALUES ('C002', '数据库原理', 'CS201', 'D001', 3.5, 72, '必修', '林老师', '2024春季', '学习关系数据库理论和SQL编程', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);
INSERT INTO `course` VALUES ('C003', 'Web开发技术', 'SE102', 'D002', 3.0, 64, '选修', '黄老师', '2024秋季', '学习Web前后端开发技术', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);
INSERT INTO `course` VALUES ('C004', '数据结构与算法', 'CS103', 'D001', 4.0, 80, '必修', '张老师', '2024春季', '学习基本数据结构和算法设计', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);
INSERT INTO `course` VALUES ('C005', '机器学习基础', 'DS201', 'D003', 3.0, 60, '选修', '刘老师', '2024秋季', '学习机器学习基本算法和应用', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);

-- ----------------------------
-- Table structure for coursehistory
-- ----------------------------
DROP TABLE IF EXISTS `coursehistory`;
CREATE TABLE `coursehistory`  (
  `course_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程ID',
  `course_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程编码',
  `dept_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属院系ID',
  `credit` decimal(4, 1) NULL DEFAULT NULL COMMENT '学分',
  `course_hours` int(0) NULL DEFAULT NULL COMMENT '学时',
  `course_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程类型',
  `teacher_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任课教师',
  `semester` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学期',
  `course_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '课程描述',
  `cflag` int(0) NULL DEFAULT NULL COMMENT '状态标志'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程历史记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dept_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '院系ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '院系名称',
  `dept_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '院系编码',
  `dept_head` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系负责人',
  `dept_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `dept_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `dept_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '院系描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `dflag` int(0) NULL DEFAULT 0 COMMENT '状态标志(0=正常,1=删除)',
  PRIMARY KEY (`dept_id`) USING BTREE,
  UNIQUE INDEX `dept_code`(`dept_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '院系信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('D001', '计算机科学与技术学院', 'CS', '张教授', '020-12345678', 'cs@lingnan.edu.cn', '培养计算机科学与技术专业人才', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);
INSERT INTO `department` VALUES ('D002', '软件工程学院', 'SE', '李教授', '020-87654321', 'se@lingnan.edu.cn', '培养软件开发与应用人才', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);
INSERT INTO `department` VALUES ('D003', '数据科学学院', 'DS', '王教授', '020-11223344', 'ds@lingnan.edu.cn', '培养数据科学与大数据技术人才', '2025-10-26 23:08:21', '2025-10-26 23:08:21', 0);

-- ----------------------------
-- Table structure for departmenthistory
-- ----------------------------
DROP TABLE IF EXISTS `departmenthistory`;
CREATE TABLE `departmenthistory`  (
  `dept_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系名称',
  `dept_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系编码',
  `dept_head` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系负责人',
  `dept_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `dept_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `dept_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '院系描述',
  `dflag` int(0) NULL DEFAULT NULL COMMENT '状态标志'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '院系历史记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `iid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iflag` int(0) NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('003', 'iu', 1);
INSERT INTO `item` VALUES ('005', 'i立刻就哦i', 1);
INSERT INTO `item` VALUES ('1', '科学项目', 0);
INSERT INTO `item` VALUES ('111', '应用项目', 0);
INSERT INTO `item` VALUES ('2', '数学项目', 2);
INSERT INTO `item` VALUES ('24', '版本', 1);

-- ----------------------------
-- Table structure for itemhistory
-- ----------------------------
DROP TABLE IF EXISTS `itemhistory`;
CREATE TABLE `itemhistory`  (
  `iid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iflag` int(0) NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of itemhistory
-- ----------------------------
INSERT INTO `itemhistory` VALUES ('100', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('101', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('102', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('103', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('104', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('105', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('106', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('107', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('108', 'jjj', 8);
INSERT INTO `itemhistory` VALUES ('11', 'jj哦', 27);
INSERT INTO `itemhistory` VALUES ('110', 'jjj', 8);
INSERT INTO `itemhistory` VALUES ('111', 'jjj', 22);
INSERT INTO `itemhistory` VALUES ('112', 'jjj', 27);
INSERT INTO `itemhistory` VALUES ('114', 'jjj', 19);
INSERT INTO `itemhistory` VALUES ('115', 'jjj', 27);
INSERT INTO `itemhistory` VALUES ('116', 'jjj', 27);
INSERT INTO `itemhistory` VALUES ('117', 'jjj', 19);
INSERT INTO `itemhistory` VALUES ('12', 'jjo', 1);
INSERT INTO `itemhistory` VALUES ('121', 'jjj', 1);
INSERT INTO `itemhistory` VALUES ('122', 'jjj', 1);
INSERT INTO `itemhistory` VALUES ('123', '急急急', 1);
INSERT INTO `itemhistory` VALUES ('13', 'fvu', 2);
INSERT INTO `itemhistory` VALUES ('14', 'jjj', 27);
INSERT INTO `itemhistory` VALUES ('143', 'tjgu', 11);
INSERT INTO `itemhistory` VALUES ('144', '好好好', 19);
INSERT INTO `itemhistory` VALUES ('145', 'yg', 123);
INSERT INTO `itemhistory` VALUES ('147', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('166', 'jjj', 19);
INSERT INTO `itemhistory` VALUES ('17', 'jjj', 21);
INSERT INTO `itemhistory` VALUES ('177', 'jjj', 19);
INSERT INTO `itemhistory` VALUES ('18', 'j匹配', 21);
INSERT INTO `itemhistory` VALUES ('191', '好好好', 19);
INSERT INTO `itemhistory` VALUES ('21', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('222', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('23', '右有', 1);
INSERT INTO `itemhistory` VALUES ('25', '简洁', 1);
INSERT INTO `itemhistory` VALUES ('254', 'ijodf', 32);
INSERT INTO `itemhistory` VALUES ('3', '物理项目', 1);
INSERT INTO `itemhistory` VALUES ('30', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('301', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('302', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('303', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('304', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('305', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('311', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('321', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('322', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('33', '有', 1);
INSERT INTO `itemhistory` VALUES ('333', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('34', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('35', '有', 1);
INSERT INTO `itemhistory` VALUES ('36', 'ey', 20);
INSERT INTO `itemhistory` VALUES ('37', '有', 1);
INSERT INTO `itemhistory` VALUES ('38', '有', 1);
INSERT INTO `itemhistory` VALUES ('4', '英语', 1);
INSERT INTO `itemhistory` VALUES ('41', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('42', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('444', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('45', '让人', 1);
INSERT INTO `itemhistory` VALUES ('456', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('46', '让人', 1);
INSERT INTO `itemhistory` VALUES ('47', '让人', 1);
INSERT INTO `itemhistory` VALUES ('574', 'sgsgrw', 42);
INSERT INTO `itemhistory` VALUES ('6', '新物理', 2);
INSERT INTO `itemhistory` VALUES ('66', '啦啦啦', 1);
INSERT INTO `itemhistory` VALUES ('674', 'fijo', 87);
INSERT INTO `itemhistory` VALUES ('7', '陈', 0);
INSERT INTO `itemhistory` VALUES ('70', '撒打', 2);
INSERT INTO `itemhistory` VALUES ('71', '撒打算', 2);
INSERT INTO `itemhistory` VALUES ('74', '撒打', 2);
INSERT INTO `itemhistory` VALUES ('75', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('77', '撒打算', 2);
INSERT INTO `itemhistory` VALUES ('777', 'jjj', 7);
INSERT INTO `itemhistory` VALUES ('78', '撒打', 2);
INSERT INTO `itemhistory` VALUES ('780', 'ads', 1);
INSERT INTO `itemhistory` VALUES ('781', 'hsvdjh', 1);
INSERT INTO `itemhistory` VALUES ('784', 'ads', 1);
INSERT INTO `itemhistory` VALUES ('81', '急急急', 2);
INSERT INTO `itemhistory` VALUES ('86', '急急急', 19);
INSERT INTO `itemhistory` VALUES ('861', '急急急', 20);
INSERT INTO `itemhistory` VALUES ('866', '急急急', 20);
INSERT INTO `itemhistory` VALUES ('867', '急急急', 1);
INSERT INTO `itemhistory` VALUES ('87', '科学', 1);
INSERT INTO `itemhistory` VALUES ('88', '科学项目', 0);
INSERT INTO `itemhistory` VALUES ('89', '急急急', 1);
INSERT INTO `itemhistory` VALUES ('932', 'sbcjcnm', 69);
INSERT INTO `itemhistory` VALUES ('97', '陈', 1980);
INSERT INTO `itemhistory` VALUES ('98', '急急急', 1);
INSERT INTO `itemhistory` VALUES ('99', '急急急', 1);

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `sid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `scflag` int(0) NOT NULL,
  PRIMARY KEY (`sid`, `iid`) USING BTREE,
  INDEX `cid`(`iid`) USING BTREE,
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('001', '1', '老师', 2);
INSERT INTO `job` VALUES ('001', '2', '辅导员', 1);
INSERT INTO `job` VALUES ('002', '111', '经理人', 2);
INSERT INTO `job` VALUES ('003', '2', '老师', 7);
INSERT INTO `job` VALUES ('010', '005', '哦哦', 1);
INSERT INTO `job` VALUES ('010', '2', '看看', 1);
INSERT INTO `job` VALUES ('888', '24', '老师', 7);

-- ----------------------------
-- Table structure for jobhistory
-- ----------------------------
DROP TABLE IF EXISTS `jobhistory`;
CREATE TABLE `jobhistory`  (
  `sid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `scflag` int(0) NOT NULL,
  `job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`sid`, `cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jobhistory
-- ----------------------------
INSERT INTO `jobhistory` VALUES ('005', '1', 3, '指挥');
INSERT INTO `jobhistory` VALUES ('005', '3', 1, '项目经理');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `spassword` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sright` int(0) NOT NULL,
  `stflag` int(0) NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('001', 'admin', 'admin', 1, 1);
INSERT INTO `student` VALUES ('002', '甲', '123', 0, 1);
INSERT INTO `student` VALUES ('003', '乙', '345', 0, 1);
INSERT INTO `student` VALUES ('010', '还不i', '111', 0, 1);
INSERT INTO `student` VALUES ('012', '密码', '111', 0, 1);
INSERT INTO `student` VALUES ('018', '中山', '123', 0, 1);
INSERT INTO `student` VALUES ('485', '简介', '123', 0, 1);
INSERT INTO `student` VALUES ('888', 'kkk', '111', 0, 1);

-- ----------------------------
-- Table structure for studenthistory
-- ----------------------------
DROP TABLE IF EXISTS `studenthistory`;
CREATE TABLE `studenthistory`  (
  `sid` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `spassword` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sright` int(0) NOT NULL,
  `stflag` int(0) NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of studenthistory
-- ----------------------------
INSERT INTO `studenthistory` VALUES ('004', '丙', '666', 0, 2);
INSERT INTO `studenthistory` VALUES ('005', '啊啊', 'abcd', 0, 1);
INSERT INTO `studenthistory` VALUES ('006', '2b', '777', 0, 0);
INSERT INTO `studenthistory` VALUES ('007', '小孩', '888', 0, 0);
INSERT INTO `studenthistory` VALUES ('008', 'gg', '456', 0, 0);
INSERT INTO `studenthistory` VALUES ('011', '8iyb', '111', 0, 1);
INSERT INTO `studenthistory` VALUES ('013', '酷酷酷', '111', 0, 0);
INSERT INTO `studenthistory` VALUES ('017', '还不i', '111', 0, 0);
INSERT INTO `studenthistory` VALUES ('99', '陈', '1980', 0, 0);

-- ----------------------------
-- View structure for jobview
-- ----------------------------
DROP VIEW IF EXISTS `jobview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `jobview` AS select `j`.`sid` AS `sid`,`j`.`iid` AS `iid`,`stu`.`sname` AS `sname`,`it`.`iname` AS `iname`,`j`.`job` AS `job` from ((`job` `j` join `student` `stu` on((`j`.`sid` = `stu`.`sid`))) join `item` `it` on((`j`.`iid` = `it`.`iid`)));

SET FOREIGN_KEY_CHECKS = 1;
