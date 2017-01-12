<%-- 
    Document   : actualizarAnticipoProveedor
    Created on : 27/09/2016, 03:01:06 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoProveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    AnticipoProveedor anticipoProveedor = (AnticipoProveedor) request.getAttribute("anticipoProveedor");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/AnticipoProveedorController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar anticipo proveedor</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_anticipo_p" value="<%=anticipoProveedor.getId_anticipo_p()%>" readonly=""/>

                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=anticipoProveedor.getFecha()%>" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>proveedor:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_proveedor" id="id_proveedor" required="">
                                    <option selected="" value="<%=anticipoProveedor.getId_proveedor()%>"><%=anticipoProveedor.getProveedor()%></option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="monto_anticipo" type="number" value="<%=anticipoProveedor.getMonto_anticipo()%>" step="any" required=""/>                             
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/AnticipoProveedorController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
