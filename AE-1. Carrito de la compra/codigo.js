// =============================== Declaración de variables globales ==================================

var nombre_art;
var precio_art;
var unidades;
var aviso1, aviso2, aviso3;
var btnAddCarrito;
var artEnCarrito;
var precio_total;
var forma_pago;
var acepto_condiciones;
var btnImprimir;
var btnReset;


// =====================================================================================================

// ============================= Función inicializadora de variables ===================================

function initVariables(){
    nombre_art = document.getElementById("nombre_articulo");
    precio_art = document.getElementById("precio_articulo");
    unidades = document.getElementById("unidades");
    aviso1 = document.getElementById("aviso1");
    aviso2 = document.getElementById("aviso2");
    aviso3 = document.getElementById("aviso3");
    btnAddCarrito = document.getElementById("btnAddCarrito");
    artEnCarrito = document.getElementById("articulosEnCarrito");
    precio_total = document.getElementById("precio_total");
    forma_pago = document.getElementById("forma_pago");
    acepto_condiciones = document.getElementById("acepto_condiciones");
    btnImprimir = document.getElementById("btnImprimir");
    btnReset = document.getElementById("btnReset");
}

// ====================================================================================================

// =============================== Función inicializadora de eventos ==================================

function initEventos(){
    btnAddCarrito.addEventListener("click", sumarPrecioCarrito);
    btnImprimir.addEventListener("click", imprimir);
}
// =====================================================================================================

// ========================================= Resto de funciones ========================================

// ------------------------------------------- Pedro Gómez --------------------------------------------
// Primer punto

function sumarPrecioCarrito(){
    var pre_tot = parseFloat(precio_total.value);
    
    

    // Si el nombre del artículo está vacío escribe en la pantalla que falta el artículo, si no, deja vacío el aviso.
    if(nombre_art.value == ''){
        aviso1.innerHTML = " &nbsp&nbspFalta artículo.";
    }else{
        aviso1.innerHTML = "";
    }

    // Si el precio del artículo está vacío escribe en la pantalla que falta el artículo, si no, lo deja vacío.
    // Si el valor introducido no es un número, que el dato debe ser numérico.
    // Si ninguna de las condiciones anteriores se cumple, quitará el aviso.
    parseFloat(precio_art.value);
    if(precio_art.value == ''){
        aviso2.innerHTML = " &nbsp&nbspFalta precio.";
    }else if(typeof(precio_art.value == 'string')){
        aviso2.innerHTML = " &nbsp&nbspEl precio debe ser numérico.";
    }else{
        aviso2.innerHTML = "";
    }

    // Si las unidades son inferiores o iguales a 0, escribe en la pantalla que el número de unidades no puede ser 0 o negativo.
    if(unidades.value <= 0){
        aviso3.innerHTML = " &nbsp&nbspEl número de unidades no puede ser menor o igual a 0.";
    }else{
        aviso3.innerHTML = "";
    }


    if(nombre_art.value != '' && precio_art.value != '' /*& typeof(precio_art.value) != 'string'*/ & parseInt(unidades.value) >= 1){
        aviso1.innerHTML = '';
        aviso2.innerHTML = '';
        aviso3.innerHTML = '';

        pre_tot += parseFloat(precio_art.value)*parseInt(unidades.value);
        precio_total.value = pre_tot;

        if(artEnCarrito.value == ''){
            artEnCarrito.value += nombre_art.value;
        }else{
            artEnCarrito.value += ', ' + nombre_art.value;
        }

        nombre_art.value = '';
        precio_art.value = '';
        unidades.value = 1;    
        nombre_art.style.focus = true;
        nombre_art.focus();
    }
}

// ------------------------------------------- Pedro --------------------------------------------
// Cuando se pulse el botón "imprimir" debe mostrar a través de una ventana, tanto la lista de la compra final como el precio final del carrito según muestra la imágen.
// Si no se ha seleccionado forma de pago, deberá aparecer un mensaje "seleccione método de pago".

function imprimir(){
    if(forma_pago.value == "seleccione"){
        alert("Seleccione método de pago");
    
    }else{ 
        alert("Los artículos del carrito son " + artEnCarrito.value + "\n" + 
            "El precio total es: " + precio_total.value + " €" 
         + "\n" + "Forma de pago: " + forma_pago.value);

    }

}


// ------------------------------------------- Dani --------------------------------------------
// Quinto punto



// ------------------------------------------- Ahmed --------------------------------------------
// Segundo y tercer punto



// =====================================================================================================

// =========================================== Principal ===============================================

window.addEventListener("load",init);
function init(){
    initVariables();
    initEventos();
    
}

// =====================================================================================================