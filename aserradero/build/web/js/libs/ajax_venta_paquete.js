/* global alertify */

$(function(){
    $("#agregar_venta_paquete").off("click");
    $("#agregar_venta_paquete").on("click", function(e) {
        /*Función para agregar elementos a la cola de venta*/
        var id_venta=$("#id_venta").val();
        var volumen = $("#volumen").val();
        var id_madera = $("#id_madera").val();
        var num_piezas = $("#num_piezas").val();
        var monto = $("#monto").val();
        var tipo_madera = $("#tipo_madera").val();
        var numero_paquete = $("#numero_paquete").val();
        $.ajax({
            url: 'VentasAjaxController',
            type: 'POST',
            data: {'accion':"add_venta_paquete",'id_venta':id_venta,'numero_paquete':numero_paquete,'id_madera':id_madera,'volumen':volumen,'num_piezas':num_piezas,'monto':monto,'tipo_madera':tipo_madera},
            dataType: 'json'
        }).done(function (data){
                if(data.success==="true"){
                    $("#monto").val('');
                    $("#volumen").val('');
                    $("#num_detalle").val('');
                    $("#volumen_unitaria").val('');
                    $("#costo_volumen").val('');
                    $("#id_madera").val('');
                    $("#num_piezas").val('');
                    $("#pieza_existencia").val('');
                    $("#tipo_madera").val('Madera');
                    $("#numero_paquete").val('');
                    alertify.success("El producto fue agregado a la lista");
                    $("#detalle_producto_paquete").load('moduloVenta/ventaPaquete/detalleVentaPaquete.jsp');
                }else{
                    //alert(data.success);
                  alertify.error("El producto no fue agregado a la lista");
                  $("#detalle_producto_paquete").load('moduloVenta/ventaPaquete/detalleVentaPaquete.jsp');
                }
            });
        $("#detalle_producto_paquete").load('moduloVenta/ventaPaquete/detalleVentaPaquete.jsp');
    });
        //en caso de que se elimine una fila
    $(".eliminar_vp").off("click");
    $(".eliminar_vp").on("click", function(e) {
        /* Función para eliminar productos de la cola de venta*/
        var id = $(this).attr("id");
        var numero_paquete = $(this).attr("name");
        var tipo_madera = $(this).attr("form");
        var id_venta=$("#id_venta").val();
        var volumen = $("#volumen").val();
        var id_madera = $("#id_madera").val();
        var num_piezas = $("#num_piezas").val();
        var monto = $("#monto").val();
        $.ajax({
            url: 'VentasAjaxController',
            type: 'POST',
            data: {'accion':"del_venta_paquete",'id_venta':id_venta,'numero_paquete':numero_paquete,'id_madera':id,'tipo_madera':tipo_madera},
            dataType: 'json'
        }).done(function(data){
            if(data.success==="true"){
                alertify.success("El producto se borró con éxito");
                $("#detalle_producto_paquete").load('moduloVenta/ventaPaquete/detalleVentaPaquete.jsp');
            }else{
                alertify.error("El producto no pudo ser borrado");
                //alertify.error(data.msj);
                $("#detalle_producto_paquete").load('moduloVenta/ventaPaquete/detalleVentaPaquete.jsp');
            }
        });
        $("#detalle_producto_paquete").load('moduloVenta/ventaPaquete/detalleVentaPaquete.jsp');
    });
});
