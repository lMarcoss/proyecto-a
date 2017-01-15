<%--
    Document   : anticipoClientes
    Created on : 26/09/2016, 04:42:00 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoCliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<AnticipoCliente> anticipoClientes = (List<AnticipoCliente>) request.getAttribute("listaAnticipos");
    String mensaje = (String) request.getAttribute("mensaje");
%>



<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Anticipo Clientes</title>
    </head>
    <body>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
       <%@ include file="/TEMPLATE/menu.jsp" %>
       <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Anticipos de cliente</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de anticipos de clientes</h3>
                        </div>
                        <div class="panel-body">
                            <form method="POST" action="/aserradero/AnticipoClienteController?action=buscar">
                                <select name="nombre_campo" class="input-busc" >
                                    <option value="fecha">Fecha</option>
                                    <option value="cliente">Cliente</option>
                                    <option value="monto_anticipo">Monto anticipo</option>
                                    <option value="empleado">Registró</option>
                                </select>
                                <input type="search" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                <input type="submit" value="Buscar" class="btn btn-success" >
                            </form>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <th>N°</th>
                                    <th>Fecha</th>
                                    <th>Cliente</th>
                                    <th>Monto anticipo </th>
                                    <th>Registró</th>
                                    <th> </th>
                                </thead>
                                <tbody>
                                    <%

                                        int i = 0;
                                        for (AnticipoCliente anticipoCliente : anticipoClientes) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + anticipoCliente.getFecha() + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoCliente.getId_cliente() + "\">" + anticipoCliente.getCliente() + "</a></td>"
                                                    + "<td>" + anticipoCliente.getMonto_anticipo() + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + anticipoCliente.getId_empleado() + "\">" + anticipoCliente.getEmpleado() + "</a></td>"
                                                    + "<td><a class='btn btn-warning'  href=\"/aserradero/AnticipoClienteController?action=modificar&id_anticipo_c=" + anticipoCliente.getId_anticipo_c() + "\">Modificar</a></td>"
                                                    //                                + "<td><a class='btn btn-danger'  href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/AnticipoClienteController?action=eliminar&id_anticipo_c="+anticipoCliente.getId_anticipo_c()+"';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary"  value="Agregar anticipo cliente" onClick=" window.location.href = '/aserradero/AnticipoClienteController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->        
    </body>
</html>
