<%-- 
    Document   : primer_usuario
    Created on : 18/01/2017, 03:39:32 AM
    Author     : lmarcoss
--%>

<%@page import="entidades.registros.Municipio"%>
<%@page import="entidades.registros.Localidad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Municipio> municipios = (List<Municipio>) request.getAttribute("listaMunicipios");
    List<Localidad> localidades = (List<Localidad>) request.getAttribute("listaLocalidades");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="col-md-13">
                    <h2>Registrar Usuario</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-13">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta para que el registro sea con éxito</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/UsuarioController?action=insertar_primer_registro" method="post" id="formregistro">
                                <!-- 
                                *************************************************************************************************
                                Datos personales
                                *************************************************************************************************
                                -->

                                <div>
                                    <h3>Datos personales</h3>
                                    <div class="lado_derecho"><!-- Grupo derecho-->
                                        <div class="form-group">
                                            <label class="control-label">Id_persona:</label>
                                            <input class="form-control" type="text" name="id_persona" id="id_persona" maxlength="18" placeholder="Escriba su CURP de la persona" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <i class="glyphicon glyphicon-user"></i>
                                            <label class="control-label">Nombre (s):</label>
                                            <input class="form-control" type="text" name="nombre" id="nombre" maxlength="45" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <i class="glyphicon glyphicon-user"></i>
                                            <label class="control-label">Apellido paterno:</label>
                                            <input class="form-control" type="text" name="apellido_paterno" id="apellido_paterno" maxlength="45" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <i class="glyphicon glyphicon-user"></i>
                                            <label class="control-label">Apellido materno:</label>
                                            <input class="form-control" type="text" name="apellido_materno" id="apellido_materno" maxlength="45"/>
                                        </div>
                                    </div>
                                    <div class="lado_izquierdo"><!-- Grupo Izquierdo -->
                                        <div class="form-group">
                                            <label class="control-label">Dirección:</label>
                                            <input class="form-control" type="text" name="direccion" title="dirección" placeholder="Ejemplo: Avenida Juárez #17" maxlength="60"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Sexo:</label>
                                            <select class="form-control" name="sexo" id="sexo">
                                                <option value="H">Hombre</option>
                                                <option value="M">Mujer</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <i class="glyphicon glyphicon-calendar"></i>
                                            <label class="control-label">Fecha de nacimiento:</label>
                                            <input class="form-control" type="date" name="fecha_nacimiento" id="fecha_nacimiento" required="" maxlength="10" title="Es importante la fecha de nacimiento para asignar un identificador a la persona">
                                        </div>
                                        <div class="form-group">
                                            <i class="glyphicon glyphicon-phone"></i>
                                            <label class="control-label">Teléfono:</label>
                                            <input class="form-control" type="text" name="telefono" pattern="[0-9]{10}" title="10 dígitos" placeholder="951xxxxxxx" maxlength="10"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- 
                                *************************************************************************************************
                                Usuario
                                *************************************************************************************************
                                -->
                                <div>
                                    <h3>Usuario</h3>
                                    <div class="lado_derecho"><!-- Grupo derecho-->
                                        <div class="form-group">
                                            <label for="nombre_usuario">Nombre de usuario:</label>
                                            <input class="form-control" type="text" name="nombre_usuario" placeholder="Escribe un nombre de usuario" maxlength="45" required="" />
                                        </div>
                                    </div>
                                    <div class="lado_izquierdo"><!-- Grupo Izquierdo -->
                                        <div class="form-group">
                                            <label for="password">Contraseña</label>
                                            <input class="form-control" type="password" name="contrasenia" placeholder="Escribe una contraseña nueva" maxlength="20" required=""/>
                                        </div>
                                    </div>
                                </div>
                                <!-- 
                                *************************************************************************************************
                                Localidad
                                *************************************************************************************************
                                -->
                                <div>
                                    <h3>Localidad</h3>
                                    <div class="lado_derecho"><!-- Grupo derecho-->
                                        <div class="form-group">
                                            <label class="control-label">Nombre de la localidad:</label>
                                            <input type="text" class="form-control" name="nombre_localidad" id="nombre_localidad"  maxlength="59" placeholder="Escribe nombre localidad o selecciona de la lista si aparece -->" required=""/>
                                        </div>    
                                    </div>
                                    <div class="lado_izquierdo"><!-- Grupo Izquierdo -->
                                        <div class="form-group">
                                            <label class="control-label">Lista de localidades:</label>
                                            <select class="form-control" name="_nombre_localidad" id="_nombre_localidad" required="" onblur="seleccionarEstadoLocalidad()">
                                                <option></option>
                                                <%
                                                    for (Localidad localidad : localidades) {
                                                        out.print("<option value='" + localidad.getNombre_localidad() + "'>" + localidad.getNombre_localidad() + ", " + localidad.getNombre_municipio() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <!-- 
                                *************************************************************************************************
                                Municipio
                                *************************************************************************************************
                                -->
                                <div>
                                    <h3>Municipio</h3>
                                    <div class="lado_derecho"><!-- Grupo derecho-->
                                        <div class="form-group">
                                            <label class="control-label">Nombre del municipio:</label>
                                            <input type="text" class="form-control" name="nombre_municipio" maxlength="59" placeholder="Escribe nombre municipio o selecciona de la lista si aparece -->" required=""/>
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
                                    </div>
                                    <div class="lado_izquierdo"><!-- Grupo Izquierdo -->
                                        <div class="form-group">
                                            <label class="control-label">Lista de municipios:</label>
                                            <select class="form-control" name="_nombre_municipio" id="_nombre_municipio" required="">
                                                <option></option>
                                                <%
                                                    for (Municipio municipio : municipios) {
                                                        out.print("<option value='" + municipio.getNombre_municipio() + "'>" + municipio.getNombre_municipio() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <br>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-success col-md-6 pull-left" value="Guardar"/>
                                            <a href="/aserradero/UsuarioController?action=listar">
                                                <input type="button" class="btn btn-warning col-md-6 pull-right" value="Cancelar"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div><!-- Panel principal-->
                </div>
            </div>
        </div>        
    </body>
</html>
