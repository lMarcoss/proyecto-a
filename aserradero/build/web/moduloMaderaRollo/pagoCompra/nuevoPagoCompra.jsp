<%-- 
    Document   : nuevoPagoCompra
    Created on : 19-nov-2016, 22:27:07
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaRollo.CuentaPago"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<CuentaPago> listaCuentaPago = (List<CuentaPago>) request.getAttribute("listaCuentaPago");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <script src="/aserradero/js/selectorMontoPorPagar.js"></script>
        <title>Nuevo pago</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoCompraController?action=insertar" method="post" id="formregistro">
                <h3>Registrar pago</h3>
                <fieldset id="user-details">
                    <table>
                        <%
                            if (!listaCuentaPago.isEmpty()) {
                        %>
                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" placeholder="AAAA/MM/DD"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Proveedor:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_proveedor" id="id_proveedor" required="" title="Seleccione un proveedor" onblur="establecerMontoAPagar()">
                                    <option></option>
                                    <%
                                        for (CuentaPago cuenta : listaCuentaPago) {
                                            out.print("<option value='" + cuenta.getId_proveedor() + "'>" + cuenta.getProveedor() + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Cuenta por pagar:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="cuenta_por_pagar" id="cuenta_por_pagar" required="" disabled="">
                                    <option></option>
                                    <%
                                        for (CuentaPago cuenta : listaCuentaPago) {
                                            out.print("<option value='" + cuenta.getCuenta_por_pagar() + "'>" + cuenta.getCuenta_por_pagar() + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Cuenta por cobrar (anticipo):</label></td>
                            <td style="padding-left: 10px;">
                                <select name="cuenta_por_cobrar" id="cuenta_por_cobrar" required="" disabled="">
                                    <option></option>
                                    <%
                                        for (CuentaPago cuenta : listaCuentaPago) {
                                            out.print("<option value='" + cuenta.getCuenta_por_cobrar() + "'>" + cuenta.getCuenta_por_cobrar() + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto a pagar:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_pago" id="monto_pago" min="0.00" max="" required="" onblur="calcularMontoPorPagar()"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto pendiente (por pagar):</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_por_pagar" id="monto_por_pagar" min="0.00" max="" required="" readonly=""></td>
                        </tr>
                        <input type="hidden" step="0.01" name="monto_por_cobrar" id="monto_por_cobrar" min="0.00" max="" required="" readonly="">
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoCompraController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"></td>
                        </tr>
                        <%
                        } else {
                            out.println("<p style='color:red;'>No hay compras pendientes por pagar.</p>");
                        %>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoCompraController?action=listar"><input type="button" value="Aceptar"/></a> </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
