
// Actualiza la canidad de minas a descubrir
function actualizarBombas(){
    document.getElementById("minas").innerHTML = String(juego.tablero.minas - juego.tablero.banderas);
}

function resultado(text){
  alert( text );
}

// Verifica si se gana el juego
function juegoGanado(){
    for (var fil = 0; fil != juego.tablero.fils; ++ fil){
        for (var col = 0; col != juego.tablero.cols; ++ col){
            if ( juego.tablero.isMina(fil, col) && ( juego.tablero.isBandera(fil, col) == false) ){
                document.getElementById( juego.tablero.getImgId(fil, col) ).src = juego.tablero.getImgSrc("bandera");
                ++ self.banderas;
            }
        }
    }
    resultado("Ganaste!");
    juego.finJuego();
  
}

// Verifica si se pierde el juego
function juegoPerdido (fil, col){
    juego.tablero.mostrarMinas(fil, col);
    resultado("Perdiste!");
    juego.finJuego();
}

// Captura la posicion donde se hace click izquierdo para tomar una accion
function onLeftClick(fil, col){
    if ( juego.tablero.isBandera(fil, col) ) {
        return;
    }
    if ( juego.tablero.isMina(fil, col) ){
        juegoPerdido(fil, col);
        return;
    }
    juego.tablero.press(fil, col);
    if (0 == juego.tablero.libres){
        juegoGanado();
    }
}

// Toma una accion al hacer click derecho
function onRightClick(fil, col){
    if ( juego.tablero.celdas[fil][col].estado == 'visible' ){
        return;
    }
    juego.tablero.cambiarEstado(fil, col);
}

// Captura un evento del mouse
function onMouseDown(e){
  var img = (e? e.target: window.event.srcElement);
  var evento;
  
  if ( img.className == "celda" ){
      
    // obtiene que boton del mouse se presiono
    evento = (e? e.which: window.event.button);
    
    if ( evento == 1){
	onLeftClick(img.mRow, img.mCol);
    } else {
      onRightClick(img.mRow, img.mCol);
    }
  }
  actualizarBombas();
  return(false);
}

// Cuenta el tiempo transcurrido
function temporizador(){
  var diff;
  var mins;
  var secs;
  
  if (juego.iniciarTiempo){
    diff = Math.floor( ( new Date().getTime() - juego.iniciarTiempo) / 1000);
    mins = "0" + String( Math.floor(diff / 60) );
    secs = "0" + String(diff % 60);
    document.getElementById("tiempo").innerHTML = 
      mins.substring(mins.length - 2) + ":" + secs.substring(secs.length - 2);
    setTimeout( this.temporizador, 1000 );
  }
}
    
// Toma el control del mouse
function controlarMouse(){
  juego.tablero.div.onmousedown   = onMouseDown;
  juego.tablero.div.onclick       = function(){return false;};
  juego.tablero.div.oncontextmenu = function(){return false;};
}

// Crea el tablero
function crearTablero(){
  switch( document.getElementById("b-nivel").value ){
    case "facil":
        juego.tablero.create(9, 9,  10);
        break;
    case "medio":
        juego.tablero.create(16, 16,  40);
        break;
    case "avanzado":
        juego.tablero.create(16, 20,  99);
        break;
  }
}

// Objeto Juego
function Game(){

    this.tablero = new Board();
    this.iniciarTiempo = null;
    
    // Inicializa el juego
    this.iniciar = function(){
        crearTablero();
        controlarMouse();
        this.iniciarTemporizador();
        actualizarBombas();
    }
    
    // Reinicia el juego
    this.reiniciar = function(){
        this.tablero.eliminarImgs();
        this.iniciar();
    }
    
    // Detiene el tiempo y suelta el control del mouse al finalizar el juego
    this.finJuego = function(){
        this.iniciarTiempo = null;
	this.tablero.div.onmousedown = null;
    }  
    
    // Inicializa el tiempo
    this.iniciarTemporizador = function(){
        this.iniciarTiempo = new Date().getTime();
        temporizador();
    }
}

