<#import "parts/common.ftlh" as c>

<@c.page>

    <h5>${user.username}</h5>
    ${message?ifExists}

    <br>
    <#if user.avatar??>
        <img class="rounded-img" src="/img/avatar/${user.avatar}" class="someClass "/>
    </#if>
    <br>

    <form action="/user/profile/profileData" method="post" enctype="multipart/form-data">
        <div class="container">
            <br>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="avatar" id="customFile">
                    <label class="custom-file-label col-sm-4" for="customFile">Choose avatar</label>
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
                    <input type="password" name="password" class="form-control" placeholder="password"
                           required minlength="8" maxlength="20" />
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <a href="/user/profile" class="btn btn-light"> Back</a>
            <button class="btn btn-primary" type="submit">Save</button>

        </div>
    </form>
</@c.page>