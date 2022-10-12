<#macro login path isRegisterForm>

    <form action="${path}" method="post">

        <div class="form-group">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-3">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="user name"
                value="${username!}"/>

                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>

            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-3">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="password"/>

                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>

            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Surname:</label>
                <div class="col-sm-3">
                    <input type="text" name="surname"
                           class="form-control ${(surnameError??)?string('is-invalid', '')}"
                           placeholder="surname"
                           value="${surname!}"/>

                    <#if surnameError??>
                        <div class="invalid-feedback">
                            ${surnameError}
                        </div>
                    </#if>

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Name:</label>
                <div class="col-sm-3">
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"
                           placeholder="name"
                           value="${name!}"/>

                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>

                </div>
            </div>

            <div class="col-sm-6">
                <div class="g-recaptcha" data-sitekey="6LcGLXciAAAAAPfPMKIeBB_Pj9NjjwOX1nYwOyvc"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
            <br/><br/>

        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration" class="btn btn-light">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-secondary" type="submit">Sign Out</button>
    </form>
</#macro>