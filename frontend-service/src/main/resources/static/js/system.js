$(document).ready(function() {
    $('#stop-application').click(function() {
        $.post('/configuration/system/stop-application');
    });
});