<#import "parts/common.ftlh" as c>
<@c.page>

    <br>
    SELECT DATE

    <form action="/wallets/profile/${wallet.id}/financeSelectDate" method="post">
        <div class="container">
            <br>
        </div>
        <div class="form-group row">

            <label class="col-sm-1 col-form-label">Start:</label>
            <div class="col-sm-3">
                <input type="date" name="startDate" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label">End:</label>
            <div class="col-sm-3">
                <input type="date" name="endDate" class="form-control"/>
            </div>
        </div>
        <#--TEST   START     -->
        <td class="tdLabel">
            <label for="result" class="label">
                Choose file format :
            </label>
        </td>
        <td>
            <select name="fileFormat" id="result">
                <option value="-1">Choose file format</option>
                <option value="TXT">TXT</option>
                <option value="XML">XML</option>
                <option value="HTML">HTML</option>
            </select>
        </td>
        <br>
        <#--TEST END-->
        <#--    SAVE -->
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <a href="/wallets/profile/${wallet.id}/financeDetails" class="btn btn-light"> Back</a>
        <button class="btn btn-primary" type="submit">Search</button>
        </div>
    </form>

    <div class="container mt-3">

    FINANCE DETAILS :
    Wallet ID : ${wallet.id};
    Name : ${wallet.walletName};
    <br>
    <#if startDate?? && endDate??>
        Date ${startDate!} - ${endDate!}
        <br>
        Income : ${income!} ; Spending : ${spending!}
        <br>
        File : ${fileName}
        <input type="hidden" name="fileName" value="${fileName!}"/>
        <a href="/financeReports/${fileName}" class="badge badge-info">Download report</a>
    </#if>
    <br> <br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Finance Date</th>
            <th scope="col">Amount money</th>
            <th scope="col">Operation</th>
            <th scope="col">Balance</th>
        </tr>
        </thead>
        <tbody>
        <#if financeList??>
        <#list financeList?sortBy("date") as finance>
            <tr>
                <td>${finance.id}</td>
                <#--                <td>${finance.stringDate}</td>-->
                <td>${finance.date}</td>
                <td>${finance.amountMoney}</td>
                <td>${finance.operation}</td>
                <td>${finance.balance}</td>
            </tr>
        <#else >
            No finance operations
        </#list>
        </#if>
        </tbody>
    </table>

        <div/>

</@c.page>