webpackJsonp([20],{"5ACN":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("t5DY"),i=a("VgBk"),c=a("PJh5"),s=a.n(c),r=(i.a,{data:function(){return{name:"",createTime:"",content:""}},components:{Scroll:i.a},created:function(){this.initDate()},methods:{initDate:function(){var t=this;Object(n.a)("/article/content/"+this.$route.params.id).then(function(e){console.log(e),t.name=e.data.name;for(var a=0;a<e.data.length;a+=1)e.data[a].createTime=s()(e.data[a].createTime).format("YYYY-MM-DD");t.content=e.data.content})}}}),o={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"articleDetails"},[n("scroll",[n("div",{staticClass:"head"},[n("a",{attrs:{href:"javascript:void(0)"},on:{click:function(e){t.$router.push("/healthCenter")}}},[n("img",{attrs:{src:a("DtD7"),alt:""}})]),t._v(" "),n("h2",[t._v("文章详情")])]),t._v(" "),n("div",{staticClass:"articleDetails-content"},[n("div",{staticClass:"title"},[n("h2",[t._v(t._s(t.name))]),t._v(" "),n("p",{staticClass:"time"},[t._v(t._s(t.createTime))])]),t._v(" "),n("div",{staticClass:"articleContent"},[t._v("\n                "+t._s(t.content)+"\n            ")])])])],1)},staticRenderFns:[]};var l=a("VU/8")(r,o,!1,function(t){a("guFL")},"data-v-25c5fe2c",null);e.default=l.exports},guFL:function(t,e){}});
//# sourceMappingURL=20.e7b98508cce471a5bb25.js.map