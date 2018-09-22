<#if showNavigation!true>
<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="#">CRM</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

             <#list Session.topLevelMenus as topLevelMenu>
                <#if topLevelMenu.children?? && topLevelMenu.children?size gt 0>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${topLevelMenu.title!externalReference}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <#list topLevelMenu.children as child>
                                <a class="dropdown-item" href="${child.uri}">${child.title!externalReference}</a>

                            </#list>
                        </div>
                    </li>
                <#else>
                  <li class="nav-item">
                      <a class="nav-link" href="${topLevelMenu.uri}">${topLevelMenu.title!externalReference}</a>
                  </li>
                </#if>
             </#list>
        </ul>
        <input id="global-search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value="${searchTerm!''}">
    </div>
</nav>
</#if>