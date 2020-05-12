package com.dongkap.feign.dto.notification;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailNotificationDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5053426923486522719L;
	private String from;
    private String to;
    private String subject;
    private String content;
    private String fileTemplate;
    private Map<String, Object> bodyTemplate;

}
