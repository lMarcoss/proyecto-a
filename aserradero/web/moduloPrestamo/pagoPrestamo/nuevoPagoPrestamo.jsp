<%--
    Document   : nuevoPagoPrestamo
    Created on : 27-nov-2016, 22:27:07
    Author     : lmarcoss
--%>

<%@page import="entidades.prestamo.Prestamo"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Prestamo> listaPrestamo = (List<Prestamo>) request.getAttribute("listaPrestamo");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <script src="/aserradero/js/selectorPagoPrestamo.js"></script>
        <title>Nuevo pago</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo registro</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoPrestamoController?action=insertar" method="post" id="formregistro">
                            <%
                                if(!listaPrestamo.isEmpty()){
                            %>
                            <div class="lado_derecho">
                                <div class="form-group">
                                    <label class="control-label">Préstamo a pagar:</label>

                                        <select class="form-control"  name="id_prestamo" id="id_prestamo" required="" title="Seleccione el préstamo" onblur="seleccionarPagoPrestamo()">
                                            <option></option>
                                            <%
                                                for(Prestamo prestamo: listaPrestamo){
                                                    out.print("<option value='"+prestamo.getId_prestamo()+"'>Prestado el "+prestamo.getFecha()+" por "+prestamo.getPrestador()+"</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto préstamo:</label>

                                        <select class="form-control"  name="monto_prestamo" id="monto_prestamo" required="" disabled="">
                                            <option></option>
                                            <%
                                                for(Prestamo prestamo: listaPrestamo){
                                                    out.print("<option value='"+prestamo.getMonto_prestamo()+"'>"+prestamo.getMonto_prestamo()+"</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Fecha de préstamo:</label>

                                        <select class="form-control"  name="fecha_prestamo" id="fecha_prestamo" required="" disabled="">
                                            <option></option>
                                            <%
                                                for(Prestamo prestamo: listaPrestamo){
                                                    out.print("<option value='"+prestamo.getFecha()+"'>"+prestamo.getFecha()+"</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto pagado:</label>

                                        <select class="form-control"  name="monto_pagado" id="monto_pagado" required="" disabled="">
                                            <option></option>
                                            <%
                                                for(Prestamo prestamo: listaPrestamo){
                                                    out.print("<option value='"+prestamo.getMonto_pagado()+"'>"+prestamo.getMonto_pagado()+"</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                            </div>
                            <div class="lado_izquierdo">                                
                                <div class="form-group">
                                    <label class="control-label">Monto por pagar:</label>

                                        <select class="form-control"  name="monto_por_pagar" id="monto_por_pagar" required="" disabled="">
                                            <option></option>
                                            <%
                                                for(Prestamo prestamo: listaPrestamo){
                                                    out.print("<option value='"+prestamo.getMonto_por_pagar()+"'>"+prestamo.getMonto_por_pagar()+"</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label for="fecha">Fecha de pago:</label>
                                    <input type="date" class="form-control"  name="fecha" required="" placeholder="AAAA/MM/DD"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto a pagar:</label>
                                    <input type="number" class="form-control"  step="0.01" name="monto_pago" id="monto_pago" min="0.01" max="" required="">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PagoPrestamoController?action=listar"><input type="button" value="Cancelar" class="btn btn-warning"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar">
                                </div>
                            </div>
                                
                                
                                
                                <%
                                    }else{
                                        out.println("<p style='color:red;'>No hay prestamos pendientes por pagar.</p>");
                                %>
                                <div class="form-group">
                                    <a href="/aserradero/PagoPrestamoController?action=listar"><input type="button" value="Aceptar" class="btn btn-success"/></a>
                                </div>
                                <%
                                    }
                                %>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
