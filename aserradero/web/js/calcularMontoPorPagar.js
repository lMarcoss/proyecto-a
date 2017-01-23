/*
 */
function calcularMontoPorPagar(){
    //Verificamos el proveedor seleccionado
    
    var cuenta_pendiente = document.getElementById('cuenta_pendiente').value;
    
    // El máximo monto que se puede pagar es el monto asociado al proveedor 
    document.getElementById('monto_pago').max = cuenta_pendiente;
    
    var monto_por_pagar = cuenta_pendiente - document.getElementById('monto_pago').value;
    document.getElementById('monto_por_pagar').value = monto_por_pagar.toFixed(2);
    document.getElementById('monto_por_pagar').max = monto_por_pagar.toFixed(2);
    document.getElementById('monto_por_pagar').min = monto_por_pagar.toFixed(2);

    return null;
}