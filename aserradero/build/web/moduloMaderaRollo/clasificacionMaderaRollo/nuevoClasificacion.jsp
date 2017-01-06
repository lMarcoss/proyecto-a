<%--
    Document   : nuevoCostoMaderaCompra
    Created on : 26/09/2016, 11:14:36 PM
    Author     : rcortes
--%>

<%@page import="entidades.registros.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <form action="/aserradero/ClasificacionMaderaRolloController?action=insertar" method="POST">
            <h3>Registrar clasificación y costo para un proveedor</h3>
            <fieldset id="user-details">
                <table>
                    <tr>
                        <td style="padding-left: 10px;"><label for="proveedor">Proveedor</label></td>
                        <td style="padding-left: 10px;">
                            <select name="id_proveedor">
                                <option></option>
                                <%
                                    for (Proveedor proveedor : proveedores) {
                                        out.print("<option value='"+proveedor.getId_proveedor()+"'>"+proveedor.getProveedor()+"</option>");
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label for="clasificacion">Clasificación</label></td>
                        <td style="padding-left: 10px;">
                            <select name="clasificacion">
                                <option value="Primario">Primario</option>
                                <option value="Secundario">Secundario</option>
                                <option value="Terciario">Terciario</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label for="costo">Costo</label></td>
                        <td style="padding-left: 10px;"><input type="number" step=".01" name="costo" min="0.01" max="999.99" required=""/></td>
                    </tr>                        
                    <tr>
                        <td style="padding-left: 10px;"><a href="/aserradero/ClasificacionMaderaRolloController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                        <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div><!--Fin Formulario de registro-->
</body>
</html>
