/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function formValidation()
{
var uid = document.registration.user;
var passid = document.registration.password;
var passid2 = document.registration.password2;
var ucountry = document.registration.country;
var uzip = document.registration.zip;
var uemail = document.registration.ema;
var uemail2 = document.registration.ema2;
if(userid_validation(uid,5,12))
{
    if(passid_validation(passid,7,12,passid2)){
            if(countryselect(ucountry)){
                 if(allnumeric(uzip)){
                    if(ValidateEmail(uemail,uemail2)){
            
                            } 
                    }
                } 
            }
        }
return true;
}

function userid_validation(uid,mx,my)
{
var uid_len = uid.value.length;
if (uid_len === 0 || uid_len >= my || uid_len < mx)
{
    alert("User is mandatory / Length must be between "+mx+" and "+my);
    uid.focus();
    return false;
}
return true;
}

function passid_validation(passid,mx,my,passid2){
    var passid_len = passid.value.length;
if (passid_len === 0 ||passid_len >= my || passid_len < mx)
{
alert("Password is mandatory  / Length must be between "+mx+" and "+my);
passid.focus();
return false;
}else if(passid.value !== passid2.value){
alert("Passwords don't match" );
passid.focus();
passid2.focus();
return false;
}
return true;
}

function countryselect(ucountry){
if(ucountry.value === "Default"){
alert('Select a country');
ucountry.focus();
return false;
    }else{
    return true;
    }   
}

function allnumeric(uzip){ 
var numbers = /^[0-9]+$/;
if(uzip.value.match(numbers)){
return true;
}
else{
alert('Post code only contais digits');
uzip.focus();
return false;
    }
}

function ValidateEmail(uemail,uemail2){
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
if(uemail.value.match(mailformat))
{
    if(uemail.value === uemail2.value){
       alert('Correctly sent');
        document.register.action="Register";
        document.register.method="POST";
        document.register.submit(); 
       return true;
    }else{
        alert("Emails don't match");
        uemail.focus();
        return false;
    }

}
else
{
alert("You have entered an invalid email address!");
uemail.focus();
return false;
}
}
