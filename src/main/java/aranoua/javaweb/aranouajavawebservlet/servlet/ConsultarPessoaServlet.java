package aranoua.javaweb.aranouajavawebservlet.servlet;

import aranoua.javaweb.aranouajavawebservlet.dao.PessoaDao;
import aranoua.javaweb.aranouajavawebservlet.model.Pessoa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "consultarPessoaServlet", value = "/consulta-pessoa")
public class ConsultarPessoaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//                <input id="pessoa_nome" type="text" name="nome" required>
//                <input id="pessoa_email" type="text" name="email" required>
//                <input type="submit" name="enviar" value="Enviar">
//
        String id = request.getParameter("id");

        PessoaDao pessoaDao = new PessoaDao();

        Pessoa pessoa = null;

        try {
            pessoa = pessoaDao.consultar(Long.parseLong(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Consultar de Pessoa</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Consultar Pessoa</h1>");
        out.println("<p>Nome:"+pessoa.getNome()+", Email:"+pessoa.getEmail()+"</p>");

        out.println("</body>");
        out.println("</html>");

    }


}
