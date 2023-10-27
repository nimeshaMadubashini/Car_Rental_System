let baseUrl="http://localhost:8081/Back_End_war/"
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


$("#btnSaveCustomer").click(function () {
    let formDate=new FormData($("#customerForm")[0]);
    $.ajax({
        url: baseUrl+"reg_User",
        method:"post",
        data:formDate,
        contentType:false,
        processData:false,
        success:function (res) {
            alert(res.message);

        },
        error:function (error) {
            alert(JSON.parse(error.responseText).message);

        }

    });
});