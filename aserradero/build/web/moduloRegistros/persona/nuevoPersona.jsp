<%--
    Document   : nuevoPersona
    Created on : 16-sep-2016, 19:47:45
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Localidad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp"%>
        <script src="/aserradero/js/persona/id_persona.js"></script>
        <script src="/aserradero/js/persona/selector_estadoPersona.js"></script>
        <title>Nuevo</title>
        <script>
            $(document).ready(function ($){
                 $("#registros").css("background","#448D00");
                 $("#personas").css("background","#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container" style="margin-top:60px;">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">Registrar persona</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Es importante rellenar correctamente, ya que no se podrá modificar</div>
                        <div class="panel-body">
                            <form action="/aserradero/PersonaController?action=insertar" method="post" id="formregistro" onsubmit="return validarPersona()">
                                <div class="lado_derecho"><!-- Grupo derecho-->
                                    <div class="form-group">
                                        <label class="control-label">Id_persona:</label>
                                        <input class="form-control" type="text" name="id_persona" id="id_persona" maxlength="18" placeholder="Escriba la CURP de la persona" />
                                    </div>
                                    <div class="form-group">
                                        <!--<label class="control-label">Id_persona:</label>-->
                                        <input class="form-control" type="button" value="Crear un id para la persona" onclick="crearIdPersona()">
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <label class="control-label">Nombre:</label>
                                        <input class="form-control" type="text" name="nombre" id="nombre" pattern="[A-Za-z].{2,}" title="Sólo letras aA-zZ, al menos 3 letras" maxlength="45" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <label class="control-label">Apellido paterno:</label>
                                        <input class="form-control" type="text" name="apellido_paterno" id="apellido_paterno" pattern="[A-Za-z].{2,}" title="Sólo letras aA-zZ, al menos 3 letras" maxlength="45" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <label class="control-label">Apellido materno:</label>                                        
                                        <input class="form-control" type="text" name="apellido_materno" id="apellido_materno" pattern="[A-Za-z].{2,}" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="45"/>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="control-label">Dirección:</label>
                                        <input class="form-control" type="text" name="direccion" title="dirección" placeholder="ej. carr Oaxaca Puerto Ángel km 97" maxlength="60"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Localidad:</label>
                                        <select class="form-control" name="nombre_localidad" id="nombre_localidad" required="" onblur="seleccionarEstadoLocalidad()">
                                            <option></option>
                                            <%
                                                for (Localidad localidad : localidades) {
                                                    out.print("<option value='"+localidad.getNombre_localidad()+"'>"+localidad.getNombre_localidad()+", "+localidad.getNombre_municipio()+"</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="lado_izquierdo"><!-- Grupo Izquierdo -->
                                    <div class="form-group">
                                        <label class="control-label">Municipio</label>
                                        <select class="form-control" name="_nombre_municipio" id="_nombre_municipio" required="" disabled="">
                                            <option></option>
                                            <%
                                                for (Localidad localidad : localidades) {
                                                    out.print("<option value='"+localidad.getNombre_municipio()+"'>"+localidad.getNombre_municipio()+"</option>");
                                                }
                                            %>
                                        </select>
                                        <input type="hidden" name="nombre_municipio" id="nombre_municipio">
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="control-label">Estado:</label>
                                        <select class="form-control" name="_estado" id="_estado" required="" disabled="">
                                            <option></option>
                                            <%
                                                for (Localidad localidad : localidades) {
                                                    out.print("<option value='"+localidad.getEstado()+"'>"+localidad.getEstado()+"</option>");
                                                }
                                            %>
                                        </select>
                                        <input type="hidden" name="estado" id="estado">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Sexo:</label>
                                        <select class="form-control" name="sexo" id="sexo">
                                            <option value="H">Hombre</option>
                                            <option value="M">Mujer</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-calendar"></i>
                                        <label class="control-label">Fecha de nacimiento:</label>
                                        <input class="form-control" type="date" name="fecha_nacimiento" id="fecha_nacimiento" required="" maxlength="10" title="Es importante la fecha de nacimiento para asignar un identificador a la persona">
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-phone"></i>
                                        <label class="control-label">Teléfono:</label>
                                        <input class="form-control" type="text" name="telefono" pattern="[0-9]{10}" title="10 dígitos" placeholder="951xxxxxxx" maxlength="10"/>
                                    </div>
                                    <br>
                                    <div class="form-group pull-right col-md-11">
                                        <a href="/aserradero/PersonaController?action=listar"><input class="btn btn-warning col-md-5 pull-left" type="button" value="Cancelar"/></a>
                                        <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
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
