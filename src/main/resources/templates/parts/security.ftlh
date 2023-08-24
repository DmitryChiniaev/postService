<#assign
    known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        currentUserId = user.getId()
    >
<#else>
    <#assign
        currentUserId = -1
    >
</#if>