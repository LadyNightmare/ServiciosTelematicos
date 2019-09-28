/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


   function validateForm() {
                     var user = document.login.nombre;
                    var pwd = document.login.password;
                     
        if ( (user.value === null) || (user.value.length===0) || (pwd.value === null) || (pwd.value.length===0) ) {
            alert("No se pueden dejar campos vacios");
             
            return false;
                 }else{
                 document.login.action="LoginServlet";
                 document.login.method="POST";
                 document.login.submit(); 
                   return true;
                 }
              }

function tiempo(){
        hoy=new Date();
        h=hoy.getHours();
        m=hoy.getMinutes();
        s=hoy.getSeconds();
        m=checkTime(m);
        s=checkTime(s);
        document.getElementById('reloj').innerHTML=h+":"+m+":"+s;
        t=setTimeout('tiempo()',500);
}
function checkTime(i)
{
    if (i<10) {
        i="0" + i;
    } 
    return i;
}


         