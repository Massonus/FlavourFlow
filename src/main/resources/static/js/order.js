function createOrder(event) {
    event.preventDefault();

    let form = document.getElementById("order-checkout");
    let csrf = document.getElementById("_csrf").value;

    let name = document.getElementById("orderName").value;
    let phone = document.getElementById("orderPhone").value;
    let date = document.getElementById("date").valueAsDate;

    const body = JSON.stringify({
        name: name,
        phone: phone,
        date: date
    });

    const url = "/order/create";

    fetch(url, {
        method: "POST",
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,
    })
        .then(res => res.json())
        .then((data) => {
            console.log(data.date);
            /*window.location.href = "/admin/panel";*/
        })
        .catch(error => {
            console.log(error);
        })

}

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