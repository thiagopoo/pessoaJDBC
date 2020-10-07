package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {

    private static FabricaDeConexao instance;

    private FabricaDeConexao(){};

    public static FabricaDeConexao getInstance(){
        if (instance == null){
            instance = new FabricaDeConexao();
        }
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:banco_teste.db");
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}
