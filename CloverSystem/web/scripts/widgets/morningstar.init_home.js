/* Init loader for ARC HomePage
 *   
 *  Loads all required libs and widgets to render this page.
 *  
 * Date: Tues July 6 
 *
 */



(function($) {
    if (window.$.Loader === undefined) {
        alert('$.Loader not loaded!');
        return true;
    };
    $.Loader.animation('show');

    $(window)
		.ready(function() {
		    /*
		    * OPTIONS
		    */
		    $.Loader({
		        base: {
		            js: 'http://jqueryui.com/ui/jquery.ui.',
		            css: 'http://jqueryui.com/themes/base/jquery.ui.',
		            img: ''
		        },
		        debug: false
		    });

		    /*
		    * Show Highlighter in code
		    */
		    $('pre[class^=brush]').Loader({
		        base: {
		            css: 'SyntaxHighlighter/styles/sh',
		            js: 'SyntaxHighlighter/scripts/sh'
		        },
		        url: [
					'Core.js',
					'BrushJScript.js',
					'Core.css',
					'ThemeDefault.css'
				],
		        success: function() {
		            SyntaxHighlighter.config.clipboardSwf = 'scripts/clipboard.swf';
		            SyntaxHighlighter.all();
		        }
		    });

		    /*
		    *	TABS
		    */
		    $("div#tabs").Loader(
			{
			    url: [
					'core.css',
					'theme.css',
					'tabs.css',
					'core.js',
					'widget.js',
					'tabs.js'
				],
			    success: function() {
			        $(this).tabs();
			    }
			});

		    /*
		    *	DIALOG
		    */
		    $("div#dialog").Loader(
			{
			    url: [
					'core.css',
					'theme.css',
					'dialog.css',
					'resizable.css',
					'core.js',
					'widget.js',
					'mouse.js',
					'draggable.js',
					'position.js',
					'resizable.js',
					'dialog.js'
				],
			    success: function() {
			        $(this).dialog();
			    }
			});


		    /*
		    *	BUTTONS
		    */
		    $("a.button, input.button, button.button").Loader(
			{
			    url: [
					'core.css',
					'theme.css',
					'button.css',
					'core.js',
					'widget.js',
					'button.js'
				],
			    success: function() {
			        $(this).button();
			    }
			});


		    /*
		    *	AUTOCOMPLETE
		    */
		    var availableTags = [
				"c++", "java", "php", "coldfusion",
				"javascript", "asp", "ruby", "python",
				"c", "scala", "groovy", "haskell", "perl"
			];
		    $('input#tags')
				.Loader(
				'keydown',
				{
				    url: [
						'core.js',
						'widget.js',
						'position.js',
						'autocomplete.js'
					],
				    success: function() {
				        $(this).autocomplete({
				            source: availableTags
				        });
				    },
				    dataType: 'js'
				})
				.Loader([
					'core.css',
					'theme.css',
					'autocomplete.css'
				]);


		    /*
		    *	DATEPICKER
		    */
		    $("input#datepicker").Loader(
			'focus',
			{
			    url: [
					'core.css',
					'theme.css',
					'datepicker.css',
					'core.js',
					'widget.js',
					'datepicker.js'
				],
			    complete: function() {
			        $.Loader.animation('show');
			    },
			    success: function() {
			        $.Loader.animation('hide');
			        $(this)
						.datepicker()
						.datepicker("show");
			    }
			}
			);


		    /*
		    *	ACCORDION
		    */
		    $.Loader({
		        name: '$.fn.accordion',
		        url: [
					'core.css',
					'theme.css',
					'accordion.css',
					'core.js',
					'widget.js',
					'accordion.js'
				]
		    });
		    $('#accordion').accordion();


		    /*
		    *	DRAGGABLE
		    */
		    $('div#draggable')
				.Loader(
				'hover',
				{
				    url: [
						'core.js',
						'widget.js',
						'mouse.js',
						'draggable.js'
					],
				    success: function() {
				        $(this)
							.draggable()
							.css({ 'cursor': 'move' });
				        $.Loader.animation('hide');
				    },
				    complete: function(target) {
				        $('div#draggable').css('cursor', 'wait');
				        $.Loader.animation('show');
				    }
				})
				.Loader(
				[
					'core.css',
					'theme.css'
				])
				.css({
				    cursor: 'move',
				    height: '150px',
				    padding: '0.5em',
				    width: '150px'
				});

		    $.Loader.animation('hide');
		})

		.unload(function() {
		    $.Loader.animation('show');
		});
})(jQuery);