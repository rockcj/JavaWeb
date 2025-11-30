package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.JobService;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JobController - 工作/任务管理控制器
 * 迁移自Job相关的Servlet
 */
@Controller
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private JobService jobService;

    /**
     * 查询所有工作
     * 对应原：JobQueryAllServlet
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session) {
        List<Job> list = jobService.queryAllJob();
        session.setAttribute("allJob", list);
        return "redirect:/admin/allJob.jsp";
    }

    /**
     * 插入工作（批量）
     * 对应原：JobInsertServlet
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertJob(@RequestBody String jsonData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Job> jobs = mapper.readValue(jsonData,
                    mapper.getTypeFactory().constructCollectionType(List.class, Job.class));
            for (Job job : jobs) {
                jobService.insertJob(job);
            }
            return "{\"status\":\"success\"}";
        } catch (Exception e) {
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }

    /**
     * 更新工作信息
     * 对应原：JobUpdateServlet
     */
    @RequestMapping("/update")
    public String updateJob(@RequestParam("sid") String sid,
                           @RequestParam("iid") String iid,
                           @RequestParam("job") String job,
                           @RequestParam("scflag") int scflag) {
        jobService.editJob(sid, iid, job, scflag);
        return "redirect:/job/queryAll";
    }

    /**
     * 删除工作（支持单个和批量）
     * 对应原：JobDeleteServlet
     */
    @RequestMapping("/delete")
    public String deleteJob(@RequestParam("sid") String sid,
                          @RequestParam("iid") String iid,
                          @RequestParam(value = "flag", required = false) String flag) {
        if (flag != null) {
            // 批量删除
            String[] sids = sid.split(",");
            String[] iids = iid.split(",");
            for (int i = 0; i < sids.length; i++) {
                jobService.deleteJob(sids[i], iids[i]);
            }
        } else {
            // 单个删除
            jobService.deleteJob(sid, iid);
        }
        return "redirect:/job/queryAll";
    }

    /**
     * 编辑工作（保留原有功能）
     */
    public void editJob() {
        // 保留原有控制台输入功能，用于测试
    }
}
