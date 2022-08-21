<#import "parts/common.ftlh" as c>
<#import "parts/modalButton.ftlh" as m>
<@c.page>

    <head>

        <h3>USER ACCESS</h3>

        <h5>Wallet name ${wallet.walletName} ; id ${wallet.id}</h5>
        <br>
    </head>



<#-- TEST START-->
    <div id="accordion">
        <#--    1 FORM START -->
        <div class="card">
            <div class="card-header" id="heading1">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                        Renaming the wallet
                    </button>
                </h5>
            </div>

            <div id="collapse1" class="collapse" aria-labelledby="heading1" data-parent="#accordion">
                <div class="card-body">

                    <form action="/wallets/profile/${wallet.id}" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 col-form-label"> Wallet Name : </label>
                            <div class="col-sm-3">
                                <input type="text" name="walletName" class="form-control" value="${wallet.walletName}"
                                       placeholder="${wallet.walletName}"/>
                                <button class="btn btn-success mt-1" type="submit">Save</button>
                            </div>
                            <input type="hidden" name="balance" value="${wallet.balance}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <#--    1 FORM END -->
        <#--    2 FORM START -->
        <div class="card">
            <div class="card-header" id="heading2">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapse2">
                        Wallet replenishment
                    </button>
                </h5>
            </div>

            <div id="collapse2" class="collapse" aria-labelledby="heading2" data-parent="#accordion">
                <div class="card-body">
                    <#--    PUT YOUR CODE -->
                    TEST
                    TEST
                    TEST
                </div>
            </div>
        </div>
        <#--    2 FORM END -->
        <#--    3 FORM START -->
        <div class="card">
            <div class="card-header" id="heading3">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse3" aria-expanded="true" aria-controls="collapse3">
                        Cash withdrawal
                    </button>
                </h5>
            </div>

            <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordion">
                <div class="card-body">
                    <#--    PUT YOUR CODE -->
                    TEST
                    TEST
                    TEST
                </div>
            </div>
        </div>
        <#--    3 FORM END -->
        <#--    4 FORM START -->
        <div class="card">
            <div class="card-header" id="heading4">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse4" aria-expanded="true" aria-controls="collapse4">
                        Transferring cash to another wallet
                    </button>
                </h5>
            </div>

            <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordion">
                <div class="card-body">
                    <#--    PUT YOUR CODE -->
                    TEST
                    TEST
                    TEST
                </div>
            </div>
        </div>
        <#--    4 FORM END -->
        <#--    5 FORM START -->
        <div class="card">
            <div class="card-header" id="heading5">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse5" aria-expanded="true" aria-controls="collapse5">
                        Interest income
                    </button>
                </h5>
            </div>

            <div id="collapse5" class="collapse" aria-labelledby="heading5" data-parent="#accordion">
                <div class="card-body">
                    <#--    PUT YOUR CODE -->
                    TEST
                    TEST
                    TEST
                </div>
            </div>
        </div>
        <#--    5 FORM END -->
        <#--    6 FORM START -->
        <div class="card">
            <div class="card-header" id="heading6">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse6" aria-expanded="false" aria-controls="collapse6">
                        Delete this wallet - ${wallet.walletName}
                    </button>
                </h5>
            </div>

            <div id="collapse6" class="collapse" aria-labelledby="heading6" data-parent="#accordion">
                <div class="card-body">
                    <@m.page>
                    Are you sure? You want to delete the wallet?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <a href="/wallets/profile/${wallet.id}/delete" class="btn btn-danger">Delete</a>
                </div>
                    </@m.page>
                </div>
            </div>
        </div>
        <#--    6 FORM END -->

    </div>


<#-- TEST END-->
</@c.page>

