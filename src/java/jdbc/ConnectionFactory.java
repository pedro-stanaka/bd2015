package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory instance = null;

    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    public void readProperties() {
        Properties properties = new Properties();

        try {
            // Carrega o arquivo com as propriedades do banco de dados.
            String path = "jdbc/datasource.properties";
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(path);
            properties.load(input);

            // Obtém as informações do banco de dados a partir do arquivo carregado.
            dbHost = properties.getProperty("host");
            dbPort = properties.getProperty("port");
            dbName = properties.getProperty("name");
            dbUser = properties.getProperty("user");
            dbPassword = properties.getProperty("password");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            // Registra o driver na JVM.
            Class.forName("org.postgresql.Driver");

            // Lê as propriedades do banco de dados.
            readProperties();

            // Forma a URL de conexão.
            String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

            // Estabelece uma conexão.
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return connection;
    }
}