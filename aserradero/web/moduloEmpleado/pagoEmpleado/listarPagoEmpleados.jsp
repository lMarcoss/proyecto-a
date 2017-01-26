<%--
    Document   : pagoEmpleados
    Created on : 29/09/2016, 08:02:31 AM
    Author     : Marcos
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="entidades.empleado.PagoEmpleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    List<PagoEmpleado> pagoEmpleados = (List<PagoEmpleado>) request.getAttribute("listaPagoEmpleados");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pago empleados</title>
    </head>
    <body>
        <!--menu-->
        <%if (((String) sesion.getAttribute("rol")).equals("Administrador")) {%>
        <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%} else {%>
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Registros de pagos a empleados</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si el registro  que busaca no aparece, agreguelo</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Empleado</th>
                                        <th>Monto</th>
                                        <th>Observacion</th>
                                        <th> </th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        DecimalFormat form=new DecimalFormat("###,###.##");
                                        int i = 0;
                                        for (PagoEmpleado pagoEmpleado : pagoEmpleados) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + pagoEmpleado.getFecha() + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + pagoEmpleado.getId_empleado() + "\">" + pagoEmpleado.getEmpleado() + "</a></td>"
                                                    + "<td>" + form.format(pagoEmpleado.getMonto()) + "</td>"
                                                    + "<td>" + pagoEmpleado.getObservacion() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/PagoEmpleadoController?action=modificar&id_pago_empleado=" + pagoEmpleado.getId_pago_empleado() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn-danger btn' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoEmpleadoController?action=eliminar&id_pago_empleado=" + pagoEmpleado.getId_pago_empleado() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Agregar pago empleado" onClick=" window.location.href = '/aserradero/PagoEmpleadoController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
