window.clt = window.clt || {};

clt.default = {
		
	url: window.location.protocol + "//" + window.location.host + "/" + window.location.pathname.split("/")[1],
	type: ["introduction", "liveagent", "wechat", "confirm", "congratulations"],
	introduction: {
		title: "Introduction",
		buttons: ["Next"],
		fields: [{
			label: "Matters need attention",
			type: "div",
			default: "1. This service is based on Wechat and Salesforce.com.</br>2. This service provide you just for demo/test now, other contact us please.</br>3. You will be limited for 3 connection in this service, contact us if you have requirement.</br>4. You should ready for Wechat and SFDC liveagent configuration before use this service.</br>5. You will be leave some information can not be delete, but we'll be provide delete service in next time.<p style=\"color: red; font-size: bold;\">6. App Id and App Secret are required to refresh access_token if you refresh it by us.</p>7. Welcome you to use this service!"
		}, {
			label: "Agreement",
			type: "checkbox",
			value: true,
			error: "You must agree it with notices."
		}]
	},

	wechat: {
		title: "Wechat Infor.",
		buttons: ["Back", "Next"],
		fields: [{
			label: "Account ID",
			type: "selectInput",
			help: "Id of official wechat.Click link for help.",
			link: "help/wechat.html",
			minlength: 15,
			maxlength: -1,
			readonly: false
		}, {
			label: "App ID",
			type: "input",
			help: "Username of official wechat.Click link for help.",
			link: "help/wechat.html",
			minlength: 15,
			maxlength: -1,
			readonly: false
		}, {
			label: "App Secret",
			type: "input",
			help: "Password of official wechat.Click link for help.</br>App Id and App Secret are required to refresh access_token.if you refresh it by yourself, you don't neet write them here.",
			link: "help/wechat.html",
			minlength: 15,
			maxlength: -1,
			readonly: false
		}, {
			label: "Token",
			type: "input",
			help: "Custom field of official wechat security.Click link for help.",
			link: "help/wechat.html",
			minlength: 8,
			maxlength: -1,
			readonly: false
		}, {
			label: "Refresh Type",
			type: "checkbox",
			help: "Select true stand for refresh by us, else by yourself.",
			readonly: false
		}, {
			label: "Refresh Now",
			type: "checkbox",
			help: "Refresh Wechat token right now.You must select it if refresh by us.",
			readonly: false
		}]
	},
	liveagent: {
		title: "Live Agent Infor.",
		buttons: ["Add", "Back",  "Next"],
		fields: [{
			label: "Organization ID",
			type: "selectInput",
			help: "Your org id.Click link for help.",
			link: "help/la.html",
			minlength: 15,
			maxlength: 15,
			readonly: false
		}, {
			label: "Deployment ID",
			type: "input",
			help: "Click link for help.",
			link: "help/la.html",
			minlength: 15,
			maxlength: 15,
			readonly: false
		}, {
			label: "End Point",
			type: "input",
			help: "The address of live agent.Click link for help.",
			link: "help/la.html",
			minlength: 15,
			maxlength: -1,
			readonly: false
		}, {
			label: "Button ID",
			type: "input",
			help: "Click link for help.",
			link: "help/la.html",
			minlength: 15,
			maxlength: 15,
			readonly: false
		}]
	},
	confirm: {
		title: "Confirmation.",
		buttons: ["Confirm", "Back",  "Cancel"],
		fields: [{
			label: "Account ID",
			type: "input",
			readonly: true
		}, {
			label: "App ID",
			type: "input",
			readonly: true
		}, {
			label: "App Secret",
			type: "input",
			readonly: true
		}, {
			label: "Token",
			type: "input",
			readonly: true
		}, {
			label: "Organization ID",
			type: "input",
			readonly: true
		}, {
			label: "Deployment ID",
			type: "input",
			readonly: true
		}, {
			label: "End Point",
			type: "input",
			readonly: true
		}, {
			label: "Button ID",
			type: "input",
			readonly: true
		}]
	},

	congratulations: {
		title: "Congratulations",
		buttons: ["Complete"],
		fields: [{
			label: "tips",
			type: "div",
			default: "Congratulations! You can use this service now, send message from your wechat official account to feel what's easy.</br>Remind again, the connections limit for 3 now, you can contact us to increase it."
		}]
	},
	currentStep: 0
};

clt.data = {
	accountid: null,
	appid: null,
	appsecret: null,
	token: null,
	organizationid: null,
	deploymentid: null,
	endpoint: null,
	buttonid: [],
	companyname: null,
	yourname: null,
	mobile: null,
	email: null,
	refreshtype: null,
	refreshnow: null,
	accId: null
};

clt.template = {
default: {
    classname: "section",
    style: "f",
    styles: ["f", "t"]
},

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

    return this.parseToDom(html);
},

