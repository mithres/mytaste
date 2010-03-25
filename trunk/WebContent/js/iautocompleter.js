/**
 * Interface Elements for jQuery
 * Autocompleter
 * 
 * http://interface.eyecon.ro
 * 
 * Copyright (c) 2006 Stefan Petre
 * Dual licensed under the MIT (MIT-LICENSE.txt) 
 * and GPL (GPL-LICENSE.txt) licenses.
 *  
 */

/**
 * Attach AJAX driven autocomplete/sugestion box to text input fields.
 *
 * 
 * 
 * @name Autocomplete
 * @description Attach AJAX driven autocomplete/sugestion box to text input fields.
 * @param Hash hash A hash of parameters
 * @option String source the URL to request
 * @option Integer delay (optional) the delayed time to start the AJAX request
 * @option Boolean autofill (optional) when true the first sugested value fills the input
 * @option String helperClass (optional) the CSS class applied to sugestion box
 * @option String selectClass (optional) the CSS class applied to selected/hovered item
 * @option Integer minchars (optional) the number of characters needed before starting AJAX request
 * @option Hash fx (optional) {type:[slide|blind|fade]; duration: integer} the fx type to apply to sugestion box and duration for that fx
 * @option Function onSelect (optional) A function to be executed whenever an item it is selected
 * @option Function onShow (optional) A function to be executed whenever the suggection box is displayed
 * @option Function onHide (optional) A function to be executed whenever the suggection box is hidden
 * @option Function onHighlight (optional) A function to be executed whenever an item it is highlighted
 *
 * @type jQuery
 * @cat Plugins/Interface
 * @author Stefan Petre
 */
