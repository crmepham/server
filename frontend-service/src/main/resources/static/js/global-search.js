$(document).ready(function() {
    $('#global-search').keyup(function(e) {
        if(e.keyCode == 13)
        {
            var searchTerm = $(this).val();
            location.href = '/search?text=' + searchTerm;
        }
    });
});