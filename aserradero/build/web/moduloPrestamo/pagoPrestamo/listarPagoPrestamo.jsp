<%-- 
    Document   : listarPagoPrestamo
    Created on : 27-nov-2016, 22:22:17
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.prestamo.PagoPrestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<PagoPrestamo> listaPagoPrestamo = (List<PagoPrestamo>) request.getAttribute("listaPagoPrestamo");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pago préstamo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/PagoPrestamoController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="fecha">Fecha</option>
                                <option value="prestador">Prestador</option>
                                <option value="monto_pagado">Monto pagado</option>
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
                    <th>Prestador</th>
                    <th>Monto pagado</th>
                    <th>Registró</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i = 0;
                    for (PagoPrestamo pago : listaPagoPrestamo) {
                        out.print("<tr>"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + pago.getFecha() + "</td>"
                                + "<td>" + pago.getPrestador() + "</td>"
                                + "<td>" + pago.getMonto_pago() + "</td>"
                                + "<td>" + pago.getEmpleado() + "</td>"
                                + "<td><a href=\"/aserradero/PagoPrestamoController?action=modificar&id_pago=" + pago.getId_pago() + "&id_prestamo=" + pago.getId_prestamo() + "\">Modificar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoPrestamoController?action=eliminar&id_pago=" + pago.getId_pago() + "';};\">Eliminar</a></td>"
                                + "</tr>");
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Registrar pago" onClick=" window.location.href = '/aserradero/PagoPrestamoController?action=nuevo'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
