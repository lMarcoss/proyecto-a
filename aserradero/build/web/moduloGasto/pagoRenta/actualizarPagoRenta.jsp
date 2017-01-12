<%-- 
    Document   : actualizarPagoRenta
    Created on : 25/09/2016, 10:15:35 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoRenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%PagoRenta pagorenta = (PagoRenta) request.getAttribute("pagorenta");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoRentaController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_pago_renta" value="<%= pagorenta.getId_pago_renta()%>"  readonly="" required=""/>
                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=pagorenta.getFecha()%>" required=""/></td> 
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="nombre_persona">Nombre</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="nombre_persona" value="<%=pagorenta.getNombre_persona()%>" maxlength="49" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="monto">Monto</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto" value="<%=pagorenta.getMonto()%>" min="0.0" max="999999.99" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="observacion">Observacion</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="observacion" maxlength="249" value="<%=pagorenta.getObservacion()%>"/></td>
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
