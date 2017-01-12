/*
 * Funcion para asignar el maximo de piezas y volumen para las salidas madera rollo
 */
function salidaMaderaRolloPermitido(){
    // Obtenemos cantidades en existencia
    var num_pieza_primario = document.getElementById('num_pieza_primarioE').value;
    var volumen_primario = document.getElementById('volumen_primarioE').value;
    
    var num_pieza_secundario = document.getElementById('num_pieza_secundarioE').value;
    var volumen_secundario = document.getElementById('volumen_secundarioE').value;
    
    var num_pieza_terciario = document.getElementById('num_pieza_terciarioE').value;
    var volumen_terciario = document.getElementById('volumen_terciarioE').value;
    
    //Establecemos cantidades en existencia como valores máximos
    document.getElementById('num_pieza_primario').max = num_pieza_primario;
    document.getElementById('volumen_primario').max = volumen_primario;
    
    document.getElementById('num_pieza_secundario').max = num_pieza_secundario;
    document.getElementById('volumen_secundario').max = volumen_secundario;
    
    document.getElementById('num_pieza_terciario').max = num_pieza_terciario;
    document.getElementById('volumen_terciario').max = volumen_terciario;
    return null;
}
