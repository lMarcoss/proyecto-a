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
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo registro</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PrestamoController?action=insertar" method="post" id="formregistro">
                                <input type="hidden" name="id_prestamo" value="1"readonly="" required="">
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input type="date" class="form-control"  name="fecha" id="fecha" required="" maxlength="10">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Prestador:</label>

                                        <select class="form-control"  name="id_prestador" required="">
                                            <option></option>
                                            <%
                                                for (Persona persona : personas) {
                                                    out.print("<option value='"+persona.getId_persona()+"'>"+persona.getNombre()+"</option>");
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input type="number" class="form-control"  name="monto_prestamo" step="0.01" min="0.01" max="99999999.99"  required="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">% de interés mensual:</label>
                                    <input type="number" class="form-control"  name="interes" step="1" min="0" max="100"  required="">
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/PrestamoController?action=listar"><input type="button" value="Cancelar" class="btn btn-warning"/></a>
                                    <input type="submit" id="registrar" value="Guardar" class="btn btn-success"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
