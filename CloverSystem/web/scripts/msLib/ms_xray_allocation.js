/// <reference name="MicrosoftAjax.js"/>
currentSelectedMenu = 'Tools';

var dialogFormXray = $('#dialog-form-xray')[0];
var btnPopPortfolioSave = $('#btnPopPortfolioSave')[0];
var btnPortfolioSave = $('#btnPortfolioSave')[0];
var btnPortfolioCancel = $('#btnPortfolioCancel')[0];
var txtPortfolioName = $('#txtPortfolioName')[0];
var ddlUserXRay = $('#ddlUserXRay')[0];
var btnImport = $('#btnImport')[0];
var imgLoad = '/Content/Images/ajax-loader.gif';
var optExcelImport = $('#' + _excelImportVal)[0];
var dvImportOption = $('#dvImportOption')[0];
var ctxXhr = null;
var btnExport = $('#btnExport')[0];
var btnDelete = $('#btnDelete')[0];
var btnClearAll = $('#btnClearAll')[0];


var pageXRay = {
    getUniverse: function() {
        var selected = $('#ddlUniverse').val();
        return selected.indexOf(',') > -1 ? selected.split(',') : [selected];
    }
}

function overrideElement(eleChild, eleBase, overrides) {

    $.each(overrides, function(k, v) {
        $.map(v, function(f) {
            switch (k) {
                case 'attr':
                    $(eleChild).attr(f, $(eleBase).attr(f));
                    break;
                case 'custom':
                    f(eleChild, eleBase);
                    break;
                case 'css':
                    $(eleChild).css(f, $(eleBase).css(f));
                    break;
            }
        });
    });

}

$(".chzn-select").ajaxChosen({
    method: 'POST',
    url: '/PortfolioXray/SearchInvestments',
    type: 'POST',
    data: { pageIndex: 0, tags: pageXRay.getUniverse() },
    asyn: true,
    traditional: true,
    dataType: "json",
    complete: function() {
        //alert('complete' + MS_XRay.Storage().length);
        //MS_XRay.BindItems();



        //return $("<option value='1'>ffff</option><option value='aaa'>aaa</option>").toArray();
        //return $("<optgroup style='background-color:Red;' label='AAAA'></optgroup>").toArray();

    }
},
    function(data) {
        //alert('success' + MS_XRay.Storage().length);
        var terms = {};
        if (typeof data == 'string') {
            data = $.parseJSON(data);
        }
        $.each(data.Items, function(i, val) {
            terms[i] = MS_XRay.CreateOption(val);
        });

        return terms;
    });

$('#btnXRay').click(function() {

    var url = '/PortfolioXray/';
    var params = '';
    var percentage = 0;
    var containZero = false;
    var query = '';
    $.map($('input[ms_xray_allocator=""]').toArray(), function(n) {

        var iValue = Number.parseInvariant($(n).val());
        if (query.length > 0) {
            query += ("|" + $(n).attr('ms_xray') + "," + $(n).val());
        }
        else {
            query += ($(n).attr('ms_xray') + "," + $(n).val());
        }
        percentage += iValue;
        if (iValue <= 0) {
            containZero = true;
        }
    });

    if (percentage == 100 && containZero == false) {

        window.open('/PortfolioXray/Display?' + "id=" + $(ddlUserXRay).val() + "&query=" + query, '_self');

    }
    else if (containZero == true) {
        alert("All investments must have an allocation greater than zero.");
    }
    else {
        alert('The allocation does not total 100%. Please amend to proceed.');
    }

});

$('#btnClearAllocation').click(function() {
    MS_XRay.ClearAllocation();
    MS_XRay.CalculateAllocation();
    MS_XRay.UpdateCountInvestment();    
});


$('#btnClearAll').click(function() {
    MS_XRay.ClearAll();
    MS_XRay.UpdateCountInvestment();        
    ToggleActionButtons();    
});


//Fixed magic text
$(function() {
    $('#searchTextbox').val('Enter ticker, APIR, text');
})

