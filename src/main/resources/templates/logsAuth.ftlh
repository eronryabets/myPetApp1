<#import "parts/common.ftlh" as c>
<@c.page>

 Strtistic

    <br>
    <a href="/user"
       class="btn btn-dark"> Back
    </a>

    <br>
    <#list files?sortBy("file")?reverse as file>
        <tr>
            <td><a href="/user/logsAuth/${file.getName()}">${file.getName()}</a></td>
            <td>#{ ((file.length())/1024)?round } KB</td>
            <a href="/user/logsAuth/delete/${file.getName()}" class="badge badge-danger">delete</a>
            <br>

        </tr>
    <#else >
        No files
    </#list>

</@c.page>