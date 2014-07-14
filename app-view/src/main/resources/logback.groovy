import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.*

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %gray(%logger{36}) - %yellow(%msg%n)"
    }
}

appender("FILE", FileAppender) {
    append = false
    file = "app-view.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}

logger("org.springframework", INFO)
logger("com.vivareal", debug)
root(INFO, ["STDOUT", "FILE"])