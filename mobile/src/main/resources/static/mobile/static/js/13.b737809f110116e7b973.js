webpackJsonp([13],{aw58:function(t,e){},k9Us:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=n("t5DY"),a=n("VgBk"),s=(a.a,{data:function(){return{parentTitle:[]}},components:{Scroll:a.a},created:function(){this.initDate()},methods:{initDate:function(){var t=this;Object(i.a)("/article/menu_list",{type:3}).then(function(e){console.log(e),t.parentTitle=e.data})},toArticleDetails:function(t){this.$router.push("/healthCenter/components/articleDetails/"+t)}}}),o={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{ref:"solution",staticClass:"solution"},[n("scroll",t._l(t.parentTitle,function(e,i){return n("div",{key:i,staticClass:"solution-content"},[n("div",{staticClass:"title"},[n("h2",[n("span"),t._v(t._s(e.name))])]),t._v(" "),n("ul",t._l(e.conList,function(e,i){return n("li",{key:i,on:{click:function(n){t.toArticleDetails(e.id)}}},[n("a",{attrs:{href:"javascript:void(0)"}},[t._v(t._s(e.name))])])}))])}))],1)},staticRenderFns:[]};var c=n("VU/8")(s,o,!1,function(t){n("aw58")},"data-v-516a57de",null);e.default=c.exports}});
//# sourceMappingURL=13.b737809f110116e7b973.js.map