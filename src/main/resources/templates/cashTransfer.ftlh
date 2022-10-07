<#import "parts/common.ftlh" as c>
<@c.page>
    <head>

        <h3>USER ACCESS</h3>

        <h5>Wallet ${wallet.type} name ${wallet.walletName}</h5>
        <h5>Wallet id ${wallet.id}</h5>
        <h5>Wallet balance ${wallet.balance}</h5>
        <h5><p style="color:red">${errorMessage!}</p></h5>

        <br>


    </head>

        <form action="/wallets/profile/${wallet.id}/cashTransfer" method="post">
            <div class="form-group">

                <label class="col-sm-3 col-form-label"> Please input amount: </label>
                <div class="col-sm-3">
                    <input type="number" name="amount" class="form-control"
                           placeholder="amount"/>
                </div>

                <label class="col-sm-3 col-form-label"> Please input another wallet id: </label>
                <div class="col-sm-3">
                    <input type="number" name="anotherWalletId" class="form-control"
                           placeholder="another wallet id"/>
                    <button class="btn btn-success mt-1" type="submit">Confirm</button>
                </div>

                <input type="hidden" name="balance" value="${wallet.balance}">
                <input type="hidden" name="walletName" value="${wallet.walletName}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
        </form>


</@c.page>