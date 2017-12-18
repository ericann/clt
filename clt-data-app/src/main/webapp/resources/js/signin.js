window.clt = window.clt || {};

clt.default = {
		
	url: window.location.protocol + "//" + window.location.host + "/" + window.location.pathname.split("/")[1],
	type: ["scanqr"],
	scanqr: {
		title: "Login",
		buttons: [],
		fields: [{
			label: "Scan QR",
			type: "img",
			default: "http://localhost:8080/clt/resources/images/loading.gif"
		}]
	},

	currentStep: 0
};

clt.data = {
	companyname: null,
	yourname: null,
	mobile: null,
	email: null,
	accId: null,
	conId: null,
	ticket: null,
};

clt.template = {
		
    createInput: function(field, text) {
        var html = '<input type="{type}" {readonly} name="{name}" required="true" id="{id}" {key}="{value}">';
        
        html = html.replace("{type}", field.type);
        
        html = html.replace("{readonly}", field.readonly ? 'readonly="readonly"' : "");
        
        html = html.replace("{name}", field.label.replace(" ", "").toLowerCase());

        html = html.replace("{id}", field.label);

        html = html.replace("{value}", text);

        if(field.type == "checkbox" && text) {
            html = html.replace("{key}", "checked");
        } else {
            html = html.replace("{key}", "value");
        }

        return html;
    },
    
    createImage: function(label, src) {
        var html = '<div name="{0}" id="{1}"><img src="{2}"></img></div>';

        //Replace name
        html = html.replace("{0}", label.replace(" ", "").toLowerCase());
        //Replace id
        html = html.replace("{1}", label);
        //Replace id
        html = html.replace("{2}", src);

        return html;
    },
	   
	createDIV: function(label, text) {
	    var html = '<div name="{0}" id="{1}">{2}</div>';
	
	    //Replace name
	    html = html.replace("{0}", label.replace(" ", "").toLowerCase());
	    //Replace id
	    html = html.replace("{1}", label);
	    //Replace id
	    html = html.replace("{2}", text);
	
	    return html;
	},
	    
	createContent: function(field) {
	    var html = '<div class="f_field">' +
	                    '<div class="f_label">' +
	                        '<label>{label}</label>' +
	                    '</div>' +
	                    '<div class="f_values" >' +
	                        '<div class="f_value">{content}</div>' +
	                        '<div class="f_link"><a href="{href}" target="_blank">help</a></div>' + 
	                        '<div class="f_help">{helptext}</div>' +
	                    '</div>' +
	                '</div>',
	        name = field.label.replace(" ", "").toLowerCase();
	        text = clt.data[name] || field.default || "",
	        help = field.help || "",
	        content = "";
	
	    switch(field.type) {
			
			case "img":
	            content = this.createImage(field.label, field.default);
	            break;
	        case "div":
	            content = this.createDIV(field.label, field.default);
	            break;
	        default:
	            content = this.createInput(field, text);
	    }
	
	    html = html.replace("{label}", field.label);
	
	    html = html.replace("{content}", content);
	
	    if(field.link) {
	        html = html.replace("{href}", field.link);
	    } else {
	        html = html.replace('<a href="{href}" target="_blank">help</a>', "");
	    }
	
	    html = html.replace("{helptext}", help);
	    
	    return html;
	},
	
	createButton: function(label) {
	    var html = '<div class="f_button" id={0}>{1}</div>';
	    html = html.replace("{0}", label.toLowerCase()).replace("{1}", label);
	    return html;
	},
	
	createSection: function(obj) {
		
	    var html = '<div class="section">' +
	                    '<div class="error_info"></div>' +
	                    '<div class="title">{0}</div>' + 
	                    '<div class="content">' + 
	                    	'{1}' +
	                    '</div>' +
	                    '<div class="f_action">{2}</div>' + 
	                '</div>';
	    html = html.replace("{0}", obj.title || "");
	    var content = '';
	    var buttons = '';
	
	    for(var i = 0; i < obj.fields.length; i++) {
	        var field = obj.fields[i];
	        content += this.createContent(field);
	    }
	
	    for(var i = 0; i < obj.buttons.length; i++) {
	        buttons += this.createButton(obj.buttons[i]);
	    }
	    
	    html = html.replace("{1}", content).replace("{2}", buttons);
	    return clt.action.parseToDom(html);
	},
};

