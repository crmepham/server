$(document).ready(function() {
    $('#account-transaction-create-btn').click(function() {
        var name        = document.getElementById('account-transaction-name').value;
        var value       = document.getElementById('account-transaction-value').value;
        var percent     = document.getElementById('account-transaction-percent').value;
        var type        = document.getElementById('account-transaction-type').value;
        var accountId   = document.getElementById('account-transaction-account-id').value;

        if (name.length == 0 || value == '0' || value.length == 0 || percent.length == 0 || type.length == 0) {
            $('.modal-error').show();
        } else {
            $('.modal-error').hide();

            var posting = $.post( '/applications/accounts/transaction/create', {
                name:name,
                value:value,
                percent:percent,
                type:type,
                accountId:accountId} );

            posting.done(function(result) {

                if (result.success) {

                    document.getElementById('account-transaction-name').value = '';
                    document.getElementById('account-transaction-name').focus();

                    $('.modal-error').hide();
                    $('.modal-success').show();

                } else {
                    $('.modal-success').hide();
                    $('.modal-error').text(result.failureReason);
                    $('.modal-error').show();
                }
            });
        }
    });
});