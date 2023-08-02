<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="col mt-2">
    <h5><span class="badge rounded-pill text-bg-light">Login page</span></h5>
</div>
<@l.login "/login" false/>
</@c.page>