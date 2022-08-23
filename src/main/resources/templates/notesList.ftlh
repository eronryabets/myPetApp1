<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<#import "parts/modalButton.ftlh" as m>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/notes" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                       placeholder="Search by title">
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
            <form method="post">
                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="tag" placeholder="Title">
                </div>

                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Example textarea</label>
                    <textarea class="form-control" name="text" id="exampleFormControlTextarea1" rows="3"></textarea>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <br>
    <div class="card-columns">
        <#list notes?sortBy("date")?reverse as notes>
            <div class="card mt-3" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${notes.tag}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${notes.stringDate}</h6>
                    <p class="card-text">${notes.text}</p>
                    <a href="/notes/edit/${notes.id}" class="badge badge-info">edit</a>
                    <a href="/notes/delete/${notes.id}" class="badge badge-danger">delete</a>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>

</@c.page>

