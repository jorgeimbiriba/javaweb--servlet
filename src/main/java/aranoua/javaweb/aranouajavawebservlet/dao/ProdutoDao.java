package aranoua.javaweb.aranouajavawebservlet.dao;

import aranoua.javaweb.aranouajavawebservlet.model.Produto;
import aranoua.javaweb.aranouajavawebservlet.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    public void inserir (Produto produto) throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "insert into produto" +
                " (numerodetombo,nomedoproduto,marca,modelo)" +
                " values" +
                " ('" + produto.getNumeroDeTombo() + "','" + produto.getNomeDoProduto() + "','" + produto.getMarca() +
                "','" +  produto.getModelo() + "')";

        System.out.println("SQL:" + sql);

        instrucao.execute(sql);
    }

    public void alterar(Produto produto) throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "update produto"+
                " set nomedoproduto = '"+produto.getNomeDoProduto()+"',"+
                "     marca = '"+produto.getMarca()+"',"+
                "     modelo = '"+produto.getModelo()+"'"+
                " where numerodetombo = "+produto.getNumeroDeTombo();

        System.out.println("SQL:" + sql);

        instrucao.execute(sql);

    }

    public void excluir(Long numerodetombo) throws SQLException{
        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "delete from produto " +
                " where numerodetombo = "+numerodetombo;

        System.out.println("SQL:" + sql);

        instrucao.execute(sql);
    }

    public List<Produto> listar() throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "select numerodetombo, nomedoproduto, marca, modelo  from produto";

        System.out.println("SQL:"+sql);

        boolean resultado = instrucao.execute(sql);

        List<Produto> produtos = new ArrayList<>();
        Produto produto;

        if(resultado){
            ResultSet resultados = instrucao.getResultSet();
            while(resultados.next()){
                produto = new Produto();
                produto.setNumeroDeTombo(resultados.getInt(1));
                produto.setNomeDoProduto(resultados.getString(2));
                produto.setMarca(resultados.getString(3));
                produto.setModelo(resultados.getString(4));
                produtos.add(produto);
            }
        }
        return produtos;
    }

    public Produto consultar(Long numerodetombo) throws SQLException{

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "select numerodetombo, nomedoproduto, marca, modelo from produto where numerodetombo="+numerodetombo;
        //String sql = "select id,nome,email from pessoa where id="+id;

        System.out.println("SQL:"+sql);

        boolean resultado = instrucao.execute(sql);

        Produto produto;

        if(resultado){
            ResultSet resultados = instrucao.getResultSet();
            while(resultados.next()){
                produto = new Produto();
                produto.setNumeroDeTombo(resultados.getInt(1));
                produto.setNomeDoProduto(resultados.getString(2));
                produto.setMarca(resultados.getString(3));
                produto.setModelo(resultados.getString(4));
                return produto;
            }
        }
        return null;
    }

}