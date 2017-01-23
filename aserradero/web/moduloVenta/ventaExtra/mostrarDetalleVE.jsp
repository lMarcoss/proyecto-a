<%--
    Document   : mostrarDetalleVE
    Created on : 16-nov-2016, 15:27:49
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaExtra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <VentaExtra> detalles = (List<VentaExtra>) request.getAttribute("detalles");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas </title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Detalle de venta extra</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Detalles de la venta</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Venta</th>
                                        <th>Monto</th>
                                        <th>Observacion</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i=0;
                                        for (VentaExtra detalle : detalles) {
                                            out.print("<tr>"
                                                +"<td>"+(i+1)+"</td>"
                                                +"<td>"+detalle.getTipo()+"</td>"
                                                +"<td>"+detalle.getMonto()+"</td>"
                                                +"<td>"+detalle.getObservacion()+"</td>"
                                                +"<td><a class='btn btn-warning' href=\"/aserradero/VentaExtraController?action=modificar&id_venta="+detalle.getId_venta()+"&tipo="+detalle.getTipo()+"\">Modificar</a></td>"
                                                + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaExtraController?action=eliminar&id_venta="+detalle.getId_venta()+"&tipo="+detalle.getTipo()+"';};\">Eliminar</a></td>"
                                            + "</tr>" );
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Regresar" onClick=" window.location.href='/aserradero/VentaExtraController?action=listar' ">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
