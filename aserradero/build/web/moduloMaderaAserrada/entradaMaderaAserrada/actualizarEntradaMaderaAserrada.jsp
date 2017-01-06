<%-- 
    Document   : actualizarEntradaMaderaAserrada
    Created on : 28-sep-2016, 9:48:54
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.EntradaMaderaAserrada"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    EntradaMaderaAserrada entrada = (EntradaMaderaAserrada) request.getAttribute("entrada");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <!--Formulario de registro-->
        <div>
            <form action="/aserradero/EntradaMaderaAserradaController?action=actualizar" method="post" id="formregistro">
                <h3>Modificar datos de madera aserrada</h3>
                <fieldset id="user-details">
                    <table>
                        <input type="hidden" name="id_entrada" value="<%=entrada.getId_entrada()%>" readonly=""/>
                        <tr>
                            <td style="padding-left: 10px;"><label>Fecha:</label></td>
                            <td style="padding-left: 10px;">
                                <input type="date" name="fecha" value="<%=entrada.getFecha()%>" readonly="" required=""/>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Madera:</label></td>
                            <td style="padding-left: 10px;">
                                <select name="id_madera" required="" id="id_madera" disabled="">
                                    <option value="<%=entrada.getId_madera()%>"><%=entrada.getId_madera()%></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 10px;"><label>Número de piezas:</label></td>
                            <td style="padding-left: 10px;"><input type="number" name="num_piezas" id="num_piezas" value="<%=entrada.getNum_piezas()%>" min="0" max="999" required="" title="Escribe la cantidad de piezas"/></td>
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
