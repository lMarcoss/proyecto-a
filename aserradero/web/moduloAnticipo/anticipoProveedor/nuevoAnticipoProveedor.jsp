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
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo anticipo de proveedor</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/AnticipoProveedorController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                   <label class="control-label">Fecha:</label>
                                   <input type="date" class="form-control" name="fecha" required="" />
                               </div>
                               <div class="form-group">
                                   <label class="control-label">proveedor:</label>
                                   <select class="form-control" name="id_proveedor" required="" title="Si no existe el proveedor que busca, primero agreguelo en la lista de proveedores">
                                       <option></option>
                                       <%
                                           for (Proveedor proveedor : proveedores) {
                                               out.print("<option value='"+proveedor.getId_proveedor()+"'>"+proveedor.getProveedor()+"</option>");
                                           }
                                       %>
                                   </select>
                               </div>
                               <div class="form-group">
                                   <label class="control-label">Monto:</label>
                                   <input name="monto_anticipo" type="number" class="form-control" min='0.01' max='99999999.99' step=".01" required=""/>
                               </div>
                               <div class="form-group pull-right">
                                   <a href="/aserradero/AnticipoProveedorController?action=listar"><input type="button" class="btn btn-warning"  value="Cancelar"/></a>
                                   <input type="submit" value="Guardar" class="btn btn-success" />
                               </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
