$(function () {
    $.ajax({
        url: 'api/users/current',
        method: 'GET',
        success: function (res) {
            console.log(res);
            if(res) {
                $('#logged-in').show();
                $('#user-email').text(res.email);
            } else {
                $('#logged-out').show();
            }
        }
    });
})