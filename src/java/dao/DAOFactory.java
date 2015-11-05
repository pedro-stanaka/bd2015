package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import jdbc.ConnectionFactory;

public class DAOFactory implements AutoCloseable {

    private Connection connection = null;

    public DAOFactory() throws ClassNotFoundException, IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
    }

    public void beginTransaction() throws SQLException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao abrir transação.");
        }
    }

    public Savepoint createSavepoint(String name) throws SQLException {
        try {
            return connection.setSavepoint(name);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao executar transação.");
        }
    }

    public void commitTransaction() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao finalizar transação.");
        }
    }

    public void rollbackTransaction() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao executar transação.");
        }
    }

    public void rollbackTransactionTo(Savepoint savepoint) throws SQLException {
        try {
            connection.rollback(savepoint);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao executar transação.");
        }
    }

    public void endTransaction() throws SQLException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao finalizar transação.");
        }
    }

    public void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao fechar conexão ao banco de dados.");
        }
    }

    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO(connection);
    }

    public TaxiDAO getTaxiDAO() {
        return new TaxiDAO(connection);
    }

    @Override
    public void close() throws SQLException {
        closeConnection();
    }
}