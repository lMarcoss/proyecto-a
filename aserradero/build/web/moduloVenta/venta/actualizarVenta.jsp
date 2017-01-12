<%-- 
    Document   : actualizarVenta
    Created on : 21-sep-2016, 21:48:48
    Author     : lmarcoss
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.venta.Venta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Venta venta = (Venta) request.getAttribute("venta");
    BigDecimal mayor = BigDecimal.valueOf(Double.valueOf("0"));
    Double monto = Double.valueOf(venta.getMonto().toString());
    Double pago = Double.valueOf(venta.getPago().toString());
    if (monto > pago) {
        mayor = venta.getMonto();
    } else if (pago > monto) {
        mayor = venta.getPago();
    } else if (monto >= pago) { // entra si las dos anteriores no cumplen
        mayor = venta.getMonto();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <!--<script src="/aserradero/js/venta/actualizarPagoVenta.js"></script>-->
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div>
            <form action="/aserradero/VentaController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar datos venta</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=venta.getFecha()%>" min="1920-01-01" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Id venta:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="id_venta" value="<%=venta.getId_venta()%>" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Cliente</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_cliente" id="id_cliente">
                                    <option selected="" value="<%=venta.getId_cliente()%>"><%=venta.getCliente()%></option>
                                </select>
                        </tr>
                        <input type="hidden" name="id_empleado" id="id_empleado" value="<%=venta.getId_empleado()%>" readonly="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto total venta: </label></td>
                            <td style="padding-left: 10px;">
                                <input type="number" step="0.01" name="monto" id="monto" value="<%=venta.getMonto()%>" readonly="">
                            </td>
                        </tr>
                        <input type="hidden" step="0.01" name="pago_anterior" id="pago_anterior" value="<%=venta.getPago()%>" readonly="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Pago en efectivo:</label></td>
                            <td style="padding-left: 10px;">
                                <input type="number" name="pago" id="pago" step="0.01" value="<%=venta.getPago()%>" min="0" max="<%=mayor%>" required="" onclick="actualizarPagoVenta()">
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Tipo venta:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="tipo_venta" id="tipo_venta">
                                    <option selected="" value="<%=venta.getTipo_venta()%>"><%=venta.getTipo_venta()%></option>
                                </select>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/Venta<%=venta.getTipo_venta()%>Controller?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
