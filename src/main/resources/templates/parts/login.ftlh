<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="col-sm-4">
        <div class="form-floating mb-3">
            <input type="text" name="username" class="form-control id="floatingInput" placeholder="User name" required/>
            <label for="floatingInput" class="form-label">User name</label>
        </div>
    </div>
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="password" name="password" class="form-control id="floatingPassword" placeholder="Password" required/>
            <label for="floatingPassword">Password</label>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="email" name="email" class="form-control id="floatingEmail" placeholder="john@smith.com" required/>
            <label for="floatingEmail">E-mail</label>
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
<form class="row" action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary"><#if user??>Sign Out<#else>Log in</#if></button>
</form>
</#macro>