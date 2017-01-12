/*
 * Funcion para calcular el monto máximo permitido al realizar un pago compra
 */
function establecerMontoAPagar() {
    //Verificamos el proveedor seleccionado
    var id_proveedor = document.getElementById('id_proveedor');
    var id_seleccionado = id_proveedor.selectedIndex;

    //Selecciona el monto asociado al proveedor seleccionado
    document.getElementById('cuenta_por_pagar').selectedIndex = id_seleccionado;
    document.getElementById('cuenta_por_cobrar').selectedIndex = id_seleccionado;

    //Selecciona cuenta por cobrar asociado al proveedor
    var cuenta_por_pagar = document.getElementById('cuenta_por_pagar').value;


    //El máximo monto que se puede pagar es equivalente a cuenta por pagar: (existe una tabla anticipo para otro caso) 
    document.getElementById('monto_pago').max = cuenta_por_pagar;
    document.getElementById('monto_pago').min = 0;
    document.getElementById('monto_por_cobrar').value = document.getElementById('cuenta_por_cobrar').value;
    return null;
}

// Función para calcular el cuenta pendiente por pagar al hacer pago
function calcularMontoPorPagar() {
    var cuenta_por_pagar = document.getElementById('cuenta_por_pagar').value;
    var monto_pago;

    if (cuenta_por_pagar.length > 0) {
        if (cuenta_por_pagar <= 0) { // no hay cuenta por pagar: no habrá cuenta pendiente
            document.getElementById('monto_por_pagar').value = 0;
        } else { // 
            monto_pago = document.getElementById('monto_pago').value;
            if (monto_pago.length > 0) {
                document.getElementById('monto_por_pagar').value = (cuenta_por_pagar - monto_pago).toFixed(2);
            }
        }

    }
    return null;
}