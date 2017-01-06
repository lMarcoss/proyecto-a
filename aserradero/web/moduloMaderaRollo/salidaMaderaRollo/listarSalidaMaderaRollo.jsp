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

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"

               <!-- ************************** opción de búsqueda-->
               <form method="POST" action="/aserradero/SalidaMaderaRolloController?action=buscar">
            <table>
                <tr>
                    <td>
                        <select name="nombre_campo" >
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
                <th></th>
            </tr>
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
        </table>
        <div>
            <input type="button" value="Registrar salida" onClick=" window.location.href = '/aserradero/SalidaMaderaRolloController?action=nuevo'">
        </div>
    </div><!-- Resultado Consulta-->
</body>
</html>
