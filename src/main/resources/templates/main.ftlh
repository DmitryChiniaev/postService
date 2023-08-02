<#import "parts/common.ftlh" as c>
<@c.page>

<div class="col mt-2">
    <h5><span class="badge rounded-pill text-bg-light"><a href="/user">User list</a></span></h5>
</div>

<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
  Add new message
</a>
<div class="collapse" id="collapseExample">
<form class="row g-3 mt-2" method="post" enctype="multipart/form-data">
    <div class="col-sm-3">
            <input type="text" class="form-control" name="text" id="floatingInput" placeholder="Введите сообщение" />
    </div>
    <div class="col-sm">
            <input type="text" class="form-control" name="tag" id="floatingTag" placeholder="Тэг">
    </div>
    <div class="col-sm-4">
        <input type="file" class="form-control" id="formFile" name="file">
    </div>
    <div class="col">
        <input type="hidden" class="form-control" name="_csrf" value="${_csrf.token}">
    </div>
    <div class="col-sm">
        <button type="submit" class="btn btn-outline-primary">Добавить</button>
    </div>
</form>
</div>

<form class="row gx-5 mt-2" method="get" action="/main">
    <div class="col-3">
        <input class="form-control" type="text" name="tagFilter" value="${tagFilter?ifExists}">
    </div>
    <div class="col">
        <button type="submit" class="btn btn-outline-primary">Найти по тегу</button>
    </div>
</form>

<form class="row gx-5" method="get" action="/main">
    <div class="col-3">
        <input class="form-control" type="text" name="filter" value="${filter?ifExists}"">
    </div>
    <div class="col">
        <button type="submit" class="btn btn-outline-primary">Найти по автору</button>
    </div>
</form>

<div class="row row-cols-auto row-cols-md-4 g-4">
    <#list messages as message>
    <div class="col">
    <div class="card border-dark mb-3 my-3" style="max-width: 18rem;">
        <div class="row g-0">
            <div class="col-md-4">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="img-fluid rounded-start">
                </#if>
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title"><i>${message.tag}</i></h5>
                    <p class="card-text"><span>${message.text}</span></p>
                    <p class="card-text"><small class="text-body-secondary"><strong>${message.authorName}</strong></small></p>
                </div>
            </div>
        </div>
    </div>
    </div>
    <#else>
    No message
    </#list>
</div>
</@c.page>