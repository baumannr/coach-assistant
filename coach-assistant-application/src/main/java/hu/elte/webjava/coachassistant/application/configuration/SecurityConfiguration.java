package hu.elte.webjava.coachassistant.application.configuration;

import hu.elte.webjava.coachassistant.application.common.Mappings;
import hu.elte.webjava.coachassistant.application.security.LoginSuccessHandler;
import hu.elte.webjava.coachassistant.application.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring MVC security configuration.
 * @author baumannr
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String URL_H2_CONSOLE = "/h2-console/**";
    private static final String URL_CSS = "/css/**";
    private static final String URL_LIB = "/lib/**";
    private static final String URL_IMG = "/img/**";
    private static final String URL_FAVICON = "/favicon.ico";
    private final LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public SecurityConfiguration(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(URL_CSS).permitAll()
                .antMatchers(URL_LIB).permitAll()
                .antMatchers(URL_IMG).permitAll()
                .antMatchers(URL_H2_CONSOLE).permitAll()
                .antMatchers(URL_FAVICON).permitAll()
                .antMatchers(Mappings.GET_REGISTER).permitAll()
                .antMatchers(Mappings.HOME).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(Mappings.PREFIX_CLIENT_DASHBOARD + "/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_CLIENT_PROFILE + "/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_CALORIE_DIARY + "/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_WEIGHT_DIARY + "/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_COACH_DASHBOARD + "/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_COACH_PROFILE + "/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_EXERCISE + "/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_CLIENT_DETAILS + "/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/**")
                    .hasAnyRole(Role.ROLE_COACH.getNameWithoutPrefix(), Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/index/**")
                    .hasAnyRole(Role.ROLE_COACH.getNameWithoutPrefix(), Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN  + "/details/**")
                    .hasAnyRole(Role.ROLE_COACH.getNameWithoutPrefix(), Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/subscribe/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/unsubscribe/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/change-subscription/**").hasRole(Role.ROLE_CLIENT.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/create/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/edit/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .antMatchers(Mappings.PREFIX_TRAINING_PLAN + "/delete/**").hasRole(Role.ROLE_COACH.getNameWithoutPrefix())
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers(URL_H2_CONSOLE)
            .and()
                .headers().frameOptions().sameOrigin() // h2 console access
            .and()
                .formLogin()
                    .loginPage(Mappings.LOGIN).permitAll()
                    .failureUrl(Mappings.LOGIN + "?error=true")
                    .successHandler(loginSuccessHandler)
                .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
