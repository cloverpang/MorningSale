/// <reference name="MicrosoftAjax.js"/>






(function($) {

    var _config = null;

    var methods = {


        overrideElement: function(eleChild, eleBase, overrides) {

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

        },

        /*Unlimited parameters*/
        ignoreError: function() {
            for (var i = 0; i < arguments.length; i++) {
                try { eval(arguments[i]()); } catch (e) { }
            }
        },
        swapIcon: function(icon, css, attrs, before) {

            var that = this;


            icon = arguments.length == 0 ? '' : icon;
            icon = icon == '' ? 'default' : icon;

            var shardowId = '___shadow' + $(this).attr('id');
            var elemShardow = $('<div id=' + shardowId + '></div>');
            if ($('div[id="' + shardowId + '"]').length == 0) {

                if (before) {
                    $(this).before(elemShardow);
                }
                else {
                    $(this).after(elemShardow);
                }

                var customCss = { 'margin': '1px auto auto 1px', 'position': 'absolute', 'width': '15px', 'height': '15px', 'z-index': '999', 'float': 'left' };
                try {

                    var nPos = {
                        'left': +$(this).position().left + 'px',
                        'top': +$(this).position().top + 'px'
                    };
                    $.extend(customCss, nPos);

                }
                catch (e) {

                }
                $(elemShardow).css(customCss);

            }
            elemShardow = $('div[id="' + shardowId + '"]')[0];

            if (icon == 'default') {
                $(elemShardow).fadeOut();
                //$(this).fadeIn();
            }


            switch (icon) {
                case 'loading':
                case 'load':
                    $(elemShardow).css(
                        {
                            'background-image': 'url("/Content/Images/ajax-loader.gif")'
                        }
                    );
                    //$(this).hide();
                    $(elemShardow).fadeIn();
                    break;
                case 'error':
                    $(elemShardow).css(
                        {
                            'background-image': 'url("/Content/Images/icon_error.gif")'
                        }
                    );
                    $(elemShardow).fadeIn();
                    //$(this).fadeIn();
                    break;
            }

            if (attrs != null) {
                $.extend(elemShardow, attrs);
            }

            if (css != null) {
                $(elemShardow).css(css);
            }

        }
    };


    $.fn.ms_base = function(method) {

        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            throw { msg: 'Init object by assinging a configuration not allowed by this object.' };
        } else {
            $.error('Method ' + method + ' does not exist on mslib.');
        }

    };



})(jQuery);



(function($) {

    //Properties
    var _items = [];
    var _isInit = false;
    var _config = null;
    var _link = null;


    var methods = {

        /*
        link(optional) =>  
        key => 
        */
        //Generic configuration
        config: function(c) {

            if (c === 'object') {
                _config = c;
            }
            else {
                return _config;
            }

        },
        //Functions
        add: function(item) {
            _items.push(item);
        },
        /*An item can be both object with key or only key.*/
        remove: function(handler) {

            _items = _.reject(_items, handler);

        },
        list: function() {
        return _items;
        }
    };

    var constructors = {
        init: function(config) {
            /*
            {
            'key': null,
            'items': [],
            'link': An xpath expression indicate object which will be linked.
            }
            */
            _config = config;
            _isInit = true;
            return methods;
        }
    };


    $.fn.ms_collection = function(method) {


        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return constructors.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on mslib.');
        }

    };



})(jQuery);





(function($) {

    var _items = null;
    var _isInit = false;
    var _config = null;
    var _xhrStack = [];




    function loadTrigger(element, eventName) {
        this.element = element;
        this.event = event;
    };



    var methods = {

        /**
        dialogTrigger (optional): null,
        saveTrigger (optional): null,
        cancelTrigger (optional): null,
        loadTrigger (optional): {element,event},
        namingField :null,
        saveService : null,
        loadService : null,
        getCodesHandler: null,
        */
        config: function(c) {

            if (c === 'object') {
                _config = c;
            }
            else {
                return _config;
            }

        },
        //Functions
        hide: function() {
            var dialogTrigger = _config.dialogTrigger;
            $.fn.ms_base('ignoreError', function() {
                $(_config.dialogTrigger).hide();
            });
        },

        show: function() {
            var dialogTrigger = _config.dialogTrigger;
            $.fn.ms_base('ignoreError', function() {
                $(dialogTrigger).show();
            });

        },

        save: function(opt) {
            var saveService = _config.saveService;
            var namingField = _config.namingField;
            var items = eval(_config.getCodesHandler)();
            opt = $.extend({
                type: 'POST',
                url: saveService,
                data: { 'name': namingField, 'items': items },
                traditional: true
            }, opt);


            _xhrStack.push($.ajax(opt));
            return _.last(_xhrStack);
        },

        load: function(opt) {
            var loadService = _config.loadService;
            opt = $.extend({
                type: 'POST',
                url: loadService,
                data: {},
                traditional: true
            }, opt);

            _xhrStack.push($.ajax(opt));
            return _.last(_xhrStack);
        }
    };


    var constructors = {
        init: function(config) {

            _config = config;
            //_items.link = new $.fn.ms_collection(config.key, config.link);

            //Assign operation for optional configs
            $.fn.ms_base('ignoreError',
                        function() {
                            $(ctx.saveTrigger).click(function() {
                                ctx.save({});
                                ctx.hide();
                            });
                        }, function() {
                            $(ctx.cancelTrigger).click(function() {
                                ctx.hide();
                            });
                        }, function() {
                            $(ctx.loadTrigger).click(function() {
                                var v = $(this).val();
                                if (typeof v === 'object') {
                                    ctx.load();
                                }
                            });
                        });

            _isInit = true;
            return methods;
        }
    };


    $.fn.ms_xray = function(method) {

        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return constructors.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on mslib.');
        }

    };

})(jQuery);



