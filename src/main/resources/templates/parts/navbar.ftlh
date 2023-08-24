
<#include "security.ftlh">

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">PostService</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Main page</a>
        </li>
        <#if user??>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/user/profile">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/main">Messages list</a>
        </li>
         <li class="nav-item">
           <a class="nav-link active" href="/user-messages/${currentUserId}">My messages</a>
         </li>
         </#if>
      </ul>
      <form class="row" action="/logout" method="post">
          <input type="hidden" name="_csrf" value="${_csrf.token}" />
          <button type="submit" class="btn btn-primary"><#if user??>Sign Out<#else>Log in</#if></button>
      </form>
    </div>
</nav>