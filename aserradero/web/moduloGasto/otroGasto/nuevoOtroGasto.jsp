<%--
    Document   : nuevoOtroGasto
    Created on : 26/09/2016, 03:18:11 PM
    Author     : rcortes
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form action="/aserradero/OtroGastoController?action=insertar" method="post" id="formregistro">
                <h3>Agregar gasto</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" placeholder="AAAA/MM/DD"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Nombre del gasto:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="nombre_gasto" maxlength="249" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto" min="0.01" max="999999.99" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Observación:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="observacion" maxlength="249"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/OtroGastoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
