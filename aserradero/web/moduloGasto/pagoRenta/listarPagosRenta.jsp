<%-- 
    Document   : pagosrenta
    Created on : 24/09/2016, 11:19:22 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoRenta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List <PagoRenta> pagosrenta = (List<PagoRenta>) request.getAttribute("listaPagosRenta");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Renta</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
        
        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/PagoRentaController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="fecha">Fecha</option>
                                <option value="nombre_persona">Nombre persona</option>
                                <option value="empleado">Registró</option>
                                <option value="monto">Monto</option>
                                <option value="observacion">Observación</option>
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
                        <th>Nombre persona</th>
                        <th>Monto</th>
                        <th>Observación</th>
                        <th>Registró</th>
                    </tr>
                    <%                        
                        int i=0;
                        for (PagoRenta pago_renta : pagosrenta) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                +"<td>"+pago_renta.getFecha()+"</td>"
                                +"<td>"+pago_renta.getNombre_persona()+"</td>"
                                +"<td>"+pago_renta.getMonto()+"</td>"
                                +"<td>"+pago_renta.getObservacion()+"</td>"
                                +"<td>"+pago_renta.getEmpleado()+"</td>"
                                +"<td><a href=\"/aserradero/PagoRentaController?action=modificar&id_pago_renta="+pago_renta.getId_pago_renta()+"\">Modificar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoRentaController?action=eliminar&id_pago_renta="+pago_renta.getId_pago_renta()+"';};\">Eliminar</a></td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
            </table>
            <div>
                <input type="button" value="Agregar pago" onClick=" window.location.href='/aserradero/PagoRentaController?action=nuevo'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
