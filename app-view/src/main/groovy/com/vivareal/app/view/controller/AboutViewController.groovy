package com.vivareal.app.view.controller

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author Andrés Amado
 */
@Slf4j
@Controller
@RequestMapping("/about")
class AboutViewController extends ViewController {

    private static final String PAGE_ABOUT = "about"

    @RequestMapping
    String getExecutions(final ModelMap model) {
        updatedView(model, PAGE_ABOUT)
    }

}
