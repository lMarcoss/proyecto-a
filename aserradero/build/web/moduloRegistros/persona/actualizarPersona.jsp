<%--
    Document   : actualizarPersona
    Created on : 16-sep-2016, 19:48:04
    Author     : lmarcoss
--%>

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
                    <h2>Modificar datos de una persona (dirección y teléfono)</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si necesitas cambiar datos personales, elimina esta persona y vuelvelo a registrar con los nuevos datos</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PersonaController?action=actualizar" method="post" id="formregistro">
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label" >Id persona:</label>
                                        <input type="text" class="form-control" name="id_persona" value="<%=persona.getId_persona()%>" title="sólo lectura" maxlength="18" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" >Nombre:</label>
                                        <input type="text" class="form-control" name="nombre" value="<%=persona.getNombre()%>"  pattern="[A-Za-z].{2,}" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" >Localidad:</label>
                                        <input type="text" class="form-control" name="nombre_localidad" value="<%=persona.getNombre_localidad()%>" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" >Municipio:</label>
                                        <input type="text" class="form-control" name="nombre_municipio" value="<%=persona.getNombre_municipio()%>" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" >Estado:</label>
                                        <input type="text" class="form-control" name="estado" value="<%=persona.getEstado()%>" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required="" readonly=""/>
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label" >Dirección:</label>
                                        <input type="text" class="form-control" name="direccion" value="<%=persona.getDireccion()%>" title="dirección" placeholder="ej. carr Oaxaca Puerto Ángel km 97" maxlength="60"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" >Sexo:</label>
                                        <select name="sexo" class="form-control">
                                            <option value="<%=persona.getSexo()%>"><%=persona.getSexo()%></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-calendar"></i>
                                        <label class="control-label" >Fecha de nacimiento:</label>
                                        <input type="date" class="form-control" value="<%=persona.getFecha_nacimiento()%>" name="fecha_nacimiento" required="" readonly=""  maxlength="10" title="Es importante la fecha de nacimiento para asignar un identificador a la persona"/>
                                    </div>
                                    <div class="form-group">
                                        <i class="glyphicon glyphicon-phone"></i>
                                        <label class="control-label" >Teléfono:</label>
                                        <input type="text" class="form-control" name="telefono" value="<%=persona.getTelefono()%>" pattern="[0-9]{10}" title="10 dígitos" maxlength="10"/>
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
