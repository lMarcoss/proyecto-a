<%--
    Document   : municipios
    Created on : 13-sep-2016, 13:13:32
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Localidad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Localidad> localidades = (List<Localidad>) request.getAttribute("listaLocalidades");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
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
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container" >
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">LISTADO DE LOCALICADES</h2>
                </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Si la persona que busca no aparece en el listado, agréguelo</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-busc" >
                            <form method="POST" action="/aserradero/LocalidadController?action=buscar">
                                <select name="nombre_campo" class="input-busc">
                                    <option value="nombre_localidad">Localidad</option>
                                    <option value="nombre_municipio">Municipio</option>
                                    <option value="telefono">Teléfono</option>
                                </select>
                                <input type="text" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                <input type="submit" value="Buscar" class="btn btn-success">
                            </form>
                        </div><!-- panel de búsqueda-->
                        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th>Localidad</th>
                                    <th>Municipio</th>
                                    <th>Teléfono</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i=0;
                                    for (Localidad localidad : localidades) {
                                        out.print("<tr>"
                                            +"<td>"+(i+1)+"</td>"
                                            +"<td>"+localidad.getNombre_localidad()+"</td>"
                                            +"<td><a href=\"/aserradero/MunicipioController?action=buscar_municipio&nombre_municipio="+localidad.getNombre_municipio()+"&estado="+localidad.getEstado()+"\">"+localidad.getNombre_municipio()+"</a></td>"
                                            +"<td>"+localidad.getTelefono_localidad()+"</td>"
                                            +"<td><a class=\"btn btn-warning\" href=\"/aserradero/LocalidadController?action=modificar&nombre_localidad="+localidad.getNombre_localidad()+"\">Modificar</a></td>"
                                            + "<td><a class=\"btn btn-danger\" href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/LocalidadController?action=eliminar&nombre_localidad="+localidad.getNombre_localidad()+"';};\">Eliminar</a></td>"
                                        + "</tr>" );
                                        i++;
                                    }
                                %>
                            </tbody>
                        </table>
                        <div class="agregar_element">
                            <input type="button" class="btn btn-primary" value="Agregar localidad" onClick=" window.location.href='/aserradero/LocalidadController?action=nuevo' ">
                        </div>
                    </div>
                </div><!-- panel primario -->
              </div>
            </div>
        </div>
    </body>
</html>
