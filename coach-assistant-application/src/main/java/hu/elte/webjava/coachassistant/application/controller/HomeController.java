package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.Mappings;
import hu.elte.webjava.coachassistant.application.common.Views;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return getView();
    }

    @GetMapping(Mappings.HOME)
    public String home() {
        return getView();
    }

    private String getView() {
        if (SecurityHelper.isLoggedIn()) {
            if (SecurityHelper.isClient()) {
                return Views.REDIRECT_CLIENT_DASHBOARD_INDEX;
            } else if (SecurityHelper.isCoach()){
                return Views.REDIRECT_COACH_DASHBOARD_INDEX;
            }
        }
        return Views.HOME;
    }
}
