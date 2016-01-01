/**
 * This function continuously asks Izou if there are any updates to the dashboard, and updates the dashboard if there
 * are.
 */
function dashboardUpdateCheck() {
    $.ajax({
        url: 'DashboardHandler.java',
        type: 'POST',
        success: function(response) {
            var data = JSON.parse(response);

            var div = document.createElement('div');
            div.className = 'test';
            $("#inline-wrapper").append(div);
            div.style.backgroundColor = data.color;

            setTimeout(dashboardUpdateCheck, 500);
        },
        error: function(error) {
            console.log("An error occurred");
        }
    })
}