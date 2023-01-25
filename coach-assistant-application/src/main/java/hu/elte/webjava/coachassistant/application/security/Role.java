package hu.elte.webjava.coachassistant.application.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENT("CLIENT"), ROLE_COACH("COACH");

    private final String nameWithoutPrefix;

    Role(String withoutPrefix) {
        this.nameWithoutPrefix = withoutPrefix;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getNameWithoutPrefix() {
        return nameWithoutPrefix;
    }
}
