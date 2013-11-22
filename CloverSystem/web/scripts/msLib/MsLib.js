/// <reference name="MicrosoftAjax.js"/>
var MS_UTILITIES = {
    //DATA: { CHECKBOX_GROUP: [/*Must contain this object {groupname,checkbox element}*/] },
    registerGroupCheckbox: function(pParentElement, pChildElement) {
        var childElements = $(pParentElement).data('ms_toggle_checkbox');
        childElements = childElements == null ? [] : childElements;
        childElements.push(pChildElement);
        $(pParentElement).data('ms_toggle_checkbox', childElements);

    },
    toggleGroupCheckbox: function(pElement) {
        var boxes = $(pElement).data('ms_toggle_checkbox');
        $(boxes).attr('checked',
            boxes.length == $(boxes).filter(':checked').length
            ? false
            : true
        );
    },
    join: function(pArray, pJoiner) {//Obsoleted
        var output = '';
        $.map(pArray, function(n, i) {
            output += (i != 0 ? pJoiner : '') + n;
        });
        return output;
    },
    compatibleCallback: function(pObjs) {
        var isComapat = 0;
        for (i in pObjs) {
            var iBrowsers = pObjs[i].broswers;
            var iCallback = pObjs[i].callback;
            for (j in pObjs) {
                isComapat = eval('$.browser.' + iBrowsers) != undefined ? 1 : 0;
                if (isComapat == 1) { iCallback(); break; }
            }
            if (isComapat == 1) break;
        }
    },
    parseDate: function(date) {
        var re = /-?\d+/;
        var m = re.exec(date);
        var d = new Date(parseInt(m[0]));
        return d;
    }

}


var MS_TEST = {
    label: 'mstar test -> ',
    test: function(pFlag) {
        if (arguments.length == 0) {//get
            return $.data(document.body, 'mstar_testable');
        }
        else if (arguments.length == 1) {//set
            $.data(document.body, 'mstar_testable', pFlag);
        }
        else {
            throw 'Unexpected parameter!';
        }
    },
    assert: function(pExp, pObject, pShowCallee) {
        if (MS_TEST.test() == 1) {
            Sys.Debug.assert(pExp, MS_TEST.label, pShowCallee);
        }
    },
    trace: function(pText) {
        Sys.Debug.trace(MS_TEST + pText);
    },
    traceDump: function(pObject,pName) {
        Sys.Debug.traceDump(pObject,MS_TEST + pName);
    }
}

//(function($) {
//    var 
//    
//    
//    
//    .test = function(pFlag) {
//        if (arguments.length == 0) {//get
//            return $.data('body', 'mstar_testable');
//        }
//        else if (arguments.length == 1) {//set
//            $.data('body', 'mstar_testable', pFlag);
//        }
//        else {
//            throw 'Unexpected parameter!';
//        }
//    }
//    $.fn.mstar.assert = function(pExp, pObject) {
//        if ($.test() == 1)
//            console.assert(pExp, pObject);
//    }
//})(jQuery);


