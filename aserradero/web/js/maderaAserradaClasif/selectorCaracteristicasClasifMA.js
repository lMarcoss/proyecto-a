/*
 * Estas funciones se utiliza al registrar nueva clasificacion de Madera que se produce en el aserradero
 * : Selecciona el ancho y grueso de la clasificacion de acuerdo a las fracciones seleccionadas
 */
function seleccionarGrueso() {
    
    //Datos para Grueso
    var grueso_f = document.getElementById('grueso_f').value;
    if(grueso_f == "3/4"){
        document.getElementById('grueso').value = "0.75";
    }else if(grueso_f == "3 1/2"){
        document.getElementById('grueso').value = "3.5";
    }else if(grueso_f == "1 3/4"){
        document.getElementById('grueso').value = "1.75";
    }else if(grueso_f == "1 1/2"){
        document.getElementById('grueso').value = "1.50";
    }else{
        document.getElementById('grueso').value = "";
    }
    
    return null;
}
function seleccionarAncho() {
    //Datos para ancho
    var ancho_f = document.getElementById('ancho_f').value;
    if(ancho_f == "3 1/2"){
        document.getElementById('ancho').value = "3.50";
    }else if(grueso_f == ""){
        document.getElementById('ancho').value = "";
    }else{
        document.getElementById('ancho').value = ancho_f;
    }

    return null;
}