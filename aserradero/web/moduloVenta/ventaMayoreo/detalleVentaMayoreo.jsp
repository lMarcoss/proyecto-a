<%-- 
    Document   : detalleNuevaVentaMayoreo
    Created on : 15/10/2016, 06:38:04 PM
    Author     : rcortes
--%>

<%@page import="entidades.venta.VentaMayoreo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
        HttpSession sesion_ajax = request.getSession(true);
        ArrayList<VentaMayoreo> VentaMay = (ArrayList<VentaMayoreo>) sesion_ajax.getAttribute("detalle_venta_mayoreo");
        if((sesion_ajax.getAttribute("detalle_venta_mayoreo"))!=null){
            if(VentaMay.size()>0){//Si la cantida de productos agregados es mayor a cero
                out.print("<table class='table'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Madera</th>");
                out.print("<th>Número de piezas</th>");
                out.print("<th>Volumen</th>");
                out.print("<th>Monto</th>");
                out.print("<th>Tipo madera</th>");
                out.print("<th></th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");//Inicia el cuerpo de la tabla    
                for(VentaMayoreo a:VentaMay){
                    out.print("<tr>");
                    out.print("<td>"+a.getId_madera()+"</td>");
                    out.print("<td>"+a.getNum_piezas()+"</td>");                
                    out.print("<td>"+a.getVolumen()+"</td>");
                    out.print("<td>"+a.getMonto()+"</td>");
                    out.print("<td>"+a.getTipo_madera()+"</td>");
                    out.print("<td><input type='button' value='Eliminar' class='btn btn-danger eliminar-vm' id='"+a.getId_madera()+"' name = '"+a.getTipo_madera()+"'/></td>");
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
<script src="/aserradero/js/libs/ajax_venta_mayoreo.js"></script>
