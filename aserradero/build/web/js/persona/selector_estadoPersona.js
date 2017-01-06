/*Seleccionar el estado que pertenece una localidad al registrar una persona*/
function seleccionarEstadoLocalidad() {
    var nombre_localidad = document.getElementById('nombre_localidad');
    var localidad_seleccionada = nombre_localidad.selectedIndex;

    document.getElementById('_nombre_municipio').selectedIndex = localidad_seleccionada;
    document.getElementById('_estado').selectedIndex = localidad_seleccionada;
    
    document.getElementById('nombre_municipio').value = document.getElementById('_nombre_municipio').value;
    document.getElementById('estado').value = document.getElementById('_estado').value;
    return null;
}