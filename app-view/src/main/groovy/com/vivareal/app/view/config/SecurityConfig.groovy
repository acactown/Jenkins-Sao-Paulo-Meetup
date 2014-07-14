package com.vivareal.app.view.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.FilterRegistration
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

/**
 * @author Andrés Amado
 */
@Configuration
@ImportResource("classpath:META-INF/spring/security-context.xml")
class SecurityConfig implements WebApplicationInitializer {

    @Bean
    DelegatingFilterProxy springSecurityFilterChain() {
        new DelegatingFilterProxy()
    }

    @Override
    void onStartup(final ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext(displayName: "Jenkins Meetup App - VivaReal")
        rootContext.register(ApplicationContext)

        servletContext.addListener(new ContextLoaderListener(rootContext))

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")

        FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*")
    }

}
