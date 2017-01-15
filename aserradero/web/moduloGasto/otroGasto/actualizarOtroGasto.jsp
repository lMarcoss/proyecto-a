<%--
    Document   : actualizarOtroGasto
    Created on : 26/09/2016, 03:36:38 PM
    Author     : rcortes
--%>
<%@page import="entidades.gasto.OtroGasto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    OtroGasto otrogasto = (OtroGasto) request.getAttribute("otrogasto");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
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
                            <form action="/aserradero/OtroGastoController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_gasto" value="<%= otrogasto.getId_gasto()%>"  readonly="" required="">
                                <div class="form-group">
                                    <label class="control-label" >Fecha</label>
                                    <input type="date" class="form-control" name="fecha" value="<%=otrogasto.getFecha()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="nombre_gasto">Nombre gasto</label>
                                    <input type="text" class="form-control" name="nombre_gasto" value="<%=otrogasto.getNombre_gasto()%>" maxlength="249" required="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="monto">Monto</label>
                                    <input type="number" class="form-control" name="monto" value="<%=otrogasto.getMonto()%>" step="0.01" min="0.00" max="999999.99" required="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="observacion">Observacion</label>
                                    <input type="text" class="form-control" name="observacion" value="<%=otrogasto.getObservacion()%>" maxlength="249">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/OtroGastoController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                    <!--<td><input type="submit" value="Registrar" class="submit"/> -->
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
