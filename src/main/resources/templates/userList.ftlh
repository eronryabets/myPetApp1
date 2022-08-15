<head>
    <h5>User list page</h5>
</head>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>