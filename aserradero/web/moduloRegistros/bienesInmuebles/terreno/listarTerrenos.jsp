<%--
    Document   : terrenos
    Created on : 18/12/2016, 04:46:44 PM
    Author     : lmarcoss
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="entidades.registros.bienesInmuebles.Terreno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Terreno> terrenos = (List<Terreno>) request.getAttribute("listaTerrenos");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Terrenos</title>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#vehiculos").css("background", "#448D00");
                $("#terrenos").css("background", "#448D00");
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
                    <h2 class="page-header">Listado de terrenos</h2>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Si el terreno no aparece, agréguelo</h3>
                    </div>
                    <div class="panel-body">
                        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th>Nombre</th>
                                    <th>Dimension</th>
                                    <th>Direccion</th>
                                    <th>Localidad</th>
                                    <th>Valor estimado</th>
                                    <th>Registró</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    DecimalFormat form=new DecimalFormat("###,###.##");
                                    int i = 0;
                                    for (Terreno terreno : terrenos) {
                                        out.print("<tr>"
                                                + "<td>" + (i + 1) + "</td>"
                                                + "<td>" + terreno.getNombre() + "</td>"
                                                + "<td>" + terreno.getDimension() + "</td>"
                                                + "<td>" + terreno.getDireccion() + "</td>"
                                                + "<td>" + terreno.getNombre_localidad() + ", " + terreno.getNombre_municipio() + "</td>"
                                                + "<td>" + form.format(terreno.getValor_estimado()) + "</td>"
                                                + "<td>" + terreno.getEmpleado() + "</td>"
                                                + "<td><a class=\"btn btn-warning\" href=\"/aserradero/TerrenoController?action=modificar&id_terreno=" + terreno.getId_terreno() + "\">Modificar</a></td>"
                                                + "<td><a class=\"btn btn-danger\" href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/TerrenoController?action=eliminar&id_terreno=" + terreno.getId_terreno() + "';};\">Eliminar</a></td>"
                                                + "</tr>");
                                        i++;
                                    }
                                %>
                            </tbody>
                        </table>
                        <div class="agregar_element">
                            <input type="button" class="btn btn-primary" value="Registrar terreno" onClick=" window.location.href = '/aserradero/TerrenoController?action=nuevo'">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
