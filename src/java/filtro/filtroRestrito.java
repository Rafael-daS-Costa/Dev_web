package filtro;

import entidade.Funcionarios;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "filtroRestrito", urlPatterns = {"/admin/*"})
public class filtroRestrito implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        Funcionarios funcionario = (Funcionarios)((HttpServletRequest) request).getSession().getAttribute("funcionario");

        if ((funcionario != null) && (!((String) funcionario.getNome()).isEmpty())) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/home");
        }
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
