function validateForm() {
    let login = document.forms["myForm"]["login"].value;
    let password = document.forms["myForm"]["password"].value;
    let email = document.forms["myForm"]["email"].value;
    let isValid = true;

    if (login === "") {
        document.getElementById("loginError").textContent = "Please input login";
        isValid = false;
    } else if (!/^[a-zA-Z][a-zA-Z0-9_]*$/.test(login)) {
        document.getElementById("loginError").textContent = "Login must start with a letter and contain only letters, numbers, and underscores";
        isValid = false;
    } else {
        document.getElementById("loginError").textContent = "";
    }

    if (password === "") {
        document.getElementById("passwordError").textContent = "Please input password";
        isValid = false;
    } else if (!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W_]).{4,15}$/.test(password)) {
        document.getElementById("passwordError").textContent = "Password must be 4-15 characters long and contain at least one letter, one digit, and one special character";
        isValid = false;
    } else {
        document.getElementById("passwordError").textContent = "";
    }

    if (email === "") {
        document.getElementById("emailError").textContent = "Please input email";
        isValid = false;
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        document.getElementById("emailError").textContent = "Invalid email format";
        isValid = false;
    } else {
        document.getElementById("emailError").textContent = "";
    }

    return isValid;
}