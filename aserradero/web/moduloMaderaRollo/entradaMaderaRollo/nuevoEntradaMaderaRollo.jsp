<%--
    Document   : nuevaEntradaMaderaRollo
    Created on : 26/09/2016, 06:08:43 PM
    Author     : rcortes
--%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.registros.Proveedor"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    List<Empleado> choferes = (List<Empleado>) request.getAttribute("choferes");
    Date fechaActual = (Date) request.getAttribute("fechaActual");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Agregar una nueva entrada de madera</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene todos los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/EntradaMaderaRolloController?action=insertar" method="POST" class="control-form">
                                <div class="lado_derecho"><!-- Grupo derecho-->
                                    <div class="form-group">
                                        <label class="control-label" for="fecha">Fecha</label>
                                        <input class="form-control" type="date" name="fecha" value="<%=fechaActual%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Proveedor</label>
                                        <select class="form-control" name="id_proveedor" required="" title="Si no existe el proveedor que busca, primero agreguelo en la lista de proveedores">
                                            <option></option>
                                            <%
                                                for (Proveedor proveedor : proveedores) {
                                                    out.print("<option value='" + proveedor.getId_proveedor() + "'>" + proveedor.getProveedor() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Chofer</label>
                                        <select class="form-control" name="id_chofer" required="" title="Si no existe el empleado que busca, primero agreguelo en la lista de empleados">
                                            <option></option>
                                            <%
                                                for (Empleado chofer : choferes) {
                                                    out.print("<option value='" + chofer.getId_empleado() + "'>" + chofer.getEmpleado() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Num. piezas primario</label>
                                        <input class="form-control" type="number" name="num_pieza_primario" min="0" max="999" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen primario</label>
                                        <input class="form-control" type="number" name="volumen_primario" id="volumen" step=".001" min="0.000" max="999999.999"  required="" />
                                    </div>
                                </div>
                                <div class="lado_izquierdo"><!-- Grupo izquierdo-->
                                    <div class="form-group">
                                        <label class="control-label">Num. piezas secundario</label>
                                        <input class="form-control" type="number" name="num_pieza_secundario" min="0" max="999" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen secundario</label>
                                        <input class="form-control" type="number" name="volumen_secundario" id="volumen" step=".001" min="0.000" max="999999.999"  required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Num. piezas terciario</label>
                                        <input class="form-control" type="number" name="num_pieza_terciario" min="0" max="999" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen terciario</label>
                                        <input class="form-control" type="number" name="volumen_terciario" id="volumen" step=".001" min="0.000" max="999999.999"  required=""/>
                                    </div>
                                    <div class="form-group">
                                        <a href="/aserradero/EntradaMaderaRolloController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                        <input type="submit" value="Guardar" class="btn btn-success"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
