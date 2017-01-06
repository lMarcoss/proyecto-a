/*Seleccionar el estado que pertenece un municipio al registrar localidad*/
function seleccionarEstadoMunicipio() {
    var nombre_municipio = document.getElementById('nombre_municipio');
    var municipio_seleccionada = nombre_municipio.selectedIndex;

    document.getElementById('estado_municipio').selectedIndex = municipio_seleccionada;
    
    var estado = document.getElementById('estado_municipio').value;
    document.getElementById('estado').value = estado;
    return null;
}