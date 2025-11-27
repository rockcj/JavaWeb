package cn.edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private String deptId;
    private String deptName;
    private String deptCode;
    private String deptHead;
    private String deptPhone;
    private String deptEmail;
    private String deptDesc;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer dflag;
}