$(document).ready(function() {
    let prefix = '/applications/viewer/';
    let container = $('#image-container');
    $.post(prefix + 'files', function(files) {
        for (let i = 0; i < files.length; i++) {
            $.post(prefix + 'image/' + files[i].shortReference, function(image) {
                container.append(image).fadeIn(1500);
            });
        }
    });
});