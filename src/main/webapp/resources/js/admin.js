$(document).ready(function() {
    this.updateUsersList = function() {	
        $.ajax({
            method: 'GET',
            url: '/messenger/users/getAllUsersSelect2WithRoles',
            dataType: 'json'
        }).always(function(data) {
            $('#all-users').select2({
                placeholder: 'Select user',
                data: data
            });
        });
    }
    var that = this;
    (function() {
        that.updateUsersList();
        $('#add-new-user').dialog({autoOpen: false});
        $('#info-incomplete').dialog({autoOpen: false});
        $('#user-added').dialog({autoOpen: false});
    })();

    $('#delete-user').click(function() {
        $('#choose-contact').dialog({autoOpen: false});
        if ($('#all-users').val() === "") {
            $('#choose-contact').dialog('open');
        } else {
            var obj = {};
            obj.userToDelete = $('#all-users').val();
            $.ajax({
                method: 'POST',
                url: '/messenger/users/deleteUser',
                dataType: 'json',
                data: obj
            }).always(function() {
                that.updateUsersList();
            });
        }
    });
    $('#make-admin').click(function() {
        $('#choose-contact').dialog({autoOpen: false});
        if ($('#all-users').val() === "") {
            $('#choose-contact').dialog('open');
        } else {
            var obj = {};
            obj.userToMakeAdmin = $('#all-users').val();
            $.ajax({
                method: 'POST',
                url: '/messenger/userRoles/makeUserAdmin',
                dataType: 'json',
                data: obj
            }).always(function() {
                that.updateUsersList();
            });
        }
    });
    $('#revert-admin').click(function() {
        $('#choose-contact').dialog({autoOpen: false});
        if ($('#all-users').val() === "") {
            $('#choose-contact').dialog('open');
        } else {
            var obj = {};
            obj.userToRevertAdmin = $('#all-users').val();
            $.ajax({
                method: 'POST',
                url: '/messenger/userRoles/removeAdminRole',
                dataType: 'json',
                data: obj
            }).always(function() {
                that.updateUsersList();
            });
        }
    });
    $('#add-new-user-button').click(function() {
        $('#add-new-user').dialog('open');
        $('#add-user-login').val('');
        $('#add-user-email').val('');
        $('#add-user-password').val('');
        $('#add-user-name').val('');
    });
    $('#add-user-actual').click(function() {
        if ($('#add-user-login').val() === "" || $('#add-user-email').val() === "" ||
                $('#add-user-password').val() === "") {
            $('#info-incomplete').dialog('open');
        } else {
            var obj = {};
            obj.username = $('#add-user-login').val();
            obj.email = $('#add-user-email').val();
            obj.password = $('#add-user-password').val();
            obj.name = $('#add-user-name').val();
            $.ajax({
                method: 'POST',
                url: '/messenger/registration/register',
                dataType: 'json',
                data: obj
            }).always(function() {
                $('#add-new-user').dialog('close');
                $('#user-added').dialog('open');
                that.updateUsersList();
            });
        }
    });
});
