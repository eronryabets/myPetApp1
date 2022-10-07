<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h3>ADMIN ACCESS</h3>
    <h5><p style="color:red">${errorMessage!}</p></h5>
    <h7>Wallet name ${wallet.walletName} ; id ${wallet.id};</h7>
    <br>
    Wallet's owner
    <#if (wallet.user.username)??>${wallet.user.username}<#else> Didn't have owner</#if>
    ; id
    <#if (wallet.user.id)??>${wallet.user.id}<#else> none</#if>

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
    <input type="text" name="user_id" value="<#if (wallet.user.id)??>${wallet.user.id}<#else> 0</#if>" required minlength="1">

    <br><br>
    <input type="hidden" name="walletId" value="${wallet.id}">
<#--    <input type="hidden" name="WalletsOwner" value="${wallet.user}">-->
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Save</button>
    <br>

</form>


</@c.page>

