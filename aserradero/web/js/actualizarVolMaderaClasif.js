/*
 * Esta funcion se utiliza al registrar nueva clasificacion de Madera que se produce en el aserradero
 * :En el jsp: /maderaClasificacion/nuevoMaderaClasificacion
 * : Calcula el volumen dependiendo del ancho, largo y grueso de una madera
 */
function actualizarVolumenMaderaClasif(){
    // Leemos: grueso, ancho y largo de la madera
    var grueso = document.getElementById('grueso').value;
    var ancho = document.getElementById('ancho').value;
    var largo = document.getElementById('largo').value;
    var volumen = (grueso * ancho * largo)/12;
    volumen = volumen.toFixed(3);
    //Escribimos el volumen
    document.getElementById('volumen').value = volumen;
}