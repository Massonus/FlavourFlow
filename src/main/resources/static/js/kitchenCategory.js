function createKitchenCategory(event) {
    event.preventDefault();

    let form = document.getElementById("addCategory");
    let csrf = document.getElementById("csrf").value;

    let title = form.elements.title.value.toUpperCase();

    const body = JSON.stringify({
        title: title
    });

    const url = "/category/add-new-category";

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
            console.log(data.title)
            window.location.href = "/admin/panel";
        })
        .catch(error => {
            console.log(error);
        })
}

function deleteKitchenCategory(event, categoryId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;


    const url = `/category/delete-category/${categoryId}`;

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

function editKitchenCategory(event, categoryId) {
    event.preventDefault();

    let form = document.getElementById("editCategory");
    let csrf = document.getElementById("csrf").value;

    let title = form.elements.title.value.toUpperCase();

    const body = JSON.stringify({
        title: title
    });

    const url = `/category/edit-category/${categoryId}`;

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

