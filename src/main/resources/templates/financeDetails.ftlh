<#import "parts/common.ftlh" as c>
<@c.page>
FINANCE DETAILS :


    <div id="accordion">

        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading1">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                        Доходы и расходы за последнюю неделю :
                    </button>
                </h5>
            </div>

            <div id="collapse1" class="collapse" aria-labelledby="heading1" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading2">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapse2">
                        Доходы и расходы за последний месяц :
                    </button>
                </h5>
            </div>

            <div id="collapse2" class="collapse" aria-labelledby="heading2" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading3">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse3" aria-expanded="true" aria-controls="collapse3">
                        Доходы и расходы за 1-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordion">
                <div class="card-body">


                    <#--    PUT YOUR CODE -->


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
                        <#list financeDate?sortBy("date")?reverse as finance>
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
                        </tbody>
                    </table>


                    <#--      END CODE         -->


                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading4">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse4" aria-expanded="true" aria-controls="collapse4">
                        Доходы и расходы за 2-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading5">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse5" aria-expanded="true" aria-controls="collapse5">
                        Доходы и расходы за 3-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse5" class="collapse" aria-labelledby="heading5" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading6">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse6" aria-expanded="true" aria-controls="collapse6">
                        Доходы и расходы за 4-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse6" class="collapse" aria-labelledby="heading6" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading7">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse7" aria-expanded="true" aria-controls="collapse7">
                        Доходы и расходы за весь год :
                    </button>
                </h5>
            </div>

            <div id="collapse7" class="collapse" aria-labelledby="heading7" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->

    </div>

    <br>
    <a href="/wallets/profile/${wallet.id}" class="btn btn-dark"> Back</a>
    <br>

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
<#--                <td>${finance.stringDate}</td>-->
                <td>${finance.date}</td>
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