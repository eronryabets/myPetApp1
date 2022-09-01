<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h5>User list page! Hello ${user.name}</h5>
</head>

<form action="/user" method="post">
    User login :
    <input type="text" name="username" value="${user.username}">

    <br><br>
    User name :
    <input type="text" name="name" value="${user.name}">

    <br><br>
    User surname :
    <input type="text" name="surname" value="${user.surname}">

    <br><br>
    User password :
    <input type="text" name="password" placeholder="password">
    <br><br>
    Roles :
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}"
                        ${user.roles?seq_contains(role)?string
                        ("checked", "")}>${role}</label>
        </div>
    </#list>

    <input type="hidden" name="userId" value="${user.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <br><br>
    <button type="submit">Save</button>
    <br><br>
    <a href="/user"
       class="btn btn-light"> Back
    </a>
</form>

</@c.page>