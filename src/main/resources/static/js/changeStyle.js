var theme = document.getElementById("theme");
var nav = document.getElementById('navbar');
var sort = document.getElementById("sort");
var dropdownMenu = document.getElementById("dropdown-menu");
var kitchenCategory = document.getElementById("kitchenCategory");
var companyCountry = document.getElementById("companyCountry");

function changeStyle() {

    let checked = document.getElementById("checker").checked;

    if (checked) {

        theme.href = "/css/dark-theme.css";
        nav.className = "navbar fixed-top navbar-expand-lg navbar-dark bg-dark";

        if (!(sort === null)) {
            sort.className = "form-select bg-dark";
        }

        if (!(kitchenCategory === null)) {
            kitchenCategory.className = "form-select bg-dark";
        }

        if (!(companyCountry === null)) {
            companyCountry.className = "form-select bg-dark";
        }

        if (!(dropdownMenu === null)) {
            dropdownMenu.className = "dropdown-menu bg-dark";
        }

        localStorage.setItem('sort', 'form-select bg-dark');
        localStorage.setItem('form-select', 'form-select bg-dark');
        localStorage.setItem('dropdown', 'dropdown-menu bg-dark');
        localStorage.setItem('theme', '/css/dark-theme.css');
        localStorage.setItem('class', 'navbar fixed-top navbar-expand-lg navbar-dark bg-dark');
        localStorage.setItem('checker', 'true');


    } else {

        theme.href = "/css/light-theme.css";
        nav.className = "navbar fixed-top navbar-expand-lg navbar-light bg-light";


        if (!(sort === null)) {
            sort.className = "form-select";
        }

        if (!(kitchenCategory === null)) {
            kitchenCategory.className = "form-select";
        }

        if (!(companyCountry === null)) {
            companyCountry.className = "form-select";
        }

        if (!(dropdownMenu === null)) {
            dropdownMenu.className = "dropdown-menu";
        }

        localStorage.setItem('dropdown', 'dropdown-menu');
        localStorage.setItem('form-select', 'form-select');
        localStorage.setItem('sort', 'dropdown mt-3 bg-light py-3 px-4 categories rounded');
        localStorage.setItem('theme', '/css/light-theme.css');
        localStorage.setItem('class', 'navbar fixed-top navbar-expand-lg navbar-light bg-light');
        localStorage.setItem('checker', 'false');
    }
}

function setTheme() {
    theme.href = localStorage.getItem('theme') || '/css/light-theme.css';
    nav.className = localStorage.getItem('class') || 'navbar fixed-top navbar-expand-lg navbar-light bg-light';

    if (!(sort === null)) {
        sort.className = localStorage.getItem('sort') || 'form-select';
        dropdownMenu.className = localStorage.getItem('dropdown') || 'dropdown-menu';
        companyCountry.className = localStorage.getItem('form-select') || 'form-select';
        kitchenCategory.className = localStorage.getItem('form-select') || 'form-select';
    }

    let checker = document.getElementById("checker");
    let getItem = localStorage.getItem('checker') || false;
    let isChecked = JSON.parse(getItem);

    if (isChecked) {
        checker.setAttribute("checked", "checked");
    } else {
        checker.removeAttribute("checked");
    }
}