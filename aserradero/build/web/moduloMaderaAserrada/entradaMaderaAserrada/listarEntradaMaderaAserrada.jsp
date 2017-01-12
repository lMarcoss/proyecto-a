<%-- 
    Document   : produccionMaderas
    Created on : 28-sep-2016, 9:48:27
    Author     : lmarcoss
--%>
<%@page import="entidades.maderaAserrada.EntradaMaderaAserrada"%>
<%@page import="java.util.List"%>
<%
    List <EntradaMaderaAserrada> listaMEAserrada = (List<EntradaMaderaAserrada>) request.getAttribute("listaEntradaMaderaAserrada");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Producción madera</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
        
        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/EntradaMaderaAserradaController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                            <option value="fecha">Fecha</option>
                            <option value="id_madera">Id madera</option>
                            <option value="num_piezas">Número de piezas</option>
                            <option value="empleado">Registró</option>
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
                        <th>Id_madera</th>
                        <th>Núm. piezas</th>
                        <th>Registró</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <%
                        int i=0;
                        for (EntradaMaderaAserrada maderaAserrada : listaMEAserrada) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                +"<td>"+maderaAserrada.getFecha()+"</td>"
                                +"<td>"+maderaAserrada.getId_madera()+"</td>"
                                +"<td>"+maderaAserrada.getNum_piezas()+"</td>"
                                +"<td>"+maderaAserrada.getEmpleado()+"</td>"
                                +"<td><a href=\"/aserradero/EntradaMaderaAserradaController?action=modificar&id_entrada="+maderaAserrada.getId_entrada()+"\">Modificar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/EntradaMaderaAserradaController?action=eliminar&id_entrada="+maderaAserrada.getId_entrada()+"';};\">Eliminar</a></td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
            </table>
            <div>
                <input type="button" value="Agregar entrada" onClick=" window.location.href='/aserradero/EntradaMaderaAserradaController?action=nuevo' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