var MS_XRay = {
    /*Configuration*/
    DestinationTable: '#tblXRay', //tr:not(.T_label)
    RowTemplate: $('#tmplRowXRay')[0],
    SearchDatasource: '/PortfolioXray/SearchInvestments',
    AllocationCell: '#tblXRay .T_label .td_all_2',
    MAXIMUM_ROWS: 50,
    /*Methods*/
    Storage: function(data) {

        if (data == null) {
            if ($.data(document.body, 'ms_xray') == null) {
                $.data(document.body, 'ms_xray', []);
            }
            return $.data(document.body, 'ms_xray');
        }
        else {
            $.data(document.body, 'ms_xray', data);
        }

    },
    CreateOption: function(embeded) {
        var op = document.createElement('option');
        $.data(op, 'ms_xray_code', embeded.InvestmentCode);
        $.data(op, 'ms_xray_embeded', embeded);
        $(op).attr('ms_xray', embeded.InvestmentCode);
        MS_XRay.Storage(
                $.merge(MS_XRay.Storage(), [embeded])
            );
        $(op).text(embeded.InvestmentName);
        $(op).val(embeded.InvestmentCode);
        return op;
    },

    UpdateCountInvestment: function() {
        var count = 0;
        $.map($('input[ms_xray_allocator=""]'), function(n, i) {
            $(n).attr('tabindex', i + 2);
            count = i + 1;
        });
        $('#tdInvestmentsCounter').text(String.format('Investments ({0})', count));
        $('#dvAllocator,#dvMsgGuid').show(500, function() {
            $('#dvMsgGuid2').hide(500);
        });
    },
    RemoveItem: function(pCode) {

        var removedItems = $(String.format('{0} tr[ms_xray="{1}"]', this.DestinationTable, pCode)).toArray();
        $.map(removedItems, function(n, i) {

            var iEmbeded = $(n).attr('ms_xray_embeded');
            if (typeof iEmbeded == 'string') {
                iEmbeded = $.parseJSON(iEmbeded);
            }
            var iNewOption = MS_XRay.CreateOption(iEmbeded);
            var iExp = '#selectedObjs optgroup[label="' + iEmbeded.InvestmentType + '"]';
            $(iNewOption).appendTo(iExp);
            $(n).remove();

        });



        $('#selectedObjs').trigger('liszt:updated');
        this.CalculateAllocation();
        this.UpdateCountInvestment();
        ToggleActionButtons();

    },
    ClearAllocation: function() {
        $.map($('input[ms_xray]').toArray(), function(n) {

            $(n).val('');

        });
    },
    ClearAll: function() {
        $.map($('tr[ms_xray]').toArray(), function(n) {

            $(n).remove();

        });

    },
    CalculateAllocation: function(obj, args) {

        if (args != null) {
            args = String.fromCharCode(args);
        }

        var sum = 0;
        $.map($('input[ms_xray_allocator=""]').toArray(), function(n) {
            var iVal = $(n).val().trim() == '';
            if (n == obj) {
                iVal += args;
            }
            sum += iVal
                    ? 0
                    : Number.parseInvariant($(n).val().trim());
        });

        if (sum == 100) {
            sum = sum.toFixed(0);
        }
        else {
            //sum = sum.toFixed(2);
        }

        $(this.AllocationCell).html(
                sum != 100
                    ? String.format('<label class="error">{0}% Allocation</label>', sum)
                    : String.format('<label>{0}% Allocation</label>', sum)
            );
    },
    addItem: function(data) {

        var args = arguments;
        data = data == null ? [] : data;

        var loop = null;
        if (args.length == 1) {
            loop = this.getSelectedData(data);
        }
        else {
            loop = $.makeArray(this.getSelectedData());
        }

        $.map(loop, function(n) {
            var iDate = '';
            try {


                if (n == null) {
                    iDate = '';
                }
                else {
                    iDate = eval('new ' + n.PortfolioDate.replace(/(\/)/g, ''));
                    n.PortfolioDate = iDate.getDay() + '/' + iDate.getMonth() + '/' + iDate.getFullYear();
                }


            } catch (e) { }
            if (args.length == 0) {
                data.push(n);
            }
        });

        var trNodes = $('#tmplRowXRay').tmpl(data).filter('tr').toArray();
        var selectedTr = $('#tblXRaytr:not(:eq(0))').length;




        if (trNodes.length + selectedTr <= this.MAXIMUM_ROWS) {
            $.map(trNodes, function(n) {
                var iLookup = '#tblXRay tr[ms_xray="' + $(n).attr('ms_xray') + '"]';
                if ($(iLookup).length == 0) {
                    //Add node to tr
                    var iDestNode = $('#tblXRay tbody tr:eq(0)')[0];
                    $(n).insertAfter(iDestNode);

                    //Remove selected node from option
                    MS_XRay.removeElementInOption(n);
                }
            });
            $('#selectedObjs').trigger('liszt:updated');
        }
        else {
            alert('Maximum numbers of portfolio are ' + this.MAXIMUM_ROWS + ' items.');
        }


        this.UpdateCountInvestment();

        ToggleActionButtons();
    },
    removeElementInOption: function(element) {

        var expRemoved = '#selectedObjs option[ms_xray="' + $(element).attr('ms_xray') + '"]';

        $(expRemoved).remove();
        this.UpdateCountInvestment();
    },
    getSelectedData: function(data) {
        var jsonData = [];
        var args = arguments;
        var selectedObjs = data == null
                ? $('#selectedObjs option:selected').toArray()
                : data;

        $.map(selectedObjs, function(n, i) {
            if (args.length == 1) {
                jsonData.push(n);
            }
            else {
                var iEmbeded = $.data(n, 'ms_xray_embeded');
                jsonData.push(iEmbeded);
            }
        });
        return jsonData;
    },
    loadPortfolioById: function() {
        if ($('#ddlUserXRay').val() == '') {
            $('#btnClearAll').click();
        }
        else {

            $('#queryHidden').val('');
            LoadPortfolioAjax();
        }

    },
    preventExceedPoint: function(obj, arg) {
        var pressKey = arg.which || arg.keyCode; //Fix IE7
        var chDown = String.fromCharCode(pressKey);
        var countExpected = jQuery.grep([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '.'], function(n, i) { return n == chDown }).length;
        var expChecker = /^([0-9]*)((?:\.[0-9]{0,2})?)$/;
        var ms_xray_has_hl = typeof $.data(obj, 'ms_xray_has_hl') !== 'undefined';


        if (pressKey == 13) {
            $.map($('input[ms_xray_allocator=""]').toArray(), function(n) {

                if ($(n).attr('id') == $(obj).attr('id')) {
                    var nextTab = parseInt($(n).attr('tabindex')) + 1;
                    var nextObj = $('input[ms_xray_allocator=""][tabindex=' + nextTab + ']')[0];

                    if (typeof nextObj !== 'undefined') {
                        $.data(obj, 'ms_xray_has_hl', $(nextObj).val());
                        nextObj.select();
                    }
                }

            });

            return true;
        }
        else if (pressKey == 8 || pressKey == 9) {

            return true;
        }
        else if (countExpected == 1) {

            if (ms_xray_has_hl == true) {
                $(obj).val('');
                $.removeData(obj, "ms_xray_has_hl");
            }

            if (expChecker.test($(obj).val() + chDown)) //check combined
            {

                return true;
            }
            else {


                if (/(\.)([0-9]{3,100})/.test($(obj).val() + chDown)) {
                    alert('All Allocations can be up to 2 decimal places only.');
                }

                obj.select();
                $.data(obj, 'ms_xray_has_hl', $(obj).val());
                return false;
            }
        }
        else {
            return false;
        }



    }
}

