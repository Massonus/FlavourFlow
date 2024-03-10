function createCountry(event) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let title = document.getElementById("countryTitle").value.toUpperCase();

    const body = JSON.stringify({
        title: title
    });

    const url = "/country/add";

    fetch(url, {
        method: "POST",
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,
    })
        .then(res => {
            window.location.href = "/admin/panel";
        })
        .catch(error => {
            console.log(error);
        })
}

function editCountry(event, categoryId) {
    event.preventDefault();

    let form = document.getElementById("editCategory");
    let csrf = document.getElementById("csrf").value;

    let title = form.elements.title.value.toUpperCase();

    const body = JSON.stringify({
        title: title
    });

    const url = `/category/edit/${categoryId}`;

    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,
    })
        .then(res => {
            if (res.ok) {
                console.log("SUCCESS");
                window.location.href = "/admin/panel";
            } else {
                return res.text();
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function deleteCountry(event, categoryId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;


    const url = `/category/delete/${categoryId}`;

    fetch(url, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(res => {

            if (res.ok) {
                console.log("SUCCESS");
                window.location.href = "/admin/panel";

            } else {
                return res.text();
            }
        })
        .catch(error =>
            console.error(error));
}

