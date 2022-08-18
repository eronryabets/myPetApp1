<#macro login path isRegisterForm>

    <form action="${path}" method="post">

        <div class="form-group">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-3">
                <input type="text" name="username" class="form-control" placeholder="user name"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-3">
                <input type="password" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>
         <#if isRegisterForm>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Surname:</label>
            <div class="col-sm-3">
                <input type="text" name="surname" class="form-control" placeholder="surname"/>
            </div>
        </div>

             <div class="form-group">
                 <label class="col-sm-2 col-form-label"> Name:</label>
                 <div class="col-sm-3">
                     <input type="text" name="name" class="form-control" placeholder="name"/>
                 </div>
             </div>
         </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
         <#if !isRegisterForm><a href="/registration" class="btn btn-light">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>

