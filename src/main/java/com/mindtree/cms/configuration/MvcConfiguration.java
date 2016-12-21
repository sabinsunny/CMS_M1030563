/**
 * 
 */
package com.mindtree.cms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


/**
 * @author M1030563
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mindtree.cms")
@Import(SecurityConfig.class)
@EnableAspectJAutoProxy
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
    private Environment environment;
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/customLogin").setViewName("customLogin");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		Long maxSize = Long.parseLong(environment.getRequiredProperty("maxUploadSize"));
		multipartResolver.setMaxUploadSize(maxSize);
		return multipartResolver;
	}
}
