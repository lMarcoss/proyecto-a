<%--
    Document   : actualizarPagoLuz
    Created on : 26/09/2016, 01:54:35 PM
    Author     : rcortes
--%>

<%@page import="entidades.gasto.PagoLuz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%PagoLuz pagoluz = (PagoLuz) request.getAttribute("pagoLuz");%>
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
            <form action="/aserradero/PagoLuzController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_pago_luz" value="<%= pagoluz.getId_pago_luz()%>"  readonly="" title="No puedes cambiar esto" >
                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" value="<%=pagoluz.getFecha()%>" readonly=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Empleado</label></td>
                            <td style="padding-left: 10px;">
                                <input type="hidden" name="id_empleado" value="<%=pagoluz.getId_empleado()%>" required="" readonly="">
                                <input type="text" name="id_empleado" value="<%=pagoluz.getEmpleado()%>" required="" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="monto">Monto</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto" value="<%=pagoluz.getMonto()%>" min="0.00" max="999999.99" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="observacion">Observacion</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="observacion" value="<%=pagoluz.getObservacion()%>" maxlength="249"></td>
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
