/**
* @author darren, jack, rae, updated by Clover
*/

var CloverJsonDataTemplateItem = function(opt) {
    var defaultOptions = {
        /**
        * Id internal tracking id
        * 
        * @type Int
        */
        Id: '',

        /**
        * AjaxDataRoot our AJAX data row container, path to data
        * context
        * 
        * @type String 
        */
        AjaxDataRoot: "item",

        /**
        * JsonTotalRowsParam The location of the total rows variable in
        * the feed.
        * 
        * @type String
        */
        JsonTotalRowsParam: "totalRows",

        /**
        * Name Reference name for lookups
        * 
        * @type String
        */
        Name: '',

        /**
        * Section The Site Section RR, MR, etc
        * 
        * @type String
        */
        Section: '',

        /**
        * Subsection the subsection MR1, etc, etc
        * 
        * @type String
        */
        Subsection: '',

        /**
        * Options For adding extra params
        * 
        * @type String
        */
        Options: '',

        /**
        * URL The querystring
        * 
        * @type String
        */
        Url: '',

        /**
        * ServerURL Controller Action
        * 
        * @type String
        */
        ServerURL: '',

        /**
        * TBL Output element content rendered to
        * 
        * @type String
        */
        Tbl: '',

        /**
        * Template The name of the template used to render this row
        * 
        * @type String
        */
        Template: '',

        /**
        * Title The title of the control
        * 
        * @type String
        */
        Title: '',

        /**
        * Paginate Do we add a pagination control
        * 
        * @type boolean
        */
        Paginate: false,

        /**
        * Historic So we display historical records
        * 
        * @type boolean
        */
        Historic: false,

        /**
        * Norecs Number of records to retrieve in this request.
        * 
        * @type int
        */
        Norecs: 0,

        /**
        * Pagesize The current pagesize, how many records per page, this is the first page size. Subsequent pages can be different than the first page depending on the ApendSize.
        * 
        * @type int
        */
        Pagesize: 0,

        /**
        * CurrentPage The current page
        * 
        * @type int
        */
        CurrentPage: 0,

        /**
        * TotalRows Total number of rows returned from controller for
        * current request
        * 
        * @type int
        */
        TotalRows: 0,

        /**
        * PaginationControlID The class of the pagination control
        * 
        * @type string
        */
        PaginationControlID: ".pagination",

        /**
        * HeaderResultID The ID of the total rows header item
        * 
        * @type string
        */
        HeaderResultID: "#header_result",

        /**
        * Jsonsection
        * 
        * @type string
        */
        Jsonsection: '',

        /**
        * SearchControl Search control ID for dynamic searches
        * 
        * @type string
        */
        SearchControl: '',

        /**
        * SortingControl ID of the control that contains sorting
        * 
        * @type string
        */
        SortingControl: '',

        /**
        * SortingClassName Classname of the items u want to use as sort
        * headers
        */
        SortingClassName: ".arcSort",

        /**
        * CacheAjax Do we cache our AJAX requests
        * 
        * @type boolean
        */
        CacheAjax: false,

        /**
        * Append Do we append the rows or page
        * 
        * @type boolean
        */
        Append: false,

        /**
        * AppendSize Number of items to be retrieved next request.
        * 
        * @type int
        */
        AppendSize: 10,

        /**
        * StripeRows Do we want row striping of our results
        * 
        * @type bool
        */
        StripeRows: true,

        /**
        * RowOddClass Class to apply to odd rows
        * 
        * @type string
        */
        RowOddClass: "RowOdd",

        /**
        * RowEvenClass Class to apply to even rows
        * 
        * @type string
        */
        RowEvenClass: "RowEven",

        /**
        * FadeInTableOnLoad Do we want the results control to fade in
        * 
        * @type bool
        */
        FadeInTableOnLoad: true,

        /**
        * TableFadeInSpeed IF we fade, how quicly
        * 
        * @type int
        */
        TableFadeInSpeed: 5,

        /**
        * SortField The name of the current sortfield
        * 
        * @type string
        */
        SortField: '',

        /**
        * SortOrder What is the current sort order
        * 
        * @type string
        */
        SortOrder: '',

        /**
        * FireBugActive Is firebug active
        * 
        * @type bool
        */
        FireBugActive: true,

        /**
        * ValidRowsFieldName The name of the field to check for values.
        * Used with ValidRows
        * 
        * @see ValidRows
        */
        ValidRowsFieldName: '',

        /**
        * ValidRows Used with ValidRowsFieldName to ensure only rows
        * with matching values are returned
        * 
        * @Type Array
        */
        ValidRows: [],

        /**
        * LookupFields Array of fields that can be used for replace.
        * List of fieldnames
        * 
        * @type array
        */
        LookupFields: [],

        /**
        * LookupFieldFunctions Array of functions to be executed
        * against matching field defined in LookupFields
        */
        LookupFieldFunctions: [],

        /**
        * Private internal var DataHolder
        * 
        * @type string
        * 
        */
        _DataHolder: '',

        /**
        * MoreLink ID of the control that the More link will be
        * rendered to
        * 
        * @type string
        */
        MoreLink: '',

        /**
        * Assign more button id that disable action will be operated 
        * after out of data.  
        * 
        * @type string
        */
        MoreButton: '',

        /**
        * Type What type this is, fund, stock, etc, etc
        * 
        * @type string
        */
        Type: 'Fund',

        /**
        * The request type
        * 
        * @type String
        */
        RequestType: 'POST',

        /**
        * DataType
        * 
        * @type String The datatype, json, xml, etc, etc
        */
        DataType: 'json',

        /**
        * Cache
        * 
        * @type bool Do we cache ajax requests
        */
        Cache: false,

        /**
        * ContentType
        * 
        * @type string The content type of the request
        */
        ContentType: 'application/json; charset=utf-8',

        /**
        * FireBugActive
        * 
        * @type bool Detect firebug
        */

        FireBugActive: false,

        /**
        * ChartDataArray
        * @type Array of data for charting
        */
        ChartDataArray: [],


        /**
        * Ticker
        * @type string or int of fund or stock.
        * If set, then the system will attempt to pass this value as ticker to the backend.
        * This can be used to restrict returned documents.
        */
        Ticker: '',

        TaggedVideoListOnly: false,

        /**
        * DisplyFromToResultID The ID of the item to display results from 1 to 10 out of 1000 for example
        * 
        * @type string
        */
        DisplyFromToResultID: '',
        /**
        * The ID of progress bar
        * 
        * @type string
        */
        Progress: '',
        /**
        * Tags
        * 
        * @type string
        */
        Tag: '',
        /**
        * The text to explain what's going on when no data was returned
        * 
        * @type string
        */
        /*ARC-1067*/
        EmptyDataText: 'Data not found',
        /**
        * The ID of element to be droped by EmptyDataText
        * 
        * @type string
        */
        EmptyDataLabel: '',
        /**
        * Exclude historical data
        * 
        * @type string
        */
        ExcludeHistorical: false,

        DataSource: 'ARC',

        StartPage: null,

        NoOfPages: 7,

        Success: undefined,

        AjaxError: undefined,

        Complete: undefined,

        Data: {}

    }, Loaded = 0;


    // load options
    this.opts = jQuery.extend(true, {}, defaultOptions, opt);


    // Cache our ajax requests if configured to do so.
    $.ajaxSetup({ cache: this.opts.CacheAjax, dataType: 'json' });






    // merge new pregs with old prefs. 
    this.UpdateOptions = function(optx) {
        this.opts = jQuery.extend(true, {}, this.opts, optx);

    };


    // logging function
    this.log = function(msg, type, showstacktrace) {
        if (this.opts.FireBugActive == true) {
            switch (type) {
                case "debug":
                    console.debug(msg);
                    break;

                case "info":
                    console.info(msg);
                    break;

                case "warn":
                    console.warn(msg);
                    break;

                case "error":
                    console.error(msg);
                    break;
            }
            if (showstacktrace) {
                console.trace();
            }
        }
    };



    // do we have firebug for console logging?
    if (window.console && window.console.firebug) {
        this.opts.FireBugActive = true;
        this.log("Firebug console logging enabled", "info", false);

    }





    this.GrabDataValues = function(dataitem) {
        // returns array of data values in feed.
        // extract data and place into array property for later use by graphs etc etc



    };



    this.BindMoreLink = function() {
        if (this.opts.MoreLink != 'undefined') {
            $(this.opts.MoreLink).attr('href', "/" + this.opts.Type + "/More?s=" + escape(this.opts.Section) + "&ss=" + escape(this.opts.Subsection) + "&sect=" + escape(this.opts.Name) + "&sname=" + escape(this.opts.Type));
        }
    };




    this.ReplaceValues = function(dataitem) {
        if (this.opts.LookupFields.length > 0) {
            //iterate all field in lookup
            var i = 0;
            for (i = 0; i < this.opts.LookupFields.length; i++) {
                var func = this.opts.LookupFieldFunctions[i];
                try {
                    var tempVal = this.executeFunction(this.opts.LookupFields[i], func, dataitem);
                    dataitem[this.opts.LookupFields[i]] = tempVal;
                } catch (err) {
                    // log it and ignore for now
                    this.log(err, "error", true);
                }

            }
            return dataitem;
        } else {
            return dataitem;
        }
    };


    // execute passed function
    this.executeFunction = function(fld, func, dataitem) {
        try {
            var val = this.getContextValue(fld, dataitem);
            var str = func + "('" + val + "');";
            return eval(str);
        } catch (err) {
            // if error return original value
            return fld;
        }
    };




    // traverse a path of a context object from a string representation,
    // for example "object.child.attr"
    this.getContextValue = function(str, context) {
        var path = str.split(".");


        var val = context[path[0]];
        var i;
        for (i = 1; i < path.length; i++) {
            // Return an empty string if the lookup ever hits undefined.
            if (val !== undefined && val !== null) {
                val = val[path[i]];
            } else {
                return "";
            }
        }
        // Make sure the last piece did not end up undefined.
        val = val === undefined ? "" : val;

        return val;
    };





    this.isRowValid = function(item) {
        // do we want to process this record?
        if (this.opts.ValidRows.length > 0) {
            // we have some, check if this record is valid
            var val = this.getContextValue(this.opts.ValidRowsFieldName, item);
            // field exists, check if val if valid.
            if (val.length > 0) {
                // value returned check if valid (-1 = not found)
                if (jQuery.inArray(val, this.opts.ValidRows) != -1) {
                    // valid, let pass
                    return true;
                } else {
                    // move to next record
                    return false;
                }
            }
        } else {
            // no values defined, reutrn true to row will be processed
            return true;
        }
    };


    // sets total row count
    this.SetRowRount = function(v) {
        this.opts.TotalRows = v;
    };


    // clears the table
    this.ClearTable = function() {
        if (this.opts.Append === false) {
            $(this.opts.Tbl).html('');
        }
    };


    // apply fadin to table
    this.FadeInTable = function() {
        if (this.opts.FadeInTableOnLoad) {
            $(this.opts.Tbl).html('').fadeIn();
        }
    };


    // main data loop
    this.processData = function(self, data) {

         //alert("process data start!");
        //Convert json string to object.
//        if (typeof data == 'string') {
//            data = $.parseJSON(data);
//        }
        //data = $.parseJSON(data);
        //alert(data[0].pageSize)



        // set rowcount
        self.SetRowRount(data[0][self.opts.JsonTotalRowsParam]);
        //self.opts.TotalRows = data[0][self.opts.JsonTotalRowsParam];

        
        // clear table?
        self.ClearTable();

        // Fade table
        self.FadeInTable();


        // apply pagination
        if (self.opts.Paginate)
        {
            self.ApplyPagination(self.opts.TotalRows, self.opts.CurrentPage, self.opts.Norecs);
            //alert(self.opts.TotalRows);
        } // end of pagination

        // setup some temp vars

        var rowClass = '';

        //update control with count of rows
        if (self.opts.PaginationCountcontrol !== "") {
            $(self.opts.PaginationCountcontrol).text(data[0].totalRows);
        }

        // Setup More link if required.
        self.BindMoreLink();


        // init counter
        var counter = 0;

        var binditems = self.getContextValue(self.opts.AjaxDataRoot, data[0]);

        //alert(binditems[0].companyName + 'compang name');
        var rowsArray = binditems;

        if (binditems != null && binditems != '' && (binditems.length == null || binditems.length == "undefined" || binditems.length <= 0)) {
            rowsArray = new Array();
            rowsArray[0] = binditems;
        }

        else {
            rowsArray = binditems;
        }



        $.each(rowsArray, function(i, item) {

            //alert("process data row" + i);
            if (self.isRowValid(item) === false) {
                // if not valid then move to next
                return true;
            }

            //alert(item.companyName + i);

            // do lookup
            if (self.opts.LookupFields) {
                item = self.ReplaceValues(item);
            }

            //alert(item.companyName);

            // inc counter
            counter++;

            // lookup any values that need to be replaced
            //var SectionLongName = self.SiteSectionLookup(item.SiteSection);

            // single quote causes video can't playing, so replace with another type of ’
            if (item.companyName) {
                item.companyName = item.companyName.replace(/'/g, '’');
            }


            // apply row Striping
            if (self.opts.StripeRows == true) {
                rowClass = self.ApplyRowStriping(counter, data);
            }

            //alert(rowClass);

            // apply template
            var x = self.RenderTemplate(item, rowClass);

            //alert(x);

            // render
            self.ApplyToTable(x);

            //alert("end of row - " + i);

            // append to table
        });  // end of each

    };


    // main function
    this.GetData = function() {
        //alert("get data start!");
        var self = this; // assign reference to current object to "self"
        var progressGuid = '';

        // populate URL param
        self.BuildURL();


        // do we have any existing data?
        if (self.opts._DataHolder && self.opts.ReUseData) {
            self.processData(self, self.opts._DataHolder);
        }


        if (self.opts.Progress) {
            //alert("3-1");
            progressGuid = jQuery.uuid('p-');
            //alert(progressGuid);
            //alert("3-2");
        }

        var TotalRows = 0;


        $.ajax({
            url: self.opts.Url,
            type: "POST",
            dataType: "json",
            cache: false,
            data: self.opts.Data,
            contentType: "application/json; charset=utf-8",
            beforeSend: function() {
                if (progressGuid != '' && progressGuid != null) {
                    $('#' + self.opts.Progress).addClass(progressGuid);
                    $("." + progressGuid).fadeIn();
                }
            },
            success: function(data, context) {
                //Convert json string to object.
//                if (typeof data == 'string') {
//                    data = $.parseJSON(data);
//                }

                data = $.parseJSON(data);
                //alert(data[0].totalRows);

                self.processData(self, data);

                if ($.trim(self.opts.MoreButton) != '') {
                    var pageSize = self.opts.Norecs;
                    if (self.opts.Append == true && Loaded >= 1) {
                        pageSize = self.opts.AppendSize;
                    }
                }

                TotalRows = data[0].totalRows;

                //alert(data[0].totalRows);

                Loaded++;

                if (self.opts.Success) { self.opts.Success(); }
            },
            complete: function() {
                if (self.opts.Progress) {
                    $("." + progressGuid).hide();
                }
                // apply pagination
                if (self.opts.Paginate) {
                    self.ApplyPagination(self.opts.TotalRows, self.opts.CurrentPage, self.opts.Pagesize);
                } // end of pagination

                var numRows = $(self.opts.Tbl).find('tr').length;

                // Show or hide more button, progress wheel and notification message
                if (numRows > 0) {
                    if (self.opts.EmptyDataLabel) {
                        $(self.opts.EmptyDataLabel).html('').hide();
                    }
                    // Show/Hide more button
                    if (self.opts.MoreButton) {
                        if (numRows < TotalRows) {
                            $(self.opts.MoreButton).show();
                        } else {
                            $(self.opts.MoreButton).hide();
                        }
                    }
                } else {
                    if (self.opts.EmptyDataLabel) {
                        $(self.opts.EmptyDataLabel).html(self.opts.EmptyDataText).show();
                    }
                    // Hide more button
                    if (self.opts.MoreButton) {
                        $(self.opts.MoreButton).hide();
                    }
                }

                if (self.opts.Complete) { self.opts.Complete(); }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                if (self.opts.AjaxError) { self.opts.AjaxError(xhr);}
            }

        });

        //alert("get data end");

    }; // end of GetData







    /**
    * RenderTemplate Renders the passed data to the passed template
    * 
    * @param (Object)
    *            item The object to render
    * @param (String)
    *            rowClass The class to be applied to this row.
    * @param (String)
    *            arrow Which image to show.
    * @returns String
    */
    this.RenderTemplate = function(item, rowClass) {

        //alert("find the template ing");

        //populate template                
        var x = $.tempest(this.getTemplateName(item), {
            item: item,
            rowBG: rowClass
        });

        //var x = this.opts.Template;

        //alert("find the template ing ...");

        this.log(x, "info", true);
        return x;
    };



    this.getTemplateName = function(x) {
//
//        if (x.DocType == "Price Change") {
//            return (this.CustomTemplateLookup(x.DocType));
//        }

        if (this.opts.Template != undefined && this.opts.Template != "") {
            return (this.opts.Template);
        }

        //return template name upon SiteSection
//        if (x.SiteSection != undefined && x.SiteSection != null) {
//            return (this.CustomTemplateLookup(x.SiteSection));
//        }

        //return template name upon document Type
//        if (x.DocType != undefined && x.DocType != null) {
//            return (this.CustomTemplateLookup(x.DocType));
//        } else {
//            return (this.opts.Template);
//        }
    };



    this.ApplyToTable = function(x) {

        $(this.opts.Tbl).append(x).fadeIn(this.opts.TableFadeInSpeed);
    };




    this.formatJsonDate = function(d) {
        if (d != null && d.toString().length > 0) {
            var vdate = eval(d.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"));
            return vdate;
        } else {
            return null;
        }
    };
    
   // format date field into something readable, if the date is match yyyy-mm-dd or yyyy/mm/dd format it, otherwise not format
    this.FormatDate = function(d) {
        var isoExp = /^\s*(\d{4})([-\/\._])(\d{2})([-\/\._])(\d{2})\s*/;
        var part = isoExp.exec(d.toString());
        var date = new Date(NaN);
        var month;
        if (part)
        {
            month = +part[3];
            date.setFullYear(part[1], month - 1, part[5]);
            if (month != date.getMonth() + 1) {
                date.setTime(NaN);
            }
            var mDate = $.datepicker.formatDate("dd-M-yy", new Date(date.toString()));
            return mDate;
        }
        else
        {
           return d.toString();
        }
    };
    
    this.StringTruncat = function(oldStr, maxLength, endWith) {
            if (oldStr == null)
                return oldStr.toString() + endWith.toString();

            //判断原字符串是否大于最大长度 
            if (oldStr.toString().length > maxLength)
            {
                //截取原字符串 
                var strTmp = oldStr.toString().substring(0, maxLength);

                //判断后缀是否为空 
                if (endWith == null)
                    return strTmp;
                else
                    return strTmp.toString() + endWith.toString();
            }
            return oldStr;
    };

    // serialize form, append to url and refresh data.
    this.FilterData = function() {

        // grab our search form and serialize
        if (this.opts.SearchControl != "undefined") {
            var f = $(this.opts.SearchControl).serialize();
            // strip appersands from string
            this.opts.Options = f.replace(/&/g, ":");
            // disable append
            // darren:this needs work
            this.RefreshData();
        }
    };

    this.RefreshData = function() {

        //alert("refresh data start");
        if (this.opts.Append == true) {
            this.opts.Append = false;
        }

        this.ClearTable();
        this.BuildURL();
        this.GetData();
        this.opts.Append = true;
        //alert("refresh data end");
    }

    this.RefreshOptionsData = function(options) {
        //alert(options);
        //this.log("Re-Loading all Data", "info", false);
        this.opts.Options = options;
        this.BuildURL();
        //alert(this.opts.Url);
        this.GetData();
    }



    this.ShowAllData = function() {
        this.log("Re-Loading all Data", "info", false);
        this.opts.Options = "";
        this.BuildURL();
        this.GetData();

    };




    this.SetupSortControl = function() {
        // bind handlers to control items
        // grab all spans with class arcSort
        var self = this; // assign reference to current object to "self" as "this" has a different context below
        // iterate and bind click handler

        if (self.opts.SortingControl != "undefined") {
            $(self.opts.SortingClassName).live("click", function() {
                // call sorting function
                self.SortData($(this), $(this).attr("name"), $(this).attr("class"));
            });
        } // end of if
    };




    /**
    * SortData apply sort, rebuild url and refresh data also modify
    * classname from asc->desc and vica versa update passed element
    * with new classname
    * 
    * @param (String)
    *            ID of the control
    * @param (String)
    *            Column Name
    * @param (String)
    *            The Sort Direction ASC/DESC
    */
    this.SortData = function(control, col, order) {

        // split class name to get direction
        var temp = order.split(" ");
        var sorder = temp[1];
        this.opts.SortField = col;
        this.opts.SortOrder = sorder;

        if (sorder == "asc") {
            $(control).removeClass('asc');
            $(control).addClass('desc');
            // show image for sort direction TODO
            this.opts.SortOrder = "desc";
        } else {
            $(control).removeClass('desc');
            $(control).addClass('asc');
            // show image for sort direction TODO
            this.opts.SortOrder = "asc";
        }
        // build url and refresh data
        this.BuildURL();
        this.FilterData();

    };




    // build our query URL
    this.BuildURL = function() {
        // incase of More button click, needs number of item to be retireved
        var noOfItems = this.opts.Norecs;
        if (this.opts.Append == true && Loaded >= 1) {
            noOfItems = this.opts.AppendSize;
        }
        // url contains combimnation of elements
        // basepath, section, subsection, cnt, historic, page, options,ie hack
        this.opts.Url = this.opts.ServerURL
            + "?section=" + this.opts.Section
            + "&subSection=" + this.opts.Subsection
            + "&noOfItems=" + noOfItems
            + "&currentPage=" + this.opts.CurrentPage
            + "&query=" + this.opts.Options
            + "&sortName=" + this.opts.SortField
            + "&sortOrder=" + this.opts.SortOrder
            + "&tag=" + this.opts.Tag
            + "&pageSize=" + this.opts.Pagesize
            + "&dataSource=" + this.opts.DataSource

        //alert(this.opts.Url);
        this.opts.Url += this.AddHackForIE();
    };



    // apply row classes to passed data.
    this.ApplyRowStriping = function(counter, data) {

        //If there are only counter
        if (this.opts.StartPage == null) {

            var noOfItems = this.opts.Norecs;
            if (this.opts.Append == true && Loaded >= 1) {
                noOfItems = this.opts.AppendSize;
            }
            var rowIndex = counter + (this.opts.CurrentPage == 0 ? 0 : this.opts.Pagesize + ((this.opts.CurrentPage - 1) * noOfItems));

            if (rowIndex % 2 === 0) {
                return this.opts.RowEvenClass;
            } else {
                return this.opts.RowOddClass;
            }
        }
        //If there are both counter and startRow.
        else if (arguments.length == 2 && this.opts.StartPage == 1) {
            if ((counter + (data.PageSize * (data.CurrentPage - 1))) % 2 === 0) {
                return this.opts.RowEvenClass;
            } else {
                return this.opts.RowOddClass;
            }
        }

    };



    // append value to querystring to stop IE caching requests when we dont want
    this.AddHackForIE = function() {
        var currentTime = new Date();
        return "&random=" + currentTime.getTime();
    };



    //page_index starting from 0, so we need to add it up by 1 before display
    this.setPaginationPage = function(page_index) {
        //var url = paginationHolder.URL;

        this.opts.CurrentPage = parseInt(page_index);
        //    paginationHolder.URL = $.param.querystring(url, 'rp=' + this.opts.CurrentPage);
        this.BuildURL();
        this.FilterData();
    };




    this.setPaginationRowSize = function(size) {
        this.opts.Pagesize = size;
        this.opts.CurrentPage = 1;
        this.opts.Norecs = size;
        // this.opts.rp
        var url = this.BuildURL();
        //url = $.param.querystring(url, 'cnt=' + size);
        // url = $.param.querystring(url, 'rp=1');
        // alert(size + ' '+ url);
        // paginationHolder.URL = url;
        this.GetData();
    };



    // update pagination plugins with new values
    this.ApplyPagination = function(TotalRows, CurrentPage, PageSize) {


        $(this.opts.PaginationControlID).pagination(
                            TotalRows, {
                                current_page: parseInt(CurrentPage),
                                items_per_page: PageSize,
                                ArcObject: this
                            }
                        );
        // set header results with count of rows
        $(this.opts.HeaderResultID).text('(' + TotalRows + ')');

        // set Display from to result 
        var to = TotalRows;
        if ((CurrentPage * PageSize + PageSize) < to) { to = CurrentPage * PageSize + PageSize; }
        $(this.opts.DisplyFromToResultID).text('Display: ' + (CurrentPage * PageSize + 1) + ' - ' + to + ' out of ' + TotalRows + ' | Results per page:');

    }; // end of ApplyPagination






    this.ShowMore = function() {
        this.opts.CurrentPage = this.opts.CurrentPage + 1;
        this.GetData();
    };



    // Replace shortname with longname for sitesections.
    // add new sections here
    this.SiteSectionLookup = function(sect) {

        // add new sections here

        var v = "";

        switch (sect) {

            case "RR":
                v = "Corporate Action";
                break;

            case "DL":
                v = "Document Library";
                break;

            case "MR":
                v = "Market Review";
                break;

            case "LE":
                v = "Learning";
                break;

            case "MP":
                v = "Model Portfolio";
                break;

            case "RET":
                v = "Fund Return";
                break;
        }
        return v;
    };


    // lookup for correct image to show for recommendation
    // add or change this function to change the image class
    this.RecommendationImageLookup = function(rec) {

        var v = "";
        switch (rec) {

            case "Recommended":
                v = "img_arrowUP";
                break;

            case "Avoid":
                v = "img_arrowDOWN";
                break;

            case "Investment Grade":
                v = "img_arrowUP";
                break;

            case "Highly Recommended":
                v = "img_arrowUP";
                break;

            case "Hold":
                v = "img_arrowDOWN";
                break;

            default:
                return "";

        }
        return v;
    };

    // lookup for correct image to show for report icon (research report, asx300, video ...)
    this.DocumentTypeImageLookup = function(rec) {

        var v = "";
        switch (rec) {

            case "Research Report":
                v = "img_report";
                break;

            case "Announcement":
                v = "img_news";
                break;

            case "commentary":
                v = "img_commentary";
                break;

            case "video":
                v = "img_video";
                break;

            case "Price Change":
                v = "img_priceChange";
                break;

            default:
                return "img_report";

        }
        return v;
    };





    // lookup for correct image to show for report icon (research report, asx300, video ...)
    this.CustomTemplateLookup = function(rec) {

        var v = "";
        switch (rec) {

            case "RR":
                v = "FundsReport";
                break;

            case "Announcement":
                v = "StockAnnouncement";
                break;

            case "MR":
                v = "commentaryrow";
                break;

            case "DL":
                v = "commentaryrow";
                break;

            case "MPW":
                v = "commentaryrow";
                break;

            case "MP":
                v = "commentaryrow";
                break;

            case "ETF":
                v = "commentaryrow";
                break;


            case "APL":
                v = "commentaryrow";
                break;


            case "ETFResearchReport":
                v = "ETFReport";
                break;

            case "LICResearchReport":
                v = "LICReport";
                break;

            case "RET":
                v = "commentaryrow";
                break;

            case "MACRO":
                v = "FundsReport";
                break;

            case "SA":
                v = "FundsReport";
                break;


            case "M*VIDEO":
                v = "videorow";
                break;

            case "VIDEO":
                v = "videorow";
                break;

            case "Price Change":
                v = "StockPriceChange";
                break;

            case "Research Report":
                v = "ResearchReport";
                break;

            case "digestAsx300":
                v = "SignalGReport";
                break;

            default:
                return this.opts.Template;

        }
        return v;
    };

    // setup sort control
    this.SetupSortControl();


};                                                                                                                                                                                                                                                                                                                                                                                                    // end of class


function CloverJsonDataTemplateHolder() {
    this.name;
    // array of CloverBookTemplateItem objects
    this.data = [];
};


// param retrieval function
$.urlParam = function(name) {
    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
    try {
        return results[1] || 0;
    } catch (e) {
        return "";
    }

};


