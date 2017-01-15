<%--
    Document   : actualizarPagoRenta
    Created on : 25/09/2016, 10:15:35 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoRenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%PagoRenta pagorenta = (PagoRenta) request.getAttribute("pagorenta");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %> <div class="container">
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
                            <form action="/aserradero/PagoRentaController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_pago_renta" value="<%= pagorenta.getId_pago_renta()%>"  readonly="" required=""/>
                                <div class="form-group">
                                    <label class="control-label"  for="fecha">Fecha</label>
                                    <input type="date" class="form-control" name="fecha" value="<%=pagorenta.getFecha()%>" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="nombre_persona">Nombre</label>
                                    <input type="text" class="form-control" name="nombre_persona" value="<%=pagorenta.getNombre_persona()%>" maxlength="49" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="monto">Monto</label>
                                    <input type="number" class="form-control" step="0.01" name="monto" value="<%=pagorenta.getMonto()%>" min="0.0" max="999999.99" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="observacion">Observacion</label>
                                    <input type="text" class="form-control" name="observacion" maxlength="249" value="<%=pagorenta.getObservacion()%>"/>
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
