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
                    formularioPessoa(request,response);
                    break;
                case "consultar":
                    consultarPessoa(request,response);
                    break;
                case "alterar":
                    formularioPessoa(request,response);
                    break;
                case "excluir":
                    excluirPessoa(request,response);
                    break;
                default:
                    listarPessoa(request, response);
            }

        }catch(Exception e){
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tituloCadastrar = "Cadastro de Pessoa";
        String tituloAlterar = "Alteração de Pessoa";
        String titulo;

        String mensagemCadastrar = "Pessoa cadastrada com sucesso!";
        String mensagemAlterar = "Pessoa alterada com sucesso!";
        String mensagem;

        try {

            String salvar = request.getParameter("salvar");

            if (salvar != null) {

                Pessoa pessoa = new Pessoa();

                pessoa.setNome(request.getParameter("nome"));
                pessoa.setEmail(request.getParameter("email"));

                PessoaDao pessoaDao = new PessoaDao();

                String id = request.getParameter("id");

                if(id != null && !id.equals("0")){
                    //Alterar
                    titulo = tituloAlterar;
                    mensagem = mensagemAlterar;
                    pessoa.setId(Integer.parseInt(id));
                    pessoaDao.alterar(pessoa);
                }else {
                    titulo = tituloCadastrar;
                    mensagem = mensagemCadastrar;
                    pessoaDao.inserir(pessoa);
                }

                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>"+titulo+"</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>"+titulo+"</h1>");

                out.println("<p>"+mensagem+"</p>");

                out.println("<a href='pessoa'>Voltar</a>");

                out.println("</body>");
                out.println("</html>");

            }

        }catch(Exception e){
            throw new ServletException(e);
        }


    }

    private void formularioPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //Se vier com o I D preenchido, é para mostrar o formulário de alterar.
            String id = request.getParameter("id");

            Pessoa pessoa;

            if (id != null) {
                PessoaDao pessoaDao = new PessoaDao();
                pessoa = pessoaDao.consultar(Long.parseLong(id));
            }else{
                pessoa =  new Pessoa();
                pessoa.setNome("");
                pessoa.setEmail("");
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");

            if(id != null){
                out.println("<title>Alterar Pessoa</title>");
            }else{
                out.println("<title>Cadastrar Pessoa</title>");
            }


            out.println("</head>");
            out.println("<body>");

            if(id != null){
                out.println("<h1>Alterar Pessoa</h1>");
            }else{
                out.println("<h1>Cadastrar Pessoa</h1>");
            }


            out.println("<form action='pessoa' method='post'>");

            out.println("<input type='hidden' name='id' value='"+pessoa.getId()+"'>");

            out.println("<label for='pessoa_nome'>Nome:</label>");

            out.println("<input type='text' name='nome' value='"+pessoa.getNome()+"' required>");

            out.println("<br><br>");
            out.println("<label for='pessoa_email'>E-mail:</label>");

            out.println("<input type='text' name='email' value='"+pessoa.getEmail()+"' required>");

            out.println("<br><br>");
            out.println("<input type='reset' name='limpar' value='Limpar'>");
            out.println("<input type='submit' name='salvar' value='Salvar'>");

            out.println("</form>");

            out.println("<a href='pessoa'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
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

            out.println("<table border='1'>");

            out.println("<th>Id</th><th>Nome</th><th>Email</th>");
            for (Pessoa pessoa : pessoas) {
//                http://localhost:8080/pessoa?acao=consultar&id=1
                out.println("<tr><td><a href='pessoa?acao=consultar&id="+pessoa.getId()+"'>" + pessoa.getId() + "</a></td><td>" + pessoa.getNome() + "</td><td>" + pessoa.getEmail() + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><br>");
            out.println("<a href='pessoa?acao=cadastrar'>Cadastrar Pessoa</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    private void consultarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            PessoaDao pessoaDao = new PessoaDao();

//            http://localhost:8080/pessoa?acao=consultar&id=1

            String id = request.getParameter("id");

            Pessoa pessoa = pessoaDao.consultar(Long.parseLong(id));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Consulta Detalhes da Pessoa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Consulta Detalhes da Pessoa</h1>");

            out.println("<p>Id: "+pessoa.getId() +"</p>");
            out.println("<p>Nome: "+pessoa.getNome() +"</p>");
            out.println("<p>Email: "+pessoa.getEmail() +"</p>");

            out.println("<a href='pessoa?acao=alterar&id="+pessoa.getId()+"'>Alterar</a>");
            out.println("<a href='pessoa?acao=excluir&id="+pessoa.getId()+"'>Excluir</a>");
            out.println("<a href='pessoa'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    private void excluirPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            PessoaDao pessoaDao = new PessoaDao();

//          http://localhost:8080/pessoa?acao=excluir&id=1

            String id = request.getParameter("id");

            pessoaDao.excluir(Long.parseLong(id));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Exclusão de Pessoa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exclusão de Pessoa</h1>");

            out.println("<p> Pessoa excluída com sucesso</p>");

            out.println("<a href='pessoa'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }
}