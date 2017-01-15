<%--
    Document   : otrosgastos
    Created on : 26/09/2016, 02:42:29 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.OtroGasto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<OtroGasto> otrosgastos = (List<OtroGasto>) request.getAttribute("listaOtrosGasto");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Otros gastos</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Otros Gastos</h2>
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
                                <form method="POST" action="/aserradero/OtroGastoController?action=buscar">
                                    <select name="nombre_campo" class="input-busc">
                                        <option value="fecha">Fecha</option>
                                        <option value="nombre_gasto">Nombre de gasto</option>
                                        <option value="monto">Monto</option>
                                        <option value="observacion">Observación</option>
                                        <option value="empleado">Registró</option>
                                    </select>
                                    <input type="text" name="dato" class="input-busc" placeholder="Escriba su búsqueda">
                                    <input type="submit" value="Buscar" class="btn btn-success">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Nombre de gasto</th>
                                        <th>Monto</th>
                                        <th>observación</th>
                                        <th>Registró</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (OtroGasto otrogasto : otrosgastos) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + otrogasto.getFecha() + "</td>"
                                                    + "<td>" + otrogasto.getNombre_gasto() + "</td>"
                                                    + "<td>" + otrogasto.getMonto() + "</td>"
                                                    + "<td>" + otrogasto.getObservacion() + "</td>"
                                                    + "<td>" + otrogasto.getEmpleado() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/OtroGastoController?action=modificar&id_gasto=" + otrogasto.getId_gasto() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/OtroGastoController?action=eliminar&id_gasto=" + otrogasto.getId_gasto() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div  class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Registrar otro gasto" onClick=" window.location.href = '/aserradero/OtroGastoController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->

        <!-- ************************** opción de búsqueda-->
        <div>

                <table>
                    <tr>
                        <td>

                        </td>
                        <td></td>
                        <td colspan="2"></td>
                    </tr>
                </table>
            </form>
        </div> <!-- Fin opción de búsqueda-->

        <!-- ************************* Resultado Consulta-->
        <div>
            <table class="table-condensed">


            </table>

        </div><!-- Resultado Consulta-->
    </body>
</html>
