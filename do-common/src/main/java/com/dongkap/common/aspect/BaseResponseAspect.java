package com.dongkap.common.aspect;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.common.utils.SuccessCode;

@Aspect
@Component
public class BaseResponseAspect {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	@Around("execution(org.springframework.http.ResponseEntity<com.dongkap.common.http.ApiBaseResponse> *(..)) && "
			+ "@annotation(com.dongkap.common.aspect.ResponseSuccess)")
    public ResponseEntity<ApiBaseResponse> doBaseResponseSuccess(ProceedingJoinPoint pjp) throws Throwable {
		ResponseEntity<ApiBaseResponse> result = extracted(pjp);
		ApiBaseResponse response = new ApiBaseResponse(); 
		if(result.getBody() != null)
			response = result.getBody();
		Locale locale = Locale.getDefault();
		try {
			final Signature signature = pjp.getSignature();
		    if(signature instanceof MethodSignature){
		        final MethodSignature ms = (MethodSignature) signature;
		        final Method method = ms.getMethod();
				RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
				SuccessCode status = method.getAnnotation(ResponseSuccess.class).value();
				HttpServletRequest request = (HttpServletRequest) attrs.resolveReference(RequestAttributes.REFERENCE_REQUEST);
				if(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE) != null)
					locale = Locale.forLanguageTag(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE));
				response.setRespStatusCode(status.name());
				response.getRespStatusMessage().put(response.getRespStatusCode(), messageSource.getMessage(status.name(), null, locale));
				result = new ResponseEntity<ApiBaseResponse>(response, status.getStatus());
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			response.setRespStatusCode(ErrorCode.ERR_SYS0500.name());
			response.getRespStatusMessage().put(ErrorCode.ERR_SYS0500.name(), messageSource.getMessage(ErrorCode.ERR_SYS0500.name(), null, locale));
			result = new ResponseEntity<ApiBaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return result;
    }

	@SuppressWarnings("unchecked")
	private ResponseEntity<ApiBaseResponse> extracted(ProceedingJoinPoint pjp) throws Throwable {
		return (ResponseEntity<ApiBaseResponse>)pjp.proceed();
	}

}
