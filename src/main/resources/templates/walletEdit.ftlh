<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h5>User list page! Hello ${user.name} now you edit wallet ${wallet.walletName}</h5>
</head>

<h3>Wallet ${wallet.walletName}</h3>
<form action="/profile/${user.id}/wallets/${wallet.id}" method="post">
    Wallet name :
    <input type="text" name="walletName" value="${wallet.walletName}">

    <br><br>
    Wallet balance :
    <input type="text" name="balance" value="${wallet.balance}">

    <br><br>
<#--    <input type="hidden"  name="walletId" value="${wallet.id}">-->
<#--    <input type="hidden"  name="userId" value="${user.id}">-->

    <button type="submit">Save</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <br>

</form>

<form method="get" action="/profile/${user.id}/wallets/" class="form-inline">
    <button type="submit" class="btn btn-primary ml-2">Back</button>
</form>

</@c.page>

