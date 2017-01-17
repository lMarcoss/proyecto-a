<%-- 
    Document   : listarBalanceCuenta
    Created on : 15/01/2017, 01:19:37 PM
    Author     : lmarcoss
--%>

<%@page import="entidades.cuenta.BalanceCuenta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<BalanceCuenta> listaBalance = (List<BalanceCuenta>) request.getAttribute("listaBalance");
    String cuenta_total = (String) request.getAttribute("cuenta_total");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Balance de cuenta</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <% if (!listaBalance.isEmpty()) {%>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Balance de cuenta</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"></h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="80%">
                                <thead>
                                    <tr>
                                        <th style='text-align: left;'></th>
                                        <th style='text-align: left;'></th>
                                        <th style='text-align: left;'></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (BalanceCuenta balance : listaBalance) {
                                            out.print("<tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Cuenta inicial</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getCuenta_inicial() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Bienes inmuebles</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getBienes_inmuebles() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Inventario madera en rollo</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getInventario_m_rollo() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Inventario madera aserrada</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getInventario_m_aserrada() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Pagos venta</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getVenta_en_efectivo() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Cuenta por cobrar</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getCuenta_por_cobrar() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Anticipo de clientes</th>"
                                                    + "<td style='text-align: right;'> + " + balance.getAnticipo_cliente()+ "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Anticipo a proveedores</th>"
                                                    + "<td style='text-align: right;'> - " + balance.getAnticipo_proveedor()+ "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Préstamo</th>"
                                                    + "<td style='text-align: right;'> - " + balance.getPrestamo() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Pago empleado</th>"
                                                    + "<td style='text-align: right;'> - " + balance.getPago_empleado() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Gastos</th>"
                                                    + "<td style='text-align: right;'> - " + balance.getGastos() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Cuenta por pagar</th>"
                                                    + "<td style='text-align: right;'> - " + balance.getCuenta_por_pagar() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Pagos compra</th>"
                                                    + "<td style='text-align: right;'> - " + balance.getPagos_compra() + "</td>"
                                                    + "</tr>");
                                        }
                                        out.print("<tr>"
                                                    + "<td></td>"
                                                    + "<th style='text-align: left;'>Total</th>"
                                                    + "<td style='text-align: right;'><b>" + cuenta_total + "</b></td>"
                                                    + "</tr>");
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
        <%} else {%>
        <h3 style="color: red;">No hay cuentas</h3>
        <%}%>
    </body>
</html>