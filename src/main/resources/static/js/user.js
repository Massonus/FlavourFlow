function createUser(event) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let role = document.getElementById("role").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        document.getElementById("passwordError").textContent = "passwords are different";
        return;
    }

    if (role === undefined) {
        role = "USER";
    }

    const body = JSON.stringify({
        username: username,
        email: email,
        role: role,
        password: password
    });

    const url = "/user/add";

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
                window.location.href = `/admin/panel`;
            }
        })
        .catch(error => {
            console.log(error);
        })
}