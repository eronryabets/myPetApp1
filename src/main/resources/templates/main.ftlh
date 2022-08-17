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
<form action="/logout" method="post">
    <input type="submit" value="Sign Out"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>