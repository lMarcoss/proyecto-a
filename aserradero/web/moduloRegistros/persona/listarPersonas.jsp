<%--
    Document   : personas
    Created on : 16-sep-2016, 19:02:24
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Persona"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Persona> personas = (List<Persona>) request.getAttribute("listaPersonas");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Personas</title>
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
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container" style="margin-top:60px;">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">Lista de personas</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si la persona que busca no aparece en el listado, agréguelo</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group form-busc">
                                <form method="POST" action="/aserradero/PersonaController?action=buscar" class="form-horizontal navbar-form">
                                    <select name="nombre_campo" class="input-busc">
                                        <option value="nombre">Nombre</option>
                                        <option value="localidad">Localidad</option>
                                        <option value="direccion">Dirección</option>
                                        <option value="sexo">Sexo</option>
                                        <option value="fecha_nacimiento">Fecha de nacimiento</option>
                                        <option value="telefono">Telefono</option>
                                    </select>
                                    <input type="text" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                    <input type="submit" value="Buscar" class="btn btn-success">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Nombre</th>
                                        <th>Localidad</th>
                                        <th>Dirección</th>
                                        <th>Sexo</th>
                                        <th>Fecha de nacimiento</th>
                                        <th>Telefono</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% int i = 0;
                                        for (Persona persona : personas) {
                                            out.print("<tr>" + "<td>" + (i + 1)
                                                    + "<td>" + persona.getNombre() + "</td>"
                                                    + "<td><a href=\"/aserradero/LocalidadController?action=buscar_localidad&nombre_localidad=" + persona.getNombre_localidad()+ "\">" + persona.getNombre_localidad()+ "</a></td>"
                                                    + "<td>" + persona.getDireccion() + "</td>"
                                                    + "<td>" + persona.getSexo() + "</td>"
                                                    + "<td>" + persona.getFecha_nacimiento() + "</td>"
                                                    + "<td>" + persona.getTelefono() + "</td>"
                                                    + "<td><a class='btn btn-info' href=\"/aserradero/PersonaController?action=modificar&id_persona=" + persona.getId_persona() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PersonaController?action=eliminar&id_persona=" + persona.getId_persona() + "';};\">Eliminar</a></td>" + "</tr>");
                                            i++;
                                        }%>
                                </tbody>
                            </table>
                            <div class="agregar_element">
                                <input type="button" class="btn btn-primary" value="Agregar persona" onClick=" window.location.href = '/aserradero/PersonaController?action=nuevo'">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
