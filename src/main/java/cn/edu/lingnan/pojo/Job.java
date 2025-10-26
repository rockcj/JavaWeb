package cn.edu.lingnan.pojo;

public class Job{
    private String sid;
    private String iid;
    private String job;
    private int scflag;

    public Job() {
    }
    public Job(String sid, String iid, String job, int scflag) {
        this.sid = sid;
        this.iid = iid;
        this.job = job;
        this.scflag = scflag;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getScflag() {
        return scflag;
    }

    public void setScflag(int scflag) {
        this.scflag = scflag;
    }

    @Override
    public String toString() {
        return "Job{" +
                "sid='" + sid + '\'' +
                ", iid='" + iid + '\'' +
                ", job=" + job +
                ", scflag=" + scflag +
                '}';
    }
}
