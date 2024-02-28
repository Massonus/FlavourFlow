var theme = document.getElementById("theme");

function changeStyle() {

    if (theme.getAttribute("href") === "/css/light-theme.css") {
        // …то переключаемся на "dark-theme.css"
        theme.href = "/css/dark-theme.css";
        // Сохраняем значение в хранилище
        localStorage.setItem('theme', '/css/dark-theme.css');
        // В противном случае…
    } else {
        // …переключаемся на "light-theme.css"
        theme.href = "/css/light-theme.css";
        // Сохраняем значение в хранилище
        localStorage.setItem('theme', '/css/light-theme.css');
    }
}

function setTheme() {
    theme.href = localStorage.getItem('theme') || '/css/light-theme.css';
}