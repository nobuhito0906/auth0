package monte

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class LogoutHandler
    @Autowired constructor(private val clientRegistrationRepository: ClientRegistrationRepository) :
    SecurityContextLogoutHandler() {

    override fun logout(
        httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse,
        authentication: Authentication
    ) {
        super.logout(httpServletRequest, httpServletResponse, authentication)

        httpServletResponse.sendRedirect(UriComponentsBuilder
            .fromHttpUrl("${clientRegistration.providerDetails.configurationMetadata["issuer"]}v2/logout?client_id={clientId}&returnTo={returnTo}")
            .encode()
            .buildAndExpand(clientRegistration.clientId, ServletUriComponentsBuilder.fromCurrentContextPath().build().toString())
            .toUriString())
    }

    private val clientRegistration: ClientRegistration
        get() = clientRegistrationRepository.findByRegistrationId("auth0")
}
