<#import "parts/common.ftlh" as c>
<@c.page>
<div class="col mt-2">
    <h5><span class="badge rounded-pill text-bg-light"><a href="/user">User list</a></span></h5>
</div>

<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
  New message
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
<form class="row g-3 mt-2" method="post" enctype="multipart/form-data">
    <div class="col-sm-3">
        <input type="text" class="form-control" name="text" id="floatingInput" placeholder="Введите сообщение" required/>
    </div>
    <div class="col-sm">
        <input type="text" class="form-control" name="tag" id="floatingTag" placeholder="Тэг" required>
    </div>
    <div class="col-sm-4">
        <input type="file" class="form-control" id="formFile" name="file">
    </div>
    <div class="col">
        <input type="hidden" class="form-control" name="_csrf" value="${_csrf.token}">
    </div>
    <div class="col">
        <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
    </div>
    <div class="col-sm">
        <button type="submit" class="btn btn-outline-primary">Save message</button>
    </div>
</form>
</div>
<form class="row gx-5 mt-2" method="get" action="/main">
    <div class="col-3">
        <input class="form-control" type="text" name="tagFilter" value="${tagFilter?ifExists}" required>
    </div>
    <div class="col">
        <button type="submit" class="btn btn-outline-primary">Найти по тегу</button>
    </div>
</form>

<form class="row gx-5" method="get" action="/main">
    <div class="col-3">
        <input class="form-control" type="text" name="filter" value="${filter?ifExists}" required>
    </div>
    <div class="col">
        <button type="submit" class="btn btn-outline-primary">Найти по автору</button>
    </div>
</form>
<#include "parts/messageList.ftlh" />
</@c.page>