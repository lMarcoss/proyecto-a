<%--
    Document   : actualizarPagoPrestamo
    Created on : 27-nov-2016, 22:27:24
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.prestamo.PagoPrestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PagoPrestamo pagoPrestamo = (PagoPrestamo) request.getAttribute("pagoPrestamo");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <script src="/aserradero/js/actualizarPagoPrestamo.js"></script>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actualización de datos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoPrestamoController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_pago" id="id_pago" value="<%= pagoPrestamo.getId_pago()%>"  readonly="" required="">
                                <input type="hidden" name="id_prestamo" id="id_prestamo" value="<%= pagoPrestamo.getId_prestamo()%>"  readonly="" required="">
                                <input type="hidden" name="monto_prestamo_a" id="monto_prestamo_a" value="<%= pagoPrestamo.getMonto_prestamo()%>"  readonly="" required="">

                                <!--Monto pagado y por pagar antes de actualizar-->
                                <input type="hidden" name="monto_pago_a" id="monto_pago_a" value="<%= pagoPrestamo.getMonto_pago()%>"  readonly="" required="">
                                <input type="hidden" name="monto_por_pagar_a" id="monto_por_pagar_a" value="<%= pagoPrestamo.getMonto_por_pagar()%>"  readonly="" required="">

                                <div class="form-group">
                                    <label class="control-label">Fecha</label>
                                    <input type="date" class="form-control"  name="fecha" value="<%=pagoPrestamo.getFecha()%>">
                                </div>

                                <div class="form-group">
                                    <label class="control-label">Prestador</label>

                                        <select class="form-control"  name="id_prestador" required="" onblur="actualizarPagoPrestamo()">
                                            <option value='<%=pagoPrestamo.getId_prestador()%>'><%=pagoPrestamo.getPrestador()%></option>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto pago:</label>
                                    <input type="number" class="form-control"  step="0.01" name="monto_pago" id="monto_pago" value="<%=pagoPrestamo.getMonto_pago()%>" min="0.00" max="" required="">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PagoPrestamoController?action=listar"><input type="button" value="Cancelar" class="btn btn-warning"/></a>
                                    <input type="submit" value="Guardar" class="btn btn-success"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
