<#import "parts/common.ftlh" as c>

<@c.page>
List of users
<div>
<form action="/user/remove" method="get">
    <button type="submit">Удалить все сообщения</button>
</form>
</div>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>