package com.dongkap.feign.dto.notification;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationActionsDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7497716741642417911L;
	private String action;
    private String icon;
    private String title;

}
