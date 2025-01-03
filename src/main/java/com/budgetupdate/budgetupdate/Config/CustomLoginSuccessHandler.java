package com.budgetupdate.budgetupdate.Config;

import com.budgetupdate.budgetupdate.Models.User;
import com.budgetupdate.budgetupdate.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private UserService userService; // Inject UserService to access user details

    @Autowired
    public CustomLoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        // Use the default redirect strategy provided by Spring Security
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    // Determine the target URL based on whether the user is an admin or not
    protected String determineTargetUrl(Authentication authentication) {
        // Get the principal, which is the Spring Security UserDetails object
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        // Fetch the User entity from the UserService using the username
        User user = userService.findByEmail(principal.getUsername());

        // Check if the user is an admin
        if (user.isAdmin()) {
            // Redirect to the admin dashboard
            return "/admin/admindashboard";
        } else {
            // Redirect to the user dashboard
            return "/user/userdashboard";
        }
    }
}
