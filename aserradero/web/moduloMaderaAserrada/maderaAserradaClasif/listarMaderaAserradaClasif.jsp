<%--
    Document   : maderaClasificaciones
    Created on : 27-sep-2016, 2:44:58
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.MaderaAserradaClasif"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MaderaAserradaClasif> listaMaderaAserradaClasif = (List<MaderaAserradaClasif>) request.getAttribute("listaMaderaAserradaClasif");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Clasificación madera aserrada</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Clasificación de madera aserrada</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si la clasificación no aparece en la lista agreguela</h3>
                        </div>
                        <div class="panel-body">
                            <form method="POST" action="/aserradero/MaderaAserradaClasifController?action=buscar">
                                <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>N°</th>
                                            <th>Madera</th>
                                            <th>Grueso</th>
                                            <th>Ancho</th>
                                            <th>Largo</th>
                                            <th>Volumen unitario</th>
                                            <th>Costo por volumen</th>
                                            <th>Registró</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            int i = 0;
                                            for (MaderaAserradaClasif maderaClasificacion : listaMaderaAserradaClasif) {
                                                out.print("<tr>"
                                                        + "<td>" + (i + 1) + "</td>"
                                                        + "<td>" + maderaClasificacion.getId_madera() + "</td>"
                                                        + "<td>" + maderaClasificacion.getGrueso_f() + "</td>"
                                                        + "<td>" + maderaClasificacion.getAncho_f() + "</td>"
                                                        + "<td>" + maderaClasificacion.getLargo_f() + "</td>"
                                                        + "<td>" + maderaClasificacion.getVolumen() + "</td>"
                                                        + "<td>" + maderaClasificacion.getCosto_por_volumen() + "</td>"
                                                        + "<td>" + maderaClasificacion.getEmpleado() + "</td>"
                                                        + "<td><a class='btn btn-warning' href=\"/aserradero/MaderaAserradaClasifController?action=modificar&id_madera=" + maderaClasificacion.getId_madera() + "\">Modificar</a></td>"
                                                        + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/MaderaAserradaClasifController?action=eliminar&id_madera=" + maderaClasificacion.getId_madera() + "';};\">Eliminar</a></td>"
                                                        + "</tr>");
                                                i++;
                                            }
                                        %>
                                    </tbody>
                                </table>
                                <div class="agregar_element">
                                    <input type="button" class="btn btn-primary" value="Registrar clasificación" onClick=" window.location.href = '/aserradero/MaderaAserradaClasifController?action=nuevo'">
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
