<#include '../../shared/header.ftl' />

<@pageHeader title="Fragments" uri="/configuration/fragments" action="Create" actionUri="/configuration/fragments/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="fragment" action="/configuration/fragments/<#if item.id??>update<#else>create</#if>" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">External Reference</label></div>
                            <div class="col-10"><input name="externalReference" class="form-control no-padding" type="text" placeholder="External Reference" value="${item.externalReference!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Title</label></div>
                            <div class="col-10"><input name="title" class="form-control no-padding" type="text" placeholder="Title" value="${item.title!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Description</label></div>
                            <div class="col-10"><input name="description" class="form-control no-padding" type="text" placeholder="Description" value="${item.description!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Uri</label></div>
                            <div class="col-10"><input name="uri" class="form-control no-padding" type="text" placeholder="Uri" value="${item.uri!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Item Order</label></div>
                            <div class="col-10"><input name="itemOrder" class="form-control no-padding" type="text" placeholder="Item order" value="${item.itemOrder!''}"></div>
                        </div>

                        <div class="form-group row">
							<#assign checked = item.enabled?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Enabled</label></div>
                            <div class="col-10"><input name="enabled" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Parameters</label></div>
                            <div class="col-10"><input name="parameters" class="form-control no-padding" type="text" placeholder="Parameters" value="${item.parameters!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Query</label></div>
                            <div class="col-10"><textarea id="query" name="query">${item.query!''}</textarea></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Design</label></div>
                            <div class="col-10"><textarea id="design" name="design">${item.design!''}</textarea></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right">Create</button>
                        <a href="/configuration/fragments">
                            <button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />