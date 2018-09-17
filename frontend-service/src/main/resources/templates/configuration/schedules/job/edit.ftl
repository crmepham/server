<#include '../../../shared/header.ftl' />

<@pageHeader title="Schedules" uri="/configuration/schedules" action="Invoke" actionUri="/configuration/schedules/job/" + item.id + "/invoke"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="schedule" action="/configuration/schedules/job/update" method="post">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <input type="hidden" name="scheduleId" value="${item.scheduleId}"/>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">External Reference</label></div>
                            <div class="col-10"><input name="externalReference" class="form-control no-padding" type="text" value="${item.externalReference!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Title</label></div>
                            <div class="col-10"><input name="title" class="form-control no-padding" type="text" value="${item.title!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Description</label></div>
                            <div class="col-10"><input name="description" class="form-control no-padding" type="text" value="${item.description!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Implementation</label></div>
                            <div class="col-10"><input name="implementation" class="form-control no-padding" type="text" value="${item.implementation!''}" readonly></div>
                        </div>

                        <div class="form-group row">
							<#assign checked = item.enabled?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Enabled</label></div>
                            <div class="col-10"><input name="enabled" type="checkbox" ${checked}></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right">Update</button>
                        <a href="/configuration/schedules/${item.scheduleId!''}">
                            <button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../../shared/footer.ftl' />