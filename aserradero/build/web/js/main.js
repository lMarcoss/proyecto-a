
//Validar que ha escrito nombre de usuario y contraseña
window.addEventListener('load',function(){
    document.getElementById('entrar').addEventListener('click', function(){
        var nombre_usuario = document.getElementById('nombre_usuario').value;
        var contrasenia = document.getElementById('contrasenia').value;
        
        var iniciar = false;
        if(nombre_usuario.length > 0 && contrasenia.length > 0){
            iniciar = true;
        }
        if(iniciar){
            document.getElementById('forminiciar').submit();
        }else{
            alert("Escribe nombre de usuario y contraseña");
        }
    });
});

