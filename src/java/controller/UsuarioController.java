package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {
        "/usuario/checkLogin",
        "/usuario/create",
        "/usuario/read",
        "/usuario/update",
        "/usuario/delete",
        "/usuario"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO<Usuario> dao;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);

                break;
            case "/usuario/read":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    Usuario usuario = dao.read(Integer.parseInt(request.getParameter("id")));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(usuario);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;
            case "/usuario/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    Usuario usuario = dao.read(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("usuario", usuario);

                    dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;
            case "/usuario/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    List<Usuario> usuarioList = dao.all();
                    request.setAttribute("usuarioList", usuarioList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
                dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO dao;
        Usuario usuario = new Usuario();
        HttpSession session = request.getSession();
        String nascimento;

        switch (request.getServletPath()) {
            case "/usuario/create": {
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setNome(request.getParameter("nome"));

                nascimento = request.getParameter("nascimento");


                try (DAOFactory daoFactory = new DAOFactory()) {

                    java.util.Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
                    usuario.setNascimento(new Date(dataNascimento.getTime()));

                    dao = daoFactory.getUsuarioDAO();

                    dao.create(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario");
                } catch (ClassNotFoundException | IOException | ParseException | SQLException ex) {
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario/create");
                }

                break;
            }
            case "/usuario/update": {
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setLogin(request.getParameter("login"));
                usuario.setNome(request.getParameter("nome"));

                nascimento = request.getParameter("nascimento");

                if (!request.getParameter("senha").isEmpty()) {
                    usuario.setSenha(request.getParameter("senha"));
                }

                try (DAOFactory daoFactory = new DAOFactory()) {
                    java.util.Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
                    usuario.setNascimento(new Date(dataNascimento.getTime()));

                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario");
                } catch (ClassNotFoundException | IOException | ParseException | SQLException ex) {
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario/update");
                }

                break;
            }
            case "/usuario/delete": {
                String[] usuarios = request.getParameterValues("delete");

                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String usuarioId : usuarios) {
                            dao.delete(Integer.parseInt(usuarioId));
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break; // end - delete
            }
            case "/usuario/checkLogin": {
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    usuario = dao.getByLogin(request.getParameter("login"));

                    Gson gson = new Gson();
                    Map<String, String> result = new HashMap<>();
                    if (usuario != null) {
                        result.put("status", "USADO");
                    } else {
                        result.put("status", "NAO_USADO");
                    }

                    String json = gson.toJson(result);
                    response.setContentType("application/json");
                    response.getOutputStream().print(json);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;
            }
        }
    }
}