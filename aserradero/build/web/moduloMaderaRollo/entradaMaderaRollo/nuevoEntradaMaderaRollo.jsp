<%--
    Document   : nuevaEntradaMaderaRollo
    Created on : 26/09/2016, 06:08:43 PM
    Author     : rcortes
--%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.registros.Proveedor"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    List<Empleado> choferes = (List<Empleado>) request.getAttribute("choferes");
    Date fechaActual = (Date) request.getAttribute("fechaActual");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <form action="/aserradero/EntradaMaderaRolloController?action=insertar" method="POST">
            <h3>Agregar nueva entrada de madera</h3>
            <fieldset id="user-details">
                <table>
                    <tr>
                        <td style="padding-left: 10px;"><label for="fecha">Fecha</label></td>
                        <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=fechaActual%>"/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Proveedor</label></td>
                        <td style="padding-left: 10px;">
                            <select name="id_proveedor" required="" title="Si no existe el proveedor que busca, primero agreguelo en la lista de proveedores">
                                <option></option>
                                <%
                                    for (Proveedor proveedor : proveedores) {
                                        out.print("<option value='" + proveedor.getId_proveedor() + "'>" + proveedor.getProveedor() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Chofer</label></td>
                        <td style="padding-left: 10px;">
                            <select name="id_chofer" required="" title="Si no existe el empleado que busca, primero agreguelo en la lista de empleados">
                                <option></option>
                                <%
                                    for (Empleado chofer : choferes) {
                                        out.print("<option value='" + chofer.getId_empleado() + "'>" + chofer.getEmpleado() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Num. piezas primario</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="num_pieza_primario" min="0" max="999" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Volumen primario</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="volumen_primario" id="volumen" step=".001" min="0.000" max="99999.999"  required="" /></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Num. piezas secundario</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="num_pieza_secundario" min="0" max="999" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Volumen secundario</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="volumen_secundario" id="volumen" step=".001" min="0.000" max="99999.999"  required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Num. piezas terciario</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="num_pieza_terciario" min="0" max="999" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Volumen terciario</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="volumen_terciario" id="volumen" step=".001" min="0.000" max="99999.999"  required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><a href="/aserradero/EntradaMaderaRolloController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                        <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div><!--Fin Formulario de registro-->
</body>
</html>
