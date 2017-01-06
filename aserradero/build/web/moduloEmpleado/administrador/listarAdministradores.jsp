<%-- 
    Document   : administradores
    Created on : 16-oct-2016, 23:48:32
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.empleado.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Administrador> administradores = (List<Administrador>) request.getAttribute("listaAdministradores");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Administradores</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>"
        
        <!-- ************************** opción de búsqueda-->
        <div>
            <form method="POST" action="/aserradero/AdministradorController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select name="nombre_campo" >
                            <option value="administrador">Administrador</option>
                            <option value="cuenta_inicial">Cuenta inicial</option>
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
                        <th>Administrador</th>
                        <th>Cuenta inicial</th>
                        <th></th>
                    </tr>
                    <%
                        int i=0;
                        for (Administrador administrador : administradores) {
                            out.print("<tr>"
                                +"<td>"+(i+1)+"</td>"
                                +"<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona="+administrador.getId_administrador()+"\">"+administrador.getNombre()+"</a></td>"
                                +"<td>"+administrador.getCuenta_inicial()+"</td>"
                                +"<td><a href=\"/aserradero/AdministradorController?action=modificar&id_administrador="+administrador.getId_administrador()+"\">Modificar cuenta</a></td>"
//                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/AdministradorController?action=eliminar&id_administrador="+administrador.getId_administrador()+"';};\">Eliminar</a></td>"
                            + "</tr>" );
                            i++;
                        }
                    %>
            </table>
            <div>
                <input type="button" value="Nuevo administrador" onClick=" window.location.href='/aserradero/AdministradorController?action=nuevo' ">
            </div>
        </div><!-- Resultado Consulta-->
    </body>
</html>
