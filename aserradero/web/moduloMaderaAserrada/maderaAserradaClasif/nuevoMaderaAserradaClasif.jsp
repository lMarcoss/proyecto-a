<%--
    Document   : nuevoMaderaAserradaClasif
    Created on : 23/12/2016, 11:45:26 PM
    Author     : lmarcoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
        <script src="/aserradero/js/maderaAserradaClasif/selectorCaracteristicasClasifMA.js"></script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nueva clasificación</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene todos los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/MaderaAserradaClasifController?action=insertar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label">Madera:</label>
                                        <input type="text" class="form-control" name="id_madera" title="Al menos cinco letras al principio" required="" maxlength="29"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Grueso:</label>
                                        <select class="form-control" name="grueso_f" id="grueso_f" required="" onblur="seleccionarGrueso()">
                                            <option></option>
                                            <option value="3/4">3/4</option>
                                            <option value="3 1/2">3 1/2</option>
                                            <option value="1 3/4">1 3/4</option>
                                            <option value="1 1/2">1 1/2</option>
                                        </select>
                                        <input name="grueso" id="grueso" type="hidden">
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">Ancho:</label>
                                        <select class="form-control" name="ancho_f" id="ancho_f" required="" onblur="seleccionarAncho()">
                                            <option></option>
                                            <option value="12">12</option>
                                            <option value="10">10</option>
                                            <option value="8">8</option>
                                            <option value="6">6</option>
                                            <option value="4">4</option>
                                            <option value="3 1/2">3 1/2</option>
                                        </select>
                                        <input name="ancho" id="ancho" type="hidden">
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Largo:</label>
                                        <select class="form-control" name="largo_f" id="largo_f" required="">
                                            <option value="8 1/4">8 1/4</option>
                                        </select>
                                        <input name="largo" id="largo" type="hidden" value="8.25">
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">Costo por volumen</label>
                                        <input type="number" class="form-control" name="costo_por_volumen" id="costo_por_volumen" step=".01" min="0.01" max="999.99" title="Sólo números" required="" />
                                    </div>
                                    <div class="form-group pull-right">
                                        <a href="/aserradero/MaderaAserradaClasifController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
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
