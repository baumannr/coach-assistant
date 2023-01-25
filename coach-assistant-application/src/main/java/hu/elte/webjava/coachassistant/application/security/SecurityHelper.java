package hu.elte.webjava.coachassistant.application.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ! (authentication instanceof AnonymousAuthenticationToken);
    }

    public static boolean isClient() {
        DomainUserPrincipal principal = getPrincipal();
        return principal.getAuthorities().contains(Role.ROLE_CLIENT);
    }

    public static boolean isCoach() {
        DomainUserPrincipal principal = getPrincipal();
        return principal.getAuthorities().contains(Role.ROLE_COACH);
    }

    public static int getUserId() {
        DomainUserPrincipal principal = getPrincipal();
        return principal.getUserId();
    }

    public static String getFirstName() {
        DomainUserPrincipal principal = getPrincipal();
        return principal.getFirstName();
    }

    public static String getLastName() {
        DomainUserPrincipal principal = getPrincipal();
        return principal.getLastName();
    }

    private static DomainUserPrincipal getPrincipal() {
        return (DomainUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
