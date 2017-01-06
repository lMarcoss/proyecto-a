<%-- 
    Document   : nuevoPagoEmpleado
    Created on : 29/09/2016, 08:15:45 AM
    Author     : Marcos
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoEmpleadoController?action=insertar" method="post" id="formregistro">
                <h3>Agregar pago empleado</h3>
                <fieldset id="user-details">
                    <table>
                         <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" /></td>
                        </tr>
       
                        <tr>
                            <td style="padding-left: 10px;"><label>empleado:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_empleado" required="" title="Si no existe el empleado que busca, primero agreguelo en la lista de empleados">
                                    <option></option>
                                    <%
                                        List <Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
                                        for (Empleado empleado : empleados) {
                                            out.print("<option value='"+empleado.getId_empleado()+"'>"+empleado.getEmpleado()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="monto" type="number" min='0.01' max='99999999.99' step=".01" required=""/>                             
                            </td>
                        </tr>
                       
                        
                        <tr>
                            <td style="padding-left: 10px;"><label>Observacion:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="observacion" type="text"/>                             
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoEmpleadoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
