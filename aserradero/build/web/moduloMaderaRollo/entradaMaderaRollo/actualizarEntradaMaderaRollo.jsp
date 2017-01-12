<%--
    Document   : actualizarEntradaMaderaRollo
    Created on : 26/09/2016, 06:08:32 PM
    Author     : rcortes
--%>

<%@page import="entidades.maderaRollo.EntradaMaderaRollo"%>
<%@page import="entidades.registros.Proveedor"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    EntradaMaderaRollo entrada = (EntradaMaderaRollo) request.getAttribute("entrada");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    List<Empleado> choferes = (List<Empleado>) request.getAttribute("choferes");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/EntradaMaderaRolloController?action=actualizar" method="post" id="formregistro">
                <h3>Modificar entrada madera en rollo</h3>
                <fieldset id="user-details">
                    <table>
                        <td style="padding-left: 10px;"><input type="hidden" name="id_entrada" value="<%=entrada.getId_entrada()%>"  readonly=""/></td>

                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=entrada.getFecha()%>"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="id_proveedor">Proveedor</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_proveedor" required="" title="Si no existe el proveedor que busca, primero agreguelo en la lista de proveedores">
                                    <%
                                        for (Proveedor proveedor : proveedores) {
                                            if (entrada.getId_proveedor().equals(proveedor.getId_proveedor())) {
                                                out.print("<option selected=\"selected\" value='" + proveedor.getId_proveedor() + "'>" + proveedor.getProveedor() + "</option>");
                                            } else {
                                                out.print("<option value='" + proveedor.getId_proveedor() + "'>" + proveedor.getProveedor() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="id_chofer">Chofer</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_chofer" required="" title="Si no existe el empleado que busca, primero agreguelo en la lista de empleados">                                    
                                    <%
                                        for (Empleado chofer : choferes) {
                                            if (entrada.getId_chofer().equals(chofer.getId_empleado())) {
                                                out.print("<option selected=\"selected\" value='" + chofer.getId_empleado() + "'>" + chofer.getEmpleado() + "</option>");
                                            } else {
                                                out.print("<option value='" + chofer.getId_empleado() + "'>" + chofer.getEmpleado() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="num_piezas">Num. piezas primario</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="num_pieza_primario" value="<%=entrada.getNum_pieza_primario()%>" min="0" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen primario</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="volumen_primario" id="volumen" value="<%=entrada.getVolumen_primario()%>" step=".001" min="0.000" max="99999.999"  required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="num_piezas">Num. piezas secundario</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="num_pieza_secundario" value="<%=entrada.getNum_pieza_secundario()%>" min="0" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen secundario</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="volumen_secundario" id="volumen" value="<%=entrada.getVolumen_secundario()%>" step=".001" min="0.000" max="99999.999"  required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="num_piezas">Num. piezas terciario</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="num_pieza_terciario" value="<%=entrada.getNum_pieza_terciario()%>" min="0" required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Volumen terciario</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="volumen_terciario" id="volumen" value="<%=entrada.getVolumen_terciario()%>" step=".001" min="0.000" max="99999.999"  required=""/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/EntradaMaderaRolloController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
