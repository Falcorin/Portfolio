$(function(){

    const appendAffair = function(data){
        var affairCode = '<a href="#" class="affair-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#affair-list')
            .append('<div>' + affairCode + '</div>');
    };

    //Show adding affair form
    $('#show-add-affair-form').click(function(){
        $('#affair-form').css('display', 'flex');
    });

    //Closing affair form
    $('#affair-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting affair
    $(document).on('click', '.affair-link', function(){
        var link = $(this);
        var affairId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/affair/' + affairId,
            success: function(response)
            {
                var code = '<span>ID:' + response.id + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    //Adding affair
    $('#save-affair').click(function()
    {
        var data = $('#affair-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/affair/',
            data: data,
            success: function(response)
            {
                $('#affair-form').css('display', 'none');
                var affair = {};
                affair.id = response;
                var dataArray = $('#affair-form form').serializeArray();
                for(i in dataArray) {
                    affair[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendAffair(affair);
            }
        });
        return false;
    });

});
