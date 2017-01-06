<%-- 
    Document   : nuevoUsuario
    Created on : 11-sep-2016, 18:15:05
    Author     : lmarcoss
--%>


<%@page import="entidades.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%List <Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/UsuarioController?action=nuevo" method="post" id="formregistro">
                <h3>Registrar usuario</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label>Empleado:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_empleado" required="">
                                    <option></option>
                                    <%
                                        for (Empleado empleado : empleados) {
                                            out.print("<option value='"+empleado.getId_empleado()+"'>"+empleado.getEmpleado()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="nombre_usuario">Nombre usuario:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="nombre_usuario" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="45" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="password">Contraseña</label></td>
                            <td style="padding-left: 10px;"><input type="password" name="contrasenia" maxlength="20" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Email:</label></td>
                            <td style="padding-left: 10px;"><input type="email" name="email" maxlength="50"/></td>
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
