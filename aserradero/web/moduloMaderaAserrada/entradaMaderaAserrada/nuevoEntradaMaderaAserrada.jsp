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
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nueva entrada de madera</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene todos los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/EntradaMaderaAserradaController?action=insertar" method="post" id="formregistro">
                                <input type="hidden" name="id_entrada" value="1" id="id_entrada"/>
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label></td>
                                        <input class="form-control" type="date" name="fecha" required=""/>
                                    </td>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Madera:</label></td>

                                        <select class="form-control" name="id_madera" required="" id="id_madera">
                                            <option></option>
                                            <%
                                                for (MaderaAserradaClasif clasificacion : clasificaciones) {
                                                    out.print("<option value='"+clasificacion.getId_madera()+"'>"+clasificacion.getId_madera()+"</option>");
                                                }
                                            %>
                                        </select>
                                    </td>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Número de piezas:</label></td>
                                    <input type="number" class="form-control" name="num_piezas" id="num_piezas" min="1" max="999" required="" title="Escribe la cantidad de piezas"/></td>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/EntradaMaderaAserradaController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a> </td>
                                    <input type="submit" class="btn btn-success" value="Guardar"/></td>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
