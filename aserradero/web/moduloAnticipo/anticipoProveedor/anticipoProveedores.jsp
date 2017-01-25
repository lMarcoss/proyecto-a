<%--
    Document   : anticipoProveedores
    Created on : 27/09/2016, 01:14:24 PM
    Author     : Marcos
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="entidades.anticipo.AnticipoProveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<AnticipoProveedor> anticipoProveedores = (List<AnticipoProveedor>) request.getAttribute("listaAnticipos");
    String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Anticipo proveedores</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Anticipo de proveedores</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si el registro que busca no aparece, agregélo</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Proveedor</th>
                                        <th>Monto anticipo </th>
                                        <th>Registró</th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        DecimalFormat form=new DecimalFormat("###,###.##");
                                        int i = 0;
                                        for (AnticipoProveedor anticipoProveedor : anticipoProveedores) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + anticipoProveedor.getFecha() + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoProveedor.getId_proveedor() + "\">" + anticipoProveedor.getProveedor() + "</a></td>"
                                                    + "<td>" + form.format(anticipoProveedor.getMonto_anticipo()) + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoProveedor.getId_empleado() + "\">" + anticipoProveedor.getEmpleado() + "</a></td>"
                                                    + "<td><a class='btn btn-warning'  href=\"/aserradero/AnticipoProveedorController?action=modificar&id_anticipo_p=" + anticipoProveedor.getId_anticipo_p() + "\">Modificar</a></td>"
                                                    //                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/AnticipoProveedorController?action=eliminar&id_anticipo_p="+anticipoProveedor.getId_anticipo_p()+"';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Agregar anticipo proveedor" onClick=" window.location.href = '/aserradero/AnticipoProveedorController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
