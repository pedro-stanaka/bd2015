package controller;

import dao.UsuarioDAO;
import dao.DAO;
import java.io.IOException;
import java.sql.Date;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);

                break;
            case "/usuario/read":
                dao = new UsuarioDAO();
                
                request.setAttribute("usuario", dao.read(Integer.parseInt(request.getParameter("id"))));
                
                dispatcher = request.getRequestDispatcher("/view/usuario/read.jsp");
                dispatcher.forward(request, response);
                
                break;
            case "/usuario/update":
                dao = new UsuarioDAO();

                request.setAttribute("usuario", dao.read(Integer.parseInt(request.getParameter("id"))));

                dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                dispatcher.forward(request, response);

                break;
            case "/usuario/delete":
                dao = new UsuarioDAO();

                dao.delete(Integer.parseInt(request.getParameter("id")));

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario":
                dao = new UsuarioDAO();

                request.setAttribute("usuarioList", dao.all());

                dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
                dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        Usuario usuario;

        switch (request.getServletPath()) {
            case "/usuario/create":
                dao = new UsuarioDAO();
                usuario = new Usuario();

                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setNome(request.getParameter("nome"));
                usuario.setNascimento(Date.valueOf(request.getParameter("nascimento")));

                dao.create(usuario);

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario/update":
                dao = new UsuarioDAO();
                usuario = new Usuario();

                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setNome(request.getParameter("nome"));
                usuario.setNascimento(Date.valueOf(request.getParameter("nascimento")));

                dao.update(usuario);

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
        }
    }
}