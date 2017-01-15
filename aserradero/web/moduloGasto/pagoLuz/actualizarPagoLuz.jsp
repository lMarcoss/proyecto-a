s<%--
    Document   : actualizarPagoLuz
    Created on : 26/09/2016, 01:54:35 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoLuz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%PagoLuz pagoluz = (PagoLuz) request.getAttribute("pagoLuz");%>
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
                            <form action="/aserradero/PagoLuzController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_pago_luz" value="<%= pagoluz.getId_pago_luz()%>"  readonly="" title="No puedes cambiar esto" >
                                <div class="form-group">
                                    <label class="control-label"  for="fecha">Fecha</label>
                                    <input type="date" class="form-control" name="fecha" required="" value="<%=pagoluz.getFecha()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label" >Empleado</label>

                                        <input type="hidden" name="id_empleado" value="<%=pagoluz.getId_empleado()%>" required="" readonly="">
                                        <input type="text" class="form-control" name="id_empleado" value="<%=pagoluz.getEmpleado()%>" required="" readonly="">

                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="monto">Monto</label>
                                    <input type="number" class="form-control" step="0.01" name="monto" value="<%=pagoluz.getMonto()%>" min="0.00" max="999999.99" required="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label"  for="observacion">Observacion</label>
                                    <input type="text" class="form-control" name="observacion" value="<%=pagoluz.getObservacion()%>" maxlength="249">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PagoLuzController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
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
