function openOrderForm() {
    document.getElementById("order-modal").classList.add("open");
    window.addEventListener('keydown', (e) => {
        if (e.key === "Escape") {
            document.getElementById("order-modal").classList.remove("open")
        }
    });
    document.querySelector("#order-modal .modal__box").addEventListener('click', event => {
        event._isClickWithInModal = true;
    });
    document.getElementById("order-modal").addEventListener('click', event => {
        if (event._isClickWithInModal) return;
        event.currentTarget.classList.remove('open');
    });
}

function closeOrderForm() {
    document.getElementById("order-modal").classList.remove("open");
}