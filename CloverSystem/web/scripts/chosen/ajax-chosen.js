
(function($) {
    if ($ == null) $ = jQuery;
    var countRequest = 0;
    return $.fn.ajaxChosen = function(settings, callback) {
        var chosenXhr, defaultOptions, options, select;
        if (settings == null) settings = {};
        if (callback == null) callback = function() { };
        defaultOptions = {
            minTermLength: 3,
            afterTypeDelay: 500,
            jsonTermKey: "term"
        };

        //        if ($.browser.msie && parseInt($.browser.version) < 8) {
        //            select = this[0];
        //        }
        //        else {
        select = this;
        //        }


        chosenXhr = null;
        options = $.extend({}, defaultOptions, settings);
        this.chosen();
        return this.each(function() {
            return $(this).next('.chzn-container').find(".search-field > input, .chzn-search > input").bind('keyup', function() {
                var field, msg, val;
                val = $.trim($(this).attr('value'));
                msg = val.length < options.minTermLength ? "Keep typing..." : "Looking for '" + val + "'";
                select.next('.chzn-container').find('.no-results').text(msg);
                if (val === $(this).data('prevVal')) return false;
                $(this).data('prevVal', val);
                if (this.timer) clearTimeout(this.timer);
                if (val.length < options.minTermLength) return false;
                field = $(this);
                if (!(options.data != null)) options.data = {};
                options.data[options.jsonTermKey] = val;
                options.data['pageIndex'] = 0;
                options.data['tags'] = pageXRay.getUniverse();
                if (typeof success === "undefined" || success === null) {
                    success = options.success;
                }
                options.success = function(data) {
                    var items, selected_values;
                    if (!(data != null)) return;
                    selected_values = [];
                    select.find('option').each(function() {
                        if (!$(this).is(":selected")) {
                            return $(this).remove();
                        } else {
                            return selected_values.push($(this).val() + "-" + $(this).text());
                        }
                    });

                    items = callback(data);

                    //In case there were option tags.

                    if ($.browser.mozilla && parseFloat($.browser.version) < 4) {

                        //Fixed FF3
                        $.each(items, function(i, n) {
                            if (selected_values.indexOf(i + "-" + $(n).text()) === -1) {

                                var iInvest = $.data(n, 'ms_xray_embeded').InvestmentType;

                                return $(select).find('optgroup[label="' + iInvest + '"]').append(n);
                            }
                        });
                    
                    }
                    else if ($(items).has('option').length > 0) {
                        $.each(items, function(i, n) {
                            if (selected_values.indexOf(i + "-" + $(n).text()) === -1) {

                                var iInvest = $.data(n, 'ms_xray_embeded').InvestmentType;

                                return $(select).find('optgroup[label="' + iInvest + '"]').append(n);
                            }
                        });
                    }
                    else {
                        $.each(items, function(value, text) {
                            if (selected_values.indexOf(value + "-" + text) === -1) {
                                return $("<option />").attr('value', value).html(text).appendTo(select);
                            }
                        });
                    }


                    select.trigger("liszt:updated");
                    if (typeof success !== "undefined" && success !== null) success();
                    field.attr('value', val);
                    return field.css('width', 'auto');
                };

                //if (countRequest == 0) {
                //    countRequest++;
                return this.timer = setTimeout(function() {
                    if (chosenXhr) chosenXhr.abort();
                    return chosenXhr = $.ajax(options);
                }, options.afterTypeDelay);
                //}


            });
        });
    };
})($);
