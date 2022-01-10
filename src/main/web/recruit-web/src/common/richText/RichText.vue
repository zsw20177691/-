<template>
  <div>
    <froala :tag="'textarea'" :config="froalaConfig" v-model="froalaContent"></froala>
  </div>
</template>

<script>
  import jQuery from 'jquery'
  import VueFroala from 'vue-froala-wysiwyg'
  // import 'froala-editor/js/plugins.pkgd.min.js';


  export default {
    name: 'RichText',
    props: ["height", "size"],
    data() {
      return {

        froalaConfig: {
          toolbarBottom: this.size == 'mini' ,
          toolbarButtons: this.size == 'mini' ? [] : ['undo', 'redo', 'clearFormatting', '|',
            'bold', 'italic', 'underline', 'strikeThrough', '|',
            'fontFamily', 'fontSize', 'color', '|', 'paragraphFormat', 'align',  'outdent',
            'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertVideo', 'embedly', 'insertFile',
            'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', '|', 'print',
            'spellChecker', 'help', '|', 'fullscreen'
          ],
          // 　　　　　 ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color',
          //  'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink',
          //  'insertImage', 'insertVideo', 'embedly', 'insertFile', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll',
          //  'clearFormatting', '|', 'print', 'spellChecker', 'help', 'html', '|', 'undo', 'redo'],//显示可操作项
          // theme: "dark",//主题
          placeholder: "请填写内容",
          language: "zh_cn", //国际化
          imageUploadURL: this.$common.baseUrl + 'upload/img', //上传url
          imageAllowedTypes: ['jpeg', 'jpg', 'png', 'gif', 'bmp', ],
          imageManagerToggleTags: false,
          fileUploadURL: this.$common.baseUrl + 'upload/file', //上传url 更多上传介绍 请访问https://www.froala.com/wysiwyg-editor/docs/options
          videoUploadURL: '/media',
          videoAllowedTypes: ['avi', 'mov', 'mp4', 'm4v', 'mpeg', 'mpg', 'wmv', 'ogv'],
          quickInsertEnabled: false,
          // quickInsertButtons: ['image', 'table', 'ul', 'ol', 'hr'], //快速插入项
          // toolbarVisibleWithoutSelection: true,//是否开启 不选中模式
          disableRightClick: true,//是否屏蔽右击
          colorsHEXInput: false, //关闭16进制色值
          toolbarSticky: true, //操作栏是否自动吸顶
          pastePlain: this.size == 'mini' ? true : false,
          zIndex: 1000,
          height: this.height,
          events: {
            'froalaEditor.initialized': function() {
              console.log('initialized')
            }
          },
        },
        froalaContent: "", //默认测试文本
      }
    }
  }
</script>

<style>
</style>
