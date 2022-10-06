$(document).ready(function () {
    $('#productNamed').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/products/suggestions?", {q: request.term}, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );

    $("#btnProductSearch").click(function () {
        const inputText = $("#productNamed").val();
        if (inputText.length === 0) {
            alert("Enter product name or description");
        } else {
            let productSearch = document.getElementById('productSearch');
            if (productSearch) {
                let input = document.createElement("input");
                input.setAttribute("type", "hidden");
                input.setAttribute("name", "productSearch");
                input.setAttribute("value", inputText);
                productSearch.appendChild(input);
            }
        }
    });
});