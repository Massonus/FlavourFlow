function rate(itemId, rating) {

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
        .then(res => res.json())
        .then((data) => {

            console.log(data.rating);

        })
        .catch(error => {
            console.log(error);
        })
}