<%--
    Document   : compras
    Created on : 26/09/2016, 06:08:14 PM
    Author     : rcortes
--%>

<%@page import="entidades.maderaRollo.EntradaMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<EntradaMaderaRollo> entradas = (List<EntradaMaderaRollo>) request.getAttribute("listaEntradaMaderaRollo");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>EntradaMaderaRollos</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>Listado de entrada de madera en rollos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si no aparece el registro que busca, agréguelo</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc">
                                <form method="POST" action="/aserradero/EntradaMaderaRolloController?action=buscar">
                                    <select name="nombre_campo" class="input-busc">
                                        <option value="fecha">Fecha</option>
                                        <option value="proveedor">Proveedor</option>
                                        <option value="chofer">Chofer</option>
                                        <option value="empleado">Registró</option>
                                        <option value="num_pieza_primario">Número de piezas primario</option>
                                        <option value="volumen_primario">Volumen primario</option>
                                        <option value="num_pieza_secundario">Número de piezas secundario</option>
                                        <option value="volumen_secundario">Volumen secundario</option>
                                        <option value="num_pieza_terciario">Número de piezas terciario</option>
                                        <option value="volumen_terciario">Volumen terciario</option>
                                        <option value="num_pieza_total">Total piezas</option>
                                        <option value="volumen_total">Volumen total</option>
                                        <option value="costo_total">Costo total</option>
                                    </select>
                                    <input type="text" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                    <input type="submit" value="Buscar" class="btn btn-success">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <th>N°</th>
                                    <th>Fecha</th>
                                    <th>Proveedor</th>
                                    <th>Chofer</th>
                                    <th>Registró</th>
                                    <th>piezas primario</th>
                                    <th>Vol. primario</th>
                                    <th>piezas secundario</th>
                                    <th>Vol. secundario</th>
                                    <th>piezas terciario</th>
                                    <th>Vol. Terciario</th>
                                    <th>Total piezas</th>
                                    <th>Vol. total</th>
                                    <th>Costo total</th>
                                    <th></th>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (EntradaMaderaRollo entrada : entradas) {
                                            out.print("<tr>"
                                            + "<td>" + (i + 1) + "</td>"
                                            + "<td>" + entrada.getFecha() + "</td>"
                                            + "<td>" + entrada.getProveedor() + "</td>"
                                            + "<td>" + entrada.getChofer() + "</td>"
                                            + "<td>" + entrada.getEmpleado() + "</td>"
                                            + "<td>" + entrada.getNum_pieza_primario() + "</td>"
                                            + "<td>" + entrada.getVolumen_primario() + "</td>"
                                            + "<td>" + entrada.getNum_pieza_secundario() + "</td>"
                                            + "<td>" + entrada.getVolumen_secundario() + "</td>"
                                            + "<td>" + entrada.getNum_pieza_terciario() + "</td>"
                                            + "<td>" + entrada.getVolumen_terciario() + "</td>"
                                            + "<td>" + entrada.getNum_pieza_total() + "</td>"
                                            + "<td>" + entrada.getVolumen_total() + "</td>"
                                            + "<td>" + entrada.getCosto_total() + "</td>"
                                            + "<td><a class='btn btn-warning' href=\"/aserradero/EntradaMaderaRolloController?action=modificar&id_entrada=" + entrada.getId_entrada() + "\">Modificar</a></td>"
                                            //                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/EntradaMaderaRolloController?action=eliminar&id_entrada="+entrada.getId_entrada()+"';};\">Eliminar</a></td>"
                                            + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar_element">
                                <input type="button" class="btn btn-primary" value="Registrar entrada" onClick=" window.location.href = '/aserradero/EntradaMaderaRolloController?action=nuevo'">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
