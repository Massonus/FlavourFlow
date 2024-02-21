function deleteKitchenCategory(event, categoryId) {
    event.preventDefault();

    let csrf = document.getElementById("_csrf").value;


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