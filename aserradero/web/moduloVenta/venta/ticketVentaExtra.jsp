<%-- 
    Document   : ticketVentaExtra
    Created on : 02-oct-2016, 15:56:54
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.venta.VentaExtra"%>
<%@page import="ticketVenta.DatosClienteTicket"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DatosClienteTicket datosCliente = (DatosClienteTicket) request.getAttribute("datosCliente");
    List<VentaExtra> listaMaderaExtra = (List<VentaExtra>) request.getAttribute("listaMaderaExtra");
    BigDecimal costo_venta = (BigDecimal) request.getAttribute("costo_venta");
    String tipo_ticket = (String) request.getAttribute("tipo_ticket");
    String id_venta = (String) request.getAttribute("id_venta");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket venta extra</title>
        <link rel="stylesheet" href="/aserradero/dist/css/bootstrap.css">
    </head>
    <body>
        <div class="container">
            <header style="text-align: center;">
                <p>ASERRADERO ANTONIO CORTES HERNÁNDEZ</p>
                <p>COMPRA-VENTA DE PRODUCTOS FORESTALES<p>
                <p>MADERA EN ROLLO Y ASERRADA</p>
            </header>
            <div>
                <table style="width: 100%;">
                    <tr>
                        <td>Cliente: <%= datosCliente.getCliente()%></td>
                        <td>Municipio: <%= datosCliente.getMunicipio()%></td>
                    </tr>
                    <tr>
                        <td>Dirección: <%= datosCliente.getDireccion()%></td>
                        <td>Estado: <%= datosCliente.getEstado()%></td>
                    </tr>
                    <tr>
                        <td>Localidad: <%= datosCliente.getLocalidad()%></td>
                        <td>Fecha: <%= datosCliente.getFecha()%></td>
                    </tr>
                    <tr>
                        <td>Id_venta: <%= id_venta%></td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <br>
            <div class="table-responsive" style="width: 50%;">
                <table class="table table-hover">
                    <tr>
                        <th>Tipo</th>
                        <th>monto</th>
                        <th>observacion</th>
                    </tr>
                    <%
                        for (VentaExtra venta : listaMaderaExtra) {
                            out.print("<tr>");
                            out.print("<td>" + venta.getTipo() + "</td>");
                            if (tipo_ticket.equals("costo")) {
                                out.print("<td>" + venta.getMonto() + "</td>");
                            } else {
                                out.print("<td></td>");
                            }
                            out.print("<td>" + venta.getObservacion() + "</td>");
                            out.print("</tr>");
                        }
                    %>
                    <tr>
                        <td><b>Total</b></td>
                        <%
                            if (tipo_ticket.equals("costo")) {
                                out.print("<td><b>" + costo_venta + "</b></td>");
                            } else {
                                out.print("<td></td>");
                            }
                        %>
                        <td></td>
                    </tr>
                </table>      
            </div>
        </div>
    </body>
</html>
