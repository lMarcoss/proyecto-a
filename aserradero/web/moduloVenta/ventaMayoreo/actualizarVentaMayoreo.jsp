<%-- 
    Document   : actualizarVentaMayoreo
    Created on : 27-sep-2016, 12:36:56
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.InventarioMaderaAserrada"%>
<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaMayoreo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaAserrada> listaInventario = (List<InventarioMaderaAserrada>) request.getAttribute("listaInventario");
    VentaMayoreo ventaMayoreo = (VentaMayoreo) request.getAttribute("ventaMayoreo");
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


        <div>
            <form action="/aserradero/VentaMayoreoController?action=actualizar" method="post" id="formregistro">
                <h3>Actualizar venta Mayoreo</h3>
                <fieldset id="user-details">
                    <table>

                        <input type="hidden" name="id_venta" readonly="" value="<%=ventaMayoreo.getId_venta()%>">
                        <tr>
                            <td style="padding-left: 10px;"><label>Madera</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_madera" id="id_madera">
                                    <option value="<%=ventaMayoreo.getId_madera()%>"><%=ventaMayoreo.getId_madera()%></option>
                                </select>
                        </tr>
                        <%
                            for (InventarioMaderaAserrada inventario : listaInventario) {
                                if (inventario.getId_madera().equals(ventaMayoreo.getId_madera())) {
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
                                    <option value="<%=(inventario.getNum_piezas() + ventaMayoreo.getNum_piezas())%>"><%=(inventario.getNum_piezas() + ventaMayoreo.getNum_piezas())%></option>
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
                            <td style="padding-left: 10px;"><input type="number" name="num_piezas" id="num_piezas" value="<%=ventaMayoreo.getNum_piezas()%>" required="" title="Escribe la cantidad de piezas"  min="0" max="<%=(piezas + ventaMayoreo.getNum_piezas())%>" onblur="calcularVolumenTotal()"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.001" min="0.001" max="99999.999" name="volumen" id="volumen" value="<%=ventaMayoreo.getVolumen()%>" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.001" min="0.001" max="99999999.99" name="monto" id="monto" value="<%=ventaMayoreo.getMonto()%>" required="" readonly=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Tipo madera:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="tipo_madera" id="tipo_madera">
                                    <option value="<%=ventaMayoreo.getTipo_madera()%>"><%=ventaMayoreo.getTipo_madera()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/VentaMayoreoController?action=detalle&id_venta=<%=ventaMayoreo.getId_venta()%>"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
