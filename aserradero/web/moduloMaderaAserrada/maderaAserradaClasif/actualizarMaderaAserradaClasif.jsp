<%--
    Document   : actualizarMaderaClasificacion
    Created on : 27-sep-2016, 2:45:22
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.MaderaAserradaClasif"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) request.getAttribute("mAClasif");
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
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/MaderaAserradaClasifController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label">Madera:</label>
                                        <input type="text" class="form-control" name="id_madera" value="<%=maderaClasificacion.getId_madera()%>" title="sólo lectura" required="" readonly="">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Grueso:</label>
                                        <input type="number" class="form-control" name="grueso" id="grueso" step=".01" min="0.01" max="9999.99" value="<%=maderaClasificacion.getGrueso()%>"  title="Sólo números" required="" >
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Grueso (fracción o pulgada):</label>
                                        <input type="text" class="form-control" name="grueso_f" id="grueso_f" value="<%=maderaClasificacion.getGrueso_f()%>" maxlength="10" required="" placeholder='3 1/2 o "9"' title="Tal y como se mostrará en el ticket">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Ancho:</label>
                                        <input type="number" class="form-control"  name="ancho" step=".01" min="0.01" max="9999.99" id="ancho" value="<%=maderaClasificacion.getAncho()%>" title="Sólo números" required="" >
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Ancho (fracción o pulgada):</label>
                                        <input type="text" class="form-control" name="ancho_f" id="ancho_f" value="<%=maderaClasificacion.getAncho_f()%>" maxlength="10" required="" placeholder='3 1/2, "9" o 9' title="Tal y como se mostrará en el ticket">
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Largo:</label>
                                        <input type="number" class="form-control" name="largo" id="largo" step=".01" min="0.01" max="9999.99" value="<%=maderaClasificacion.getLargo()%>" title="Sólo números" required="" >
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Largo (fracción o pulgada):</label>
                                        <input type="text" class="form-control" name="largo_f" id="largo_f" value="<%=maderaClasificacion.getLargo_f()%>" maxlength="10" required="" placeholder='3 1/2, "9" o 9' title="Tal y como se mostrará en el ticket">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Costo por volumen</label>
                                        <input type="number" class="form-control" name="costo_por_volumen" step=".01" min="0.01" max="99999.99" value="<%=maderaClasificacion.getCosto_por_volumen()%>" title="Sólo números" required="" />
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/MaderaAserradaClasifController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
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
