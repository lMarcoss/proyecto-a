    /* global alertify */

window.onload=function(){
    var mensaje = document.getElementById('mensaje').value;
    switch (mensaje){
        // Mensajes de error para modulo Madera en rollo
        case "inventario_entrada_inalcanzable":
            alert("Verifica que los datos sea correctos\n\n\nNo se puede registrar una salida con número de piezas o volumen mayor al inventario existente\n\n");
            break;
        case "error_registrar_entrada_madera_rollo":
            alert("No se pudo crear el registro \n\nVerifica que tengas registrado los costos de las clasificaciones de madera en rollo, Primario, Secundario y Terciario \n\nDebe estar registrado las tres clasificaciones \n\nPara registrar los costos ve al menú \nMadera en rollo -> Clasificación");
            break;
        case "error_nuevo":
            alert("Ocurrió un error al intentar agregar nuevo registro");
            break;
        case "error_modificar":
            alert("Ocurrió un error al intentar modificar registro");
        case "error_actualizar":
            alert("Ocurrió un error al intentar guardar cambios");
            break;
        case "error_eliminar":
            alert("Ocurrió un error al intentar eliminar registro");
            break;
        case "error_registrar":
            alert("Ocurrió un error al crear nuevo registro, \nverifica que rellenastes correctamente los campos, o que el registro no esté registrado previamente");
            break;
        case "error_buscar_campo":
            alert("Ocurrió un error al intentar la búsqueda");
            break;
        case "error_buscar_id":
            alert("Error al buscar Id");
            break;
        case "registrado":                        
            alertify.alert("El nuevo registro fue exitoso");
            break;
        case "actualizado":
            alertify.alert("Los datos se actualizaron correctamente");
            break;
        case "eliminado":
            alertify.alert("El registro fue eliminado exitosamente");
            break;
        //MENSAJES DE PAGO_COMPRA
        case "pagado":
            alert("Pago realizado correctamente");
            break;
        case "monto_inalcanzable_proveedor":
            alert("No se puede realizar el pago, no existe cantidad suficiente en la cuenta de proveedor \n Intenta con pago Normal");
            break;
        case "cuenta_proveedor_no_encontrado":
            alert("No se puede realizar el pago, el proveedor no tiene cuenta");
            break;
        case "error_pago":
            alert("No se pudo realizar el pago");
            break;
        // mensjaes de cobro Venta
        case "error_cobro":
            alert("No se pudo realizar el cobro");
            break;
        case "monto_inalcanzable_cliente":
            alert("No se puede realizar el pago! No hay cantidad suficiente en la cuenta del cliente \n Intenta con pago con pago inmediado");
            break;
        case "cuenta_cliente_no_encontrado":
            alert("No se puede realizar el pago! El cliente no tiene cuenta \n Intenta con pago inmediato");
            break;
        case "error_ticket":
            alert("No se puede puede generar ticket sin cobrar la venta! intenta con el enlace cobrar venta");
            break;
        default: //alert(mensaje);
            break;
    }
    return null;
};