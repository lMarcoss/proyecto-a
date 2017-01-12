<%-- 
    Document   : listarPagoCompra
    Created on : 19-nov-2016, 22:22:17
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.maderaRollo.PagoCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<PagoCompra> listaPagoCompra = (List<PagoCompra>) request.getAttribute("listaPagoCompra");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pago compra</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <br>
        <br>
        <br>
        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/PagoCompraController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="fecha">Fecha</option>
                                <option value="proveedor">Proveedor</option>
                                <option value="monto_pago">Monto pagado</option>
                                <option value="monto_por_pagar">Cuenta por pagar</option>
                                <option value="monto_por_cobrar">Cuenta por cobrar</option>
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
                    <th>Proveedor</th>
                    <th>Monto pago</th>
                    <th>Cuenta por pagar</th>
                    <th>Cuenta por cobrar</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i = 0;
                    for (PagoCompra pago : listaPagoCompra) {
                        out.print("<tr>"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + pago.getFecha() + "</td>"
                                + "<td>" + pago.getProveedor() + "</td>"
                                + "<td>" + pago.getMonto_pago() + "</td>"
                                + "<td>" + pago.getMonto_por_pagar() + "</td>"
                                + "<td>" + pago.getMonto_por_cobrar()+ "</td>"
                                + "<td><a href=\"/aserradero/PagoCompraController?action=detalle&id_pago=" + pago.getId_pago() + "&id_proveedor="+pago.getId_proveedor()+"\">Detalle</a></td>"
                                + "<td><a href=\"/aserradero/PagoCompraController?action=modificar&id_pago=" + pago.getId_pago() + "\">Modificar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoCompraController?action=eliminar&id_pago=" + pago.getId_pago() + "';};\">Eliminar</a></td>"
                                + "</tr>");
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Registrar pago" onClick=" window.location.href = '/aserradero/PagoCompraController?action=nuevo'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
