<%--
    Document   : actualizarVentaMayoreo
    Created on : 27-sep-2016, 12:36:56
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.InventarioMaderaAserrada"%>
<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaMayoreo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaAserrada> listaInventario = (List<InventarioMaderaAserrada>) request.getAttribute("listaInventario");
    VentaMayoreo ventaMayoreo = (VentaMayoreo) request.getAttribute("ventaMayoreo");
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
                    <h2>Actualizar venta mayoreo</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los datos necesarios y guarde los cambio</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/VentaMayoreoController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <input type="hidden" name="id_venta" readonly="" value="<%=ventaMayoreo.getId_venta()%>">
                                    <div class="form-group">
                                        <label class="control-label">Madera</label>
                                        <select class="form-control" name="id_madera" id="id_madera">
                                            <option value="<%=ventaMayoreo.getId_madera()%>"><%=ventaMayoreo.getId_madera()%></option>
                                        </select>
                                    </div>
                                    <%
                                        for (InventarioMaderaAserrada inventario : listaInventario) {
                                            if (inventario.getId_madera().equals(ventaMayoreo.getId_madera())) {
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
                                            <option value="<%=(inventario.getNum_piezas() + ventaMayoreo.getNum_piezas())%>"><%=(inventario.getNum_piezas() + ventaMayoreo.getNum_piezas())%></option>
                                        </select>
                                    </div>
                                    <%}
                                        }
                                    %>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Número de piezas:</label>
                                        <input class="form-control" type="number" name="num_piezas" id="num_piezas" value="<%=ventaMayoreo.getNum_piezas()%>" required="" title="Escribe la cantidad de piezas"  min="0" max="<%=(piezas + ventaMayoreo.getNum_piezas())%>" onblur="calcularVolumenTotal()"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen:</label>
                                        <input class="form-control" type="number" step="0.001" min="0.001" max="99999.999" name="volumen" id="volumen" value="<%=ventaMayoreo.getVolumen()%>" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Monto:</label>
                                        <input class="form-control" type="number" step="0.001" min="0.001" max="99999999.99" name="monto" id="monto" value="<%=ventaMayoreo.getMonto()%>" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Tipo madera:</label>
                                        <select class="form-control" name="tipo_madera" id="tipo_madera">
                                            <option value="<%=ventaMayoreo.getTipo_madera()%>"><%=ventaMayoreo.getTipo_madera()%></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/VentaMayoreoController?action=detalle&id_venta=<%=ventaMayoreo.getId_venta()%>"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
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
