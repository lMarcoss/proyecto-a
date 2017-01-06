<%--
    Document   : actualizarOtroGasto
    Created on : 26/09/2016, 03:36:38 PM
    Author     : rcortes
--%>
<%@page import="entidades.gasto.OtroGasto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    OtroGasto otrogasto = (OtroGasto) request.getAttribute("otrogasto");
%>
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
            <form action="/aserradero/OtroGastoController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_gasto" value="<%= otrogasto.getId_gasto()%>"  readonly="" required="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=otrogasto.getFecha()%>" readonly=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="nombre_gasto">Nombre gasto</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="nombre_gasto" value="<%=otrogasto.getNombre_gasto()%>" maxlength="249" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="monto">Monto</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="monto" value="<%=otrogasto.getMonto()%>" step="0.01" min="0.00" max="999999.99" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="observacion">Observacion</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="observacion" value="<%=otrogasto.getObservacion()%>" maxlength="249"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/OtroGastoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <!--<td><input type="submit" value="Registrar" class="submit"/> </td>-->
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
