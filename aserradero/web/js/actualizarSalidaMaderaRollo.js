/*
 * Funcion para definir el maximo de piezas a actualizar al modificar la salida de madera aserrada
 */
function actualizarCantidadPiezasPermitida(){
    // establecer piezas maximas
    var pieza_existente = document.getElementById('pieza_existente').value;
    var pieza_actualizar = document.getElementById('pieza_actualizar').value;
    
    if(pieza_existente == ''){
       pieza_existente = 0; 
    }
    if(pieza_actualizar == ''){
       pieza_actualizar = 0;
    }
    var cantidad_max_pieza = parseInt(pieza_existente) + parseInt(pieza_actualizar);
    document.getElementById('num_piezas').max = cantidad_max_pieza;
    
    //establecer volumen maximo
    
    return null;
}
function actualizarVolumenPermitido(){
    var volumen_existente = document.getElementById('volumen_existente').value;
    var volumen_actualizar = document.getElementById('volumen_actualizar').value;
    
    if(volumen_existente == ''){
        volumen_existente = 0;
    }
    
    if(volumen_actualizar == ''){
        volumen_actualizar = 0;
    }
    var volumen_maxima = parseInt(volumen_existente) + parseInt(volumen_actualizar);
    document.getElementById('volumen_total').max = volumen_maxima;
    return null;
}