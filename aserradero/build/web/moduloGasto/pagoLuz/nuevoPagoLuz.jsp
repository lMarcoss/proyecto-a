<%--
    Document   : nuevoPagoLuz
    Created on : 26/09/2016, 01:06:15 PM
    Author     : rcortes
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date fecha = Date.valueOf(LocalDate.now());
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
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoLuzController?action=insertar" method="post" id="formregistro">
                <h3>Agregar pago</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" value="<%=fecha%>"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" min="0.01" max="999999.99" name="monto" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="observacion">Observación:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="observacion" maxlength="249"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoLuzController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
