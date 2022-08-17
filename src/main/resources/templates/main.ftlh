<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<h5>Hello, guest</h5>
<#--User List-->
<br>
<br>
<a href="/registration"
   class="btn btn-light"> Registration page
</a>
<br>
<br>
<a href="/user"
   class="btn btn-light"> User list page
</a>
<br>
<br>
<a href="/profile"
   class="btn btn-light"> My profile
</a>
<br><br>
    <@l.logout/>

</@c.page>