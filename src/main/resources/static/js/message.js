function createMessage(event, itemId, itemType, csrf) {
    event.preventDefault();

    let text = document.getElementById("comment").value;

    const body = JSON.stringify({
        text: text,
        itemId: itemId,
        itemType: itemType.toUpperCase()
    });

    const url = "/message/add";

    fetch(url, {
        method: 'POST',
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,

    })
        .then(res => res.json())
        .then((data) => {

            if (data.itemType === "COMPANY") {
                window.location.href = `/company/info/${data.itemId}`;
            } else {
                window.location.href = `/product/${data.itemId}`;
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function editMessage(event, messageId, itemType, itemId, csrf) {
    event.preventDefault();

    let text = document.getElementById("comment").value;

    const body = JSON.stringify({
        messageId: messageId,
        text: text,
        itemType: itemType
    });

    const url = "/message/edit";

    fetch(url, {
        method: 'PUT',
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,

    })
        .then(res => res.json())
        .then((data) => {

            if (data.itemType === "COMPANY") {
                window.location.href = `/company/info/${itemId}`;
            } else {
                window.location.href = `/product/${itemId}`;
            }

        })
        .catch(error => {
            console.log(error);
        })
}