<#import "parts/common.ftlh" as c>
<#import "login.ftlh" as l>
<link rel="stylesheet" href="/static/avatar.css">


<@c.page>

    <head xmlns:container="http://www.w3.org/1999/xhtml">

        <h5>Profile page! Hello ${user.name}</h5>
    </head>
    <br><br>

    <img class="rounded-img" src="/img/avatar/Screenshot_821.png" class="someClass "/>


<#--    ссылка на изменение данных профиля-->
    <br><br>
    <a href="/user/profile/profileData"
       class="btn btn-light"> Edit Profile
    </a>

<#--    ссылка на кошельки профилья -->
    <br><br>
    <a href="/wallets/userWallets"
       class="btn btn-light"> Wallets
    </a>



</@c.page>
