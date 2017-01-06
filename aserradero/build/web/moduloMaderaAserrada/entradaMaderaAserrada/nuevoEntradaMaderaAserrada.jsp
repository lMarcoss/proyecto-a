<%-- 
    Document   : nuevoEntradaMaderaAserrada
    Created on : 28-sep-2016, 9:48:39
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.MaderaAserradaClasif"%>
<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <MaderaAserradaClasif> clasificaciones = (List<MaderaAserradaClasif>) request.getAttribute("clasificaciones");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <div>
            <form action="/aserradero/EntradaMaderaAserradaController?action=insertar" method="post" id="formregistro">
                <h3>Registrar madera aserrada</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_entrada" value="1" id="id_entrada"/>
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;">
                                <input type="date" name="fecha" required=""/>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Madera:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_madera" required="" id="id_madera">
                                    <option></option>
                                    <%
                                        for (MaderaAserradaClasif clasificacion : clasificaciones) {
                                            out.print("<option value='"+clasificacion.getId_madera()+"'>"+clasificacion.getId_madera()+"</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Número de piezas:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="num_piezas" id="num_piezas" min="1" max="999" required="" title="Escribe la cantidad de piezas"/></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><a href="/aserradero/EntradaMaderaAserradaController?action=listar"><input type="button" value="Cancelar"/></a> </td>
                            <td style="padding-left: 10px;"><input type="submit" value="Guardar"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
