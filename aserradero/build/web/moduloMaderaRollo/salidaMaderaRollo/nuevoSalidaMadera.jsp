<%--
    Document   : nuevoSalidaMadera
    Created on : 27-oct-2016, 20:43:44
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaRollo.InventarioMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaRollo> inventarioMR = (List<InventarioMaderaRollo>) request.getAttribute("inventario");
    Date fechaActual = (Date) request.getAttribute("fechaActual");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
        <script src="/aserradero/js/salidaMaderaRollo.js"></script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div>
            <%if (!inventarioMR.isEmpty()) {%>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h2>Registrar salida de madera</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                            </div>
                            <div class="panel-body">
                                <form action="/aserradero/SalidaMaderaRolloController?action=insertar" method="POST">
                                    <div class="lado_derecho">
                                        <div class="form-group">
                                            <label class="control-label">Fecha</label>
                                            <input type="date" class="form-control" name="fecha" value="<%=fechaActual%>" required="" onblur="salidaMaderaRolloPermitido()">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label clasif-label">Clasificación primario</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Piezas en existencia</label>
                                            <select class="form-control" name="num_pieza_primarioE" id="num_pieza_primarioE">
                                                <%
                                                    for (InventarioMaderaRollo inventario : inventarioMR) {
                                                        out.print("<option value='" + inventario.getNum_pieza_primario() + "'>" + inventario.getNum_pieza_primario() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Volumen existente</label>
                                            <select class="form-control" name="volumen_primarioE" id="volumen_primarioE">
                                                <%
                                                    for (InventarioMaderaRollo inventario : inventarioMR) {
                                                        out.print("<option value='" + inventario.getVolumen_primario() + "'>" + inventario.getVolumen_primario() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Num. piezas a sacar</label>
                                            <input type="number" class="form-control" name="num_pieza_primario" id="num_pieza_primario" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Volumen a sacar</label>
                                            <input type="number" class="form-control" step=".001" name="volumen_primario" id="volumen_primario" min="0" max="99999.999" required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label clasif-label">Clasificación secundario</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Piezas en existencia</label>
                                            <select class="form-control" name="num_pieza_secundarioE" id="num_pieza_secundarioE">
                                                <%
                                                    for (InventarioMaderaRollo inventario : inventarioMR) {
                                                        out.print("<option value='" + inventario.getNum_pieza_secundario() + "'>" + inventario.getNum_pieza_secundario() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="lado_izquierdo">
                                        <div class="form-group">
                                            <label class="control-label">Volumen existente</label>
                                            <select class="form-control" name="volumen_secundarioE" id="volumen_secundarioE">
                                                <%
                                                    for (InventarioMaderaRollo inventario : inventarioMR) {
                                                        out.print("<option value='" + inventario.getVolumen_secundario() + "'>" + inventario.getVolumen_secundario() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                            <label class="control-label">Num. piezas a sacar</label>
                                        <input type="number" class="form-control" name="num_pieza_secundario" id="num_pieza_secundario" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()">
                                        <div class="form-group">
                                            <label class="control-label">Volumen a sacar</label>
                                            <input type="number" class="form-control" step=".001" name="volumen_secundario" id="volumen_secundario" min="0" max="99999.999" required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label clasif-label">Clasificación terciaria</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Piezas en existencia</label>
                                            <select class="form-control" name="num_pieza_terciarioE" id="num_pieza_terciarioE">
                                                <%
                                                    for (InventarioMaderaRollo inventario : inventarioMR) {
                                                        out.print("<option value='" + inventario.getNum_pieza_terciario() + "'>" + inventario.getNum_pieza_terciario() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Volumen existente</label>
                                            <select class="form-control" name="volumen_terciarioE" id="volumen_terciarioE">
                                                <%
                                                    for (InventarioMaderaRollo inventario : inventarioMR) {
                                                        out.print("<option value='" + inventario.getVolumen_terciario() + "'>" + inventario.getVolumen_terciario() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                            <label class="control-label">Num. piezas a sacar</label>
                                        <input type="number" class="form-control" name="num_pieza_terciario" id="num_pieza_terciario" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()">
                                        <div class="form-group">
                                            <label class="control-label">Volumen a sacar</label>
                                            <input type="number" class="form-control" step=".001" name="volumen_terciario" id="volumen_terciario" min="0" max="99999.999" required="">
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
            <%} else {%>
            <br>
            <br>
            <h2>No se puede registrar salida, No hay inventario</h2>
            <a href="/aserradero/SalidaMaderaRolloController?action=listar"><input type="button" class="btn btn-success" value="Aceptar"/></a>
                <%}%>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
