function validarPersona() {
    //Validar que la fecha tenga formato dd/mm/aaaa
    fecha_nacimiento = document.getElementById("fecha_nacimiento").value;
    if( fecha_nacimiento.length >10 | fecha_nacimiento.length<10) {
        alert("Error! el Formato de fecha debe ser: dd/mm/aaaa");
      return false;
    }else{
        return true;
    }
}