<%-- 
    Document   : cuentaPorPagarProveedores
    Created on : 27/12/2016, 05:51:09 PM
    Author     : lmarcoss
--%>

<%@page import="entidades.anticipo.CuentaPorPagar"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<CuentaPorPagar> cuentaPorPagares = (List<CuentaPorPagar>) request.getAttribute("cuentaPorPagares");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Cuentas por pagar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ************************** opción de búsqueda-->
        <form method="POST" action="/aserradero/CuentaPorPagarController?action=buscar_proveedor">
            <table>
                <tr>
                    <td>
                        <select name="nombre_campo" >
                            <option value="proveedor">Proveedor</option>
                            <option value="monto">Monto</option>
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
                <th>Proveedor</th>
                <th>Monto </th>
            </tr>
            <%
                int i = 0;
                for (CuentaPorPagar cuentaPorPagar : cuentaPorPagares) {
                    out.print("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + cuentaPorPagar.getId_persona() + "\">" + cuentaPorPagar.getPersona() + "</a></td>"
                            + "<td>" + cuentaPorPagar.getMonto() + "</td>"
                            + "</tr>");
                    i++;
                }
            %>
        </table>
        <div>
        </div>
    </div><!-- Resultado Consulta-->
</body>
</html>
