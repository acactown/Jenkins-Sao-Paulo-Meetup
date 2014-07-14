package com.vivareal.app.view.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import org.springframework.web.servlet.mvc.WebContentInterceptor

import static com.vivareal.app.view.config.ApplicationContext.*

/**
 * @author Andrés Amado
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.vivareal.view.controller")
class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    ReloadableResourceBundleMessageSource messageSource() {
        new ReloadableResourceBundleMessageSource(
                fallbackToSystemLocale: false,
                defaultEncoding: ENCODING,
                basename: "classpath:messages/messages"
        )
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        new LocaleChangeInterceptor(paramName: "lang")
    }

    @Bean
    WebContentInterceptor webContentInterceptor() {
        new WebContentInterceptor(
                cacheSeconds: 0,
                useExpiresHeader: true,
                useCacheControlHeader: true,
                useCacheControlNoStore: true
        )
    }

    @Bean
    SessionLocaleResolver cookieLocaleResolver() {
        new SessionLocaleResolver(defaultLocale: new Locale("en"))
    }


    @Bean
    CommonsMultipartResolver multipartResolver() {
        new CommonsMultipartResolver(maxUploadSize: -1)
    }

    @Override
    void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(webContentInterceptor())
        registry.addInterceptor(localeChangeInterceptor())
    }

    @Override
    void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable()
    }

    @Override
    void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico")
    }

}
