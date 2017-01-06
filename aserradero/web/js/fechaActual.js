/* 
 * Función para obtener la fecha actual
 */
function obtenerFechaActual(){
    
    var fecha = new Date();
    var dia = fecha.getDate();
    var mes = (fecha.getMonth() +1);
    if(dia < 10){
        dia = "0" + dia;
    }
    if(mes < 10){
        mes = "0" +mes;
    }
    var hoy = dia + "-" + mes + "-" + fecha.getFullYear();
    document.getElementById('fecha').value = dia + "-" + mes + "-" + fecha.getFullYear();
    alert("Hoy es:" + hoy);
}
window.onload = function() {
    obtenerFechaActual();
};  