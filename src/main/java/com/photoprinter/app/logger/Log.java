package com.photoprinter.app.logger;


import io.reactivex.Observable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class Log {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public static final String SEVICE_EXCEPTION_MSG = "ServiceException in ";
	public static final String UNKNOWN_EXCEPTION_MSG = "Unknown - Exception in ";
	public static final String UNKNOWN_ERROR_MSG = "Unknown - Error in ";

	@Around("execution(* com.community.notification.service.*.*(..)) ")
	public Object serviceLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
		
		Object serviceLog = null;
		LOGGER.info("*** Entering Service Implementation ***\n");
		long startTime = System.currentTimeMillis();
		try {
			serviceLog = joinPoint.proceed();
		} catch (Throwable e) {
			LOGGER.error("\n Exception was thrown executing " + joinPoint.getSignature().getName() + " method "
					+ e);
			throw e;
		}
		LOGGER.info("Class : {}, Method : {}, Time Elapsed : {} ms\n"
				+ "\n*** Exiting Service Implementation ***\n", className, methodName,(System.currentTimeMillis() - startTime));
		return serviceLog;
	}
	
	@AfterReturning(value = "execution(* com.community.notification.service.*.*(..)))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Observable<Object> observable = Observable.just(result);
        System.out.println(result.toString());
        
		 LOGGER.info("Class : {}, Method : {}, Result : {}", className, methodName, observable.subscribe(r -> r.toString()));
	 }
	
	@Around("execution(* com.community.notification.controller.*.*(..))")
	public Object controllerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        
        Object[] signatureArgs = joinPoint.getArgs();
        
		Object controllerLog = null;
		LOGGER.info("*** Entering Controller ***");
		long startTime = System.currentTimeMillis();
		try {
			controllerLog = joinPoint.proceed();
		} catch (Throwable e) {
			LOGGER.error("\n Exception was thrown executing " + joinPoint.getSignature().getName() + " method "
					+ e);
			throw e;
		}
		LOGGER.info("Class : {}, Method : {}, Parameters : {}, Time Elapsed : {} ms\n"
				+ "\n*** Exiting Controller ***\n", className, methodName, getParameters(signatureArgs), (System.currentTimeMillis() - startTime));
		return controllerLog;
	}
	
	private String getParameters(Object[] signatureArgs) {
		if(!(signatureArgs != null && signatureArgs.length > 0)) return null;
		return  Arrays.stream(signatureArgs).filter(Objects::nonNull).map(args -> args.toString()).collect(Collectors.joining());
	}
}
