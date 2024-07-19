function createMessage(event, itemId, csrf) {
    event.preventDefault();

    let text = document.getElementById("comment").value;

    const body = JSON.stringify({
        text: text,
        itemId: itemId,
    });

    const url = "/comment/add";

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

function editComment(event, commentId, itemId, csrf) {
    event.preventDefault();

    let text = document.getElementById("comment").value;

    const body = JSON.stringify({
        commentId: commentId,
        text: text
    });

    const url = "/comment/edit";

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

function deleteMessage(commentId, itemId, csrf) {

    if (!confirm("Do you really want do delete this comment?")) {
        return;
    }

    const url = `/comment/delete`;

    const body = JSON.stringify({
        commentId: commentId,
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

function likeComment(commentId, itemId, csrf) {

    const url = `/comment/like`;

    const body = JSON.stringify({
        commentId: commentId,
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
            document.querySelector(`#likes-${commentId} span`).innerHTML = `${data.likes}`;

            if (data.isLiked) {
                document.querySelector(`#likes-${commentId} i`).className = "bi bi-heart-fill";
            } else {
                document.querySelector(`#likes-${commentId} i`).className = "bi bi-heart";
            }

        })
        .catch(error =>
            console.error(error));
}