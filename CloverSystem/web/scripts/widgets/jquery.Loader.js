﻿/*!
* jQuery Plugin Loader v0.9.9.5
* http://kanema.com.br/
*
* Dual licensed under the MIT or GPL Version 2 licenses.
*
* Date: Fri May 28 14:21:00 2010 -0300
*/

; jQuery.Loader || (function($) {
    /*
    * @parms (String OR Array OR Object), (Function OR NULL), (Object OR NULL)
    * Main function that Loaders the js, css and img
    */
    $.Loader = $.Loader || function(url, callback, options) {
        if (callback && callback.constructor === Object)
            options = callback;
        if (url && url.constructor === Object) {
            options = url;
            if (url.url === undefined) {
                $.Loader.defaults = $.extend($.Loader.defaults, options);
                return true;
            };
        };
        options = $.extend({}, $.Loader.defaults, options);

        if ($.Loader.instance === null) {
            //	DEBUG
            if (options && options.debug && window.console)
                console.info('Function $.Loader.Class instantiated');
            $.Loader.instance = $.Loader.instance || new $.Loader.Class();
            delete $.Loader.Class;
        };
        return $.Loader.instance.type(url, callback, options);
    };

    /*
    * @parms String, (Object OR NULL)
    * Function to add an element to the power to Loader files that exist or have any action
    */
    $.fn.Loader = $.fn.Loader || function(eventType, options) {
        var that = this,
		selectors = $(that);
        if (eventType === undefined)
            return this;
        if (options !== undefined) {
            eventType = eventType.replace('hover', 'mousemove');
            $(that).one(
				eventType + '',
				function() {
				    if ($.isFunction(options.complete))
				        options.complete(that, selectors);
				    $.Loader
					(
						options.url,
						options.success,
						{
						    target: that,
						    elements: selectors,
						    eventType: options.eventType,
						    dataType: options.dataType,

						    debug: options.debug,
						    base: options.base,

						    async: options.async,
						    cache: options.cache,
						    global: options.global,
						    ifModified: options.ifModified,
						    scriptCharset: options.scriptCharset,
						    timeOut: options.timeOut
						}
					);
				}
			);
        }
        else {
            if (selectors.size() > 0)
                if (eventType.url !== undefined) {
                $.Loader(
						eventType.url,
						eventType.success,
						{
						    target: that,
						    elements: selectors,

						    debug: eventType.debug,
						    base: eventType.base,

						    async: eventType.async,
						    cache: eventType.cache,
						    global: eventType.global,
						    ifModified: eventType.ifModified,
						    scriptCharset: eventType.scriptCharset,
						    timeOut: eventType.timeOut
						}
					);
            } else {
                $.Loader(eventType);
            };
        };
        return this;
    };

    $.extend(
		$.Loader, {
		    /*
		    * @access public
		    */
		    defaults: {
		        base: {
		            js: '',
		            css: '',
		            img: ''
		        },
		        debug: false,

		        target: null,
		        elements: null,
		        eventType: '',
		        count: 0,
		        dataType: [],

		        async: false,
		        cache: true,
		        complete: null,
		        global: true,
		        ifModified: false,
		        scriptCharset: null,
		        timeOut: 60000,

		        extension: ''
		    },

		    /*
		    * @access private
		    */
		    count: { 'i': 0, 'o': 0 },
		    past: [],
		    call: [],
		    img: [],
		    loadingTime: 0,

		    /*
		    * @static
		    * Main class with the variable to instantiate
		    */
		    instance: null,
		    Class: function() {
		        /*
		        * Notes type argument must return
		        */
		        this.type = function(url, callback, options) {
		            return this.type[url.constructor].apply(this, arguments)
		        };

		        /*
		        * @params String, Function, Object
		        */
		        this.type[String] = function(url, callback, options) {
		            //	DEBUG
		            if (options && options.debug && window.console) {
		                console.info('Loading: ' + url);
		                var loadingTime = new Date().getTime();
		            }
		            var options = options || {},
					validate = {
					    js: /.js$/.test(url),
					    css: /.css$/.test(url),
					    isNull: (options.dataType === '') ? false : options.dataType
					};

		            if ($.inArray(url, $.Loader.past) === -1 || options.cache === false) {
		                $.Loader.past.push(url);

		                if (options.dataType === 'js' || (validate.js && validate.isNull)) {
		                    $.ajax({
		                        dataType: 'script',
		                        type: 'GET',

		                        async: options.async,
		                        cache: options.cache,
		                        complete: options.complete,
		                        global: options.global,
		                        ifModified: options.ifModified,
		                        scriptCharset: options.scriptCharset,
		                        timeOut: options.timeOut,
		                        type: options.type,

		                        url: ((/http\b/.test(url)) ? '' : options.base.js) + url + '',
		                        success: function() {
		                            //	DEBUG
		                            if (options && options.debug && window.console) {
		                                loadingTime = ((new Date().getTime()) - loadingTime);
		                                console.info('Loaded: ' + url.replace(options.base.js, '') + ' (' + loadingTime + 'ms)');
		                                $.Loader.loadingTime += loadingTime;
		                            };
		                            $.Loader.instance.callback({
		                                callback: callback,
		                                target: this,
		                                elements: $(this),
		                                count: options.count,
		                                params: arguments
		                            });
		                        },
		                        error: function() {
		                            if (options && options.debug && window.console) {
		                                console.info('Error in:' + url.replace(options.base.js, ''));
		                            };
		                        }
		                    });
		                } else if (options.dataType === 'css' || (validate.css && validate.isNull)) {
		                    var link = document.createElement('link');
		                    url = ((/http\b/.test(url)) ? '' : options.base.css) + url;
		                    if (options.cache === false) {
		                        url += ((/\?/).test(url)) ? "" : "?";
		                        url += new Date().valueOf().toString();
		                    }
		                    $(link).attr({ 'href': url, 'rel': 'stylesheet', 'type': 'text/css' });
		                    document.body.appendChild(link);
		                    $(window).ready(function() {
		                        if (options && options.debug && window.console) {
		                            loadingTime = ((new Date().getTime()) - loadingTime);
		                            console.info('Loaded: ' + url.replace(options.base.css, '') + ' (' + loadingTime + 'ms)');
		                            $.Loader.loadingTime += loadingTime;
		                        };
		                        return $.Loader.instance.callback({
		                            callback: callback,
		                            target: $('link[href="' + url + '"]'),
		                            count: options.count
		                        });
		                    });
		                } else {
		                    var img = $.Loader.img.length;
		                    url = ((/http\b/.test(url)) ? '' : options.base.img) + url + '';
		                    if (options.cache === false) {
		                        url += ((/\?/).test(url)) ? "" : "?";
		                        url += new Date().valueOf().toString();
		                    }
		                    var obj = $('<img />')
											.attr('src', url)
											.one('load readystatechange', function() {
											    //	DEBUG
											    if (options && options.debug && window.console) {
											        loadingTime = ((new Date().getTime()) - loadingTime);
											        console.info('Loaded: ' + url.replace(options.base.img, '') + ' (' + loadingTime + 'ms)');
											        $.Loader.loadingTime += loadingTime;
											    };
											    $.Loader.instance.callback({
											        callback: callback,
											        target: this,
											        elements: $(this),
											        count: options.count
											    })
											});
		                    $.Loader.img.push({
		                        img: obj
		                    });
		                }
		            } else {
		                if (options.dataType === 'js' || (validate.js && validate.isNull)) {
		                    options.target = this;
		                    options.elements = $(this);
		                } else if (options.dataType === 'css' || (validate.css && validate.isNull)) {
		                    options.target = $('link[href="' + url + '"]');
		                    options.elements = $('link');
		                } else {
		                    options.target = $('[src=' + url + ']', $.Loader.img);
		                    options.elements = $($.Loader.img);
		                }
		                $.Loader.instance.callback(
							{
							    count: options.count,
							    callback: callback,
							    target: options.target,
							    elements: options.elements
							}
						);
		            };

		        };

		        /*
		        * @params Array, Function, Object
		        */
		        this.type[Array] = function(url, callback, options) {
		            var options = options || {},
					dataType = options.dataType;
		            options.count = 1;
		            if ($.isFunction(callback))
		                $.Loader.call.push(
							{
							    success: callback,
							    target: options.target,
							    elements: options.elements
							}
						);

		            $.Loader.count.i += url.length;
		            while (url.length > 0) {
		                var i = url.shift();
		                if (i.url && (i.url).constructor === String) {
		                    $.Loader.count.i -= url.length + 1;
		                    while (i && i.constructor === Object) {
		                        $.Loader(i.url, i.success);
		                        i = url.shift();
		                    }
		                    return;
		                };
		                options.dataType = (dataType.constructor !== String) ? dataType.shift() : dataType;
		                $.Loader(i + '', null, options);
		            };
		        };

		        /*
		        * @params Object, Function, Object
		        */
		        this.type[Object] = function(url, callback, options) {
		            var options = options || {},
					dataType = options.dataType;
		            options.count = 1;
		            if ($.isFunction(callback))
		                $.Loader.call.push(
							{
							    success: callback,
							    target: options.target,
							    elements: options.elements
							}
						);

		            $.each(url, function() { $.Loader.count.i++ });
		            $.each(url, function(index, element) {
		                if (dataType !== undefined)
		                    options.dataType = dataType.shift();
		                $.Loader(index, element, options);
		            });
		        };

		        /*
		        * @params Function, Element, Elements, Number
		        * Function to execute and compute the overall success
		        */
		        this.callback = function(obj) {
		            $.extend(
						{},
						{
						    callback: null,
						    target: null,
						    elements: null,
						    count: null,
						    params: null
						},
						obj
					);
		            $.Loader.count.o += obj.count;
		            if ($.isFunction(obj.callback))
		                obj.callback(obj.target, obj.elements, obj.params);
		            if ($.Loader.count.o == $.Loader.count.i) {
		                while ($.Loader.call.length) {
		                    var exe = $.Loader.call.shift();
		                    exe.success(
								exe.target,
								exe.elements
							);
		                };
		                //	DEBUG
		                if ($.Loader.defaults.debug && window.console) {
		                    console.info('Master function executed (' + $.Loader.loadingTime + 'ms)');
		                    $.Loader.loadingTime = 0;
		                };
		            }
		        };

		    }
		}
	);

    /*
    * Function to show the loading
    */
    $.extend(
		$.Loader,
		{
		    animation: function(type) {
		        if ($('#jquery-Loader-loader').size() < 1) {
		            $.Loader($.Loader.animation.bg);
		            $('<div />')
						.attr('id', 'jquery-Loader-loader')
						.css({
						    '-moz-border-radius': '10px',
						    '-webkit-border-radius': '10px',
						    'background': 'url(' + $.Loader.animation.bg + ') no-repeat 50% 50% #333',
						    'border': '2px solid #666',
						    'float': 'left',
						    'font-size': '0',
						    'left': '50%',
						    'padding': '25px',
						    'position': 'fixed',
						    'top': '50%'
						})
						.hide()
						.appendTo('body');
		        };

		        if (arguments[0] === 'show') {
		            $('#jquery-Loader-loader').show();
		        } else if (arguments[0] === 'hide') {
		            $('#jquery-Loader-loader').fadeOut();
		        };
		    }
		}
	);
    $.extend(
		$.Loader.animation,
		{
		    contructor: $.Loader.animation,
		    bg: $.Loader.animation.bg || 'include-loader.gif'
		}
	);
})(window.jQuery);