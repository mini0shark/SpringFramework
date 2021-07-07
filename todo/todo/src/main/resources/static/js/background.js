const body = document.querySelector("body");
const IMG_NUMBER=6;


function paintImage(imgNumber){
    const image = new Image();
    image.src = `/imgs/${imgNumber}.jpg`;
    image.classList.add("bgImage");
    body.appendChild(image);
}
function genRandom(){
    const number = Math.floor(Math.random()* IMG_NUMBER);
    return number+1;
}
function init(){
    const randomNumber=genRandom();
    paintImage(randomNumber);
}

init();