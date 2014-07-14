package com.vivareal.app.view.controller

import com.vivareal.app.view.domain.ViewException
import com.vivareal.app.view.domain.Notification
import com.vivareal.app.view.domain.Notification.NotificationLevel
import groovy.util.logging.Slf4j
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ModelAttribute

import static org.apache.commons.lang.WordUtils.*

/**
 * @author Andrés Amado
 */
@Slf4j
class ViewController {

    private Notification notification

    private static final String ACTIVE = "active"
    private static final String H1 = "h1"
    private static final String VIEW_ERROR = "error"

    @ExceptionHandler(ViewException)
    String handleException(final ModelMap model, final Exception exception) {
        addNotification(NotificationLevel.ERROR, "ERROR", exception.getMessage())
        updatedView(model, VIEW_ERROR)
    }

    @ModelAttribute("notification")
    Notification getNotification() {
        notification
    }

    protected void addNotification(final NotificationLevel level, final String title, final String message) {
        notification = new Notification(
                level: level,
                title: title,
                message: message
        )
    }

    protected String updatedView(final ModelMap model, final String view) {
        updatedView(model, view, null)
    }

    protected String updatedView(final ModelMap model, final String view, final Map<String, Object> params) {
        model.addAttribute(ACTIVE, view)
        model.addAttribute(H1, capitalizeFully(view))
        if (params) {
            model.addAllObjects(params)
        }

        view
    }

}
