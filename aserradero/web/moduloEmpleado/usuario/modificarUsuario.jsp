<%-- 
    Document   : actualizarUsuario
    Created on : 15-sep-2016, 20:21:28
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession(false);
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%if(((String)sesion.getAttribute("rol")).equals("Administrador")){%>
            <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%}else{%>
            <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/UsuarioController?action=actualizar" method="post" id="formregistro">
                <h3>Modificar contraseña de usuario</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label for="id_empleado">Empleado:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_empleado" required="">
                                    <option value="<%= usuario.getId_empleado()%>"><%= usuario.getEmpleado()%></option>
                                </select>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="nombre_usuario">Nombre de usuario:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="nombre_usuario" value="<%=usuario.getNombre_usuario()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="45" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="password">Contraseña</label></td>
                            <td style="padding-left: 10px;"><input type="password" name="contrasenia"  maxlength="20" required=""  placeholder="Escriba una nueva contraseña" title="Click en cancelar para no cambiar contraseña" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Email:</label></td>
                            <td style="padding-left: 10px;"><input type="email" name="email" value="<%=usuario.getEmail()%>" maxlength="50"/></td>
                        </tr>

                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/UsuarioController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
