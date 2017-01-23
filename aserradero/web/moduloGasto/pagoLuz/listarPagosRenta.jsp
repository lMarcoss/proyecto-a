<%--
    Document   : pagosluz
    Created on : 26/09/2016, 12:30:04 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoLuz"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<PagoLuz> pagosluz = (List<PagoLuz>) request.getAttribute("listaPagosRenta");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pagos de luz</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Pago de luz</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si el registro que busca no aparece, agréguelo</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Monto</th>
                                        <th>Observación</th>
                                        <th>Registró</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (PagoLuz pagoluz : pagosluz) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + pagoluz.getFecha() + "</td>"
                                                    + "<td>" + pagoluz.getMonto() + "</td>"
                                                    + "<td>" + pagoluz.getObservacion() + "</td>"
                                                    + "<td>" + pagoluz.getEmpleado() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/PagoLuzController?action=modificar&id_pago_luz=" + pagoluz.getId_pago_luz() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoLuzController?action=eliminar&id_pago_luz=" + pagoluz.getId_pago_luz() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Registrar pago luz" onClick=" window.location.href = '/aserradero/PagoLuzController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
