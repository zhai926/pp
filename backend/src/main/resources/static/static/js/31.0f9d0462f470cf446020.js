webpackJsonp([31],{TO8n:function(e,n,t){(e.exports=t("BkJT")(!1)).push([e.i,"\n.main[data-v-1febbe78] .el-form {\n  padding: 20px 20px;\n}\n.main[data-v-1febbe78] .el-form .el-input__inner {\n    width: 100%;\n    background: #f4f4f4;\n}\n.main[data-v-1febbe78] .el-form .el-textarea__inner {\n    width: 100%;\n    min-height: 150px !important;\n    background: #f4f4f4;\n}\n.main[data-v-1febbe78] .el-form .headline h2 {\n    display: inline-block;\n    color: #333333;\n    font-size: 14px;\n    padding-bottom: 24px;\n}\n.main[data-v-1febbe78] .el-form .headline h2 span {\n      width: 6px;\n      height: 20px;\n      display: inline-block;\n      background: #2ea0ff;\n      border-radius: 4px;\n      position: relative;\n      top: 5px;\n      margin-right: 10px;\n}\n.main[data-v-1febbe78] .el-form .headline .selectContent {\n    margin-left: 25px;\n    display: inline-block;\n    margin-bottom: 25px;\n}\n.main[data-v-1febbe78] .el-form .pic {\n    position: absolute;\n    top: 40px;\n    width: 100%;\n    height: 100%;\n    background: #ffffff;\n}\n.main[data-v-1febbe78] .el-form .pic img {\n      width: 80px;\n      height: 80px;\n      overflow: hidden;\n}\n.main[data-v-1febbe78] .el-form /deep/ .btn {\n    margin-top: 40px;\n    padding-bottom: 290px;\n}\n.main[data-v-1febbe78] .el-form /deep/ .btn .el-form-item__content {\n      margin-left: 0 !important;\n      color: #666666;\n      font-size: 14px;\n}\n.main[data-v-1febbe78] .el-form /deep/ .btn .el-form-item__content .cancel {\n        margin-left: 150px;\n}\n",""])},dl8w:function(e,n,t){var a=t("TO8n");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("8bSs")("3a371a04",a,!0)},g9c1:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=t("4YfN"),i=t.n(a),o=t("t5DY"),r=t("9rMa"),l={data:function(){return{editorOption:{placeholder:"请输入文章内容"},ruleForm:{menuId:"",name:"",content:""}}},computed:i()({},Object(r.b)(["uploadFileHeader"])),created:function(){this.init()},methods:{init:function(){this.ruleForm.menuId=this.$route.params.id},submitForm:function(){var e=this;Object(o.a)("/article_content/save",this.ruleForm).then(function(n){e.$message({message:n.message,type:"success"})})},resetForm:function(e){this.$router.push("/enterpriseArticle/list")}}},s={render:function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{staticClass:"main"},[t("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,"label-width":"100px"}},[t("div",{staticClass:"headline"},[t("h2",[t("span"),e._v("添加企业文章")])]),e._v(" "),t("el-form-item",{attrs:{label:"文章名",prop:"linkAddress"}},[t("el-input",{model:{value:e.ruleForm.name,callback:function(n){e.$set(e.ruleForm,"name",n)},expression:"ruleForm.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"文章内容",prop:"contentsArticle"}},[t("quill-editor",{ref:"myQuillEditor",staticClass:"appwrapper",attrs:{options:e.editorOption},model:{value:e.ruleForm.content,callback:function(n){e.$set(e.ruleForm,"content",n)},expression:"ruleForm.content"}})],1),e._v(" "),t("el-form-item",{staticClass:"btn"},[t("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("保存信息")]),e._v(" "),t("el-button",{staticClass:"cancel",on:{click:e.resetForm}},[e._v("取消编辑")])],1)],1)],1)},staticRenderFns:[]};var m=t("/Xao")(l,s,!1,function(e){t("dl8w")},"data-v-1febbe78",null);n.default=m.exports}});