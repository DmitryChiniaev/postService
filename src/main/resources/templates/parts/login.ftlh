<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="col-sm-4">
        <div class="form-floating mb-3">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
            class="form-control ${(usernameError??)?string('is-invalid','')" id="floatingInput" placeholder="User name"/>
            <label for="floatingInput">User name</label>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="password" name="password" value="<#if user??>${user.email}</#if>"
            class="form-control ${(passwordError??)?string('is-invalid','')" id="floatingPassword" placeholder="Password"/>
            <label for="floatingPassword">Password</label>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="password" name="passwordConfirmation" class="form-control ${(passwordConfirmationError??)?string('is-invalid','')" id="floatingRetypePassword" placeholder="Retype Password"/>
            <label for="floatingRetypePassword">Retype Password</label>
            <#if passwordConfirmationError??>
                <div class="invalid-feedback">
                    ${passwordConfirmationError}
                </div>
            </#if>
        </div>
    </div>
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid','')" id="floatingEmail" placeholder="john@smith.com"/>
            <label for="floatingEmail">E-mail</label>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="row row-cols-auto">
        <div class="col">
            <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign in</#if></button>
        </div>
        <div class="col">
            <#if !isRegisterForm><h6><span class="badge rounded-pill text-bg-light""><a href="/registration">Add new user</a></span></h6></#if>
        </div>
    </div>
</form>
</#macro>

<#macro logout>
<form class="row " action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Sign out</button>
</form>
</#macro>