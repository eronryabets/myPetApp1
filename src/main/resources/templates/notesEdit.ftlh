<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
EDIT NOTES
    <br><br>

    <form method="post">
        <div class="form-group">
            <input type="text" class="form-control col-sm-4" name="tag" value="${notes.tag}">
        </div>

        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" name="text" id="exampleFormControlTextarea1"
                      rows="3" >${notes.text}</textarea>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
    <a href="/notes" class="badge badge-secondary">back</a>

</@c.page>

