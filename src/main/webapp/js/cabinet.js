jQuery(document).ready(function(){
    adjustForUserRole();
    delay();
});

function delay() {
    var secs = 1000;
    setTimeout('jQuery("body").css("visibility","visible");', secs);
}

var listOfProducts = null;

$.get("products",function (data) {
    if (data!==''){
        listOfProducts = data;
    }
}).done(function () {
    var content = '';

    jQuery.each(JSON.parse(listOfProducts),function (i,value) {
        content+= '<div class="col-md-4">'+
                  '<div class="card mb-4 box-shadow">'+
                  '<img class="card-img-top" src="images/3000-min.jpeg" alt="Card image cap">'+
                  '<div class="card-body">'+
                  '<p class="card-text">'+value.name+'</p>'+
                  '<p class="card-text">Price: '+value.price+'</p>'+
                  '<p class="card-text">Decription: '+value.description+'</p>'+
                  '<div class="d-flex justify-content-between align-items-center">'+
                  '<div class="btn-group user">'+
                  '<a href="product?id='+value.id+'"><button type="button" class="btn btn-sm btn-outline-secondary">View</button></a>'+
                  '</div>'+
                  '<small class="text-muted">9 mins</small>'+
                  '</div>'+
                  '</div>'+
                  '</div>'+
                  '</div>'
        });
    $('.input-data').html(content);
});

function adjustForUserRole() {
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
};