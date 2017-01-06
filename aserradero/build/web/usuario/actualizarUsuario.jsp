<%-- 
    Document   : actualizarUsuario
    Created on : 15-sep-2016, 20:21:28
    Author     : lmarcoss
--%>

<%@page import="entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Usuario usuario = (Usuario) request.getAttribute("usuario");%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/UsuarioController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label for="id_empleado">Id empleado:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="id_empleado" value="<%= usuario.getId_empleado()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="6" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="nombre_usuario">Usuario:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="nombre_usuario" value="<%=usuario.getNombre_usuario()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="45" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="password">Contraseña</label></td>
                            <td style="padding-left: 10px;"><input type="password" name="contrasenia"  maxlength="20" required=""  placeholder="Escriba una nueva contraseña" title="Escriba una nueva contraseña para este usuario" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Email:</label></td>
                            <td style="padding-left: 10px;"><input type="email" name="email" value="<%=usuario.getEmail()%>" maxlength="50"/></td>
                        </tr>

                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/UsuarioController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <!--<td><input type="submit" value="Registrar" class="submit"/> </td>-->
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
