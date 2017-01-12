<%--
    Document   : reporte
    Created on : 26/09/2016, 06:08:14 PM
    Author     : rcortes
--%>

<%@page import="entidadesVirtuales.ReporteCompra"%>
<%@page import="entidades.Compra"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String mensaje = (String)request.getAttribute("mensaje"); %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Reporte de compras</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"></h3>
                        </div>
                        <div class="panel-body">

                        </div><!--panel-body-->
                    </div><!--panel full-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--container-->
        <!-- ************************** opción de búsqueda-->
        <div>

        </div> <!-- Fin opción de búsqueda-->
        <!-- ************************* Resultado Consulta-->
<!--        <div class="col-md-12">
            <table id="tabla" class="display" cellspacing="0" width="100%">-->
        <div>
            <table class="table-condensed">
                <thead>
                    <tr>
                        <th>N°</th>
                        <th>Id compra</th>
                        <th>Fecha</th>
                        <th>Piezas</th>
                        <th>Proveedor</th>
                        <th>Chofer</th>
                        <th>Empleado</th>
                        <th>V. primario</th>
                        <th>V. secundario</th>
                        <th>V. terciario</th>
                        <th>V. Total</th>
                        <th>Monto total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List <ReporteCompra> datosReporte = (List<ReporteCompra>) request.getAttribute("datos_reporte");
                        int i=0;
                        for (ReporteCompra reporte : datosReporte) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                +"<td>"+reporte.getId_compra()+"</td>"
                                +"<td>"+reporte.getFecha()+"</td>"
                                +"<td>"+reporte.getNum_piezas()+"</td>"
                                +"<td>"+reporte.getNombre_proveedor()+"</td>"
                                +"<td>"+reporte.getNombre_chofer()+"</td>"
                                +"<td>"+reporte.getNombre_empleado()+"</td>"
                                +"<td>"+reporte.getVol_primario()+"</td>"
                                +"<td>"+reporte.getVol_secundario()+"</td>"
                                +"<td>"+reporte.getVol_terciario()+"</td>"
                                +"<td>"+reporte.getVolumen_total()+"</td>"
                                +"<td>"+reporte.getMonto_total()+"</td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
                </tbody>
            </table>
        </div><!-- Resultado Consulta-->
    </body>
</html>
