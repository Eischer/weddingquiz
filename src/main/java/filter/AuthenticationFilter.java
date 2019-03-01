package filter;

import service.SessionData;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Checks if the User is logged in and so authorized to access the admin area.
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements javax.servlet.Filter {

    @Inject
    private SessionData sessionData;

    private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpResponse.setDateHeader("Expires", 0); // Proxies.

        String reqURI = httpRequest.getRequestURI();

        if ((reqURI.startsWith("/wedding/quiz.xhtml")
                || reqURI.startsWith("/wedding/end.xhtml")
                ||  reqURI.startsWith("/wedding/createCategory.xhtml")
                || reqURI.startsWith("/wedding/createQuestions.xhtml")) && sessionData.getCurrentPerson() == null) {
            if ("partial/ajax".equals(httpRequest.getHeader("Faces-Request"))) {
                httpResponse.setContentType("text/xml");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().printf(AJAX_REDIRECT_XML, httpRequest.getContextPath() + "/start.xhtml");
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/start.xhtml");
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) {
        //Nothing to do here
    }

    @Override
    public void destroy() {
        //Nothing to do here
    }
}
