<#import "parts/common.ftlh" as c>
<@c.page>
CREDIT STAT
    <br>
    Wallet ID : ${wallet.id};
    Name : ${wallet.walletName};
    Balance : ${wallet.balance};

    <form action="/wallets/profile/${wallet.id}/walletCreditStat" method="post">
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
    </#if>
    <br> <br>


    <div/>


</@c.page>