<%-- 
    Document   : detalleVentaExtra
    Created on : 30/10/2016, 07:26:16 PM
    Author     : rcortes
--%>

<%@page import="entidades.venta.VentaExtra"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        HttpSession sesion_ajax = request.getSession(true);
        ArrayList<VentaExtra> VentaMay = (ArrayList<VentaExtra>) sesion_ajax.getAttribute("detalle_venta_extra");
        if((sesion_ajax.getAttribute("detalle_venta_extra"))!=null){
            if(VentaMay.size()>0){//Si la cantida de productos agregados es mayor a cero
                out.print("<table class='table'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Tipo</th>");                                
                out.print("<th>Monto</th>");
                out.print("<th>Observación</th>");
                out.print("<th></th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");//Inicia el cuerpo de la tabla    
                for(VentaExtra a:VentaMay){
                    out.print("<tr>");
                    out.print("<td>"+a.getTipo()+"</td>");
                    out.print("<td>"+a.getMonto()+"</td>");                
                    out.print("<td>"+a.getObservacion()+"</td>");
                    out.print("<td><input type='button' value='Eliminar' class='btn btn-danger eliminar-ve' id='"+a.getTipo()+"'/></td>");
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
<script src="/aserradero/js/libs/ajax_venta_extra.js"></script>