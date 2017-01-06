/*
 * Función para establecer máximo de pago para una venta
 * 
 */

function actualizarPagoVenta() {
    var monto = document.getElementById('monto').value;                 // monto total venta
    var pago_anterior = document.getElementById('pago_anterior').value; //pago registrado al hacer la venta
    
    if (pago_anterior.length > 0 & monto.length > 0) { //ambos tienen valor
        if (monto > pago_anterior) {
            document.getElementById('pago').max = monto;
//            alert(monto);
//            alert("monto");

        } else if (pago_anterior > monto) {
            document.getElementById('pago').max = pago_anterior;
//            alert(pago_anterior);
//            alert("pago_anterior");
//            alert(monto);
        } else if (monto >= pago_anterior) {
            document.getElementById('pago').max = monto;
//            alert("iguales");
//            alert(monto);
        }
    } else if (pago_anterior.length > 0) {
        document.getElementById('pago').max = pago_anterior;
    } else if (monto.length > 0) {
        document.getElementById('pago').max = monto;
    } else {
        document.getElementById('pago').max = 999999.99;
    }
    document.getElementById('pago').min = 0;
    return null;
}