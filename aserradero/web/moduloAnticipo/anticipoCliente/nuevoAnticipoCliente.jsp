<%-- 
    Document   : nuevoAnticipoCliente
    Created on : 26/09/2016, 06:45:08 PM
    Author     : Marcos
--%>


<%@page import="entidades.registros.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <!--<script type="text/javascript" src="/aserradero/js/fechaActual.js"></script>-->
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/AnticipoClienteController?action=insertar" method="post" id="formregistro">
                <h3>Agregar anticipo cliente</h3>
                <fieldset id="user-details">
                    <table>

                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" id="fecha" value="" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>cliente:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_cliente" required="" title="Si no existe el cliente que busca, primero agreguelo en la lista de clientes">
                                    <option></option>
                                    <%
                                        for (Cliente cliente : clientes) {
                                            out.print("<option value='" + cliente.getId_cliente() + "'>" + cliente.getCliente() + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="monto_anticipo" type="number" min='0.01' max='999999.99' step=".01" required=""/>                             
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/AnticipoClienteController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
