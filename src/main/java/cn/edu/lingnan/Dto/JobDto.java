package cn.edu.lingnan.Dto;

public class JobDto {
    private String sid;
    private String iid;
    private String sname;
    private String iname;
    private String job;

    public JobDto() {
    }

    public JobDto(String sid, String iid, String sname, String iname, String job) {
        this.sid = sid;
        this.iid = iid;
        this.sname = sname;
        this.iname = iname;
        this.job = job;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "JobDto{" +
                "sid='" + sid + '\'' +
                ", iid='" + iid + '\'' +
                ", sname='" + sname + '\'' +
                ", iname='" + iname + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
