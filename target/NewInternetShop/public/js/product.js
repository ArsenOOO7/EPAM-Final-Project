const buttons = [document.getElementById("name_a"),
    document.getElementById("name_d"),
    document.getElementById("price_a"),
    document.getElementById("price_d"),
    document.getElementById("novelty")
]

const form = document.getElementById("search_form");
form.addEventListener("submit", function(event){
    event.preventDefault();
    buildLink("");
})

buttons.forEach((button, index) => button.addEventListener("click", function(event){
    let sortType = "";
    switch(index){
        case 0:
            // sort((jsonA, jsonB) => jsonA.name.localeCompare(jsonB.name));
            sortType = "sortNameAsc";
            break;
        case 1:
            // sort((jsonA, jsonB) => jsonB.name.localeCompare(jsonA.name));
            sortType = "sortNameDesc";
            break;
        case 2:
            // sort((jsonA, jsonB) => jsonA.price - jsonB.price);
            sortType = "sortPriceAsc";
            break;
        case 3:
            // sort((jsonA, jsonB) => jsonB.price - jsonA.price);
            sortType = "sortPriceDesc";
            break;
        case 4:
            // sort((jsonA, jsonB) => jsonA.start_date - jsonB.start_date);
            sortType = "sortNovelty";
            break;
    }
    buildLink(sortType);
}))

function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

loadPage()

function loadPage(){

    const search_element = document.getElementById("search_product");
    const price = [document.getElementById("min_price"), document.getElementById("max_price")];
    const category = document.getElementById("category");
    const color = document.getElementById("color");
    const size = [document.getElementById("min_size"), document.getElementById("max_size")];

    search_element.value = getParameterByName("name");
    price[0].value = getParameterByName("min_price");
    price[1].value = getParameterByName("max_price");
    category.value = getParameterByName("category");
    color.value = getParameterByName("color");
    size[0] = getParameterByName("min_size");
    size[1] = getParameterByName("max_size");

}

function buildLink(sortType){

    const link = new Link();
    const search_element = document.getElementById("search_product");
    const price = [document.getElementById("min_price"), document.getElementById("max_price")];
    const category = document.getElementById("category");
    const color = document.getElementById("color");
    const size = [document.getElementById("min_size"), document.getElementById("max_size")];

    // const language = document.getElementById("lang");
    if(search_element.value !== ""){
        link.addValue("name", search_element.value);
    }
    // else{
    //     if(getParameterByName("name") !== null){
    //         link.addValue("name", getParameterByName("name"));
    //     }
    // }

    if(price[0].value !== ""){
        link.addValue("min_price", price[0].value);
    }
    // else{
    //     if(getParameterByName("min_price") !== null){
    //         link.addValue("min_price", getParameterByName("min_price"));
    //     }
    // }
    if(price[1].value !== ""){
        link.addValue("max_price", price[1].value);
    }
    // else{
    //     if(getParameterByName("max_price") !== null){
    //         link.addValue("max_price", getParameterByName("max_price"));
    //     }
    // }
    if(category.value !== ""){
        link.addValue("category", category.value);
    }
    // else{
    //     if(getParameterByName("category") !== null){
    //         link.addValue("category", getParameterByName("category"));
    //     }
    // }
    if(color.value !== ""){
        link.addValue("color", color.value);
    }
    // else{
    //     if(getParameterByName("color") !== null){
    //         link.addValue("color", getParameterByName("color"));
    //     }
    // }
    if(size[0].value !== ""){
        link.addValue("min_size", size[0].value);
    }

    // else{
    //     if(getParameterByName("min_size") !== null){
    //         link.addValue("min_size", getParameterByName("min_size"));
    //     }
    // }
    if(size[1].value !== ""){
        link.addValue("max_size", size[1].value);
    }

    // else{
    //     if(getParameterByName("max_size") !== null){
    //         link.addValue("max_size", getParameterByName("max_size"));
    //     }
    // }
    if(sortType !== ""){
        link.addValue("sort", sortType);
    }

    // else{
    //     if(getParameterByName("sort") !== null){
    //         link.addValue("sort", getParameterByName("sort"));
    //     }
    // }


    window.location.href = link.buildLink();
}


class Link{
    link = "http://localhost:8080/shop/product?";
    values = [];

    addValue(key, value){
        let query = key + "=" + value;
        this.values.push(query);
    }

    buildLink(){
        return this.link + this.values.join("&");
    }
}
