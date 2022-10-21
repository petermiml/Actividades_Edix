// ========================= Declaración de variables globales =================

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
var valoresAceptados = /^[0-9]*(\.?)[0-9]+$/;
var mostrarFormuTarjeta;
var mostrarFormuEfectivo;
var importeEfectivo;

// ========================== Principal ========================================

window.addEventListener("load",init);
function init(){
    initVariables();
    initEventos();
    ocultarDatosTarjeta();
    ocultarDatosEfectivo();
    mostrarImporteEfectivo();
}

// ======================== Función inicializadora de variables =================

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
    mostrarFormuTarjeta = document.getElementById("formuDatosTarjeta");
    mostrarFormuEfectivo = document.getElementById("formuDatosEfectivo"); 
    importeEfectivo = document.getElementById("importeEfectivo");

}

// ========================== Función inicializadora de eventos ================

function initEventos(){
    btnAddCarrito.addEventListener("click", sumarPrecioCarrito);
    btnImprimir.addEventListener("click", imprimir);
    btnReset.addEventListener("click", restab);
    forma_pago.addEventListener("click",escribirFormulario);
    acepto_condiciones.addEventListener("change", permisoImpresion);
}

// =========================== Resto de funciones ===============================

// --------------------------- Pedro Gómez --------------------------------------
// Primer punto

function sumarPrecioCarrito(){
    var pre_tot = parseFloat(precio_total.value);
    var precioNumerico = false;

    /* Si el nombre del artículo está vacío escribe en la pantalla que falta 
    el artículo, si no, deja vacío el aviso.
    */
    if(nombre_art.value == ''){
        aviso1.innerHTML = " &nbsp&nbspFalta artículo.";
    }else{
        aviso1.innerHTML = "";
    }

    /* Si el precio del artículo está vacío escribe en la pantalla que falta el
     artículo, si no, lo deja vacío.
    * Si el valor introducido no es un número, avisa de que el dato debe ser 
     numérico.
    * Si el precio es inferior a 0, avisará de que no puede ser inferior a 0 
    (igual a 0 si, debido a un posible
    descuento).
    * Si ninguna de las condiciones anteriores se cumple, quitará el aviso.
    */

    if(precio_art.value == ''){
        aviso2.innerHTML = " &nbsp&nbspFalta precio.";
    }else if(precio_art.value.match(valoresAceptados)){
        aviso2.innerHTML = "";
        precioNumerico = true;
    }else if(precio_art.value < 0){
        aviso2.innerHTML = "El precio no puede ser inferior a 0. Introduce un precio válido.";
    }else{
        aviso2.innerHTML = "&nbsp&nbspEl precio debe ser numérico.";  
    }

    /* Si las unidades son inferiores o iguales a 0, escribe en la pantalla 
    que el número de unidades no puede ser 0 o negativo.
    */
    if(unidades.value <= 0){
        aviso3.innerHTML = " &nbsp&nbspEl número de unidades no puede ser menor o igual a 0.";
    }else{
        aviso3.innerHTML = "";
    }

    /*  Si el nombre del articulo no está vacío, el precio es numérico y las 
    unidades son superiores o iguales a 1:
    -multiplicara el precio del articulo por el de unidades y se lo sumará al 
    precio total.
    -Posteriormente, mostrará el precio total redondeado a dos decimales. 
    -Además introducirá en el campo del carrito los artículos añadidos separados
    por comas si son mas de uno.
    */

    if(nombre_art.value != '' && precioNumerico == true & parseInt(unidades.value) >= 1){
        aviso1.innerHTML = '';
        aviso2.innerHTML = '';
        aviso3.innerHTML = '';

        pre_tot += parseFloat(precio_art.value)*parseInt(unidades.value);
        precio_total.value = pre_tot.toFixed(2);

        if(artEnCarrito.value == ''){
            artEnCarrito.value += nombre_art.value;
        }else{
            artEnCarrito.value += ', ' + nombre_art.value;
        }

        nombre_art.value = '';
        precio_art.value = '';
        unidades.value = 1;    
        nombre_art.focus();
    }
}

// ------------------------------------------- Ahmed ------------------------------------------
// Segundo punto. Formas de pago.

// Quedarán ocultas  las dos tablas de entrada. 
function ocultarDatosTarjeta(){
    mostrarFormuTarjeta.style.display = 'none';
}
function ocultarDatosEfectivo(){
    mostrarFormuEfectivo.style.display ='none';
}
/* Funcion para rellenar los datos de pago.
* Si se selecciona “Tarjeta” aparecerán tres nuevas cajas de texto para introducir 
los datos de la tarjeta bancaria.
* Si se selecciona “Efectivo” aparecerá una nueva caja de texto con el importe 
total del carrito.
*/ 
function escribirFormulario(){
    if(forma_pago.value =="tarjeta"){
        mostrarFormuTarjeta.style.display ='block';
        mostrarFormuEfectivo.style.display ='none';
    }
    else if(forma_pago.value =="efectivo"){
        mostrarFormuEfectivo.style.display ='block';
        mostrarFormuTarjeta.style.display = 'none';
        mostrarImporteEfectivo();
    }else{
        mostrarFormuEfectivo.style.display ='none';
        mostrarFormuTarjeta.style.display = 'none';
    }
}
function mostrarImporteEfectivo(){
    importeEfectivo.value = precio_total.value;
}
// ------------------------------------------- Dani --------------------------------------------
/* Tercer punto
* El botón “Imprimir” se habilitará cuando se acepten las condiciones de compra.
*/
function permisoImpresion(){
    
    if(acepto_condiciones.checked == true){
        
        btnImprimir.disabled = false;
    }else{
        
        btnImprimir.disabled = true;
    }
    }

// ------------------------------------------- Pedro García------------------------------------------
/* Cuarto punto

* Cuando se pulse el botón "imprimir" debe mostrar a través de una ventana, 
tanto la lista de la compra final como el precio final del carrito según
muestra la imágen.
* Si no se ha seleccionado forma de pago, deberá aparecer un mensaje 
"seleccione método de pago".
*/

function imprimir(){
    if(forma_pago.value == "seleccione"){
        alert("Seleccione método de pago");
    
    }else{ 
        alert("Los artículos del carrito son: " + artEnCarrito.value + "\n" + 
            "El precio total es: " + precio_total.value + " €" 
         + "\n" + "Forma de pago: " + forma_pago.value);

    }

}

// ------------------------------------------- Dani --------------------------------------------
/* Quinto punto
*Con el botón “Restablecer” se resetean todas las cajas de texto para poder
 introducir un nuevo producto. La caja para el nombre del artículo recibe 
 el foco para facilitar la entrada de datos. Inicializa la caja “Precio total 
 del carrito” a 0 para facilitar las operaciones aritméticas.
*/
function restab(){
    nombre_art.value = '';
    precio_art.value = '';
    precio_total.value = '';
    artEnCarrito.value = '';
    unidades.value = 1;
    aviso1.innerHTML = "";
    aviso2.innerHTML = "";
    aviso3.innerHTML = "";    
    nombre_art.focus();  
}
