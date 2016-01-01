function systemCheck() {
    var data = {
        key: "value"
    };

    $.ajax({
        url: 'IzouDashboardHandler.java',
        type: 'POST',
        data: data,
        success: function(response) {
            var data = JSON.parse(response);

            var div = document.createElement('div');
            div.className = 'test';
            $("#inline-wrapper").append(div);
            div.style.backgroundColor = data.color;

            setTimeout(systemCheck, 500);
        },
        error: function(error) {
            console.log("An error occurred");
        }
    })
}