<%-- 
    Document   : actualizarVentaPaquete
    Created on : 28-sep-2016, 9:56:38
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.InventarioMaderaAserrada"%>
<%@page import="entidades.venta.VentaPaquete"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaAserrada> listaInventario = (List<InventarioMaderaAserrada>) request.getAttribute("listaInventario");
    VentaPaquete ventaPaquete = (VentaPaquete) request.getAttribute("ventaPaquete");
    int piezas = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
        <script src="/aserradero/js/SelectorCostoVenta.js"></script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!--formulario de registro-->
        <div>
            <form action="/aserradero/VentaPaqueteController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar venta paquete</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label>Id venta:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="id_venta" value="<%=ventaPaquete.getId_venta()%>" readonly=""/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Número de Paquete</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="numero_paquete" value="<%=ventaPaquete.getNumero_paquete()%>" min="1" max="99999999999" title="Sólo número" readonly=""/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Madera</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_madera">
                                    <option value="<%=ventaPaquete.getId_madera()%>"><%=ventaPaquete.getId_madera()%></option>
                                </select>
                            <td></td>
                        </tr>
                        <%
                            for (InventarioMaderaAserrada inventario : listaInventario) {
                                if (inventario.getId_madera().equals(ventaPaquete.getId_madera())) {
                                    piezas = inventario.getNum_piezas();
                        %>
                        <tr>
                            <td><label>Volumen unitaria</label></td>
                            <td>
                                <select name="volumen_unitaria" id="volumen_unitaria">
                                    <option value="<%=inventario.getVolumen_unitario()%>"><%=inventario.getVolumen_unitario()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Costo por volumen</label></td>
                            <td>
                                <select name="costo_volumen" id="costo_volumen">
                                    <option value="<%=inventario.getCosto_por_volumen()%>"><%=inventario.getCosto_por_volumen()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Piezas en existencia</label></td>
                            <td>
                                <select name="pieza_existencia" id="pieza_existencia">
                                    <option value="<%=(inventario.getNum_piezas() + ventaPaquete.getNum_piezas())%>"><%=(inventario.getNum_piezas() + ventaPaquete.getNum_piezas())%></option>
                                </select>
                            </td>
                        </tr>

                        <!--//                                    out.print("<tr><td style='padding-left: 10px;'><label>Madera:</label></td><td style='padding-left: 10px;'><input name='id_madera' id='id_madera' value='" + inventario.getId_madera() + "' readonly=''/></td></tr>");-->
                        <!--out.print("<tr><td>volumen unitaria<select name='volumen_unitaria' id='volumen_unitaria' readonly=''><option value='" + inventario.getVolumen_unitario() + "'>" + inventario.getVolumen_unitario() + "</option></select></td>");-->
                        <!--out.print("<td>Costo volumen<select name='costo_volumen' id='costo_volumen' readonly=''><option value='" + inventario.getCosto_por_volumen() + "'>" + inventario.getCosto_por_volumen() + "</option></select></td></tr>");-->
                        <%}
                            }
                        %>
                        <tr>
                            <td style="padding-left: 10px;"><label>Número de piezas:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="num_piezas" id="num_piezas" value="<%=ventaPaquete.getNum_piezas()%>" min="1" max="<%=(piezas + ventaPaquete.getNum_piezas())%>" required="" title="Escribe la cantidad de piezas" onblur="calcularVolumenTotal()"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="volumen" id="volumen" value="<%=ventaPaquete.getVolumen()%>" step="0.001" min="0.001" max="99999.999"  required="" readonly=""/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="monto" id="monto" value="<%=ventaPaquete.getMonto()%>" step="0.01" min="0.01" max="99999999.99"  required="" readonly=""/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Tipo madera</label></td>
                            <td style="padding-left: 10px;">
                                <select name="tipo_madera" id="tipo_madera" required="">
                                    <option selected="" value="<%=ventaPaquete.getTipo_madera()%>"><%=ventaPaquete.getTipo_madera()%></option>
                                </select>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/VentaPaqueteController?action=detalle&id_venta=<%=ventaPaquete.getId_venta()%>"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
