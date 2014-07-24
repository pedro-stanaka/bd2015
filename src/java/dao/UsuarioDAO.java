package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

    private static final String createQuery = "INSERT INTO usuario(login, senha, nome, nascimento) VALUES(?, md5(?), ?, ?);";
    private static final String readQuery = "SELECT * FROM usuario WHERE id = ?;";
    private static final String updateQuery = "UPDATE usuario SET login = ?, senha = ?, nome = ?, nascimento = ? WHERE id = ?;";
    private static final String deleteQuery = "DELETE FROM usuario WHERE id = ?;";
    private static final String allQuery = "SELECT * FROM usuario;";
    
    @Override
    public void create(Usuario usuario) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setDate(4, usuario.getNascimento());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario read(Integer id) {
        Usuario usuario = new Usuario();

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setId(result.getInt("id"));
                    usuario.setLogin(result.getString("login"));
                    usuario.setSenha(result.getString("senha"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setDate(4, usuario.getNascimento());
            statement.setInt(5, usuario.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> all() {
        List<Usuario> usuarioList = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(allQuery);
             ResultSet result = statement.executeQuery();) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setLogin(result.getString("login"));

                usuarioList.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarioList;
    }
}