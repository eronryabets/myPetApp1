<#import "parts/common.ftlh" as c>
<#import  "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-1">Add new user</div>
    <h5><p style="color:red">${errorMessage!}</p></h5>
    <@l.login "/registration" true />
</@c.page>
