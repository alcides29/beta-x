// Objeto Juego
function Game() {
    var self = this;

    this.board     = new Board();
    this.iniciarTiempo = null;

    this.run = function() {
        self.iniciar();
    }
    this.iniciar = function() {
        self.createBoard();
        self.registerMouse();
        self.iniciarTemporizador();
        self.actualizarBombas();
    }
    this.reiniciar = function() {
        self.board.destroyImgs();
        self.iniciar();
    }

    this.onMouseDown = function(e) {
        var img = getMouseObject(e);
        if ( self.board.isCelda(img) ){
            if ( getMouseButton(e) == 1)
                self.onLeftClick(img.mRow, img.mCol);
            else
                self.onRightClick(img.mRow, img.mCol);
        }
        self.actualizarBombas();
        return(false);
    }
    this.onLeftClick = function(fil, col) {
        if ( self.board.isBandera(fil, col) )
            return;
        if ( self.board.isMina(fil, col) ) {
            self.looseGame(fil, col);
            return;
        }
        self.board.flip(fil, col);
        if (0 == self.board.downs)
            self.winGame();
    }
    this.onRightClick = function(fil, col) {
        if ( self.board.isDown(fil, col) )
            return;
        self.board.changeState(fil, col);
        if (self.board.banderas == self.board.minas)
            self.tryWinGame();
    }
      
    // Verifica si se gana el juego
    this.tryWinGame = function() {
        if ( self.board.testBanderas() )
            self.winGame();
    }
    this.winGame = function() {
        //self.showSmiley("happy");
        //alert("ganaste");
        self.board.showBanderas();
        self.endGame();
    }
    this.looseGame = function(fil, col) {
        //self.showSmiley("sad");
        self.board.showMinas(fil, col);
        document.innerHTML = "oops"; 
        self.endGame();
    }
    this.endGame = function() {
        self.stopTemporizador();
        self.unregisterMouse();
    }

    this.actualizarBombas = function() {
        document.getElementById("div-minas").innerHTML = String(self.board.minas - self.board.banderas);
    }
      
    this.showSmiley = function(pic) {
        var img = document.getElementById("img-smiley");
        img.src = self.board.getImgSrc(pic);
        img.style.visibility = "visible";
    }

    this.iniciarTemporizador = function() {
        self.iniciarTiempo = new Date().getTime();
        self.temporizador();
    }
    this.stopTemporizador = function() {
        self.iniciarTiempo = null;
    }
    this.temporizador = function() {
        if (self.iniciarTiempo) {
            var diff = Math.floor( ( new Date().getTime() - self.iniciarTiempo) / 1000);
            var mins = "0" + String( Math.floor(diff / 60) );
            var secs = "0" + String(diff % 60);
            document.getElementById("div-tiempo").innerHTML = 
                mins.substring(mins.length - 2) + ":" + secs.substring(secs.length - 2);
            setTimeout(self.temporizador, 1000);
        }
    }

    this.registerMouse = function() {
        self.board.div.onmousedown   = self.onMouseDown;
        self.board.div.onclick       = function(){return false;};
        self.board.div.ondblclick    = function(){return false;};
        self.board.div.oncontextmenu = function(){return false;};
    }
    this.unregisterMouse = function() {
        self.board.div.onmousedown = null;
    }
      
    this.createBoard = function() {
        switch( document.getElementById("bn-nivel").value ) {
            case "facil"    : self.board.create(9, 9,  10); break;
            case "medio"  : self.board.create(16, 16,  40); break;
            case "avanzado": self.board.create(16, 20,  99); break;
        }
    }
}