clt.action = {
	next: function() {

		var result = clt.action.validation();

		if(!(result.flag)) {
			return;
		}

		if(clt.default.type[clt.default.currentStep + 1]) {
			clt.default.currentStep++;
		} else {
			clt.default.currentStep = 1;
		}

		if(clt.default.currentStep != 0) {
			currentStepNo = clt.default.currentStep - 1;
			currentStep = clt.default.type[currentStepNo];
			var inputs = document.querySelectorAll(".f_value input");
			for(var i = 0; i < inputs.length; i++) {
				var name = inputs[i].id.replace(" ", "").toLowerCase(),
					type = inputs[i].type,
					v = type == "checkbox" ? inputs[i].checked : inputs[i].value;

				if(clt.data[name] === undefined) {
					continue;
				}

				if(name.indexOf("buttonid") != -1) {
					clt.data.buttonid.push(v);
				} else {
					clt.data[name] = v;
				}
			}
		}
		
		clt.action.removeSection();
		clt.action.changeSection();
	},
	
	refresh: function() {
		document.getElementById("Scan QR").querySelector("img").src = clt.default.scanqr.fields[0].default;
		clt.action.doCall(clt.default.url + "/security/getQR", null,
				function(result) {
					var r = JSON.parse(result);
					document.getElementById("Scan QR").querySelector("img").src = r.url;
					clt.action.doCall(clt.default.url + "/security/accesstoken/" + r.ticket, null,
							function(result) {
								alert("Login Success.");
								window.location.href = "/index";
					}, 
					function(result) {
						alert("Login Failed.");
						console.log("--failed: " + result);
					}, "POST", 120000);
		}, null);
	},

	back: function() {
		clt.default.currentStep--;
		clt.action.removeSection();
		clt.action.changeSection();
	},
	
	doCall: function(url, data, success, error, type, timeout) {
		var obj = {
				url: url,
				data: data,
				success: success,
				error: error,
				timeout: timeout
		};
		
		if(type && type.toLowerCase() == 'post') {
			Ajax.post(obj);
		} else {
			Ajax.get(obj);
		}
	},

	loading: function() {
		//var html = '<div class="loading" id="loading"></div>';
		var d = document.createElement("div");
		d.classList.add("loading");
		d.id = "loading";
		//var newdom = clt.action.parseToDom(html);
		document.body.appendChild(d);
	},
	
	clearDomById: function(id) {
		var d = document.getElementById(id);
		if(d) {
			d.outerHTML = "";
		}
	},
	
	parseToDom: function(domStr) {
		var d = document.createElement("div");
		d.innerHTML = domStr;
		return d.childNodes;
	},

	removeSection: function() {
		var sections = document.getElementsByClassName("section");
		if(sections.length != 0) {
			document.body.removeChild(sections[sections.length - 1]);
		}
	},

	changeSection: function() {
		
		clt.action.removeSection();
        //可扩展功能，tab切换清除数据
        if(clt.default.clear) {
        	clt.action.reset();
        }
        
        var currentStep = clt.default.type[clt.default.currentStep];
        var html = clt.template.createSection(clt.default[currentStep]);
        
        document.body.appendChild(html.item(0));
        
        if(clt.default[currentStep].buttons) {
            var buttons = clt.default[currentStep].buttons;

            for(var i = 0; i < buttons.length; i++) {
                var b = document.getElementById(buttons[i].toLowerCase());

                if(b) {
                    b.addEventListener("click", clt.action[buttons[i].toLowerCase()], false);
                    //b.nextSibling.addEventListener("mouseover", function(){alert("msg")}, false);
                }
            }
        }
	},
	
	checkWechatStatus: function(ticket) {
		clt.action.doCall({
			url: clt.default.url + "/wechat/bind/" + ticket
		});
	},

	validation: function() {
		var inputs = document.querySelectorAll(".content input"),

			currentStep = clt.default.type[clt.default.currentStep],
			fields = clt.default[currentStep].fields,

			result = {flag: true, msg: ""},
			
			//临时跳过
			rtH = document.getElementById("Refresh Type");
			refreshCheckbox = rtH ? rtH.checked : undefined;
			;
			
		for(var i = 0; i < fields.length; i++) {

			var input = document.getElementById(fields[i].label);
			if(!input || input.tagName == "DIV") {
				continue;
			}

			input.classList.remove("error");

			if(input.type == "checkbox" && fields[i].value) {
				result = clt.action.checkRequired(input.checked, fields[i].value, fields[i].error);
			}

			if(input.type == "text") {
				if(refreshCheckbox === false && (input.id == "App ID" || input.id == "App Secret")) {
					continue;
				}
				
				result = clt.action.checkLength(input.value, fields[i].minlength, fields[i].maxlength, fields[i].label);
			}

			if(!(result.flag)) {

				input.classList.add("error");
				input.parentElement.parentElement.parentElement.parentElement.parentElement.firstElementChild.innerHTML = result.msg;
				break;
			}
		}

		return result;
	},

	checkRequired: function(value, claim, error) {
		var result = {flag: true, msg: ""};

		if(value != claim) {
			result.flag = false;
			result.msg = error;
		}

		return result;
	},

	checkLength: function(text, min, max, label) {
		var result = {flag: true, msg: ""};

		if(min == -1 && max != -1) {
			if(text.length > max) {
				result.msg = "Field of " + label + " max length is " + max + ".";
				result.flag = false;
			}
		} else if(max == -1 && min != -1) {
			if(text.length < min) {
				result.msg = "Field of " + label + " min length is " + min + ".";
				result.flag = false;
			}
		} else if(max != -1 && min != -1) {
			if(text.length < min || text.length > max) {
				result.msg = "Field of " + label + " max length is " + max + ", min length is " + min + ".";
				result.flag = false;
			}
		}

		return result;
	}
};

clt.init = function() {
	clt.action.changeSection();
	clt.action.refresh();
}