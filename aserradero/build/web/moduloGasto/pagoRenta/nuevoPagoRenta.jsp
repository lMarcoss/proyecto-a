<%-- 
    Document   : nuevoPagoRenta
    Created on : 25/09/2016, 09:32:46 PM
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
        <form action="/aserradero/PagoRentaController?action=insertar" method="POST">
            <h3>Agregar nuevo pago de renta</h3>
            <fieldset id="user-details">
                <table>                        
                    <tr>
                        <td style="padding-left: 10px;"><label for="fecha">Fecha</label></td>
                        <td style="padding-left: 10px;"><input type="date" name="fecha" required="" placeholder="AAAA/MM/DD"/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label for="nombre_persona">Nombre</label></td>
                        <td style="padding-left: 10px;"><input type="text" name="nombre_persona" maxlength="49" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label for="monto">Monto</label></td>
                        <td style="padding-left: 10px;"><input type="number" step=".01" name="monto" min="0.01" max="999999.99" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label for="observacion">Observacion</label></td>
                        <td style="padding-left: 10px;"><input type="text" name="observacion" maxlength="249"/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><a href="/aserradero/PagoRentaController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                        <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div><!--Fin Formulario de registro-->  
</body>
</html>
