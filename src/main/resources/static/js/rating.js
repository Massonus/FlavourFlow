function rate(event, itemId, itemType, rating) {
    event.preventDefault();
    let csrf = document.getElementById("csrf").value;


    const body = JSON.stringify({
        itemId: itemId,
        rating: rating
    });

    const url = `/company/rate`;

    fetch(url, {
        method: 'POST',
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body
    })
        .then(response => {
            if (itemType === "COMPANY" && response.ok) {
                window.location.href = `/company/info/${itemId}`;
            } else {
                window.location.href = `/product/${itemId}`;
            }
        })
        .catch(error => {
            console.log(error);
        })
}