<%-- 
    Document   : actualizarPagoPrestamo
    Created on : 27-nov-2016, 22:27:24
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.prestamo.PagoPrestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PagoPrestamo pagoPrestamo = (PagoPrestamo) request.getAttribute("pagoPrestamo");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <script src="/aserradero/js/actualizarPagoPrestamo.js"></script>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoPrestamoController?action=actualizar" method="post" id="formregistro">
                <h3>Modificar pago préstamo</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_pago" id="id_pago" value="<%= pagoPrestamo.getId_pago()%>"  readonly="" required="">
                        <input type="hidden" name="id_prestamo" id="id_prestamo" value="<%= pagoPrestamo.getId_prestamo()%>"  readonly="" required="">                        
                        <input type="hidden" name="monto_prestamo_a" id="monto_prestamo_a" value="<%= pagoPrestamo.getMonto_prestamo()%>"  readonly="" required="">
                        
                        <!--Monto pagado y por pagar antes de actualizar-->
                        <input type="hidden" name="monto_pago_a" id="monto_pago_a" value="<%= pagoPrestamo.getMonto_pago()%>"  readonly="" required="">
                        <input type="hidden" name="monto_por_pagar_a" id="monto_por_pagar_a" value="<%= pagoPrestamo.getMonto_por_pagar()%>"  readonly="" required=""> 
                        
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=pagoPrestamo.getFecha()%>" readonly=""></td>
                        </tr>

                        <tr>
                            <td style="padding-left: 10px;"><label>Prestador</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_prestador" required="" onblur="actualizarPagoPrestamo()">
                                    <option value='<%=pagoPrestamo.getId_prestador()%>'><%=pagoPrestamo.getPrestador()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto pago:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_pago" id="monto_pago" value="<%=pagoPrestamo.getMonto_pago()%>" min="0.01" max="" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoPrestamoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
