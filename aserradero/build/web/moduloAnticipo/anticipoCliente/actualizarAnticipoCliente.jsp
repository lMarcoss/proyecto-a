<%-- 
    Document   : actualizarAnticipoCliente
    Created on : 26/09/2016, 07:46:55 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%AnticipoCliente anticipoCliente = (AnticipoCliente) request.getAttribute("anticipoCliente");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo anticipo cliente</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/AnticipoClienteController?action=actualizar" method="post" id="formregistro">
                <h3>Agregar anticipo cliente</h3>
                <fieldset id="user-details">

                    <table>
                        <input type="hidden" name="id_anticipo_c" value="<%=anticipoCliente.getId_anticipo_c()%>" readonly=""/>
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=anticipoCliente.getFecha()%>" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>cliente:</label></td>
                            <td>
                                <select name="id_cliente" id="id_cliente">
                                    <option selected="" value="<%=anticipoCliente.getId_cliente()%>"><%=anticipoCliente.getCliente()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;">
                                <input type="number" name="monto_anticipo" value="<%=anticipoCliente.getMonto_anticipo()%>" step="0.01" min="0.00" max="999999.99" required=""/>
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
