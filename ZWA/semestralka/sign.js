window.addEventListener("DOMContentLoaded", init);

function init() {
    const form = document.getElementById("form");
    const username = document.getElementById("username");
    const email = document.getElementById("email");
    const password = document.getElementById("password");
    const password2 = document.getElementById("password2");
    const submit = document.getElementById("submit");
    const name = document.getElementById("name");
    const surname = document.getElementById("surname");

    let formValid = {
        name: false,
        surname: false,
        username: false,
        email: false,
        password: false,
        password2: false
    };

    surname.addEventListener("input", () => validateForm(surname));
    name.addEventListener("input", () => validateForm(name));
    username.addEventListener("input", () => validateForm(username));
    email.addEventListener("input", () => validateForm(email));
    password.addEventListener("input", () => validateForm(password));
    password2.addEventListener("input", () => validateForm(password2));
    
    function validateForm(field) {
        const error = document.getElementById("error");
        let currentFieldValid = true;
        
        field.style.borderColor = '';
        field.style.backgroundColor = '';
        

        if (field === name) {
            if (name.value.length < 3 ) {
                setInvalidInput(name);
                currentFieldValid = false;
                formValid.name = false;
                error.textContent = "Name must be at least 3 characters long";
            }
            else if (name.value.length > 30) {
                setInvalidInput(name);
                currentFieldValid = false;
                formValid.name = false;
                error.textContent = "Name must be less than 30 characters long";
            }
            else if (!/^[a-zA-Z]+$/.test(name.value)) {
                setInvalidInput(name);
                currentFieldValid = false;
                formValid.name = false;
                error.textContent = "Name must contain only alphabetic characters";
            }
             else
             {
                formValid.name = true;
            }


        }

        if (field === surname) {
            if (surname.value.length < 3 ) {
                setInvalidInput(surname);
                currentFieldValid = false;
                formValid.surname = false;
                error.textContent = "Surname must be at least 3 characters long";
            }
            else if (surname.value.length > 30) {
                setInvalidInput(surname);
                currentFieldValid = false;
                formValid.surname = false;
                error.textContent = "Surname must be less than 30 characters long";
            }
            else if (!/^[a-zA-Z]+$/.test(surname.value)) {   
                setInvalidInput(surname);
                currentFieldValid = false;
                formValid.surname = false;
                error.textContent = "Surname must contain only alphabetic characters";
            } else {
                formValid.surname = true;
            }
        }
        if (field === username ){
            if (username.value.length < 3) {
                setInvalidInput(username);
                currentFieldValid = false;
                formValid.username = false;
                error.textContent = "Username must be at least 3 characters long";
            }
            else if (username.value.length > 30 ) {
                setInvalidInput(username);
                currentFieldValid = false;
                formValid.username = false;
                error.textContent = "Username must be less than 30 characters long";
            }
            else if (/\s/.test(username.value)) {
                setInvalidInput(username);
                currentFieldValid = false;
                formValid.username = false;
                error.textContent = "Username cannot contain spaces";
            } 
            else
            {
                formValid.username = true;
            }
        }

        if (field ===email ){
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email.value)) {
                setInvalidInput(email);
                currentFieldValid = false;
                formValid.email = false;
                error.textContent = "Invalid email address";
             } 
            else if (email.value.length > 30) {
                setInvalidInput(email);
                currentFieldValid = false;
                formValid.email = false;
                error.textContent = "Email must be less than 30 characters long";
            }
            else 
            {
            formValid.email = true;
            }
        }
        if(field === password){
            const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d\s\W]{6,}$/;
            if (password.value.length < 6) {    
                setInvalidInput(password);
                currentFieldValid = false;
                formValid.password = false;
                error.textContent = "Password must be at least 6 characters long";
            }
            else if (password.value.length > 50) {
                setInvalidInput(password);
                currentFieldValid = false;
                formValid.password = false;
                error.textContent = "Password must be less than 50 characters long";
            }
            else if (!passwordPattern.test(password.value) && field === password) {
                setInvalidInput(password);
                currentFieldValid = false;
                formValid.password = false;
                error.textContent = "Password must contain at least one uppercase letter, one lowercase letter and one digit";
            } else
            {
                formValid.password = true;
            }
        }
    
        if (field === password2) {
            if (password.value !== password2.value) {
                setInvalidInput(password2);
                currentFieldValid = false;
                formValid.password2 = false;
                error.textContent = "Passwords do not match";
            } else {
                formValid.password2 = true;
            }
        }
        
        const isFormValid = Object.values(formValid).every(value => value === true);
        submit.disabled = !isFormValid;
        console.log(submit.disabled);

        if (!currentFieldValid) {
            error.style.display = "block";
        }
        else if (currentFieldValid) {
            error.style.display = "none";
            error.textContent="";
        }

        return currentFieldValid;
    }
    
    function setInvalidInput(element) {
        element.style.borderColor = '#ff0000';
        element.style.backgroundColor = '#fff0f0';
    }
    
    form.addEventListener("submit", function(e) {
        const isValid = Object.values(formValid).every(value => value === true);


        if (!isValid) {
            e.preventDefault();
        }
    });
}