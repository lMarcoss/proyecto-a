<%--
    Document   : actualizarSalidaMaderaRollo
    Created on : 28/10/2016, 05:09:40 PM
    Author     : li-906
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaRollo.InventarioMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page import="entidades.maderaRollo.SalidaMaderaRollo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    SalidaMaderaRollo salida = (SalidaMaderaRollo) request.getAttribute("salidaMaderaRollo");
    List <InventarioMaderaRollo> inventarioMR = (List<InventarioMaderaRollo>) request.getAttribute("inventarioMR");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
        <script src="/aserradero/js/salidaMaderaRollo.js"></script>
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
                            <h3 class="panel-title">Actualice los datos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/SalidaMaderaRolloController?action=actualizar" method="POST">
                                <input type="hidden" name="id_salida" id="id_salida" value="<%= salida.getId_salida()%>" readonly="" required="">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label">Fecha</label>
                                        <input type="date" class="form-control" name="fecha" value="<%=salida.getFecha()%>" required="" onblur="salidaMaderaRolloPermitido()">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label clasif-label">Clasificación primario</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Piezas en existencia</label>

                                        <select class="form-control" name="num_pieza_primarioE" id="num_pieza_primarioE">
                                            <%
                                                for (InventarioMaderaRollo inventario : inventarioMR) {
                                                    out.print("<option value='" + (inventario.getNum_pieza_primario() + salida.getNum_pieza_primario()) + "'>" + (inventario.getNum_pieza_primario() + salida.getNum_pieza_primario()) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen existente</label>
                                        <select class="form-control" name="volumen_primarioE" id="volumen_primarioE">
                                            <%
                                                for (InventarioMaderaRollo inventario : inventarioMR) {
                                                    out.print("<option value='" + (inventario.getVolumen_primario().add(salida.getVolumen_primario())) + "'>" + (inventario.getVolumen_primario().add(salida.getVolumen_primario())) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <label class="control-label">Num. piezas a sacar</label>
                                    <input type="number" class="form-control" name="num_pieza_primario" id="num_pieza_primario" value="<%= salida.getNum_pieza_primario()%>" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()">
                                    <div class="form-group">
                                        <label class="control-label">Volumen a sacar</label>
                                        <input type="number" class="form-control" step=".001" name="volumen_primario" id="volumen_primario" value="<%= salida.getVolumen_primario()%>" min="0" max="99999.999" required="" onclick="salidaMaderaRolloPermitido()">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label clasif-label">Clasificación secundario</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Piezas en existencia</label>
                                        <select class="form-control" name="num_pieza_secundarioE" id="num_pieza_secundarioE">
                                            <%
                                                for (InventarioMaderaRollo inventario : inventarioMR) {
                                                    out.print("<option value='" + (inventario.getNum_pieza_secundario() + salida.getNum_pieza_secundario()) + "'>" + (inventario.getNum_pieza_secundario() + salida.getNum_pieza_secundario()) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen existente</label>
                                        <select class="form-control" name="volumen_secundarioE" id="volumen_secundarioE">
                                            <%
                                                for (InventarioMaderaRollo inventario : inventarioMR) {
                                                    out.print("<option value='" + (inventario.getVolumen_secundario().add(salida.getVolumen_secundario())) + "'>" + inventario.getVolumen_secundario().add(salida.getVolumen_secundario()) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Num. piezas a sacar</label>
                                        <input type="number" class="form-control" name="num_pieza_secundario" id="num_pieza_secundario" value="<%= salida.getNum_pieza_secundario()%>" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen a sacar</label>
                                        <input type="number" class="form-control" step=".001" name="volumen_secundario" id="volumen_secundario" value="<%= salida.getVolumen_secundario()%>" min="0" max="99999.999" required="" onclick="salidaMaderaRolloPermitido()">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label clasif-label">Clasificación terciaria</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Piezas en existencia</label>
                                        <select class="form-control" name="num_pieza_terciarioE" id="num_pieza_terciarioE">
                                            <%
                                                for (InventarioMaderaRollo inventario : inventarioMR) {
                                                    out.print("<option value='" + (inventario.getNum_pieza_terciario() + salida.getNum_pieza_terciario()) + "'>" + (inventario.getNum_pieza_terciario() + salida.getNum_pieza_terciario()) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen existente</label>
                                        <select class="form-control" name="volumen_terciarioE" id="volumen_terciarioE">
                                            <%
                                                for (InventarioMaderaRollo inventario : inventarioMR) {
                                                    out.print("<option value='" + inventario.getVolumen_terciario().add(salida.getVolumen_terciario()) + "'>" + inventario.getVolumen_terciario().add(salida.getVolumen_terciario()) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Num. piezas a sacar</label>
                                        <input type="number" class="form-control" name="num_pieza_terciario" id="num_pieza_terciario" value="<%= salida.getNum_pieza_terciario()%>" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen a sacar</label>
                                        <input type="number" class="form-control" step=".001" name="volumen_terciario" id="volumen_terciario" value="<%= salida.getVolumen_terciario()%>" min="0" max="99999.999" required="" onclick="salidaMaderaRolloPermitido()">
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/SalidaMaderaRolloController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success" value="Guardar"/>
                                    </div>
                                </div>
                            </form>
                        </div><!--panel-body-->
                    </div><!--panel full-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--container-->
    </body>
</html>
