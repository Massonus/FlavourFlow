var theme = document.getElementById("theme");
var nav = document.getElementById('navbar');
var cards = document.getElementsByClassName("card");
var tableHeads = document.getElementsByClassName("thead");
var formControls = document.getElementsByClassName("form-control");
var formSelects = document.getElementsByClassName("form-select");
var userTable = document.getElementById("user-table");
var categoryTable = document.getElementById("category-table");
var companyTable = document.getElementById("company-table");
var countryTable = document.getElementById("country-table");
var productTable = document.getElementById("product-table");
var dropMenu = document.getElementsByClassName("dropdown-menu");
var footer = document.getElementById("footer");
var footerText = document.getElementById("footer-text");

function changeStyle() {

    let checked = document.getElementById("checker").checked;

    if (checked) {

        theme.href = "/css/dark-theme.css";
        nav.className = "navbar fixed-top navbar-expand-lg navbar-dark bg-dark";

        if (!(cards === null)) {
            for (const element of cards) {
                element.classList.remove("bg-light");
                element.classList.add("bg-dark");
            }
        }

        if (!(tableHeads === null)) {
            for (const element of tableHeads) {
                element.classList.remove("bg-light");
                element.classList.add("bg-dark");
            }
        }

        if (!(formControls === null)) {
            for (const element of formControls) {
                element.classList.remove("bg-light");
                element.classList.add("bg-dark");
            }
        }

        if (!(tableHeads === null)) {
            for (const element of tableHeads) {
                element.classList.remove("bg-light");
                element.classList.add("bg-dark");
            }
        }

        if (!(dropMenu === null)) {
            for (const element of dropMenu) {
                element.classList.remove("bg-light");
                element.classList.add("bg-dark");
            }
        }

        if (!(dropMenu === null)) {
            for (const element of dropMenu) {
                element.classList.remove("bg-light");
                element.classList.add("bg-dark");
            }
        }

        if (!(formSelects === null)) {
            for (const element of formSelects) {
                element.classList.add("bg-dark");
            }
        }

        if (!(userTable === null)) {
            userTable.className = "table bg-dark";
            categoryTable.className = "table bg-dark";
            countryTable.className = "table bg-dark";
            companyTable.className = "table bg-dark";
        }

        if (!(productTable === null)) {
            productTable.className = "table bg-dark";
        }

        footer.className = "py-1 bg-black bg-gradient";
        footerText.className = "mb-3 mb-md-0 text-white";

        localStorage.setItem("footer-text", "mb-3 mb-md-0 text-white");
        localStorage.setItem("footer", "py-1 bg-black bg-gradient");
        localStorage.setItem('table', 'table bg-dark');
        localStorage.setItem('old-card', 'bg-light');
        localStorage.setItem('new-card', 'bg-dark');
        localStorage.setItem('form-select-add', 'bg-dark');
        localStorage.setItem('form-select-remove', 'select');
        localStorage.setItem('theme', '/css/dark-theme.css');
        localStorage.setItem('class', 'navbar fixed-top navbar-expand-lg navbar-dark bg-dark');
        localStorage.setItem('checker', 'true');


    } else {

        theme.href = "/css/light-theme.css";
        nav.className = "navbar fixed-top navbar-expand-lg navbar-light bg-light";

        if (!(cards === null)) {
            for (const element of cards) {
                element.classList.remove("bg-dark");
                element.classList.add("bg-light");
            }
        }

        if (!(tableHeads === null)) {
            for (const element of tableHeads) {
                element.classList.remove("bg-dark");
                element.classList.add("bg-light");
            }
        }

        if (!(formControls === null)) {
            for (const element of formControls) {
                element.classList.remove("bg-dark");
                element.classList.add("bg-light");
            }
        }

        if (!(dropMenu === null)) {
            for (const element of dropMenu) {
                element.classList.remove("bg-dark");
                element.classList.add("bg-light");
            }
        }

        if (!(formSelects === null)) {
            for (const element of formSelects) {
                element.classList.remove("bg-dark");
            }
        }

        if (!(userTable === null)) {
            userTable.className = "table table-striped table-hover";
            categoryTable.className = "table table-striped table-hover";
            countryTable.className = "table table-striped table-hover";
            companyTable.className = "table table-striped table-hover";
        }

        if (!(productTable === null)) {
            productTable.className = "table table-striped table-hover";
        }

        footer.className = "py-1 bg-secondary bg-gradient";
        footerText.className = "mb-3 mb-md-0 text-black";

        localStorage.setItem("footer-text", "mb-3 mb-md-0 text-black");
        localStorage.setItem("footer", "py-1 bg-secondary bg-gradient");
        localStorage.setItem("old-footer-text", "text-white");
        localStorage.setItem("new-footer-text", "text-black");
        localStorage.setItem('table', 'table table-striped table-hover');
        localStorage.setItem('old-card', 'bg-dark');
        localStorage.setItem('new-card', 'bg-light');
        localStorage.setItem('form-select-add', 'select');
        localStorage.setItem('form-select-remove', 'bg-dark');
        localStorage.setItem('theme', '/css/light-theme.css');
        localStorage.setItem('class', 'navbar fixed-top navbar-expand-lg navbar-light bg-light');
        localStorage.setItem('checker', 'false');
    }
}

