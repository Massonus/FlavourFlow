function checkOrder() {

    fetch("/basket/check", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(res => res.json())
        .then((data) => {

            console.log(data.isSuccess);
            if (data.isSuccess) {
                openOrderForm();
            } else {
                window.location.href = `/basket?size=${data.size}`;
            }
        })
        .catch(error => console.log(error));
}

function afterAlert(companyId) {
    console.log(companyId);
    window.location.href = `/basket?companyId=${companyId}`;
}

function createOrder(event, companyId, availableBonuses) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let time = document.getElementById("orderTime").value;
    let bonuses = document.getElementById("bonuses").value;
    let address = document.getElementById("address").value;
    let date = document.getElementById("orderDate").valueAsDate;

    if (date <= new Date() || date.getFullYear() > new Date().getFullYear()) {
        document.getElementById("dateAlert").classList.remove('d-none');
        document.getElementById("dateError").textContent = "Incorrect date";
        return;
    }

    if (time === "" || undefined) {
        document.getElementById("timeAlert").classList.remove('d-none');
        document.getElementById("timeError").textContent = "Input time";
        return;
    }

    if (bonuses.trim() === "") {
        bonuses = 0;
    }

    if (bonuses > availableBonuses) {
        document.getElementById("bonusesAlert").classList.remove('d-none');
        document.getElementById("bonusesError").textContent = "Incorrect bonuses";
        return;
    }

    if (address === "" || undefined) {
        document.getElementById("addressAlert").classList.remove('d-none');
        document.getElementById("addressError").textContent = "Input your address";
        return;
    }

    const body = JSON.stringify({
        date: date,
        time: time,
        bonuses: bonuses,
        isSuccess: true,
        companyId: companyId,
        address: address
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
    }).then(res => res.json())
        .then((data) => {

            if (data.isTimeError) {
                document.getElementById("timeAlert").classList.remove('d-none');
                document.getElementById("timeError").textContent = "Work time: 7:00 - 19:00";

            } else {
                window.location.href = "/basket";
            }

        })
        .catch(error => {
            console.log(error);
        })

}

function openOrderForm() {
    document.getElementById("order-form-modal").classList.add("open");
}
