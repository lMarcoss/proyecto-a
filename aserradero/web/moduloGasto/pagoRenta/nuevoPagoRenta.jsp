<%--
    Document   : nuevoPagoRenta
    Created on : 25/09/2016, 09:32:46 PM
    Author     : rcortes
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %> <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Agregar nuevo pago de renta</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoRentaController?action=insertar" method="POST">
                                <div class="form-group">
                                    <label class="control-label"  for="fecha">Fecha</label>
                                    <input type="date" class="form-control" name="fecha" required="" placeholder="AAAA/MM/DD"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="nombre_persona">Nombre</label>
                                    <input type="text" class="form-control" name="nombre_persona" maxlength="49" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="monto">Monto</label>
                                    <input type="number" class="form-control" step=".01" name="monto" min="0.01" max="999999.99" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="observacion">Observacion</label>
                                    <input type="text" class="form-control" name="observacion" maxlength="249"/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PagoRentaController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