createImage: function(label, src) {
    var html = '<div name="{0}" id="{1}"><img src="{2}" onload="showListService(this)"></img></div>';

    //Replace name
    html = html.replace("{0}", label.replace(" ", "").toLowerCase());
    //Replace id
    html = html.replace("{1}", label);
    //Replace id
    html = html.replace("{2}", src);

    return this.parseToDom(html);
},
   
createDIV: function(label, text) {
    var html = '<div name="{0}" id="{1}">{2}</div>';

    //Replace name
    html = html.replace("{0}", label.replace(" ", "").toLowerCase());
    //Replace id
    html = html.replace("{1}", label);
    //Replace id
    html = html.replace("{2}", text);

    return this.parseToDom(html);
},

createHref: function(label, text) {
    var html = '<a name="{0}" id="{1}" href="#" >{3}</a>';
    
    //Replace name
    html = html.replace("{0}", label.replace(" ", "").toLowerCase());
    //Replace id
    html = html.replace("{1}", label);
    //Replace id
    html = html.replace("{3}", text);

    return this.parseToDom(html);
},

createSelectInput: function(field) {
    var html = '<select name="{0}" id="{1}" >{3}</select>';
    
    //Replace name
    html = html.replace("{0}", field.label.replace(" ", "").toLowerCase());
    //Replace id
    html = html.replace("{1}", field.label);
    //Replace id
    html = html.replace("{3}", field.default);

    return this.parseToDom(html);
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
            content = this.createHref(field.label, field.default);
            break;
        case "selectInput":
            content = this.createSelectInput(field);
            break;
        default:
            content = this.createInput(field, text);
    }
    
    return content;
},
    
createField: function(field) {
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
    var htmlDom = this.parseToDom(html)[0],
        f_valueD = htmlDom.getElementsByClassName("f_values")[0];
    
    f_valueD.innerHTML = "";
    f_valueD.appendChild(content[0]);
    //html = html.replace("{content}", content);

    return htmlDom;
},

