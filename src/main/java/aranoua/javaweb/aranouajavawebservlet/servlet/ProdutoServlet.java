package aranoua.javaweb.aranouajavawebservlet.servlet;

import aranoua.javaweb.aranouajavawebservlet.dao.ProdutoDao;
import aranoua.javaweb.aranouajavawebservlet.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "produtoServlet", value = "/produto")
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //http://localhost:8080/produto?acao=cadastrar

            String acao = request.getParameter("acao");
            switch (acao != null ? acao : "" ) {
                case "cadastrar":
                    formularioProduto(request,response);
                    break;
                case "consultar":
                    consultarProduto(request,response);
                    break;
                case "alterar":
                    formularioProduto(request,response);
                    break;
                case "excluir":
                    excluirProduto(request,response);
                    break;
                default:
                    listarProduto(request, response);
            }

        }catch(Exception e){
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tituloCadastrar = "Cadastro de Produto";
        String tituloAlterar = "Alteração de Produto";
        String titulo;

        String mensagemCadastrar = "Produto cadastrado com sucesso!";
        String mensagemAlterar = "Produto alterado com sucesso!";
        String mensagem;

        try {

            String salvar = request.getParameter("salvar");

            if (salvar != null) {

                Produto produto = new Produto();

                produto.setNomeDoProduto(request.getParameter("nome"));
                produto.setMarca(request.getParameter("marca"));
                produto.setModelo(request.getParameter("modelo"));

                ProdutoDao produtoDao = new ProdutoDao();

                String numerodetombo = request.getParameter("numerodetombo");

                if(numerodetombo != null && !numerodetombo.equals("0")){
                    //Alterar
                    titulo = tituloAlterar;
                    mensagem = mensagemAlterar;
                    produto.setNumeroDeTombo(Integer.parseInt(numerodetombo));
                    produtoDao.alterar(produto);
                }else {
                    titulo = tituloCadastrar;
                    mensagem = mensagemCadastrar;
                    produtoDao.inserir(produto);
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

                out.println("<a href='produto'>Voltar</a>");

                out.println("</body>");
                out.println("</html>");

            }

        }catch(Exception e){
            throw new ServletException(e);
        }


    }

    private void formularioProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //Se vier com o I D preenchido, é para mostrar o formulário de alterar.
            String numerodetombo = request.getParameter("numerodetombo");

            Produto produto;

            if (numerodetombo != null) {
                ProdutoDao produtoDao = new ProdutoDao();
                produto = produtoDao.consultar(Long.parseLong(numerodetombo));
            }else{
                produto =  new Produto();
                produto.setNomeDoProduto("");
                produto.setMarca("");
                produto.setModelo("");
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");

            if(numerodetombo != null){
                out.println("<title>Alterar Produto</title>");
            }else{
                out.println("<title>Cadastrar Produto</title>");
            }

            out.println("</head>");
            out.println("<body>");

            if(numerodetombo!= null){
                out.println("<h1>Alterar Produto</h1>");
            }else{
                out.println("<h1>Cadastrar Produto</h1>");
            }


            out.println("<form action='produto' method='post'>");

            out.println("<input type='hidden' name='numerodetombo' value='"+produto.getNumeroDeTombo()+"'>");

            out.println("<label for='produto_nome'>Nome do Produto:</label>");
            out.println("<input type='text' name='nome' value='"+produto.getNomeDoProduto()+"' required>");
            out.println("<br><br>");

            out.println("<label for='produto_marca'>Marca:</label>");
            out.println("<input type='text' name='marca' value='"+produto.getMarca()+"' required>");
            out.println("<br><br>");

            out.println("<label for='produto_modelo'>Modelo:</label>");
            out.println("<input type='text' name='modelo' value='"+produto.getModelo()+"' required>");
            out.println("<br><br>");

            out.println("<input type='reset' name='limpar' value='Limpar'>");
            out.println("<input type='submit' name='salvar' value='Salvar'>");

            out.println("</form>");

            out.println("<a href='produto'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");

        }catch (Exception e){
            throw new ServletException(e);
        }
    }


    private void listarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            ProdutoDao produtoDao = new ProdutoDao();

            List<Produto> produtos = produtoDao.listar();

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Lista de Produtos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Produtos</h1>");

            out.println("<table border='1'>");

            out.println("<th>Número de Tombo</th><th>Nome</th><th>Marca</th><th>Modelo</th>");
            for (Produto produto : produtos) {
                out.println("<tr><td><a href='produto?acao=consultar&id=" + produto.getNumeroDeTombo() + "'>" + produto.getNumeroDeTombo() + "</a></td><td>" + produto.getNomeDoProduto() +
                        "</td><td>" + produto.getMarca() + "</td><td>" + produto.getModelo() + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><br>");
            out.println("<a href='produto?acao=cadastrar'>Cadastrar Produto</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    private void consultarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            ProdutoDao produtoDao = new ProdutoDao();

//           http://localhost:8080/produto?acao=consultar&id=1

            String id = request.getParameter("id");

            Produto produto = produtoDao.consultar(Long.parseLong(id));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Consulta Detalhes da Produto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Consulta Detalhes da Produto</h1>");

            out.println("<p>Número de Tombo: "+produto.getNumeroDeTombo() +"</p>");
            out.println("<p>Nome: "+produto.getNomeDoProduto() +"</p>");
            out.println("<p>Marca: "+produto.getMarca()+"</p>");
            out.println("<p>Modelo: "+produto.getModelo()+"</p>");

            out.println("<a href='produto?acao=alterar&id="+produto.getNumeroDeTombo()+"'>Alterar</a>");
            out.println("<a href='produto?acao=excluir&id="+produto.getNumeroDeTombo()+"'>Excluir</a>");
            out.println("<a href='produto'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            ProdutoDao produtoDao = new ProdutoDao();

//          http://localhost:8080/produto?acao=excluir&id=1

            String id = request.getParameter("id");

            produtoDao.excluir(Long.parseLong(id));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Exclusão de Produto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exclusão de Produto</h1>");

            out.println("<p> Produto excluído com sucesso</p>");

            out.println("<a href='produto'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
            throw new ServletException(e);
        }
    }
}