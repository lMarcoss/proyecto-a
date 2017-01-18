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
            <form class="" action="/aserradero/generadorpdfticket" method="POST">
                <header style="text-align: center;">
                    <input type="hidden" name="encabezado" value="ASERRADERO ANTONIO CORTES HERNÁNDEZ">
                    <input type="hidden" name="encabezado1" value="COMPRA-VENTA DE PRODUCTOS FORESTALES">
                    <input type="hidden" name="encabezado2" value="MADERA EN ROLLO Y ASERRADA">
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
                        <!--Datos a enviar al controlador para imprimir el pdf-->
                        <input type="hidden" name="Cliente" value="<%= datosCliente.getCliente()%>">
                        <input type="hidden" name="Municipio" value="<%= datosCliente.getMunicipio()%>">
                        <input type="hidden" name="Direccion" value="<%= datosCliente.getDireccion()%>">
                        <input type="hidden" name="Estado" value="<%= datosCliente.getEstado()%>">
                        <input type="hidden" name="Localidad" value="<%= datosCliente.getLocalidad()%>">
                        <input type="hidden" name="Fecha" value="<%= datosCliente.getFecha()%>">
                        <input type="hidden" name="Id_venta" value="<%=id_venta%>">
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
                            int i=0;
                            for (VentaExtra venta : listaMaderaExtra) {                                
                                out.print("<tr>");
                                out.print("<input type=\"hidden\" name=\"tipo[]\" value=\""+venta.getTipo()+"\"/>");
                                out.print("<td>" + venta.getTipo() + ">");                                
                                if (tipo_ticket.equals("costo")) {
                                    out.print("<td>" + venta.getMonto() + "</td>");
                                    out.print("<input type=\"hidden\" name=\"costo[]\" value=\""+venta.getMonto()+"\"/>");
                                } else {
                                    out.print("<td></td>");
                                    out.print("<input type=\"hidden\" name=\"tipo[]\" value=\" \"/>");
                                }
                                out.print("<td>" + venta.getObservacion() + "</td>");
                                out.print("<input type=\"hidden\" name=\"observacion[]\" value=\""+venta.getObservacion()+"\"/>");
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
                <div class="form-group">
                    <button type="submit" name="button" class="btn btn-primary">Generar PDF</button>
                </div>
            </form>
        </div>
    </body>
</html>
