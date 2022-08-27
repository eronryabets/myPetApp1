<#import "parts/common.ftlh" as c>
<@c.page>
    CREDIT STAT
    <br>
    Wallet ID : ${wallet.id};
    Name : ${wallet.walletName};
    Balance : ${wallet.balance} ${wallet.currency};

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

    <br>

    <#if (wallet.balance > 0)>
        YOU DON'T HAVE CREDIT DEBT
    </#if>
    <br>


    <#if startDate?? && endDate?? && reductionAmount??>
        Date ${startDate!} - ${endDate!}
        <br>
        Debit percentage : ${percentage!}
        <br>
        Reduction amount : ${reductionAmount!}
        <br>
        Amount with percentage : ${amountWithPercentage!}
        <br>
    </#if>

    <br> <br>


    <div/>


</@c.page>