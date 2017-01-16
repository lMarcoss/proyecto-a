<%--
    Document   : mostrarDetalleVP
    Created on : 4/12/2016, 04:31:29 PM
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaPaquete"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <VentaPaquete> detalles = (List<VentaPaquete>) request.getAttribute("detalles");
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
                    <h2>Detalle de venta</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Detalle de venta por paquete</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Paquete</th>
                                        <th>Madera</th>
                                        <th>Núm piezas</th>
                                        <th>Volúmen</th>
                                        <th>Costo</th>
                                        <th>Tipo madera</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i=0;
                                        for (VentaPaquete detalle : detalles) {
                                            out.print("<tr>"
                                                +"<td>"+(i+1)+"</td>"
                                                +"<td>"+detalle.getNumero_paquete()+"</td>"
                                                +"<td>"+detalle.getId_madera()+"</td>"
                                                +"<td>"+detalle.getNum_piezas()+"</td>"
                                                +"<td>"+detalle.getVolumen()+"</td>"
                                                +"<td>"+detalle.getMonto()+"</td>"
                                                +"<td>"+detalle.getTipo_madera()+"</td>"
                                                +"<td><a class='btn btn-warning' href=\"/aserradero/VentaPaqueteController?action=modificar&id_venta="+detalle.getId_venta()+"&numero_paquete="+detalle.getNumero_paquete()+"&id_madera="+detalle.getId_madera()+"&tipo_madera="+detalle.getTipo_madera()+"\">Modificar</a></td>"
                                                +"<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaPaqueteController?action=eliminar&id_venta="+detalle.getId_venta()+"&numero_paquete="+detalle.getNumero_paquete()+"&id_madera="+detalle.getId_madera()+"&tipo_madera="+detalle.getTipo_madera()+"';};\">Eliminar</a></td>"
                                            + "</tr>" );
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Regresar" onClick=" window.location.href='/aserradero/VentaPaqueteController?action=listar' ">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
