<#include '../../shared/header.ftl' />

<@pageHeader title='Files' uri='/applications/files' action="Create" actionUri="/applications/files/create"/>

<div class="container h-100">

    <#if item.absolutePath?? && !fileExists>
    <div class="alert alert-danger" role="alert">
        File no longer exists in the file system at location <em>${item.absolutePath}</em>.
    </div>
    </#if>

    <#if item.id??>
        <div class="row">
            <div class="col-md-12">
                <div class="card margin-bottom">
                    <div class="card-header">File</div>
                    <div class="card-body" style="overflow: no-display;padding-right: 20px;">

                        <#if item.absolutePath?? && fileExists>

                            <div class="alert alert-success" role="alert">
                                Referenced file does exist in the file system at location <em>${item.absolutePath}</em>.
                            </div>


                            <#if item.type == "Image">
                                <img src="/file/${item.externalReference}" title="${item.title!item.externalReference}" />
                            </#if>

                        </#if>

                        <#if !(item.absolutePath??)>
                        <form name="file" action="/applications/files/${item.id?c}/upload" method="post" enctype="multipart/form-data">

                            <div class="form-group row">
                                <div class="col-12"><input type="file" name="file" /></div>
                            </div>

                            <button class="btn btn-primary btn-sm float-right">Upload</button>
                        </form>
                        </#if>

                        <br><br>
                        <#if item.absolutePath?? && fileExists>
                            <a href="/applications/files/download/${item.externalReference}">
                                <button type="button" class="btn btn-primary btn-sm float-right">Download</button>
                            </a>
                            <a href="/applications/files/${item.id?c}/delete/file">
                                <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </#if>

    <div class="row ">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="file" action="/applications/files/update" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id?c}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">External Reference*</label></div>
                            <div class="col-10"><input name="externalReference" class="form-control no-padding" type="text" placeholder="External Reference" value="${item.externalReference!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Title*</label></div>
                            <div class="col-10"><input name="title" class="form-control no-padding" type="text" placeholder="Title" value="${item.title!''}" required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Description</label></div>
                            <div class="col-10"><input name="description" class="form-control no-padding" type="text" placeholder="Description" value="${item.description!''}" required></div>
                        </div>

                        <#if item.id??>
                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">Short Reference</label></div>
                                <div class="col-10"><input name="shortReference" class="form-control no-padding" type="text" placeholder="External Reference" value="${item.shortReference!''}" readonly></div>
                            </div>

                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">Short Link</label></div>
                                <div class="col-10"><input class="form-control no-padding" type="text" value="https://crm86.uk/i/${item.shortReference!''}" readonly></div>
                            </div>

                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">Web Link</label></div>
                                <div class="col-10"><input class="form-control no-padding" type="text" value="https://crm86.uk/w/${item.shortReference!''}" readonly></div>
                            </div>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Type*</label></div>
                            <div class="col-10">
							<#if types?has_content>
                                <select name="type" class="select" <#if item.absolutePath?? && fileExists>disabled</#if>>
                                    <option disabled>Type</option>
                                    <#list types as type>

                                        <#if item.type?? && type == item.type>
                                            <option selected value="${type}">${type}</option>
                                        <#else>
                                            <option value="${type}">${type}</option>
                                        </#if>

                                    </#list>
                                </select>
                            </#if>
                            </div>
                        </div>

                        <#if item.absolutePath?? && fileExists><input name="type" type="hidden" value="${item.type!''}"></#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Path Suffix</label></div>
                            <div class="col-10"><input name="pathSuffix" class="form-control no-padding" type="text" placeholder="Path suffix" value="${item.pathSuffix!''}" <#if item.absolutePath?? && fileExists>readonly</#if>></div>
                        </div>

                        <#if item.absolutePath?has_content>
                            <div class="form-group row">
                                <div class="col-2"><label class="col-form-label right">Absolute Path</label></div>
                                <div class="col-10"><input name="absolutePath" class="form-control no-padding" type="text" value="${item.absolutePath}" readonly></div>
                            </div>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right"><#if item.id??>Update<#else>Create</#if></button>
                        <a href="/applications/files">
                            <button type="button" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>
                        <#if item.id??>
                            <a href="/applications/files/${item.id?c}/delete">
                                <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                            </a>
                        </#if>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#if item.id?? && item.properties?size gt 0>
        <div class="row">
            <div class="col-md-12">
                <div class="card margin-bottom">
                    <div class="card-header">Properties</div>
                    <div class="card-body">
                        <#list item.properties as p>
                            <div class="form-group row">
                                <div class="col-3"><label class="col-form-label right">${p.title!p.name}</label></div>
                                <div class="col-9"><input class="form-control no-padding" type="text" value="${p.value}<#if p.name == 'size'>MB</#if>" readonly></div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </#if>

</div>

<#include '../../shared/footer.ftl' />