<#include '../../shared/header.ftl' />

<@pageHeader title='Todos' uri='/applications/todos' action="Create" actionUri="/applications/todos/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="todo" action="/applications/todos/update" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Instruction*</label></div>
                            <div class="col-10"><input name="instruction" class="form-control no-padding" type="text" placeholder="Instruction" value="${item.instruction!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Context</label></div>
                            <div class="col-10"><input name="context" class="form-control no-padding" type="text" placeholder="Context" value="${item.context!''}" required></div>
                        </div>

                        <div class="form-group row">
						<#assign checked = (item.pinned!false)?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Pinned</label></div>
                            <div class="col-10"><input name="pinned" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right"><#if item.id??>Update<#else>Create</#if></button>
                        <a href="/applications/todos">
                            <button type="button" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>
                        <#if item.id??>
                            <a href="/applications/todos/${item.id}/delete">
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