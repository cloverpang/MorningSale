
$(document).ready(function() {
    $('#MenuId').change(function() {
        $('#PageId').attr('value', -1);
        $(this).parents('form').submit();
    });

    $('#PageId').change(function() {
    $(this).parents('form').submit();
    });
});
    