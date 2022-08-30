<#import "parts/common.ftlh" as c>
<@c.page>
    <h5>Exchange Rates : </h5>

    <table class="table table-dark table-striped table-bordered">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Currency code</th>
            <th scope="col">Units</th>
            <th scope="col">Amount</th>
        </tr>
        </thead>
        <tbody>
        <#list list as exchangeRates>
            <tr>
                <td>${exchangeRates.startDate}</td>
                <td>${exchangeRates.currencyCodeL}</td>
                <td>${exchangeRates.units}</td>
                <td>${exchangeRates.amount} UAH</td>
            </tr>
        <#else >
            No finance Exchange Rates
        </#list>
        </tbody>
    </table>

    <br><br>
    <a href="/user/profile"
       class="btn btn-light"> My profile
    </a>
    <br><br>
    <a href="/messages"
       class="btn btn-light"> Messages
    </a>


</@c.page>