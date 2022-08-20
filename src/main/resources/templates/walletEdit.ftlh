<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h5>Wallet name ${wallet.walletName} ; id ${wallet.id}</h5>
    <br>
    Wallet's owner ${wallet.user.username}; id  ${wallet.user.id}
    <br>
</head>

<form action="/wallets" method="post">
    <br><br>
    Wallet name :
    <input type="text" name="walletName" value="${wallet.walletName}">

    <br><br>
    Wallet balance :
    <input type="text" name="balance" value="${wallet.balance}">

<#--    <br><br>-->
<#--    Wallet's owner :-->
<#--    <input type="text" name="user_id" value="${wallet.user.id}">-->

    <br><br>
    <input type="hidden" name="walletId" value="${wallet.id}">
<#--    <input type="hidden" name="WalletsOwner" value="${wallet.user}">-->
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Save</button>
    <br>

</form>


</@c.page>

