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
        <script src="/aserradero/js/maderaAserradaClasif/selectorCaracteristicasClasifMA.js"></script>
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
                                        <input type="hidden" class="form-control" name="id_madera" value="<%=maderaClasificacion.getId_madera()%>" required="" readonly="">
                                        <input type="text" class="form-control" name="id_madera_nuevo" value="<%=maderaClasificacion.getId_madera()%>" required="">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Grueso:</label>
                                        <select class="form-control" name="grueso_f" id="grueso_f" required="" onblur="seleccionarGrueso()">
                                            <option></option>
                                            <%if (maderaClasificacion.getGrueso_f().equals("3/4")) {%>
                                            <option selected="" value="3/4">3/4</option>
                                            <%} else {%>
                                            <option selected="" value="3/4">3/4</option><%}%>

                                            <%if (maderaClasificacion.getGrueso_f().equals("3 1/2")) {%>
                                            <option selected="" value="3 1/2">3 1/2</option>
                                            <%} else {%>
                                            <option value="3 1/2">3 1/2</option><%}%>

                                            <%if (maderaClasificacion.getGrueso_f().equals("1 3/4")) {%>
                                            <option value="1 3/4">1 3/4</option>
                                            <%} else {%>
                                            <option value="1 3/4">1 3/4</option><%}%>

                                            <%if (maderaClasificacion.getGrueso_f().equals("1 1/2")) {%>
                                            <option value="1 1/2">1 1/2</option>
                                            <%} else {%>
                                            <option value="1 1/2">1 1/2</option><%}%>
                                        </select>
                                        <input name="grueso" id="grueso" type="hidden" value="<%=maderaClasificacion.getGrueso()%>">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Ancho:</label>
                                        <select class="form-control" name="ancho_f" id="ancho_f" required="" onblur="seleccionarAncho()">
                                            <option></option>
                                            <%if (maderaClasificacion.getAncho_f().equals("12")) {%>
                                            <option selected="" value="12">12</option>
                                            <%} else {%>
                                            <option selected="" value="12">12</option><%}%>

                                            <%if (maderaClasificacion.getAncho_f().equals("10")) {%>
                                            <option selected="" value="10">10</option>
                                            <%} else {%>
                                            <option selected="" value="10">10</option><%}%>

                                            <%if (maderaClasificacion.getAncho_f().equals("8")) {%>
                                            <option selected="" value="8">8</option>
                                            <%} else {%>
                                            <option selected="" value="8">8</option><%}%>

                                            <%if (maderaClasificacion.getAncho_f().equals("6")) {%>
                                            <option selected="" value="6">6</option>
                                            <%} else {%>
                                            <option selected="" value="6">6</option><%}%>

                                            <%if (maderaClasificacion.getAncho_f().equals("4")) {%>
                                            <option selected="" value="4">4</option>
                                            <%} else {%>
                                            <option selected="" value="4">4</option><%}%>

                                            <%if (maderaClasificacion.getAncho_f().equals("3 1/2")) {%>
                                            <option selected="" value="3 1/2">3 1/2</option>
                                            <%} else {%>
                                            <option selected="" value="3 1/2">3 1/2</option><%}%>
                                        </select>
                                        <input type="hidden" name="ancho" id="ancho" value="<%=maderaClasificacion.getAncho()%>">
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Largo:</label>
                                        <select class="form-control" name="largo_f" id="largo_f">
                                            <option value="<%=maderaClasificacion.getLargo_f()%>"><%=maderaClasificacion.getLargo_f()%></option>
                                        </select>
                                        <input type="hidden"  name="largo" id="largo" value="<%=maderaClasificacion.getLargo()%>" >
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
