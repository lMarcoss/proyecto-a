<%--
    Document   : detalleVentaPaquete
    Created on : 7/11/2016, 05:24:31 PM
    Author     : rcortes
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entidades.venta.VentaPaquete"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        HttpSession sesion_ajax = request.getSession(true);
        ArrayList<VentaPaquete> VentaPaq = (ArrayList<VentaPaquete>) sesion_ajax.getAttribute("detalle_venta_paquete");
        if((sesion_ajax.getAttribute("detalle_venta_paquete"))!=null){
            if(VentaPaq.size()>0){//Si la cantida de productos agregados es mayor a cero
                out.print("<table class='table'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Número paquete</th>");
                out.print("<th>Madera</th>");
                out.print("<th>Número de piezas</th>");
                out.print("<th>Volumen</th>");
                out.print("<th>Monto</th>");
                out.print("<th>Tipo madera</th>");
                out.print("<th></th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");//Inicia el cuerpo de la tabla
                for(VentaPaquete a:VentaPaq){
                    out.print("<tr>");
                    out.print("<td>"+a.getNumero_paquete()+"</td>");
                    out.print("<td>"+a.getId_madera()+"</td>");
                    out.print("<td>"+a.getNum_piezas()+"</td>");
                    out.print("<td>"+a.getVolumen()+"</td>");
                    out.print("<td>"+a.getMonto()+"</td>");
                    out.print("<td>"+a.getTipo_madera()+"</td>");
                    out.print("<td><input type='button' value='Eliminar' name='"+a.getNumero_paquete()+"' id='"+a.getId_madera()+"' form='"+a.getTipo_madera()+"' class='btn btn-danger eliminar_vp'/></td>");
                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
            }else{
                out.print("<h3 class='panel-title'>No hay registros agregados</h3>");
            }
        }else{
            out.print("<h3 class='panel-title'>No hay registros agregados</h3>");
        }
    %>
<script src="/aserradero/js/libs/ajax_venta_paquete.js"></script>