// Board
function Board() {
    var self = this;

    this.div = document.getElementById("div-tablero");
  
    this.celdas = null;  
    this.fils  = 2;
    this.cols  = 0;
    this.minas = 0;
    this.downs = 0;
    this.banderas = 0;

    this.imgClass  = "celda";
    this.imgWidth  = 25;
    this.imgHeight = 25;
    this.imgURL    = "imagenes/";
    this.imgExt    = ".png";

    this.create = function(fils, cols, minas) {
        self.celdas = null;
        self.fils  = fils;
        self.cols  = cols;
        self.minas = minas;
        self.downs = (self.fils * self.cols) - self.minas;
        self.banderas = 0;

        self.createCeldas();
        self.putMinas();
        self.createImgs();
    }
  
    this.createCeldas = function() {
        self.celdas = new Array(self.fils);
        for (var fil = 0; fil != self.fils; ++ fil) {
            self.celdas[fil] = new Array(self.cols);
            for (var col = 0; col != self.cols; ++ col) {
                self.celdas[fil][col] = new Celda();
            }
        }
    }
  
  this.putMinas = function() {
    for (var mina = 0; mina != self.minas; ++ mina)
      self.putRandMina();
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
    for (var r = Math.max(fil - 1, 0); r <= Math.min(fil + 1, self.fils - 1); ++ r)
      for (var c = Math.max(col - 1, 0); c <= Math.min(col + 1, self.cols - 1); ++ c)
        if ( self.isMina(r, c) == false)
          ++ self.celdas[r][c].value;
  }

  this.createImgs = function() {
    for (var fil = 0; fil != self.fils; ++ fil)
      for (var col = 0; col != self.cols; ++ col)
        this.createImg(fil, col);
  }
  
  this.createImg = function(fil, col) {
    var img = document.createElement("img");
    
    img.id           = self.getImgId(fil, col);
    img.className    = self.imgClass;
    img.style.width  = String(self.imgWidth)  + "px";
    img.style.height = String(self.imgHeight) + "px";
    img.style.top    = String( Math.floor( ( (350 - self.imgHeight * self.fils) / 2) + (fil * (self.imgHeight - 1) ) ) ) + "px";
    img.style.left   = String( Math.floor( ( (650 - self.imgWidth  * self.cols) / 2) + (col * (self.imgWidth  - 1) ) ) ) + "px";
    img.src          = self.getImgSrc("limpio");
    img.mRow         = fil;
    img.mCol         = col;

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
    self.celdas[fil][col].state = "down";
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

  this.changeState = function(fil, col) {
    var change = self.getNextState(fil, col);
    if ( ("bandera" == change) && (self.banderas == self.minas) )
      return;

    if ("bandera" == change)
      ++ self.banderas;
    if ("duda" == change)
      -- self.banderas;

    self.getImgElement(fil, col).src = self.getImgSrc(change);
    self.celdas[fil][col].state = change;
  }
  
  // Cambia al siguiente estado (al hacer click derecho)
  this.getNextState = function(fil, col) {
    switch( self.celdas[fil][col].state ){
      case "limpio"      : return("bandera");
      case "bandera"    : return("duda");
      case "duda": return("limpio");
    }
  }

  this.testBanderas = function() {
    for (var fil = 0; fil != self.fils; ++ fil)
      for (var col = 0; col != self.cols; ++ col)
        if ( self.isBandera(fil, col) && ( self.isMina(fil, col) == false ) )
          return(false);
    return(true);
  }

  this.showMinas = function(filBoom, colBoom) {
    for (var fil = 0; fil != self.fils; ++ fil)
      for (var col = 0; col != self.cols; ++ col)
        if ( self.isMina(fil, col) || self.isBandera(fil, col) )
          self.showMina(fil, col, filBoom, colBoom);
  }
  
  // Muestra donde estan las minas una vez terminado el juego
  this.showMina = function(fil, col, filBoom, colBoom){
    var pic = "mina";
    
    if ( self.isBandera(fil, col) )
       pic = self.isMina(fil, col)? "bandera" : "cross";
    if ( self.isMina(fil, col) )
       pic = "mina";
    if ( (fil == filBoom) && (col == colBoom) )
      pic = "boom";

    self.getImgElement(fil, col).src = self.getImgSrc(pic);
//    alert("mina");
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
    return( 'bandera' == self.celdas[fil][col].state );
  }
  this.isDown = function(fil, col) {
    return( 'down' == self.celdas[fil][col].state );
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
  this.state = 'limpio';
}

// Funcion para generacion de numeros aleatorios
function rand(x) {
  return( Math.floor( Math.random() * x ) );
}

function getMouseObject(e) {
  return(e? e.target: window.event.srcElement);
}
function getMouseButton(e) {
  return(e? e.which: window.event.button);
}

// Instanciacion y ejecucion del juego
var juego = new Game();
juego.run();