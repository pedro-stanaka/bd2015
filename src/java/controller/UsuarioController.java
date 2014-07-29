package controller;

import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet(urlPatterns = {"/usuario/create",
                           "/usuario/read",
                           "/usuario/update",
                           "/usuario/delete",
                           "/usuario"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);

                break;
            case "/usuario/read":
            case "/usuario/update":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    Usuario usuario = (Usuario) dao.read(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("usuario", usuario);

                    dispatcher = request.getRequestDispatcher("/view" + request.getServletPath() + ".jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;
            case "/usuario/delete":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    List<Usuario> usuarioList = dao.all();
                    request.setAttribute("usuarioList", usuarioList);

                    dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao;
        Usuario usuario = new Usuario();

        switch (request.getServletPath()) {
            case "/usuario/create":
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setNome(request.getParameter("nome"));
                usuario.setNascimento(Date.valueOf(request.getParameter("nascimento")));

                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.create(usuario);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario/update":
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setLogin(request.getParameter("login"));
                usuario.setNome(request.getParameter("nome"));
                usuario.setNascimento(Date.valueOf(request.getParameter("nascimento")));

                if (!request.getParameter("senha").isEmpty()) {
                    usuario.setSenha(request.getParameter("senha"));
                }

                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario/delete":
                String[] usuarios = request.getParameterValues("delete");

                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (int i = 0; i < usuarios.length; ++i) {
                            dao.delete(Integer.parseInt(usuarios[i]));
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");
        }
    }
}