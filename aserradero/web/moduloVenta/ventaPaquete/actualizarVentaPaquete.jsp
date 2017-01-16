<%--
    Document   : actualizarVentaPaquete
    Created on : 28-sep-2016, 9:56:38
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.InventarioMaderaAserrada"%>
<%@page import="entidades.venta.VentaPaquete"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaAserrada> listaInventario = (List<InventarioMaderaAserrada>) request.getAttribute("listaInventario");
    VentaPaquete ventaPaquete = (VentaPaquete) request.getAttribute("ventaPaquete");
    int piezas = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <script src="/aserradero/js/SelectorCostoVenta.js"></script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actualización de venta por paquete</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los datos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/VentaPaqueteController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label">Id venta:</label>
                                        <input type="text" class="form-control" name="id_venta" value="<%=ventaPaquete.getId_venta()%>" readonly=""/>

                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Número de Paquete</label>
                                        <input class="form-control" type="number" name="numero_paquete" value="<%=ventaPaquete.getNumero_paquete()%>" min="1" max="99999999999" title="Sólo número" readonly=""/>

                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Madera</label>

                                            <select class="form-control" name="id_madera">
                                                <option value="<%=ventaPaquete.getId_madera()%>"><%=ventaPaquete.getId_madera()%></option>
                                            </select>

                                    </div>
                                    <%
                                        for (InventarioMaderaAserrada inventario : listaInventario) {
                                            if (inventario.getId_madera().equals(ventaPaquete.getId_madera())) {
                                                piezas = inventario.getNum_piezas();
                                    %>
                                    <div class="form-group">
                                        <label class="control-label">Volumen unitaria</label>

                                            <select class="form-control" name="volumen_unitaria" id="volumen_unitaria">
                                                <option value="<%=inventario.getVolumen_unitario()%>"><%=inventario.getVolumen_unitario()%></option>
                                            </select>

                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Costo por volumen</label>

                                            <select class="form-control" name="costo_volumen" id="costo_volumen">
                                                <option value="<%=inventario.getCosto_por_volumen()%>"><%=inventario.getCosto_por_volumen()%></option>
                                            </select>

                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Piezas en existencia</label>

                                            <select class="form-control" name="pieza_existencia" id="pieza_existencia">
                                                <option value="<%=(inventario.getNum_piezas() + ventaPaquete.getNum_piezas())%>"><%=(inventario.getNum_piezas() + ventaPaquete.getNum_piezas())%></option>
                                            </select>

                                    </div>

                                    <%}
                                        }
                                    %>
                                    <div class="form-group">
                                        <label class="control-label">Número de piezas:</label>
                                        <input class="form-control" type="number" name="num_piezas" id="num_piezas" value="<%=ventaPaquete.getNum_piezas()%>" min="1" max="<%=(piezas + ventaPaquete.getNum_piezas())%>" required="" title="Escribe la cantidad de piezas" onblur="calcularVolumenTotal()"/>

                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Volumen:</label>
                                        <input class="form-control" type="number" name="volumen" id="volumen" value="<%=ventaPaquete.getVolumen()%>" step="0.001" min="0.001" max="99999.999"  required="" readonly=""/>

                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Monto:</label>
                                        <input class="form-control" type="number" name="monto" id="monto" value="<%=ventaPaquete.getMonto()%>" step="0.01" min="0.01" max="99999999.99"  required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Tipo madera</label>
                                        <select class="form-control" name="tipo_madera" id="tipo_madera" required="">
                                            <option selected="" value="<%=ventaPaquete.getTipo_madera()%>"><%=ventaPaquete.getTipo_madera()%></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/VentaPaqueteController?action=detalle&id_venta=<%=ventaPaquete.getId_venta()%>"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success" value="Guardar"/>
                                    </div>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
