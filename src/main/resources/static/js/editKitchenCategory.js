function editKitchenCategory(event, categoryId) {
    event.preventDefault();

    let form = document.getElementById("editCategory");
    let csrf = document.getElementById("_csrf").value;

    let title = form.elements.title.value;

    const body = JSON.stringify({
        title: title
    });

    const url = `/category/edit-category/${categoryId}`;

    fetch(url, {
        method: 'PUT',
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