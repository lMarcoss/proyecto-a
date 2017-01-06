function seleccionarPagoPrestamo(){
    //Verificamos el id_prestamo seleccionado a pagar
    var id_prestamo = document.getElementById('id_prestamo');
    var id_seleccionado = id_prestamo.selectedIndex;
    //establecemos el valor del monto pago sin valor para evitar problemas
    document.getElementById('monto_pago').value = "";
    
    //Seleccionar el monto prestamo, fecha prestamo, monto pagado y monto por pagar
    document.getElementById('monto_prestamo').selectedIndex = id_seleccionado;
    document.getElementById('fecha_prestamo').selectedIndex = id_seleccionado;
    document.getElementById('monto_pagado').selectedIndex = id_seleccionado;
    document.getElementById('monto_por_pagar').selectedIndex = id_seleccionado;
    
    var monto_por_pagar = document.getElementById('monto_por_pagar').value;
    document.getElementById('monto_pago').max = monto_por_pagar;
    document.getElementById('monto_pago').min = 0.01;
}