package com.dongkap.feign.dto.google;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "success",
    "challenge_ts",
    "hostname",
    "error-codes"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GoogleResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 440653391245887729L;

	 
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("challenge_ts")
    private String challengeTs;
    
    @JsonProperty("hostname")
    private String hostname;
    
    @JsonProperty("error-codes")
    private ErrorCode[] errorCodes;
 
    @JsonIgnore
    public boolean hasClientError() {
        ErrorCode[] errors = getErrorCodes();
        if(errors == null) {
            return false;
        }
        for(ErrorCode error : errors) {
            switch(error) {
                case InvalidResponse:
                case MissingResponse:
                    return true;
			default:
				break;
            }
        }
        return false;
    }
 
    static enum ErrorCode {
        MissingSecret,     InvalidSecret,
        MissingResponse,   InvalidResponse;
 
        private static Map<String, ErrorCode> errorsMap = new HashMap<String, ErrorCode>(4);
 
        static {
            errorsMap.put("missing-input-secret",   MissingSecret);
            errorsMap.put("invalid-input-secret",   InvalidSecret);
            errorsMap.put("missing-input-response", MissingResponse);
            errorsMap.put("invalid-input-response", InvalidResponse);
        }
 
        @JsonCreator
        public static ErrorCode forValue(String value) {
            return errorsMap.get(value.toLowerCase());
        }
    }

}
