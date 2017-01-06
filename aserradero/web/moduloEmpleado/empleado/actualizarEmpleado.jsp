<%-- 
    Document   : actualizarEmpleado
    Created on : 24-sep-2016, 22:13:01
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Empleado empleado = (Empleado) request.getAttribute("empleado");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/EmpleadoController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar empleado</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"></td>
                            <td style="padding-left: 10px;">
                                <input type="hidden" name="id_empleado" value="<%=empleado.getId_empleado()%>" readonly="">
                                <input type="hidden" name="id_persona" value="<%=empleado.getId_persona()%>" readonly="">
                                <input type="hidden" name="id_jefe" value="<%=empleado.getId_jefe()%>" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Nombre empleado:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="empleado" value="<%=empleado.getEmpleado()%>" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Rol:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="rol" required="">
                                    <%if (empleado.getRol().equals("Administrador")) {%>
                                    <option value="Administrador" selected="">Administrador</option>
                                    <%} else if (empleado.getRol().equals("Empleado")) {%>
                                    <option value="Empleado" selected="">Empleado</option>
                                    <option value="Chofer">Chofer</option>
                                    <option value="Vendedor">Vendedor</option>
                                    <%} else if (empleado.getRol().equals("Chofer")) {%>
                                    <option value="Empleado">Empleado</option>
                                    <option value="Chofer" selected="">Chofer</option>
                                    <option value="Vendedor">Vendedor</option>
                                    <%} else if (empleado.getRol().equals("Vendedor")) {%>
                                    <option value="Empleado">Empleado</option>
                                    <option value="Chofer">Chofer</option>
                                    <option value="Vendedor" selected="">Vendedor</option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Estaus</label></td>
                            <td>
                                <select name="estatus" required="">
                                    <%if (empleado.getRol().equals("Administrador")) { %>
                                    <option value="Activo" selected="">Activo</option>
                                    <%} else if (empleado.getEstatus().equals("Activo")) { %>
                                    <option value="Activo" selected="">Activo</option>
                                    <option value="Inactivo">Inactivo</option>
                                    <%} else {%>
                                    <option value="Activo">Activo</option>
                                    <option value="Inactivo" selected="">Inactivo</option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Jefe:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="empleado" value="<%=empleado.getJefe()%>" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/EmpleadoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
