/// <reference name="MicrosoftAjax.js"/>
var MS_DOWNLOAD = {
    _checkboxsQuery: 'input[name="download"][type="checkbox"]',
    _currentCheckboxs: function(pCheckboxQuery) {
        if (arguments.length == 0) {
            return $(MS_DOWNLOAD._checkboxsQuery).toArray();
        }
        else if (arguments.length == 1) {
            MS_DOWNLOAD._checkboxsQuery = pCheckboxQuery;
        }
    },
    _data: function(pId, pObj) {

        var cKey = $.data(document.body, 'ms_download_key');
        if (arguments.length == 1)//Do get
        {
            var keyIndex = -1;
            if (cKey == null) cKey = [];
            for (i in cKey) {
                if (pId == cKey[i].Id) {
                    keyIndex = i;
                    break;
                }
            }
            if (keyIndex == -1) {
                throw 'Your specific key could not be found!';
            }
            else {
                return cKey[keyIndex];
            }
        }
        else if (arguments.length == 2)//Do set
        {
            var objSet = null;
            if (cKey == undefined) {
                $.data(document.body, 'ms_download_key', [pObj]);
            }
            else {
                var isContain = 0;
                $.map(cKey, function(n, i) {
                    if (n.Id == pObj.Id) {
                        cKey[i] = pObj;
                        isContain = 1;
                    }
                });
                if (isContain == 0) {
                    //Array.add(cKey, pObj);
                    cKey.push(pObj);
                }
                $.data(document.body, 'ms_download_key', cKey);
            }
        }
        else if (arguments.length == 0) {//Get all
            return cKey;
        }
        else {
            throw 'Unexpected argrument length!';
        }

    },
    _current: function(pGroup, pId) {

        if (arguments.length == 2) {//Set
            $.map(MS_DOWNLOAD._data(), function(n) {

                if (n.Group == pGroup) {
                    if (n.Id != pId) {
                        n._Current = 0;
                        $('#' + n.Id).css('background-color', '');
                    }
                    else {
                        n._Current = 1;
                        n._Codes = [];
                        $('#' + n.Id).css('background-color', '#E4F0FF');
                        /*no need to do with 'all'.*/
                        if (n.Type == 'favorite') {
                            $.map(MS_PREFERENCE.MS_DATA.PREFERENCE, function(o) {
                                //Array.add(n._Codes, o.Value);
                                n._Codes.push(o.Value);
                            });
                        }
                        else if (n.Type == 'current') {
                            $.map(MS_DOWNLOAD._currentCheckboxs(), function(o) {
                                //Array.add(n._Codes, $(o).val());
                                n._Codes.push($(o).val());
                            });
                        }
                    }
                    MS_DOWNLOAD._data(pId, n);

                }
            });
            MS_DOWNLOAD.updateTickboxs(pGroup);

            //Test
            MS_TEST.assert(
                eval(function() { return MS_DOWNLOAD._data(pId)._Current == 1; })
                , '_current must be 1 once is in current group'
                , false);

        }
        else if (arguments.length == 1) {//Get
            var objs = MS_DOWNLOAD._data();
            for (i in objs) {
                if (objs[i]._Current == 1 && objs[i].Group == pGroup) {


                    //Test
                    MS_TEST.assert(
                            eval(function() { return objs[i]._Current == 1; })
                            , '_current must be exists!'
                            , false);

                    return objs[i];
                }
            }
        }
        else {
            throw 'Invalid argrment lenght!';
        }

    },
    register: function(pObject, pCheckboxs) {

        var objEmbed = null;
        if (pObject.Type == 'all') {/*{Id,Type,Url,CallBack}*/
            objEmbed = {
                _Current: 0,
                _Codes: [],
                Id: pObject.Id,
                Type: pObject.Type,
                Url: pObject.Url,
                Group: pObject.Group,
                Callback: pObject.Callback
            }
        }
        else if (pObject.Type == 'current') {
            objEmbed = {
                _Current: 1, /*defalut context*/
                _Codes: [],
                Id: pObject.Id,
                Type: pObject.Type,
                Url: pObject.Url,
                Group: pObject.Group,
                Callback: pObject.Callback
            }
        }
        else if (pObject.Type == 'favorite') {
            objEmbed = {
                _Current: 0,
                _Codes: [],
                Id: pObject.Id,
                Type: pObject.Type,
                Url: pObject.Url,
                Group: pObject.Group,
                Callback: pObject.Callback
            }
        }
        if (objEmbed == null) {
            throw 'Unexpected LoadType!';
        }

        MS_DOWNLOAD._data(pObject.Id, objEmbed);

        //Test
        MS_TEST.assert(
          eval(function() {
              return MS_DOWNLOAD._data().length > 0;
          })
          , 'register'
          , false);

    },
    active: function(pGroup, pId) {
        MS_DOWNLOAD._current(pGroup, pId);
    },
    toggle: function(pGroup, pCheckbox) {
        var objEmbed = MS_DOWNLOAD._current(pGroup);
        if (!objEmbed) {
            return;
        }
        switch (objEmbed.Type) {
            case 'all':
                {
                    if (!$(pCheckbox).prop('checked')) {
                        MS_DOWNLOAD.addExcludeList(objEmbed.Id, $(pCheckbox).val());
                    }
                    else {
                        MS_DOWNLOAD.removeExcludeList(objEmbed.Id, $(pCheckbox).val());
                    }
                }
                break;
            case 'current':
            case 'favorite':
                {
                    if ($(pCheckbox).prop('checked')) {
                        //Array.add(objEmbed._Codes, $(pCheckbox).val());
                        objEmbed._Codes.push($(pCheckbox).val());
                    }
                    else {
                        Array.remove(objEmbed._Codes, $(pCheckbox).val());
                    }
                    MS_DOWNLOAD._data(objEmbed.Id, objEmbed);
                }
                break;
        }

    },
    updateTickboxs: function(pGroup) {

        var objEmbed = MS_DOWNLOAD._current(pGroup);
        if (!objEmbed) {
            return;
        }
        switch (objEmbed.Type) {
            case 'all':
                {
                    $.map(MS_DOWNLOAD._currentCheckboxs(), function(n) {
                        if ($.inArray($(n).val(), objEmbed._Codes) == -1)//An element isn't contained in excluded list then tick it.
                        {
                            $(n).prop('checked', true);
                        }
                    });
                }
                break;
            case 'current':
            case 'favorite':
                {
                    $.map(MS_DOWNLOAD._currentCheckboxs(), function(n) {
                        if ($.inArray($(n).val(), objEmbed._Codes) != -1)//An element is contained in code list then tick it.
                        {
                            $(n).prop('checked', true);
                        }
                        else {
                            $(n).prop('checked', false);
                        }
                    });
                }
                break;
        }
        MS_DOWNLOAD._customHighlight();
    },
    download: function(pGroup) {

        var actObj = MS_DOWNLOAD._current(pGroup);
        var reqData = { 'excludes': [], 'codes': [] };
        switch (actObj.Type) {
            case 'all':
                {
                    reqData.excludes = actObj._Codes.length == 0 ? ['xxx'] : actObj._Codes;
                    actObj.Callback(reqData);
                }
                break;
            case 'current':
            case 'favorite':
                {
                    reqData.codes = actObj._Codes.length == 0 ? ['xxx'] : actObj._Codes;
                    actObj.Callback(reqData);
                }
                break;
        }

    },
    _download: function(url, data, method) {
        //url and data options required
        if (url && data) {
            //data can be string of parameters or array/object
            data = typeof data == 'string' ? data : jQuery.param(data);
            //split params into form inputs
            var inputs = '';
            jQuery.each(data.split('&'), function() {
                var pair = this.split('=');
                inputs += String.format('<input type="hidden" name="{0}" value="{1}" />', pair[0], pair[1]); ;
            });
            //send request
            jQuery(String.format('<form action="{0}" method="{1}">{2}</form>', url, (method || 'post'), inputs))
                .appendTo('body')
                .submit()
                .remove();
        };
    },
    addExcludeList: function(pId, pCode) {
        var objEmbed = MS_DOWNLOAD._data(pId);
        //Array.add(objEmbed._Codes, pCode);
        objEmbed._Codes.push(pCode);
        MS_DOWNLOAD._data(pId, objEmbed);

        //Test
        MS_TEST.assert(
            eval(function() {
                return $.grep(MS_DOWNLOAD._data(), function(n) {

                }).length == 1;
            }),
            'addExcludeList must contain added code!',
            false);

    },
    removeExcludeList: function(pId, pCode) {
        var objEmbed = MS_DOWNLOAD._data(pId);
        Array.remove(objEmbed._Codes, pCode);
        MS_DOWNLOAD._data(pId, objEmbed);

        //Test
        MS_TEST.assert(
            eval(function() {
                return $.grep(MS_DOWNLOAD._data(), function(n) {

                }).length == 0;
            }),
            'addExcludeList must not contain removed code!',
            false);

    },
    _customHighlight: function() {
        if (location.pathname.indexOf('/Stock/Screener') > -1) {/*For stock screener.*/


            $("#screenerresults").find(':checkbox').each(function(i, n) {
                if ($(n).prop('checked') == true) {/*Jump to tr by .parent().parent()*/
                    $(n).parent().parent().addClass('highlight');
                } else {
                    $(n).parent().parent().removeClass('highlight');
                }
            });
        }
        else if (location.pathname.indexOf('Fund/Screener') > -1) {/*For fund screener.*/


            $("#screenerresults").find('.RowOdd , .RowEven').each(function(i, n) {
                var iCheckbox = $(n).find(':checkbox').get(0);
                if ($(iCheckbox).prop('checked') == true) {
                    $(n).addClass('highlight');
                } else {
                    $(n).removeClass('highlight');
                }
            });
        }


    }
}
