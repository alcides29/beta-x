// Captura la posicion donde se hace click izquierdo para tomar una accion
function onLeftClick(fil, col){
  if ( juego.tablero.isBandera(fil, col) )
      return;
  if ( juego.tablero.isMina(fil, col) ){
      juego.juegoPerdido(fil, col);
      return;
  }
  juego.tablero.flip(fil, col);
  if (0 == juego.tablero.downs){
    //alert("ganaste");
    juego.tablero.showBanderas();
    juego.finJuego();
  }
}

// Toma una accion al hacer click derecho
function onRightClick(fil, col){
  if ( juego.tablero.isDown(fil, col) )
    return;
  
  juego.tablero.cambiarEstado(fil, col);
  
  if (juego.tablero.banderas == juego.tablero.minas)
    juego.verificarJuegoGanado();
}

// Captura un evento del mouse
function onMouseDown(e){
  var img = (e? e.target: window.event.srcElement);
  if ( juego.tablero.isCelda(img) ){
    
    // obtiene que boton del mouse se presiono
    var evento = (e? e.which: window.event.button);
    if ( evento == 1)
      onLeftClick(img.mRow, img.mCol);
    else
      onRightClick(img.mRow, img.mCol);
  }
  juego.actualizarBombas();
  return(false);
}

// Toma el control del mouse
function controlarMouse(){
  juego.tablero.div.onmousedown   = onMouseDown;
  juego.tablero.div.onclick       = function(){return false;};
  juego.tablero.div.ondblclick    = function(){return false;};
  juego.tablero.div.oncontextmenu = function(){return false;};
}

// Crea el tablero
function crearTablero(){
  switch( document.getElementById("bn-nivel").value ){
    case "facil"    : juego.tablero.create(9, 9,  10); break;
    case "medio"    : juego.tablero.create(16, 16,  40); break;
    case "avanzado" : juego.tablero.create(16, 20,  99); break;
  }
}

// Objeto Juego
function Game(){
    var self = this;

    this.tablero     = new Board();
    this.iniciarTiempo = null;

    this.run = function(){
        self.iniciar();
    }
    this.iniciar = function(){
        crearTablero();
        controlarMouse();
        self.iniciarTemporizador();
        self.actualizarBombas();
    }
    this.reiniciar = function(){
        self.tablero.destroyImgs();
        self.iniciar();
    }

    // Verifica si se gana el juego
    this.verificarJuegoGanado = function(){
        if ( self.tablero.testBanderas() ){
	  //alert("ganaste");
	  self.tablero.showBanderas();
	  self.finJuego();
	}
    }
    
    // Verifica si se pierde el juego
    this.juegoPerdido = function(fil, col){
        self.tablero.mostrarMinas(fil, col);
        document.innerHTML = "oops"; 
        self.finJuego();
    }
    
    this.finJuego = function(){
        self.stopTemporizador();
	self.tablero.div.onmousedown = null; //Soltamos el control del mouse
    }

    this.actualizarBombas = function(){
        document.getElementById("div-minas").innerHTML = String(self.tablero.minas - self.tablero.banderas);
    }

    this.iniciarTemporizador = function(){
        self.iniciarTiempo = new Date().getTime();
        self.temporizador();
    }
    this.stopTemporizador = function(){
        self.iniciarTiempo = null;
    }
    this.temporizador = function(){
        if (self.iniciarTiempo){
            var diff = Math.floor( ( new Date().getTime() - self.iniciarTiempo) / 1000);
            var mins = "0" + String( Math.floor(diff / 60) );
            var secs = "0" + String(diff % 60);
            document.getElementById("div-tiempo").innerHTML = 
                mins.substring(mins.length - 2) + ":" + secs.substring(secs.length - 2);
            setTimeout(self.temporizador, 1000);
        }
    }
}

