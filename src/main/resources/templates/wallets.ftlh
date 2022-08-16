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
    </form>
</div>
<#--<input type="hidden" value="${user.id}" name="userId">-->

<h3>Wallets List</h3>
</body>
