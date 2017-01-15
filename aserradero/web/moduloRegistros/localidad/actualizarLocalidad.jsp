<%--
    Document   : actualizarMunicipio
    Created on : 14-sep-2016, 20:09:50
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Localidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Localidad localidad = (Localidad) request.getAttribute("localidad");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#localidades").css("background", "#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>ACTUALIZACIÓN DE LOCALIDAD</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/LocalidadController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="name">Nombre localidad:</label>
                                    <input type="text" class="form-control" name="nombre_localidad" value="<%= localidad.getNombre_localidad()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="name">Nombre municipio:</label>
                                    <input type="text" class="form-control" name="nombre_municipio" value="<%= localidad.getNombre_municipio()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <i class="glyphicon glyphicon-phone"></i>
                                    <label class="control-label" for="telefono">Teléfono:</label>
                                    <input type="text" class="form-control" name="telefono" value="<%=localidad.getTelefono_localidad()%>" pattern="[0-9]{10}" title="10 dígitos"/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/LocalidadController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
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
