import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "pessoaServlet", value = "/pessoa")
public class PessoaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //http://localhost:8080/pessoa?acao=cadastrar

            String acao = request.getParameter("acao");
            switch (acao != null ? acao : "" ) {
                case "cadastrar":
                    System.out.println("Cadastrar Pessoa");
                    break;
                case "consultar":
                    System.out.println("Consultar Pessoa");
                    break;
                case "alterar":
                    System.out.println("Alterar Pessoa");
                    break;
                case "excluir":
                    System.out.println("Excluir Pessoa");
                    break;
                default:
                    listarPessoa(request, response);
            }

        }catch(Exception e){
            throw new ServletException(e);
        }

    }

    private void listarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            PessoaDao pessoaDao = new PessoaDao();

            List<Pessoa> pessoas = pessoaDao.listar();

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

            out.println("<table border=\"1\">");

            out.println("<th>Nome</th><th>Email</th>");
            for (Pessoa pessoa : pessoas) {
                out.println("<tr><td>" + pessoa.getNome() + "</td><td>" + pessoa.getEmail() + "</td></tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }
