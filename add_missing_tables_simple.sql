USE school;

CREATE TABLE IF NOT EXISTS teacher (
    teacher_id VARCHAR(20) NOT NULL,
    teacher_name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NULL DEFAULT NULL,
    age INT NULL DEFAULT NULL,
    phone VARCHAR(20) NULL DEFAULT NULL,
    email VARCHAR(100) NULL DEFAULT NULL,
    dept_id VARCHAR(20) NULL DEFAULT NULL,
    title VARCHAR(20) NULL DEFAULT NULL,
    tflag INT DEFAULT 0,
    PRIMARY KEY (teacher_id),
    INDEX dept_id (dept_id),
    CONSTRAINT teacher_ibfk_1 FOREIGN KEY (dept_id) REFERENCES department (dept_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS score (
    score_id VARCHAR(20) NOT NULL,
    student_id VARCHAR(20) NOT NULL,
    course_id VARCHAR(20) NOT NULL,
    score DECIMAL(5,2) NULL DEFAULT NULL,
    semester VARCHAR(20) NULL DEFAULT NULL,
    exam_type VARCHAR(20) NULL DEFAULT NULL,
    flag INT DEFAULT 0,
    PRIMARY KEY (score_id),
    INDEX student_id (student_id),
    INDEX course_id (course_id),
    CONSTRAINT score_ibfk_1 FOREIGN KEY (student_id) REFERENCES student (sid) ON DELETE RESTRICT,
    CONSTRAINT score_ibfk_2 FOREIGN KEY (course_id) REFERENCES course (course_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS timetable (
    timetable_id VARCHAR(20) NOT NULL,
    course_id VARCHAR(20) NOT NULL,
    teacher_id VARCHAR(20) NOT NULL,
    classroom_id VARCHAR(20) NULL DEFAULT NULL,
    day_of_week VARCHAR(10) NULL DEFAULT NULL,
    time_slot VARCHAR(20) NULL DEFAULT NULL,
    semester VARCHAR(20) NULL DEFAULT NULL,
    tflag INT DEFAULT 0,
    PRIMARY KEY (timetable_id),
    INDEX course_id (course_id),
    INDEX teacher_id (teacher_id),
    CONSTRAINT timetable_ibfk_1 FOREIGN KEY (course_id) REFERENCES course (course_id) ON DELETE RESTRICT,
    CONSTRAINT timetable_ibfk_2 FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

