function createMessage(event, itemId, csrf) {
    event.preventDefault();

    let text = document.getElementById("comment").value;

    const body = JSON.stringify({
        text: text,
        itemId: itemId,
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
        .then(res => {
            if (res.ok) {
                window.location.href = `/company/info/${itemId}`;
            }
        })
        .catch(error => {
            console.log(error);
        })
}

function editMessage(event, messageId, itemId, csrf) {
    event.preventDefault();

    let text = document.getElementById("comment").value;

    const body = JSON.stringify({
        messageId: messageId,
        text: text
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
        .then(res => {
            if (res.ok) {
                window.location.href = `/company/info/${itemId}`;
            }
        })
        .catch(error => {
            console.log(error);
        })
}

function deleteMessage(messageId, itemId, csrf) {

    if (!confirm("Do you really want do delete this message?")) {
        return;
    }

    const url = `/message/delete`;

    const body = JSON.stringify({
        messageId: messageId,
        itemId: itemId
    });

    fetch(url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            'X-CSRF-TOKEN': csrf
        },
        body: body
    })
        .then(res => {
            if (res.ok) {
                window.location.href = `/company/info/${itemId}`;
            }
        })
        .catch(error =>
            console.error(error));
}

function likeMessage(messageId, itemId, csrf) {

    const url = `/message/like`;

    const body = JSON.stringify({
        messageId: messageId,
        itemId: itemId
    });

    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            'X-CSRF-TOKEN': csrf
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {
            document.querySelector(`#likes-${messageId} span`).innerHTML = `${data.likes}`;

            if (data.isLiked) {
                document.querySelector(`#likes-${messageId} i`).className = "bi bi-heart-fill";
            } else {
                document.querySelector(`#likes-${messageId} i`).className = "bi bi-heart";
            }

        })
        .catch(error =>
            console.error(error));
}