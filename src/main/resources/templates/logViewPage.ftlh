<#import "parts/common.ftlh" as c>
<@c.page>

    LOG FILE

    <br>
    <a href="/user/logsAuth"
       class="badge badge-pill badge-dark"> Back
    </a>

    <br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th scope="col">log file : ${fileName}</th>
        </tr>
        </thead>
        <tbody>
        <#list logs?reverse as log>
            <tr>
                <td>${log}</td>
            </tr>
        <#else >
            File is empty
        </#list>
        </tbody>
    </table>


</@c.page>