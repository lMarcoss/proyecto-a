<%--
    Document   : clientes
    Created on : 27-sep-2016, 1:03:55
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("listaClientes");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Clientes</title>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#clientes").css("background", "#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>" />
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">LISTADO DE CLIENTES</h2>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Si el cliente que busca no aparece, agréguelo</h3>
                    </div>
                    <div class="panel-body">
                        <table id="tabla" class="display cell-border" cellspacing="0" width="100%"><!-- Tabla que muestra los resultados de la consulta a la base de datos-->
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th>Cliente</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i = 0;
                                    for (Cliente cliente : clientes) {
                                        out.print("<tr>"
                                                + "<td>" + (i + 1) + "</td>"
                                                + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + cliente.getId_cliente() + "\">" + cliente.getCliente() + "</a></td>"
                                                + "<td><a class=\"btn btn-danger\" href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/ClienteController?action=eliminar&id_cliente=" + cliente.getId_cliente() + "';};\">Eliminar</a></td>"
                                                + "</tr>");
                                        i++;
                                    }
                                %>
                            </tbody>
                        </table><!-- Fin de tabla -->
                        <div class="agregar_element"><!-- Botón agregar elementos -->
                            <input type="button" class="btn btn-primary" value="Agregar cliente" onClick=" window.location.href = '/aserradero/ClienteController?action=nuevo'">
                        </div><!-- Fin Agregar elementos-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
