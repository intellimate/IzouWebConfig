/**
 * Updates each site to show the current setup of Izou (eg. number of addOns loaded ect)
 */
function updateTemplate() {
    var data = {

    };

    $.ajax({
        url: 'TemplateHandler.java',
        data: data,
        type: 'POST',
        success: function(response) {
            var data = JSON.parse(response);

            for (var i = 0; i < data.length; i++) {
                var addOnData = data[i];
                var type = addOnData.type.value;
                var name = addOnData.addOnName.value;
                var version = addOnData.version.value;

                var li;
                if (type === "sdk") {
                    li = "<li><a href=\"sdkTemplate.html\">" + version + "</a></li>";
                    $("#sdkList").append(li);
                } else if (type === "addOn") {
                    li = "<li><a href=\"addOnTemplate.html\">" + name + "</a></li>";
                    $("#addOnList").append(li);
                }
            }
        },
        error: function(error) {
            console.log("An error occurred");
        }
    });
}