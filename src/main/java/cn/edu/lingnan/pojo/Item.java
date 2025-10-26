package cn.edu.lingnan.pojo;

public class Item {
    private String iid;
    private String iname;
    private int iflag;

    public Item() {
    }

    public Item(String iid, String iname, int iflag) {
        this.iid = iid;
        this.iname = iname;
        this.iflag = iflag;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public int getIflag() {
        return iflag;
    }

    public void setIflag(int iflag) {
        this.iflag = iflag;
    }

    @Override
    public String toString() {
        return "ItemDao{" +
                "iid='" + iid + '\'' +
                ", iname='" + iname + '\'' +
                ", iflag=" + iflag +
                '}';
    }
}
