let msgText; 

window.onload = () => {
    msgText = document.getElementById("messageText");
    console.log("Texto inicial del párrafo:", msgText.innerText);

    const input = document.getElementById("inputText");
    console.log("Contenido del textarea:", input.value);

    const listElement = document.getElementById("formList");
    const elements = document.querySelectorAll("#formList li");

    elements.forEach((element, index) => {
        console.log(`Elemento ${index + 1}:`, element);
        element.textContent = `Nuevo elemento ${index + 1}`;
    });

    msgText.addEventListener("click", () => {
        msgText.style.color = "#FF5733";
    });
};

function showMessage() {
    console.log("¡Botón activado! Esta es la función 2.");
}

function showParagraph() {
    console.log("Contenido actual del párrafo:", msgText.innerHTML);
}


function addTextToList() {
    const input = document.getElementById("inputText");
    const list = document.getElementById("formList");

    if (input.value.trim() !== "") {  
        const newItem = document.createElement("li");
        newItem.textContent = input.value;
        list.appendChild(newItem);
        input.value = "";  
    }
}