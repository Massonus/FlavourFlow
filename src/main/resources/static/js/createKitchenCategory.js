function createKitchenCategory(event) {
    event.preventDefault();

    let form = document.getElementById("addCategory");
    let csrf = document.getElementById("_csrf").value;

    let title = form.elements.title.value;

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