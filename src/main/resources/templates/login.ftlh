<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h5><p style="color:red">${message!}</p></h5>
    <@l.login "/login" false/>
</@c.page>