$(document).ready(function() {
    this.updateUsersList = function() {
        $.ajax({
            type: 'GET',
            url: '/messenger/contacts/getMyContactsSelect2',
            dataType: 'json',
            success: function(data) {
                $('#my-contacts').select2({
                    placeholder: 'Select user to delete',
                    data: data
                });
                $('#my-contacts-send').select2({
                    placeholder: 'Select addressee',
                    data: data
                });
            }
        });
    }
    var that = this;
    (function() {
        that.updateUsersList();
    })();
    $.ajax({
        type: 'GET',
        url: '/messenger/users/getAllUsersSelect2',
        dataType: 'json',
        success: function(data) {
            $('#all-contacts').select2({
                placeholder: 'Select user to add',
                data: data
            });
        }
    });

    $('#dialog-contacts').dialog({autoOpen: false});
    $('#choose-contact').dialog({autoOpen: false});
    $('#message-sent').dialog({autoOpen: false});
    $('#dialog-send-message').dialog({autoOpen: false});
    $('#open-contacts').click(function() {
        $('#dialog-contacts').dialog('open');
        $('div:has(#dialog-contacts)').width("500px");
    });
    $('#dialog-password').dialog({autoOpen: false});
    $('#change-password').click(function() {
        $('#dialog-password').dialog('open');
    });
    $('#user-change-password').click(function() {
        if (($('#conf-new-password').val()).localeCompare($('#new-password').val()) === 0) {
            $('#password-changed').dialog({autoOpen: false});
            var obj = {};
            obj.password = $('#conf-new-password').val();
            $.ajax({
                type: 'POST',
                url: '/messenger/users/changeMyPassword',
                data: obj
            }).always(function() {
                $('#dialog-password').dialog('close');
                $('#password-changed').dialog('open');
            });
        } else {
            $('#password-wrong').dialog({autoOpen: false});
            $('#password-wrong').dialog('open');
        }
    });
    $('#add-contact').click(function() {
        if ($('#all-contacts').val() !== "") {
            var obj = {};
            obj.userToAdd = $('#all-contacts').val();
            $.ajax({
                type: 'POST',
                url: '/messenger/contacts/addUserToContacts',
                data: obj
            }).always(function() {
                that.updateUsersList();
            });
        } else {
            $('#choose-contact').dialog('open');
        }
    });
    $('#delete-contact').click(function() {
        if ($('#my-contacts').val() !== "") {
            var obj = {};
            obj.userToDelete = $('#my-contacts').val();
            $.ajax({
                type: 'POST',
                url: '/messenger/contacts/deleteContact',
                data: obj
            }).always(function() {
                that.updateUsersList();
            });
        } else {
            $('#choose-contact').dialog('open');
        }
    });
    $('#send-message').click(function() {
        $('#dialog-send-message').dialog('open');
        $('div:has(#dialog-send-message)').width("500px");
        $('div:has(#dialog-send-message)').height("250px");
    });
    $('#send-message-actual').click(function() {
        if ($('#my-contacts-send').val() === "") {
            $('#choose-contact').dialog('open');
        } else {
            var obj = {};
            obj.addressee = $('#my-contacts-send').val();
            obj.theme = $('#my-theme').val();
            obj.text = $('#my-text').val();
            $.ajax({
                type: 'POST',
                url: '/messenger/messages/sendMessage',
                data: obj,
                success: function() {
                    $('#dialog-send-message').dialog('close');
                    $('#message-sent').dialog('open');
                    that.gridBuild();
                }
            });
        }
    });
    $('#delete-this-message').click(function() {
        var obj = {};
        obj.id = that.messageId;
        $.ajax({
            type: 'POST',
            url: '/messenger/messages/deleteMessageById',
            data: obj
        }).always(function() {
            $('#dialog-message-read').dialog('close');
            that.gridBuild();
        });
    });
});
