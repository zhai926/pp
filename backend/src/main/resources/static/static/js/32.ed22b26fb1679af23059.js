webpackJsonp([32],{"2bJb":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("4YfN"),i=a.n(n),l=a("t5DY"),s=a("9rMa"),o=a("myXI"),r={data:function(){return{imgUrl:o.b,imgUpload:o.a,ruleForm:{type:1,fileId:"",url:""}}},computed:i()({},Object(s.b)(["uploadFileHeader"])),methods:{uploadSuccMsgUrl:function(e,t){this.ruleForm.fileId=e.data.fileId,200===e.status?(this.$message({message:e.message,type:"success"}),this.form.logo=e.data):this.$message({message:e.message,type:"success"})},submitForm:function(e){var t=this;Object(l.a)("goods/save",this.ruleForm).then(function(e){t.$message({message:e.message,type:"success"})})},resetForm:function(){this.$router.push("/commodityInfo/list")}}},m={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"main"},[a("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,"label-width":"100px"}},[a("div",{staticClass:"headline"},[a("h2",[a("span"),e._v("商品图片")])]),e._v(" "),a("el-form-item",{attrs:{label:"商品图片",prop:"name"}},[a("el-upload",{ref:"upload",staticClass:"upload-demo",attrs:{limit:1,drag:"",action:e.imgUpload,headers:e.uploadFileHeader,"on-success":e.uploadSuccMsgUrl,multiple:""}},[e.ruleForm.fileId?a("div",{staticClass:"pic"},[a("img",{attrs:{src:e.imgUrl+e.ruleForm.fileId}})]):e._e(),e._v(" "),a("i",{staticClass:"el-icon-upload"}),e._v(" "),a("div",{staticClass:"el-upload__text"},[e._v("将文件拖到此处，或\n          "),a("em",[e._v("点击上传")])]),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过500kb")])])],1),e._v(" "),a("div",{staticClass:"headline"},[a("h2",[a("span"),e._v("链接设置")])]),e._v(" "),a("el-form-item",{attrs:{label:"链接地址",prop:"linkAddress"}},[a("el-input",{model:{value:e.ruleForm.url,callback:function(t){e.$set(e.ruleForm,"url",t)},expression:"ruleForm.url"}})],1),e._v(" "),a("el-form-item",{staticClass:"btn"},[a("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("保存信息")]),e._v(" "),a("el-button",{staticClass:"cancel",on:{click:e.resetForm}},[e._v("取消编辑")])],1)],1)],1)},staticRenderFns:[]};var d=a("/Xao")(r,m,!1,function(e){a("5hhR")},"data-v-163a8046",null);t.default=d.exports},"5hhR":function(e,t,a){var n=a("c49R");"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);a("8bSs")("412449ac",n,!0)},c49R:function(e,t,a){(e.exports=a("BkJT")(!1)).push([e.i,"\n.main[data-v-163a8046] .el-form {\n  padding: 20px 20px;\n}\n.main[data-v-163a8046] .el-form .el-input__inner {\n    width: 100%;\n    background: #f4f4f4;\n}\n.main[data-v-163a8046] .el-form .el-textarea__inner {\n    width: 100%;\n    min-height: 150px !important;\n    background: #f4f4f4;\n}\n.main[data-v-163a8046] .el-form .headline h2 {\n    display: inline-block;\n    color: #333333;\n    font-size: 14px;\n    padding-bottom: 24px;\n}\n.main[data-v-163a8046] .el-form .headline h2 span {\n      width: 6px;\n      height: 20px;\n      display: inline-block;\n      background: #2ea0ff;\n      border-radius: 4px;\n      position: relative;\n      top: 5px;\n      margin-right: 10px;\n}\n.main[data-v-163a8046] .el-form .headline .selectContent {\n    margin-left: 25px;\n    display: inline-block;\n    margin-bottom: 25px;\n}\n.main[data-v-163a8046] .el-form .pic {\n    position: absolute;\n    top: 40px;\n    width: 100%;\n    height: 100%;\n    background: #ffffff;\n}\n.main[data-v-163a8046] .el-form .pic img {\n      width: 80px;\n      height: 80px;\n      overflow: hidden;\n}\n.main[data-v-163a8046] .el-form /deep/ .btn {\n    margin-top: 40px;\n    padding-bottom: 290px;\n}\n.main[data-v-163a8046] .el-form /deep/ .btn .el-form-item__content {\n      margin-left: 0 !important;\n      color: #666666;\n      font-size: 14px;\n}\n.main[data-v-163a8046] .el-form /deep/ .btn .el-form-item__content .cancel {\n        margin-left: 150px;\n}\n",""])}});