function ToggleActionButtons() {

    var selectedPortfiolioId = $('#ddlUserXRay').val();
    var showActions = $('#tblXRay tr').length > 1 ? true : false;

    if (showActions == true) {
        $('#divActions').show();
        $('#divClearAll').show();
    }
    else {
        $('#dvAllocator,#dvMsgGuid').hide(500, function() {
            $('#dvMsgGuid2').show(500);
        });
        $('#divActions').hide();
        $('#divClearAll').hide();        
    }

    if (selectedPortfiolioId > 0) {
        $('#div_btnDelete').show();
    }
    else {
        $('#div_btnDelete').hide();

    }
}

function LoadPortfolioAjax() {
    var ddlUserXRay = $('#ddlUserXRay')[0];
    var selected = $(ddlUserXRay).val();
    var query = $('#queryHidden').val();

    if (selected != '' || query != '') {

        $(ddlUserXRay).prop('disabled', true);

        //Load data
        ctxXhr = $.ajax({
            'url': '/PortfolioXray/GetPortfolioXrayItems',
            'type': 'POST',
            'data': { 'id': selected, 'query': query },
            'dataType': 'json',
            'beforeSend': function() {

                $('#dvImportOption').ms_base('swapIcon', 'loading', {
                    'margin-left': '-25px',
                    'margin-top': '6px'
                });

            },
            'success': function(d) {

                //Clear data
                $.map($('a[ms_xray]').toArray(), function(n) {
                    $(n).click();
                });

                //Add items
                MS_XRay.addItem(d);
                $('#dvImportOption').ms_base('swapIcon', '');
                MS_XRay.CalculateAllocation();
                $(ddlUserXRay).prop('disabled', false);
                ToggleActionButtons();
            },
            'error': function() {
                $($('#dvImportOption')).ms_base('swapIcon', 'error', {});
            }
        });

    }

}






