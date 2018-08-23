String.prototype.replaceSpaceAndFirstLower = function() {
	return this.replace(/\s+/g,"").replace(/^\S/, function(w) {
		return w.toLowerCase();
	});
}

window.clt = {
	default: {
		baseURL: (function() {
					return window.document.location.pathname.substring(0, 
						window.document.location.pathname.substring(1).indexOf("/") + 1);
				})(),
		TOKEN: "CLT-ACCESS-TOKEN",
		//后期扩展功能，当页面中存在多个Section中，根据此项值可以使所有存在Section同时只打开一个
		stayOneExpand: false,

		action: {
			foldable: function(e) {
				e = e.target || e;
				
				var content = e.parentElement.parentElement.nextElementSibling,
					action = content.nextElementSibling;
				if(e.classList.contains("fold")) {
					e.classList.remove("fold");
					e.classList.add("expand");

					content.style.display = "none";
					action.style.display = "none";
				} else {
					
					if(clt.default.stayOneExpand) {
						clt.default.action.foldAll();
					}

					e.classList.remove("expand");
					e.classList.add("fold");

					content.style.display = "block";
					action.style.display = "block";
				}
			}, 

			foldAll: function() {
				var sections = document.querySelectorAll(".section");
				for(var i = 0; i < sections.length; i++) {
					var foldButton = sections[i].querySelector(".s_fold div");
					if(foldButton.classList.contains("fold")) {
						foldButton.click();
					}
				}
			},

			editable: function(e) {
				e = e.target || e;
			},
			
			changeSection: function() {
				
			}
		}
	},

	metadata: {

	},

	template: {
		//f - form, t - table, default - f
		default: {
		    classname: "section",
		    style: "f",
		    styles: ["f", "t"]
		},

		createInput: function(field, text) {
	        var html = '<input type="{type}" {readonly} name="{name}" required="true" id="{id}" {key}="{value}">';
	        
	        html = html.replace("{type}", field.type);
	        
	        html = html.replace("{readonly}", field.readonly ? 'readonly="readonly"' : "");
	        
	        html = html.replace("{name}", field.label.replaceSpaceAndFirstLower());

	        html = html.replace("{id}", field.label);

	        html = html.replace("{value}", field.default || "");

	        if(field.type == "checkbox" && text) {
	            html = html.replace("{key}", "checked");
	        } else {
	            html = html.replace("{key}", "value");
	        }

	        return this.parse(html);
	    },
	    
	    createImage: function(label, src) {
	        var html = '<div name="{0}" id="{1}"><img src="{2}"></img></div>';

	        //Replace name
	        html = html.replace("{0}", label.replaceSpaceAndFirstLower());
	        //Replace id
	        html = html.replace("{1}", label);
	        //Replace id
	        html = html.replace("{2}", src);

	        return this.parse(html);
	    },
		   
		createDIV: function(label, text) {
		    var html = '<div name="{0}" id="{1}">{2}</div>';
		
		    //Replace name
		    html = html.replace("{0}", label.replaceSpaceAndFirstLower());
		    //Replace id
		    html = html.replace("{1}", label);
		    //Replace id
		    html = html.replace("{2}", text);
		
		    return this.parse(html);
		},

		createHref: function(label, href, text) {
		    var html = '<a name="{0}" id="{1}" href="" >{3}</a>';
		    
		    //Replace name
		    html = html.replace("{0}", label.replaceSpaceAndFirstLower());
		    //Replace id
		    html = html.replace("{1}", label);
		    //Replace id
		    html = html.replace("{3}", text);
		
		    return this.parse(html);
		},

		createSelectInput: function(field) {
		    var html = '<div class="input-select-box">' +
					//'<div name="{name}" id="{id}" class="input-box" contenteditable="true" onclick="">{default}</div>' +
					'<input name="{name}" id="{id}" class="input-box" onclick="getSelectContent(this)" value="{default}" table="{table}"/>' + 
		    		'<div class="select-box">' +
						'<div class="select-detail-loading" onclick="">loading...</div>' +
					'</div>' +
				'</div>';
		    
		    //Replace name
		    html = html.replace("{name}", field.label.replaceSpaceAndFirstLower());
		    //Replace id
		    html = html.replace("{id}", field.label);
		    //Replace field value
		    html = html.replace("{default}", field.value || "");
		    //Replace table
		    html = html.replace("{table}", field.table);
		
		    return this.parse(html);
		},

		createTextarea: function(field) {
	        var html = '<textarea required="true" id="{id}" name="{name}" value=""/>';
	        
	        html = html.replace("{name}", field.label.replaceSpaceAndFirstLower());

	        html = html.replace("{id}", field.label);

	        html = html.replace("{value}", field.default || "");

	        return this.parse(html);
	    },

		createShowTypeService: function(field) {
		    var content = "";
		    
		    switch(field.type) {
		    
		        case "img":
		            content = this.createImage(field.label, field.default);
		            break;
		        case "div":
		            content = this.createDIV(field.label, field.default);
		            break;
		        case "a":
		            content = this.createHref(field.label, field.href ,field.default);
		            break;
		        case "selectInput":
		            content = this.createSelectInput(field);
		            break;
		        case "textarea":
		            content = this.createTextarea(field);
		            break;
		        default:
		            content = this.createInput(field);
		    }
		    
		    return content;
		},
		    
		createField: function(field) {
		    var html = '<div class="f_field">' +
		               		'<div class="f_label">' +
		                    	'<label>{label}</label>' +
		                	'</div>' +
		                	'<div class="f_values" >' +
		                    	//'<div class="f_value">{content}</div>' +
		                    	'<div class="f_value"></div>' +
			                    '<div class="f_link"><a href="{href}" target="_blank">help</a></div>' + 
			                    '<div class="f_help">{helptext}</div>' +
			                '</div>' +
		            	'</div>',
		        name = field.label.replaceSpaceAndFirstLower();
		        text = field.default || "",
		        help = field.help || "",
		        content = this.createShowTypeService(field);
		
		    if(field.link) {
		        html = html.replace("{href}", field.link);
		    } else {
		        html = html.replace('<a href="{href}" target="_blank">help</a>', "");
		    }
		
		    html = html.replace("{helptext}", help);
		
		    html = html.replace("{label}", field.label);
			html = this.parse(html);
			html.querySelector(".f_value").appendChild(content);

		    return html;
		},
		
		createRows: function(fields) {
		    
		    var headerH = '<div class="t_header"></div>',
		        bodyH = '<div class="t_body"></div>',
		        html = [],
		        info = '<div class="t_info" id="{id}"></div>',
		        detail = '<div class="t_row" name="{name}"></div>',
		        contentHeader = [],
		        contentBody = [];
		    
		    for(var i = 0; i < fields.length; i++) {
		        var row = [];
		        for(var j = 0; j < fields[i].length; j++) {
		
		            var detailD = this.parse(detail.replace("{name}", fields[i][j].label));
		                detailD.appendChild(this.createShowTypeService(fields[i][j]));
		            if(fields[i][j].isHeader) {
		                contentHeader.push(detailD);
		            } else {
		                row.push(detailD);
		            }
		        }
		        
		        if(row.length) {
		            contentBody.push(row);
		        }
		    }
		    
		    var t_info_h = null;
		    var t_info_b = null;
		    
		    if(contentHeader.length != 0) {
		        t_info_h = this.parse(info);
		        
		        for(var i = 0; i < contentHeader.length; i++) {
		            t_info_h.appendChild(contentHeader[i]);
		        }
		        header = this.parse(headerH);
		        header.appendChild(t_info_h);
		        html.push(header);
		    }
		    
		    if(contentBody.length != 0) {
		        for(var i = 0; i < contentBody.length; i++) {
		            t_info_b = this.parse(info);
		            
		            for(var j = 0; j < contentBody[i].length; j++) {
		                t_info_b.appendChild(contentBody[i][j]);
		            }
		            body = this.parse(bodyH);
		            body.appendChild(t_info_b);
		            html.push(body);
		        }
		    }
		    
		    return html;
		},
		    
		createContent: function(fields, style) {
		    var html = [];
		
		    if(!fields) {
		        return html;
		    }
		    if(style && style != this.default.style) {
		        html = this.createRows(fields);
		    } else {
		        for(var i = 0; i < fields.length; i++) {
		            var field = fields[i];
		            html.push(this.createField(field));
		        }
		    }
		    return html;
		},
		
		createButton: function(label, style) {
		    var html = '<div class="{pre}_button" id={0}>{1}</div>';
		    html = html.replace("{pre}", style);
		    html = html.replace("{0}", label.toLowerCase()).replace("{1}", label);
		    return this.parse(html);
		},
		
		createSection: function(obj) {

			obj.style = !obj.style || obj.style == this.default.style ? this.default.style : "t";
			
			var fTitleContent = '<div class="s_fold"><div class="fold"></div></div>' +
							'<div class="default">{title}</div>', 
	    
	        tTitleContent = '<div class="s_fold"><div class="fold"></div></div>' +
	                        '<div class="default">{title}</div>' +
	                        '<div class="row_count">{count}</div>',
	                        
	        html = '<div class="section">' +
	                    '<div class="error_info"></div>' +
	                    '<div class="title">' +
	                    (obj.style == "f" ? fTitleContent : tTitleContent) +
	                    '</div>' + 
	                    '<div class="content"></div>' +
	                    '<div class="{pre}_action"></div>' + 
	                '</div>';

		    html = html.replace("{pre}", obj.style);
		    html = html.replace("{title}", obj.title || "");
		    html = html.replace("{count}", obj.count || "");
		    html = this.parse(html);

	    	html.querySelector(".s_fold div").addEventListener("click", clt.default.action.foldable, false);

		    var contents = this.createContent(obj.fields, obj.style),
		    	buttons = '', content = html.querySelector(".content");
		
		    if(Object.prototype.toString.call(contents) === "[object HTMLDivElement]") {
		        content.appendChild(contents);
		    
		    } else {
		        for(var i = 0; i < contents.length; i++) {
		            content.appendChild(contents[i]);
		        }
		    }
		
		    for(var i = 0; i < obj.buttons.length; i++) {
		        html.querySelector("." + obj.style + "_action").appendChild(this.createButton(obj.buttons[i], obj.style));
		    }
		    
		    return html;
		},

		parse: function(html) {
		    var d = document.createElement("div");
		    d.innerHTML = html;
		    return d.firstElementChild;
		}
	},

	action: {
		
	}
}