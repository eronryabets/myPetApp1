<head>
    <h5>Profile editor! Hello ${user.name}</h5>
</head>

<form action="/user" method="post">
    User name :
    <input type="text" name="name" value="${user.name}">
    <br><br>
    User surname :
    <input type="text" name="surname" value="${user.surname}">
    <br><br>
    User password :
    <input type="text" name="password" value="${user.password}">

    <input type="hidden" value="${user.id}" name="userId">
    <#--    <input type="hidden" value="${_csrf.token}" name="_csrf">-->
    <br><br>
    <button type="submit">Save</button>
</form>