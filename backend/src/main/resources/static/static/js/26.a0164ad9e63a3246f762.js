webpackJsonp([26],{QmHK:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=n("4YfN"),i=n.n(a),l=n("t5DY"),o=n("myXI"),s=n("9rMa"),r={data:function(){return{imgUrl:o.b,imgUpload:o.a,editorOption:{},ruleForm:{type:1,id:"",fileId:"",url:"",content:""}}},computed:i()({},Object(s.b)(["uploadFileHeader"])),created:function(){this.initDate()},methods:{initDate:function(){var t=this;this.ruleForm.id=this.$route.params.id,Object(l.a)("/goods/detail/"+this.ruleForm.id).then(function(e){console.log(e),t.ruleForm.fileId=e.data.fileId,t.ruleForm.url=e.data.url})},uploadSuccMsgUrl:function(t,e){this.ruleForm.fileId=t.data.fileId,200===t.status?(this.$message({message:t.message,type:"success"}),this.form.logo=t.data):this.$message({message:t.message,type:"success"})},submitForm:function(){var t=this;Object(l.a)("goods/save",this.ruleForm).then(function(e){t.$message({message:e.message,type:"success"}),t.initDate()})},resetForm:function(){this.$router.push("/commodityInfo/list")}}},d={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"main"},[n("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:t.ruleForm,"label-width":"100px"}},[n("div",{staticClass:"headline"},[n("h2",[n("span"),t._v("商品图片")])]),t._v(" "),n("el-form-item",{attrs:{label:"商品图片",prop:"name"}},[n("el-upload",{ref:"upload",staticClass:"upload-demo",attrs:{limit:1,drag:"",action:t.imgUpload,headers:t.uploadFileHeader,"on-success":t.uploadSuccMsgUrl,multiple:""}},[t.ruleForm.fileId?n("div",{staticClass:"pic"},[n("img",{attrs:{src:t.imgUrl+t.ruleForm.fileId}})]):t._e(),t._v(" "),n("i",{staticClass:"el-icon-upload"}),t._v(" "),n("div",{staticClass:"el-upload__text"},[t._v("将文件拖到此处，或\n          "),n("em",[t._v("点击上传")])]),t._v(" "),n("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("只能上传jpg/png文件，且不超过500kb")])])],1),t._v(" "),n("div",{staticClass:"headline"},[n("h2",[n("span"),t._v("链接设置")])]),t._v(" "),n("el-form-item",{attrs:{label:"链接地址",prop:"linkAddress"}},[n("el-input",{model:{value:t.ruleForm.url,callback:function(e){t.$set(t.ruleForm,"url",e)},expression:"ruleForm.url"}})],1),t._v(" "),n("el-form-item",{staticClass:"btn"},[n("el-button",{attrs:{type:"primary"},on:{click:t.submitForm}},[t._v("保存信息")]),t._v(" "),n("el-button",{staticClass:"cancel",on:{click:t.resetForm}},[t._v("取消编辑")])],1)],1)],1)},staticRenderFns:[]};var m=n("/Xao")(r,d,!1,function(t){n("tKHN")},"data-v-3710757c",null);e.default=m.exports},"dtk/":function(t,e,n){(t.exports=n("BkJT")(!1)).push([t.i,"\n.main[data-v-3710757c] .el-form {\n  padding: 20px 20px;\n}\n.main[data-v-3710757c] .el-form .el-input__inner {\n    width: 100%;\n    background: #f4f4f4;\n}\n.main[data-v-3710757c] .el-form .el-textarea__inner {\n    width: 100%;\n    min-height: 150px !important;\n    background: #f4f4f4;\n}\n.main[data-v-3710757c] .el-form .headline h2 {\n    display: inline-block;\n    color: #333333;\n    font-size: 14px;\n    padding-bottom: 24px;\n}\n.main[data-v-3710757c] .el-form .headline h2 span {\n      width: 6px;\n      height: 20px;\n      display: inline-block;\n      background: #2ea0ff;\n      border-radius: 4px;\n      position: relative;\n      top: 5px;\n      margin-right: 10px;\n}\n.main[data-v-3710757c] .el-form .headline .selectContent {\n    margin-left: 25px;\n    display: inline-block;\n    margin-bottom: 25px;\n}\n.main[data-v-3710757c] .el-form .pic {\n    position: absolute;\n    top: 40px;\n    width: 100%;\n    height: 100%;\n    background: #ffffff;\n}\n.main[data-v-3710757c] .el-form .pic img {\n      width: 100px;\n      height: 100px;\n      overflow: hidden;\n}\n.main[data-v-3710757c] .el-form /deep/ .btn {\n    margin-top: 40px;\n    padding-bottom: 290px;\n}\n.main[data-v-3710757c] .el-form /deep/ .btn .el-form-item__content {\n      margin-left: 0 !important;\n      color: #666666;\n      font-size: 14px;\n}\n.main[data-v-3710757c] .el-form /deep/ .btn .el-form-item__content .cancel {\n        margin-left: 150px;\n}\n",""])},tKHN:function(t,e,n){var a=n("dtk/");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);n("8bSs")("5932b982",a,!0)}});