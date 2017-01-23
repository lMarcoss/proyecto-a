<%--
    Document   : nuevoMunicipio
    Created on : 13-sep-2016, 17:20:34
    Author     : lmarcoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container" >
            <div class="row">
                <div class="col-md-12">
                    <h2>Registrar municipio</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/MunicipioController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label" for="name">Nombre municipio:</label>
                                    <input type="text" class="form-control" name="nombre_municipio"  pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="59" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Estado:</label>
                                    <select name="estado" id="estado" class="form-control" required="">
                                        <option selected="disables" disabled="">Seleccione un estado de la lista</option>
                                        <option value="AGUASCALIENTES">AGUASCALIENTES</option>
                                        <option value="BAJA CALIFORNIA">BAJA CALIFORNIA</option>
                                        <option value="BAJA CALIFORNIA SUR">BAJA CALIFORNIA SUR</option>
                                        <option value="CAMPECHE">CAMPECHE</option>
                                        <option value="COAHUILA">COAHUILA</option>
                                        <option value="COLIMA">COLIMA</option>
                                        <option value="CHIAPAS">CHIAPAS</option>
                                        <option value="CHIHUAHUA">CHIHUAHUA</option>
                                        <option value="DISTRITO FEDERAL">DISTRITO FEDERAL</option>
                                        <option value="DURANGO">DURANGO</option>
                                        <option value="GUANAJUATO">GUANAJUATO</option>
                                        <option value="GUERRERO">GUERRERO</option>
                                        <option value="HIDALGO">HIDALGO</option>
                                        <option value="JALISCO">JALISCO</option>
                                        <option value="MÉXICO">MÉXICO</option>
                                        <option value="MICHOACÁN">MICHOACÁN</option>
                                        <option value="MORELOS">MORELOS</option>
                                        <option value="NAYARIT">NAYARIT</option>
                                        <option value="NUEVO LEÓN">NUEVO LEÓN</option>
                                        <option value="OAXACA">OAXACA</option>
                                        <option value="PUEBLA">PUEBLA</option>
                                        <option value="QUERÉTARO">QUERÉTARO</option>
                                        <option value="QUINTANA ROO">QUINTANA ROO</option>
                                        <option value="SAN LUIS POTOSÍ">SAN LUIS POTOSÍ</option>
                                        <option value="SINALOA">SINALOA</option>
                                        <option value="SONORA">SONORA</option>
                                        <option value="TABASCO">TABASCO</option>
                                        <option value="TAMAULIPAS">TAMAULIPAS</option>
                                        <option value="TLAXCALA">TLAXCALA</option>
                                        <option value="VERACRUZ">VERACRUZ</option>
                                        <option value="YUCATÁN">YUCATÁN</option>
                                        <option value="ZACATECAS">ZACATECAS</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="telefono">Teléfono:</label>
                                    <input type="text" class="form-control" name="telefono" pattern="[0-9]{10}" title="10 dígitos"/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/MunicipioController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                    <input class="btn btn-success" type="submit" value="Guardar"/>
                                </div>
                            </form>
                        </div>
                    </div><!-- Panel principal-->
                </div>
            </div>
        </div>        
    </body>
</html>
