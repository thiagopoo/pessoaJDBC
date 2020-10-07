package dao;

import factory.FabricaDeConexao;
import model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    protected Connection getConnection(){
        return FabricaDeConexao.getInstance().getConnection();
    }

    public void insert(String tabela, int id, String nome, int idade){
        String sql = "INSERT INTO "
                    + tabela
                    + " (ID, NOME, IDADE) VALUES ("
                    + id
                    + ","
                    + nome
                    + ","
                    + idade
                    + ");";

        try(PreparedStatement prepared = getConnection().prepareStatement(sql) ){
            prepared.execute();
            System.out.println("Inserido Corretamente");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Pessoa> select(String coluna, String tabela, String where){
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT "
                + coluna
                + " from "
                + tabela
                + " where ";
        try(PreparedStatement prepared = getConnection().prepareStatement(sql)){
            ResultSet result = prepared.executeQuery();
            while (result.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getInt(1));
                pessoa.setNome(result.getString(2));
                pessoa.setIdade(result.getInt(3));
                lista.add(pessoa);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void altera (String tabela, String[] coluna, String[] value, String where){
        String sql = "UPDATE "
                + tabela
                + " SET "
                + coluna
                + " = "
                + value
                + " WHERE "
                + where;
        try(PreparedStatement prepared = getConnection().prepareStatement(sql)){
            prepared.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public  void remove (String tabela, String where){
        String sql = "DELETE FROM "
                + tabela
                + " WHERE"
                + where;
        try(PreparedStatement prepared = getConnection().prepareStatement(sql)){
            prepared.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
