<%-- 
    Document   : mostrarDetalleVP
    Created on : 4/12/2016, 04:31:29 PM
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaPaquete"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <VentaPaquete> detalles = (List<VentaPaquete>) request.getAttribute("detalles");
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
            <!--<h3>Detalles de la venta </h3>-->
            <table class="table-condensed">
                <tr>
                    <th>N°</th>
                    <th>Paquete</th>
                    <th>Madera</th>
                    <th>Núm piezas</th>
                    <th>Volúmen</th>
                    <th>Costo</th>
                    <th>Tipo madera</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i=0;
                    for (VentaPaquete detalle : detalles) {
                        out.print("<tr>"
                            +"<td>"+(i+1)+"</td>"
                            +"<td>"+detalle.getNumero_paquete()+"</td>"
                            +"<td>"+detalle.getId_madera()+"</td>"
                            +"<td>"+detalle.getNum_piezas()+"</td>"
                            +"<td>"+detalle.getVolumen()+"</td>"
                            +"<td>"+detalle.getMonto()+"</td>"
                            +"<td>"+detalle.getTipo_madera()+"</td>"
                            +"<td><a href=\"/aserradero/VentaPaqueteController?action=modificar&id_venta="+detalle.getId_venta()+"&numero_paquete="+detalle.getNumero_paquete()+"&id_madera="+detalle.getId_madera()+"&tipo_madera="+detalle.getTipo_madera()+"\">Modificar</a></td>"
                            +"<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaPaqueteController?action=eliminar&id_venta="+detalle.getId_venta()+"&numero_paquete="+detalle.getNumero_paquete()+"&id_madera="+detalle.getId_madera()+"&tipo_madera="+detalle.getTipo_madera()+"';};\">Eliminar</a></td>"
                        + "</tr>" );
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Regresar" onClick=" window.location.href='/aserradero/VentaPaqueteController?action=listar' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
