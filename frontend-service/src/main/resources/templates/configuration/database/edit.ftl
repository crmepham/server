<#include '../../shared/header.ftl' />

<@pageHeader title="Database Configurations" uri="/configuration/database" action="Create" actionUri="/configuration/database/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="fragment" action="/configuration/database/<#if item.id??>update<#else>create</#if>" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Type</label></div>
                            <div class="col-10"><input name="type" class="form-control no-padding" type="text" placeholder="Type" value="${item.type!'mysql'}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Host</label></div>
                            <div class="col-10"><input name="host" class="form-control no-padding" type="text" placeholder="Host" value="${item.host!'localhost'}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Name</label></div>
                            <div class="col-10"><input name="name" class="form-control no-padding" type="text" placeholder="Name" value="${item.name!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Username</label></div>
                            <div class="col-10"><input name="username" class="form-control no-padding" type="text" placeholder="Username" value="${item.username!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Password</label></div>
                            <div class="col-10"><input name="password" class="form-control no-padding" type="text" placeholder="Password" value="${item.password!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Port</label></div>
                            <div class="col-10"><input name="port" class="form-control no-padding" type="text" placeholder="Port" value="${item.port!'3306'}"></div>
                        </div>

                        <div class="form-group row">
							<#assign checked = item.enabled?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Enabled</label></div>
                            <div class="col-10"><input name="enabled" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <#assign checked = item.deleteExistingData?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Delete existing data</label></div>
                            <div class="col-10"><input name="deleteExistingData" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <#assign checked = item.dropTables?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Drop tables</label></div>
                            <div class="col-10"><input name="dropTables" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <#assign checked = item.addIfNotExists?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Add if not exists</label></div>
                            <div class="col-10"><input name="addIfNotExists" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Notes</label></div>
                            <div class="col-10"><textarea id="notes" name="notes">${item.notes!''}</textarea></div>
                        </div>

                        <@commonMeta item=item/>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right"><#if item.id??>Update<#else>Create</#if></button>
                        <a href="/configuration/database">
                            <button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>
                        <#if item.id??>
                            <a href="/configuration/database/${item.id}/delete">
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