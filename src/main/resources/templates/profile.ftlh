<#import "parts/common.ftlh" as c>

<@c.page>
<div class="col mt-2">
    <h5><span class="badge rounded-pill text-bg-light">${username}</span></h5>
</div>
${message?ifExists}
<form method="post">
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password"/>
            <label for="floatingPassword">Password</label>
        </div>
    </div>
    <div class="col-sm-4">
         <div class="form-floating mb-3">
            <input type="email" name="email" class="form-control" id="floatingEmail" placeholder="john@smith.com" value="${email!''}"/>
            <label for="floatingEmail">E-mail</label>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="row row-cols-auto">
        <div class="col">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form>
</@c.page>