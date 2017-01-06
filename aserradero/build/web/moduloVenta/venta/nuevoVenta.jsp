<%-- 
    Document   : nuevoVenta
    Created on : 21-sep-2016, 21:27:12
    Author     : lmarcoss
--%>


<%@page import="entidades.Empleado"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.MaderaClasificacion"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date fecha = Date.valueOf(LocalDate.now());
    List <Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    List <Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <%@ include file="/TEMPLATE/headNuevo.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <div>
            <form action="/aserradero/VentaController?action=nuevo" method="post" id="formregistro">
                <h3>Registrar venta</h3>
                
                <fieldset id="user-details">
                    <table>
                        <!--<input type="hidden" name="id_venta" maxlength="29" required="">-->
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" value="<%=fecha%>" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Id venta:</label></td>
                            <td style="padding-left: 10px;"><input type="text" name="id_venta" title="escribe un identificador para la venta"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Cliente</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_cliente" required="">
                                    <option></option>
                                    <%
                                        for (Cliente cliente : clientes) {
                                            out.print("<option value='"+cliente.getId_cliente()+"'>"+cliente.getCliente()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Empleado:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_empleado" required="">
                                    <option></option>
                                    <%
                                        for (Empleado empleado : empleados) {
                                            out.print("<option value='"+empleado.getId_empleado()+"'>"+empleado.getEmpleado()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Tipo pago:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="estatus" value="Sin pagar" id="estatus" required="" readonly=""/> 
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Tipo venta:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="tipo_venta" required="">
                                    <option></option>
                                    <option value="Paquete">Paquete</option>
                                    <option value="Mayoreo">Mayoreo</option>
                                    <option value="Extra">Extra</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/VentaController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <!--<td><input type="submit" value="Registrar" class="submit"/> </td>-->
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
