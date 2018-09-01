$(document).ready(function() {

    $('.data-table').DataTable({
        "columns": [
            null,
            null
        ],
        "lengthChange": false,
        responsive: true,
        "paging":   true,
        "ordering": false,
        "info":     true,
        /*"order": [[ 1, "asc" ]],*/
        stateSave: false,
        "pageLength": 20,
        "pagingType": "numbers"


    });

});