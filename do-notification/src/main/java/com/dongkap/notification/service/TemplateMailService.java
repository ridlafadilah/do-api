package com.dongkap.notification.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

@Component("templateMailService")
public class TemplateMailService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public String getTemplate(Map<String, Object> input, String fileTemplate, Locale locale) {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/");

        if(locale == null) {
        	locale = Locale.getDefault();
        }
        cfg.setIncompatibleImprovements(new Version(2, 3, 26));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.getDefault());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        StringWriter writer = new StringWriter();
        Template template;
		try {
			template = cfg.getTemplate("templates/mail/" + fileTemplate);
			template.process(input, writer);
			writer.close();
		} catch (TemplateNotFoundException e) {
			LOGGER.error("[Dongkap] Template Mail Error : ", e);
		} catch (MalformedTemplateNameException e) {
			LOGGER.error("[Dongkap] Template Mail Error : ", e);
		} catch (ParseException e) {
			LOGGER.error("[Dongkap] Template Mail Error : ", e);
		} catch (IOException e) {
			LOGGER.error("[Dongkap] Template Mail Error : ", e);
		} catch (TemplateException e) {
			LOGGER.error("[Dongkap] Template Mail Error : ", e);
		}
		return writer.toString();
	}

}
