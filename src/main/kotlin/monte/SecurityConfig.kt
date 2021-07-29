package monte

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
open class SecurityConfig(private val logoutHandler: LogoutHandler) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.oauth2Login()
            .and().logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .addLogoutHandler(logoutHandler)
    }
}
