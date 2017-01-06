<%--
    Document   : otrosgastos
    Created on : 26/09/2016, 02:42:29 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.OtroGasto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<OtroGasto> otrosgastos = (List<OtroGasto>) request.getAttribute("listaOtrosGasto");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Otros gastos</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/OtroGastoController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="fecha">Fecha</option>
                                <option value="nombre_gasto">Nombre de gasto</option>
                                <option value="monto">Monto</option>
                                <option value="observacion">Observación</option>
                                <option value="empleado">Registró</option>
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
                    <th>Fecha</th>
                    <th>Nombre de gasto</th>
                    <th>Monto</th>
                    <th>observación</th>
                    <th>Registró</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i = 0;
                    for (OtroGasto otrogasto : otrosgastos) {
                        out.print("<tr>"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + otrogasto.getFecha() + "</td>"
                                + "<td>" + otrogasto.getNombre_gasto() + "</td>"
                                + "<td>" + otrogasto.getMonto() + "</td>"
                                + "<td>" + otrogasto.getObservacion() + "</td>"
                                + "<td>" + otrogasto.getEmpleado() + "</td>"
                                + "<td><a href=\"/aserradero/OtroGastoController?action=modificar&id_gasto=" + otrogasto.getId_gasto() + "\">Modificar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/OtroGastoController?action=eliminar&id_gasto=" + otrogasto.getId_gasto() + "';};\">Eliminar</a></td>"
                                + "</tr>");
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Registrar otro gasto" onClick=" window.location.href = '/aserradero/OtroGastoController?action=nuevo'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
