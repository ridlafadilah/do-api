package com.dongkap.common.http;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.security.SignatureEncrypt;
import com.dongkap.common.utils.DateUtil;
import com.dongkap.common.utils.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 * @see <a href="https://angular-exercise-signature.stackblitz.io/">Generate Signature Header</a>
 */

// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class SignatureInterceptor implements Filter {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${do.signature.private-key}")
	private String privateKey;
	
	@Value("${do.signature.public-key}")
	private String publicKey;

	@Value("${do.signature.param-key}")
	private String paramKey;

	@Value("${do.signature.param-timestamp}")
	private String paramTimestamp;

	@Value("${do.signature.param-signature}")
	private String paramSignature;

	private String message;

	private String datenow;

	private ObjectMapper objectMapper;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	public void setObjectMapper( ObjectMapper objectMapper ) {
		this.objectMapper = objectMapper;
	}

    public SignatureInterceptor() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String hashMessage = "";
        
        if (!"OPTIONS".equalsIgnoreCase(request.getMethod()) &&
    		StringUtils.containsIgnoreCase(request.getHeader("Authorization"), "bearer")) {
        	try {
        		if(request.getHeader(this.paramKey) == null
        				&& request.getHeader(this.paramTimestamp) == null
        				&& request.getHeader(this.paramSignature) == null)
    				throw new SystemErrorException(ErrorCode.ERR_UNAUTHORIZED);
            	if(!request.getHeader(this.paramKey).equals(publicKey))
    				throw new SystemErrorException(ErrorCode.ERR_XDOKEY);
            	try {
            		datenow = DateUtil.formatDate(new Date(new Long(request.getHeader(this.paramTimestamp)) * 1000), DateUtil.DEFAULT_FORMAT_DATE);
            		if(!datenow.equals(DateUtil.DATE.format(new Date())))
        				throw new SystemErrorException(ErrorCode.ERR_XDOTIMESTAMP);
    			} catch (Exception e) {
    				throw new SystemErrorException(ErrorCode.ERR_XDOTIMESTAMP);
    			}
        		message = 	request.getHeader(this.paramKey) + ":" + 
							request.getHeader(this.paramTimestamp) + ":" +
							datenow  + ":" +
							request.getRequestURI()  + ":" +
							request.getHeader("Authorization").replaceAll("(?i)bearer ", "");
        		hashMessage = SignatureEncrypt.getInstance().hash(this.privateKey, message);
        		if(!hashMessage.equals(request.getHeader(this.paramSignature)))
    				throw new SystemErrorException(ErrorCode.ERR_XDOSIGNATURE);
        		chain.doFilter(req, res);
			} catch (SystemErrorException e) {
				LOGGER.error("Signature Header : {} , Signature Server : {}", request.getHeader(this.paramSignature), hashMessage);
				response.getWriter().write(unauthorized(e.getErrorCode(), request));
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			} catch (Exception e) {
				LOGGER.error("Signature Header : {} , Exception : {}", request.getHeader(this.paramSignature), e);
				response.getWriter().write(unauthorized(ErrorCode.ERR_UNAUTHORIZED, request));
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			}
        }else
        	chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
    
    private String unauthorized(ErrorCode err, HttpServletRequest request) throws JsonProcessingException {
		Locale locale = Locale.getDefault();
    	if(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE) != null)
			locale = Locale.forLanguageTag(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE));		
		ApiBaseResponse baseResponse = new ApiBaseResponse();
		baseResponse.setRespStatusCode(err.name());
		baseResponse.getRespStatusMessage().put(err.name(), messageSource.getMessage(err.name(), null, locale));
		return objectMapper.writeValueAsString(baseResponse);
    }

}
