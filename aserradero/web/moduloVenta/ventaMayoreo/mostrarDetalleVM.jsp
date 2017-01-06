<%-- 
    Document   : mostrarDetalleVM
    Created on : 26/12/2016, 10:27:56 PM
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaMayoreo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<VentaMayoreo> detalles = (List<VentaMayoreo>) request.getAttribute("detalles");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas </title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"

               <!-- ************************* Resultado Consulta-->
               <div>
            <h3>Detalles de la venta</h3>
            <table class="table-condensed">
                <tr>
                    <th>N°</th>
                    <th>Madera</th>
                    <th>Piezas</th>
                    <th>Volumen</th>
                    <th>Costo</th>
                    <th>Tipo_madera</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i = 0;
                    for (VentaMayoreo detalle : detalles) {
                        out.print("<tr>"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + detalle.getId_madera() + "</td>"
                                + "<td>" + detalle.getNum_piezas() + "</td>"
                                + "<td>" + detalle.getVolumen() + "</td>"
                                + "<td>" + detalle.getMonto() + "</td>"
                                + "<td>" + detalle.getTipo_madera() + "</td>"
                                + "<td><a href=\"/aserradero/VentaMayoreoController?action=modificar&id_venta=" + detalle.getId_venta() + "&id_madera=" + detalle.getId_madera() + "&tipo_madera=" + detalle.getTipo_madera() + "\">Modificar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaMayoreoController?action=eliminar&id_venta=" + detalle.getId_venta() + "&id_madera=" + detalle.getId_madera() + "&tipo_madera=" + detalle.getTipo_madera() + "';};\">Eliminar</a></td>"
                                + "</tr>");
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Regresar" onClick=" window.location.href = '/aserradero/VentaMayoreoController?action=listar'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
