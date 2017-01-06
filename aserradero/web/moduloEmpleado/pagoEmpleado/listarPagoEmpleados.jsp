<%-- 
    Document   : pagoEmpleados
    Created on : 29/09/2016, 08:02:31 AM
    Author     : Marcos
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.empleado.PagoEmpleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <PagoEmpleado> pagoEmpleados = (List<PagoEmpleado>) request.getAttribute("listaPagoEmpleados");
    String mensaje=(String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pago empleados</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
        
        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/PagoEmpleadoController?action=buscar">
                <table class="table-condensed">
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                            <option value="fecha">Fecha</option>
                            <option value="empleado">Empleado</option>
                            <option value="monto">Monto</option>
                            <option value="observacion">Observación</option>
                        </select>
                        </td>
                        <td><input type="text" name="dato" placeholder="Escriba su búsqueda"></td>
                        <td colspan="2"><input type="submit" value="Buscar"></td>
                    </tr>
                </table>
            </form>
        </div> <!-- Fin opción de búsqueda-->
        
        <!-- ************************* Resultado Consulta-->
        <div>
            <table class="table-condensed">
                    <tr>
                        <th>N°</th>
                        <th>Fecha</th>
                        <th>Empleado</th>
                        <th>Monto</th>
                        <th>Observacion</th>
                        <th> </th>
                        <th> </th>
                    </tr>
                    <%
                        int i=0;
                        for (PagoEmpleado pagoEmpleado : pagoEmpleados) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                +"<td>"+pagoEmpleado.getFecha()+"</td>"    
                                +"<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona="+pagoEmpleado.getId_empleado()+"\">"+pagoEmpleado.getEmpleado()+"</a></td>"
                                +"<td>"+pagoEmpleado.getMonto()+"</td>"
                                +"<td>"+pagoEmpleado.getObservacion()+"</td>"
                                +"<td><a href=\"/aserradero/PagoEmpleadoController?action=modificar&id_pago_empleado="+pagoEmpleado.getId_pago_empleado()+"\">Actualizar</a></td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoEmpleadoController?action=eliminar&id_pago_empleado="+pagoEmpleado.getId_pago_empleado()+"';};\">Eliminar</a></td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
            </table>
            <div>
                <input type="button" value="Agregar pago empleado" onClick=" window.location.href='/aserradero/PagoEmpleadoController?action=nuevo' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
