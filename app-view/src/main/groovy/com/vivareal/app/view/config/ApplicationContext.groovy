package com.vivareal.app.view.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @author Andrés Amado
 */
@Configuration
@Import([MvcConfig, ThymeleafConfig, SecurityConfig])
@ImportResource([
    "classpath:META-INF/spring/app-service.xml",
    "classpath:META-INF/spring/app-view.xml"
])
public class ApplicationContext {

    public static final Charset CHARSET = StandardCharsets.UTF_8
    public static final String ENCODING = CHARSET.name()
    public static final String DATE_FORMAT = "yyyy-mm-dd"

}
