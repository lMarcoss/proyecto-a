<%--
    Document   : actualizarEntradaMaderaRollo
    Created on : 26/09/2016, 06:08:32 PM
    Author     : rcortes
--%>

<%@page import="entidades.maderaRollo.EntradaMaderaRollo"%>
<%@page import="entidades.registros.Proveedor"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    EntradaMaderaRollo entrada = (EntradaMaderaRollo) request.getAttribute("entrada");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    List<Empleado> choferes = (List<Empleado>) request.getAttribute("choferes");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
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
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/EntradaMaderaRolloController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_entrada" value="<%=entrada.getId_entrada()%>"  readonly=""/>
                                <div class="lado_derecho">
                                    <div class="form-group">
                                        <label class="control-label" for="fecha">Fecha</label>
                                        <input type="date" class="form-control" name="fecha" value="<%=entrada.getFecha()%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="id_proveedor">Proveedor</label>
                                        <select class="form-control" name="id_proveedor" required="" title="Si no existe el proveedor que busca, primero agreguelo en la lista de proveedores">
                                            <%
                                                for (Proveedor proveedor : proveedores) {
                                                    if (entrada.getId_proveedor().equals(proveedor.getId_proveedor())) {
                                                        out.print("<option selected=\"selected\" value='" + proveedor.getId_proveedor() + "'>" + proveedor.getProveedor() + "</option>");
                                                    } else {
                                                        out.print("<option value='" + proveedor.getId_proveedor() + "'>" + proveedor.getProveedor() + "</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="id_chofer">Chofer</label>
                                        <select class="form-control" name="id_chofer" required="" title="Si no existe el empleado que busca, primero agreguelo en la lista de empleados">
                                        <%
                                            for (Empleado chofer : choferes) {
                                                if (entrada.getId_chofer().equals(chofer.getId_empleado())) {
                                                    out.print("<option selected=\"selected\" value='" + chofer.getId_empleado() + "'>" + chofer.getEmpleado() + "</option>");
                                                } else {
                                                    out.print("<option value='" + chofer.getId_empleado() + "'>" + chofer.getEmpleado() + "</option>");
                                                }
                                            }
                                        %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="num_piezas">Num. piezas primario</label>
                                        <input type="number" class="form-control" name="num_pieza_primario" value="<%=entrada.getNum_pieza_primario()%>" min="0" required=""/>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">Volumen primario</label>
                                        <input type="number" class="form-control" name="volumen_primario" id="volumen" value="<%=entrada.getVolumen_primario()%>" step=".001" min="0.000" max="999999.999" required=""/>
                                    </div>
                                </div>
                                <div class="lado_izquierdo">
                                    <div class="form-group">
                                        <label class="control-label" for="num_piezas">Num. piezas secundario</label>
                                        <input type="number" class="form-control" name="num_pieza_secundario" value="<%=entrada.getNum_pieza_secundario()%>" min="0" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen secundario</label>
                                        <input type="number" class="form-control" name="volumen_secundario" id="volumen" value="<%=entrada.getVolumen_secundario()%>" step=".001" min="0.000" max="999999.999" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="num_piezas">Num. piezas terciario</label>
                                        <input type="number" class="form-control" name="num_pieza_terciario" value="<%=entrada.getNum_pieza_terciario()%>" min="0" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Volumen terciario</label>
                                        <input type="number" class="form-control" name="volumen_terciario" id="volumen" value="<%=entrada.getVolumen_terciario()%>" step=".001" min="0.000" max="999999.999" required=""/>
                                    </div>
                                    <div class="form-group pull-right">
                                        <a href="/aserradero/EntradaMaderaRolloController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                        <input type="submit" class="btn btn-success" value="Guardar"/>
                                    </div>
                                </div>
                            </form>
                        </div><!--panel-body-->
                    </div><!--panel full-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--container-->
    </body>
</html>
