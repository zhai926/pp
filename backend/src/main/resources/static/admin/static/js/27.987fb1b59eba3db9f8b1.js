webpackJsonp([27],{Bu8o:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=t("4YfN"),i=t.n(a),o=t("t5DY"),r=t("9rMa"),l={data:function(){return{editorOption:{placeholder:"请输入文章内容"},ruleForm:{id:"",menuId:"",name:"",content:""}}},computed:i()({},Object(r.b)(["uploadFileHeader"])),created:function(){this.initDate()},methods:{initDate:function(){var n=this;this.ruleForm.id=this.$route.params.id,Object(o.a)("/article_content/detail/"+this.ruleForm.id).then(function(e){console.log(e),n.ruleForm.name=e.data.name,n.ruleForm.content=e.data.content,n.ruleForm.menuId=e.data.menuId})},submitForm:function(){var n=this;Object(o.a)("/article_content/save",this.ruleForm).then(function(e){n.$message({message:e.message,type:"success"}),n.initDate()})},resetForm:function(){this.$router.push("/enterpriseArticle/list")}}},d={render:function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",{staticClass:"main"},[t("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:n.ruleForm,"label-width":"100px"}},[t("div",{staticClass:"headline"},[t("h2",[t("span"),n._v("编辑企业文章")])]),n._v(" "),t("el-form-item",{attrs:{label:"文章名",prop:"linkAddress"}},[t("el-input",{model:{value:n.ruleForm.name,callback:function(e){n.$set(n.ruleForm,"name",e)},expression:"ruleForm.name"}})],1),n._v(" "),t("el-form-item",{attrs:{label:"文章内容",prop:"contentsArticle"}},[t("quill-editor",{ref:"myQuillEditor",staticClass:"appwrapper",attrs:{options:n.editorOption},model:{value:n.ruleForm.content,callback:function(e){n.$set(n.ruleForm,"content",e)},expression:"ruleForm.content"}})],1),n._v(" "),t("el-form-item",{staticClass:"btn"},[t("el-button",{attrs:{type:"primary"},on:{click:n.submitForm}},[n._v("保存信息")]),n._v(" "),t("el-button",{staticClass:"cancel",on:{click:n.resetForm}},[n._v("取消编辑")])],1)],1)],1)},staticRenderFns:[]};var m=t("/Xao")(l,d,!1,function(n){t("LQrg")},"data-v-2db9df79",null);e.default=m.exports},F4d8:function(n,e,t){(n.exports=t("BkJT")(!1)).push([n.i,"\n.main[data-v-2db9df79] .el-form {\n  padding: 20px 20px;\n}\n.main[data-v-2db9df79] .el-form .el-input__inner {\n    width: 100%;\n    background: #f4f4f4;\n}\n.main[data-v-2db9df79] .el-form .el-textarea__inner {\n    width: 100%;\n    min-height: 150px !important;\n    background: #f4f4f4;\n}\n.main[data-v-2db9df79] .el-form .headline h2 {\n    display: inline-block;\n    color: #333333;\n    font-size: 14px;\n    padding-bottom: 24px;\n}\n.main[data-v-2db9df79] .el-form .headline h2 span {\n      width: 6px;\n      height: 20px;\n      display: inline-block;\n      background: #2ea0ff;\n      border-radius: 4px;\n      position: relative;\n      top: 5px;\n      margin-right: 10px;\n}\n.main[data-v-2db9df79] .el-form .headline .selectContent {\n    margin-left: 25px;\n    display: inline-block;\n    margin-bottom: 25px;\n}\n.main[data-v-2db9df79] .el-form .pic {\n    position: absolute;\n    top: 40px;\n    width: 100%;\n    height: 100%;\n    background: #ffffff;\n}\n.main[data-v-2db9df79] .el-form .pic img {\n      width: 80px;\n      height: 80px;\n      overflow: hidden;\n}\n.main[data-v-2db9df79] .el-form /deep/ .btn {\n    margin-top: 40px;\n    padding-bottom: 290px;\n}\n.main[data-v-2db9df79] .el-form /deep/ .btn .el-form-item__content {\n      margin-left: 0 !important;\n      color: #666666;\n      font-size: 14px;\n}\n.main[data-v-2db9df79] .el-form /deep/ .btn .el-form-item__content .cancel {\n        margin-left: 150px;\n}\n",""])},LQrg:function(n,e,t){var a=t("F4d8");"string"==typeof a&&(a=[[n.i,a,""]]),a.locals&&(n.exports=a.locals);t("8bSs")("5625d1b8",a,!0)}});