function Board() {
    var self = this;

    this.div = document.getElementById("tablero");

    this.imgClass  = "celda";
    this.imgWidth  = 25;
    this.imgHeight = 25;
    this.imgURL    = "imagenes/";
    this.imgExt    = ".png";

    //Crea el tablero
    this.create = function(filas, columnas, minas) {
        self.celdas = null;
        self.fils  = filas;
        self.cols  = columnas;
        self.minas = minas;
        self.libres = (self.fils * self.cols) - self.minas;
        self.banderas = 0;
        //crea una matriz de celdas de dimension filas x columnas
        self.celdas = new Array(self.fils);
        for (var fil = 0; fil != self.fils; ++ fil) {
            self.celdas[fil] = new Array(self.cols);
            for (var col = 0; col != self.cols; ++ col) {
                self.celdas[fil][col] = new Celda();
            }
        }
        self.minarTablero();
        for (var fil = 0; fil != self.fils; ++ fil) {
            for (var col = 0; col != self.cols; ++ col){
                this.createImg(fil, col);
            }
        }
        //self.createImgs();
    }
    // coloca las minas en el tablero y calcula los valores para las demas casillas
    this.minarTablero = function() {
        var mina = 0;
        var fila, columna;
        while(mina < self.minas){
            fila = Math.floor( Math.random() * self.fils );
            columna = Math.floor( Math.random() * self.cols );
            if (self.isMina(fila, columna)== false){
                //le damos el valor de 10 a las minas
                self.celdas[fila][columna].valor = 10;
                ++ mina;
            }
        }
        for (var fil = 0; fil != self.fils; ++ fil) {
            for (var col = 0; col != self.cols; ++ col) {
                if (self.isMina(fil, col)==false){
                    if(col>0){
                        if(fil>0&&self.isMina(fil-1, col-1)) {
                            ++ self.celdas[fil][col].valor;
                        }
                        if(self.isMina(fil, col-1)) {
                            ++ self.celdas[fil][col].valor;
                        }
                        if(fil<self.fils-1&&self.isMina(fil+1, col-1)) {
                            ++ self.celdas[fil][col].valor;
                        }
                    }
                    if(fil>0&&self.isMina(fil-1, col)) {
                        ++ self.celdas[fil][col].valor;
                    }
                    if(fil<self.fils-1&&self.isMina(fil+1, col)) {
                        ++ self.celdas[fil][col].valor;
                    }
                    if (col<self.cols-1){
                        if(fil>0&&self.isMina(fil-1, col+1)) {
                            ++ self.celdas[fil][col].valor;
                        }
                        if(self.isMina(fil, col+1)) {
                            ++ self.celdas[fil][col].valor;
                        }
                        if(fil<self.fils-1&&self.isMina(fil+1, col+1)) {
                            ++ self.celdas[fil][col].valor;
                        }
                        
                    }
                }
            }
        }
    }
    //crea la imagen del tablero
    this.createImgs = function() {
        for (var fil = 0; fil != self.fils; ++ fil)
            for (var col = 0; col != self.cols; ++ col)
                this.createImg(fil, col);
    }
    //Crea la imagen de los cuadros
    this.createImg = function(fil, col) {
        var img = document.createElement("img");

        img.id = self.getImgId(fil, col);
        img.className = "celda";
        img.style.width = String(self.imgWidth)  + "px";
        img.style.height = String(self.imgHeight) + "px";
        img.style.top = String( Math.floor( ( (350 - self.imgHeight * self.fils) / 2) + (fil * (self.imgHeight - 1) ) ) ) + "px";
        img.style.left = String( Math.floor( ( (650 - self.imgWidth  * self.cols) / 2) + (col * (self.imgWidth  - 1) ) ) ) + "px";
        img.src = self.getImgSrc("limpio");
        img.mRow = fil;
        img.mCol = col;

        self.div.appendChild(img);
    }
    //limpia el html de las <img> innecesarias
    this.eliminarImgs = function() {
        for (var fil = 0; fil != self.fils; ++ fil) {
            for (var col = 0; col != self.cols; ++ col) {
                self.div.removeChild( document.getElementById( self.getImgId(fil, col) ) );
            }
        }
    }
    

    //funcion q presiona una celda dejandola libre, se aplica recursion para los casos en q la celda no tenga minas adyacentes y presiona a las celdas adyacentes
    this.press = function(fil, col) {
        if ( self.celdas[fil][col].estado == 'visible' || self.isBandera(fil, col)) {
            return;
        }
        document.getElementById( self.getImgId(fil, col) ).src = self.getImgSrc( self.celdas[fil][col].valor );
        self.celdas[fil][col].estado = "visible";
        -- self.libres;
        if ( 0 == self.celdas[fil][col].valor ) {
            if(col>0){
                if(fil>0) {
                    self.press(fil-1, col- 1);
                }
                self.press(fil, col- 1);
                if(fil<self.fils-1) {
                    self.press(fil + 1, col - 1);
                }
            }
            if(fil>0) {
                self.press(fil - 1, col);
            }
            if(fil<self.fils-1) {
                self.press(fil + 1, col);
            }
            if (col<self.cols-1){
                if(fil>0) {
                    self.press(fil - 1, col + 1);
                }
                self.press(fil, col + 1);
                if(fil<self.fils-1) {
                    self.press(fil+1, col + 1);
                }
            }
        }   
    }

    
    // Cambia al siguiente estado (al hacer click derecho)
    this.cambiarEstado = function(fil, col) {
        if (self.celdas[fil][col].estado == "limpio") {
            var cambio = "bandera";
            ++ self.banderas;
        } else if (self.celdas[fil][col].estado == "bandera") {
            var cambio = "duda";
            -- self.banderas;
        } else if (self.celdas[fil][col].estado == "duda") {
            var cambio = "limpio";
        }
        document.getElementById( self.getImgId(fil, col) ).src = self.getImgSrc(cambio);
        self.celdas[fil][col].estado = cambio;
    }
    
    //muestra donde est�n todas las minas
    this.mostrarMinas = function(filBoom, colBoom) {
        for (var fil = 0; fil != self.fils; ++ fil) {
            for (var col = 0; col != self.cols; ++ col){
                if ( self.isMina(fil, col) || self.isBandera(fil, col) ) {
                    var dibujo = "mina";
                    if (self.isBandera(fil, col) ) {
                        if (self.isMina(fil, col)){
                            dibujo = "bandera";
                        } else {
                            dibujo = "error";
                        }
                    } else if (self.isMina(fil, col) ) {
                        dibujo = "mina";
                    }
                    if ((fil == filBoom) && (col == colBoom) ) {
                        dibujo = "boom";
                    }   
                    document.getElementById( self.getImgId(fil, col) ).src = self.getImgSrc(dibujo);
                }
            }
        }
    } 

    //funcion booleana q verifica si en una celda hay una mina, las celdas con minas se diferencian por tener el valor 10
    this.isMina = function(fil, col) {
        return( self.celdas[fil][col].valor == 10 );
    }
    //funcion booleana q verifica si una celda esta marcada con una bandera, las celdas marcadas con bandera poseen el estado "bandera"
    this.isBandera = function(fil, col) {
        return(  self.celdas[fil][col].estado == 'bandera');
    } 

    //obtiene el id de una celda, q es la fila-columna
    this.getImgId = function(fil, col) {
        return( String(fil) + "-" + String(col) );
    }
    //obtiene la direccion local en el q se encuentra la imagen a desplegar
    this.getImgSrc = function(dibujo) {
        return(  self.imgURL + dibujo + self.imgExt );
    }
}

// Celdas del tablero
function Celda() {
  this.valor = 0;
  this.estado = 'limpio';
}

// Instanciacion y ejecucion del juego
var juego = new Game();
juego.iniciar();