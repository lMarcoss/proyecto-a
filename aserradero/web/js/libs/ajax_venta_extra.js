/* global alertify */
$(function(){
    $("#agregar_Ven_Ex").off("click");//Agregar producto a venta extra
    $("#agregar_Ven_Ex").on("click", function(e) {
        var id_venta=$("#id_venta").val();
        var tipo = $("#tipo").val();
        var monto = $("#monto").val();
        var observacion = $("#observacion").val();
        $.ajax({
            url: 'VentasAjaxController',
            type: 'post',
            data: {'accion':"add_venta_extra",'id_venta':id_venta,'tipo':tipo,'monto':monto,'observacion':observacion},
            dataType: 'json'
        }).done(function(data){
            if(data.success==="true"){
                $("#monto").val('');
                $("#tipo").val('');
                $("#observacion").val('');
                $(".detalle-producto").load('moduloVenta/ventaExtra/detalleVentaExtra.jsp');
                $(".detalle-producto").load('moduloVenta/ventaExtra/detalleVentaExtra.jsp');
                alertify.success("El producto fue agregado con éxito");
            }else{
                alertify.error("El producto no puede ser agregado a lista");
                $(".detalle-producto").load('moduloVenta/ventaExtra/detalleVentaExtra.jsp');
            }
        });
    });
    //Eliminar
    $(".eliminar-ve").off("click");//Agregar producto a venta extra
    $(".eliminar-ve").on("click", function(e) {
        var id = $(this).attr("id");
        var id_venta=$("#id_venta").val();
        var tipo = $("#tipo").val();
        var monto = $("#monto").val();
        var observacion = $("#observacion").val();
        $.ajax({
            url: 'VentasAjaxController',
            type: 'POST',
            data: {'accion':"del_venta_extra",'id_venta':id_venta,'tipo':id,'monto':monto,'observacion':observacion},
            dataType: 'json'
        }).done(function(data){
            if(data.success==="true"){
                alertify.success("El producto fue borrado de la lista");
                $(".detalle-producto").load('moduloVenta/ventaExtra/detalleVentaExtra.jsp');
            }else{
                alertify.error("El producto no pudo ser borrado");
                $(".detalle-producto").load('moduloVenta/ventaExtra/detalleVentaExtra.jsp');
            }
        });
        $(".detalle-producto").load('moduloVenta/ventaExtra/detalleVentaExtra.jsp');
    });
});
