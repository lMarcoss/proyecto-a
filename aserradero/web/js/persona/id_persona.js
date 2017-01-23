function crearIdPersona(){
    var CURP = "";
        
    //Leer apellido paterno, convertir a mayuscula y eliminar sufijos y espacios
    var paterno = document.getElementById('apellido_paterno').value.toUpperCase();
    var letra_paterno;
    var vocal_paterno;
    if(paterno.length > 0){
        paterno = eliminarSufijos(paterno);
        letra_paterno = paterno.charAt(0);
        
        //buscar primer vocal después de la primera letra del apellido paterno
        vocal_paterno = buscarVocalApellidoPaterno(paterno);
    }
    
    
    
    //leer apeellido materno eliminar espacios y convertir a mayúsculas 
    var materno = document.getElementById('apellido_materno').value.toUpperCase();
    var letra_materno;
    if(materno.length > 0){
        materno = eliminarSufijos(materno);
    }
    
    if(materno.length > 0){
        letra_materno = materno.charAt(0);
    }else{
        letra_materno = "X";
    }
    
    //Obtener letra nombre
    var nombre = document.getElementById('nombre').value.toUpperCase();
    var letra_nombre;
    if(nombre.length > 0){
        nombre = eliminarPrimerNombre(nombre);
        letra_nombre = nombre.charAt(0);
    }
    
    // Obtener fecha de nacimiento:se utilizará los 4 digitos de año
    var fecha = document.getElementById("fecha_nacimiento").value;
    if(fecha.length > 0){
        fecha = obtenerFechaNacimiento(fecha);
    }
    
    
    //Obtener sexo
    var sexo = document.getElementById("sexo").value.toUpperCase();
    
    //obtener estado
    var estado = document.getElementById("estado").value.toUpperCase();
    if(estado.length > 0){
        estado = obtenerEstado(estado);
    }
    
    //Obtener consonante interna no inicial del apellido paterno
    var consonante_paterno = obtenerConsonanteInterna(paterno);
    if(consonante_paterno.length < 1){
        consonante_paterno = "X";
    }
    
    // Obtener consonante interna no inicial del apellido materno
    var consonante_materno = "";
    if(materno.length>0){
        consonante_materno = obtenerConsonanteInterna(materno);
    }
    if(consonante_materno.length < 1 | materno.length < 1 ){
        consonante_materno = "X";
    }   
    
    //Consonante no inicial del nombre
    var consonante_nombre = obtenerConsonanteInterna(nombre);
    if(consonante_nombre.length <1){
        consonante_nombre = "X";
    }
    //concatenar resultados
    var CURP = letra_paterno + vocal_paterno + letra_materno + letra_nombre + fecha + sexo + estado + consonante_paterno + consonante_materno + consonante_nombre;
    CURP = CURP.replace("Ñ","X");
    
    document.getElementById('id_persona').value = CURP;
}

function eliminarEspaciosPrincipio(cadena){
    while(cadena.charAt(0).length < 1){
        cadena = cadena.substr(1, cadena.length - 1);
    }
    return cadena;
}
function obtenerConsonanteInterna(cadena){
    var letra_c = "";
    var consonantes = /^[BCDFGHJKLMNÑPQRSTVWXYZ]/i;
    var indice = 1;
    var encontrado = false;
    while(indice < cadena.length & !encontrado){
        if(consonantes.test(cadena.charAt(indice))){
            letra_c = cadena.charAt(indice);
            encontrado = true;
        }
        indice++;
    }
    return letra_c;
}
function obtenerFechaNacimiento(fecha){
    fecha = fecha.replace(/-/gi,"");
    if(fecha.length > 8){
        var letrasAEliminar = fecha.length - 8;
        var contador = 0;
        while(contador<letrasAEliminar){
            fecha = fecha.replace(fecha.charAt(0),"");
            contador++;
        }
    }
    return fecha;
}
function eliminarSufijos(cadena){
        cadena = cadena.replace("LAS","");
        cadena = cadena.replace("DEL","");
        cadena = cadena.replace("DE","");
        cadena = cadena.replace("LA","");
        cadena = cadena.replace("Y","");
        cadena = eliminarEspaciosPrincipio(cadena);
    return cadena;
}
function buscarVocalApellidoPaterno(paterno){
    var vocal_paterno = "";
    var vocales = /^[AEIOU]/i;
    var indice = 1;
    var encontrado = false;
    while(indice < paterno.length & !encontrado){
        if(vocales.test(paterno.charAt(indice))){
            vocal_paterno = paterno.charAt(indice);
            encontrado = true;
        }
        indice++;
    }
    return vocal_paterno;   
}
function eliminarPrimerNombre(nombre){
    nombre = eliminarEspaciosPrincipio(nombre);
    //verificar si tiene dos nombres: y Si el nombre es maria o jose eliminarlo
    if(/ /.test(nombre)){
        if(nombre.substring(0, nombre.search(" ")) == "MARIA"){
            nombre = nombre.replace("MARIA", "");
        }else if(nombre.substring(0, nombre.search(" ")) == "MARÍA"){
            nombre = nombre.replace("MARÍA", "");
        }else if(nombre.substring(0, nombre.search(" ")) == "JOSÉ"){
            nombre = nombre.replace("JOSÉ", "");
        }else if(nombre.substring(0, nombre.search(" ")) == "JOSE"){
            nombre = nombre.replace("JOSE", "");
        }
    }
    nombre = eliminarEspaciosPrincipio(nombre);
    return nombre;
}
function obtenerEstado(estado){
    switch(estado){
        case "AGUASCALIENTES": estado="AS"; break;
        case "BAJA CALIFORNIA":estado="BC"; break;
        case "BAJA CALIFORNIA SUR": estado="BS"; break;
        case "CAMPECHE": estado="CC"; break;
        case "COAHUILA DE ZARAGOZA": estado="CL"; break;
        case "COLIMA": estado="CM"; break;
        case "CHIAPAS": estado="CS"; break;
        case "CHIHUAHUA": estado="CH"; break;
        case "DISTRITO FEDERAL": estado="DF"; break;
        case "DURANGO": estado="DG"; break;
        case "GUANAJUATO": estado="GT"; break;
        case "GUERRERO": estado="GR"; break;
        case "HIDALGO": estado="HG"; break;
        case "JALISCO": estado="JC"; break;
        case "MÉXICO": estado="MC"; break;
        case "MICHOACÁN DE OCAMPO": estado="MN"; break;
        case "MORELOS": estado="MS"; break;
        case "NAYARIT": estado="NT"; break;
        case "NUEVO LEÓN": estado="NL"; break;
        case "OAXACA": estado="OC"; break;
        case "PUEBLA": estado="PL"; break;
        case "QUERÉTARO": estado="QT"; break;
        case "QUINTANA ROO": estado="QR"; break;
        case "SAN LUIS POTOSÍ": estado="SP"; break;
        case "SINALOA": estado="SL"; break;
        case "SONORA": estado="SR"; break;
        case "TABASCO": estado="TC"; break;
        case "TAMAULIPAS": estado="TS"; break;
        case "TLAXCALA": estado="TL"; break;
        case "VERACRUZ DE IGNACIO DE LA LLAVE": estado="VZ"; break;
        case "YUCATÁN": estado="YN"; break;
        case "ZACATECAS": estado="ZS"; break;
        default: estado ="NE"; break;
    }
    return estado;
}