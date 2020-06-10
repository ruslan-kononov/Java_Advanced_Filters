jQuery(document).ready(function(){
    delay();
});

function delay() {
    var secs = 1000;
    setTimeout('jQuery("body").css("visibility","visible");', secs);
}

var listOfProductsOrdered = null;

$.get("bucket",function (data) {
    if (data!==''){
        listOfProductsOrdered = data;
    }
}).done(function () {
    var content = '';
    var total = 0.0;

    jQuery.each(JSON.parse(listOfProductsOrdered),function (i,value) {
        var total_per_unit = value.quantity*value.price;
        total += total_per_unit;

        content+=
            '<tr>'+
            '<th scope="row" class="align-middle">'+(i+1)+'</th>'+
            '<td class="align-middle">'+value.name+'</td>'+
            '<td class="align-middle">'+value.price+'</td>'+
            '<td class="align-middle">'+value.quantity+'</td>'+
            '<td class="align-middle">'+value.purchaseDate+'</td>'+
            '<td class="align-middle">'+total_per_unit+'</td>'+
            '<td><button type="button" class="close" data-bucket-id="'+value.bucketId+'" aria-label="Close">\n' +
            '  <span aria-hidden="true">&times;</span>\n' +
            '</button></td>'+
            '</tr>'
    });
    content+=
        '<tr>'+
        '<th scope="row"></th>'+
        '<td></td>'+
        '<td></td>'+
        '<td></td>'+
        '<td></td>'+
        '<td>'+total.toFixed(2)+'</td>'+
        '<td></td>'+
        '</tr>'
    $('.table-body').html(content);
}).done(function () {
    $(".close").click(function () {
        var bucketId = $(this).data("bucket-id");
        var customURL='';
        var URLcontent = window.location.href.split('/');
        for (var i = 0;i<URLcontent.length-1;i++){
            customURL+=URLcontent[i]+'/';
        }
        customURL+='bucket?bucketId='+bucketId;

        $.ajax({
            url:customURL,
            type:'DELETE',
            success: [function (data) {
                if (data='success'){
                    location.reload();
                }
            }]
        });
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
