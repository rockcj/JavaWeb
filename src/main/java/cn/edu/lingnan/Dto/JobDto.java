package cn.edu.lingnan.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private String sid;
    private String iid;
    private String sname;
    private String iname;
    private String job;
}