jQuery.iAuto = {
	
	empty : function()
	{
		var inputID = $(this).attr('id');
		jQuery.iAuto[inputID].content.empty();
		if (jQuery.iAuto[inputID].iframe) {
			jQuery.iAuto[inputID].iframe.hide();
		}
	},

	clear : function()
	{
		var self = this;
		var inputID = $(this).attr('id');
		jQuery.iAuto[inputID].items = null;
		jQuery.iAuto[inputID].selectedItem = null;
//		jQuery.iAuto.lastValue = jQuery.iAuto.subject.value;
		if(jQuery.iAuto[inputID].helper.css('display') == 'block') {
			if (jQuery.iAuto[inputID].subject.autoCFG.fx) {
				switch(jQuery.iAuto[inputID].subject.autoCFG.fx.type) {
					case 'fade':
						jQuery.iAuto[inputID].helper.fadeOut(jQuery.iAuto[inputID].subject.autoCFG.fx.duration, function(){jQuery.iAuto.empty.apply(this);});
						break;
					case 'slide':
						jQuery.iAuto[inputID].helper.SlideOutUp(jQuery.iAuto[inputID].subject.autoCFG.fx.duration, function(){jQuery.iAuto.empty.apply(this);});
						break;
					case 'blind':
						jQuery.iAuto[inputID].helper.BlindUp(jQuery.iAuto[inputID].subject.autoCFG.fx.duration, function(){jQuery.iAuto.empty.apply(this);});
						break;
				}
			} else {
				jQuery.iAuto[inputID].helper.hide();
			}
			if (jQuery.iAuto[inputID].subject.autoCFG.onHide)
				jQuery.iAuto[inputID].subject.autoCFG.onHide.apply(jQuery.iAuto[inputID].subject, [jQuery.iAuto[inputID].helper, jQuery.iAuto[inputID].iframe]);
		} else {
			jQuery.iAuto.empty.apply(this);
		}
//		window.clearTimeout(jQuery.iAuto.timer);
	},
	
	/**
	 * 
	 */
	update: function(result){
		var self = this;
		var inputID = $(this).attr('id');
		var subject = jQuery.iAuto[inputID].subject;
		var subjectValue = jQuery.iAuto.getFieldValues(subject);
		subject.autoCFG.lastSuggestion = result;
		var size = subject.autoCFG.lastSuggestion.size();
		if (size > 0) {
			var toWrite = '';
			subject.autoCFG.lastSuggestion.each(
				function(nr)
				{
					if(subject.autoCFG.getJSON){
						toWrite += '<li rel="' + this + '" dir="' + nr + '" style="cursor: default;">' + this + '</li>';
					}else{
						toWrite += '<li rel="' + jQuery('value', this).text() + '" dir="' + nr + '" style="cursor: default;">' + jQuery('text', this).text() + '</li>';
					}
				}
			);
			if (subject.autoCFG.autofill) {
				var valueToAdd = null;
				if(subjectValue.autoCFG.getJSON){
					valueToAdd = subject.autoCFG.lastSuggestion.get(0);
				}else{
					valueToAdd = jQuery('value', subject.autoCFG.lastSuggestion.get(0)).text();
				}
				subject.value = subjectValue.pre + valueToAdd + subject.autoCFG.multipleSeparator + subjectValue.post;
				jQuery.iAuto.selection(
					subject, 
					subjectValue.item.length != valueToAdd.length ? (subjectValue.pre.length + subjectValue.item.length) : valueToAdd.length,
					subjectValue.item.length != valueToAdd.length ? (subjectValue.pre.length + valueToAdd.length) : valueToAdd.length
				);
			}
			
			if (size > 0) {
				jQuery.iAuto.writeItems(subject, toWrite);
				jQuery.iAuto[inputID].cacheResult[jQuery.iAuto[inputID].lastValue] = toWrite;
			} else {
				jQuery.iAuto.clear.apply(this);
			}
		} else {
			jQuery.iAuto.clear.apply(this);
		}
	},
	
	sendRequest: function(callback){
		var self = this;
		var inputID = $(this).attr('id');
		var subject = jQuery.iAuto[inputID].subject;
		var subjectValue = jQuery.iAuto.getFieldValues(subject);
		//var selectionStart = jQuery.iAuto.getSelectionStart(subject);
		if (subject && subjectValue.item != jQuery.iAuto[inputID].lastValue && subjectValue.item.length >= subject.autoCFG.minchars) {
			jQuery.iAuto[inputID].lastValue = subjectValue.item;
			jQuery.iAuto[inputID].currentValue = subjectValue.value;
			var data = {};
			data[jQuery(subject).attr('name') || 'field'] = jQuery(subject).attr('name') || 'field';
			data[jQuery(subject).attr('id') || 'value'] = subjectValue.item;

			if(subject.autoCFG.getJSON){
				jQuery.getJSON(subject.autoCFG.source, data, function(json){
					if(callback){
						callback(json);
					}
				});				
			}else{
				jQuery.ajax(
					{
						type: 'POST',
						data: jQuery.param(data),
						success: function(xml)
						{
							if(callback){
								callback(xml);
							}
						},
						url : subject.autoCFG.source
					}
				);
			}
		}
	},
	
	updatelocal: function(){
		var inputID = $(this).attr('id');
		var self = this;
		var size = 0;
		var filterResult = function(){
			return $('item value', jQuery.iAuto[inputID].entireResult).filter(function(index){
				if(size >= jQuery.iAuto[inputID].subject.autoCFG.pageSize){
					return false;
				}
				if($(this).text().indexOf(jQuery.iAuto[inputID].lastValue) == 0){
					size++;
					return true;
				}else{
					return false;
				}
			});
		};
		var filterJSON = function(){
			var result = [];
			jQuery.each(jQuery.iAuto[inputID].entireResult, function(key, values){
				jQuery.each(values, function(i,value){
					if(size >= jQuery.iAuto[inputID].subject.autoCFG.pageSize){
						return false;
					}
					if(value.indexOf(jQuery.iAuto[inputID].lastValue) == 0){
						size++;
						result.push(value);
					}
				});
			});
			return result;
		};
		var subject = jQuery.iAuto[inputID].subject;
		if(jQuery.iAuto[inputID].entireResult){
			// client has already done the request.
			var subjectValue = jQuery.iAuto.getFieldValues(subject);
			jQuery.iAuto[inputID].lastValue = subjectValue.item;
			jQuery.iAuto[inputID].currentValue = subjectValue.value;
			if(subject.autoCFG.getJSON){
				jQuery.iAuto.update.apply(this, [$(filterJSON())]);
			}else{
				jQuery.iAuto.update.apply(this, [$(filterResult()).parent('item')]);
			}
		}else{
			if(subject.autoCFG.getJSON){
				jQuery.iAuto.sendRequest.apply(this, [function(entire){
					jQuery.iAuto[inputID].entireResult = entire;
					jQuery.iAuto.update.apply(self, [$(filterJSON())]);
				}]);
			}else{
				jQuery.iAuto.sendRequest.apply(this, [function(entireXML){
					jQuery.iAuto[inputID].entireResult = entireXML;
					jQuery.iAuto.update.apply(self, [$(filterResult()).parent('item')]);
				}]);
			}
		}
	},

	updateremote : function (){
		var self = this;
		var inputID = $(this).attr('id');
		var subject = jQuery.iAuto[inputID].subject;
		if(subject.autoCFG.getJSON){
			jQuery.iAuto.sendRequest.apply(this, [(function(json){
				jQuery.each(json, function(key, values){
					jQuery.iAuto.update.apply(self, [$(values)]);
				});
			})]);	
		}else{
			jQuery.iAuto.sendRequest.apply(this, [(function(xml){
				jQuery.iAuto.update.apply(self, [$('item', xml)]);
			})]);
		}
	},
	
	writeItems : function(subject, toWrite){
		var inputID = $(subject).attr('id');
		jQuery.iAuto[inputID].content.html(toWrite);
		jQuery.iAuto[inputID].items = jQuery('li', jQuery.iAuto[inputID].content.get(0));

		jQuery.iAuto[inputID].items
			.mouseover(function(e){jQuery.iAuto.hoverItem.apply(this, [subject, e])})
			.bind('click', function(e){jQuery.iAuto.clickItem.apply(this, [subject ,e])});
		var position = jQuery.iUtil.getPosition(subject);
		var size = jQuery.iUtil.getSize(subject);
		jQuery.iAuto[inputID].helper
			.css('top', position.y + size.hb + 'px')
			.css('left', position.x +  'px');
		if(subject.autoCFG.helperClass){
			jQuery.iAuto[inputID].helper.addClass(subject.autoCFG.helperClass);
		}else{
			var helper = jQuery.iAuto[inputID].helper;
			$.each(subject.autoCFG.helperStyle, function(key, value){
				helper.css(key, value);
			});
		}
		if (jQuery.iAuto[inputID].iframe) {
			jQuery.iAuto[inputID].iframe
				.css('display', 'block')
				.css('top', position.y + size.hb + 'px')
				.css('left', position.x +  'px')
				.css('width', jQuery.iAuto[inputID].helper.css('width'))
				.css('height', jQuery.iAuto[inputID].helper.css('height'));
		}
//		jQuery.iAuto.selectedItem = 0;
//		jQuery.iAuto.items.get(0).className = subject.autoCFG.selectClass;
		jQuery.iAuto.applyOn(subject,subject.autoCFG.lastSuggestion.get(0), 'onHighlight');
		
		if (jQuery.iAuto[inputID].helper.css('display') == 'none') {
			if (subject.autoCFG.inputWidth) {
				var borders = jQuery.iUtil.getPadding(subject, true);
				var paddings = jQuery.iUtil.getBorder(subject, true);
				jQuery.iAuto[inputID].helper.css('width', subject.offsetWidth - (jQuery.boxModel ? (borders.l + borders.r + paddings.l + paddings.r) : 0 ) + 'px');
			}
			if (subject.autoCFG.fx) {
				switch(subject.autoCFG.fx.type) {
					case 'fade':
						jQuery.iAuto[inputID].helper.fadeIn(subject.autoCFG.fx.duration);
						break;
					case 'slide':
						jQuery.iAuto[inputID].helper.SlideInUp(subject.autoCFG.fx.duration);
						break;
					case 'blind':
						jQuery.iAuto[inputID].helper.BlindDown(subject.autoCFG.fx.duration);
						break;
				}
			} else {
				jQuery.iAuto[inputID].helper.show();
			}
			
			if (jQuery.iAuto[inputID].subject.autoCFG.onShow)
				jQuery.iAuto[inputID].subject.autoCFG.onShow.apply(jQuery.iAuto[inputID].subject, [jQuery.iAuto[inputID].helper, jQuery.iAuto[inputID].iframe]);
		}
	},
	
	checkCache : function(s)
	{
		var subject = this;
		var inputID = $(this).attr('id');
		if(jQuery.iAuto[inputID].cacheResult[s.item]){
			jQuery.iAuto[inputID].timer = window.setTimeout(function(){
				jQuery.iAuto.writeItems(subject, jQuery.iAuto[inputID].cacheResult[s.item]);
			}, this.autoCFG.delay);
			this.autoCFG.inCache = true;			
		}else{
			this.autoCFG.inCache = false;
		}
	},

	selection : function(field, start, end)
	{
		if (field.createTextRange) {
			var selRange = field.createTextRange();
			selRange.collapse(true);
			selRange.moveStart("character", start);
			selRange.moveEnd("character", - end + start);
			selRange.select();
		} else if (field.setSelectionRange) {
			field.setSelectionRange(start, end);
		} else {
			if (field.selectionStart) {
				field.selectionStart = start;
				field.selectionEnd = end;
			}
		}
		field.focus();
	},
	
	getSelectionStart : function(field)
	{
		if (field.selectionStart)
			return field.selectionStart;
		else if(field.createTextRange) {
			var selRange = document.selection.createRange();
			var selRange2 = selRange.duplicate();
			return 0 - selRange2.moveStart('character', -100000);
			//result.end = result.start + range.text.length;
			/*var selRange = document.selection.createRange();
			var isCollapsed = selRange.compareEndPoints("StartToEnd", selRange) == 0;
			if (!isCollapsed)
				selRange.collapse(true);
			var bookmark = selRange.getBookmark();
			return bookmark.charCodeAt(2) - 2;*/
		}
	},
	
	getFieldValues : function(field)
	{
		var fieldData = {
			value: field.value,
			pre: '',
			post: '',
			item: ''
		};
		
		if(field.autoCFG.multiple) {
			var finishedPre = false;
			var selectionStart = jQuery.iAuto.getSelectionStart(field)||0;
			var chunks = fieldData.value.split(field.autoCFG.multipleSeparator);
			for (var i=0; i<chunks.length; i++) {
				if(
					(fieldData.pre.length + chunks[i].length >= selectionStart
					 || 
					selectionStart == 0)
					 && 
					!finishedPre 
				) {
					if (fieldData.pre.length <= selectionStart)
						fieldData.item = chunks[i];
					else 
						fieldData.post += chunks[i] + (chunks[i] != '' ? field.autoCFG.multipleSeparator : '');
					finishedPre = true;
				} else if (finishedPre){
					fieldData.post += chunks[i] + (chunks[i] != '' ? field.autoCFG.multipleSeparator : '');
				}
				if(!finishedPre) {
					fieldData.pre += chunks[i] + (chunks.length > 1 ? field.autoCFG.multipleSeparator : '');
				}
			}
		} else {
			fieldData.item = fieldData.value;
		}
		return fieldData;
	},
	
	confirmPressedKey: function(){
		var args = Array.prototype.slice.call(arguments, 0);
		var pressedKey = args.pop();
		var result = false;
		$.each(args, function(index, num){
			if(num == pressedKey){
				result = true;
				return false;
			}
		});
		return result;
	},
	
	autocomplete : function(e)
	{
		var self = this;
		var inputID = $(this).attr('id');
		window.clearTimeout(jQuery.iAuto[inputID].timer);
		var subject = jQuery.iAuto.getFieldValues(this);
		
		var pressedKey = e.charCode || e.keyCode || -1;
		if (jQuery.iAuto.confirmPressedKey(13, 27, 35, 36, 38, 40, 9, pressedKey) && jQuery.iAuto[inputID].items) {
			if (window.event) {
				window.event.cancelBubble = true;
				window.event.returnValue = false;
			} else {
				e.preventDefault();
				e.stopPropagation();
			}

			if (jQuery.iAuto[inputID].selectedItem != null){
				jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0).className = '';
				// We need to restore original inner style definition
				var selectedItem = $(jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0));
				$.each(jQuery.iAuto[inputID].subject.autoCFG.originalSelectStyle, function(key, value){
					selectedItem.css(key, value);
				});
			}
			else
				jQuery.iAuto[inputID].selectedItem = -1;
			switch(pressedKey) {
				//enter
				case 9:
				case 13:
					if (jQuery.iAuto[inputID].selectedItem == -1)
						jQuery.iAuto[inputID].selectedItem = 0;
					var selectedItem = jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0);
					var valueToAdd = selectedItem.getAttribute('rel');
					this.value = subject.pre + valueToAdd + this.autoCFG.multipleSeparator + subject.post;
					jQuery.iAuto[inputID].lastValue = subject.item;
					jQuery.iAuto.selection(
						this, 
						subject.pre.length + valueToAdd.length + this.autoCFG.multipleSeparator.length, 
						subject.pre.length + valueToAdd.length + this.autoCFG.multipleSeparator.length
					);
					jQuery.iAuto.clear.apply(self);
					if(this.autoCFG.onBeforeSelect){
						this.autoCFG.onBeforeSelect();
					}
					if (this.autoCFG.onSelect) {
						iteration = parseInt(selectedItem.getAttribute('dir'))||0;
						jQuery.iAuto.applyOn(self, this.autoCFG.lastSuggestion.get(iteration), 'onSelect');
					}
					if(this.autoCFG.onAfterSelect){
						this.autoCFG.onAfterSelect();
					}
					if (this.scrollIntoView)
						this.scrollIntoView(false);
					return pressedKey != 13;
					break;
				//escape
				case 27:
					this.value = subject.pre + jQuery.iAuto[inputID].lastValue + this.autoCFG.multipleSeparator + subject.post;
					this.autoCFG.lastSuggestion = null;
					jQuery.iAuto.clear.apply(self);
					if (this.scrollIntoView)
						this.scrollIntoView(false);
					return false;
					break;
				//end
				case 35:
					jQuery.iAuto[inputID].selectedItem = jQuery.iAuto[inputID].items.size() - 1;
					break;
				//home
				case 36:
					jQuery.iAuto[inputID].selectedItem = 0;
					break;
				//up
				case 38:
					jQuery.iAuto[inputID].selectedItem --;
					if (jQuery.iAuto[inputID].selectedItem < 0)
						jQuery.iAuto[inputID].selectedItem = jQuery.iAuto[inputID].items.size() - 1;
					break;
				case 40:
					jQuery.iAuto[inputID].selectedItem ++;
					if (jQuery.iAuto[inputID].selectedItem == jQuery.iAuto[inputID].items.size())
						jQuery.iAuto[inputID].selectedItem = 0;
					break;
			}
			jQuery.iAuto.applyOn(this, this.autoCFG.lastSuggestion.get(jQuery.iAuto[inputID].selectedItem||0), 'onHighlight');
			if(this.autoCFG.selectClass){
				jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0).className = this.autoCFG.selectClass;
			}else{
				var selectedItem = $(jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0));
				$.each(this.autoCFG.selectStyle, function(key, value){
					jQuery.iAuto[inputID].subject.autoCFG.originalSelectStyle[key] = selectedItem.css(key);
					selectedItem.css(key, value);
				}); 
			}
			if (jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0).scrollIntoView)
				jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0).scrollIntoView(false);
			if(this.autoCFG.autofill) {
				var valToAdd = jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0).getAttribute('rel');
				this.value = subject.pre + valToAdd + this.autoCFG.multipleSeparator + subject.post;
				if(jQuery.iAuto[inputID].lastValue.length != valToAdd.length)
					jQuery.iAuto.selection(
						this, 
						subject.pre.length + jQuery.iAuto[inputID].lastValue.length, 
						subject.pre.length + valToAdd.length
					);
			}
			return false;
		}
		jQuery.iAuto.checkCache.apply(this, [subject]);
		
		if (this.autoCFG.inCache == false) {
			if (subject.item != jQuery.iAuto[inputID].lastValue && subject.item.length >= this.autoCFG.minchars)
				jQuery.iAuto[inputID].timer = window.setTimeout(function(){jQuery.iAuto['update' + self.autoCFG.mode].apply(self)}, this.autoCFG.delay);
			if (jQuery.iAuto[inputID].items) {
				jQuery.iAuto.clear.apply(this);
			}
		}
		return true;
	},

	applyOn: function(field, item, type)
	{
		if (field.autoCFG[type]) {
			var data = null;
			if(field.autoCFG.getJSON){
				data = item;
			}else{
				data = {};
				childs = item.getElementsByTagName('*');
				for(i=0; i<childs.length; i++){
					data[childs[i].tagName] = childs[i].firstChild.nodeValue;
				}
			}
			field.autoCFG[type].apply(field,[data]);
		}
	},
	
	hoverItem : function(subject, e)
	{
		var inputID = $(subject).attr('id');
		if (jQuery.iAuto[inputID].items) {
			if (jQuery.iAuto[inputID].selectedItem != null){
				var selectItem = jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0);
				selectItem.className = '';
				selectItem = $(selectItem);
				// We need to restore original inner style definition first.
				$.each(jQuery.iAuto[inputID].subject.autoCFG.originalSelectStyle, function(key, value){
					selectItem.css(key, value);
				});
			}
			jQuery.iAuto[inputID].selectedItem = parseInt(this.getAttribute('dir'))||0;
			if(jQuery.iAuto[inputID].subject.autoCFG.selectClass){
				jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0).className = jQuery.iAuto[inputID].subject.autoCFG.selectClass;
			}else{
				var selectedItem = $(jQuery.iAuto[inputID].items.get(jQuery.iAuto[inputID].selectedItem||0));
				$.each(jQuery.iAuto[inputID].subject.autoCFG.selectStyle, function(key, value){
					jQuery.iAuto[inputID].subject.autoCFG.originalSelectStyle[key] = selectedItem.css(key); 
					selectedItem.css(key, value);
				});
			}
		}
	},

	clickItem : function(self, event)
	{	
		var inputID = $(self).attr('id');
		window.clearTimeout(jQuery.iAuto[inputID].timer);
		
		event = event || jQuery.event.fix( window.event );
		event.preventDefault();
		event.stopPropagation();
		var subject = jQuery.iAuto.getFieldValues(jQuery.iAuto[inputID].subject);
		var valueToAdd = this.getAttribute('rel');
		jQuery.iAuto[inputID].subject.value = subject.pre + valueToAdd + jQuery.iAuto[inputID].subject.autoCFG.multipleSeparator + subject.post;
		jQuery.iAuto[inputID].lastValue = this.getAttribute('rel');
		jQuery.iAuto.selection(
			jQuery.iAuto[inputID].subject, 
			subject.pre.length + valueToAdd.length + jQuery.iAuto[inputID].subject.autoCFG.multipleSeparator.length, 
			subject.pre.length + valueToAdd.length + jQuery.iAuto[inputID].subject.autoCFG.multipleSeparator.length
		);
		jQuery.iAuto.clear.apply(self);
		if(jQuery.iAuto[inputID].subject.autoCFG.onBeforeSelect){
			jQuery.iAuto[inputID].subject.autoCFG.onBeforeSelect();
		}
		if (jQuery.iAuto[inputID].subject.autoCFG.onSelect) {
			iteration = parseInt(this.getAttribute('dir'))||0;
			jQuery.iAuto.applyOn(jQuery.iAuto[inputID].subject,jQuery.iAuto[inputID].subject.autoCFG.lastSuggestion.get(iteration), 'onSelect');
		}
		if(jQuery.iAuto[inputID].subject.autoCFG.onAfterSelect){
			jQuery.iAuto[inputID].subject.autoCFG.onAfterSelect();
		}

		return false;
	},

	protect : function(e)
	{
		var inputID = $(this).attr('id');
		pressedKey = e.charCode || e.keyCode || -1;
		if (jQuery.iAuto.confirmPressedKey(13, 27, 35, 36, 38, 40, pressedKey) && jQuery.iAuto[inputID].items) {
			if (window.event) {
				window.event.cancelBubble = true;
				window.event.returnValue = false;
			} else {
				e.preventDefault();
				e.stopPropagation();
			}
			return false;
		}
	},

	build : function(options)
	{
//		if (!options.mode || !options.source || !jQuery.iUtil) {
//			return;
//		}
		var inputID = $(this).attr('id');
		jQuery.iAuto[inputID] = {
			helper : null,
			content : null,
			iframe: null,
			timer : null,
			// last single input value
			lastValue: null,
			// the whole input value
			currentValue: null,
			subject: null,
			selectedItem : null,
			items: null,
			cacheResult: {},
			entireResult: options.entireResult
		};
		
		if (!jQuery.iAuto[inputID].helper) {
			if (jQuery.browser.msie) {
				jQuery('body', document).append('<iframe style="display:none;position:absolute;filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0);" id="autocompleteIframe_' + inputID + '" src="javascript:false;" frameborder="0" scrolling="no"></iframe>');
				jQuery.iAuto[inputID].iframe = jQuery('#autocompleteIframe_' + inputID);
			}
			jQuery('body', document).append('<div id="autocompleteHelper_' + inputID + '" style="position: absolute; top: 0; left: 0; z-index: 30001; display: none;"><ul style="margin: 0;padding: 0; list-style: none; z-index: 30002;">&nbsp;</ul></div>');
			jQuery.iAuto[inputID].helper = jQuery('#autocompleteHelper_' + inputID);
			jQuery.iAuto[inputID].content = jQuery('ul', jQuery.iAuto[inputID].helper);
		}

		return this.each(
			function()
			{
				if (this.tagName != 'INPUT' && this.getAttribute('type') != 'text' )
					return;
				this.autoCFG = {};
				this.autoCFG.pageSize = options.pageSize || 10;
				// local or remote.If this.autoCFG.mode is local, client requests information only once then read information in the local, otherwise one input one request.
				this.autoCFG.mode = options.mode;
				this.autoCFG.getJSON = options.getJSON ? true : false;
				this.autoCFG.source = options.source;
				this.autoCFG.minchars = Math.abs(parseInt(options.minchars)||1);
				this.autoCFG.helperClass = options.helperClass ? options.helperClass : '';
				this.autoCFG.selectClass = options.selectClass ? options.selectClass : '';
				this.autoCFG.helperStyle = options.helperStyle ? options.helperStyle : {};
				this.autoCFG.selectStyle = options.selectStyle ? options.selectStyle: {};
				// This attribute stores the original inner style definition so that we can restore inner style definition as we hope.
				this.autoCFG.originalSelectStyle = {};
				this.autoCFG.onBeforeSelect = options.onBeforeSelect && options.onBeforeSelect.constructor == Function ? options.onBeforeSelect : null;
				this.autoCFG.onSelect = options.onSelect && options.onSelect.constructor == Function ? options.onSelect : null;
				this.autoCFG.onAfterSelect = options.onAfterSelect && options.onAfterSelect.constructor == Function ? options.onAfterSelect : null;
				this.autoCFG.onShow = options.onShow && options.onShow.constructor == Function ? options.onShow : null;
				this.autoCFG.onHide = options.onHide && options.onHide.constructor == Function ? options.onHide : null;
				this.autoCFG.onHighlight = options.onHighlight && options.onHighlight.constructor == Function ? options.onHighlight : null;
				this.autoCFG.inputWidth = options.inputWidth || false;
				this.autoCFG.multiple = options.multiple || false;
				this.autoCFG.multipleSeparator = this.autoCFG.multiple ? (options.multipleSeparator || ', ') : '';
				this.autoCFG.autofill = options.autofill ? true : false;
				this.autoCFG.delay = Math.abs(parseInt(options.delay)||1000);
				if (options.fx && options.fx.constructor == Object) {
					if (!options.fx.type || !/fade|slide|blind/.test(options.fx.type)) {
						options.fx.type = 'slide';
					}
					if (options.fx.type == 'slide' && !jQuery.fx.slide)
						return;
					if (options.fx.type == 'blind' && !jQuery.fx.BlindDirection)
						return;

					options.fx.duration = Math.abs(parseInt(options.fx.duration)||400);
					if (options.fx.duration > this.autoCFG.delay) {
						options.fx.duration = this.autoCFG.delay - 100;
					}
					this.autoCFG.fx = options.fx;
				}
				this.autoCFG.lastSuggestion = null;
				this.autoCFG.inCache = false;
				jQuery.iAuto[inputID].subject = this;
				jQuery.iAuto[inputID].lastValue = this.value;
			}
		);
	},
	
	completerClear: function(){
		var inputID = $(this).attr('id');
		var self = this;
		jQuery.iAuto[inputID].timer = window.setTimeout(function(){jQuery.iAuto.clear.apply(self);}, 200);
	},
	
	start: function(){
		jQuery(this)
			.keypress(jQuery.iAuto.protect)
			.keyup(jQuery.iAuto.autocomplete)
			.blur(jQuery.iAuto.completerClear);
	},
	
	stop: function(){
		jQuery(this)
			.unbind('keypress', jQuery.iAuto.protect)
			.unbind('keyup', jQuery.iAuto.autocomplete)
			.unbind('blur', jQuery.iAuto.completerClear);
	},
	
	destroy: function(){
		var inputID = $(this).attr('id');
		$('#autocompleteIframe_' + inputID).remove();
		$('#autocompleteHelper_' + inputID).remove();
	}
};
jQuery.fn.Autocomplete = jQuery.iAuto.build;
jQuery.fn.start = jQuery.iAuto.start;
jQuery.fn.stop = jQuery.iAuto.stop;
jQuery.fn.destroy = jQuery.iAuto.destroy;