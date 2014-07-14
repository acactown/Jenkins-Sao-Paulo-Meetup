package com.vivareal.app.view.domain

import groovy.util.logging.Slf4j

/**
 * @author Andrés Amado
 */
@Slf4j
class ViewException extends Exception {

    ViewException(final String message, final Throwable ex) {
        super(message, ex)
        log.error(message, ex)
    }
}
