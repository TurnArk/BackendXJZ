package com.work.logistics.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class SensitiveOperationAspect {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveOperationAspect.class);

    @Pointcut("@annotation(com.work.logistics.aop.SensitiveOperation)")
    public void sensitiveOperation(){}

    @Around("sensitiveOperation()")
    public Object recordSensitiveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
        Method method;

        // 获取注解
        SensitiveOperation annotation = null;
//        String operationName = annotation != null ? annotation.value() : method.getName();

        // 采用接口+实现的结构
        try{
            method = joinPoint
                    .getTarget()
                    .getClass()
                    .getMethod(signature.getName(), signature.getParameterTypes());
            annotation = method.getAnnotation(SensitiveOperation.class);
        }catch (NoSuchMethodException e){
            logger.warn("从实现类获取方法失败，退回接口方法。", e);
            method = signature.getMethod();
        }

        String operationName = annotation != null ? annotation.value() : method.getName();

        // 记录日志信息
//        System.out.println("[敏感操作] 记录敏感操作日志：" + operationName);
//        System.out.println("[敏感操作] 参数：" + Arrays.toString(joinPoint.getArgs()));
        logger.info("[敏感操作] 开始 - 操作名称: {}, 参数: {}",operationName,Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            logger.info("[敏感操作] 结束 - 操作名称: {}, 结果: {}",operationName,result);
            return result;
        }catch (Exception e){
            logger.error("[敏感操作] 结束 - 操作名称: {}, 异常: {}",operationName,e.getMessage(), e);
            throw e;
        }
//        System.out.println("[敏感操作] 结果：" + result);
    }
}
