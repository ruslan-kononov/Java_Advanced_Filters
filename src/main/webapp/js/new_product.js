$('button.create').click(function () {
    validateRegistration();
})

function displaySuccessAlert() {
    $(".alert.alert-success").show();
}

function displayDangerAlert() {
    $(".alert.alert-danger").show();
}

function validateRegistration() {
    $(".alert.alert-success").hide();
    $(".alert.alert-danger").hide();

    var productName = $(".product-form input.productName");
    var price = $(".product-form input.price");
    var description = $(".product-form textarea.description");

    var arr = [productName, price, description];

    if (productName.val() == "" || price.val() == "" || description.val() == "") {
        alert("Some fields are empty!");
        $(".product-form input[type=text],.product-form input[type=number]").each(function () {
            $(this).removeClass("red");
            $(this).attr('title', '');
            if ($(this).val() == '') {
                $(this).addClass("red");
                if ($(this).attr("name").match("product_name")) {
                    $(this).attr('placeholder', 'Enter product name');
                } else if ($(this).attr("name").match("price")) {
                    $(this).attr('placeholder', 'Enter product price');
                }
            }
        });
        return false;
    } else if (description.val().length > 255) {
        description.addClass("red");
        alert("Description is too long!");
        return false;
    }else if (productName.val().length > 250) {
        productName.addClass("red");
        alert("Product name is too long!");
        return false;
    } else {
        var newProduct = {
            productName: productName.val(),
            price: price.val(),
            description: description.val()
        };
        $.ajax({
            type: 'POST',
            url: "product",
            data: newProduct,
            success: [function (data) {
                if (data == "successful") {
                    $('.product-form').trigger('reset');
                    displaySuccessAlert();
                }else{
                    displayDangerAlert();
                }
            }]
        });
        return true;
    }
}

$('.description').on('keyup', function () {
    if ($(".description").val().length > 255) {
        $(".description").addClass("red");
    } else
        $(".description").removeClass("red");
});

$('button.buy').click(function () {
    var productId = $('button.buy').attr("product-id");
    $.post("bucket",{'productId':productId},function (data) {
        if(data == 'Success'){
            $('#exampleModalCenter .close').click();
        }
    });
});

$(function() {
    var role = null;
    $.get('user-role', function (data) {
        if (data !== '') {
            role = data;
        }
    }).done(function () {
        if (role === "ADMINISTRATOR") {
            $('.user').hide();
        }else{
            $('.admin').hide();
        }
    });
});

