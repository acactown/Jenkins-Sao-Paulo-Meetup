package com.vivareal.app.view.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author Andrés Amado
 */
@Controller
@RequestMapping("/")
class MediatorController extends ViewController {

    @RequestMapping
    String getIndex(final ModelMap model) {
        "redirect:/jobs"
    }

}
