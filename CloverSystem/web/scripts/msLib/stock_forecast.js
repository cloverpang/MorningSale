

        function addCommas(nStr)
        {
            if (isNaN(nStr))
            {
                return nStr;
            }
            else 
            {
                nStr += '';
	            x = nStr.split('.');
	            x1 = x[0];
	            x2 = x.length > 1 ? '.' + x[1] : '';
	            var rgx = /(\d+)(\d{3})/;
	            while (rgx.test(x1)) {
		            x1 = x1.replace(rgx, '$1' + ',' + '$2');
	            }
	            return x1 + x2;
            }
    	    
        }
       

        function reformatTable()
        {
        
                //Assign comma
                $.map($('#dvContent tr[sq_seq_a]'),function(n,i){
                    var iJQForecast = $('#dvForecast tr[sq_seq_a="'+$(n).attr('sq_seq_a')+'"]');
                    var iHeight = parseInt($(n).height());
                    if ($(n).attr('sq_seq_a').indexOf('t') > -1 || $(n).attr('sq_seq_a').indexOf('f') > -1)
                    {
                    
                        $.map(iJQForecast.find('td'),function(m){
                            $(m).text(
                                addCommas($(m).text())
                            );
                        });
                        
                        $.map($(n).find('td'),function(m){
                            $(m).text(
                                addCommas($(m).text())
                            );
                        });
                        
                    }
                
                });
        
        

                //For ie7 only
                if ($.browser.msie && $.browser.version == '7.0' )
                {

                    $('#tblForecast').css('margin-top', '2px');
                    $.map($('#dvContent tr[sq_seq_a]'), function(n, i) {
                        var iJQForecast = $(String.format('#dvForecast tr[sq_seq_a="{0}"]', $(n).attr('sq_seq_a')));
                        var iHeight = parseInt($(n).height());

                        switch (iHeight) {
                            case 16:
                                iHeight = iHeight - 1;
                                break;
                            case 23:
                            case 39:
                                iHeight = iHeight - 7;
                                break;
                        }

                        if (iJQForecast.hasClass('RowOdd')) {
                            iJQForecast.removeClass('RowOdd').addClass('RowOdd_R');
                        }

                        iJQForecast.height(iHeight);
                        iJQForecast.find('td').css({width: '90px' });
                        iJQForecast.find('td').height(0);
                        
                    });
                    
                }
                else
                {
                    
                    $.map($('#dvContent tr[sq_seq_a]'),function(n,i){
                        var iJQForecast = $('#dvForecast tr[sq_seq_a="'+$(n).attr('sq_seq_a')+'"]');
                        var iHeight = parseInt($(n).height());
                        iJQForecast.css({height:iHeight + 'px'})
                        
                        if (iJQForecast.hasClass('RowOdd'))
                        {
                            iJQForecast.removeClass('RowOdd');
                            iJQForecast.addClass('RowOdd_R');
                        }
                    });
                    
                }



        }

        //Bind data
        function bindForecast()
        {
            $.map(_actualData, function(n) {               
                $("#tBody").tmpl(n).appendTo($('#dvContent'));
            });            
            $('#dvContent tr').clone({withDataAndEvents:true}).appendTo('#dvForecast');
            $("#dvContent td[sp_columntype='Forecast'] ,  #dvForecast td[sp_columntype='Actual']").remove();
        }
        
        function filterForecastData(objs,filter)
        {
            var results = [];
            $.map(objs,function(obj,j){
                var result = {
                                GroupName : obj.GroupName,
                                RowLabel : obj.RowLabel,
                                Columns : [],
                                Index : obj.Index,
                                Attr: obj.Attr,
                                TextStyle: obj.TextStyle,
                                Sequence : j
                };

                $.map(obj.Columns,function(n,i){
                

                        if (n.ColumnType == 'Actual')
                        {
                            if (parseInt(n.Label) <= _currentYear -5)
                            {
                                n.Past5Years = true;
                            }
                            else
                            {
                                n.Past5Years = false;
                            }

                            result.Columns.push(n)
                        }
                        else if (n.ColumnType == 'Forecast')
                        {
                            result.Columns.push(n)
                            
                        }
                       
                    
                });
                results.push(result);
            })
        
            return results;
        }
        
        function toggleYearRagne(range)
        {
            var configDisable = {
                                    'color' : _color_disable,
                                    'text-decoration':'none',
                                    'cursor':'text'
                                };
            var configEnable = {
                                'color':'',
                                'text-decoration':'underline',
                                'cursor':'pointer'
                                };
            switch (range)
            {
                case 'past' :
                    $('td[Past5Years=false]').hide();
                    $('td[Past5Years=true]').show();
                    $('#lnkNext').css(configEnable); 
                    $('#lnkPrev').css(configDisable); 
                    break;
                case 'last' :
                    $('td[Past5Years=true]').hide();
                    $('td[Past5Years=false]').show();
                    $('#lnkNext').css(configDisable);
                    $('#lnkPrev').css(configEnable); 
                break;
                
            }

            

            
        }
            
        function loadForcast() {


            //Clear content.
            $('#dvLoadingContent').html('&nbsp;&nbsp;<img src="/Content/Images/ajax-loader.gif" border="0"/>&nbsp;&nbsp;');
            $('#dvLoadingForecast').html('&nbsp;&nbsp;<img src="/Content/Images/ajax-loader.gif" border="0"/>&nbsp;&nbsp;');            
            
            //Check whether there are available data or not. 
            //Load data to variable in case no data.
            if (_forecastData == null && _actualData  == null)
            {
                $.post(_uri + _focusYears, function(data) {
                    if (typeof data == 'string') {
                        data = $.parseJSON(data);
                    }
                    _actualData = filterForecastData(data, 'Actual');
                    //_forecastData = filterForecastData(data,'Forecast');
                    //empty the divs
                    $('#dvLoadingContent').html('');
                    $('#dvLoadingForecast').html('');
                    $('#dvContent').html('');
                    $('#dvForecast').html('');
                    bindForecast();
                    toggleYearRagne('last');
                    reformatTable();
                });
            }

        }


        try{
            loadForcast();
        }
        catch(ex)
        {
            //alert(ex);
        }
        

        ///For downloading CSV file.
        function loadCSV(tabName) {

            window.open('../DownloadCsv/' + tabName + '/' + _asxCode + '?pid=' + _guid, '_blank');
        }