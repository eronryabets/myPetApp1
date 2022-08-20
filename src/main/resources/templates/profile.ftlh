<#import "parts/common.ftlh" as c>
<#import "login.ftlh" as l>

<@c.page>

    <head>
        <h5>Profile page! Hello ${user.name}</h5>
    </head>

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
