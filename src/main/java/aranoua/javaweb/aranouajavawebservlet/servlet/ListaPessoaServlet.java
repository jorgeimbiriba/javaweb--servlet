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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listaPessoaServlet",value = "/lista-pessoas")
public class ListaPessoaServlet extends HttpServlet {

    private List<Pessoa> pessoas = new ArrayList<>();

    private void carregarPessoas()  {
//        pessoas.add(new Pessoa("Rogerio","11111111","rogerio@email.com"));
//        pessoas.add(new Pessoa("Maria","222222","maria@email.com"));
//        pessoas.add(new Pessoa("João","333333","joao@email.com"));

        PessoaDao pessoaDao = new PessoaDao();

        try {

            this.pessoas = pessoaDao.listar();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        carregarPessoas();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Lista de Pessoas</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Lista de Pessoas</h1>");
        out.println("<p>O cadastro de pessoa é importante para a coleta de dados...</p>");

        out.println("<table border=\"1\">");

        out.println("<th>Nome</th><th>CPF</th><th>Email</th>");
        for(Pessoa pessoa: pessoas){
            out.println("<tr><td>"+pessoa.getNome()+"</td><td>"+pessoa.getEmail()+"</td></tr>");
        }

        out.println("</table>");

//        <table border="1" >
//                <th>Nome</th><th>CPF</th><th>E-mail</th>
//                <tr><td>Rogério</td><td>111111</td><td>rogerio@email.com</td></tr>
//                <tr><td>Maria</td><td>222222</td><td>maria@email.com</td></tr>
//                <tr><td>João</td><td>333333</td><td>joao@email.com</td></tr>
//                </table>


        out.println("</body>");
        out.println("</html>");

    }

}
