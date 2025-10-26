package cn.edu.lingnan.dao;

import cn.edu.lingnan.service.JobService;
import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Item;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static JobService jobService = new JobServiceImpMysql();
    private static ItemService itemService = new ItemServiceImpMysql();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            System.out.print("请输入选项: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    testJobService(scanner);
                    break;
                case 2:
                    testItemService(scanner);
                    break;
                case 0:
                    System.out.println("退出程序");
                    break;
                default:
                    System.out.println("无效选项，请重新输入！");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("请选择要测试的服务:");
        System.out.println("1. JobService");
        System.out.println("2. ItemService");
        System.out.println("0. 退出");
    }

    private static void testJobService(Scanner scanner) {
        System.out.println("测试 JobService");

        // 测试 queryAllJob
        List<Job> allJobs = jobService.queryAllJob();
        System.out.println("所有职位记录:");
        for (Job job : allJobs) {
            System.out.println(job);
        }

        // 按学生ID查询职位
        System.out.print("请输入学生ID进行精确查询: ");
        String sid = scanner.next();
        List<Job> jobsBySid = jobService.queryJobBysId(sid);
        System.out.println("按学生ID查询的结果:");
        for (Job job : jobsBySid) {
            System.out.println(job);
        }

        // 按课程ID查询职位
        System.out.print("请输入课程ID进行精确查询: ");
        String iid = scanner.next();
        List<Job> jobsByIid = jobService.queryjobBycId(iid);
        System.out.println("按课程ID查询的结果:");
        for (Job job : jobsByIid) {
            System.out.println(job);
        }

        // 按学生姓名模糊查询职位
        System.out.print("请输入学生姓名进行模糊查询: ");
        String sname = scanner.next();
        List<Job> jobsByName = jobService.queryJobByName(sname);
        System.out.println("按学生姓名模糊查询的结果:");
        for (Job job : jobsByName) {
            System.out.println(job);
        }

        // 新增职位
        System.out.print("请输入新增的学生ID: ");
        sid = scanner.next();
        System.out.print("请输入新增的课程ID: ");
        iid = scanner.next();
        System.out.print("请输入新增的职位名称: ");
        String jobName = scanner.next();
        System.out.print("请输入新增的标志: ");
        int scflag = scanner.nextInt();
        Job newJob = new Job(sid, iid, jobName, scflag);
        int insertResult = jobService.insertJob(newJob);
        System.out.println("新增职位结果: " + insertResult);

        // 修改职位
        System.out.print("请输入要修改的学生ID: ");
        sid = scanner.next();
        System.out.print("请输入要修改的课程ID: ");
        iid = scanner.next();
        System.out.print("请输入新的职位名称: ");
        jobName = scanner.next();
        System.out.print("请输入新的标志: ");
        scflag = scanner.nextInt();
        int editResult = jobService.editJob(sid, iid, jobName, scflag);
        System.out.println("修改职位结果: " + editResult);

        // 按学生ID和课程ID查询职位
        System.out.print("请输入学生ID进行精确查询: ");
        sid = scanner.next();
        System.out.print("请输入课程ID进行精确查询: ");
        iid = scanner.next();
        Job jobByIds = jobService.queryJobBysIdAndcId(sid, iid);
        System.out.println("按学生ID和课程ID查询的结果:");
        System.out.println(jobByIds);
    }

    private static void testItemService(Scanner scanner) {
        System.out.println("测试 ItemService");

        // 测试 queryAllItem
        List<Item> allItems = itemService.queryAllItem();
        System.out.println("所有项目记录:");
        for (Item item : allItems) {
            System.out.println(item);
        }

        // 按项目ID查询项目
        System.out.print("请输入项目ID进行精确查询: ");
        String iid = scanner.next();
        Item itemById = itemService.queryItemById(iid);
        System.out.println("按项目ID查询的结果:");
        System.out.println(itemById);

        // 按项目名称模糊查询项目
        System.out.print("请输入项目名称进行模糊查询: ");
        String iname = scanner.next();
        List<Item> itemsByName = itemService.queryItemByName(iname);
        System.out.println("按项目名称模糊查询的结果:");
        for (Item item : itemsByName) {
            System.out.println(item);
        }

        // 新增项目
        System.out.print("请输入新增的项目ID: ");
        iid = scanner.next();
        System.out.print("请输入新增的项目名称: ");
        iname = scanner.next();
        System.out.print("请输入新增的标志: ");
        int iflag = scanner.nextInt();
        Item newItem = new Item(iid, iname, iflag);
        itemService.insertItem(newItem);
        System.out.println("新增项目成功");

        // 删除项目
        System.out.print("请输入要删除的项目ID: ");
        iid = scanner.next();
        itemService.deleteItemById(iid);
        System.out.println("删除项目成功");

        // 修改项目
        System.out.print("请输入要修改的项目ID: ");
        iid = scanner.next();
        System.out.print("请输入新的项目名称: ");
        iname = scanner.next();
        System.out.print("请输入新的标志: ");
        iflag = scanner.nextInt();
        int editResult = itemService.editItem(iid, iname, iflag);
        System.out.println("修改项目结果: " + editResult);
    }
}
