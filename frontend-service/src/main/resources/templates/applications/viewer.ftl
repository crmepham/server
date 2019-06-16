<#include '../shared/header.ftl' />

    <div class="container-fluid">
        <div class="row">
            <div class="col-12 no-padding">
                <div id="viewer-control-container">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Type
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item type-item active" href="#">Image</a>
                            <a class="dropdown-item type-item" href="#">Video</a>
                        </div>
                    </div>

                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Year
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item year-item active" href="#">All</a>
                            <#list years as y>
                                <a class="dropdown-item year-item" href="#">${y?c}</a>
                            </#list>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div id = "image-container"></div>
            </div>
        </div>
    </div>


<#include '../shared/footer.ftl' />