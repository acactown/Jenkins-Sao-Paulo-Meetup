package com.vivareal.app.view.domain

/**
 * @author Andrés Amado
 */
class Notification {

    NotificationLevel level
    String title
    String message

    enum NotificationLevel {
        SUCCESS, INFO, ERROR, WARN

        String getCssClass() {
            String cssClass = "alert fade in "
            switch (this) {
                case SUCCESS:
                    cssClass += 'alert-success'
                    break
                case INFO:
                    cssClass += 'alert-info'
                    break
                case ERROR:
                    cssClass += 'alert-error'
                    break
                case WARN:
                    cssClass += 'alert-warn'
                    break
            }

            return cssClass
        }
    }

}
