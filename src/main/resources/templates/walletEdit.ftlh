<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h3>ADMIN ACCESS</h3>
    <h7>Wallet name ${wallet.walletName} ; id ${wallet.id};</h7>
    <br>
    Wallet's owner ${wallet.user.username}; id  ${wallet.user.id}
    <br>
    <h3> balance : ${wallet.balance};</h3>
</head>

<form action="/wallets" method="post">
    <br><br>
    Wallet name :
    <input type="text" name="walletName" value="${wallet.walletName}"  required minlength="1">

    <br><br>
    Wallet balance :
    <input type="number" name="balance" value="${wallet.balance}" placeholder="${wallet.balance}" required minlength="1">

    <br><br>
    Wallet's owner :
    <input type="text" name="user_id" value="${wallet.user.id}" required minlength="1">

    <br><br>
    <input type="hidden" name="walletId" value="${wallet.id}">
<#--    <input type="hidden" name="WalletsOwner" value="${wallet.user}">-->
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Save</button>
    <br>

</form>


</@c.page>

