function systemCheck() {

    var data = "data";

    $.ajax({
        url: 'IzouDashboardHandler.java',
        type: 'POST',
        //dataType: 'json',
        data: data,
        success: function(response) {
            var data = JSON.parse(response);

            var div = document.createElement('div');
            div.id = 'test';
            $("#wrapper").append(div);
            div.style.backgroundColor = data.color;

            setTimeout(systemCheck, 3000);
        },
        error: function(error) {
            console.log("An error occurred");
        }
    })
}