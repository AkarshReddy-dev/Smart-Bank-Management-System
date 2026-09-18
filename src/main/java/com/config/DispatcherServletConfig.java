package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
				
		return new Class[] {Config.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		Class[] configClasses= {HomeConfig.class};
		return configClasses;
	}

	@Override
	protected String[] getServletMappings() {
		
		String[] mappings= {"/"};
		return mappings;
	}

}
