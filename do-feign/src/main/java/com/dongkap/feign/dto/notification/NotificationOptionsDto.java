package com.dongkap.feign.dto.notification;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationOptionsDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5053426923486522719L;

    @JsonInclude(Include.NON_NULL)
	private List<NotificationActionsDto> actions;
    private String title;
    private String body;
    private String icon;

    @JsonInclude(Include.NON_NULL)
	private String badge;

    @JsonInclude(Include.NON_NULL)
    private String image;

    @JsonInclude(Include.NON_NULL)
    private String lang;

    @JsonInclude(Include.NON_NULL)
    private String tag;
    private Boolean renotify = false;
    private Boolean requireInteraction = false;
    private Boolean silent = false;
    private Long timestamp = new Date().getTime();
    private String dir = "auto";
    private Integer[] vibrate = new Integer[] {100, 50, 100};
    
    @JsonInclude(Include.NON_NULL)
    private Object data;

}
