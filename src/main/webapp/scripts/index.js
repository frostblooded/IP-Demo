$(function () {
    $.ajax({
        url: 'api/users/current',
        method: 'GET',
        success: function (res) {
            if(res) {
                $('#welcome').show();
                $('#user-email').text(res.email);
            } else {
                $('#no-user').show();
            }
        }
    });
})