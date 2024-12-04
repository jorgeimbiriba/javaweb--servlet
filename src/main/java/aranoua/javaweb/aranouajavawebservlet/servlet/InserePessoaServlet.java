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

@WebServlet(name = "inserePessoaServlet", value = "/insere-pessoa")
public class InserePessoaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//                <input id="pessoa_nome" type="text" name="nome" required>
//                <input id="pessoa_email" type="text" name="email" required>
//                <input type="submit" name="enviar" value="Enviar">
//
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        PessoaDao pessoaDao = new PessoaDao();
        try {
            pessoaDao.inserir(pessoa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Cadastro de Pessoa</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Cadastro de Pessoa</h1>");
        out.println("<p>A pessoa foi incluída com sucesso:</p>");
        out.println("<p>Nome:"+pessoa.getNome()+", Email:"+pessoa.getEmail()+"</p>");

        out.println("</body>");
        out.println("</html>");

    }


}
