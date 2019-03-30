<#include '../shared/header.ftl' />

<@pageHeader title="System" uri="/configuration/system" />

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <div class="system-section">
                        <p>Use this button to restart all of the three applications this system uses. Note that once stopped, the applications must be manually started again individually.</p>
                        <div class="float-right">
                            <button id="stop-application" type="button" class="btn btn-primary btn-sm">Stop Application</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />