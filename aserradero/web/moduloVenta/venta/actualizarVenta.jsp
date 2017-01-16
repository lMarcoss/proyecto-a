<%--
    Document   : actualizarVenta
    Created on : 21-sep-2016, 21:48:48
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.venta.Venta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Venta venta = (Venta) request.getAttribute("venta");
    BigDecimal mayor = BigDecimal.valueOf(Double.valueOf("0"));
    Double monto = Double.valueOf(venta.getMonto().toString());
    Double pago = Double.valueOf(venta.getPago().toString());
    if (monto > pago) {
        mayor = venta.getMonto();
    } else if (pago > monto) {
        mayor = venta.getPago();
    } else if (monto >= pago) { // entra si las dos anteriores no cumplen
        mayor = venta.getMonto();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <!--<script src="/aserradero/js/venta/actualizarPagoVenta.js"></script>-->
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actualizar datos de ventas</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/VentaController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label>Fecha:</label>
                                        <input type="date" class="form-control" name="fecha" value="<%=venta.getFecha()%>" min="1920-01-01" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label>Id venta:</label>
                                        <input type="text" class="form-control" name="id_venta" value="<%=venta.getId_venta()%>" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label>Cliente</label>
                                        <select class="form-control" name="id_cliente" id="id_cliente">
                                            <option selected="" value="<%=venta.getId_cliente()%>"><%=venta.getCliente()%></option>
                                        </select>
                                    </div>
                                    <input type="hidden" name="id_empleado" id="id_empleado" value="<%=venta.getId_empleado()%>" readonly="">
                                    <div class="form-group">
                                        <label>Monto total venta: </label>
                                        <input type="number" class="form-control" step="0.01" name="monto" id="monto" value="<%=venta.getMonto()%>" readonly="">
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <input type="hidden" step="0.01" name="pago_anterior" id="pago_anterior" value="<%=venta.getPago()%>" readonly="">
                                    <div class="form-group">
                                        <label>Pago en efectivo:</label>
                                        <input type="number" class="form-control" name="pago" id="pago" step="0.01" value="<%=venta.getPago()%>" min="0" max="<%=mayor%>" required="" onclick="actualizarPagoVenta()">
                                    </div>
                                    <div class="form-group">
                                        <label>Tipo venta:</label>
                                        <select class="form-control" name="tipo_venta" id="tipo_venta">
                                            <option selected="" value="<%=venta.getTipo_venta()%>"><%=venta.getTipo_venta()%></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/Venta<%=venta.getTipo_venta()%>Controller?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success" value="Guardar"/>
                                    </div>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
        <div>

        </div><!--Fin Formulario de registro-->
    </body>
</html>
