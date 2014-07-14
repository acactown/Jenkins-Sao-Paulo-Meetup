package com.vivareal.app.service

import groovy.json.JsonSlurper
import org.springframework.stereotype.Service
import org.springframework.util.Assert

/**
 * @author Andrés Amado
 */
@Service
class JsonClient extends Assert implements RestClient {

    @Override
    Object get(final String url) {
        notNull(url, "URL can not be null!")
        new JsonSlurper().parseText(url.toURL().text)
    }

}
