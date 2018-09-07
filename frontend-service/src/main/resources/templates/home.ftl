<#include 'shared/header.ftl' />

<#if session_dashboard??>
    <@session_dashboard.content?interpret />
	<a style="width:100%; display:block; margin-top:10px;" class="textRight" href="/admin/dashboardScreens/edit.htm?id=${session_dashboard.id}">Edit</a>
</#if>

<#include 'shared/footer.ftl' />