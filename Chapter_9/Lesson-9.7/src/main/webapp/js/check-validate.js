function validate(data) {
    if (data.login == '' || data.login.value.length < 3) {
        alert('enter user\'s login');
        return false;
    }
    if (data.password == '' || data.password.value.length < 3) {
        alert('enter user\'s password');
        return false;
    }
    if (data.name == '' || data.name.value.length < 3) {
        alert('enter user\'s name');
        return false;
    }
    if (data.email == '' || data.email.value.length == 0) {
        alert('enter user\'s email');
        return false;
    }

    if(!(/^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/.test(data.email.value)) ) {
        alert("Wrong e-mail format entered");
        return false;
    }

    if (data.country.value == "Choose the country") {
        alert('enter user\'s country');
        return false;
    }

    if (data.city.value == "Choose the city") {
        alert('enter user\'s city');
        return false;
    }

}