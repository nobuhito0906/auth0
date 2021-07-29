package monte

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/")
    fun home(model: Model, @AuthenticationPrincipal principal: OidcUser?): String {
        model.addAttribute("message", "ログインできたら表示")
        principal?.let {
            model.addAttribute("profile", it.claims);
        }
        return "hello"
    }
}
