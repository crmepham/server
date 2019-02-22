<!DOCTYPE html>
<html lang="en" style="background:black;margin:0;padding:0;">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${file.description!''}">
    <meta name="author" content="Christopher Mepham">
    <title>${file.title!''}</title>
</head>
<body style="margin:0;padding:0;">
<#if !file?? || !image??>
    <p style="color: white;">Could not find image!</p>
<#else>
    <img src="data:image/${suffix};base64,${image}" title="${file.title!''}"/>
</#if>
</body>
</html>