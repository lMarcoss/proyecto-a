<%--
    Document   : actualizarTerreno
    Created on : 18/12/2016, 05:30:15 PM
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Localidad"%>
<%@page import="entidades.registros.bienesInmuebles.Terreno"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="entidades.registros.bienesInmuebles.Vehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Terreno terreno = (Terreno) request.getAttribute("terreno");
    List <Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
%>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <script>
            $(document).ready(function ($){
                 $("#registros").css("background","#448D00");
                 $("#vehiculos").css("background","#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <script src="/aserradero/js/terreno/selector_estadoTerreno.js"></script>
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
                            <form action="/aserradero/TerrenoController?action=actualizar" method="POST" role="form">
                                <div class="lado_derecho">
                                    <input type="hidden" name="id_terreno" value="<%=terreno.getId_terreno()%>">
                                    <div class="form-group">
                                        <label class="control-label">Nombre</label>
                                        <input type="text" name="nombre" class="form-control" value="<%=terreno.getNombre()%>">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Dimensión</label>
                                        <input type="text" name="dimension" class="form-control" value="<%=terreno.getDimension()%>">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Dirección</label>
                                        <input type="text" name="direccion" class="form-control" value="<%=terreno.getDireccion()%>">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Localidad:</label>
                                        <select class="form-control" name="nombre_localidad" id="nombre_localidad" required="" onblur="seleccionarEstadoTerreno()">
                                            <%
                                                for (Localidad localidad : localidades) {
                                                    if(terreno.getNombre_localidad().equals(localidad.getNombre_localidad())){
                                                        out.print("<option selected='' value='"+localidad.getNombre_localidad()+"'>"+localidad.getNombre_localidad()+"</option>");
                                                    }else{
                                                        out.print("<option value='"+localidad.getNombre_localidad()+"'>"+localidad.getNombre_localidad()+"</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label">Municipio</label>
                                        <select class="form-control" name="_nombre_municipio" id="_nombre_municipio" required="" disabled="">
                                            <%
                                                for (Localidad localidad : localidades) {
                                                    if(terreno.getNombre_municipio().equals(localidad.getNombre_municipio())){
                                                        out.print("<option selected='' value='"+localidad.getNombre_municipio()+"'>"+localidad.getNombre_municipio()+"</option>");
                                                    }else{
                                                        out.print("<option value='"+localidad.getNombre_municipio()+"'>"+localidad.getNombre_municipio()+"</option>");
                                                    }
                                                    
                                                }
                                            %>
                                        </select>
                                        <input type="hidden" name="nombre_municipio" id="nombre_municipio">
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="control-label">Estado:</label>
                                        <select class="form-control" name="_estado" id="_estado" required="" disabled="">
                                            <%
                                                for (Localidad localidad : localidades) {
                                                    if(terreno.getEstado().equals(localidad.getEstado())){
                                                        out.print("<option selected='' value='"+localidad.getEstado()+"'>"+localidad.getEstado()+"</option>");
                                                    }else{
                                                        out.print("<option value='"+localidad.getEstado()+"'>"+localidad.getEstado()+"</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                        <input type="hidden" name="estado" id="estado">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Valor estimado</label>
                                        <input type="number" name="valor_estimado" class="form-control" step=".01" min="0.01" max="99999999.99" value="<%=terreno.getValor_estimado()%>"/>
                                    </div>
                                    <br>
                                    <div class="form-group pull-right col-md-11" >
                                        <a href="/aserradero/TerrenoController?action=listar"><input type="button" class="btn btn-warning col-lg-5 pull-left" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success col-lg-5 pull-right" value="Guardar"/>
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
