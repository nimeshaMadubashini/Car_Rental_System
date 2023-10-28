let baseUrl="http://localhost:8081/Back_End_war/"
loadAllRegUser();
$("#btnSaveCustomer").attr('disabled', true);
$("#btnUpdateCustomer").attr('disabled', true);
$("#btnDeleteCustomer").attr('disabled', true);

/*registwer user*/
$("#btnSaveCustomer").click(function () {
    let formData = new FormData($("#customerForm")[0]);
    console.log(formData);
    $.ajax({
        url: baseUrl + "reg_User",
        method: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            saveUpdateAlert("User", res.message);
            loadAllRegUsers();
        },
        error: function (error) {
            unSuccessUpdateAlert("User", JSON.parse(error.responseText).message);
        }
    });
});
/*generate next userID*/
function generateNextUserId() {
$("#user_Id").val("C00-001");
$.ajax({
    url:baseUrl + "reg_User/reg_UserIdGenerate",
    method: "GET",
    contentType: "application/json",
    dataType:"json",
    success:function (resp) {
        let id=resp.value;
        console.log(id)
        let temId=parseInt(id.speed("-")[1]);
        temId=temId+1;
        if (temId<= 9){
            $("#user_Id").val("C00-00" + tempId);
        } else if (temId <= 99) {
            $("#user_Id").val("C00-0" + tempId);
        } else {
            $("#user_Id").val("C00-" + tempId);
        }
    },
    error: function (ob, statusText, error) {
    }
})
}
/*clear textfield*/
function setTextFieldValues(firstName, lastName, contact_No, address, email, nic, license_No, nic_Img, license_Img, user_Name, password) {
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    $("#contact_No").val(contact_No);
    $("#address").val(address);
    $("#email").val(email);
    $("#nic").val(nic);
    $("#license_No").val(license_No);
    $("#nic_Img").val(nic_Img);
    $("#license_Img").val(license_Img);
    $("#user_Name").val(user_Name);
    $("#password").val(password);

    $("#firstName").focus();
    checkValidity(customerValidations);
    $("#btnSaveCustomer").attr('disabled', true);
}

/*loadAllRegUser*/
function loadAllRegUsers() {
$("#customerTable").empty();
$.ajax({
    url:baseUrl + "reg_User/loadAllRegUser",
    method:"Get",
    dataType:"json",
    success:function (res) {
        for (let i of res.data) {
            let user_Id = i.user_Id;
            let firstName = i.name.firstName;
            let lastName = i.name.lastName;
            let contact_No = i.contact_No;
            let address = i.address;
            let email = i.email;
            let nic = i.nic;
            let license_No = i.license_No;
            let nic_Img = i.nic_Img;
            let license_Img = i.license_Img;
            let role_Type = i.user.role_Type;
            let user_Name = i.user.user_Name;
            let password = i.user.password;
            let row = "<tr><td>" + user_Id + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + contact_No + "</td><td>" + address + "</td><td>" + email + "</td><td>" + nic + "</td><td>" + license_No + "</td><td>" + role_Type + "</td><td>" + user_Name + "</td><td>" + password + "</td></tr>";
            $("#customerTable").append(row);
        }
        blindClickEvents();
        generateNextUserId();
        setTextFieldValues("", "", "", "", "", "", "", "", "", "", "");
        console.log(res.message);
    }, error: function (error) {
        let message = JSON.parse(error.responseText).message;
        console.log(message);
    }
})
}

/*blindClickEvents*/
function blindClickEvents() {
    $("#customerTable>tr").on('click',function () {
        let user_Id = $(this).children().eq(0).text();
        let firstName = $(this).children().eq(1).text();
        let lastName = $(this).children().eq(2).text();
        let address = $(this).children().eq(3).text();
        let contact_No = $(this).children().eq(4).text();
        let email = $(this).children().eq(5).text();
        let nic = $(this).children().eq(6).text();
        let license_No = $(this).children().eq(7).text();
        let role_Type = $(this).children().eq(8).text();
        let user_Name = $(this).children().eq(9).text();
        let password = $(this).children().eq(10).text();


        console.log(user_Id, firstName, lastName, address, contact_No, email, nic, license_No, role_Type, user_Name, password);

        $("#user_Id").val(user_Id);
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#contact_No").val(address);
        $("#address").val(contact_No);
        $("#email").val(email);
        $("#nic").val(nic);
        $("#license_No").val(license_No);
        $("#role_Type").val(role_Type);
        $("#user_Name").val(user_Name);
        $("#password").val(password);
    });
    $("#btnSaveCustomer").attr('disabled', true);

}



