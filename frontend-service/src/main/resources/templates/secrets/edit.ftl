<#include '../shared/header.ftl' />

<@pageHeader title='Passwords' uri='/passwords' action="Create" actionUri="/secrets/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="secret" action="/secrets/update" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Context*</label></div>
                            <div class="col-10"><input name="context" class="form-control no-padding" type="text" placeholder="Context" value="${item.context!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Description</label></div>
                            <div class="col-10"><input name="description" class="form-control no-padding" type="text" placeholder="Description" value="${item.description!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Type</label></div>
                            <div class="col-10">
                                <@select name="type" options=types item=item/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right"><#if item.id??>Update<#else>Create</#if></button>
                        <a href="/passwords">
                            <button type="button" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>
                        <#if item.id??>
                            <a href="/secrets/${item.id}/delete">
                                <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                            </a>
                        </#if>
                    </form>
                </div>
            </div>
            <#if item.id?? && properties?? && properties?size gt 0>
                <div class="card margin-bottom">
                    <div class="card-header">Properties</div>
                    <div class="card-body">
                        <#list properties as p>
                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">${p.name}</label></div>
                                <div class="col-9">
                                    <input class="form-control no-padding" type="text" value="${p.value}" disabled>
                                </div>
                                <div class="col-1">
                                    <a href="/secrets/${p.secretId}/property/${p.id}/delete"><button class="btn btn-danger btn-sm float-right">Delete</button></a>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </#if>

            <#if item.id??>
                <div class="card margin-bottom">
                    <div class="card-header">Add New Property</div>
                    <div class="card-body">
                        <form name="secretProperty" action="/secrets/${item.id}/property/create" method="post">
                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">Name</label></div>
                                <div class="col-10">
                                <@select name="name" options=propertyNames item=item/>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">Value*</label></div>
                                <div class="col-10"><input name="value" class="form-control no-padding" type="text" placeholder="Value" required></div>
                            </div>

                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right"></label></div>
                                <div class="col-10">* denotes required fields.</div>
                            </div>

                            <button class="btn btn-primary btn-sm float-right">Add Property</button>
                        </form>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>

<#include '../shared/footer.ftl' />