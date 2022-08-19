<#import "parts/common.ftlh" as c>
<@c.page>


<head xmlns:form="http://www.w3.org/1999/html">
    <h5>Wallets! Hello ${user.name}</h5>
</head>

<body>
<h3>Wallet add</h3>
<br>
<div>
    <form method="post">
        <div> Введите имя кошелька</div>
        <input type="text" name="walletName" placeholder="wallet name"/>

        <div> Введите баланс</div>
        <input type="text" name="balance" placeholder="balance"/>

        <br>
        <td class="tdLabel">
            <label for="result" class="label">
                Выберите валюту:
            </label>
            <br>
        </td>
        <td>
            <select name="walletCurrency" id="result">
                <option value="-1">Выберите валюту</option>
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
                <option value="PLN">PLN</option>
                <option value="UAH">UAH</option>
            </select>
        </td>
        <br>
        <button type="submit">Add</button>
<#--        <input type="hidden" value="${user.id}" name="userId">-->
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</div>
<#--<input type="hidden" value="${user.id}" name="userId">-->

<h3>Wallets List</h3>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Wallet name</th>
        <th>Balance</th>
        <th>Currency</th>
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

            <td><a href="/profile/${user.id}/wallets/${wallet.id}/edit" class="btn btn-dark" >edit</a></td>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <td><a href="/profile/${user.id}/wallets/${wallet.id}/delete" class="btn btn-danger">delete</a></td>
        </tr>
    </#list>
    </tbody>
</table>

</body>

</@c.page>
