package aranoua.javaweb.aranouajavawebservlet.testes;

import aranoua.javaweb.aranouajavawebservlet.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaConexaoUtil {

    public static void main(String[] args) {

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        try {

            Connection conexao = conexaoUtil.getConexao();

            String sql = "select id,nome,email from pessoa";

            System.out.println("SQL:"+sql);

            Statement instrucao = conexao.createStatement();

            boolean temResultado = instrucao.execute(sql);

            if(temResultado){
                ResultSet resultados = instrucao.getResultSet();
                while(resultados.next()){
                    System.out.println("ID:"+resultados.getInt("id"));
                    System.out.println("NOME:"+resultados.getString("nome"));
                }

            }

        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }


    }

}
