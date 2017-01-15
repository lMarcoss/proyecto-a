<%--
    Document   : nuevoPagoEmpleado
    Created on : 29/09/2016, 08:15:45 AM
    Author     : Marcos
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    List <Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
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
                    <h2>Nuevo pago a empleado</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PagoEmpleadoController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input class="form-control" type="date" name="fecha" required="" />
                                </div>
                                <div class="form-group">
                                    <label class="control-label">empleado:</label>
                                    <select class="form-control" name="id_empleado" required="" title="Si no existe el empleado que busca, primero agreguelo en la lista de empleados">
                                        <option></option>
                                        <%
                                            for (Empleado empleado : empleados) {
                                                out.print("<option value='"+empleado.getId_empleado()+"'>"+empleado.getEmpleado()+"</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input class="form-control" name="monto" type="number" min='0.01' max='99999999.99' step=".01" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Observacion:</label>
                                    <input class="form-control" name="observacion" type="text"/>
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
    </body>
</html>
