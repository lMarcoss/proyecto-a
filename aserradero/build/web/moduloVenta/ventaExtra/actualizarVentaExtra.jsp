<%-- 
    Document   : actualizarVentaExtra
    Created on : 21-sep-2016, 23:43:54
    Author     : lmarcoss
--%>

<%@page import="entidades.venta.VentaExtra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    VentaExtra ventaExtra = (VentaExtra) request.getAttribute("ventaExtra");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <%@ include file="/TEMPLATE/headNuevo.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <div>
            <form action="/aserradero/VentaExtraController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar venta extra</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label>Id venta:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="id_venta" value="<%=ventaExtra.getId_venta()%>" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Tipo:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="tipo" value="<%=ventaExtra.getTipo()%>" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto" value="<%=ventaExtra.getMonto()%>" required="" min="0.01" max="99999999.99"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Observación:</label></td>
                            <td style="padding-left: 10px;"><textarea name="observacion" required=""><%=ventaExtra.getObservacion()%></textarea></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/VentaExtraController?action=detalle&id_venta=<%=ventaExtra.getId_venta()%>"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
