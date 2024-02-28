function changeStyle() {
    let nav = document.getElementById('navbar');
    let checked = document.getElementById("checker").checked;
    let searchButton = document.getElementById("search-button");
    let searchInput = document.getElementById("search-input");


    if (checked) {
        nav.className = "navbar fixed-top navbar-expand-lg navbar-dark bg-dark ";
        searchButton.className = "search-button-dark";
        searchInput.className = "search-input-dark";

    } else {
        nav.className = "navbar fixed-top navbar-expand-lg navbar-light bg-light";
        searchButton.className = "search-button-light";
        searchInput.className = "search-input-light";



    }
}