package com.dongkap.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class JsonUtils {

	ObjectMapper objectMapper;
	ObjectWriter objectWriter;

	@Autowired
	public void setObjectMapper( ObjectMapper objectMapper ) {
		this.objectMapper = objectMapper;
	}

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * Convert JSON formatted String Into Java POJO obj
	 * 
	 * @param json
	 *            Source string
	 * @param cls
	 *            Class type of returned obj
	 * @return Object Convert result from JSON formatted String to POJO obj
	 * @throws IOException
	 * @throws JsonParseException
	 *
	 */
	public <T> Object fromJson(String json, Class<T> cls) throws IOException, JsonParseException {
		return objectMapper.readValue(json, cls);
	}

	public <T> Object fromMap(Map<String, Object> map, Class<T> cls) throws JsonParseException, IOException {
		return this.objectMapper.convertValue(map, cls);
	}

	@SuppressWarnings("unchecked")
	public <T> Object fromListMap(List<Map<String, Object>> list,
			@SuppressWarnings("rawtypes") TypeReference typeReference) throws JsonParseException, IOException {
		return this.objectMapper.convertValue(list, typeReference);
	}

	public <T> List<T> jsonToListOfObj(Class<?> typeKey, String json) throws Exception {
		List<T> listo = null;
		try {
			listo = objectMapper.readValue(json,
					TypeFactory.defaultInstance().constructCollectionType(List.class, typeKey));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return listo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> jsonToMapOfObject(String json) throws Exception {
		Map<String, Object> mapto = new HashMap();
		try {
			mapto = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return mapto;
	}

	/**
	 * Convert an POJO obj into JSON formatted String
	 * 
	 * @param obj
	 * @return {@link String} of json
	 */
	public String objToJson(Object obj) {
		String json = "";
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
		}
		return json;
	}

	@SuppressWarnings("rawtypes")
	public <T> List<T> castCollection(List srcList, Class<T> clas) {
		List<T> list = new ArrayList<T>();
		for (Object obj : srcList) {
			if (obj != null && clas.isAssignableFrom(obj.getClass()))
				list.add(clas.cast(obj));
		}
		return list;
	}
}
