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
        stateSave: false,
        "pageLength": 20,
        "pagingType": "numbers"


    });

});