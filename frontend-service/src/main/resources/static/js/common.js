$(document).ready(function() {
    var sensitive = $('.sensitive');
    var sensitiveText = sensitive.text();
    var textLength = sensitiveText.length;
    var newText = "";
    for (let i = 0; i < textLength; i++) {
        newText = newText + "*";
    }
    sensitive.text(newText);
});