<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>
<@c.page>
    <@p.pager url page />

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/messages" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                       placeholder="Search by tag"/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="text" placeholder="Введите сообщение"
                           required="required" minlength = "2"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="tag" placeholder="Тэг"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile"/>
                        <label class="custom-file-label col-sm-4" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns" id="message-list">
<#--        <#list messages?sortBy("date")?reverse as message>-->
        <#list page.content as message>
            <div class="card my-3" data-id="${message.id}">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top"/>
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                    <br/>#
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    <#if (message.author.username)??>${message.author.username}<#else> none </#if>
                    <i>${message.stringDate}</i>
                    <#if isAdmin>
                    <td><a href="/messages/delete/${message.id}" class="btn btn-danger btn-xs">delete</a></td>
                    </#if>
                </div>

            </div>
        <#else>
            No message
        </#list>
    </div>
    <@p.pager url page />

</@c.page>