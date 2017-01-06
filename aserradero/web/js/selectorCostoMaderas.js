/*
 * Esta funcion se utiliza al registrar las ventas para seleccionar el costo de cada madera
 * :Y para calcular el monto a pagar por todas las piezas
 */
function seleccionarCostoMaderaVenta(){
    var id_madera = document.getElementById('id_madera');
    var madera_seleccionada = id_madera.selectedIndex;
    
    document.getElementById('pieza_existencia').selectedIndex = madera_seleccionada;
    document.getElementById('volumen_unitaria').selectedIndex = madera_seleccionada;
    document.getElementById('costo_volumen').selectedIndex = madera_seleccionada;
    document.getElementById('costo_pieza').selectedIndex = madera_seleccionada;
    
    //establecemos el maximo de piezas para venta
    var pieza_existencia = document.getElementById('pieza_existencia').value;
    document.getElementById('num_piezas').max = pieza_existencia;
    calcularVolumenTotal();
    return null;
}
function calcularVolumenTotal(){
    var volumen_unitaria = document.getElementById('volumen_unitaria').value;
    var num_piezas = document.getElementById('num_piezas').value;
    var volumen = volumen_unitaria * num_piezas;
    volumen = volumen.toFixed(3);
    document.getElementById('volumen').value = volumen;
    calcularMontoTotal();
    return null;
}

function calcularMontoTotal(){
    var costo_volumen = document.getElementById('costo_volumen').value;
    var volumen = document.getElementById('volumen').value;
    var monto = costo_volumen * volumen;
    
    monto = monto.toFixed(2);
    document.getElementById('monto').value = monto;
    return null;
}

function seleccionarCostoMaderaCompra(){
    var clasificacion = document.getElementById('clasificacion');
    var madera_seleccionada = clasificacion.selectedIndex;
    
    document.getElementById('costo_volumen').selectedIndex = madera_seleccionada;
    calcularMontoTotal();
}