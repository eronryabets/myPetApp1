<#import "parts/common.ftlh" as c>
<#import "login.ftlh" as l>


<@c.page>

    <br><br>
    <#if user.avatar??>
        <img class="rounded-img" src="/img/avatar/${user.avatar}" class="someClass "/>
    </#if>




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