var MS_SCRIPT_MANAGER = {
    wait: function(pId) {
        var elem = $('#' + pId)[0];
        $(elem).hide();
        $(elem).before(String.format('<img id="tmpImg{0}" src="/content/images/loading.gif" style="width:16px;" />', pId));
    },
    release: function(pId) {
        var elem = $('#' + pId)[0];
        $(String.format('#tmpImg{0}', pId)).remove();
        $(elem).fadeIn();
    }
}


 /*Created by wthatar*/
 var MS_PREFERENCE = {

     /*==================================*/
     MS_URL: {
         'FAVORITE_ADD': '/Favorite/AddFavoriteCode',
         'FAVORITE_GET_PREFERENCES': '/Favorite/GetPreferences',
         'FAVORITE_REMOVE': '/Favorite/RemoveFavoriteCode',
         'FAVORITE_UPDATE': '/Favorite/UpdateFavorite'
         /*FAVORITE_CATEGORY*/

     },

     /*==================================*/
     MS_FAVORITE_CATEGORY: {
         'STOCK_NEWS': 'STOCK_NEWS',
         'STOCK_MKTSENSITIVE': 'STOCK_MKTSENSITIVE',
         'STOCK_VIDEO': 'STOCK_VIDEO',
         'STOCK_COMMENTARY': 'STOCK_COMMENTARY',
         'STOCK_RESEARCH': 'STOCK_RESEARCH',
         'STOCK_MODELS': 'STOCK_MODELS',
         'FUND_VIDEO': 'FUND_VIDEO',
         'FUND_COMMENTARY': 'FUND_COMMENTARY',
         'FUND_RESEARCH': 'FUND_RESEARCH',
         'FUND_MODELS': 'FUND_MODELS',
         'ETF_VIDEO': 'ETF_VIDEO',
         'ETF_COMMENTARY': 'ETF_COMMENTARY',
         'ETF_RESEARCH': 'ETF_RESEARCH',
         'LIC_VIDEO': 'LIC_VIDEO',
         'LIC_COMMENTARY': 'LIC_COMMENTARY',
         'LIC_RESEARCH': 'LIC_RESEARCH'
     }
    , /*==================================*/
     FavoriteRequestObject: { PreferenceCategoryName: null, Value: null, FavoriteRequestType: null, RefElementId: null }

     //    , /*=============For using with MS_PREFERENCE.MS_DATA.WATCHER_LIST =====================*/
     //     WatchObject: { Id: null, ElementObj: null }
    , /*==================================*/
     EXCECUTE_QUEUE: []

    , /*==================================*/
     MS_DATA: {
         'PREFERENCE': null,
         'REGISTER_OBJECTS': [],
         'WAIT_LIST': []/*{ Id: null, CodeBatch: null }*/
     }
    , /*============To load and startup preference and other jobs.======================*/
     startUp: function(pPreferenceData) {


         if (!pPreferenceData) {
             MS_PREFERENCE.loadFavoriteList();
         }
         else {
             MS_PREFERENCE.loadFavoriteList(pPreferenceData);
         }

         /*Load favorite list*/


         /*Once finished loading preference then load feed.*/
         //         var loopCheck = setInterval(function() {

         //             if (MS_PREFERENCE.MS_DATA.PREFERENCE != null) {
         //                 clearInterval(loopCheck);

         //                 if (pCallback != undefined) {
         //                     pCallback();
         //                 }
         //                 $.map(MS_PREFERENCE.MS_DATA.REGISTER_OBJECTS, function(n) { eval(n); });

         //             }

         //         }, 500);
     }

    , /*==================================*/
     fourceRenderBoundObject: function() {
         //         $.map(MS_PREFERENCE.MS_DATA.REGISTER_OBJECTS, function(n) {
         //             eval(n);
         //         });
     }
    , /*==================================*/
     execBulkRequestFavorite: function(pFavoriteObjects) {

         var p = {
             async: false,
             type: 'POST',
             data: { favoriteCollectionRequested: pFavoriteObjects },
             complete: function(d) { },
             error: function(d) { throw d; },
             cache: false
         };

         try {
             p.success = function(d) {

                 //Remove loader.


                 $.map(pFavoriteObjects, function(n) {

                     if (n.FavoriteRequestType == 0) {
                         Array.add(MS_PREFERENCE.MS_DATA.PREFERENCE, { Name: n.PreferenceCategoryName, Value: n.Value })
                         MS_PREFERENCE.assingStatus(document.getElementById(RefElementId), 1);
                     }
                     else if (n.FavoriteRequestType == 1) {
                         var removeCollection = new Array();
                         for (i in MS_PREFERENCE.MS_DATA.PREFERENCE) {
                             if (MS_PREFERENCE.MS_DATA.PREFERENCE[i].Name == n.PreferenceCategoryName && MS_PREFERENCE.MS_DATA.PREFERENCE[i].Value == n.Value) {
                                 removeCollection.push(MS_PREFERENCE.MS_DATA.PREFERENCE[i]);
                             }
                         }

                         $.map(removeCollection, function(n, i) {
                             Array.remove(MS_PREFERENCE.MS_DATA.PREFERENCE, n);
                         });

                         MS_PREFERENCE.assingStatus(document.getElementById(RefElementId), 0);
                     }
                     else {
                         throw 'Unexpected FavoriteRequestType ' + n.FavoriteRequestType;
                     }

                 });


             };
             $.ajax(MS_PREFERENCE.MS_URL.FAVORITE_UPDATE, p);
         }
         catch (e) {
             console.error('Occured an error while bulk update favorite list! -> %s', e);
         }


     }
     , /*==================================*/


     execRequestFavorite: function(pCmd, pCode, pCategory, pElement) {


         var p = {
             async: false,
             type: 'POST',
             data: { 'preferenceCategoryName': pCategory, 'code': pCode },
             complete: function(d) { },
             error: function(d) { throw d; },
             cache: false
         };

         if (pCmd == MS_PREFERENCE.MS_URL.FAVORITE_ADD) {
             try {
                 p.success = function(d) {
                     Array.add(MS_PREFERENCE.MS_DATA.PREFERENCE, { Name: pCategory, Value: pCode })

                     $.map($('a[class="MyResearchDown"],a[class="MyResearch"]').toArray(), function(n) {

                         try {
                             if ($.data(n, 'ms_preference').Name == pCategory && $.data(n, 'ms_preference').Value == pCode) {
                                 MS_PREFERENCE.assingStatus(n, 1);
                                 //MS_PREFERENCE.assingStatus(pElement, 1);
                             }
                         } catch (e) { }

                     });

                 };
                 $.ajax(pCmd, p);
             }
             catch (e) {
                 console.error('Occured an error while add favorite list! -> %s', e);
             }
         }
         else if (pCmd == MS_PREFERENCE.MS_URL.FAVORITE_REMOVE) {
             try {
                 p.success = function(d) {
                     var removeCollection = new Array();
                     for (i in MS_PREFERENCE.MS_DATA.PREFERENCE) {
                         if (MS_PREFERENCE.MS_DATA.PREFERENCE[i].Name == pCategory && MS_PREFERENCE.MS_DATA.PREFERENCE[i].Value == pCode) {
                             removeCollection.push(MS_PREFERENCE.MS_DATA.PREFERENCE[i]);
                         }
                     }

                     $.map(removeCollection, function(n, i) {
                         Array.remove(MS_PREFERENCE.MS_DATA.PREFERENCE, n);
                     });

                     $.map($('a[class="MyResearchDown"],a[class="MyResearch"]').toArray(), function(n) {
                         try {
                             if ($.data(n, 'ms_preference').Name == pCategory && $.data(n, 'ms_preference').Value == pCode) {
                                 MS_PREFERENCE.assingStatus(n, 0);
                                 //MS_PREFERENCE.assingStatus(pElement, 0);
                             }
                         } catch (e) { }
                     });




                 }
                 $.ajax(pCmd, p);
             }
             catch (e) {
                 console.error('Occured an error while add favorite list! -> %s', e);
             }
         }
         else {
             throw 'Not support command ' + pCmd;
         }

     }


    , /*==================================*/

     assingStatus: function(pElement, pStatus) {

         $(pElement).html('');
         //Check type
         if ($(pElement).attr('type') == 'checkbox') {

             //Assign checked status.
             if (pStatus == 1) {
                 $(pElement).attr('checked', true);
             }
             else if (pStatus == 0) {
                 $(pElement).attr('checked', false);
             }
             else {
                 throw ('Unexpected status = ' + pStatus);
             }
         }
         else if (pElement.nodeName.toLowerCase() == 'a') {

             //Assign checked status.
             if (pStatus == 1) {
                 $(pElement).addClass('MyResearchDown');
                 $(pElement).removeClass('MyResearch');
             }
             else if (pStatus == 0) {
                 $(pElement).addClass('MyResearch');
                 $(pElement).removeClass('MyResearchDown');
             }
             else {
                 throw ('Unexpected status = ' + pStatus);
             }
         }
         else {
             throw ('Not support binding favorite with type = ' + type);
         }

     }



    , /*==================================*/
     excecuteNextQueue: function() {

         for (i in MS_PREFERENCE.EXCECUTE_QUEUE) {
             eval(MS_PREFERENCE.EXCECUTE_QUEUE[i]);
             Array.removeAt(MS_PREFERENCE.EXCECUTE_QUEUE, i);
             break;
         }

     }


    , /*==================================*/
     addFavorite: function(pCode, pCategory) {
         var code = String.format('MS_PREFERENCE.execRequestFavorite(MS_PREFERENCE.MS_URL.FAVORITE_ADD,"{0}","{1}");', pCode, pCategory);
         Array.add(MS_PREFERENCE.EXCECUTE_QUEUE, code);

     }


    , /*==================================*/
     removeFavorite: function(pCode, pCategory) {

         var code = String.format('MS_PREFERENCE.execRequestFavorite(MS_PREFERENCE.MS_URL.FAVORITE_REMOVE,"{0}","{1}");', pCode, pCategory);
         Array.add(MS_PREFERENCE.EXCECUTE_QUEUE, code);

     }





    , /*==================================*/
     refreshStatus: function() {

         var cPref = $.data('*', 'ms_preference');

         try {
             for (i in cPref) {
                 try {
                     MS_PREFERENCE.updateFavoriteStatus(cPref[i]);
                 }
                 catch (x) {
                     Sys.Debug.trace(x);
                 }
             }
         }
         catch (x2) {

         }


     }


    , /*==================================*/
     watchId: -1
    , /*==================================*/
     watcher: function() {

         //Excecute waiting code.
         var countComplete = 0;
         for (i in MS_PREFERENCE.MS_DATA.WAIT_LIST) {
             var n = MS_PREFERENCE.MS_DATA.WAIT_LIST[i];
             try {

                 eval(n);
                 countComplete++;
             }
             catch (e2) { /*Sys.Debug.traceDump(e2, 'dump2error');*/ }
         }


         if (MS_PREFERENCE.MS_DATA.WAIT_LIST.length == countComplete) {
             if (MS_PREFERENCE.watchId != -1) {
                 clearInterval(MS_PREFERENCE.watchId);
                 MS_PREFERENCE.watchId = -1;
             }
         }

     }
    , /*==================================*/
     loadFavoriteList: function(pPreference) {

         try {

             var p = {
                 url: MS_PREFERENCE.MS_URL.FAVORITE_GET_PREFERENCES,
                 success: function(d) {

                     //Assign status
                     MS_PREFERENCE.MS_DATA.PREFERENCE = d;
                     MS_PREFERENCE.refreshStatus();
                     MS_PREFERENCE.watchId = setInterval(MS_PREFERENCE.watcher, 500);

                 }
             };
             if (!pPreference) {
                 $.ajax(p);
             }
             else {
                 MS_PREFERENCE.MS_DATA.PREFERENCE = pPreference;
                 MS_PREFERENCE.refreshStatus();
                 MS_PREFERENCE.watcher();
             }


         }
         catch (e) {
             console.error('Occured an error while load favorite list! -> %s', e);
         }

     }



     , /*==================================*/
     getPreference: function(pPreferenceName) {
         var returned = null;
         try {
             for (i in MS_PREFERENCE.MS_DATA.PREFERENCE) {
                 var iPREFERENCE = MS_PREFERENCE.MS_DATA.PREFERENCE[i];
                 if (iPREFERENCE.Name == pPreferenceName) {
                     returned = pPreferenceValue;
                     break;
                 }
             }
             if (returned == null) {
                 throw 'No preference ' + pPreferenceName + ' were found!';
             }
         }
         catch (e) {
             console.error(e);
         }
         return returned;
     }


     , /*==================================*/
     isPreferenceExist: function(pPreferenceName, pPreferenceValue) {

         //Sys.Debug.trace("isPreferenceExist('" + pPreferenceName + "', '" + pPreferenceValue + "')");
         try {
             for (i in MS_PREFERENCE.MS_DATA.PREFERENCE) {
                 var iPREFERENCE = MS_PREFERENCE.MS_DATA.PREFERENCE[i];
                 if (iPREFERENCE.Name == pPreferenceName && iPREFERENCE.Value == pPreferenceValue) {
                     return true;
                 }

             }

         }
         catch (e) {
             console.error(e);
         }

         /*debug*/
         //Sys.Debug.trace('\tAn item was not found!');
         //Sys.Debug.trace('\tName = ' + pPreferenceName);
         //Sys.Debug.trace('\tValue = ' + pPreferenceValue);

         return false;
     }



    , /*==================================*/
     getPreferenceCollection: function(pPreferenceName, pPreferenceValue) {
         var returned = new Array();
         try {
             $.map(MS_PREFERENCE.MS_DATA.PREFERENCE, function(n, i) {
                 returned.push(n);
             });
             returned.reverse();
         }
         catch (e) {
             console.error('Occured an error while search favorite list! -> %s', e);
         }
         return returned;
     }


    , /*==================================*/
     bindFavorite: function() {

         //    //1. Load favorite.
         //    loadFavoriteList();

         //    //2. Iterate entire elements that have property 'ms_preference' and assined preference data.
         //    $.map($("input[type='checkbox'][ms_preference]"), function(n, i) {
         //        var iPreferenceName = $.data(n,'Name');
         //        if ($(n).Name )

         //    });



     }

     //, /*==================================*/

    , /*==================================*/
     LOADER: '<img src="/content/images/loading.gif" style="width:12px; margin-right:5px;"></img>'
    , /*==================================*/
     bindPreference: function(pElement, pName, pValue, pObject) {



         //Check data before binding
         if (MS_PREFERENCE.MS_DATA.PREFERENCE == null) {

             //Add load icon.
             $(pElement).html(MS_PREFERENCE.LOADER);


             var codeBatch = String.format('$(document.getElementById("{0}")).ready(function(){{MS_PREFERENCE.bindPreference(document.getElementById("{0}"), "{1}", "{2}", null);}}); '
                , pElement.id
                , pName
                , pValue);

             //Sys.Debug.traceDump(codeBatch, 'Code Batch');
             MS_PREFERENCE.MS_DATA.WAIT_LIST.push(codeBatch);

         }
         else {
             //If contain '.' then behave it as class.
             if (pElement.toString().indexOf('.') > -1) {
                 $.data($(pElement), 'ms_preference', { Name: pName, Value: pValue, EmbededObject: pObject });
                 $.map($(pElement), function(n) {
                     MS_PREFERENCE.updateFavoriteStatus(n);
                 });
             }
             else { //Behave as element.
                 $.data(pElement, 'ms_preference', { Name: pName, Value: pValue, EmbededObject: pObject });
                 MS_PREFERENCE.updateFavoriteStatus(pElement);
             }



             /*debug*/
             //Sys.Debug.trace('bindPreference');
             //Sys.Debug.trace('\tElementId = ' + $(pElement).attr('id'));
             //Sys.Debug.trace('\tName = ' + $.data(pElement, 'ms_preference').Name);
             //Sys.Debug.trace('\tValue = ' + $.data(pElement, 'ms_preference').Value);
             //Sys.Debug.trace('\tEmbededObject = ' + $.data(pElement, 'ms_preference').EmbededObject);
         }

     }
    , /*==================================*/
     updateFavoriteStatus: function(pElement) {

         var type = $(pElement).attr('type');


         try {

             //Checkb ms_preference
             if ($.data(pElement, 'ms_preference') == null) {
                 throw ('A property ms_preference were not found!');
             }

             var name = $.data(pElement, 'ms_preference').Name;
             var value = $.data(pElement, 'ms_preference').Value;

             //alert(value);

             value = value.split(',')[0];

             //alert(value);

             //Check type
             if (type == 'checkbox') {

                 //Assign checked status.
                 if (MS_PREFERENCE.isPreferenceExist(name, value) === true) {
                     MS_PREFERENCE.assingStatus(pElement, 1);
                 }
                 else {
                     MS_PREFERENCE.assingStatus(pElement, 0);
                 }

                 /*debug*/
                 //Sys.Debug.trace('updateFavoriteStatus(' + "'" + pElement + "'" + ')');
                 //Sys.Debug.trace('\tname = ' + name);
                 //Sys.Debug.trace('\tvalue = ' + value);
             }
             else if (pElement.nodeName.toLowerCase() == 'a') {

                 //Assign checked status.
                 try {
                     //Remove loading icon.    

                     if (MS_PREFERENCE.isPreferenceExist(name, value) === true) {
                         MS_PREFERENCE.assingStatus(pElement, 1);
                     }
                     else {
                         MS_PREFERENCE.assingStatus(pElement, 0);
                     }
                 } catch (e) {
                     MS_PREFERENCE.assingStatus(pElement, 0);
                 }
             }
             else {
                 throw ('Not support binding favorite with type = ' + type);
             }

         }
         catch (e) {
             console.error(e);
         }

     }



    , /*==================================*/
     confirmSave: function(pConfirmType) {
         return true;
         //         if (pConfirmType == 1) {
         //             return confirm('Do you want to add this item to favorite list?');
         //         }
         //         else if (pConfirmType == 0) {
         //             return confirm('Do you want to remove this item from favorite list?');
         //         }
         //         else {
         //             throw 'Unexpected confirm type = ' + pConfirmType;
         //         }

     }

    , /*==================================*/
     ///Required -> pElement
     ///Optional -> 
     updatePreferenceToDb: function(pElement, pBulk) {


         /*debug*/
         //Sys.Debug.trace(arguments); 

         var type = $(pElement).attr('type');
         try {

             //Checkb ms_preference
             if ($.data(pElement, 'ms_preference') == null) {
                 throw ('A property ms_preference were not found!');
             }

             var name = $.data(pElement, 'ms_preference').Name;
             var value = $.data(pElement, 'ms_preference').Value;
             var action = null;

             var reqWithCode = MS_PREFERENCE.FavoriteRequestObject;
             var reqWithEmbeded = MS_PREFERENCE.FavoriteRequestObject;

             reqWithCode.PreferenceCategoryName = name;
             reqWithCode.Value = value;
             reqWithCode.RefElementId = $(pElement).attr('id');

             reqWithEmbeded.PreferenceCategoryName = 'ARCHIVE_' + name;
             reqWithEmbeded.Value = $.data(pElement, 'ms_preference');
             reqWithEmbeded.RefElementId = $(pElement).attr('id');

             //Check type
             if (type == 'checkbox') {

                 if ($(pElement).is(':checked') === true) {
                     action = MS_PREFERENCE.MS_URL.FAVORITE_ADD;
                     reqWithEmbeded.FavoriteRequestType = reqWithCode.FavoriteRequestType = 0;
                     if (MS_PREFERENCE.confirmSave(1) === false) { return false; }
                 }
                 else {
                     action = MS_PREFERENCE.MS_URL.FAVORITE_REMOVE;
                     reqWithEmbeded.FavoriteRequestType = reqWithCode.FavoriteRequestType = 2;
                     if (MS_PREFERENCE.confirmSave(0) === false) { return false; }
                 }


                 //Call server.
                 if (arguments.length == 2) {
                     MS_PREFERENCE.execBulkRequestFavorite([reqWithCode, reqWithEmbeded]);
                 }
                 else {
                     MS_PREFERENCE.execRequestFavorite(action, value, name, pElement);
                 }



             }
             else if (pElement.nodeName.toLowerCase() == 'a') {
                 $(pElement).html(MS_PREFERENCE.LOADER);
                 if ($(pElement).is('.MyResearchDown') === false) {
                     action = MS_PREFERENCE.MS_URL.FAVORITE_ADD;
                     reqWithEmbeded.FavoriteRequestType = reqWithCode.FavoriteRequestType = 0;
                     if (MS_PREFERENCE.confirmSave(1) === false) { return false; }
                 }
                 else {
                     action = MS_PREFERENCE.MS_URL.FAVORITE_REMOVE;
                     reqWithEmbeded.FavoriteRequestType = reqWithCode.FavoriteRequestType = 2;
                     if (MS_PREFERENCE.confirmSave(0) === false) { return false; }
                 }

                 //Call server.
                 if (arguments.length == 2) {
                     MS_PREFERENCE.execBulkRequestFavorite([reqWithCode, reqWithEmbeded]);
                 }
                 else {
                     MS_PREFERENCE.execRequestFavorite(action, value, name, pElement);
                 }




             }
             else {
                 throw ('Not support binding favorite with type = ' + type);
             }

         }
         catch (e) {
             console.error(e);
         }

         return false;

     }



}/*end*/