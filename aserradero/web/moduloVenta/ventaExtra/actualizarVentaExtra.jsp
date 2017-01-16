<%--
    Document   : actualizarVentaExtra
    Created on : 21-sep-2016, 23:43:54
    Author     : lmarcoss
--%>

<%@page import="entidades.venta.VentaExtra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    VentaExtra ventaExtra = (VentaExtra) request.getAttribute("ventaExtra");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <%@ include file="/TEMPLATE/headNuevo.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actulizar venta Extra</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los datos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/VentaExtraController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Id venta:</label></td>
                                    <input type="text" class="form-control" name="id_venta" value="<%=ventaExtra.getId_venta()%>" required="" readonly=""/></td>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Tipo:</label></td>
                                    <input type="text" class="form-control" name="tipo" value="<%=ventaExtra.getTipo()%>" readonly=""/></td>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto</label></td>
                                    <input class="form-control" type="number" step="0.01" name="monto" value="<%=ventaExtra.getMonto()%>" required="" min="0.01" max="99999999.99"/></td>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Observación:</label></td>
                                    <textarea name="observacion" class="form-control" required=""><%=ventaExtra.getObservacion()%></textarea></td>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/VentaExtraController?action=detalle&id_venta=<%=ventaExtra.getId_venta()%>"><input type="button" class="btn btn-warning" value="Cancelar"/></a> </td>
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
