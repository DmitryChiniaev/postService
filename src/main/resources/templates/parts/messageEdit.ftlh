<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
  Message editor
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