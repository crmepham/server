<#include '../../shared/header.ftl' />

<@pageHeader title='Properties' uri='/configuration/properties' />

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <#if item??>
					<form name="property" action="/configuration/properties/update" method="post">

                        <input type="hidden" value="${item.id}" name="id">

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">External Reference*</label></div>
                            <div class="col-10"><input name="externalReference" class="form-control no-padding" type="text" value="${item.externalReference!''}" placeholder="External reference" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Description</label></div>
                            <div class="col-10"><input name="description" class="form-control no-padding" type="text" value="${item.description!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Value</label></div>
                            <div class="col-10"><textarea name="value">${item.value!''}</textarea></div>
                        </div>

                        <@commonMeta item=item/>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button id="updateDashboardScreensButton" type="submit" class="btn btn-primary btn-sm float-right">Update</button>
                        <a href="/configuration/dashboards"><button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Cancel</button></a>
                    </form>
                    <#else>
					<div class="alert alert-warning">There is no property information to display</div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />