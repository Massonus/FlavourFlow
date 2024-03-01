var theme = document.getElementById("theme");
var nav = document.getElementById('navbar');
var sort = document.getElementById("sort");
var dropdownMenu = document.getElementById("dropdown-menu");

function changeStyle() {

    let checked = document.getElementById("checker").checked;

    if (checked) {

        theme.href = "/css/dark-theme.css";
        sort.className = "dropdown mt-3 bg-dark py-3 px-4 categories rounded";
        nav.className = "navbar fixed-top navbar-expand-lg navbar-dark bg-dark";
        dropdownMenu.className = "dropdown-menu bg-dark";
        localStorage.setItem('dropdown', 'dropdown-menu bg-dark');
        localStorage.setItem('sort', 'dropdown mt-3 bg-dark py-3 px-4 categories rounded');
        localStorage.setItem('theme', '/css/dark-theme.css');
        localStorage.setItem('class', 'navbar fixed-top navbar-expand-lg navbar-dark bg-dark');
        localStorage.setItem('checker', 'true');
    } else {

        theme.href = "/css/light-theme.css";
        sort.className = "dropdown mt-3 bg-light py-3 px-4 categories rounded";
        nav.className = "navbar fixed-top navbar-expand-lg navbar-light bg-light";
        dropdownMenu.className = "dropdown-menu";
        localStorage.setItem('dropdown', 'dropdown-menu');
        localStorage.setItem('sort', 'dropdown mt-3 bg-light py-3 px-4 categories rounded');
        localStorage.setItem('theme', '/css/light-theme.css');
        localStorage.setItem('class', 'navbar fixed-top navbar-expand-lg navbar-light bg-light');
        localStorage.setItem('checker', 'false');
    }
}

function setTheme() {
    theme.href = localStorage.getItem('theme') || '/css/light-theme.css';
    nav.className = localStorage.getItem('class') || 'navbar fixed-top navbar-expand-lg navbar-light bg-light';
    sort.className = localStorage.getItem('sort') || 'dropdown mt-3 bg-light py-3 px-4 categories rounded';
    dropdownMenu.className = localStorage.getItem('dropdown') || 'dropdown-menu';

    let checker = document.getElementById("checker");
    let getItem = localStorage.getItem('checker') || false;
    let isChecked = JSON.parse(getItem);

    if (isChecked) {
        checker.setAttribute("checked", "checked");
    } else {
        checker.removeAttribute("checked");
    }
}