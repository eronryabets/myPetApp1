<#import "parts/common.ftlh" as c>
<@c.page>
    <head>
        <h5>My wallets list!</h5>
    </head>


    <a class="btn btn-info" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new wallet
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <div>
                <form action="/wallets/userWallets" method="post">
                    <div> Введите имя кошелька</div>
                    <input type="text" name="walletName" placeholder="wallet name"
                           required minlength="2"/>

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
                    <td class="tdLabel">
                        <label for="result" class="label">
                            Выберите валюту:
                        </label>
                        <br>
                    </td>
                    <td>
                        <select name="walletType" id="result2">
                            <option value="-1">Выберите тип кошелька</option>
                            <option value="DEBIT">Debit</option>
                            <option value="CREDIT">Credit</option>
                        </select>
                    </td>

                    <br><br>
                    <button type="submit" class="btn btn-success">Add</button>
                    <#--            <input type="hidden" value="${wallet.user}" name="user">-->
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>

    <br><br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th>id</th>
            <th>Wallet name</th>
            <th>Balance</th>
            <th>Currency</th>
            <th>Type</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list wallets?sortBy("type")?reverse as wallet>
            <tr>
                <td>${wallet.id}</td>
                <td>${wallet.walletName}</td>
                <td>${wallet.balance}</td>
                <td>${wallet.currency}</td>
                <td>${wallet.type}</td>

                <td><a href="/wallets/profile/${wallet.id}" class="btn btn-dark">Management</a></td>
                <td><a href="/wallets/profile/${wallet.id}/financeSelectDate" class="btn btn-info">Finance stat</a></td>
                <#--                <td><a href="/wallets/profile/${wallet.id}/delete" class="btn btn-danger">delete</a></td>-->
                <#assign type = "${wallet.type}" >
                <#if type == "DEBIT">
                    <td><a href="/wallets/profile/${wallet.id}/walletDebitStat" class="btn btn-success">Debit stat</a></td>
                </#if>
                <#if type == "CREDIT">
                    <td><a href="/wallets/profile/${wallet.id}/walletCreditStat" class="btn btn-warning">Credit stat</a></td>
                </#if>
                <td><a href="/wallets/profile/${wallet.id}/cashTransfer" class="btn btn-primary">Cash transfer</a></td>
            </tr>
        <#else >
            No wallets
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </tbody>
    </table>
    <br>
    <a href="/user/profile" class="btn btn-light"> Back</a>

</@c.page>
