function actualizarPagoPrestamo(){
    //Verificamos el id_prestamo seleccionado a pagar
    var monto_prestamo_a = document.getElementById('monto_prestamo_a').value;
    var monto_pago_a = document.getElementById('monto_pago_a').value; // monto_pago que se intenta actualizar
    var monto_por_pagar_a = document.getElementById('monto_por_pagar_a').value;
    
    var monto_pagado_total = monto_prestamo_a - monto_por_pagar_a;//Monto pagado contando el pago que se intenta actualizar
//    var monto_pagado_total = monto_pagado - monto_pago; // monto pagado sin contar el pago que se intenta actualizar
    
    // establecemos el maximo para el nuevo monto pago
    document.getElementById('monto_pago').max = monto_prestamo_a - (monto_pagado_total - monto_pago_a);
    
    //establecemos el valor del monto pago sin valor para evitar problemas
//    document.getElementById('monto_pago').value = "";
    
    //Seleccionar el monto prestamo, fecha prestamo, monto pagado y monto por pagar
//    document.getElementById('monto_prestamo').selectedIndex = id_seleccionado;
//    document.getElementById('fecha_prestamo').selectedIndex = id_seleccionado;
//    document.getElementById('monto_pagado').selectedIndex = id_seleccionado;
//    document.getElementById('monto_por_pagar').selectedIndex = id_seleccionado;
    
//    var monto_por_pagar = document.getElementById('monto_por_pagar').value;
//    document.getElementById('monto_pago').max = monto_por_pagar;
//    document.getElementById('monto_pago').min = 0.01;
}