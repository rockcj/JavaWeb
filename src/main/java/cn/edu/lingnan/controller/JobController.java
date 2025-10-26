package cn.edu.lingnan.controller;

import cn.edu.lingnan.service.JobService;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;

import java.util.Scanner;

public class JobController {
    JobService job = new JobServiceImpMysql();
    public void editJob()
    {
        System.out.println("所有item信息如下:");
        System.out.println(job.queryAllJob());
        Scanner sc = new Scanner(System.in);
        System.out.println("输入要修改的 sid和iid: ");
        System.out.print("sid=");
        String sid = sc.next();
        System.out.print("iid=");
        String iid = sc.next();
        System.out.println("要修改的item信息如下:");
        System.out.println(job.queryJobBysIdAndcId(sid,iid));
        System.out.print("新job名称: ");
        String newName = sc.next();
        System.out.print("新标志位scflag: ");
        int newFlag = sc.nextInt();
        job.editJob(sid,iid,newName,newFlag);
    }
}
