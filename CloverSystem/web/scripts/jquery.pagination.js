/**
* This jQuery plugin displays pagination links inside the selected elements.
* 
* This plugin needs at least jQuery 1.4.2
*
* @author Gabriel Birke (birke *at* d-scribe *dot* de)
* @version 2.2
* @param {int} maxentries Number of entries to paginate
* @param {Object} opts Several options (see README for documentation)
* @return {Object} jQuery Object
*/
(function($) {
    /**
    * @class Class for calculating pagination values
    */
    $.PaginationCalculator = function(maxentries, opts) {
        this.maxentries = maxentries;
        this.opts = opts;
    }

    $.extend($.PaginationCalculator.prototype, {
        /**
        * Calculate the maximum number of pages
        * @method
        * @returns {Number}
        */
        numPages: function() {
            if (this.opts.ArcObject != null)
                return Math.ceil(this.maxentries / this.opts.ArcObject.opts.Norecs);
            else
                return Math.ceil(this.maxentries / this.opts.items_per_page);
        },
        /**
        * Calculate start and end point of pagination links depending on 
        * current_page and num_display_entries.
        * @returns {Array}
        */
        getInterval: function(current_page) {

            var pageSize = this.opts.ArcObject != null ? this.opts.ArcObject.opts.NoOfPages : this.opts.num_display_entries;
            var currentPg = this.opts.ArcObject != null ? this.opts.ArcObject.opts.CurrentPage : current_page;

            var ne_half = Math.floor(pageSize / 2);
            var np = this.numPages();
            var upper_limit = np - pageSize;
            var start = currentPg > ne_half ? Math.max(Math.min(currentPg - ne_half, upper_limit), 0) : 0;
            var end = currentPg > ne_half ? Math.min(currentPg + ne_half + (pageSize % 2), np) : Math.min(pageSize, np);
            return { start: start, end: end };
        }

    });

    // Initialize jQuery object container for pagination renderers
    $.PaginationRenderers = {}

    /**
    * @class Default renderer for rendering pagination links
    */
    $.PaginationRenderers.defaultRenderer = function(maxentries, opts) {
        this.maxentries = maxentries;
        this.opts = opts;
        this.pc = new $.PaginationCalculator(maxentries, opts);
    }
    $.extend($.PaginationRenderers.defaultRenderer.prototype, {
        /**
        * Helper function for generating a single link (or a span tag if it's the current page)
        * @param {Number} page_id The page id for the new item
        * @param {Number} current_page 
        * @param {Object} appendopts Options for the new item: text and classes
        * @returns {jQuery} jQuery object containing the link
        */
        createLink: function(page_id, current_page, appendopts) {
            var lnk, np = this.pc.numPages();
            page_id = page_id < 0 ? 0 : (page_id < np ? page_id : np - 1); // Normalize page id to sane value
            appendopts = $.extend({ text: page_id + 1, classes: "" }, appendopts || {});
            if (page_id == current_page) {
                lnk = $("<span class='current'>" + appendopts.text + "</span>");
            }
            else {
                lnk = $("<a>" + appendopts.text + "</a>")
					.attr('href', this.opts.link_to.replace(/__id__/, page_id));
            }
            if (appendopts.classes) { lnk.addClass(appendopts.classes); }
            lnk.data('page_id', page_id);
            return lnk;
        },
        // Generate a range of numeric links 
        appendRange: function(container, current_page, start, end, opts) {
            var i;
            for (i = start; i < end; i++) {
                this.createLink(i, current_page, opts).appendTo(container);
            }
        },
        getLinks: function(current_page, eventHandler) {

            var pageSize = this.opts.ArcObject != null ? this.opts.ArcObject.opts.NoOfPages : this.opts.num_display_entries;
            var currentPg = this.opts.ArcObject != null ? this.opts.ArcObject.opts.CurrentPage : current_page;

            var begin, end,
				interval = this.pc.getInterval(currentPg),
				np = this.pc.numPages(),
				fragment = $("<div class='pagination'></div>");

            // Generate "Previous"-Link
            if (this.opts.prev_text && (currentPg > 0 || this.opts.prev_show_always)) {
                fragment.append(this.createLink(currentPg - 1, currentPg, { text: this.opts.prev_text, classes: "prev" }));
            }
            // Generate starting points
            if (interval.start > 0 && pageSize > 0) {
                end = Math.min(pageSize, interval.start);
                this.appendRange(fragment, currentPg, 0, end, { classes: 'sp' });
                if (this.opts.num_edge_entries < interval.start && this.opts.ellipse_text) {
                    jQuery("<span>" + this.opts.ellipse_text + "</span>").appendTo(fragment);
                }
            }
            // Generate interval links
            this.appendRange(fragment, currentPg, interval.start, interval.end);
            // Generate ending points
            if (interval.end < np && this.opts.num_edge_entries > 0) {
                if (np - this.opts.num_edge_entries > interval.end && this.opts.ellipse_text) {
                    jQuery("<span>" + this.opts.ellipse_text + "</span>").appendTo(fragment);
                }
                begin = Math.max(np - this.opts.num_edge_entries, interval.end);
                this.appendRange(fragment, currentPg, begin, np, { classes: 'ep' });

            }
            // Generate "Next"-Link
            if (this.opts.next_text && (currentPg < np - 1 || this.opts.next_show_always)) {
                fragment.append(this.createLink(currentPg + 1, currentPg, { text: this.opts.next_text, classes: "next" }));
            }
            $('a', fragment).click(eventHandler);
            return fragment;
        }


    });

    // Extend jQuery
    $.fn.pagination = function(maxentries, opts) {

        // Initialize options with default values
        opts = jQuery.extend({
            ArcObject: null,
            items_per_page: 10,
            num_display_entries: 10,
            current_page: 0,
            num_edge_entries: 0,
            link_to: "#",
            prev_text: "Prev",
            next_text: "Next",
            ellipse_text: "...",
            prev_show_always: true,
            next_show_always: true,
            renderer: "defaultRenderer",
            load_first_page: false,
            callback: function() { return false; }
        }, opts || {});

        var containers = this,
			renderer, links, current_page;

        /**
        * This is the event handling function for the pagination links. 
        * @param {int} page_id The new page number
        */
        function paginationClickHandler(evt) {
            var links,
				new_current_page = $(evt.target).data('page_id'),
				continuePropagation = selectPage(new_current_page);
            if (!continuePropagation) {
                evt.stopPropagation();
            }
            return continuePropagation;
        }

        /**
        * This is a utility function for the internal event handlers. 
        * It sets the new current page on the pagination container objects, 
        * generates a new HTMl fragment for the pagination links and calls
        * the callback function.
        */
        function selectPage(new_current_page) {
            // update the link display of a all containers
            containers.data('current_page', new_current_page);
            links = renderer.getLinks(new_current_page, paginationClickHandler);
            containers.empty();
            links.appendTo(containers);
            // call the callback and propagate the event if it does not return false
            var continuePropagation = opts.callback(new_current_page, containers);
            return continuePropagation;
        }


        /**
        * Calculate the maximum number of pages
        */
        function numPages2() {
            return Math.ceil(maxentries / opts.ArcObject.opts.Norecs);
        }

        /**
        * Calculate start and end point of pagination links depending on 
        * current_page and num_display_entries.
        * @return {Array}
        */
        function getInterval2() {
            var ne_half = Math.ceil(opts.ArcObject.opts.NoOfPages / 2);
            var np = numPages2();
            var upper_limit = np - opts.ArcObject.opts.NoOfPages;
            var start = opts.ArcObject.opts.CurrentPage > ne_half ? Math.max(Math.min(opts.ArcObject.opts.CurrentPage - ne_half, upper_limit), 0) : 0;
            var end = opts.ArcObject.opts.CurrentPage > ne_half ? Math.min(opts.ArcObject.opts.CurrentPage + ne_half, np) : Math.min(opts.ArcObject.opts.NoOfPages, np);
            return [start, end];
        }

        /**
        * This is the event handling function for the pagination links. 
        * @param {int} page_id The new page number
        */
        function pageSelected(page_id, evt) {
            current_page = page_id;
            drawLinks();
            var continuePropagation = opts.callback(page_id, panel);
            if (!continuePropagation) {
                if (evt.stopPropagation) {
                    evt.stopPropagation();
                }
                else {
                    evt.cancelBubble = true;
                }
            }
            return continuePropagation;
        }


        /**
        * This function inserts the pagination links into the container element
        */
        function drawLinks() {

            panel.empty();
            var interval = getInterval2();
            var np = numPages2();
            // This helper function returns a handler function that calls pageSelected with the right page_id
            var getClickHandler = function(page_id) {
                return function(evt) { return pageSelected(page_id, evt); }
            }
            // Helper function for generating a single link (or a span tag if it's the current page)
            var appendItem = function(page_id, appendopts) {
                page_id = page_id < 0 ? 0 : (page_id < np ? page_id : np - 1); // Normalize page id to sane value
                appendopts = jQuery.extend({ text: page_id + 1, classes: "" }, appendopts || {});
                if (page_id == opts.ArcObject.opts.CurrentPage) {
                    var lnk = jQuery("<span class='current'>" + (appendopts.text) + "</span>");
                }
                else {
                    var lnk = jQuery("<a>" + (appendopts.text) + "</a>")
						.bind("click", function(e) {
						    getClickHandler(page_id);
						    opts.ArcObject.setPaginationPage(page_id);
						}).attr('href', opts.link_to.replace(/__id__/, page_id));
                }
                if (appendopts.classes) { lnk.addClass(appendopts.classes); }
                panel.append(lnk);
            }
            // Generate "Previous"-Link
            if (opts.prev_text && (current_page > 0 || opts.prev_show_always)) {
                //the below line is to generate "First"-Link
                appendItem(0, { text: 'First', classes: "prev" });
                appendItem(opts.ArcObject.opts.CurrentPage - 1, { text: opts.prev_text, classes: "prev" });
            }
            // Generate starting points
            if (interval[0] > 0 && opts.ArcObject.opts.NoOfPages > 0) {
                var end = Math.min(opts.ArcObject.opts.NoOfPages, interval[0]);
                for (var i = 0; i < end; i++) {
                    appendItem(i);
                }
                if (opts.ArcObject.opts.NoOfPages < interval[0] && opts.ellipse_text) {
                    jQuery("<span>" + opts.ellipse_text + "</span>").appendTo(panel);
                }
            }
            // Generate interval links
            for (var i = interval[0]; i < interval[1]; i++) {
                appendItem(i);
            }
            // Generate ending points
            if (interval[1] < np && opts.num_edge_entries > 0) {
                if (np - oopts.ArcObject.opts.NoOfPages > interval[1] && opts.ellipse_text) {
                    jQuery("<span>" + opts.ellipse_text + "</span>").appendTo(panel);
                }
                var begin = Math.max(np - opts.ArcObject.opts.NoOfPages, interval[1]);
                for (var i = begin; i < np; i++) {
                    appendItem(i);
                }

            }
            // Generate "Next"-Link
            if (opts.next_text && (opts.ArcObject.opts.CurrentPage < np - 1 || opts.next_show_always)) {
                appendItem(opts.ArcObject.opts.CurrentPage + 1, { text: opts.next_text, classes: "next" });
                appendItem(np, { text: 'Last', classes: "next" });
            }
        }

        // -----------------------------------
        // Initialize containers
        // -----------------------------------
        current_page = opts.ArcObject != null ? opts.ArcObject.opts.CurrentPage : opts.current_page;
        containers.data('current_page', current_page);
        // Create a sane value for maxentries and items_per_page
        maxentries = (!maxentries || maxentries < 0) ? 1 : maxentries;
        opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0) ? 1 : opts.items_per_page;



        if (opts.ArcObject != null) {

            // Store DOM element for easy access from all inner functions
            var panel = jQuery(this);
            // Attach control functions to the DOM element 
            this.selectPage = function(page_id) { pageSelected(page_id); }
            this.prevPage = function() {
                if (current_page > 0) {
                    pageSelected(current_page - 1);
                    return true;
                }
                else {
                    return false;
                }
            }
            this.nextPage = function() {
                if (current_page < numPages2() - 1) {
                    pageSelected(current_page + 1);
                    return true;
                }
                else {
                    return false;
                }
            }
            // When all initialisation is done, draw the links
            drawLinks();
            // call callback function
            opts.callback(current_page, this);
        }

        else {

            if (!$.PaginationRenderers[opts.renderer]) {
                throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
            }
            renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);

            // Attach control events to the DOM elements
            var pc = new $.PaginationCalculator(maxentries, opts);
            var np = pc.numPages();
            containers.bind('setPage', { numPages: np }, function(evt, page_id) {
                if (page_id >= 0 && page_id < evt.data.numPages) {
                    selectPage(page_id); return false;
                }
            });
            containers.bind('prevPage', function(evt) {
                var current_page = $(this).data('current_page');
                if (current_page > 0) {
                    selectPage(current_page - 1);
                }
                return false;
            });
            containers.bind('nextPage', { numPages: np }, function(evt) {
                var current_page = $(this).data('current_page');
                if (current_page < evt.data.numPages - 1) {
                    selectPage(current_page + 1);
                }
                return false;
            });
            // When all initialisation is done, draw the links
            links = renderer.getLinks(current_page, paginationClickHandler);
            containers.empty();
            links.appendTo(containers);
            // call callback function
            if (opts.load_first_page) {
                opts.callback(current_page, containers);
            }
        }
    } // End of $.fn.pagination block

})(jQuery);
