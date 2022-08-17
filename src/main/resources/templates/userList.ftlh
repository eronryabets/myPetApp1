<head>
    <h5>User list page</h5>
</head>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Username</th>
        <th>Name</th>
        <th>Surname</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
        <#else >
        No users
    </#list>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </tbody>
</table>
<br>
<a href="/main"
   class="btn btn-light"> Back
</a>