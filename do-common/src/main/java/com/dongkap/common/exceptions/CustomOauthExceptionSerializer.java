package com.dongkap.common.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2185579268211628868L;

	public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(value.getOAuth2ErrorCode(), value.getMessage());
		jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("respStatusCode", value.getOAuth2ErrorCode());
        jsonGenerator.writeObjectField("respStatusMessage", respStatusMessage);
        jsonGenerator.writeEndObject();
    }
}
