package com.dongkap.feign.dto.notification;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastNotificationDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5053426923486522719L;
	private String from;
    private String to;
    private String subject;
    private String tags;
    private String content;

}
