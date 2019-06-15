let page = 0;
let stopped = false;
let loaded = true;
$(document).ready(function() {

    load();

    $(window).scroll(function() {
        if (loaded && !stopped) {
            if ($(window).scrollTop() + $(window).height() == $(document).height()) {
                load();
            }
        }
    });
});

function load() {
    loaded = false;
    $('#image-container').append("<div class='loading-text'>Loading&hellip;</div>").fadeIn(1500);
    $.ajax({
        url: '/applications/viewer/image/page/' + page++,
        type: 'POST',
        success: function(images) {
            loaded = true;
            $('.loading-text').remove();
            $('#image-container').append(images).fadeIn(1500);
        },
        error(xhr, s, e) {
            if (xhr.status === 405) {
                loaded = true;
                stopped = true;
                $('.loading-text').remove();
                $('#image-container').append("<div class='loading-text'>All content loaded.</div>").fadeIn(1500);
            }
        }
    });
}