/*Delete User*/
$("#btnDeleteCustomer").click(function () {
    let id=$("#user_Id").val();
    $.ajax({
        url:baseUrl + "reg_User+id ="+ id +"",
        method:"delete",
        dataType:"json",
        Success:function (res) {
            saveUpdateAlert("User", resp.message);
            loadAllRegUsers();
        }, error: function (error) {
            let message = JSON.parse(error.responseText).message;
            unSuccessUpdateAlert("User", message);
        }
    });
});

/**/

/*validations*/
const regExFirstName = /^[A-z ]{3,20}$/;
const regExLastName = /^[A-z ]{3,20}$/;
const regExContactNum = /^(07(0|1|2|4|5|6|7|8)[0-9]{7})$/;
const regExCusAddress = /^[A-z0-9/ ]{4,30}$/;
const regExEmailCusAddress = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const regExNIC = /^([0-9]{12}|[0-9V]{10})$/;
const regExDrivingNIC = /^[A-Z0-9-]+$/;
const regExUserName = /^[A-z0-9/ ]{4,30}$/;
const regExPassword = /^([A-Z a-z]{5,15}[0-9]{1,10})$/;

let customerValidations=[];
customerValidations.push({
    reg: regExFirstName, field: $('#firstName'), error: 'Invalid First Name Pattern '
});
customerValidations.push({
    reg: regExLastName, field: $('#lastName'), error: 'Invalid Last Name Pattern '
});
customerValidations.push({
    reg: regExContactNum, field: $('#contact_No'), error: 'Invalid Contact Number Pattern'
});
customerValidations.push({
    reg: regExCusAddress, field: $('#address'), error: 'Invalid Address Pattern '
});
customerValidations.push({
    reg: regExEmailCusAddress, field: $('#email'), error: 'Invalid Email Address Pattern '
});
customerValidations.push({
    reg: regExNIC, field: $('#nic'), error: 'Invalid NIC Pattern'
});
customerValidations.push({
    reg: regExDrivingNIC, field: $('#license_No'), error: 'Invalid Driving License Pattern'
});
customerValidations.push({
    reg: regExUserName, field: $('#user_Name'), error: 'Invalid User Name Pattern'
});
customerValidations.push({
    reg: regExPassword, field: $('#password'), error: 'Invalid Password Pattern'
});
$("#firstName,#lastName,#contact_No,#address,#email,#nic,#license_No,#user_Name,#password").on('keydown', function (event) {
    if (event.key === "Tab") {
        event.preventDefault();
    }
});

$("#firstName,#lastName,#contact_No,#address,#email,#nic,#license_No,#user_Name,#password").on('keyup', function (event) {
    checkValidity(customerValidations);
});

$("#firstName,#lastName,#contact_No,#address,#email,#nic,#license_No,#user_Name,#password").on('blur', function (event) {
    checkValidity(customerValidations);
});
$("#firstName").on('keydown',function (event) {
    if(event.key==='Enter' && check(regExFirstName,$("#firstName" ))){
        $("#lastName").focus();
    } else {
        focusText($("#firstName"));
    }
});

$("#lastName").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExLastName, $("#lastName"))) {
        focusText($("#contact_No"));
    }
});

$("#contact_No").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExContactNum, $("#contact_No"))) {
        focusText($("#address"));
    }
});

$("#address").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExCusAddress, $("#address"))) {
        if (event.which === 13) {
            focusText($("#email"));
        }
    }
});

$("#email").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExEmailCusAddress, $("#email"))) {
        focusText($("#nic"));
    }
});

$("#nic").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExNIC, $("#nic"))) {
        focusText($("#license_No"));
    }
});

$("#license_No").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExDrivingNIC, $("#license_No"))) {
        if (event.which === 13) {
            focusText($("#user_Name"));
        }
    }
});

$("#user_Name").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExUserName, $("#user_Name"))) {
        if (event.which === 13) {
            focusText($("#password"));
        }
    }
});

$("#password").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExPassword, $("#password"))) {
        if (event.which === 13) {
            $('#btnSaveCustomer').focus();
        }
    }
});

function setButtonState(value) {
    if (value > 0) {
        $("#btnSaveCustomer").attr('disabled', true);
        $("#btnUpdateCustomer").attr('disabled', true);
        $("#btnDeleteCustomer").attr('disabled', true);
    } else {
        $("#btnSaveCustomer").attr('disabled', false);
        $("#btnUpdateCustomer").attr('disabled', false);
        $("#btnDeleteCustomer").attr('disabled', false);
    }
}