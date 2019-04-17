$(document).ready(function () {
    $("#add-account-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        ajax_submit();
    });

});

function ajax_submit() {
    var accountForm = {}
    accountForm["customerId"] = $("#customerId").val();
    accountForm["initialCredit"] = $("#initialCredit").val();
    $("#btn-add-card").prop("disabled", true);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/accounts/open",
        data: JSON.stringify(accountForm),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = "<h4>Customer Account Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#add-account-feedback').html(json);
            console.log("SUCCESS : ", data);
            $("#btn-add-card").prop("disabled", false);
        },
        error: function (e) {
            var json = "<h4>Customer Account Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#add-account-feedback').html(json);
            console.log("ERROR : ", e);
            $("#btn-add-card").prop("disabled", false);

        }
    });
}