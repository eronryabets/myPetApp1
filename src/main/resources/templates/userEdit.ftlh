<#import "parts/common.ftlh" as c>
<@c.page>

<head>
    <h5>User list page! User :  ${user.username}</h5>
</head>

    <form action="/user" method="post">
        <div class="container">
            <br>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">User Login:</label>
                <div class="col-sm-3">
                    <input type="text" name="username" class="form-control" value="${user.username}"
                           required minlength="2" maxlength="15"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Name:</label>
                <div class="col-sm-3">
                    <input type="text" name="name" class="form-control" value="${user.name}"
                           required minlength="2" maxlength="20"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Surname:</label>
                <div class="col-sm-3">
                    <input type="text" name="surname" class="form-control" value="${user.surname}"
                           required minlength="2" maxlength="20"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Password:</label>
                <div class="col-sm-3">
                    <input type="password" name="password" class="form-control" placeholder="password"/>
<#--                           required minlength="8" maxlength="20" />-->
                </div>
            </div>

        </div>

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
        <button class="btn btn-primary" type="submit">Save</button>
        <br><br>
        <a href="/user"
           class="btn btn-light"> Back
        </a>

    </form>
    <br><br>

    <form action="/user" method="post">

    </form>

</@c.page>