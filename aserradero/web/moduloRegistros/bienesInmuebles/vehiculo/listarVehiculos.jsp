<%--
    Document   : vehiculos
    Created on : 26/09/2016, 04:46:44 PM
    Author     : rcortes
--%>

<%@page import="entidades.registros.bienesInmuebles.Vehiculo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Vehiculo> vehiculos = (List<Vehiculo>) request.getAttribute("listaVehiculos");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Vehículos</title>
        <script>
            $(document).ready(function ($){
                 $("#registros").css("background","#448D00");
                 $("#vehiculos").css("background","#448D00");
                 $("#vehiculos1").css("background","#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container" >
            <div class="row">
                <div class="col-md-12">
                    <h1 class="page-header">Listado de vehículos</h1>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Si el vehículo no aparece, agréguelo</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-busc" >
                            <form method="POST" action="/aserradero/VehiculoController?action=buscar" >
                                <select name="nombre_campo" class="input-busc">
                                    <option value="matricula">Matrícula</option>
                                    <option value="tipo">Tipo</option>
                                    <option value="color">Color</option>
                                    <option value="carga_admitida">Carga máxima</option>
                                    <option value="motor">Motor</option>
                                    <option value="modelo">Modelo</option>
                                    <option value="costo">Costo</option>
                                    <option value="empleado">Registró</option>
                                </select>
                                <input type="text" class="input-busc"  name="dato" placeholder="Escriba su búsqueda" >
                                <input type="submit" value="Buscar" class="btn btn-success">
                            </form>
                        </div> <!-- Fin opción de búsqueda-->
                        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th >Matricula</th>
                                    <th>Tipo</th>
                                    <th>Color</th>
                                    <th>Carga admitida</th>
                                    <th>Motor</th>
                                    <th>Modelo</th>
                                    <th>Costo</th>
                                    <th>Registró</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                                int i=0;
                                for (Vehiculo vehiculo : vehiculos) {
                                    out.print("<tr>"
                                        +"<td>"+(i+1)+"</td>"
                                        +"<td>"+vehiculo.getMatricula()+"</td>"
                                        +"<td>"+vehiculo.getTipo()+"</td>"
                                        +"<td>"+vehiculo.getColor()+"</td>"
                                        +"<td>"+vehiculo.getCarga_admitida()+"</td>"
                                        +"<td>"+vehiculo.getMotor()+"</td>"
                                        +"<td>"+vehiculo.getModelo()+"</td>"
                                        +"<td>"+vehiculo.getCosto()+"</td>"
                                        +"<td>"+vehiculo.getEmpleado()+"</td>"
                                        +"<td><a class=\"btn btn-warning\" href=\"/aserradero/VehiculoController?action=modificar&id_vehiculo="+vehiculo.getId_vehiculo()+"\">Modificar</a></td>"
                                        + "<td><a class=\"btn btn-danger\" href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VehiculoController?action=eliminar&id_vehiculo="+vehiculo.getId_vehiculo()+"';};\">Eliminar</a></td>"
                                    + "</tr>" );
                                    i++;
                                }
                            %>
                            </tbody>
                        </table>
                        <div class="agregar_element">
                            <input type="button" class="btn btn-primary" value="Agregar vehiculo" onClick=" window.location.href='/aserradero/VehiculoController?action=nuevo' ">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
