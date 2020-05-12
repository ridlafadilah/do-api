package com.dongkap.feign.dto.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MultipartRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8052510184149880609L;
	private String path;
	private List<MultipartFile> files = new ArrayList<MultipartFile>();

}
