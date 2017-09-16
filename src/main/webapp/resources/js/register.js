function register() {
    $('#dialog-success').dialog({autoOpen: false});
    $('#dialog-error').dialog({autoOpen: false});
    var obj = {};
    obj.username = $('#username').val();
    obj.password = $('#password').val();
    obj.name = $('#name').val();
    obj.email = $('#email').val();
    obj.enabled = true;
    $.ajax({
        method: 'POST',
        url: '/messenger/registration/register',
        data: obj,
        dataType: 'text',
        success: function() {
            $('#dialog-success').dialog('open');
        },
        error: function() {
            $('#dialog-error').dialog('open');
        }
    });
}
