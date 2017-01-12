<%--
    Document   : salidaMaderaRollos
    Created on : 27-oct-2016, 22:46:40
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaRollo.SalidaMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<SalidaMaderaRollo> salidas = (List<SalidaMaderaRollo>) request.getAttribute("listaSalidaMaderaRollo");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Salidas</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Listado de salida de madera en rollos</h2>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Si el registro que busca no aparece, agréguelo</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-busc">
                            <form method="POST" action="/aserradero/SalidaMaderaRolloController?action=buscar">
                                <select name="nombre_campo" class="input-busc">
                                    <option value="fecha">Fecha</option>
                                    <option value="num_pieza_primario">Piezas primario</option>
                                    <option value="volumen_primario">Volumen primario</option>
                                    <option value="num_pieza_secundario">Piezas secundario</option>
                                    <option value="volumen_secundario">Volumen secundario</option>
                                    <option value="num_pieza_terciario">Piezas terciario</option>
                                    <option value="volumen_terciario">Volumen terciario</option>
                                    <option value="num_pieza_total">Total piezas</option>
                                    <option value="volumen_total">Volumen total</option>
                                    <option value="empleado">Registró</option>
                                </select>
                                <input type="text" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                <input type="submit" value="Buscar" class="btn btn-success">
                            </form>
                        </div><!--Form busc-->
                        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                            <thead>
                                <th>N°</th>
                                <th>Fecha</th>
                                <th>Piezas primario</th>
                                <th>Vol. primario</th>
                                <th>Piezas secundario</th>
                                <th>Vol. secundario</th>
                                <th>Piezas terciario</th>
                                <th>Vol. terciario</th>
                                <th>Total piezas</th>
                                <th>Volumen total</th>
                                <th>Registró</th>
                                <th></th>
                            </thead>
                            <tbody>
                                <%
                                    int i = 0;
                                    for (SalidaMaderaRollo salida : salidas) {
                                        out.print("<tr>"
                                        + "<td>" + (i + 1) + "</td>"
                                        + "<td>" + salida.getFecha() + "</td>"
                                        + "<td>" + salida.getNum_pieza_primario() + "</td>"
                                        + "<td>" + salida.getVolumen_primario() + "</td>"
                                        + "<td>" + salida.getNum_pieza_secundario() + "</td>"
                                        + "<td>" + salida.getVolumen_secundario() + "</td>"
                                        + "<td>" + salida.getNum_pieza_terciario() + "</td>"
                                        + "<td>" + salida.getVolumen_terciario() + "</td>"
                                        + "<td>" + salida.getNum_pieza_total() + "</td>"
                                        + "<td>" + salida.getVolumen_total() + "</td>"
                                        + "<td>" + salida.getEmpleado() + "</td>"
                                        + "<td><a href=\"/aserradero/SalidaMaderaRolloController?action=modificar&id_salida=" + salida.getId_salida() + "\">Modificar</a></td>"
                                        + "</tr>");
                                        i++;
                                    }
                                %>
                            </tbody>
                        </table>
                        <div class="agregar_element">
                            <input type="button" class="btn btn-primary" value="Registrar salida" onClick=" window.location.href = '/aserradero/SalidaMaderaRolloController?action=nuevo'">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
