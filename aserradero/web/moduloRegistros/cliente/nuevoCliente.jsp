<%--
    Document   : nuevoCliente
    Created on : 27-sep-2016, 1:04:02
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Persona"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
        <script>
            $(document).ready(function ($){
                 $("#registros").css("background","#448D00");
                 $("#clientes").css("background","#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>REGISTRO DE UN NUEVO CLIENTE</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/ClienteController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Cliente:</label>
                                    <select class="form-control" name="id_persona" required="" title="Si no existe la persona que busca, primero agreguelo en la lista de personas">
                                        <option></option>
                                        <%
                                            List <Persona> personas = (List<Persona>) request.getAttribute("personas");
                                            for (Persona persona : personas) {
                                                out.print("<option value='"+persona.getId_persona()+"'>"+persona.getNombre()+"</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/ClienteController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>
