<%--
    Document   : actualizarPagoEmpleado
    Created on : 30/09/2016, 08:48:10 AM
    Author     : Marcos
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.empleado.PagoEmpleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    PagoEmpleado pagoEmpleado = (PagoEmpleado) request.getAttribute("pagoEmpleado");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%if(((String)sesion.getAttribute("rol")).equals("Administrador")){%>
            <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%}else{%>
            <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actualización de datos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los datos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoEmpleadoController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_pago_empleado" value="<%=pagoEmpleado.getId_pago_empleado()%>" readonly="">
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input class="form-control" type="date" name="fecha" value="<%=pagoEmpleado.getFecha()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Empleado:</label>
                                    <select class="form-control" name="id_empleado">
                                        <option value="<%= pagoEmpleado.getId_empleado()%>"><%= pagoEmpleado.getEmpleado()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input class="form-control" type="number" name="monto" value="<%=pagoEmpleado.getMonto()%>" step="0.01" min="0.01" max="99999999.99" required="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Observacion:</label>
                                    <input class="form-control" type="text" name="observacion" value="<%=pagoEmpleado.getObservacion()%>" maxlength="249">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PagoEmpleadoController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->

        <div>


    </body>
</html>
