/* global alertify */

$(function(){
    $("#ver_pdf_VM").off("click");
    $("#ver_pdf_VM").on("click", function(e) {
        /*Obtener toda la información de la venta mayoreo*/
        var cliente	=$("[name=cliente]").val();
        var direccion=$("[name=direccion]").val();
        var id_venta=$("[name=id_venta]").val();
        var localidad=$("[name=localidad]").val();
        var municipio=$("[name=municipio]").val();
        var estado=$("[name=estado]").val();
        var fecha=$("[name=fecha]").val();
        var id_maderas= new Array();
        var id_madera= new Array();
        id_madera=$("[name=id_madera]").val();
        
        $.ajax({
            url: 'GenerarpdfticketVM',
            type: 'POST',
            data: {'cliente':cliente,'direccion':direccion,'id_venta':id_venta,'localidad':localidad,'municipio':municipio,'estado':estado,'fecha':fecha,'id_madera':id_madera},
            dataType: 'json'
        }).done(function (data){
            if(data.success==="true"){
                alertify.success("PDF GENERADO");
            }else{
              alertify.error("El PDF NO PUDO SER GENERADO");
            }
        });
    });
});
