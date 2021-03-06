package top.shaozuo.geektime.java01.week07.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import top.shaozuo.geektime.java01.week07.annotation.DynamicDataSource;

@Component
@Aspect
@Order(-1)
public class DynamicDataSourceAspect {
    @Before("execution(* top.shaozuo.geektime.java01.week07.service..*.*(..))")
    public void before(JoinPoint point) {
        try {
            //获取类上@DataSource注解
            DynamicDataSource annotationOfClass = point.getTarget().getClass()
                    .getAnnotation(DynamicDataSource.class);

            //获取方法上@DataSource注解
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);
            DynamicDataSource methodAnnotation = method.getAnnotation(DynamicDataSource.class);

            methodAnnotation = methodAnnotation == null ? annotationOfClass : methodAnnotation;
            String dsName = "master";
            if (methodAnnotation != null) {
                dsName = methodAnnotation.value();
            }
            DynamicDataSourceContext.setDataSource(dsName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @After("execution(* top.shaozuo.geektime.java01.week07.service..*.*(..))")
    public void after(JoinPoint point) {
        DynamicDataSourceContext.clearDataSource();
    }
}