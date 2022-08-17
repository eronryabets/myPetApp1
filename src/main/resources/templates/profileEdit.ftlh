</head>
<h5>Profile editor! Hello ${user.name}</h5>
</head>

<form action="/profile/${user.id}/profileEdit" method="post">

    User name :
    <input type="text" name="name" value="${user.name}">

    <br><br>
    User surname :
    <input type="text" name="surname" value="${user.surname}">

    <br><br>
    User password :
    <input type="text" name="password" value="${user.password}">

    <input type="hidden" value="${user.id}" name="userId">
    <br><br>
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>

    <br><br>
    <a href="/profile/${user.id}"
       class="btn btn-light"> Back
    </a>



</form>