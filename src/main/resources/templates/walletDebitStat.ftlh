<#import "parts/common.ftlh" as c>
<@c.page>
    DEBIT STAT
    <br>
    Wallet ID : ${wallet.id};
    Name : ${wallet.walletName};
    Balance : ${wallet.balance} ${wallet.currency}

    <form action="/wallets/profile/${wallet.id}/walletDebitStat" method="post">
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
        <button class="btn btn-primary" type="submit">Calc</button>
        </div>
    </form>

    <div class="container mt-3">

    <br>
    <#if startDate?? && endDate?? && amountWithPercentage??>
        Date ${startDate!} - ${endDate!}
        <br>
        Debit percentage : ${percentage!}
        <br>
        Increase amount : ${increaseAmount!}
        <br>
        Amount with percentage : ${amountWithPercentage!}
        <br>

    </#if>
    <br> <br>


    <div/>


</@c.page>