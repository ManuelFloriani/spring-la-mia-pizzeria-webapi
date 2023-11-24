// COSTANTI
const root = document.getElementById('root');
const apiURL = 'http://localhost:8080/api/pizzas';

// FUNZIONI
    // Renderizza la card della pizza
    const renderPizza = (element) => {
        console.log(element);
        return `<div class="card shadow h-100">
            <img src="${element.imageUrl}" class="card-img-top" alt="pizza-pic">
            <div class="card-body">
                <h5 class="card-title">${element.name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${element.priceInCents / 100} €</h6>
                <p class="card-text">${element.description}</p>
            </div>
        </div>`;
    }

    // Renderizza la gallery di card
const renderPizzaList = (data) =>{
    console.log(data);
    let content;
    if(data.length > 0){
        // Creo il div che conterrà la gallery
        content = '<div class="row">'
        // Itero su ogni elemento dell'array
        data.forEach((element)=>{
            content += `<div class="col-3">`;
            content += renderPizza(element);
            content += `</div>`;
        })
        content += '</div>';
    } else {
        // Creo un div con scritto nessuna pizza trovata
        content = '<div class="alert alert-info">Nessuna pizza trovata</div>';
    }
    //  sostituisco il contenuto del div root con il contenuto della gallery
    root.innerHTML = content;
}

    // Chiama l'api per ottenere il json con l'array delle pizze
const  getPizzas = async () => {
    try{
        // Ottengo i dati dall'api
        const response = await axios.get(apiURL);
        // Passo i dati alla funzione che renderizza la gallery
        renderPizzaList(response.data);
    } catch (error) {
        console.log(error);
    }
};




// MAIN
getPizzas();