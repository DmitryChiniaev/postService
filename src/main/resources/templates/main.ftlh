<#import "parts/common.ftlh" as c>

<@c.page>
<div>
    <span><a href="/user">User list</a></span>
</div>

<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
  Add new message
</a>
<div class="collapse" id="collapseExample">
<form class="row g-3 mt-2" method="post" enctype="multipart/form-data">
    <div class="col-sm-3">
        <div class="form-floating">
            <input type="text" class="form-control" name="text" id="floatingInput" placeholder="Введите сообщение" />
            <label for="floatingInput">Введите сообщение</label>
        </div>
    </div>
    <div class="col-sm">
        <div class="form-floating">
            <input type="text" class="form-control" name="tag" id="floatingTag" placeholder="Тэг">
            <label for="floatingTag">Тэг</label>
        </div>
    </div>
    <div class="col-sm-4">
        <input type="file" class="form-control" id="formFile" name="file">
    </div>
    <div class="col">
        <input type="hidden" class="form-control" name="_csrf" value="${_csrf.token}" />
    </div>
    <div class="col-sm">
        <button type="submit" class="btn btn-outline-primary">Добавить</button>
    </div>
</form>
</div>

<div>Список сообщений</div>

<form class="row gx-5" method="get" action="/main">
    <div class="col-3">
        <input class="form-control" type="text" name="tagFilter" value="${tagFilter?ifExists}">
    </div>
    <div class="col">
        <button type="submit" class="btn btn-outline-primary">Найти по тегу</button>
    </div>
</form>

<form class="row gx-5" method="get" action="/main">
    <div class="col-3">
        <input class="form-control" type="text" name="tagFilter" value="${tagFilter?ifExists}">
    </div>
    <div class="col">
        <button type="submit" class="btn btn-outline-primary">Найти по тегу</button>
    </div>
</form>

<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
    <div>
        <#if message.filename??>
            <img src="/img/${message.filename}">
        </#if>
    </div>
</div>
<#else>
No message
</#list>
</@c.page>