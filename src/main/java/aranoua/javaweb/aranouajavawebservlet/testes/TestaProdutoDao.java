package aranoua.javaweb.aranouajavawebservlet.testes;

import aranoua.javaweb.aranouajavawebservlet.dao.ProdutoDao;
import aranoua.javaweb.aranouajavawebservlet.model.Produto;

import java.sql.SQLException;
import java.util.List;

public class TestaProdutoDao {

    public static void main(String[] args) {
        ProdutoDao pessoaDao = new ProdutoDao();
        try {

            List<Produto> produtos = pessoaDao.listar();
            for (Produto produto: produtos) {
                System.out.println("Número de Tombo: " + produto.getNumeroDeTombo());
                System.out.println("Nome: " + produto.getNomeDoProduto());
                System.out.println("Marca: " + produto.getMarca());
                System.out.println("Modelo: "+ produto.getModelo());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
