package hu.elte.webjava.coachassistant.application.security;

import hu.elte.webjava.coachassistant.application.common.Views;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirects the user to the role specific dashboard page after login based on role.
 * @author baumannr
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        DomainUserPrincipal principal = (DomainUserPrincipal) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (principal.hasRole(Role.ROLE_CLIENT)) {
            redirectURL = Views.CLIENT_DASHBOARD_INDEX;
        } else if (principal.hasRole(Role.ROLE_COACH)) {
            redirectURL = Views.COACH_DASHBOARD_INDEX;
        }

        response.sendRedirect(redirectURL);
    }
}
