package iostart.Fillter;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostart.Entyti.Users;
import iostart.util.SessionUtil;


@WebFilter("/AuthorizationFilter")
public class AuthorizationFilter extends HttpFilter implements Filter {
       
  
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    public AuthorizationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String url = request.getRequestURI();
		if (url.startsWith("/WEB_ECOM/admin"))
		{
			Users user = (Users) SessionUtil.getInstance().getValue(request, "Users");
			if (user != null)
			{
				if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Admin")))
				{
					filterChain.doFilter(servletRequest, servletResponse);
				}
				else if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Seller")) || user.getRoleid() == Integer.parseInt(resourceBundle.getString("Customer")))
				{
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
				}
			}
			else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
			}
		}
		else if (url.startsWith("/WEB_ECOM/seller"))
		{
			Users user = (Users) SessionUtil.getInstance().getValue(request, "Users");
			if (user != null)
			{
				if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Seller")))
				{
					filterChain.doFilter(servletRequest, servletResponse);
				}
				else if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Admin")) || user.getRoleid() == Integer.parseInt(resourceBundle.getString("Customer")))
				{
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
				}
			}
			else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
			}
		}
		else if (url.startsWith("/WEB_ECOM/home/cart") || url.startsWith("/WEB_ECOM/user"))
		{
			Users user = (Users) SessionUtil.getInstance().getValue(request, "Users");
			if (user != null)
			{
				filterChain.doFilter(servletRequest, servletResponse);
			}
			else
			{
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
			}
		}
		else
		{
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

		this.context = fConfig.getServletContext();
	}

}
