package com.vivareal.app.view.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring3.SpringTemplateEngine
import org.thymeleaf.spring3.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ServletContextTemplateResolver

import static com.vivareal.app.view.config.ApplicationContext.*

/**
 * @author Andrés Amado
 */
@Configuration
class ThymeleafConfig {

    @Bean
    ServletContextTemplateResolver templateResolver() {
        new ServletContextTemplateResolver(
                prefix: "/WEB-INF/templates/",
                suffix: ".html",
                templateMode: "HTML5",
                cacheable: true,
                order: 1
        )
    }

    @Bean
    SpringTemplateEngine templateEngine() {
        new SpringTemplateEngine(templateResolver: templateResolver())
    }

    @Bean
    ThymeleafViewResolver thymeleafViewResolver() {
        new ThymeleafViewResolver(templateEngine: templateEngine(), characterEncoding: ENCODING)
    }

}
