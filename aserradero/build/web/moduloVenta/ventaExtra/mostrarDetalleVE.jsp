<%-- 
    Document   : mostrarDetalleVE
    Created on : 16-nov-2016, 15:27:49
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaExtra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <VentaExtra> detalles = (List<VentaExtra>) request.getAttribute("detalles");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas </title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
        
        <!-- ************************* Resultado Consulta-->
        <div>
            <h3>Detalles de la venta</h3>
            <table class="table-condensed">
                <tr>
                    <th>N°</th>
                    <th>Venta</th>
                    <th>Monto</th>
                    <th>Observacion</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i=0;
                    for (VentaExtra detalle : detalles) {
                        out.print("<tr>"
                            +"<td>"+(i+1)+"</td>"
                            +"<td>"+detalle.getTipo()+"</td>"
                            +"<td>"+detalle.getMonto()+"</td>"
                            +"<td>"+detalle.getObservacion()+"</td>"
                            +"<td><a href=\"/aserradero/VentaExtraController?action=modificar&id_venta="+detalle.getId_venta()+"&tipo="+detalle.getTipo()+"\">Modificar</a></td>"
                            + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaExtraController?action=eliminar&id_venta="+detalle.getId_venta()+"&tipo="+detalle.getTipo()+"';};\">Eliminar</a></td>"
                        + "</tr>" );
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Regresar" onClick=" window.location.href='/aserradero/VentaExtraController?action=listar' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
