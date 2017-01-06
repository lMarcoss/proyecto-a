<%-- 
    Document   : detallePagoCompra
    Created on : 4/01/2017, 05:12:52 PM
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaRollo.PagoCompra"%>
<%@page import="java.util.List"%>
<%@page import="entidades.maderaRollo.EntradaMaderaRollo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<EntradaMaderaRollo> listaEntrada = (List<EntradaMaderaRollo>) request.getAttribute("listaEntrada");
    EntradaMaderaRollo totalEntrada = (EntradaMaderaRollo) request.getAttribute("totalEntrada");
    PagoCompra pago = (PagoCompra) request.getAttribute("pago");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle pago compra</title>
        <link rel="stylesheet" href="/aserradero/dist/css/bootstrap.css">
        <style>
            #cabecera th{
                text-align: right;
            }
            #cuerpo_tabla td{
                text-align: right;
            }
        </style>
    </head>
    <body>
    <center>
        <div style="width: 90%;">
            <h2>Entrada de madera</h2>
            <table class="table-condensed table table-hover">
                <tr id="cabecera">
                    <th>N°</th>
                    <th>Fecha</th>
                    <th>Chofer</th>
                    <th>Pzas. primario</th>
                    <th>Volumen</th>
                    <th>Pzas. secundario</th>
                    <th>Volumen</th>
                    <th>Pzas. terciario</th>
                    <th>Volumen</th>
                    <th>Pzas. total</th>
                    <th>Volumen total</th>
                    <th>Costo total</th>
                </tr>
                <%
                    int i = 0;
                    for (EntradaMaderaRollo entrada : listaEntrada) {
                        out.print("<tr id=\"cuerpo_tabla\">"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + entrada.getFecha() + "</td>"
                                + "<td>" + entrada.getChofer() + "</td>"
                                + "<td>" + entrada.getNum_pieza_primario() + "</td>"
                                + "<td>" + entrada.getVolumen_primario() + "</td>"
                                + "<td>" + entrada.getNum_pieza_secundario() + "</td>"
                                + "<td>" + entrada.getVolumen_secundario() + "</td>"
                                + "<td>" + entrada.getNum_pieza_terciario() + "</td>"
                                + "<td>" + entrada.getVolumen_terciario() + "</td>"
                                + "<td>" + entrada.getNum_pieza_total() + "</td>"
                                + "<td>" + entrada.getVolumen_total() + "</td>"
                                + "<td>" + entrada.getCosto_total()+ "</td>"
                                + "</tr>");
                        i++;
                    }
                    if(totalEntrada != null){
                        out.print("<tr id=\"cuerpo_tabla\">"
                            + "<td></td>"
                            + "<td></td>"
                            + "<td><b>Total</b></td>"
                            + "<td><b>" + totalEntrada.getNum_pieza_primario() + "</b></td>"
                            + "<td><b>" + totalEntrada.getVolumen_primario() + "</b></td>"
                            + "<td><b>" + totalEntrada.getNum_pieza_secundario() + "</b></td>"
                            + "<td><b>" + totalEntrada.getVolumen_secundario() + "</b></td>"
                            + "<td><b>" + totalEntrada.getNum_pieza_terciario() + "</b></td>"
                            + "<td><b>" + totalEntrada.getVolumen_terciario() + "</b></td>"
                            + "<td><b>" + totalEntrada.getNum_pieza_total() + "</b></td>"
                            + "<td><b>" + totalEntrada.getVolumen_total() + "</b></td>"
                            + "<td><b>" + totalEntrada.getCosto_total()+ "</b></td>"
                            + "</tr>");
                    }
                %>
            </table>

        </div>
        <div style="text-align: left; width: 90%;">
            <h3>Datos del pago</h3>
            <table class="table-condensed table table-hover" style="text-align: left; width: 50%;">
                <tr>
                    <th>Fecha: </th>
                    <td><%=pago.getFecha()%></td>
                </tr>
                <tr>
                    <th>Proveedor: </th>
                    <td><%=pago.getProveedor()%></td>
                </tr>
                <tr>
                    <th>Monto pago: </th>
                    <td><%=pago.getMonto_pago()%></td>
                </tr>
                <tr>
                    <th>Cuenta por pagar: </th>
                    <td><%=pago.getMonto_por_pagar()%></td>
                </tr>
                <tr>
                    <th>Cuenta por cobrar: </th>
                    <td><%=pago.getMonto_por_cobrar()%></td>
                </tr>
            </table>
        </div>
    </center>
</body>
</html>
