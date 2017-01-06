<%--
    Document   : costomaderacompras
    Created on : 26/09/2016, 11:14:22 PM
    Author     : rcortes
--%>

<%@page import="entidades.maderaRollo.ClasificacionMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List <ClasificacionMaderaRollo> listaClasificacion = (List<ClasificacionMaderaRollo>) request.getAttribute("listaClasificacion");
    String mensaje = (String)request.getAttribute("mensaje"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input hidden="" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <!-- ************************** opción de búsqueda-->
        <h2>Costo madera en rollo por proveedor</h2>
        <div>
            <form method="POST" action="/aserradero/ClasificacionMaderaRolloController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="proveedor">Proveedor</option>
                                <option value="clasificacion">Clasificación</option>
                                <option value="costo">costo</option>
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
                        <th>Clasificación</th>
                        <th>Costo_volumen</th>
                    </tr>
                    <%
                        int i=0;
                        for (ClasificacionMaderaRollo clasificacion : listaClasificacion) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                +"<td>"+clasificacion.getProveedor()+"</td>"
                                +"<td>"+clasificacion.getClasificacion()+"</td>"
                                +"<td>"+clasificacion.getCosto()+"</td>"
                                +"<td><a href=\"/aserradero/ClasificacionMaderaRolloController?action=modificar&id_proveedor="+clasificacion.getId_proveedor()+"&clasificacion="+clasificacion.getClasificacion()+"\">Cambiar costo</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/ClasificacionMaderaRolloController?action=eliminar&id_proveedor="+clasificacion.getId_proveedor()+"&clasificacion="+clasificacion.getClasificacion()+"';};\">Eliminar</a></td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
            </table>
            <div>
                <input type="button" value="Agregar" onClick=" window.location.href='/aserradero/ClasificacionMaderaRolloController?action=nuevo' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
