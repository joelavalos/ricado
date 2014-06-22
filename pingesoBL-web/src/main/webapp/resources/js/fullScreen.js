/*
 * Función para poner el navegador en modo "pantalla completa"
 */
function toggleFullscreen() {
  if ((document.fullScreenElement && document.fullScreenElement !== null) ||    // alternative standard method
      (!document.mozFullScreen && !document.webkitIsFullScreen)) {               // current working methods
    if (document.documentElement.requestFullScreen) {
      document.documentElement.requestFullScreen();
    } else if (document.documentElement.mozRequestFullScreen) {
      document.documentElement.mozRequestFullScreen();
    } else if (document.documentElement.webkitRequestFullScreen) {
      document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
    }
  } else {
    if (document.cancelFullScreen) {
      document.cancelFullScreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
      document.webkitCancelFullScreen();
    }
  }
}

function launchFullScreen(element) {
    if(!isDocumentInFullScreenMode()){
        if (element.requestFullscreen) {
            element.requestFullscreen();
          } else if (element.msRequestFullscreen) {
            element.msRequestFullscreen();
          } else if (element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
          } else if (element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
          }
    }else{
        cancelFullscreen();      
    }
}
/*
 * Función para sacar al navegador del modo "pantalla compelta
 */
function cancelFullscreen() {
  if(document.cancelFullScreen) {
    document.cancelFullScreen();
  } else if(document.mozCancelFullScreen) {
    document.mozCancelFullScreen();
  } else if(document.webkitCancelFullScreen) {
    document.webkitCancelFullScreen();
  }
}

function isDocumentInFullScreenMode() {
  return ((document.fullScreenElement && document.fullScreenElement !== null) ||    // alternative standard methods
      (!document.mozFullScreen && !document.webkitIsFullScreen));                   // current working methods
}

