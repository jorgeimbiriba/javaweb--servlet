package aranoua.javaweb.aranouajavawebservlet.dao;

import aranoua.javaweb.aranouajavawebservlet.model.Pessoa;
import aranoua.javaweb.aranouajavawebservlet.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao {

    public void inserir (Pessoa pessoa) throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "insert into pessoa" +
                " (nome,email)" +
                " values" +
                " ('" + pessoa.getNome() + "','" + pessoa.getEmail() + "')";

        System.out.println("SQL:" + sql);

        instrucao.execute(sql);

    }

    public void alterar(Pessoa pessoa) throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "update pessoa " +
                " set nome = '"+pessoa.getNome()+"',"+
                "     email = '"+pessoa.getEmail()+"'"+
                " where id = "+pessoa.getId();

        System.out.println("SQL:" + sql);

        instrucao.execute(sql);

    }

    public void excluir(Long id) throws SQLException{
        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "delete from pessoa " +
                " where id = "+id;

        System.out.println("SQL:" + sql);

        instrucao.execute(sql);
    }

    public List<Pessoa> listar() throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "select id,nome,email from pessoa";

        System.out.println("SQL:"+sql);

        boolean resultado = instrucao.execute(sql);

        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa;

        if(resultado){
            ResultSet resultados = instrucao.getResultSet();
            while(resultados.next()){
                pessoa = new Pessoa();
                pessoa.setId(resultados.getInt(1));
                pessoa.setNome(resultados.getString(2));
                pessoa.setEmail(resultados.getString(3));
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    public Pessoa consultar(Long id) throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "select id,nome,email from pessoa where id="+id;

        System.out.println("SQL:"+sql);

        boolean resultado = instrucao.execute(sql);

        Pessoa pessoa;

        if(resultado){
            ResultSet resultados = instrucao.getResultSet();
            while(resultados.next()){
                pessoa = new Pessoa();
                pessoa.setId(resultados.getInt(1));
                pessoa.setNome(resultados.getString(2));
                pessoa.setEmail(resultados.getString(3));
                return pessoa;
            }
        }
        return null;
    }

}