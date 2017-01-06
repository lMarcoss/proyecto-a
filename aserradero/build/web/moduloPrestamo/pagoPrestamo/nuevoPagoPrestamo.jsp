<%-- 
    Document   : nuevoPagoPrestamo
    Created on : 27-nov-2016, 22:27:07
    Author     : lmarcoss
--%>

<%@page import="entidades.prestamo.Prestamo"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Prestamo> listaPrestamo = (List<Prestamo>) request.getAttribute("listaPrestamo");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <script src="/aserradero/js/selectorPagoPrestamo.js"></script>
        <title>Nuevo pago</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PagoPrestamoController?action=insertar" method="post" id="formregistro">
                <h3>Registrar pago</h3>
                <fieldset id="user-details">
                    <table>
                        <%
                            if(!listaPrestamo.isEmpty()){
                        %>
                        <tr>
                            <td style="padding-left: 10px;"><label>Préstamo a pagar:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_prestamo" id="id_prestamo" required="" title="Seleccione el préstamo" onblur="seleccionarPagoPrestamo()">
                                    <option></option>
                                    <%
                                        for(Prestamo prestamo: listaPrestamo){
                                            out.print("<option value='"+prestamo.getId_prestamo()+"'>Prestado el "+prestamo.getFecha()+" por "+prestamo.getPrestador()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto préstamo:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="monto_prestamo" id="monto_prestamo" required="" disabled="">
                                    <option></option>
                                    <%
                                        for(Prestamo prestamo: listaPrestamo){
                                            out.print("<option value='"+prestamo.getMonto_prestamo()+"'>"+prestamo.getMonto_prestamo()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha de préstamo:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="fecha_prestamo" id="fecha_prestamo" required="" disabled="">
                                    <option></option>
                                    <%
                                        for(Prestamo prestamo: listaPrestamo){
                                            out.print("<option value='"+prestamo.getFecha()+"'>"+prestamo.getFecha()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto pagado:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="monto_pagado" id="monto_pagado" required="" disabled="">
                                    <option></option>
                                    <%
                                        for(Prestamo prestamo: listaPrestamo){
                                            out.print("<option value='"+prestamo.getMonto_pagado()+"'>"+prestamo.getMonto_pagado()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto por pagar:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="monto_por_pagar" id="monto_por_pagar" required="" disabled="">
                                    <option></option>
                                    <%
                                        for(Prestamo prestamo: listaPrestamo){
                                            out.print("<option value='"+prestamo.getMonto_por_pagar()+"'>"+prestamo.getMonto_por_pagar()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label for="fecha">Fecha de pago:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" placeholder="AAAA/MM/DD"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto a pagar:</label></td>
                            <td style="padding-left: 10px;"><input type="number" step="0.01" name="monto_pago" id="monto_pago" min="0.01" max="" required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoPrestamoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"></td>
                        </tr>
                        <%
                            }else{
                                out.println("<p style='color:red;'>No hay prestamos pendientes por pagar.</p>");
                        %>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PagoPrestamoController?action=listar"><input type="button" value="Aceptar"/></a> </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
