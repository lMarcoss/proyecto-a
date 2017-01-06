<%-- 
    Document   : anticipoClientes
    Created on : 26/09/2016, 04:42:00 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoCliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<AnticipoCliente> anticipoClientes = (List<AnticipoCliente>) request.getAttribute("listaAnticipos");
    String mensaje = (String) request.getAttribute("mensaje");
%>



<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Anticipo Clientes</title>
    </head>
    <body>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
               <!--menu-->
               <%@ include file="/TEMPLATE/menu.jsp" %>

               <!-- ************************** opción de búsqueda-->
               <div>
            <form method="POST" action="/aserradero/AnticipoClienteController?action=buscar">
                <table class="table-condensed">
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                                <option value="fecha">Fecha</option>
                                <option value="cliente">Cliente</option>
                                <option value="monto_anticipo">Monto anticipo</option>
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
                    <th>Cliente</th>
                    <th>Monto anticipo </th>
                    <th>Registró</th>
                    <th> </th>
                </tr>
                <%

                    int i = 0;
                    for (AnticipoCliente anticipoCliente : anticipoClientes) {
                        out.print("<tr>"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + anticipoCliente.getFecha() + "</td>"
                                + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoCliente.getId_cliente() + "\">" + anticipoCliente.getCliente() + "</a></td>"
                                + "<td>" + anticipoCliente.getMonto_anticipo() + "</td>"
                                + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoCliente.getId_empleado() + "\">" + anticipoCliente.getEmpleado() + "</a></td>"
                                + "<td><a href=\"/aserradero/AnticipoClienteController?action=modificar&id_anticipo_c=" + anticipoCliente.getId_anticipo_c() + "\">Modificar</a></td>"
                                //                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/AnticipoClienteController?action=eliminar&id_anticipo_c="+anticipoCliente.getId_anticipo_c()+"';};\">Eliminar</a></td>"
                                + "</tr>");
                        i++;
                    }
                %>
            </table>
            <div>
                <input type="button" value="Agregar anticipo cliente" onClick=" window.location.href = '/aserradero/AnticipoClienteController?action=nuevo'">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