createRows: function(fields) {
    
    var headerH = '<div class="t_header"></div>',
        bodyH = '<div class="t_body"></div>',
        html = [],
        info = '<div class="t_info" id="{id}">' + 
                    '{details}' + 
                '</div>',
        detail = '<div class="t_row" name="{name}">{value}</div>',
        contentHeader = [],
        contentBody = [];
    
    for(var i = 0; i < fields.length; i++) {
        var row = [];
        for(var j = 0; j < fields[i].length; j++) {

            var detailD = this.parseToDom(detail.replace("{name}", fields[i][j].label))[0];
                detailD.innerText = "";
                detailD.appendChild(this.createShowTypeService(fields[i][j])[0]);
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
        t_info_h = this.parseToDom(info)[0];
        t_info_h.innerHTML = "";
        
        for(var i = 0; i < contentHeader.length; i++) {
            t_info_h.appendChild(contentHeader[i]);
        }
        header = this.parseToDom(headerH)[0];
        header.appendChild(t_info_h);
        html.push(header);
    }
    
    if(contentBody.length != 0) {
        for(var i = 0; i < contentBody.length; i++) {
            t_info_b = this.parseToDom(info)[0];
            t_info_b.innerHTML = "";
            
            for(var j = 0; j < contentBody[i].length; j++) {
                t_info_b.appendChild(contentBody[i][j]);
            }
            body = this.parseToDom(bodyH)[0];
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

createButton: function(label) {
    var html = '<div class="f_button" id={0}>{1}</div>';
    html = html.replace("{0}", label.toLowerCase()).replace("{1}", label);
    return html;
},

createButtons: function(buttons) {
    
},

createSection: function(obj) {
    
    obj.style = !obj.style || obj.style == this.default.style ? this.default.style : "t";
    
    var fTitleContent = '<div class="default">{title}</div>' +
    					'<div class="row_count">{count}</div>',
    
        tTitleContent = '<div class="s_fold"><button></button></div>' +
                        '<div class="default">{title}</div>' +
                        '<div class="row_count">{count}</div>',
                        
        html = '<div class="{classname}">' +
                    '<div class="error_info"></div>' +
                    '<div class="title">' +
                    (obj.style == "f" ? fTitleContent : tTitleContent) +
                    '</div>' + 
                    '<div class="content">' + 
                        '{content}' +
                    '</div>' +
                    '<div class="{pre}_action">{buttons}</div>' + 
                '</div>';
    html = html.replace("{pre}", obj.style);
    html = html.replace("{classname}", obj.classname || this.default.classname);
    html = html.replace("{classname}", obj.style || this.default.style);
    html = html.replace("{title}", obj.title || "");
    html = html.replace("{count}", obj.count || "");
    
    var contents = this.createContent(obj.fields, obj.style || this.default.style);
    var buttons = '';

    for(var i = 0; i < obj.buttons.length; i++) {
        buttons += this.createButton(obj.buttons[i]);
    }
    
    html = html.replace("{buttons}", buttons);
    var section = clt.action.parseToDom(html)[0],
        content = section.getElementsByClassName("content")[0];
    content.innerHTML = "";
    
    if(Object.prototype.toString.call(contents) === "[object HTMLDivElement]") {
        content.appendChild(contents);
    
    } else {
        for(var i = 0; i < contents.length; i++) {
            content.appendChild(contents[i]);
        }
    }
    return section;
},

createSections: function(obj) {
    
},

parseToDom: function(html) {
    var d = document.createElement("div");
    d.innerHTML = html;
    return d.children;
}

};

clt.action = {
	next: function() {

		var result = clt.action.validation();

		if(!(result.flag)) {
			//alert(result.msg);
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
			var inputs = document.querySelectorAll(".f_values input");
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

	back: function() {
		clt.default.currentStep--;
		clt.action.removeSection();
		clt.action.changeSection();
	},

	save: function() {
		
	},

	add: function() {
		//console.debug("In add");

		var p = event.target.parentElement,
			pp = p.parentElement,
			inputDIV = p.previousElementSibling,
			clone = inputDIV.cloneNode(true),
			input = clone.querySelector("#Button\\ ID");

		input.value = "";
		input.id = "Button ID" + Math.random();
		pp.insertBefore(clone, p);
	},
	
	doCall: function(url, data, success, error) {
		var obj = {
				url: url,
				data: data,
				success: success,
				error: error
		};
		Ajax.post(obj);
	},

	confirm: function() {
		var obj = {
			url: clt.default.url+ "/larwint/insert/bc_b",
			data: JSON.stringify(clt.data),
			success: success,
			error: error
		};
		Ajax.post(obj);
		clt.action.loading();

		function success(result) {
			//console.debug("Ajax post success");
			//console.debug(res);
			clt.action.clearDomById("loading");
			var r = JSON.parse(result);
			if(r.errCode == 0) {
				clt.action.next();
			} else {
				var error = document.getElementsByClassName("error_title");
				if(error) {
					error[0].innerHTML = r.errMsg;
				}
			}
			
		}

		function error(res) {
			clt.action.clearDomById("loading");
			var error = document.getElementsByClassName("error_title");
			if(error) {
				error[0].innerHTML = "Send Request failed, try again later please.";
			}
			//console.debug("Ajax post error");
			//console.debug(res);
		}
	},
	
	complete: function() {
		window.location.href = clt.default.url + "/pages/help/larw.html";
	},

	cancel: function() {
		clt.action.reset();
		//clt.default.currentStep = 0;
		clt.action.next();
	},

	reset: function() {
		for(var f in clt.data) {
			if(f != "buttonid") {
				clt.data[f] = null;
			} else {
				clt.data.buttonid.splice(0, clt.data[f].length);
			}
			
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
		document.body.removeChild(sections[sections.length - 1]);
		//document.body.innerHTML = document.body.innerHTML.replace(document.body.lastElementChild.outerHTML, "");
	},

	changeSection: function() {
		
		var currentStep = clt.default.type[clt.default.currentStep];
		var html = clt.template.createSection(clt.default[currentStep]);
		document.body.appendChild(html);

		if(clt.default[currentStep].buttons) {
			var buttons = clt.default[currentStep].buttons;

			for(var i = 0; i < buttons.length; i++) {
				var b = document.getElementById(buttons[i].toLowerCase());

				if(b) {
					b.addEventListener("click", clt.action[buttons[i].toLowerCase()], false);
					//b.nextSibling.addEventListener("mouseover", function(){alert("msg")}, false);
				}
			}

			/*
			help帮助
			var spans = document.querySelectorAll(".content span");
			for(var i = 0; i < spans.length; i++) {
				spans[i].addEventListener("mouseover", clt.action.helpContent, false);
				spans[i].addEventListener("mouseout", clt.action.clearHelp, false);
			}*/
		}
	},

	helpContent: function() {
		//console.debug("In helpContent");
		var input = event.target.previousSibling,
			currentStep = clt.default.currentStep,
			html = '<div class="help" style="left: {0}px; top: {1}px; position: absolute;">{2}</div>',
			fields = clt.default[clt.default.type[currentStep]].fields,
			text = '';

		for(var i = 0; i < fields.length; i++) {
			if(input.id == fields[i].label) {
				text = fields[i].help;
				break;
			}
		}

		document.body.innerHTML += html.replace("{2}", text).replace("{0}", event.clientX).replace("{1}", event.clientY);
	},

	clearHelp: function() {
		//console.debug("In clearHelp");
		var helps = document.getElementsByClassName("help");
		for(var i = 0; i < helps.length; i++) {
			document.body.removeChild(helps[i]);
		}
		
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
				input.parentElement.parentElement.parentElement.parentElement.firstElementChild.innerHTML = result.msg;
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
}
