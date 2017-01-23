<%--
    Document   : nuevoVehiculo
    Created on : 26/09/2016, 05:30:43 PM
    Author     : rcortes
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
        <script>
            $(document).ready(function ($){
                $("#registros").css("background","#448D00");
                $("#vehiculos").css("background","#448D00");
                $("#vehiculos1").css("background","#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container" >
            <div class="row">
                <div class="col-md-12">
                    <h1 class="page-header">Registrar nuevo vehículo</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/VehiculoController?action=insertar" method="POST" role="form">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label" for="matricula">Matrícula</label>
                                        <input type="text" name="matricula" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="tipo" class="control-label">Tipo</label>
                                        <input type="text" name="tipo" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="color" class="control-label">Color</label>
                                        <input type="text" name="color" class="form-control" />
                                    </div>
                                    <div class='form-group'>
                                        <label for="carga_admitida" class="control-label">Carga admitida</label>
                                        <input type="text" name="carga_admitida" class="form-control"/>
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label for="motor" class="control-label">Motor</label>
                                        <input type="text" class="form-control" name="motor" />
                                    </div>
                                    <div class="form-group">
                                        <label for="modelo" class="control-label">Modelo</label>
                                        <input type="text" class="form-control" name="modelo" />
                                    </div>
                                    <div class="form-group">
                                        <label for="costo" class="control-label">Costo</label>
                                        <input type="number" class="form-control" step=".01" name="costo" min="0.00" max="99999999.99"/>
                                    </div>
                                    <br>
                                    <div class="form-group pull-right col-md-11" >
                                        <a href="/aserradero/VehiculoController?action=listar"><input type="button" class="btn btn-warning col-lg-5 pull-left" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success col-lg-5 pull-right" value="Guardar"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
