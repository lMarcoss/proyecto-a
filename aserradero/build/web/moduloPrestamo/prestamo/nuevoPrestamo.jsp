<%-- 
    Document   : nuevoPrestamo
    Created on : 06-nov-2016, 0:57:31
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="entidades.registros.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Persona> personas = (List<Persona>) request.getAttribute("personas");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp"%>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
         <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/PrestamoController?action=insertar" method="post" id="formregistro">
                <h3>Registrar préstamo</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_prestamo" value="1"readonly="" required="">
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" id="fecha" required="" maxlength="10"></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Prestador:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_prestador" required="">
                                    <option></option>
                                    <%
                                        for (Persona persona : personas) {
                                            out.print("<option value='"+persona.getId_persona()+"'>"+persona.getNombre()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="monto_prestamo" step="0.01" min="0.01" max="99999999.99"  required=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Interés mensual:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="interes" step="1" min="0" max="100"  required=""><label>%</label></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/PrestamoController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" id="registrar" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
