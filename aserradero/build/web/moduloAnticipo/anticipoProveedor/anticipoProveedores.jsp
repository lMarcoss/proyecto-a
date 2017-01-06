<%-- 
    Document   : anticipoProveedores
    Created on : 27/09/2016, 01:14:24 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoProveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<AnticipoProveedor> anticipoProveedores = (List<AnticipoProveedor>) request.getAttribute("listaAnticipos");
    String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Anticipo proveedores</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"

               <!-- ************************** opción de búsqueda-->
               <form method="POST" action="/aserradero/AnticipoProveedorController?action=buscar">
            <table>
                <tr>
                    <td>
                        <select name="nombre_campo" >
                            <option value="fecha">Fecha</option>
                            <option value="proveedor">Proveedor</option>
                            <option value="monto_anticipo">Monto anticipo</option>
                            <option value="empleado">Registró</option>
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
                <th>Proveedor</th>
                <th>Monto anticipo </th>
                <th>Registró</th>
                <th> </th>
            </tr>
            <%
                int i = 0;
                for (AnticipoProveedor anticipoProveedor : anticipoProveedores) {
                    out.print("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + anticipoProveedor.getFecha() + "</td>"
                            + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoProveedor.getId_proveedor() + "\">" + anticipoProveedor.getProveedor() + "</a></td>"
                            + "<td>" + anticipoProveedor.getMonto_anticipo() + "</td>"
                            + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoProveedor.getId_empleado() + "\">" + anticipoProveedor.getEmpleado() + "</a></td>"
                            + "<td><a href=\"/aserradero/AnticipoProveedorController?action=modificar&id_anticipo_p=" + anticipoProveedor.getId_anticipo_p() + "\">Modificar</a></td>"
                            //                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/AnticipoProveedorController?action=eliminar&id_anticipo_p="+anticipoProveedor.getId_anticipo_p()+"';};\">Eliminar</a></td>"
                            + "</tr>");
                    i++;
                }
            %>
        </table>
        <div>
            <input type="button" value="Agregar anticipo proveedor" onClick=" window.location.href = '/aserradero/AnticipoProveedorController?action=nuevo'">
        </div>
    </div><!-- Resultado Consulta-->
</body>
</html>
