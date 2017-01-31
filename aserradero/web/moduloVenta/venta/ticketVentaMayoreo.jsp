<%--
    Document   : ticketVentaMayoreo
    Created on : 02-oct-2016, 15:56:25
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.venta.VentaMayoreo"%>
<%@page import="ticketVenta.DatosClienteTicket"%>
<%@page import="ticketVenta.Madera"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DatosClienteTicket datosCliente = (DatosClienteTicket) request.getAttribute("datosCliente");
    //listas
    List<Madera> listaMadera = (List<Madera>) request.getAttribute("listaMadera");
    List<Madera> listaAmarre = (List<Madera>) request.getAttribute("listaAmarre");
    List<VentaMayoreo> listaMaderaMayoreo = (List<VentaMayoreo>) request.getAttribute("listaMaderaMayoreo");
    //Costos
    BigDecimal costo_madera = (BigDecimal) request.getAttribute("costo_madera");
    BigDecimal costo_amarre = (BigDecimal) request.getAttribute("costo_amarre");
    BigDecimal costo_venta = (BigDecimal) request.getAttribute("costo_venta");
    //Volumen
    BigDecimal volumen_madera = (BigDecimal) request.getAttribute("volumen_madera");
    BigDecimal volumen_amarre = (BigDecimal) request.getAttribute("volumen_amarre");
    BigDecimal volumen_venta = (BigDecimal) request.getAttribute("volumen_venta");
    String id_venta = (String) request.getAttribute("id_venta");
    String tipo_ticket = (String) request.getAttribute("tipo_ticket");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket venta por mayoreo</title>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/dist/css/bootstrap.css">
        <style>
            tr th{
                text-align: right;
            }
        </style>
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
                        <% out.print("<input type='hidden' name='cliente' id='cliente' value='"+datosCliente.getCliente()+"' />"); %>
                        <td>Municipio: <%= datosCliente.getMunicipio()%></td>
                        <% out.print("<input type='hidden' name='municipio' id='municipio' value='"+datosCliente.getMunicipio()+"' />");%>
                    </tr>
                    <tr>
                        <td>Dirección: <%= datosCliente.getDireccion()%></td>
                        <% out.print("<input type='hidden' name='direccion' id='direccion' value='"+datosCliente.getDireccion()+"' />");%>
                        <td>Estado: <%= datosCliente.getEstado()%></td>
                        <% out.print("<input type='hidden' name='estado' id='estado' value='"+datosCliente.getEstado()+"' />");%>
                    </tr>
                    <tr>
                        <td>Localidad: <%= datosCliente.getLocalidad()%></td>
                        <% out.print("<input type='hidden' name='localidad' id='localidad' value='"+datosCliente.getLocalidad()+"' />");%>
                        <td>Fecha: <%= datosCliente.getFecha()%></td>
                        <% out.print("<input type='hidden' name='fecha' id='fecha' value='"+datosCliente.getFecha()+"' />");%>
                    </tr>
                    <tr>
                        <td>Id_venta: <%= id_venta%></td>
                        <% out.print("<input type='hidden' name='id_venta' id='id_venta' value='"+datosCliente.getId_venta()+"' />");%>
                        <td></td>
                    </tr>
                </table>
            </div>
            <br>
            <div class="table-responsive">
                <table class="table table-hover" style="text-align: right;">
                    <tr>
                        <th>Madera</th>
                        <th>Grueso</th>
                        <th>Ancho</th>
                        <th>Largo</th>
                        <th>Volumen</th>
                        <th>Num piezas</th>
                        <th>Costo volumen</th>
                        <th>Volumen total</th>
                        <th>Total</th>
                    </tr>
                    <%
                        for (Madera madera : listaMadera) {
                            out.print("<tr>");
                            out.print("<td>" + madera.getId_madera() + "</td>");
                            out.print("<input type='hidden' name='id_madera' id='id_madera' value='"+madera.getId_madera()+"' />");
                            out.print("<td>" + madera.getGrueso_f() + "</td>");
                            out.print("<td>" + madera.getAncho_f() + "</td>");
                            out.print("<td>" + madera.getLargo_f() + "</td>");
                            out.print("<td>" + madera.getVolumen_unitario() + "</td>");
                            out.print("<td>" + madera.getNum_piezas() + "</td>");
                            if (tipo_ticket.equals("costo")) {
                                out.print("<td>" + madera.getCosto_volumen() + "</td>");
                            } else {
                                out.print("<td></td>");
                            }
                            out.print("<td>" + madera.getVolumen_total() + "</td>");
                            if (tipo_ticket.equals("costo")) {
                                out.print("<td>" + madera.getCosto_total() + "</td>");
                            } else {
                                out.print("<td></td>");
                            }
                            out.print("</tr>");
                        }
                        out.print("<tr>"
                                + "<td colspan='6'></td>"
                                + "<td><b>subtotal (Madera): </b></td>");
                        out.print("<td><b>" + volumen_madera + "</b></td>");
                        if (tipo_ticket.equals("costo")) {
                            out.print("<td><b>" + costo_madera + "</b></td>");
                        } else {
                            out.print("<td></td>");
                        }
                        out.print("</tr>");
                    %>
                    <%
                        if (!listaAmarre.isEmpty()) {
                            out.print("<tr>");
                            out.print("<td colspan='10'><center><b>Amarre</b></center></td>");
                            out.print("</tr>");
                            for (Madera madera : listaAmarre) {
                                out.print("<tr>");
                                out.print("<td>" + madera.getId_madera() + "</td>");
                                out.print("<td>" + madera.getGrueso_f() + "</td>");
                                out.print("<td>" + madera.getAncho_f() + "</td>");
                                out.print("<td>" + madera.getLargo_f() + "</td>");
                                out.print("<td>" + madera.getVolumen_unitario() + "</td>");
                                out.print("<td>" + madera.getNum_piezas() + "</td>");
                                if (tipo_ticket.equals("costo")) {
                                    out.print("<td>" + madera.getCosto_volumen() + "</td>");
                                } else {
                                    out.print("<td></td>");
                                }
                                out.print("<td>" + madera.getVolumen_total() + "</td>");
                                if (tipo_ticket.equals("costo")) {
                                    out.print("<td>" + madera.getCosto_total() + "</td>");
                                } else {
                                    out.print("<td></td>");
                                }
                                out.print("</tr>");
                            }
                            out.print("<tr>"
                                    + "<td colspan='6'></td>"
                                    + "<td><b>subtotal (Amarre): </b></td>");
                            out.print("<td><b>" + volumen_amarre + "</b></td>");
                            if (tipo_ticket.equals("costo")) {
                                out.print("<td><b>" + costo_amarre + "</b></td>");
                            } else {
                                out.print("<td></td>");
                            }
                            out.print("</tr>");
                        }
                    %>
                    <tr>
                        <td colspan="6"></td>
                        <td><b>Total:</b></td>
                        <td><b><%=volumen_venta%></b></td>
                        <%
                            if (tipo_ticket.equals("costo")) {
                                out.print("<td><b>" + costo_venta + "</b></td>");
                            } else {
                                out.print("<td></td>");
                            }
                        %>
                    </tr>
                </table>
                <%
                    if (!listaMadera.isEmpty()) {
                %>
                <div class="table-responsive" style="width: 50%;">
                    <table class="table table-hover" style="text-align: right;">
                        <%
                            out.print("<tr>");
                            out.print("<td colspan='4'><center><b>Total madera por clasificación</b></center></td>");
                            out.print("</tr>");
                        %>
                        <tr>
                            <th>Madera</th>
                            <th>Num. piezas</th>
                            <th>Volumen total</th>
                            <th>Costo total</th>
                        </tr>
                        <%
                            // Lista de madera por clasificación
                            for (VentaMayoreo madera : listaMaderaMayoreo) {
                                out.print("<tr>");
                                out.print("<td>" + madera.getId_madera() + "</td>");
                                out.print("<td>" + madera.getNum_piezas() + "</td>");
                                out.print("<td>" + madera.getVolumen() + "</td>");
                                if (tipo_ticket.equals("costo")) {
                                    out.print("<td>" + madera.getMonto() + "</td>");
                                } else {
                                    out.print("<td></td>");
                                }
                                out.print("</tr>");
                            }
                        %>
                        <tr>
                            <td></td>
                            <td><b>Total</b></td>
                            <td><b><%= volumen_venta%></b></td>
                            <%if (tipo_ticket.equals("costo")) {%>
                            <td><b><%= costo_venta%></b></td>
                            <%} else {%>
                            <td></td>
                            <%}%>
                        </tr>
                    </table>
                </div>
                <%}%>
            </div>
            <div class="form-group pull-right">
                <button type="button" id="ver_pdf_VM" class="btn btn-primary">Ver pdf</button>
                <button type="button" id="btn-regresar-VM" class="btn btn-warning">Regresar</button>
            </div>
        </div>
    </body>
</html>
