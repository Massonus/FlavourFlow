function createUser(event, redactor) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let role = document.getElementById("role").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        document.getElementById("passwordError").textContent = "Passwords are different";
        return;
    }

    if (role === undefined && redactor === undefined) {
        role = "USER";
        redactor = 1337;
    }

    const body = JSON.stringify({
        username: username,
        email: email,
        role: role,
        password: password,
        redactor: redactor
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

function editUser(event, redactor, userId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let role = document.getElementById("role").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        document.getElementById("passwordError").textContent = "Passwords are different";
        return;
    }

    if (role === undefined && redactor === undefined) {
        role = "USER";
        redactor = 1337;
    }

    const body = JSON.stringify({
        userId: userId,
        username: username,
        email: email,
        role: role,
        password: password,
        redactor: redactor
    });

    const url = "/user/edit";

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
                window.location.href = `/admin/panel`;
            }
        })
        .catch(error => {
            console.log(error);
        })
}