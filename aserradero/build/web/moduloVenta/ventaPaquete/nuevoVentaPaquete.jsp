<%--
    Document   : nuevoProduccionDetalle
    Created on : 28-sep-2016, 9:56:07
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.InventarioMaderaAserrada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.venta.VentaPaquete"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.registros.Cliente"%>
<%@page import="entidades.venta.Venta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date fecha = Date.valueOf(LocalDate.now());
    List<InventarioMaderaAserrada> listaInventario = (List<InventarioMaderaAserrada>) request.getAttribute("listaInventario");
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    String id_nVenta = String.valueOf(request.getAttribute("siguienteventa"));
    String id_administrador = (String) request.getAttribute("id_administrador");
    HttpSession sesion_ajax = request.getSession(true);
    sesion_ajax.setAttribute("detalle_venta_paquete", null);
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <%--<%@ include file="/TEMPLATE/headNuevo.jsp" %>--%>
        <title>Nuevo</title>
        <script src="/aserradero/js/SelectorCostoVenta.js"></script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <!--formulario de registro-->
        <div id="page-wrapper" style="margin-top: 30px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Registrar venta por paquete</h1>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <!-- Panel principal -->
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body" id="PanelPrincipal">
                            <div class="col-md-12 bordebajo">
                                <!-- agrupar inputs -->
                                <form action="/aserradero/VentaController?action=insertar&tipo_venta=Paquete" method="post" id="formregistro">
                                    <!-- Formulario de venta -->
                                    <input class="form-control" type="hidden" value="<%=id_administrador%>" name="id_administrador" id="id_administrador" readonly="">
                                    <input class="form-control" type="hidden" value="<%=id_nVenta%>" name="id_venta" id="id_venta" readonly="">
                                    <input type="hidden" name="estatus" value="Sin pagar" id="estatus" class="form-control" readonly=""/>

                                    <div class="form-group col-md-2">
                                        <!-- agrupar inputs -->
                                        <label class="control-label">Fecha:</label>
                                        <input class="form-control" type="date" name="fecha" value="<%=fecha%>" required=""/>
                                    </div>
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
                                    <div class="col-md-2">
                                        <br>
                                        <input type="submit" class="btn btn-block btn-success margen-boton" value="Guardar venta"/>
                                    </div>
                                    <div class="col-md-2">
                                        <br>
                                        <a href="/aserradero/VentaPaqueteController?action=listar"><input class="btn btn-block btn-warning" type="button" value="Cancelar"/></a>
                                    </div>
                                    <!-- Fin div group -->
                                </form>
                                <!-- Formulario de venta -->
                            </div>
                            <!-- Fin div group -->
                            <div class="col-md-13">
                                <div class="col-md-12">
                                    <div class="form-group col-md-2">
                                        <label class="control-label">Paquete</label>
                                        <input type="number" class="form-control" id="numero_paquete" name="numero_paquete" min="1" max="99999999999" title="Sólo número" required="">
                                    </div>
                                    <div class="col-md-3">
                                        <label class="control-label">Madera:</label>
                                        <select name="id_madera" class="form-control" required="" id="id_madera" onblur="seleccionarCostoMaderaVenta()">
                                            <option></option>
                                            <%                                                for (InventarioMaderaAserrada inventario : listaInventario) {
                                                    out.print("<option value='" + inventario.getId_madera() + "'>" + inventario.getId_madera() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="control-label">Número de piezas:</label>
                                        <input type="number" class="form-control" name="num_piezas" id="num_piezas" min="1" max="9999" required="" title="Escribe la cantidad de piezas" onblur="calcularVolumenTotal()"/>
                                    </div>
                                    <div class="col-md-2">
                                        <label class="control-label" >Piezas en existencia</label>
                                        <select name="pieza_existencia" class="form-control" id="pieza_existencia" readonly="" disabled="">
                                            <option></option>
                                            <%
                                                for (InventarioMaderaAserrada inventario : listaInventario) {
                                                    out.print("<option value='" + inventario.getNum_piezas() + "'>" + inventario.getNum_piezas() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <label class="control-label" >Tipo madera</label>
                                        <select name="tipo_madera" class="form-control" id="tipo_madera">
                                            <option value="Madera">Madera</option>
                                            <option value="Amarre">Amarre</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="col-md-2">
                                        <label class="control-label">volumen unitaria</label>
                                        <select name="volumen_unitaria" class="form-control" id="volumen_unitaria" readonly="" disabled="">
                                            <option></option>
                                            <%
                                                for (InventarioMaderaAserrada inventario : listaInventario) {
                                                    out.print("<option value='" + inventario.getVolumen_unitario() + "'>" + inventario.getVolumen_unitario() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="control-label">Costo volumen</label>
                                        <select class="form-control" name="costo_volumen" id="costo_volumen" readonly="" disabled="">
                                            <option></option>
                                            <%
                                                for (InventarioMaderaAserrada inventario : listaInventario) {
                                                    out.print("<option value='" + inventario.getCosto_por_volumen() + "'>" + inventario.getCosto_por_volumen() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="control-label">Volumen total:</label>
                                        <input type="number" class="form-control" name="volumen" id="volumen" step="0.001" min="0.001" max="99999.999" required="" readonly=""/>
                                    </div>
                                    <div class="col-md-2">
                                        <label class="control-label">Monto total:</label>
                                        <input type="number" class="form-control" name="monto" id="monto" step="0.01" min="0.01" max="99999999.99" required="" readonly=""/>
                                    </div>
                                    <div class="col-md-2">
                                        <!--<input type="button" value="Agregar" id="agregar_venta_paquete" class="btn btn-primary centrar-btn-vp"/>-->
                                        <br>
                                        <input id="agregar_venta_paquete" type="button" class="btn btn-info col-md-15" value="Agregar producto" style="width: 100%;">
                                    </div>
                                </div>
                            </div>
                            <!-- a -->
                        </div>
                        <!-- Fin de panel venta -->
                    </div>
                    <!-- panel fin-->
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Productos</h3>
                        </div>
                        <div class="panel-body" id="detalle_producto_paquete">
                            <%
                                ArrayList<VentaPaquete> VentaPaq = (ArrayList<VentaPaquete>) sesion_ajax.getAttribute("detalle_venta_paquete");
                                if ((sesion_ajax.getAttribute("detalle_venta_paquete")) != null) {
                                    if (VentaPaq.size() > 0) {//Si la cantida de productos agregados es mayor a cero
                                        System.out.println("Hola");
                                        out.print("<table class='table'>");
                                        out.print("<thead>");
                                        out.print("<tr>");
                                        out.print("<th>Número paquete</th>");
                                        out.print("<th>Madera</th>");
                                        out.print("<th>Número de piezas</th>");
                                        out.print("<th>Volumen</th>");
                                        out.print("<th>Monto</th>");
                                        out.print("<th>Tipo madera</th>");
                                        out.print("<th></th>");
                                        out.print("</tr>");
                                        out.print("</thead>");
                                        out.print("<tbody>");//Inicia el cuerpo de la tabla
                                        for (VentaPaquete a : VentaPaq) {
                                            out.print("<tr>");
                                            out.print("<td>" + a.getNumero_paquete() + "</td>");
                                            out.print("<td>" + a.getId_madera() + "</td>");
                                            out.print("<td>" + a.getNum_piezas() + "</td>");
                                            out.print("<td>" + a.getVolumen() + "</td>");
                                            out.print("<td>" + a.getMonto() + "</td>");
                                            out.print("<td>" + a.getTipo_madera() + "</td>");
                                            out.print("<td><input type='button' value='Eliminar' class='btn btn-danger eliminar_ventap' id='" + a.getId_madera() + "' /></td>");
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
                    </div>
                    <!-- Fin panel lista de productos -->
                </div>
                <!-- col-md-12 fin -->
            </div>
            <!-- container fluid -->
        </div>
        <!--page wrapper fin -->
    </body>
</html>
