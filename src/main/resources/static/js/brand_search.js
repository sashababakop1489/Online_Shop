$(document).ready(function () {
    $('#brandNamed').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/brands/suggestions?", {q: request.term}, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );

    $("#btnBrandSearch").click(function () {
        const inputText = $("#brandNamed").val();
        if (inputText.length === 0) {
            alert("Enter product name or description");
        } else {
            let brandSearch = document.getElementById('brandSearch');
            if (brandSearch) {
                let input = document.createElement("input");
                input.setAttribute("type", "hidden");
                input.setAttribute("name", "brandSearch");
                input.setAttribute("value", inputText);
                brandSearch.appendChild(input);
            }
        }
    });
});