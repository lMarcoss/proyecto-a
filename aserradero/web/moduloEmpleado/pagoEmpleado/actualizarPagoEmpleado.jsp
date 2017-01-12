<%-- 
    Document   : actualizarPagoEmpleado
    Created on : 30/09/2016, 08:48:10 AM
    Author     : Marcos
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.empleado.PagoEmpleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    PagoEmpleado pagoEmpleado = (PagoEmpleado) request.getAttribute("pagoEmpleado");
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
            <form action="/aserradero/PagoEmpleadoController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos</h3>
                <fieldset id="user-details">

                    <table>
                        <input type="hidden" name="id_pago_empleado" value="<%=pagoEmpleado.getId_pago_empleado()%>" readonly="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=pagoEmpleado.getFecha()%>" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Empleado:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_empleado">
                                    <option value="<%= pagoEmpleado.getId_empleado()%>"><%= pagoEmpleado.getEmpleado()%></option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;">
                                <input type="number" name="monto" value="<%=pagoEmpleado.getMonto()%>" step="0.01" min="0.01" max="99999999.99" required="">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Observacion:</label></td>
                            <td style="padding-left: 10px;">
                                <input type="text" name="observacion" value="<%=pagoEmpleado.getObservacion()%>" maxlength="249">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoEmpleadoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
