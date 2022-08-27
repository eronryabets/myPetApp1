<#import "parts/common.ftlh" as c>
<@c.page>

    <#list files as file>
        <tr>
            <td><a href="/download/${file.getName()}">${file.getName()}</a></td>
            <td>#{ ((file.length())/1024)?round } KB</td>
            <br>

        </tr>
    <#else >
        No wallets
    </#list>

</@c.page>