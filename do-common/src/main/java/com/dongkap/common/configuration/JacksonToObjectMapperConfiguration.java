package com.dongkap.common.configuration;

import com.dongkap.common.utils.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Configuration
public class JacksonToObjectMapperConfiguration {
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objMapper;
		objMapper = new ObjectMapper();
		SimpleModule sm = new SimpleModule();
		sm.addSerializer(Date.class, new JsonDateSerializer());
		sm.addDeserializer(Date.class, new JsonDateDeserializer());
		sm.addSerializer(Calendar.class, new JsonDateTimeSerializer());
		sm.addDeserializer(Calendar.class, new JsonDateTimeDeserializer());
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objMapper.registerModule(sm);
		
		return objMapper;
	}
}

class JsonDateSerializer extends StdSerializer<Date>{
	
	private static final long serialVersionUID = 1L;

	public JsonDateSerializer() {
		this(null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JsonDateSerializer(Class t) {
		super(t);
	}
	
	private SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE);

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		try {
			gen.writeString(format.format(value));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}

class JsonDateDeserializer extends StdDeserializer<Date> {
	 
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE);
 
    public JsonDateDeserializer() {
        this(null);
    }
 
    public JsonDateDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException {
        String date = jsonparser.getText();

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

class JsonDateTimeSerializer extends StdSerializer<Calendar>{

	private static final long serialVersionUID = 1L;

	public JsonDateTimeSerializer() {
		this(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JsonDateTimeSerializer(Class t) {
		super(t);
	}

	private SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE_TIME);

	@Override
	public void serialize(Calendar value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		try {
			gen.writeString(format.format(value.getTime()));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}

class JsonDateTimeDeserializer extends StdDeserializer<Calendar> {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE_TIME);

	public JsonDateTimeDeserializer() {
		this(null);
	}

	public JsonDateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Calendar deserialize(JsonParser jsonparser, DeserializationContext context)
			throws IOException {
		String date = jsonparser.getText();

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(date));
			return calendar;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}