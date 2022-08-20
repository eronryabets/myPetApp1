<#import "parts/common.ftlh" as c>
<@c.page>

<h5>${user.username}</h5>
    ${message?ifExists}
    <form action="/user/profileData" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name :</label>
            <div class="col-sm-3">
                <input type="text" name="name" class="form-control" value="${user.name}" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Surname :</label>
            <div class="col-sm-3">
                <input type="text" name="surname" class="form-control" value="${user.surname}" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password :</label>
            <div class="col-sm-3">
                <input type="password" name="password" class="form-control" value="${user.password}" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>

    </form>
</@c.page>