function addToken(event) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let token = document.getElementById("accessToken").value;

    const url = `/token/add?token=${token}`;

    fetch(url, {
        method: "POST",
        redirect: 'follow',
        headers: {
            "X-CSRF-TOKEN": csrf,
        },
    })
        .then(res => {

            if (res.ok) {
                window.location.href = "/admin/panel";
            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error => {
            console.log(error);
        })
}