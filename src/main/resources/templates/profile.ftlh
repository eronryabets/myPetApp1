<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h5>User profile page! Hello ${user.name}</h5>
</head>

<br>
<br>
<a href="/profile/${user.id}/profileEdit"
   class="btn btn-light"> Edit Profile
</a>
<br>
<br>
<a href="/profile/${user.id}/wallets"
   class="btn btn-light"> Wallets
</a>

</@c.page>
