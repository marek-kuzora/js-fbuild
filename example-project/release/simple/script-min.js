var f={"simple/app":function(){}};(function(){function g(c,d){var a=h(c,d);if(e.hasOwnProperty(a)){if(e[a]===-1)throw Error("Cyclic dependency found when requiring: "+a);return e[a]}if(f[a]==null)throw Error("Module not found: "+a);var b=a.substr(0,a.lastIndexOf("/"));return e[a]=f[a](function(a){return g(b,a)})}function h(c,d){if(d[0]==="/")return d.substr(1);for(var a=/[0-9a-zA-Z_]+\/\.\.\//,b=c+"/"+d;b.indexOf("../")>-1;)b=b.replace(a,"");return b}var e={};return function(c){return g("",c)}})()("/simple/app");