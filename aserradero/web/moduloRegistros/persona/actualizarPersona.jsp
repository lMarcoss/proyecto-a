<%--
    Document   : actualizarPersona
    Created on : 16-sep-2016, 19:48:04
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Localidad"%>
<%@page import="java.util.List"%>
<%@page import="entidades.registros.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Persona persona = (Persona) request.getAttribute("persona");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#personas").css("background", "#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container" >
            <div class="row">
                <div class="col-md-12">
                    <h2>Modificar datos de una persona</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Modifica los datos necesarios y da click en Guardar</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PersonaController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho"><!-- Grupo derecho-->
                                    <div class="form-group">
                                        <label class="control-label">Id_persona:</label>
                                        <input class="form-control" type="text" name="id_persona" id="id_persona" maxlength="18" value="<%=persona.getId_persona()%>" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <label class="control-label">Nombre:</label>
                                        <input class="form-control" type="text" name="nombre" id="nombre" value="<%=persona.getNombre()%>"  maxlength="45" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <label class="control-label">Apellido paterno:</label>
                                        <input class="form-control" type="text" name="apellido_paterno" id="apellido_paterno" value="<%=persona.getApellido_paterno()%>" maxlength="45" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <label class="control-label">Apellido materno:</label>
                                        <input class="form-control" type="text" name="apellido_materno" id="apellido_materno" value="<%=persona.getApellido_materno()%>" maxlength="45"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Dirección:</label>
                                        <input class="form-control" type="text" name="direccion" value="<%=persona.getDireccion()%>" placeholder="ej. carr Oaxaca Puerto Ángel km 97" maxlength="60"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Localidad:</label>
                                        <select class="form-control" name="nombre_localidad" id="nombre_localidad" required="">
                                            <option value="<%=persona.getNombre_localidad()%>"><%=persona.getNombre_localidad()%></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="lado_izquierdo"><!-- Grupo Izquierdo -->
                                    <div class="form-group">
                                        <label class="control-label">Municipio</label>
                                        <select class="form-control" name="nombre_municipio" id="nombre_municipio" required="">
                                            <option value="<%=persona.getNombre_municipio()%>"><%=persona.getNombre_municipio()%></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Estado:</label>
                                        <select class="form-control" name="estado" id="estado" required="">
                                            <option value="<%=persona.getEstado()%>"><%=persona.getEstado()%></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Sexo:</label>
                                        <select class="form-control" name="sexo" id="sexo">
                                            <%if(persona.getSexo().equals("H")){%>
                                                <option selected="" value="H">Hombre</option>
                                                <option value="M">Mujer</option>
                                            <%}else{%>
                                                <option value="H">Hombre</option>
                                                <option selected="" value="M">Mujer</option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-calendar"></i>
                                        <label class="control-label">Fecha de nacimiento:</label>
                                        <input class="form-control" type="date" name="fecha_nacimiento" id="fecha_nacimiento" value="<%=persona.getFecha_nacimiento()%>" required="" maxlength="10">
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-phone"></i>
                                        <label class="control-label">Teléfono:</label>
                                        <input class="form-control" type="text" name="telefono" pattern="[0-9]{10}" value="<%=persona.getTelefono()%>" title="10 dígitos" placeholder="951xxxxxxx" maxlength="10"/>
                                    </div>
                                    <div class="form-group pull-right">
                                        <a href="/aserradero/PersonaController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success" value="Guardar"/>
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
