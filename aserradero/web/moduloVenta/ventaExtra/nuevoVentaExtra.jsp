<%--
    Document   : nuevoVentaExtra
    Created on : 21-sep-2016, 23:43:35
    Author     : lmarcoss
--%>

<%@page import="entidades.venta.VentaExtra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.registros.Cliente"%>
<%@page import="entidades.venta.Venta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date fecha = Date.valueOf(LocalDate.now());
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    List<Venta> ventas = (List<Venta>) request.getAttribute("ventas");
    String id_nVenta = String.valueOf(request.getAttribute("siguienteventa"));
    HttpSession sesion_ajax = request.getSession(true);
    sesion_ajax.setAttribute("detalle_venta_extra", null);
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <%@ include file="/TEMPLATE/headNuevo.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="page-header">REGISTRO DE VENTAS EXTRAS</h2>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="panel panel-primary"><!-- Panel principal -->
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body" id="PanelPrincipal">
                            <div class="col-md-12 bordebajo">
                                <form action="/aserradero/VentaController?action=insertar&tipo_venta=Extra" method="post" id="formregistro"><!-- Formulario de venta -->
                                    <div class="form-group col-md-2"><!-- agrupar inputs -->
                                        <input name="tipo_venta" value="extra" type="hidden"/>
                                        <label class="control-label">Fecha:</label>
                                        <input class="form-control" type="date" name="fecha" value="<%=fecha%>" required="" />
                                    </div>
                                    <input type="hidden" value="<%=id_nVenta%>" name="id_venta" id="id_venta" required="" readonly="">
                                    <div class="form-group col-md-2">
                                        <label class="control-label">Cliente</label>
                                        <select class="form-control" name="id_cliente" required="">
                                            <option></option>
                                            <%
                                                for (Cliente cliente : clientes) {
                                                    out.print("<option value='" + cliente.getId_cliente() + "'>" + cliente.getCliente() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label class="control-label">Pago en efectivo:</label>
                                        <input type="number" class="form-control" step="0.01" name="pago" id="pago" min="0" max="999999.99" required="">
                                    </div>
                                    <div class="col-md-2 form-group">
                                        <label class="control-label">Ticket:</label>
                                        <select class="form-control" name="ticket" id="ticket">
                                            <option value="costo">Con costo</option>
                                            <option value="sin_costo">Sin costo</option>
                                        </select>
                                    </div>
                                    <div class="form-group pull-right col-md-2"><!-- agrupar inputs -->
                                        <br>
                                        <a href="/aserradero/VentaExtraController?action=listar"><input class="btn btn-block btn-warning" type="button" value="Cancelar"/></a>
                                    </div><!-- Fin div group -->
                                    <div class="form-group pull-right col-md-2"><!-- agrupar inputs -->
                                        <br>
                                        <input type="submit" class="btn btn-block btn-success margen-boton" value="Guardar venta"/>
                                    </div><!-- Fin div group -->
                                </form><!-- Formulario de venta -->
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-2 form-group">
                                    <label class="control-label">Tipo:</label>
                                    <input type="text" id="tipo" class="form-control" name="tipo" required="" maxlength="50" placeholder="Ej: 2 costales de aserrin"/>
                                </div>
                                <div class="col-md-2 form-group">
                                    <label class="control-label">Monto</label>
                                    <input class="form-control" id="monto" type="number" step="0.01" name="monto" min="0.01" max="99999.99" required="" placeholder="345.50"/>
                                </div>
                                <div  class="col-md-6 form-group">
                                    <label class="control-label">Observación:</label>
                                    <textarea name="observacion" id="observacion" class="form-control" maxlength="100" required="" title="Escriba una observacion de la venta" placeholder="ej: venta de aserrin"></textarea>
                                </div>
                                <div class="form-group col-md-2">
                                    <input type="submit" class="btn btn-block btn-primary centrar-btn" id="agregar_Ven_Ex" value="Agregar"/>
                                </div>
                            </div>
                        </div>
                    </div><!-- panel fin-->
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Productos</h3>
                        </div>
                        <div class="panel-body detalle-producto">
                            <%                                ArrayList<VentaExtra> VentaMay = (ArrayList<VentaExtra>) sesion_ajax.getAttribute("detalle");
                                if (((sesion_ajax.getAttribute("detalle_venta_extra")) != null)) {
                                    if (VentaMay.size() > 0) {//Si la cantida de productos agregados es mayor a cero
                                        out.print("<table class='table'>");
                                        out.print("<thead>");
                                        out.print("<tr>");
                                        out.print("<th>Tipo</th>");
                                        out.print("<th>Monto</th>");
                                        out.print("<th>Observación</th>");
                                        out.print("<th></th>");
                                        out.print("</tr>");
                                        out.print("</thead>");
                                        out.print("<tbody>");//Inicia el cuerpo de la tabla
                                        for (VentaExtra a : VentaMay) {
                                            out.print("<tr>");
                                            out.print("<td>" + a.getTipo() + "</td>");
                                            out.print("<td>" + a.getMonto() + "</td>");
                                            out.print("<td>" + a.getObservacion() + "</td>");
                                            out.print("<td><input type='button' value='Eliminar' /></td>");
                                            out.print("</tr>");
                                        }
                                        out.print("</tbody>");
                                        out.print("</table>");
                                    } else {
                                        out.print("<h3 class='panel-title'>No hay registros agregados</h3>");
                                    }
                                } else {
                                    out.print("<h3 class='panel-title'>No hay registros agregados</h3>");
                                }
                            %>
                        </div>
                    </div><!-- Fin panel lista de productos -->
                </div><!-- col-md-12 fin -->
            </div><!-- container fluid -->
        </div><!--page wrapper fin -->
    </body>
</html>
