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


        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/InventarioMaderaAserradaController?action=buscar">
                <table class="table-condensed">
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="id_madera">Id madera</option>
                                <option value="num_piezas">Piezas</option>
                                <option value="volumen">Vol unitario</option>
                                <option value="volumen_total">Volumen total</option>
                                <option value="costo_por_volumen">Costo volumen</option>
                                <option value="costo_total">Costo total</option>
                            </select>
                        </td>
                        <td><input type="text" name="dato" placeholder="Escriba su búsqueda"></td>
                        <td colspan="2"><input type="submit" value="Buscar"></td>
                    </tr>
                </table>
            </form>
        </div> <!-- Fin opción de búsqueda-->

        <!-- ************************* Resultado Consulta-->
        <div>
            <table class="table-condensed">
                <tr>
                    <th>N°</th>
                    <th>Id madera</th>
                    <th>Piezas</th>
                    <th>Vol. unitario</th>
                    <th>Vol. total</th>
                    <th>Costo volumen</th>
                    <th>Costo total</th>
                    <th> </th>
                    <th> </th>
                </tr>
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
                                + "<td></td>"
                                + "<td><b>Total</b></td>"
                                + "<td><b>" + inventarioTotal.getNum_piezas()+ "</b></td>"
                                + "<td></td>"
                                + "<td><b>" + inventarioTotal.getVolumen_total()+ "</b></td>"
                                + "<td></td>"
                                + "<td><b>" + inventarioTotal.getCosto_total() + "</b></td>"
                                + "</tr>");
                    }

                %>
            </table>
            <div>
            </div>
        </div><!-- Resultado Consulta-->
        <%  } else {%>
        <h3 style="color: red;">No hay inventario</h3>
        <% }%>
    </body>
</html>
