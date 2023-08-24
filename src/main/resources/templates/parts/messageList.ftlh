<#include "security.ftlh">
<div class="row row-cols-auto row-cols-md-4 g-4">
    <#list messages as message>
    <div class="col">
        <div class="card border-dark mb-3 my-3" style="max-width: 18rem;">
            <div class="row g-0">
                <div class="col-md-8">
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="img-fluid rounded-start">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title"><i>${message.tag}</i></h5>
                        <p class="card-text"><span>${message.text}</span><br/></p>
                        <p class="card-text"><small class="text-body-secondary"><strong>
                            <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                            <#if message.author.id == currentUserId>
                                <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                                    Edit
                                </a>
                            </#if>
                        </strong></small></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#else>

    </#list>
</div>