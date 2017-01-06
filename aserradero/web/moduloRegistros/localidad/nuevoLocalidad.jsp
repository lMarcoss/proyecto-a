<%--
    Document   : nuevoMunicipio
    Created on : 13-sep-2016, 17:20:34
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.registros.Municipio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Municipio> municipios = (List<Municipio>) request.getAttribute("municipios");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
        <script src="/aserradero/js/localidad/selectorEstadoMunicipio.js"></script>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#localidades").css("background", "#448D00");
            });
        </script>
    </head>
    <body>
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container" style="margin-top:60px;">
            <div class="row">
                <div class="col-md-12">
                    <h2>REGISTRO DE LOCALIDAD</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                          <div class="panel-heading">
                                <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                          </div>
                          <div class="panel-body">
                                <form action="/aserradero/LocalidadController?action=insertar" method="post" id="formregistro">
                                    <div class="form-group">
                                        <label class="control-label" for="name">Nombre localidad:</label>
                                        <input type="text" class="form-control" name="nombre_localidad"  pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="name">Nombre de Municipio:</label>
                                        <select name="nombre_municipio" id="nombre_municipio" required="" class="form-control" onblur="seleccionarEstadoMunicipio()">
                                            <option></option>
                                            <%
                                                for (Municipio municipio : municipios) {
                                                    out.print("<option value='"+municipio.getNombre_municipio()+"'>"+municipio.getNombre_municipio()+"</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="name">Estado:</label>
                                        <select name="estado_municipio" id="estado_municipio" class="form-control" required="" disabled="">
                                            <option></option>
                                            <%
                                                for (Municipio municipio : municipios) {
                                                    out.print("<option value='"+municipio.getEstado()+"'>"+municipio.getEstado()+"</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <input type="hidden" name="estado" id="estado">
                                    <div class="form-group">
                                        <label class="control-label" for="telefono">Teléfono:</label>
                                        <input type="text" class="form-control" name="telefono" pattern="[0-9]{10}" title="10 dígitos"/>
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/LocalidadController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success" value="Guardar"/>
                                    </div>
                                </form>
                          </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
