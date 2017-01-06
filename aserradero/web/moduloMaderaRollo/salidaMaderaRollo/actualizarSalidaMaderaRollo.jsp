<%-- 
    Document   : actualizarSalidaMaderaRollo
    Created on : 28/10/2016, 05:09:40 PM
    Author     : li-906
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaRollo.InventarioMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page import="entidades.maderaRollo.SalidaMaderaRollo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    SalidaMaderaRollo salida = (SalidaMaderaRollo) request.getAttribute("salidaMaderaRollo");
    List <InventarioMaderaRollo> inventarioMR = (List<InventarioMaderaRollo>) request.getAttribute("inventarioMR");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
        <script src="/aserradero/js/salidaMaderaRollo.js"></script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <div>
            <!-- ******************* Formulario de registro-->
            <form action="/aserradero/SalidaMaderaRolloController?action=actualizar" method="POST">
                <h3>Modificar salida madera rollo</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_salida" id="id_salida" value="<%= salida.getId_salida()%>" readonly="" required="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=salida.getFecha()%>" required="" onblur="salidaMaderaRolloPermitido()"></td>
                        </tr>
                        <tr>
                            <td colspan="2"> <label>Clasificación primario</label></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Piezas en existencia</label></td>
                            <td style="padding-left: 10px;">
                                <select name="num_pieza_primarioE" id="num_pieza_primarioE">
                                    <%
                                        for (InventarioMaderaRollo inventario : inventarioMR) {
                                            out.print("<option value='" + (inventario.getNum_pieza_primario() + salida.getNum_pieza_primario()) + "'>" + (inventario.getNum_pieza_primario() + salida.getNum_pieza_primario()) + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen existente</label></td>
                            <td style="padding-left: 10px;">
                                <select name="volumen_primarioE" id="volumen_primarioE">
                                    <%
                                        for (InventarioMaderaRollo inventario : inventarioMR) {
                                            out.print("<option value='" + (inventario.getVolumen_primario().add(salida.getVolumen_primario())) + "'>" + (inventario.getVolumen_primario().add(salida.getVolumen_primario())) + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <td style="padding-left: 10px;"><label>Num. piezas a sacar</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="num_pieza_primario" id="num_pieza_primario" value="<%= salida.getNum_pieza_primario()%>" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()"></td>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen a sacar</label></td>
                            <td style="padding-left: 10px;"><input type="number" step=".001" name="volumen_primario" id="volumen_primario" value="<%= salida.getVolumen_primario()%>" min="0" max="99999.999" required="" onclick="salidaMaderaRolloPermitido()"></td>
                        </tr>

                        <tr>
                            <td colspan="2"> <label>Clasificación secundario</label></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Piezas en existencia</label></td>
                            <td style="padding-left: 10px;">
                                <select name="num_pieza_secundarioE" id="num_pieza_secundarioE">
                                    <%
                                        for (InventarioMaderaRollo inventario : inventarioMR) {
                                            out.print("<option value='" + (inventario.getNum_pieza_secundario() + salida.getNum_pieza_secundario()) + "'>" + (inventario.getNum_pieza_secundario() + salida.getNum_pieza_secundario()) + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen existente</label></td>
                            <td style="padding-left: 10px;">
                                <select name="volumen_secundarioE" id="volumen_secundarioE">
                                    <%
                                        for (InventarioMaderaRollo inventario : inventarioMR) {
                                            out.print("<option value='" + (inventario.getVolumen_secundario().add(salida.getVolumen_secundario())) + "'>" + inventario.getVolumen_secundario().add(salida.getVolumen_secundario()) + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <td style="padding-left: 10px;"><label>Num. piezas a sacar</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="num_pieza_secundario" id="num_pieza_secundario" value="<%= salida.getNum_pieza_secundario()%>" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()"></td>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen a sacar</label></td>
                            <td style="padding-left: 10px;"><input type="number" step=".001" name="volumen_secundario" id="volumen_secundario" value="<%= salida.getVolumen_secundario()%>" min="0" max="99999.999" required="" onclick="salidaMaderaRolloPermitido()"></td>
                        </tr>

                        <tr>
                            <td colspan="2"> <label>Clasificación terciaria</label></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Piezas en existencia</label></td>
                            <td style="padding-left: 10px;">
                                <select name="num_pieza_terciarioE" id="num_pieza_terciarioE">
                                    <%
                                        for (InventarioMaderaRollo inventario : inventarioMR) {
                                            out.print("<option value='" + (inventario.getNum_pieza_terciario() + salida.getNum_pieza_terciario()) + "'>" + (inventario.getNum_pieza_terciario() + salida.getNum_pieza_terciario()) + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen existente</label></td>
                            <td style="padding-left: 10px;">
                                <select name="volumen_terciarioE" id="volumen_terciarioE">
                                    <%
                                        for (InventarioMaderaRollo inventario : inventarioMR) {
                                            out.print("<option value='" + inventario.getVolumen_terciario().add(salida.getVolumen_terciario()) + "'>" + inventario.getVolumen_terciario().add(salida.getVolumen_terciario()) + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <td style="padding-left: 10px;"><label>Num. piezas a sacar</label></td>
                        <td style="padding-left: 10px;"><input type="number" name="num_pieza_terciario" id="num_pieza_terciario" value="<%= salida.getNum_pieza_terciario()%>" min="0" max="999" required="" onclick="salidaMaderaRolloPermitido()"></td>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen a sacar</label></td>
                            <td style="padding-left: 10px;"><input type="number" step=".001" name="volumen_terciario" id="volumen_terciario" value="<%= salida.getVolumen_terciario()%>" min="0" max="99999.999" required="" onclick="salidaMaderaRolloPermitido()"></td>
                        </tr>

                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/SalidaMaderaRolloController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
