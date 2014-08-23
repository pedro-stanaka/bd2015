package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {

    private static final String createQuery =
                                "INSERT INTO usuario(login, senha, nome, nascimento) " +
                                "VALUES(?, md5(?), ?, ?);";

    private static final String readQuery =
                                "SELECT login, nome, nascimento " +
                                "FROM usuario " +
                                "WHERE id = ?;";

    private static final String updateQuery =
                                "UPDATE usuario " +
                                "SET login = ?, nome = ?, nascimento = ? " +
                                "WHERE id = ?;";

    private static final String updateWithPasswordQuery =
                                "UPDATE usuario " +
                                "SET login = ?, nome = ?, nascimento = ?, senha = md5(?) " +
                                "WHERE id = ?;";

    private static final String deleteQuery =
                                "DELETE FROM usuario " +
                                "WHERE id = ?;";

    private static final String allQuery =
                                "SELECT id, login " +
                                "FROM usuario " +
                                "ORDER BY id;";

    private static final String authenticateQuery =
                                "SELECT id, nome, nascimento " +
                                "FROM usuario " +
                                "WHERE login = ? AND senha = md5(?);";

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setDate(4, usuario.getNascimento());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uq_usuario_login")) {
                throw new SQLException("Erro ao inserir usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    @Override
    public Usuario read(Integer id) throws SQLException {
        Usuario usuario = new Usuario();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setId(id);
                    usuario.setLogin(result.getString("login"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                } else {
                    throw new SQLException("Erro ao visualizar: usuário não encontrado.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar usuário.");
            }
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String query;

        if (usuario.getSenha() == null) {
            query = updateQuery;
        } else {
            query = updateWithPasswordQuery;
        }

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getNome());
            statement.setDate(3, usuario.getNascimento());

            if (usuario.getSenha() == null) {
                statement.setInt(4, usuario.getId());
            } else {
                statement.setString(4, usuario.getSenha());
                statement.setInt(5, usuario.getId());
            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: usuário não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_usuario_login")) {
                throw new SQLException("Erro ao editar usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar usuário.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir usuário.");
            }
        }
    }

    @Override
    public List<Usuario> all() throws SQLException {
        List<Usuario> usuarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
             ResultSet result = statement.executeQuery();) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setLogin(result.getString("login"));

                usuarioList.add(usuario);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return usuarioList;
    }

    public void authenticate(Usuario usuario) throws SQLException, SecurityException {
        try (PreparedStatement statement = connection.prepareStatement(authenticateQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setId(result.getInt("id"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao autenticar usuário.");
        }
    }
}