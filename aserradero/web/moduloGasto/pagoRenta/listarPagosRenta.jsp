<%--
    Document   : pagosrenta
    Created on : 24/09/2016, 11:19:22 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoRenta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <PagoRenta> pagosrenta = (List<PagoRenta>) request.getAttribute("listaPagosRenta");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Renta</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Pagos de renta</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si el registro que busca no aparece, agréguelo</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc">
                                <form method="POST" action="/aserradero/PagoRentaController?action=buscar">
                                    <select name="nombre_campo" class="input-busc" >
                                        <option value="fecha">Fecha</option>
                                        <option value="nombre_persona">Nombre persona</option>
                                        <option value="empleado">Registró</option>
                                        <option value="monto">Monto</option>
                                        <option value="observacion">Observación</option>
                                    </select>
                                    <input type="text" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Nombre persona</th>
                                        <th>Monto</th>
                                        <th>Observación</th>
                                        <th>Registró</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i=0;
                                        for (PagoRenta pago_renta : pagosrenta) {
                                            out.print("<tr>"
                                                +"<td>"+(i+1)+"</td>"
                                                +"<td>"+pago_renta.getFecha()+"</td>"
                                                +"<td>"+pago_renta.getNombre_persona()+"</td>"
                                                +"<td>"+pago_renta.getMonto()+"</td>"
                                                +"<td>"+pago_renta.getObservacion()+"</td>"
                                                +"<td>"+pago_renta.getEmpleado()+"</td>"
                                                +"<td><a class='btn btn-warning' href=\"/aserradero/PagoRentaController?action=modificar&id_pago_renta="+pago_renta.getId_pago_renta()+"\">Modificar</a></td>"
                                                + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoRentaController?action=eliminar&id_pago_renta="+pago_renta.getId_pago_renta()+"';};\">Eliminar</a></td>"
                                            + "</tr>" );
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" value="Agregar" class="btn btn-primary"  onClick=" window.location.href='/aserradero/PagoRentaController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
