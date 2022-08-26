<#import "parts/common.ftlh" as c>
<@c.page>
    FINANCE DETAILS :
    Wallet ID : ${wallet.id};
    Name : ${wallet.walletName};

    <div id="accordion">

        <#--    FIRST FORM START -->
        <div class="card">
            <div class="card-header" id="heading1">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                        Выбрать интересующий периуд  :
                    </button>
                </h5>
            </div>

            <div id="collapse1" class="collapse" aria-labelledby="heading1" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    SEARCH
                    <#--      END CODE         -->

                </div>
            </div>
        </div>
        <#--    FIRST FORM END -->
        <#--    SECOND FORM START -->
        <div class="card">
            <div class="card-header" id="heading2">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapse2">
                        Доходы и расходы за прошлую неделю :
                    </button>
                </h5>
            </div>

            <div id="collapse2" class="collapse" aria-labelledby="heading2" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeLastWeek}
                    Расходы : ${spendingLastWeek}
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
                        <#list lastWeek?sortBy("date")?reverse as finance>
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
        <#--    SECOND FORM END -->
        <#--    THIRD FORM START -->
        <div class="card">
            <div class="card-header" id="heading3">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse3" aria-expanded="true" aria-controls="collapse3">
                        Доходы и расходы за текушую неделю :
                    </button>
                </h5>
            </div>

            <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeCurrentWeek}
                    Расходы : ${spendingCurrentWeek}
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
                        <#list currentWeek?sortBy("date")?reverse as finance>
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
        <#--    THIRD FORM END -->
        <#--    FOURTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading4">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse4" aria-expanded="true" aria-controls="collapse4">
                        Доходы и расходы за прошлый месяц :
                    </button>
                </h5>
            </div>

            <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
<#--                    Доходы : ${incomeLastMonth}-->
<#--                    Расходы : ${spendingLastMonth}-->
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
                        <#list lastMonth?sortBy("date")?reverse as finance>
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
        <#--    FOURTH FORM END -->
        <#--    FIFTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading5">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse5" aria-expanded="true" aria-controls="collapse5">
                        Доходы и расходы за текущий месяц :
                    </button>
                </h5>
            </div>

            <div id="collapse5" class="collapse" aria-labelledby="heading5" data-parent="#accordion">
                <div class="card-body">
                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeCurrentMonth}
                    Расходы : ${spendingCurrentMonth}
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
                        <#list currentMonth?sortBy("date")?reverse as finance>
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
        <#--    FIFTH FORM END -->
        <#--    SIXTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading6">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse6" aria-expanded="true" aria-controls="collapse6">
                        Доходы и расходы за 1-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse6" class="collapse" aria-labelledby="heading6" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeFirstQuarter}
                    Расходы : ${spendingFirstQuarter}
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
                        <#list firstQuarter?sortBy("date") as finance>
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
        <#--    SIXTH FORM END -->
        <#--    SEVENTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading7">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse7" aria-expanded="true" aria-controls="collapse7">
                        Доходы и расходы за 2-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse7" class="collapse" aria-labelledby="heading7" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeSecondQuarter}
                    Расходы : ${spendingSecondQuarter}
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
                        <#list secondQuarter?sortBy("date") as finance>
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
        <#--    SEVENTH FORM END -->
        <#--    EIGHTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading8">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse8" aria-expanded="true" aria-controls="collapse8">
                        Доходы и расходы за 3-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse8" class="collapse" aria-labelledby="heading8" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeThirdQuarter}
                    Расходы : ${spendingThirdQuarter}
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
                        <#list thirdQuarter?sortBy("date") as finance>
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
        <#--    EIGHTH FORM END -->
        <#--    NINTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading9">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse9" aria-expanded="true" aria-controls="collapse9">
                        Доходы и расходы за 4-ый налоговый квартал :
                    </button>
                </h5>
            </div>

            <div id="collapse9" class="collapse" aria-labelledby="heading9" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeFourthQuarter}
                    Расходы : ${spendingFourthQuarter}
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
                        <#list fourthQuarter?sortBy("date") as finance>
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
        <#--    NINTH FORM END -->
        <#--    TEHTH FORM START -->
        <div class="card">
            <div class="card-header" id="heading10">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse10" aria-expanded="true" aria-controls="collapse10">
                        Доходы и расходы за весь текущий год :
                    </button>
                </h5>
            </div>

            <div id="collapse10" class="collapse" aria-labelledby="heading10" data-parent="#accordion">
                <div class="card-body">

                    <#--    PUT YOUR CODE -->
                    Доходы : ${incomeCurrentYear}
                    Расходы : ${spendingCurrentYear}
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
                        <#list currentYear?sortBy("date")?reverse as finance>
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
        <#--    TEHTH FORM END -->


        <#--    TEST FORM START -->
        <div class="card">
            <div class="card-header" id="heading11">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse11" aria-expanded="true" aria-controls="collapse11">
                        queryTEST :
                    </button>
                </h5>
            </div>

            <div id="collapse11" class="collapse" aria-labelledby="heading11" data-parent="#accordion">
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
                        <#list queryTEST?sortBy("date")?reverse as finance>
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
        <#--    TEST FORM END -->

    </div>

    <br>
    <a href="/wallets/profile/${wallet.id}" class="btn btn-dark"> Back</a>


</@c.page>