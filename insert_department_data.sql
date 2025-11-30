-- 为院系表插入更多测试数据
USE school;

-- 如果院系表已有数据，先检查是否已存在，避免重复插入
INSERT IGNORE INTO department (dept_id, dept_name, dept_code, dept_head, dept_phone, dept_email, dept_desc, dflag) VALUES
('D004', '电子信息工程学院', 'EE', '刘教授', '020-22334455', 'ee@lingnan.edu.cn', '培养电子信息工程专业人才', 0),
('D005', '机械工程学院', 'ME', '王教授', '020-33445566', 'me@lingnan.edu.cn', '培养机械工程专业人才', 0),
('D006', '经济管理学院', 'EM', '李教授', '020-44556677', 'em@lingnan.edu.cn', '培养经济管理专业人才', 0),
('D007', '外国语学院', 'FL', '张教授', '020-55667788', 'fl@lingnan.edu.cn', '培养外语专业人才', 0),
('D008', '文学院', 'LA', '陈教授', '020-66778899', 'la@lingnan.edu.cn', '培养文学专业人才', 0);

