webpackJsonp([17],{"5XgM":function(e,t,n){(e.exports=n("BkJT")(!1)).push([e.i,"\n.main[data-v-662349f6] .el-form {\n  padding: 20px 20px;\n}\n.main[data-v-662349f6] .el-form .el-input__inner {\n    width: 100%;\n    background: #f4f4f4;\n}\n.main[data-v-662349f6] .el-form .el-textarea__inner {\n    width: 100%;\n    min-height: 150px !important;\n    background: #f4f4f4;\n}\n.main[data-v-662349f6] .el-form .headline h2 {\n    display: inline-block;\n    color: #333333;\n    font-size: 14px;\n    padding-bottom: 24px;\n}\n.main[data-v-662349f6] .el-form .headline h2 span {\n      width: 6px;\n      height: 20px;\n      display: inline-block;\n      background: #2ea0ff;\n      border-radius: 4px;\n      position: relative;\n      top: 5px;\n      margin-right: 10px;\n}\n.main[data-v-662349f6] .el-form .headline .selectContent {\n    margin-left: 25px;\n    display: inline-block;\n    margin-bottom: 25px;\n}\n.main[data-v-662349f6] .el-form .pic {\n    position: absolute;\n    top: 40px;\n    width: 100%;\n    height: 100%;\n    background: #ffffff;\n}\n.main[data-v-662349f6] .el-form .pic img {\n      width: 80px;\n      height: 80px;\n      overflow: hidden;\n}\n.main[data-v-662349f6] .el-form /deep/ .btn {\n    margin-top: 40px;\n    padding-bottom: 290px;\n}\n.main[data-v-662349f6] .el-form /deep/ .btn .el-form-item__content {\n      margin-left: 0 !important;\n      color: #666666;\n      font-size: 14px;\n}\n.main[data-v-662349f6] .el-form /deep/ .btn .el-form-item__content .cancel {\n        margin-left: 150px;\n}\n",""])},g9NV:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("4YfN"),i=n.n(a),l=n("t5DY"),s=n("9rMa"),o=n("myXI"),r={data:function(){return{imgUrl:o.b,imgUpload:o.a,ruleForm:{type:2,fileId:"",url:"",content:""}}},computed:i()({},Object(s.b)(["uploadFileHeader"])),methods:{uploadSuccMsgUrl:function(e,t){this.ruleForm.fileId=e.data.fileId,200===e.status?(this.$message({message:e.message,type:"success"}),this.form.logo=e.data):this.$message({message:e.message,type:"success"})},submitForm:function(e){var t=this;Object(l.a)("goods/save",this.ruleForm).then(function(e){t.$message({message:e.message,type:"success"})})},resetForm:function(){this.$router.push("/giftAdmin/list")}}},m={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"main"},[n("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,"label-width":"100px"}},[n("div",{staticClass:"headline"},[n("h2",[n("span"),e._v("商品图片")])]),e._v(" "),n("el-form-item",{attrs:{label:"商品图片",prop:"name"}},[n("el-upload",{ref:"upload",staticClass:"upload-demo",attrs:{limit:1,drag:"",action:e.imgUpload,headers:e.uploadFileHeader,"on-success":e.uploadSuccMsgUrl,multiple:""}},[e.ruleForm.fileId?n("div",{staticClass:"pic"},[n("img",{attrs:{src:e.imgUrl+e.ruleForm.fileId}})]):e._e(),e._v(" "),n("i",{staticClass:"el-icon-upload"}),e._v(" "),n("div",{staticClass:"el-upload__text"},[e._v("将文件拖到此处，或\n          "),n("em",[e._v("点击上传")])]),e._v(" "),n("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过500kb")])])],1),e._v(" "),n("div",{staticClass:"headline"},[n("h2",[n("span"),e._v("链接设置")])]),e._v(" "),n("el-form-item",{attrs:{label:"链接地址",prop:"linkAddress"}},[n("el-input",{model:{value:e.ruleForm.url,callback:function(t){e.$set(e.ruleForm,"url",t)},expression:"ruleForm.url"}})],1),e._v(" "),n("div",{staticClass:"headline"},[n("h2",[n("span"),e._v("内容设置")])]),e._v(" "),n("el-form-item",{attrs:{label:"文章内容",prop:"contentsArticle"}},[n("el-input",{attrs:{type:"textarea"},model:{value:e.ruleForm.content,callback:function(t){e.$set(e.ruleForm,"content",t)},expression:"ruleForm.content"}})],1),e._v(" "),n("el-form-item",{staticClass:"btn"},[n("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("保存信息")]),e._v(" "),n("el-button",{staticClass:"cancel",on:{click:e.resetForm}},[e._v("取消编辑")])],1)],1)],1)},staticRenderFns:[]};var d=n("/Xao")(r,m,!1,function(e){n("pqvK")},"data-v-662349f6",null);t.default=d.exports},pqvK:function(e,t,n){var a=n("5XgM");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);n("8bSs")("475ce92c",a,!0)}});