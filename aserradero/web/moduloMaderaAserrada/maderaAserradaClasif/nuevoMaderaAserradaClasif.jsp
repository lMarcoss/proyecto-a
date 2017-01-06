<%-- 
    Document   : nuevoMaderaAserradaClasif
    Created on : 23/12/2016, 11:45:26 PM
    Author     : lmarcoss
--%>

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
            <form action="/aserradero/MaderaAserradaClasifController?action=insertar" method="post" id="formregistro">
                <h3>Registrar nuevo tipo de madera producción</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label>Madera:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="id_madera" pattern="[A-Za-z].{3,}[A-Za-z0-9]" title="Al menos cinco letras al principio" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Grueso:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="grueso" id="grueso" step=".01" min="0.01" max="9999.99"  title="Sólo números" required="" onblur="calcularVolumen()"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Grueso (fracción o pulgada):</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="grueso_f" id="grueso_f" maxlength="10" required="" placeholder='3 1/2 o "9"' title="Tal y como se mostrará en el ticket"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Ancho:</label></td>
                            <td style="padding-left: 10px;"><input type="number"  name="ancho" id="ancho" step=".01" min="0.01" max="9999.99" title="Sólo números" required="" onblur="calcularVolumen()"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Ancho (fracción o pulgada):</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="ancho_f" id="ancho_f" maxlength="10" required="" placeholder='3 1/2, "9" o 9' title="Tal y como se mostrará en el ticket"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Largo:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="largo" id="largo" step=".01" min="0.01" max="9999.99" title="Sólo números" required="" onblur="calcularVolumen()"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Largo (fracción o pulgada):</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="largo_f" id="largo_f" maxlength="10" required="" placeholder='3 1/2, "9" o 9' title="Tal y como se mostrará en el ticket"></td>
                        </tr>
<!--                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="volumen" id="volumen" step=".001" min="0.001" max="99999.999" title="Sólo números" required="" readonly=""/></td>
                        </tr>-->
                        <tr>
                            <td style="padding-left: 10px;"><label>Costo por volumen</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="costo_por_volumen" id="costo_por_volumen" step=".01" min="0.01" max="999.99" title="Sólo números" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/MaderaAserradaClasifController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->  
    </body>
</html>
