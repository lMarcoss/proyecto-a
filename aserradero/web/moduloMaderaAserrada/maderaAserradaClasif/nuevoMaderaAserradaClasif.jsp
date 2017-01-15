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
                                        <input type="text" class="form-control" name="id_madera" pattern="[A-Za-z].{3,}[A-Za-z0-9]" title="Al menos cinco letras al principio" required="" />
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Grueso:</label>
                                        <input type="number" class="form-control" name="grueso" id="grueso" step=".01" min="0.01" max="9999.99"  title="Sólo números" required="" onblur="calcularVolumen()"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Grueso (fracción o pulgada):</label>
                                        <input type="text" class="form-control" name="grueso_f" id="grueso_f" maxlength="10" required="" placeholder='3 1/2 o "9"' title="Tal y como se mostrará en el ticket">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Ancho:</label>
                                        <input type="number" class="form-control"  name="ancho" id="ancho" step=".01" min="0.01" max="9999.99" title="Sólo números" required="" onblur="calcularVolumen()"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Ancho (fracción o pulgada):</label>
                                        <input type="text" class="form-control" name="ancho_f" id="ancho_f" maxlength="10" required="" placeholder='3 1/2, "9" o 9' title="Tal y como se mostrará en el ticket">
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Largo:</label>
                                        <input type="number" class="form-control" name="largo" id="largo" step=".01" min="0.01" max="9999.99" title="Sólo números" required="" onblur="calcularVolumen()"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Largo (fracción o pulgada):</label>
                                        <input type="text" class="form-control" name="largo_f" id="largo_f" maxlength="10" required="" placeholder='3 1/2, "9" o 9' title="Tal y como se mostrará en el ticket">
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
