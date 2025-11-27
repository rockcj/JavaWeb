package cn.edu.lingnan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* cn.edu.lingnan.service.*.*(..)) && " +
              "(execution(* insert*(..)) || " +
              "execution(* delete*(..)) || " +
              "execution(* update*(..)) || " +
              "execution(* query*(..)) || " +
              "execution(* find*(..)) || " +
              "execution(* get*(..)) || " +
              "execution(* edit*(..)))")
    public void crudOperation() {}

    @Pointcut("execution(* cn.edu.lingnan.service.StudentService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.CourseService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.ScoreService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.TeacherService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.TimeTableService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.DepartmentService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.JobService.*(..)) || " +
              "execution(* cn.edu.lingnan.service.ItemService.*(..))")
    public void allServiceMethods() {}

    //前置通知：在方法执行前记录日志
    @Before("crudOperation() || allServiceMethods()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        System.out.println("=== 前置通知 [LOG] ===");
        System.out.println("执行类: " + className);
        System.out.println("执行方法: " + methodName);
        System.out.println("方法参数: " + getArgumentsInfo(args));
        System.out.println("开始执行...");
        System.out.println("========================");
    }

    //后置通知：在方法执行后记录日志（无论成功还是失败都会执行）
    @AfterReturning(pointcut = "crudOperation() || allServiceMethods()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println("=== 后置通知 [LOG] ===");
        System.out.println("执行类: " + className);
        System.out.println("执行方法: " + methodName);
        System.out.println("执行结果: " + getResultInfo(result));
        System.out.println("执行完成！");
        System.out.println("========================");
    }

    //辅助方法：获取参数信息
    private String getArgumentsInfo(Object[] args) {
        if (args == null || args.length == 0) {
            return "无参数";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("arg[").append(i).append("]: ").append(args[i] != null ? args[i].toString() : "null");
        }
        return sb.toString();
    }

    //辅助方法：获取结果信息
    private String getResultInfo(Object result) {
        if (result == null) {
            return "null";
        }

        if (result instanceof java.util.Collection) {
            return "集合类型，大小: " + ((java.util.Collection<?>) result).size();
        }

        if (result instanceof Integer || result instanceof Long) {
            return "数值类型: " + result;
        }

        return "对象类型: " + result.getClass().getSimpleName() + " - " + result.toString();
    }
}