$(function() {




    function getSelectedXrayName() {
        return $.map($(ddlUserXRay).find(':selected').toArray(),function(f) {
            return $(f).val() == '' ? '' : $(f).text()
        })[0];
    }

    function deletePortfolio(portfolioId) {



        $('#div_btnDelete').ms_base('swapIcon', 'loading', {});
        $.post('/PortfolioXray/DeletePortfolio', { 'portfolioId': portfolioId }, function(d) {

            var rs = d === 'True';
            if (rs) {
                $(ddlUserXRay).find('option[value="' + portfolioId + '"]').remove();
                $('.ctx_ul').find('li[val="' + portfolioId + '"]').remove();
                $(ddlUserXRay).val('')
                                  .prop('disabled', false);
                $(btnClearAll).click();

            }
            else {
                alert('Ooops!!! An error was occured. \r\nPlease try again later.');
            }
            $('#div_btnDelete').ms_base('swapIcon', '');
        });

    }

    $(btnDelete).click(function() {

        var selectedVal = $(ddlUserXRay).val();
        if (selectedVal == '') {

            alert('Please select portfolio to be deleted.');

        }
        else {

            if (confirm('Are you sure you want to delete this portfolio?')) {
                deletePortfolio(selectedVal);
            }

        }


    });


    function enhanceExport(eleExport) {

        $.getScript('/Scripts/CSV/table2CSV.js', function() {

            var counter = 0;
            $(eleExport).click(function() {

                counter++;
                var cData = $('#tblXRay tr:gt(0)').toArray();
                var myTable = $('#tblTmpTmp')[0];
                $(myTable).find('tr:gt(0)').remove();
                if (!cData) {
                    cData = [];
                }

                $.map(cData, function(n) {

                    var d = eval('(' + $(n).attr('ms_xray_embeded') + ')');
                    var iAllocate = $(n).find('input[type="text"]:eq(0)').val();
                    var iTr = $('<tr></tr>')
                                .append(
                                    $('<td>').text(d.InvestmentCode)
                                )
                                .append(
                                    $('<td>').text(d.InvestmentName.trim().replace(/(\()(.*)(\))$/g, ''))
                                )
                                .append(
                                    $('<td>').text(iAllocate)
                                );

                    $(myTable).append(iTr);

                });



                var csvValue = $(myTable).table2CSV({
                    delivery: 'value',
                    header: ['Tickers/ASX Code', 'Investment Name', '% Amount']

                });
                $('#csv_text').val(csvValue);

                $('#frmCSV').submit();

            });


        });



    }


    enhanceExport($('#btnExport')[0]);


    function showDialog() {

    }


    $(btnPopPortfolioSave).click(function() {
        var cItem = $('input[ms_xray_allocator=""]').toArray();
        try {
            var sum = 0;
            $.map(cItem, function(n) {
                if (isNaN(parseFloat($(n).val()))) {
                    throw "The asset allocation cannot be blank. Please add an allocation or delete the investment from the list.";
                }
                else {
                    sum += parseFloat($(n).val());
                }
            });


            if (sum != 100) {
                throw "The total asset allocation does not equal to 100%. Please amend.";
            }

            //Prepare control



            $(txtPortfolioName).val(getSelectedXrayName());
            //Pop a dailog
            $(dialogFormXray).dialog({
                modal: true, //$(this).dialog("close")
                minWidth: 390
            });
            $(btnPortfolioSave).ms_base('swapIcon', '', {});


        } catch (ex) {
            alert(ex);
        }


    });

    //Close
    $(btnPortfolioCancel).click(function() {

        $(dialogFormXray).dialog('close');

    });

    $(btnImport).click(function() {

        $('#queryHidden').val('');
        LoadPortfolioAjax();

    });



    $(btnPortfolioSave).click(function() {

        var changedName = false;
        var nameIsExists = false;
        var allowOperation = false;

        $(ddlUserXRay).find('option').filter(function(i, o) {
            if ($(o).text() == $(txtPortfolioName).val()) {
                nameIsExists = true;
            }
        });

        if (getSelectedXrayName() == $(txtPortfolioName).val()) {
            changedName = true;
        }

        if (nameIsExists && changedName) {
            allowOperation = confirm('Are you sure you want to overwrite the existing portfolio?');
        }
        else {
            allowOperation = true;
        }


        //Not allowed to be operated.
        if (!allowOperation) {
            return;
        }


        var jqxhr = $.ajax({
            'url': '/PortfolioXray/SaveXRay',
            'type': 'POST',
            'data': $.toDictionary({
                'name': $('#txtPortfolioName').val(),
                'items': (function() {
                    var cItem = [];
                    $.map($('input[ms_xray_allocator=""]').toArray(), function(n) {
                        var iItem = {
                            'Code': $(n).attr('ms_xray'),
                            'Ratio': $(n).val()
                        };
                        cItem.push(iItem);
                    });
                    return cItem;
                })()
            }),
            'dataType': 'json',
            'beforeSend': function() {
                $(btnPortfolioSave).ms_base('swapIcon', 'loading', {});
            },
            'success': function(d) {
                $(btnPortfolioSave).ms_base('swapIcon', '', {});
                $(dialogFormXray).dialog('close');
                var elemOption = $('<option />').attr({ 'value': d.XRayId });
                elemOption.text(d.Name);
                var overwrite = nameIsExists && changedName && allowOperation;
                if (!overwrite) {
                    $(ddlUserXRay).append(elemOption).val(d.XRayId);
                }
                ToggleActionButtons();

            },
            'error': function(d) {
                $(btnPortfolioSave).ms_base('swapIcon', 'error', {});
            }
        });


    });


    window.location.pathname.split('/')[window.location.pathname.lastIndexOf('/')]

});


$(document).ready(function() {

    try {
        var querystringId = $.query.get('id');
        var query = $.query.get('query');

        if (querystringId) {
            $('#ddlUserXRay').val(querystringId);
        }

        if (querystringId || query) {
            LoadPortfolioAjax();
        }
        //Fixed FF3
        if (querystringId == '') {
            $('#ddlUserXRay').val('');
        }

        $('#ddlUserXRay').change(MS_XRay.loadPortfolioById);
        ToggleActionButtons();

    } catch (e) { }

});