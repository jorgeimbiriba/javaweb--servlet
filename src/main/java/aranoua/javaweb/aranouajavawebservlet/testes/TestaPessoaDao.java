package aranoua.javaweb.aranouajavawebservlet.testes;

import aranoua.javaweb.aranouajavawebservlet.dao.PessoaDao;
import aranoua.javaweb.aranouajavawebservlet.model.Pessoa;

import java.sql.SQLException;
import java.util.List;

public class TestaPessoaDao {

    public static void main(String[] args) {
        PessoaDao pessoaDao = new PessoaDao();
        try {

            List<Pessoa> pessoas = pessoaDao.listar();
            for (Pessoa pessoa : pessoas) {
                System.out.println("Id: " + pessoa.getId());
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("Email: " + pessoa.getEmail());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
