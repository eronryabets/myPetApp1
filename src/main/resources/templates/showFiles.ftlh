<#import "parts/common.ftlh" as c>
<@c.page>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new File
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label col-sm-4" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>
    <br><br>

    <#list files?sortBy("file")?reverse as file>
        <tr>
            <td><a href="/download/${file.getName()}">${file.getName()}</a></td>
            <td>#{ ((file.length())/1024)?round } KB</td>
            <a href="/download/delete/${file.getName()}" class="badge badge-danger">delete</a>
            <br>

        </tr>
    <#else >
        No wallets
    </#list>

</@c.page>