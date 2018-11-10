<#include '../../shared/header.ftl' />

<@pageHeader title="Schedules" uri="/configuration/schedules"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="schedule" action="/configuration/schedules/update" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">External Reference</label></div>
                            <div class="col-10"><input name="externalReference" class="form-control no-padding" type="text" placeholder="External Reference" value="${item.externalReference!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Title</label></div>
                            <div class="col-10"><input name="title" class="form-control no-padding" type="text" placeholder="Title" value="${item.title!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Description</label></div>
                            <div class="col-10"><input name="description" class="form-control no-padding" type="text" placeholder="Description" value="${item.description!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Cron Expression</label></div>
                            <div class="col-10"><input name="cronExpression" class="form-control no-padding" type="text" placeholder="Uri" value="${item.cronExpression!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right">Update</button>
                        <a href="/configuration/schedules">
                            <button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>

                    </form>
                </div>
            </div>

            <div class="card">
                <div class="card-header">Jobs</div>
                <div class="card-body">
                <#if jobs?size gt 0>
                    <table class="data-table" class="display">
                        <thead>
                        <tr>
                            <th width="50%">Title</th>
                            <th width="25%">Implementation</th>
                            <th width="25%" class="text-right">Enabled</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list jobs as j>
                        <tr>
                            <td><p><a href="/configuration/schedules/job/${j.id}">${j.title!j.externalReference}</a></p></td>
                            <td><p>${j.implementation}</p></td>
                            <td class="text-right"><p><@iconBoolean j.enabled /></p></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                <#else>
                    <table><tr><td>There are currently no jobs defined.</td></tr></table>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />