var e={"source/Math":function(c,b){b.add=function(a,b){return a+b}}},f;f=function(){function c(a){if(b.hasOwnProperty(a)){if(b[a]===-1)throw Error("Cyclic dependency found when requiring: "+a);return b[a]}b[a]=-1;if(e[a]==null)throw Error("Module not found: "+a);var d={},g=e[a](c,d);return b[a]=g!=null?g:d}var b={};return c}()("source/Math");console.log("Working example project 12");console.log(f.add(Math.random(),10));