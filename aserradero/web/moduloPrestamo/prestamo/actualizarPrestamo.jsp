<%--
    Document   : actualizarPrestamo
    Created on : 06-nov-2016, 0:57:42
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Persona"%>
<%@page import="java.util.List"%>
<%@page import="entidades.empleado.Administrador"%>
<%@page import="entidades.prestamo.Prestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
    List <Persona> personas = (List<Persona>) request.getAttribute("personas");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp"%>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actualización de datos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los campos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/PrestamoController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_prestamo" value="<%=prestamo.getId_prestamo()%>"readonly="" required="">
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input type="date" class="form-control"  name="fecha" id="fecha" value="<%=prestamo.getFecha()%>" required="" maxlength="10">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Persona:</label>

                                        <select class="form-control"  name="id_prestador" required="">
                                            <%
                                                for (Persona persona : personas) {
                                                    if(prestamo.getId_prestador().substring(0, 18).equals(persona.getId_persona())){
                                                        out.print("<option selected=\"\" value='"+prestamo.getId_prestador()+"'>"+persona.getNombre()+"</option>");
                                                    }
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Administrador:</label>

                                        <select class="form-control"  name="id_empleado" required="">
                                            <option value="<%= prestamo.getId_empleado()%>"><%= prestamo.getEmpleado()%></option>
                                        </select>

                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input type="number" class="form-control"  name="monto_prestamo" value="<%=prestamo.getMonto_prestamo()%>" step="0.01" min="0.00" max="99999999.99"  required="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">% de interés mensual:</label>
                                    <input type="number" class="form-control"  name="interes" value="<%=prestamo.getInteres()%>" step="1" min="0" max="100"  required="">
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
