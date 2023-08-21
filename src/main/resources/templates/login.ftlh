<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-warning d-flex align-items-center" role="alert">
      <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
      <div>
           ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
      </div>
    </div>
</#if>
<div class="col mt-2">
    <h5><span class="badge rounded-pill text-bg-light">Login page</span></h5>
</div>
<@l.login "/login" false/>
</@c.page>