var currentSelectedMenu = '';

function FormatDate(d) {
    return new Date(d);
}

function formatJsonDate(d) {
    if (d != null && d.toString().length > 0) {
        var vdate = eval(d.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"));
        return vdate;
    } else {
        return null;
    }
}

function ReplaceDot(obj)
{
    var oldValue=$(obj).val();
    while(oldValue.indexOf("，")!=-1)
    {
        $(obj).val(oldValue.replace("，",","));
        oldValue=$(obj).val();
    }
}

function IsNumeric(obj) {
    var ValidChars = "0123456789.";
    var IsNumber = true;
    var Char;
    var sText = $(obj).val();
    for (i = 0; i < sText.length && IsNumber == true; i++) {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1) {
            $(obj).val("0");
            $(obj).removeClass("input-normal");
            $(obj).addClass("input-validation-error");
            IsNumber = false;
        }
        else
        {
            $(obj).removeClass("input-validation-error");
            $(obj).addClass("input-normal");
            IsNumber = true;
        }
    }
    return IsNumber;
}

function IsNumber(obj) {
    var ValidChars = "0123456789.";
    var IsNumber = true;
    var Char;
    var sText = $(obj).val();
    for (i = 0; i < sText.length && IsNumber == true; i++) {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1) {
            $(obj).removeClass("input-normal");
            $(obj).addClass("input-validation-error");
            IsNumber = false;
        }
        else
        {
            $(obj).removeClass("input-validation-error");
            $(obj).addClass("input-normal");
            IsNumber = true;
        }
    }
    return IsNumber;
}

function ShowTip(message)
{
//    $('#tipValue').html("");
//    $('#tipValue').append(message);
//    $('#tipDialog').dialog('open');
    $("#dialog_message:ui-dialog").dialog("destroy");
    var dlg = $('<div id="tipDialog" class="pagePop"></div>').dialog({
        modal: true,
        title: "提示信息",
        buttons: {
            '确定': function() {
                $(this).dialog("close");
            }
        }
    });
    dlg.html("<p><img src='../../content/images/pass_24.png' border='0'/>" + message + "</p>");
}

function ShowAlert(message)
{
//    $('#alertValue').html("");
//    $('#alertValue').append(message);
//    $('#alertDialog').dialog('open');
    $("#dialog_message:ui-dialog").dialog("destroy");
    var dlg = $('<div id="alertDialog" class="pagePop"></div>').dialog({
        modal: true,
        title: "提示信息",
        buttons: {
            '确定': function() {
                $(this).dialog("close");
            }
        }
    });
    dlg.html("<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left;\"></span>" + message + "</p>");
}

function compareDate(d1,d2)
{
    var arrayD1 = d1.split("-");
    var date1 = new Date(arrayD1[0],arrayD1[1],arrayD1[2]);
    var arrayD2 = d2.split("-");
    var date2 = new Date(arrayD2[0],arrayD2[1],arrayD2[2]);
    if(date1 > date2) return false;
    return true;
}

function verifyDate(d)
{
    var datePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
    return datePattern.test(d);
}

function contains(string,substr,isIgnoreCase)
{
    if(isIgnoreCase)
    {
        string=string.toLowerCase();
        substr=substr.toLowerCase();
    }
    var startChar=substr.substring(0,1);
    var strLen=substr.length;
    for(var j=0;j<string.length-strLen+1;j++)
    {
        if(string.charAt(j)==startChar)
        {
            if(string.substring(j,j+strLen)==substr)
            {
                return true;
            }
        }
    }
    return false;
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function toggleFilter() {
    $("#toggleText").toggle(200);
    if ($.trim($("#displayText").text()) == "Show Filter") {
        $("#displayText").text('Hide Filter');
    }
    else {
        $("#displayText").text('Show Filter');
    }
}

function toggle() {
    $("#toggleText").toggle(200);

    if ($.trim($("#displayText").text()) == "Show Filter") {
        $("#displayText").text('Hide Filter');
    }
    else {
        $("#displayText").text('Show Filter');
    }
}