function setTheme() {
    nav.className = localStorage.getItem('class') || 'navbar fixed-top navbar-expand-lg navbar-light bg-light';

    let checker = document.getElementById("checker");
    let getItem = localStorage.getItem('checker') || false;
    let isChecked = JSON.parse(getItem);

    if (isChecked) {
        checker.setAttribute("checked", "checked");
    } else {
        checker.removeAttribute("checked");
    }

    footer.className = localStorage.getItem("footer") || 'py-1 bg-secondary bg-gradient';
    footerText.className = localStorage.getItem("footer-text") || 'mb-3 mb-md-0 text-black';

    if (!(cards === null)) {
        for (const element of cards) {
            element.classList.remove(localStorage.getItem("old-card"));
            element.classList.add(localStorage.getItem("new-card"));
        }
    }

    if (!(tableHeads === null)) {
        for (const element of tableHeads) {
            element.classList.remove(localStorage.getItem("old-card"));
            element.classList.add(localStorage.getItem("new-card"));
        }
    }

    if (!(formControls === null)) {
        for (const element of formControls) {
            element.classList.remove(localStorage.getItem("old-card"));
            element.classList.add(localStorage.getItem("new-card"));
        }
    }

    if (!(dropMenu === null)) {
        for (const element of dropMenu) {
            element.classList.remove(localStorage.getItem("old-card"));
            element.classList.add(localStorage.getItem("new-card"));
        }
    }

    if (!(formSelects === null)) {
        for (const element of formSelects) {
            element.classList.add(localStorage.getItem("form-select-add"));
            element.classList.remove(localStorage.getItem("form-select-remove"));
        }
    }

    if (!(userTable === null)) {
        userTable.className = localStorage.getItem('table') || 'table table-striped table-hover';
        categoryTable.className = localStorage.getItem('table') || 'table table-striped table-hover';
        countryTable.className = localStorage.getItem('table') || 'table table-striped table-hover';
        companyTable.className = localStorage.getItem('table') || 'table table-striped table-hover';
    }

    if (!(productTable === null)) {
        productTable.className = localStorage.getItem('table') || 'table table-striped table-hover';
    }
}

$(document).ready(function () {
    $('#preloader').fadeOut();
})

function addEventListeners() {

    window.addEventListener('keydown', (e) => {
        if (e.key === "Escape") {
            let modals = document.getElementsByClassName("modal open");
            for (const element of modals) {
                element.classList.remove("open");
            }
        }
    });

    let modalBoxes = document.getElementsByClassName("modal__box");
    for (const element of modalBoxes) {
        element.addEventListener('click', event => {
            event._isClickWithInModal = true;
        });
    }

    let modals = document.getElementsByClassName("modal open");
    for (const element of modals) {
        element.addEventListener('click', event => {
            if (event._isClickWithInModal) return;
            event.currentTarget.classList.remove('open');
        });
    }
}

function closeModal() {
    let modalOpen = document.getElementsByClassName("modal open");
    for (const element of modalOpen) {
        element.classList.remove("open");
    }
}
