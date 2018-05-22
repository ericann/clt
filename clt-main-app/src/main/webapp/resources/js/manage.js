window.clt = window.clt || {};

clt.default = {

    baseURL: window.document.location.pathname.substring(0, window.document.location.pathname.substring(1).indexOf("/") + 1),
    objects: {
        welcome: {
            title: "Welcome",
            buttons: []
        },
        sfdc: {
            title: "Sfdc",
            buttons: []
        },
        wechat: {
            title: "Wechat",
            buttons: []
        }   
    },

    type: ["login", "welcome", "sfdc", "wechat"],
    current: 0,
    
    field: function() {
        return {
            label: null,
            default: null,
            type: null
        }
    },
    
    object: function() {
        return {
            title: null,
            buttons: [],
            fields: []
        }
    }
};

clt.data = {};

clt.metadata = {};

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
        var section = clt.action.parseToDom(html),
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

    createTopMenus: function(objs) {
        var html = '<div class="menu">' + 
                        '<div class="error_msg"></div>' + 
                        '<div class="t_title">{title}</div>' + 
                        '<div class="t_content">' + 
                            '{info}' +
                        '</div>' + 
                        //'<div class="t_action">{action}</div>' + 
                    '</div>';
        var fields = [];
        var keys = Object.keys(objs);
        for(var i = 0; i < keys.length; i++) {
            fields[i] = {
                label: i + "",
                default: objs[keys[i]].title,
                type: "a",
            }
        }

        html = html.replace("{title}", "Menu");
        var content = '';
    
        for(var i = 0; i < fields.length; i++) {
            var field = fields[i];
            content += this.createContent(field);
        }
        html = html.replace("{info}", content);
        return this.parseToDom(html);
    },

    parseToDom: function(html) {
        var d = document.createElement("div");
        d.innerHTML = html;
        return d.children;
    }

};

clt.action = {
    
    resetCurrentStep: function() {
        var select = document.getElementById("type");
        if(select) {
            clt.default.currentStep = parseInt(select.value);
            clt.action.changeSection();
        }
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
    
    save: function() {
        var step = clt.default.type[clt.default.currentStep],
            fields = clt.default[step].fields;
            
        for(var i = 0; i < fields.length; i++) {
            var f = fields[i],
                d = document.getElementById(f.label);
            clt.data[f.label] = d.value;
        }
    },
    
    reset: function() {
        for(var f in clt.data) {
            if(f == "flow") continue;
            clt.data[f] = null;
        }
    },
    
    setData: function(currentStep) {
        //clt.data.url = clt.default.url.replace("{0}", clt.default.orgType) + clt.default[currentStep].url;
        clt.data.pre_domain = clt.default.orgType;
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
        return d.childNodes[0];
    },

    removeSection: function() {
        var sections = document.getElementsByClassName("section");
        for(var i = sections.length - 1; i >= 0; i--) {
            document.body.removeChild(sections[i]);
        }
        //document.body.removeChild(sections[sections.length - 1]);
        //document.body.innerHTML = document.body.innerHTML.replace(document.body.lastElementChild.outerHTML, "");
    },

    changeSection: function() {
        clt.action.removeSection();
        //可扩展功能，tab切换清除数据
        if(clt.default.clear) {
            clt.action.reset();
        }
        
        var currentStep = clt.default.type[clt.default.currentStep];
        var html = clt.template.createSection(currentStep);
        
        //set Data
        clt.action.setData(currentStep);
        //document.body.innerHTML += html;
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
        }
    }
};

clt.init = function() {

}
