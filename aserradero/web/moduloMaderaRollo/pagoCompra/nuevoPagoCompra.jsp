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
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo pago de compra</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos cuidadosamente</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoCompraController?action=insertar" method="post" id="formregistro">
                                <%
                                    if (!listaCuentaPago.isEmpty()) {
                                %>
                                <div class="form-group">
                                    <label class="control-label" for="fecha">Fecha:</label>
                                    <input type="date" class="form-control" name="fecha" required="" placeholder="AAAA/MM/DD"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Proveedor:</label>

                                        <select class="form-control" name="id_proveedor" id="id_proveedor" required="" title="Seleccione un proveedor" onblur="establecerMontoAPagar()">
                                            <option></option>
                                            <%
                                                for (CuentaPago cuenta : listaCuentaPago) {
                                                    out.print("<option value='" + cuenta.getId_proveedor() + "'>" + cuenta.getProveedor() + "</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Cuenta por pagar:</label>

                                        <select class="form-control" name="cuenta_por_pagar" id="cuenta_por_pagar" required="" disabled="">
                                            <option></option>
                                            <%
                                                for (CuentaPago cuenta : listaCuentaPago) {
                                                    out.print("<option value='" + cuenta.getCuenta_por_pagar() + "'>" + cuenta.getCuenta_por_pagar() + "</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Cuenta por cobrar (anticipo):</label>
                                        <select class="form-control" name="cuenta_por_cobrar" id="cuenta_por_cobrar" required="" disabled="">
                                            <option></option>
                                            <%
                                                for (CuentaPago cuenta : listaCuentaPago) {
                                                    out.print("<option value='" + cuenta.getCuenta_por_cobrar() + "'>" + cuenta.getCuenta_por_cobrar() + "</option>");
                                                }
                                            %>
                                        </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto a pagar:</label>
                                    <input type="number" class="form-control" step="0.01" name="monto_pago" id="monto_pago" min="0.00" max="" required="" onblur="calcularMontoPorPagar()">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto pendiente (por pagar):</label>
                                    <input type="number" class="form-control" step="0.01" name="monto_por_pagar" id="monto_por_pagar" min="0.00" max="" required="" readonly="">
                                </div>
                                <input type="hidden" step="0.01" name="monto_por_cobrar" id="monto_por_cobrar" min="0.00" max="" required="" readonly="">
                                <div class="form-group">
                                    <a href="/aserradero/PagoCompraController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar">
                                </div>
                                <%
                                } else {
                                    out.println("<p style='color:red;'>No hay compras pendientes por pagar.</p>");
                                %>
                                <div class="form-group">
                                    <a href="/aserradero/PagoCompraController?action=listar"><input type="button" class="btn btn-primary" value="Aceptar"/></a>
                                </div>
                                <%
                                    }
                                %>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
