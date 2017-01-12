<%--
    Document   : actualizarVehiculo
    Created on : 26/09/2016, 05:30:15 PM
    Author     : rcortes
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="entidades.registros.bienesInmuebles.Vehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Vehiculo vehiculo = (Vehiculo) request.getAttribute("vehiculo");
%>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
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
        <div class="container" style="margin-top: 60px;">
            <div class="row">
                <div class="col-md-12">
                    <h2>REGISTRO DE UN NUEVO VEHÍCULO</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/VehiculoController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <input type="hidden" name="id_vehiculo" value="<%=vehiculo.getId_vehiculo()%>" required="" readonly="">
                                    <div class="form-group">
                                        <label class="control-label" for="matricula">Matrícula</label>
                                        <input type="text" class="form-control" name="matricula" value="<%=vehiculo.getMatricula()%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="tipo">Tipo</label>
                                        <input type="text" class="form-control" name="tipo" value="<%=vehiculo.getTipo()%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="color">Color</label>
                                        <input type="text" class="form-control" name="color" value="<%=vehiculo.getColor()%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="carga_admitida">Carga admitida</label>
                                        <input type="text" class="form-control" name="carga_admitida" value="<%=vehiculo.getCarga_admitida()%>"/>
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label" for="motor">Motor</label>
                                        <input type="text" class="form-control" name="motor" value="<%=vehiculo.getMotor()%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="modelo">Modelo</label>
                                        <input type="text" class="form-control" name="modelo" value="<%=vehiculo.getModelo()%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="costo">Costo</label>
                                        <input type="number" class="form-control" step=".01" name="costo" value="<%=vehiculo.getCosto()%>" min="0.0"/>
                                    </div>
                                    <br>
                                    <div class="form-group col-md-11">
                                        <a href="/aserradero/VehiculoController?action=listar"><input class="btn btn-warning col-md-5 pull-left" type="button" value="Cancelar"/></a>
                                        <input class="btn btn-success col-md-5 pull-right" type="submit" value="Guardar"/>
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
