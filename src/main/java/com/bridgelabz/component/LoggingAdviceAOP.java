package com.bridgelabz.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is AOP(Aspect Oriented Programming) class which holds all the logging related application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@Component
@Aspect
@Slf4j
public class LoggingAdviceAOP {
    /**
     * Purpose : This method is created to implement the logging mechanism throughout the application
     */
    @Pointcut(value = "execution(* com.bridgelabz.*.*.*(..) )")
    public void myPointCut() {
    }

    /**
     * Purpose : This method is created to centralize the logging statement
     * to track the application flow throughout the program
     *
     * @param point : It manages the reflection of the program for getting the method details and input parameters
     * @return : The JSON format of logger statement before and after the advice
     * @throws Throwable : when there is any exception
     */
    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();    // to get the outputs in JSON format
        String methodName = point.getSignature().getName();    // to get the method name
        String className = point.getTarget().getClass().toString(); // to get the class name
        Object[] array = point.getArgs();                            // to get the inputs
        log.info("method invoked" + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object object = point.proceed();
        // after executing  the method to get back the response of that particular class or method
        log.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }
}
