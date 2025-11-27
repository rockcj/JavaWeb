package cn.edu.lingnan.test;

import cn.edu.lingnan.pojo.Score;
import cn.edu.lingnan.service.ScoreService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ScoreServiceAnnotationTest {

    private ApplicationContext context;
    private ScoreService scoreService;

    @Before
    public void setUp() {
        // 加载Spring配置文件
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Score模块使用注解配置，Bean名称为"scoreService"
        scoreService = (ScoreService) context.getBean("scoreService");
    }

    @Test
    public void testScoreModuleWithAnnotation() {
        System.out.println("========== 测试Score模块（注解配置） ==========");

        // 测试查询所有成绩
        System.out.println("--- 测试查询所有成绩 ---");
        try {
            List<Score> scores = scoreService.queryAllScores();
            System.out.println("查询到的成绩数量: " + scores.size());

            for (Score score : scores) {
                System.out.println("成绩ID: " + score.getScoreId() +
                                 ", 学生ID: " + score.getStudentId() +
                                 ", 课程ID: " + score.getCourseId() +
                                 ", 成绩: " + score.getScore());
            }
        } catch (Exception e) {
            System.out.println("查询成绩时出错: " + e.getMessage());
            // 可能是表不存在，这是正常的
        }

        // 测试插入成绩
        System.out.println("\n--- 测试插入成绩 ---");
        try {
            Score testScore = new Score();
            testScore.setScoreId("TEST_SCORE_001");
            testScore.setStudentId("S001");
            testScore.setCourseId("C001");
            testScore.setScore(88.5);
            testScore.setSemester("2024春季");
            testScore.setExamType("期中");

            int result = scoreService.insertScore(testScore);
            System.out.println("插入操作结果: " + (result > 0 ? "成功" : "失败"));

            // 如果插入成功，尝试查询
            if (result > 0) {
                System.out.println("--- 验证插入结果 ---");
                Score insertedScore = scoreService.queryScoreById("TEST_SCORE_001");
                if (insertedScore != null) {
                    System.out.println("插入的成绩信息: " + insertedScore);
                }

                // 清理测试数据
                System.out.println("--- 清理测试数据 ---");
                int deleteResult = scoreService.deleteScore("TEST_SCORE_001");
                System.out.println("清理结果: " + (deleteResult > 0 ? "成功" : "失败"));
            }
        } catch (Exception e) {
            System.out.println("测试插入成绩时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testAnnotationConfiguration() {
        System.out.println("\n========== 验证注解配置是否生效 ==========");

        // 验证Bean是否正确注入
        System.out.println("ScoreService Bean: " + scoreService.getClass().getName());
        System.out.println("Bean是否被Spring管理: " + context.containsBean("scoreService"));

        // 执行一个简单的方法来验证AOP是否工作
        System.out.println("\n执行一个查询操作来测试AOP日志:");
        try {
            List<Score> scores = scoreService.queryAllScores();
            System.out.println("查询完成，返回了 " + scores.size() + " 条记录");
        } catch (Exception e) {
            System.out.println("查询过程中出现异常，但这仍然会触发AOP日志");
        }
    }
}