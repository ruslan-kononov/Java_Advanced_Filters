function loginRegisterSwitch() {
    $('form').animate({
        height: "toggle",
        opacity: "toggle"
    }, "slow");
}

function displaySuccessAlert() {
    $(".alert.alert-success").show();
}

function displayErrorAlert() {
    $(".alert.alert-danger").show();
}

function displayWrongLoginAlert() {
    $(".alert.alert-warning").show();
}

$('.message a').click(function () {
    loginRegisterSwitch();
});

$('button.register').click(function () {
    validateRegistration();
})


function validateRegistration() {
    var firstName = $(".register-form input.firstName");
    var lastName = $(".register-form input.lastName");
    var email = $(".register-form input.email");
    var password = $("#pass1");

    var arr = [firstName, lastName, email, password];

    arr.forEach(el => function () {
        el.removeClass("red");
    })
    if (firstName.val() == "" || lastName.val() == "" || email.val() == "" || password.val() == "") {
        alert("Not all fields are filled!");
        $(".register-form input[type=text],.register-form input[type=password]").each(function () {
            $(this).removeClass("red");
            $(this).attr('title', '');
            if ($(this).val() == '') {
                $(this).addClass("red");
                if ($(this).attr("name").match("first_name")) {
                    $(this).attr('placeholder', 'Enter your first name');
                } else if ($(this).attr("name").match("last_name")) {
                    $(this).attr('placeholder', 'Enter your last name');
                } else if ($(this).attr("name").match("email")) {
                    $(this).attr('placeholder', 'Enter your email');
                } else if ($(this).attr("name").match("password")) {
                    $(this).attr('placeholder', 'Enter your password');
                }
            }
        });
        return false;
    } else if ($('#pass1').val() != $('#pass2').val()) {
        $('#pass1, #pass2').addClass("red");
        alert("Passwords do not match!");
        return false;
    } else if ($('#pass1').val().length < 8) {
        $('#pass1, #pass2').addClass("red");
        alert("Password should be at least 8 character long!");
        return false;
    } else if (!email.val().match(/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/)) {
        email.addClass("red");
        email.attr('placeholder', 'Enter valid email!').val("");
        alert("Enter valid email!");
        return false;
    } else {
        var firstName = $(".register-form input.firstName");
        var lastName = $(".register-form input.lastName");
        var email = $(".register-form input.email");
        var password = $("#pass1");

        var userRegistration = {
            firstName: firstName.val(),
            lastName: lastName.val(),
            email: email.val(),
            password: password.val()
        };
        $.ajax({
            type: 'POST',
            url: "registration",
            data: userRegistration,
            success: [function (data) {
                if (data == "successful") {
                    $('.register-form').trigger('reset');
                    loginRegisterSwitch();
                    displaySuccessAlert();
                }
            }]
        });
    }
}

$('#pass1, #pass2').on('keyup', function () {
    if ($('#pass1').val() == $('#pass2').val()) {
        $('#pass1, #pass2').removeClass("red");
    } else
        $('#pass1, #pass2').addClass("red");
});

$(".log").click(function () {
    var email = $(".login-form input.email");
    var password = $(".login-form input.password");

    $(".alert.alert-danger").hide();
    $(".alert.alert-success").hide();
    $(".alert.alert-warning").hide();

    email.removeClass("red");
    password.removeClass("red");

    if (email.val() == '' || password.val() == '') {
        displayErrorAlert();
        if(email.val()==''){
            email.addClass("red");
            email.attr('placeholder', 'Enter email!');
        }
        if(password.val()==''){
            password.addClass("red");
            password.attr('placeholder', 'Enter password!');
        }
    }else{
        var userLogin = {
            email: email.val(),
            password: password.val()
        };

        $.post("login", userLogin, function (data) {
            if(data!==''){
                var customURL = '';
                var urlContenct = window.location.href.split('/');
                for(var i = 0;i<urlContenct.length-1;i++){
                    customURL+=urlContenct[i]+'/';
                }
                customURL+=data.destinationURL;
                window.location = customURL;
            }else{
                $('.login-form').trigger('reset');
                displayWrongLoginAlert();
            }
        });
    }
});

