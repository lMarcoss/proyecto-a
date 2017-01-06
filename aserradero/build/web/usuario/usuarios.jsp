<%-- 
    Document   : usuarios
    Created on : 15-sep-2016, 19:23:13
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Usuarios</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
        
        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/UsuarioController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                            <option value="id_empleado">Id empleado</option>
                            <option value="nombre_usuario">Usuario</option>
                            <option value="email">Email</option>
                        </select>
                        </td>
                        <td><input type="text" name="dato" placeholder="Escriba su búsqueda"></td>
                        <td colspan="2"><input type="submit" value="Buscar"></td>
                    </tr>
                </table>
            </form>
        </div> <!-- Fin opción de búsqueda-->
        
        <!-- ************************* Resultado Consulta-->
        <div>
            <table class="table-condensed">
                    <tr>
                        <th>N°</th>
                        <th>Id empleado</th>
                        <th>Usuario</th>
                        <th>Contraseña</th>
                        <th>Email</th>
                    </tr>
                    <%
                        int i=0;
                        for (Usuario usuario : usuarios) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                    +"<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona="+usuario.getId_empleado()+"\">"+usuario.getId_empleado()+"</a></td>"
                                    +"<td>"+usuario.getNombre_usuario()+"</td>"
                                    +"<td>"+usuario.getContrasenia()+"</td>"
                                    +"<td>"+usuario.getEmail()+"</td>"
                                +"<td><a href=\"/aserradero/UsuarioController?action=modificar&nombre_usuario="+usuario.getNombre_usuario()+"\">Actualizar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/UsuarioController?action=eliminar&nombre_usuario="+usuario.getNombre_usuario()+"';};\">Eliminar</a></td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
            </table>
            <div>
                <input type="button" value="Agregar usuario" onClick=" window.location.href='/aserradero/UsuarioController?action=nuevo' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