// Tablero
function Board() {
    var self = this;

    this.div = document.getElementById("tablero");
  
    this.celdas = null;  
    this.fils  = 0;
    this.cols  = 0;
    this.minas = 0;
    this.downs = 0;
    this.banderas = 0;

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
        self.downs = (self.fils * self.cols) - self.minas;
        self.banderas = 0;

        self.celdas = new Array(self.fils);
        for (var fil = 0; fil != self.fils; ++ fil) {
            self.celdas[fil] = new Array(self.cols);
            for (var col = 0; col != self.cols; ++ col) {
                self.celdas[fil][col] = new Celda();
            }
        }
        for (var mina = 0; mina != self.minas; ++ mina){
            self.putRandMina();
        }
        for (var fil = 0; fil != self.fils; ++ fil) {
            for (var col = 0; col != self.cols; ++ col){
                this.createImg(fil, col);
            }
        }
        //self.createImgs();
    }
    this.putRandMina = function() {
        var fil, col;
        do{
            fil = rand(self.fils);
            col = rand(self.cols);
        }while( self.isMina(fil, col) );
 
        self.putMina(fil, col);
        self.roundMina(fil, col);
    }
    this.putMina = function(fil, col) {
        self.celdas[fil][col].value = 'm';
    }
    this.roundMina = function(fil, col) {
        for (var r = Math.max(fil - 1, 0); r <= Math.min(fil + 1, self.fils - 1); ++ r) {
            for (var c = Math.max(col - 1, 0); c <= Math.min(col + 1, self.cols - 1); ++ c) {
                if ( self.isMina(r, c) == false) {
                    ++ self.celdas[r][c].value;
                }
            }
        }
    }

    this.createImgs = function() {
        for (var fil = 0; fil != self.fils; ++ fil)
            for (var col = 0; col != self.cols; ++ col)
                this.createImg(fil, col);
    }
    //Crea la imagen de los cuadros
    this.createImg = function(fil, col) {
        var img = document.createElement("img");
    
        img.id = self.getImgId(fil, col);
        img.className = self.imgClass;
        img.style.width = String(self.imgWidth)  + "px";
        img.style.height = String(self.imgHeight) + "px";
        img.style.top = String( Math.floor( ( (350 - self.imgHeight * self.fils) / 2) + (fil * (self.imgHeight - 1) ) ) ) + "px";
        img.style.left = String( Math.floor( ( (650 - self.imgWidth  * self.cols) / 2) + (col * (self.imgWidth  - 1) ) ) ) + "px";
        img.src = self.getImgSrc("limpio");
        img.mRow = fil;
        img.mCol = col;

        self.div.appendChild(img);
    }
    this.destroyImgs = function() {
        for (var fil = 0; fil != self.fils; ++ fil)
            for (var col = 0; col != self.cols; ++ col)
                self.destroyImg(fil, col);
    }
    this.destroyImg = function(fil, col) {
        self.div.removeChild( document.getElementById( self.getImgId(fil, col) ) );
    }

    this.flip = function(fil, col) {
        if ( self.isDown(fil, col) )
            return;
        if ( self.isBandera(fil, col) )
            return;
        self.flipCelda(fil, col);
        if ( self.isVacio(fil, col) )
        self.roundFlip(fil, col);
    }
    this.flipCelda = function(fil, col) {
        self.getImgElement(fil, col).src = self.getImgSrc( self.celdas[fil][col].value );
        self.celdas[fil][col].estado = "down";
        -- self.downs;
    }
    this.roundFlip = function(fil, col) {
        var left  = (col > 0);
        var right = (col < self.cols - 1);
        if (fil > 0) {
            if (left) self.flip(fil - 1, col - 1);
            self.flip(fil - 1, col);
            if (right) self.flip(fil - 1, col + 1);
        }
        if (left) self.flip(fil, col - 1);
        if (right) self.flip(fil, col + 1);
        if (fil < self.fils - 1) {
            if (left) self.flip(fil + 1, col - 1);
            self.flip(fil + 1, col);
            if (right) self.flip(fil + 1, col + 1);
        }
    }
    
    // Cambia al siguiente estado (al hacer click derecho)
    this.cambiarEstado = function(fil, col) {
        if (self.celdas[fil][col].state == "limpio") {
            var change = "bandera";
            ++ self.banderas;
        } else if (self.celdas[fil][col].state == "bandera") {
            var change = "duda";
            -- self.banderas;
        } else if (self.celdas[fil][col].state == "duda") {
            var change = "limpio";
        }
        self.getImgElement(fil, col).src = self.getImgSrc(cambio);
        self.celdas[fil][col].estado = cambio;
    }

    this.testBanderas = function() {
        for (var fil = 0; fil != self.fils; ++ fil)
            for (var col = 0; col != self.cols; ++ col)
                if ( self.isBandera(fil, col) && ( self.isMina(fil, col) == false ) )
                    return(false);
                return(true);
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
                    self.getImgElement(fil, col).src = self.getImgSrc(dibujo);
                }
            }
        }
    } 

  
  this.showBanderas = function() {
    for (var fil = 0; fil != self.fils; ++ fil)
      for (var col = 0; col != self.cols; ++ col)
        if ( self.isMina(fil, col) && ( self.isBandera(fil, col) == false) ){
          self.getImgElement(fil, col).src = self.getImgSrc("bandera");
          ++ self.banderas;
        }
  }

  this.isMina = function(fil, col) {
    return( 'm' == self.celdas[fil][col].value );
  }
  this.isVacio = function(fil, col) {
    return( 0 == self.celdas[fil][col].value );
  }
  this.isBandera = function(fil, col) {
    return( 'bandera' == self.celdas[fil][col].estado );
  }
  this.isDown = function(fil, col) {
    return( 'down' == self.celdas[fil][col].estado );
  }

  this.isCelda = function(img) {
    return(img.className == self.imgClass);
  }
  this.getImgElement = function(fil, col) {
    return( document.getElementById( self.getImgId(fil, col) ) );
  }
  this.getImgId = function(fil, col) {
    return( self.imgClass + String( (fil * self.cols) + col) );
  }
  this.getImgSrc = function(pic) {
    return(  self.imgURL + pic + self.imgExt );
  }
}

// Celdas del tablero
function Celda() {
  this.value = 0;
  this.estado = 'limpio';
}

// Funcion para generacion de numeros aleatorios
function rand(x) {
  return( Math.floor( Math.random() * x ) );
}

// Instanciacion y ejecucion del juego
var juego = new Game();
juego.run();