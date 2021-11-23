
$(document).ready(function() {

    $('.table #deleteButton').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });

    $('.table #editButton').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function (user,status) {
            $('#idEdit').val(user.id);
            $('#nameEdit').val(user.name);
        });
        $('#editModal').modal();

    });

});