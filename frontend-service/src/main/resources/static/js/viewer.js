let page = 0;
let stopped = false;
let loaded = true;
$(document).ready(function() {

    load('Image', 'All');

    $(window).scroll(function() {
        if (loaded && !stopped) {
            if ($(window).scrollTop() + window.innerHeight == getDocHeight()) {
                let type = $('.type-item.active').text();
                let year = $('.year-item.active').text();
                load(type, year);
            }
        }
    });

    $('.type-item').click(function() {
        $('#image-container').empty();
        $('.type-item').removeClass('active');
        stopped = false;
        page = 0;
        let type = $(this).addClass('active').text();
        let year = $('.year-item.active').text();
        load(type, year);
    });

    $('.year-item').click(function() {
        $('#image-container').empty();
        $('.year-item').removeClass('active');
        stopped = false;
        page = 0;
        let type = $('.type-item.active').text();
        let year = $(this).addClass('active').text();
        load(type, year);
    });
});

function load(type, year) {
    loaded = false;
    $('#image-container').append("<div class='loading-text'>Loading&hellip;</div>").fadeIn(1500);
    $.ajax({
        url: '/applications/viewer/'+type+'/'+year+'/'+page++,
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

function getDocHeight() {
    var D = document;
    return Math.max(
        D.body.scrollHeight, D.documentElement.scrollHeight,
        D.body.offsetHeight, D.documentElement.offsetHeight,
        D.body.clientHeight, D.documentElement.clientHeight
    );
}