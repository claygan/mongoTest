package com.quest.freemarker;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * ClassName: FreemarkerExceptionHandler Description: TODO(用一句话描述这个�?)
 * 
 * @author jupiter@zhongzhihui.com
 * @date: 2015-6-11 下午8:32:12
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {


	@Override
	public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
		//
		try {
			out.write("Freemarker Error : [" + te.getMessage() + "]");
		} catch (Exception e) {
			//
			throw new TemplateException("Failed to print error message. Cause: " + e, env);
		}
	}

}
