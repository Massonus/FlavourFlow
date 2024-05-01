function createUser(event, redactor) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let role = document.getElementById("role").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;
    let bonuses = document.getElementById("bonuses").value;

    if (!(validateUsername(username))) {
        return;
    }

    if (!(validatePassword(password, confirmPassword))) {
        return;
    }

    if (!(validateEmail(email))) {
        return;
    }

    const body = JSON.stringify({
        username: username,
        email: email,
        role: role,
        password: password,
        redactor: redactor,
        bonuses: bonuses
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
        .then(res => res.json())
        .then((data) => {

            if (data.isSameUsername) {
                document.getElementById("usernameError").textContent = "User with the same username is already exist";
                document.getElementById("usernameAlert").classList.remove('d-none');

            } else if (data.isSameEmail) {
                document.getElementById("emailError").textContent = "User with the same email is already exist";
                document.getElementById("emailAlert").classList.remove('d-none');

            } else {
                window.location.href = "/admin/panel";
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
    let bonuses = parseFloat(document.getElementById("bonuses").value.replace(/,/, '.'));

    if (!(validateUsername(username))) {
        return;
    }

    if (!(validatePassword(password, confirmPassword))) {
        return;
    }

    if (!(validateEmail(email))) {
        return;
    }

    const body = JSON.stringify({
        userId: userId,
        username: username,
        email: email,
        role: role,
        password: password,
        redactor: redactor,
        bonuses: bonuses
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

    }).then(res => res.json())
        .then((data) => {

            if (data.isSameUsername) {
                document.getElementById("usernameError").textContent = "User with the same username is already exist";
                document.getElementById("usernameAlert").classList.remove('d-none');

            } else if (data.isSameEmail) {
                document.getElementById("emailError").textContent = "User with the same email is already exist";
                document.getElementById("emailAlert").classList.remove('d-none');

            } else {
                window.location.href = "/admin/panel";
            }
        })
        .catch(error => {
            console.log(error);
        })
}

function deleteUser(userId, csrf) {

    if (!confirm("Do you really want to delete this user?")) {
        return;
    }

    const url = `/user/delete?id=${userId}`;

    fetch(url, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(res => res.json())
        .then((data) => {
            document.getElementById(`user-table-${data}`).remove();
        })
        .catch(error =>
            console.error(error));
}

function changeProfile(event, csrf) {
    event.preventDefault();

    let username = document.getElementById("staticUsername").value;
    let email = document.getElementById("staticEmail").value;

    if (!(validateUsername(username))) {
        return false;
    }

    if (!(validateEmail(email))) {
        return false;
    }

    const body = JSON.stringify({
        username: username,
        email: email,
    });

    const url = "/user/change-profile";

    fetch(url, {
        method: 'PUT',
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {

            if (data.isSuccess) {
                alert("Your profile successfully updated. Please re-login");
                window.location.href = "/logout";
                window.location.href = "/login";
            }

            if (data.isSameUsername) {
                document.getElementById("usernameError").textContent = "User with the same username is already exist";
                document.getElementById("usernameAlert").classList.remove('d-none');

            } else if (data.isSameEmail) {
                document.getElementById("emailError").textContent = "User with the same email is already exist";
                document.getElementById("emailAlert").classList.remove('d-none');

            }

        })
        .catch(error => {
            console.log(error);
        })
}

function changePassword(event, csrf) {
    event.preventDefault();

    let oldPassword = document.getElementById("oldPassword").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (!(validatePassword(password, confirmPassword))) {
        return false;
    }

    const body = JSON.stringify({
        oldPassword: oldPassword,
        password: password
    });

    const url = "/user/change-profile";

    fetch(url, {
        method: 'PUT',
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {

            if (data.isSuccess) {
                alert("Your profile successfully updated. Please re-login");
                window.location.href = "/logout";
                window.location.href = "/login";
            }

             if (data.isIncorrectOldPassword) {
                document.getElementById("oldPasswordError").textContent = "Old password is incorrect";
                document.getElementById("oldPasswordAlert").classList.remove('d-none');
            }

        })
        .catch(error => {
            console.log(error);
        })
}