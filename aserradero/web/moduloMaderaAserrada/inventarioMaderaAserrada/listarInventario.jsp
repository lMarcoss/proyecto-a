<%--
    Document   : inventarioMaderaAserrada
    Created on : 28/09/2016, 04:11:42 PM
    Author     : Marcos
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaAserrada.InventarioMaderaAserrada"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaAserrada> inventarioMaderaAserrada = (List<InventarioMaderaAserrada>) request.getAttribute("listaInventario");
    InventarioMaderaAserrada inventarioTotal = (InventarioMaderaAserrada) request.getAttribute("inventarioTotal");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Inventario</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <%if (!inventarioMaderaAserrada.isEmpty()) {%>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Invetario de madera aserrado</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Registros de inventario</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc">
                                <form method="POST" action="/aserradero/InventarioMaderaAserradaController?action=buscar">
                                    <select name="nombre_campo" class="input-busc">
                                        <option value="id_madera">Id madera</option>
                                        <option value="num_piezas">Piezas</option>
                                        <option value="volumen">Vol unitario</option>
                                        <option value="volumen_total">Volumen total</option>
                                        <option value="costo_por_volumen">Costo volumen</option>
                                        <option value="costo_total">Costo total</option>
                                    </select>
                                    <input type="text" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                    <input type="submit" value="Buscar" class="btn btn-success">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Id madera</th>
                                        <th>Piezas</th>
                                        <th>Vol. unitario</th>
                                        <th>Vol. total</th>
                                        <th>Costo volumen</th>
                                        <th>Costo total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (InventarioMaderaAserrada inventario : inventarioMaderaAserrada) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + inventario.getId_madera() + "</td>"
                                                    + "<td>" + inventario.getNum_piezas() + "</td>"
                                                    + "<td>" + inventario.getVolumen_unitario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_total() + "</td>"
                                                    + "<td>" + inventario.getCosto_por_volumen() + "</td>"
                                                    + "<td><b>" + inventario.getCosto_total() + "</b></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                        if (inventarioTotal != null) {
                                            out.print("<tr>"
                                                    + "<td>"+(i+1)+"</td>"
                                                    + "<td><b>Total</b></td>"
                                                    + "<td><b>" + inventarioTotal.getNum_piezas()+ "</b></td>"
                                                    + "<td></td>"
                                                    + "<td><b>" + inventarioTotal.getVolumen_total()+ "</b></td>"
                                                    + "<td></td>"
                                                    + "<td><b>" + inventarioTotal.getCosto_total() + "</b></td>"
                                                    + "</tr>");
                                        }

                                    %>
                                </tbody>
                            </table>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
        <%  } else {%>
        <h3 style="color: red;">No hay inventario</h3>
        <% }%>
    </body>
</html>
