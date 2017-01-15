<%-- 
    Document   : actualizarMunicipio
    Created on : 14-sep-2016, 20:09:50
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Municipio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Municipio municipio = (Municipio) request.getAttribute("municipio");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container" >
            <div class="row">
                <div class="col-md-12">
                    <h2>Modificar datos del municipio</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/MunicipioController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label" for="name">Nombre municipio:</label>
                                    <input type="text" class="form-control" name="nombre_municipio"  value="<%= municipio.getNombre_municipio()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Estado:</label>
                                    <input class="form-control" name="estado" value="<%= municipio.getEstado()%>" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="telefono">Teléfono:</label>
                                    <input type="text" class="form-control" name="telefono" value="<%=municipio.getTelefono()%>" pattern="[0-9]{10}" title="10 dígitos" maxlength="10"/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/MunicipioController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                    <input class="btn btn-success" type="submit" value="Guardar"/>
                                </div>
                            </form>
                        </div>
                    </div><!-- Panel principal-->
                </div>
            </div>
        </div>        
    </body>
</html>
