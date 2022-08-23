<#import "parts/common.ftlh" as c>
<@c.page>
FINANCE DETAILS :
    <br>
    All operations :
    <br>
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
        <#list finance?sortBy("date")?reverse as finance>
            <tr>
                <td>${finance.id}</td>
                <td>${finance.stringDate}</td>
                <td>${finance.amountMoney}</td>
                <td>${finance.operation}</td>
                <td>${finance.balance}</td>

            </tr>
        <#else >
            No finance operations
        </#list>
        </tbody>
    </table>

</@c.page>