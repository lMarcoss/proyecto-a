<%-- 
    Document   : actualizarAdministrador
    Created on : 28-nov-2016, 15:55:29
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Administrador administrador = (Administrador) request.getAttribute("administrador");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Modificar administrador</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        
        <!-- ******************* Formulario de registro-->
        <div>
            <form action="/aserradero/AdministradorController?action=actualizar" method="post" id="formregistro">
                <h3>Modificar cuenta inicial</h3>
                <fieldset id="user-details">
                    <table>
                        <tr>
                            <td style="padding-left: 10px;"><label>Administrador:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_administrador" id="id_administrador" required="">
                                    <option value="<%=administrador.getId_administrador()%>" selected=""><%=administrador.getNombre()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Cuenta incial</label></td>
                            <td style="padding-left: 10px;">
                                <input name="cuenta_inicial" type="number" value="<%=administrador.getCuenta_inicial()%>" min='0.01' max='99999999.99' step=".01"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/AdministradorController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
