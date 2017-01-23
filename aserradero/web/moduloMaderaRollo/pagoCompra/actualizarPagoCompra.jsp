<%--
    Document   : actualizarPagoCompra
    Created on : 19-nov-2016, 22:27:24
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaRollo.PagoCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PagoCompra pagoCompra = (PagoCompra) request.getAttribute("pagoCompra");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <script src="/aserradero/js/calcularMontoPorPagar.js"></script>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>                        
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">Actualización de datos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoCompraController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_pago" value="<%= pagoCompra.getId_pago()%>"  readonly="" required="">
                                <div class="form-group">
                                    <label class="control-label">Fecha</label>
                                    <input type="date" class="form-control" name="fecha" value="<%=pagoCompra.getFecha()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Proveedor</label>

                                        <select class="form-control" name="id_proveedor" required="">
                                            <option selected="" value='<%=pagoCompra.getId_proveedor()%>'><%=pagoCompra.getProveedor()%></option>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto total compra</label>
                                        <select class="form-control" name="cuenta_pendiente" id="cuenta_pendiente" required="" disabled="">
                                            <option selected="" value="<%=pagoCompra.getMonto_pago().add(pagoCompra.getMonto_por_pagar())%>"><%=pagoCompra.getMonto_pago().add(pagoCompra.getMonto_por_pagar())%></option>
                                        </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto a pagar:</label>
                                    <input type="number" class="form-control" step="0.01" name="monto_pago" id="monto_pago" value="<%=pagoCompra.getMonto_pago()%>" min="0.00" max="<%=pagoCompra.getMonto_pago().add(pagoCompra.getMonto_por_pagar())%>" required="" onblur="calcularMontoPorPagar()">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto pendiente (por pagar):</label>
                                    <input type="number" class="form-control" step="0.01" name="monto_por_pagar" value="<%=pagoCompra.getMonto_por_pagar()%>" id="monto_por_pagar" min="0.00" max="" required="" readonly="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Cuenta por cobrar:</label>
                                    <input type="number" class="form-control" step="0.01" name="monto_por_cobrar" value="<%=pagoCompra.getMonto_por_cobrar()%>" id="monto_por_pagar" min="0.00" max="" required="" readonly="">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PagoCompraController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                    <!--<td><input type="submit" value="Registrar" class="submit"/> -->
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
