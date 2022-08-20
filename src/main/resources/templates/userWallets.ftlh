<#import "parts/common.ftlh" as c>
<@c.page>
    <head>
        <h5>My wallets list!</h5>
    </head>

    <div>
        <form action="/wallets/userWallets" method="post">
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
<#--            <input type="hidden" value="${wallet.user}" name="user">-->
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

<br><br>

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
