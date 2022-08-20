<#import "parts/common.ftlh" as c>
<@c.page>
    <head>
    <h5>User list page</h5>
    </head>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>Wallet name</th>
            <th>Balance</th>
            <th>Currency</th>
            <th>Wallet's owner</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list wallets as wallet>
            <tr>
                <td>${wallet.id}</td>
                <td>${wallet.walletName}</td>
                <td>${wallet.balance}</td>
                <td>${wallet.currency}</td>
                <td>${wallet.user.username}</td>

                <td><a href="/wallets/${wallet.id}" class="btn btn-dark" >edit</a></td>
                <td><a href="/wallets/${wallet.id}/delete" class="btn btn-danger">delete</a></td>

            </tr>
        <#else >
            No wallets
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </tbody>
    </table>
    <br>
    <a href="/main"
       class="btn btn-light"> Back
    </a>

</@c.page>
