<%-- 
    Document   : nuevoAnticipoProveedor
    Created on : 27/09/2016, 01:35:58 PM
    Author     : Marcos
--%>

<%@page import="entidades.registros.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
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
            <form action="/aserradero/AnticipoProveedorController?action=insertar" method="post" id="formregistro">
                <h3>Agregar anticipo proveedor</h3>
                <fieldset id="user-details">
                    <table >
                        
                         <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;"><input type="date" name="fecha" required="" /></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>proveedor:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_proveedor" required="" title="Si no existe el proveedor que busca, primero agreguelo en la lista de proveedores">
                                    <option></option>
                                    <%
                                        for (Proveedor proveedor : proveedores) {
                                            out.print("<option value='"+proveedor.getId_proveedor()+"'>"+proveedor.getProveedor()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Monto:</label></td>
                            <td style="padding-left: 10px;">
                                <input name="monto_anticipo" type="number" min='0.01' max='99999999.99' step=".01" required=""/>                             
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/AnticipoProveedorController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
