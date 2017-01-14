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
                            <form action="/aserradero/EntradaMaderaAserradaController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_entrada" value="<%=entrada.getId_entrada()%>" readonly=""/>
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input class="form-control" type="date" name="fecha" value="<%=entrada.getFecha()%>" readonly="" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Madera:</label>
                                    <select class="form-control" name="id_madera" required="" id="id_madera" disabled="">
                                        <option value="<%=entrada.getId_madera()%>"><%=entrada.getId_madera()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Número de piezas:</label>
                                    <input class="form-control" type="number" name="num_piezas" id="num_piezas" value="<%=entrada.getNum_piezas()%>" min="0" max="999" required="" title="Escribe la cantidad de piezas"/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/EntradaMaderaAserradaController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
        <!--Formulario de registro-->
        <div>

                <h3>Modificar datos de madera aserrada</h3>
                <fieldset id="user-details">
                    <table>

                    </table>
                </fieldset>
            </form>
        </div><!--Fin Formulario de registro-->
    </body>
</html>
