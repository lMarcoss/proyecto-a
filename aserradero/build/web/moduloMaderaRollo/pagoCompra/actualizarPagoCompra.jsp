<%-- 
    Document   : actualizarPagoCompra
    Created on : 19-nov-2016, 22:27:24
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaRollo.PagoCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PagoCompra pagoCompra = (PagoCompra) request.getAttribute("pagoCompra");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <script src="/aserradero/js/calcularMontoPorPagar.js"></script>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoCompraController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_pago" value="<%= pagoCompra.getId_pago()%>"  readonly="" required="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=pagoCompra.getFecha()%>" readonly=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Proveedor</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_proveedor" required="">
                                    <option selected="" value='<%=pagoCompra.getId_proveedor()%>'><%=pagoCompra.getProveedor()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto total compra</label></td>
                            <td style="padding-left: 10px;">
                                <select name="cuenta_pendiente" id="cuenta_pendiente" required="" disabled="">
                                    <option selected="" value="<%=pagoCompra.getMonto_pago().add(pagoCompra.getMonto_por_pagar())%>"><%=pagoCompra.getMonto_pago().add(pagoCompra.getMonto_por_pagar())%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto a pagar:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_pago" id="monto_pago" value="<%=pagoCompra.getMonto_pago()%>" min="0.00" max="<%=pagoCompra.getMonto_pago().add(pagoCompra.getMonto_por_pagar())%>" required="" onblur="calcularMontoPorPagar()"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto pendiente (por pagar):</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_por_pagar" value="<%=pagoCompra.getMonto_por_pagar()%>" id="monto_por_pagar" min="0.00" max="" required="" readonly=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Cuenta por cobrar:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_por_cobrar" value="<%=pagoCompra.getMonto_por_cobrar()%>" id="monto_por_pagar" min="0.00" max="" required="" readonly=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoCompraController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <!--<td><input type="submit" value="Registrar" class="submit"/> </td>-->
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
