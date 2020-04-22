$('button.btn.logout').click(function () {
    $.get("logout",function (data) {
            if(data!==''){
                var customURL = '';
                var urlContenct = window.location.href.split('/');
                for(var i = 0;i<urlContenct.length-1;i++){
                    customURL+=urlContenct[i]+'/';
                }
                customURL+=data;
                window.location = customURL;
            }

        });
    return true;
});