/* 
library required 
jquery-1.x.x.js      : general jquery librabry
jquery-pagination.js : to print pagination
jquery-ba-bbq.js     : to get/set URL parameter
*/

/* Very simple Js Object for holding pag data */

var PaginationDataHolder = {};
// data array
PaginationDataHolder.data = [];


// Research Reports
PaginationDataHolder.data['RR'] = { PageSize: '25', CurrentPage: 1, TotalRows: 0, SiteSection: 'RR', URL: '/Fund/GetData?cnt=25&section=RR&sub=&hist=&rp=1' };


//Research Report Recommendation

PaginationDataHolder.data['RR_Rec'] = { PageSize: '3', CurrentPage: 1, TotalRows: 0, SiteSection: 'RR', URL: '/Fund/GetData?cnt=3&section=RR&sub=2&hist=&rp=1' };

// Document Library
PaginationDataHolder.data['DL'] = { PageSize: '50', CurrentPage: 1, TotalRows: 0, SiteSection: 'DL', URL: '/Fund/GetData?cnt=4&section=RR&sub=2&hist=&rp=1' };


// Approved Product List
PaginationDataHolder.data['APL'] = { PageSize: '50', CurrentPage: 1, TotalRows: 0, SiteSection: 'APL', URL: '/Fund/GetData?cnt=50&section=APL&sub=APL1&hist=&rp=1' };


// Fund Returns
PaginationDataHolder.data['FR'] = { PageSize: '50', CurrentPage: 1, TotalRows: 0, SiteSection: 'FR', URL: '/Fund/GetFundReturnData?cnt=50&section=FR&sub=&hist=&rp=1' };


// Market Review
PaginationDataHolder.data['MR'] = { PageSize: '5', CurrentPage: 1, TotalRows: 0, SiteSection: 'MR', URL: '/Fund/GetData?cnt=4&section=MR&sub=&hist=&rp=1' };


// Search Result
PaginationDataHolder.data['MP'] = { PageSize: '4', CurrentPage: 1, TotalRows: 0, SiteSection: 'MP', URL: '/Fund/GetData?cnt=4&section=MP&sub=&hist=&rp=1' };



// Search Result
PaginationDataHolder.data['SR'] = { PageSize: '50', CurrentPage: 1, TotalRows: 0, SiteSection: 'SR', URL: '/General/GetResult?rp=1cnt=50&sortname=&sortorder=&query=&querytype=' };


//page_index starting from 0, so we need to add it up by 1 before display
function setPaginationPage(page_index) {
    var url = paginationHolder.URL;
    // alert(page_index + ' '+ url);
    paginationHolder.CurrentPage = parseInt(page_index) + 1;
    paginationHolder.URL = $.param.querystring(url, 'rp=' + paginationHolder.CurrentPage);

    GetData(window.section, window.sub, window.tbl, window.template, window.option, window.noRecs);
}

function setPaginationRowSize(size) {
    paginationHolder.PageSize = size;
    paginationHolder.CurrentPage = 1;
    var url = paginationHolder.URL;
    url = $.param.querystring(url, 'cnt=' + size);
    url = $.param.querystring(url, 'rp=1');
    // alert(size + ' '+ url);
    paginationHolder.URL = url;
    GetData(window.section, window.sub, window.tbl, window.template, window.option, window.noRecs);
}

//currentpage start from page 0
jQuery.fn.printresult = function(total_rows, opts) {
    opts = jQuery.extend({
        items_per_page: 10,
        current_page: 0,
        size_options: [ '25', '50', '100', 'All'],
        size_values: [ 25, 50, 100, 1000]
    }, opts || {});


    return this.each(function() {
        var panel = jQuery(this);
        var rows = opts.items_per_page;
        var start = parseInt(opts.current_page) * parseInt(opts.items_per_page);
        var allResult = total_rows;
        panel.empty();
        panel.append('<td class="displayLabel">Display:&nbsp;</td>');
        panel.append('<td class="displayResultText">' + (parseInt(start) + 1) + ' - ' + Math.min(parseInt(start) + parseInt(rows), parseInt(allResult)) + ' out of ' + allResult + '</td>');
        panel.append('<td class="displayLabel">|&nbsp;&nbsp;Results per page:&nbsp;</td>');
        var j = opts.size_values.length;
        for (var i = 0; i < j; i++) {
            if (rows == opts.size_values[i]) {
                panel.append('<td class="displayText">' + opts.size_options[i] + '</td>');
            } else {
                panel.append('<td><a href="#" onClick="javascript:setPaginationRowSize(' + opts.size_values[i] + ');">' + opts.size_options[i] + '</a></td>');
            }
        }

//        if (rows == '25') {
//            panel.append('<td class="displayText">25</td>');
//        } else {
//            panel.append('<td><a href="#" onClick="javascript:setPaginationRowSize(25);">25</a></td>');
//        }
//        if (rows == '50') {
//            panel.append('<td class="displayText">50</td>');
//        } else {
//            panel.append('<td><a href="#" onClick="javascript:setPaginationRowSize(50);">50</a></td>');
//        }
//        if (rows == '100') {
//            panel.append('<td class="displayText">100</td>');
//        } else {
//            panel.append('<td><a href="#" onClick="javascript:setPaginationRowSize(100);">100</a></td>');
//        }
//        if (rows == '1000') {
//            panel.append('<td class="displayText">All</td>');
//        } else {
//            panel.append('<td><a href="#" onClick="javascript:setPaginationRowSize(1000);">All</a></td>');
//        }

    });
}