package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionJdbc {

    private String jdbcDriver;
    private String url;
    private String usuario;
    private String password;

    private Connection connection;

    public ConfiguracionJdbc(String jdbcDriver, String url, String usuario, String password, Connection connection) {
        this.jdbcDriver = jdbcDriver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        this.connection = connection;
    }

    public ConfiguracionJdbc() {
        this.jdbcDriver= "org.h2.Driver";
        this.url = "jdbc:h2:C:\\Users\\Laura\\h2\\bin;INIT=RUNSCRIPT FROM 'create.sql'";

        //jdbc:h2:C:\\Users\\Laura\\h2\\bin
        this.usuario = "sa";
        this.password = "";
    }


    public Connection conectarConBaseDeDatos() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(url, usuario, password);
              } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
