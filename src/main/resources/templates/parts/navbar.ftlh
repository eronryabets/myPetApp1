<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">WalletsAPP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/messages">Messages</a>
            </li>

            <#if isAdmin>

            <li class="nav-item">
                <a class="nav-link" href="/user">User List</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/wallets">Wallets List</a>
            </li>

            </#if>

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/notes">Notes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/wallets/userWallets">Wallets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/download">Files</a>
                </li>
            </#if>
        </ul>

<#--        <#if user?? && user.avatar??>-->
<#--            <img class="avatarPreview-img" src="/img/avatar/${user.avatar}" class="someClass "/>-->
<#--        </#if>-->

        <div class="navbar-text mr-3"><#if user??>${user.username}<#else>Please, login</#if></div>
        <#if user??><@l.logout />
        <#else>
            <a href="/login" class="btn btn-dark" >Login</a>
        </#if>

    </div>
</nav>