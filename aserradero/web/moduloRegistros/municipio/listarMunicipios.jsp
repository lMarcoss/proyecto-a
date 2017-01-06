<%--
    Document   : municipios
    Created on : 13-sep-2016, 13:13:32
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Municipio"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Municipio> municipios = (List<Municipio>) request.getAttribute("listaMunicipios");
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Municipios</title>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#municipios").css("background", "#448D00");
            });
        </script>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container" style="margin-top:60px;">
          <div class="row">
              <div class="col-lg-12">
                  <h2>Lista de municipios</h2>
              </div>
          </div>
          <div class="row">
              <div class="col-lg-12">
                  <div class="panel panel-primary">
                      <div class="panel-heading">
                          <h3 class="panel-title">Si el Municipio que busca no aparece, agréguelo</h3>
                      </div>
                      <div class="panel-body">
                          <div class="form-busc">
                            <form method="POST" action="/aserradero/MunicipioController?action=buscar">
                              <select name="nombre_campo" class="input-busc">
                                  <option value="nombre_municipio">Municipio</option>
                                  <option value="estado">Estado</option>
                                  <option value="telefono">Teléfono</option>
                              </select>
                              <input type="text" name="dato" class="input-busc" placeholder="Escriba su búsqueda">
                              <input type="submit" value="Buscar" class="btn btn-success" >
                            </form>
                          </div>
                          <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th>Municipio</th>
                                    <th>Estado</th>
                                    <th>Teléfono</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                              </thead>
                              <tbody>

                                  <%
                                      int i=0;
                                      for (Municipio municipio : municipios) {
                                          out.print("<tr>"
                                              +"<td>"+(i+1)+"</td>"
                                              +"<td>"+municipio.getNombre_municipio()+"</td>"
                                              +"<td>"+municipio.getEstado()+"</td>"
                                              +"<td>"+municipio.getTelefono()+"</td>"
                                              +"<td><a class=\"btn btn-warning\" href=\"/aserradero/MunicipioController?action=modificar&nombre_municipio="+municipio.getNombre_municipio()+"\">Modificar</a></td>"
                                              + "<td><a class=\"btn btn-danger\" href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/MunicipioController?action=eliminar&nombre_municipio="+municipio.getNombre_municipio()+"';};\">Eliminar</a></td>"
                                          + "</tr>" );
                                          i++;
                                      }
                                  %>
                              </tbody>
                          </table>
                          <div class="agregar_element">
                              <input type="button" class="btn btn-primary" value="Agregar municipio" onClick=" window.location.href='/aserradero/MunicipioController?action=nuevo' ">
                          </div>
                      </div>
                  </div><!-- panel fin-->
              </div>
          </div>
        </div>
    </body>
</html>
