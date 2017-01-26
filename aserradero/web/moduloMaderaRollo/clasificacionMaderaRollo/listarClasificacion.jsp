<%--
    Document   : costomaderacompras
    Created on : 26/09/2016, 11:14:22 PM
    Author     : rcortes
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="entidades.maderaRollo.ClasificacionMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<ClasificacionMaderaRollo> listaClasificacion = (List<ClasificacionMaderaRollo>) request.getAttribute("listaClasificacion");
    String mensaje = (String) request.getAttribute("mensaje");
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
        <input hidden="" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Costo de madera en rollo por proveedor</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Agregue las clasificaciones si no existen en la lista</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Proveedor</th>
                                        <th>Clasificación</th>
                                        <th>Costo_volumen</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        DecimalFormat form=new DecimalFormat("###,###.##");
                                        int i = 0;
                                        for (ClasificacionMaderaRollo clasificacion : listaClasificacion) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + clasificacion.getProveedor() + "</td>"
                                                    + "<td>" + clasificacion.getClasificacion() + "</td>"
                                                    + "<td>" + form.format(clasificacion.getCosto()) + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/ClasificacionMaderaRolloController?action=modificar&id_proveedor=" + clasificacion.getId_proveedor() + "&clasificacion=" + clasificacion.getClasificacion() + "\">Cambiar costo</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/ClasificacionMaderaRolloController?action=eliminar&id_proveedor=" + clasificacion.getId_proveedor() + "&clasificacion=" + clasificacion.getClasificacion() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar_element">
                                <input type="button" class="btn btn-primary" value="Agregar" onClick=" window.location.href = '/aserradero/ClasificacionMaderaRolloController?action=nuevo'">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
