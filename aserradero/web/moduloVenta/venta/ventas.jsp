<%-- 
    Document   : ventas
    Created on : 21-sep-2016, 21:11:30
    Author     : lmarcoss
--%>

<%@page import="entidades.venta.Venta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Venta> ventas = (List<Venta>) request.getAttribute("ventas");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"

        <!-- ************************** opción de búsqueda-->
            <form method="POST" action="/aserradero/VentaController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="fecha">Fecha</option>
                                <option value="id_venta">Id venta</option>
                                <option value="id_cliente">Id cliente</option>
                                <option value="id_empleado">Id empleado</option>
                                <option value="estatus">Status</option>
                                <option value="estatus">Tipo venta</option>
                                <option value="tipo_pago">Pago</option>
                            </select>
                        </td>
                        <td><input type="search" name="dato" placeholder="Escriba su búsqueda"></td>
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
                    <th>Id venta</th>
                    <th>Id cliente</th>
                    <th>Id empleado</th>
                    <th>Status</th>
                    <th>Tipo venta</th>
                    <th>Pago</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i=0;
                    for (Venta venta : ventas) {
                        out.print("<tr>"
                            +"<td>"+(i+1)+"</td>"
                            +"<td>"+venta.getFecha()+"</td>"
                            +"<td>"+venta.getId_venta()+"</td>"
                            +"<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona="+venta.getId_cliente()+"\">"+venta.getId_cliente()+"</a></td>"
                            +"<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona="+venta.getId_empleado()+"\">"+venta.getId_empleado()+"</a></td>"
                            +"<td>"+venta.getTipo_venta()+"</td>"
                            + "<td><a href=\"/aserradero/VentaController?action=modificar&id_venta=" + venta.getId_venta() + "&tipo_venta=Mayoreo\">Modificar pago</a></td>"
                            + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaController?action=eliminar&id_venta="+venta.getId_venta()+"';};\">Eliminar</a></td>"
                        + "</tr>" );
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Agregar venta" onClick=" window.location.href='/aserradero/VentaController?action=nuevo'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
