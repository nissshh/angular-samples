/**
 * 
 */
package com.wf.baas.pay.aspectj;

import java.util.Arrays;

import javax.ws.rs.core.HttpHeaders;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wf.baas.pay.rest.NotificationsConstant;
import com.wf.baas.pay.service.SecurityKeyValidationService;

/**
 * @author u180362
 *
 */
@Aspect
public class HttpHeadersPrintAdvice {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private SecurityKeyValidationService securityKeyValidation;

	@Around("execution(* com.wf.baas.pay.rest.controller.NotificationController.*Notification(..))")
	public void printHeaders(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("******");
		logger.debug("printHeaders() is running!");
		logger.debug("Method Name: " + joinPoint.getSignature().getName());
		Object[] arguments = joinPoint.getArgs();
		logger.debug("Metho Arguments : " + Arrays.toString(arguments));
		HttpHeaders headers = null;
		if (arguments[0] instanceof HttpHeaders) {
			headers = (HttpHeaders) arguments[0];
			if (validateKeys(headers)) {
				logger.info("Keys Validated.");
			} else {
				logger.error("Keys are not Validated.");
			}
		} else {
			logger.error("No Headers injected.");
		}

		logger.debug("Around before is running!");
		joinPoint.proceed(); // continue on the intercepted method
		// TODO Stop execution and throw exception in case headers are not
		// accepted.
		logger.debug("Around after is running!");

		logger.debug("******");
	}

	private boolean validateKeys(HttpHeaders httpHeaders) {
		String securityKey = httpHeaders
				.getHeaderString(NotificationsConstant.HTTP_HEADER_SECURITY_KEY);
		String apiKey = httpHeaders
				.getHeaderString(NotificationsConstant.HTTP_HEADER_API_KEY);
		logger.debug("Recieved Security Key as {} and API Key as {}",
				securityKey, apiKey);
		return true;
	}
}
