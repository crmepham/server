<#include '../../shared/header.ftl' />

<@pageHeader title='Monitor' uri='/applications/monitor' />

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="monitor" action="/applications/monitor/update" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Name*</label></div>
                            <div class="col-10"><input name="name" class="form-control no-padding" type="text" placeholder="Name" value="${item.name!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">URI*</label></div>
                            <div class="col-10"><input name="uri" class="form-control no-padding" type="url" placeholder="Name" value="${item.uri!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Reachable</label></div>
                            <div class="col-10"><p><@iconBoolean item.reachable /></p></div>
                        </div>

                        <#assign checked = (item.reachable!false)?string('checked', '')/>
                        <input name="reachable" type="checkbox" ${checked} style="display:none;">

                        <div class="form-group row">
                            <#assign checked = (item.notify!false)?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Notify</label></div>
                            <div class="col-10"><input name="notify" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Notes</label></div>
                            <div class="col-10"><textarea name="note" >${item.note!''}</textarea></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right"><#if item.id??>Update<#else>Create</#if></button>
                        <a href="/applications/monitor">
                            <button type="button" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>
                        <#if item.id??>
                            <a href="/applications/monitor/${item.id}/delete">
                                <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                            </a>
                        </#if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />