/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : hl-blog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 28/12/2022 17:09:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论的用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `blog_id` int(11) NULL DEFAULT NULL COMMENT '博客id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父评论id',
  `is_admin` tinyint(1) NULL DEFAULT NULL COMMENT '是否是管理员',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for blog_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '博客标题',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '博客摘要',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '博客内容',
  `type` int(11) NULL DEFAULT NULL COMMENT '所属专栏',
  `views` int(10) NULL DEFAULT 0 COMMENT '浏览量',
  `tags` int(11) NULL DEFAULT NULL COMMENT '标签',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论',
  `picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首图地址',
  `is_recommend` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启推荐',
  `is_reprint` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启转载声明',
  `is_appreciation` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启赞赏',
  `is_comment` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启评论',
  `property` int(11) NULL DEFAULT NULL COMMENT '1.原创; 2.转载; 3.翻译',
  `state` tinyint(1) NULL DEFAULT 1 COMMENT '是否发布',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_info
-- ----------------------------
INSERT INTO `blog_info` VALUES (28, '判断浏览器是否安装了flash', '浏览器是否安装了flash', '```js\n$(function() {\n  var fls = flashChecker();\n  if (fls.f) {\n    console.log(\'你安装了flash\');\n  } else {\n    console.log(\'你没有安装flash\');\n  }\n});\nfunction flashChecker(){\n  var hasFlash = 0;　　　　 //是否安装了flash\n        var flashVersion = 0;　　 //flash版本\n        if (document.all) {\n                var swf = new ActiveXObject(\'ShockwaveFlash.ShockwaveFlash\');\n                if (swf) {\n                        hasFlash = 1;\n                        VSwf = swf.GetVariable(\"$version\");\n                        flashVersion = parseInt(VSwf.split(\" \")[1].split(\",\")[0]);\n                }\n        } else {\n                if (navigator.plugins && navigator.plugins.length > 0) {\n                        var swf = navigator.plugins[\"Shockwave Flash\"];\n                        if (swf) {\n                                hasFlash = 1;\n                                var words = swf.description.split(\" \");\n                                for (var i = 0; i < words.length; ++i) {\n                                        if (isNaN(parseInt(words[i]))) {\n                                                continue;\n                                        }\n                                        flashVersion = parseInt(words[i]);\n                                }\n                        }\n                }\n        }\n        return {\n                f: hasFlash,\n                v: flashVersion\n        };\n}\n```', 20, 1, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=202585375,853911840&fm=253&fmt=auto&app=138&f=JPEG.webp', NULL, NULL, 1, NULL, 1, 1, NULL, '2022-12-28 15:12:26', NULL);
INSERT INTO `blog_info` VALUES (29, 'layer弹出层---子弹窗层关闭的同时向父弹出层传值', 'layer弹窗', ' - 父弹窗\n ```html\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <title>Title</title>\n</head>\n<body>\n    <div id=\"test\"></div>\n    <div id=\"openSonWin\"></div>\n</body>\n</html>\n ```\n```js\n$(\"#openSonWin\").on(\"click\",function(){\n    var index = layer.open({\n        type: 2,\n        title: \'选择浏览人员\',\n        shadeClose: true,\n        shade: false,\n        maxmin: true, //开启最大化最小化按钮\n        area: [\'665px\', \'600px\'],\n        content: \"./son.html\",\n        style: \'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;\',\n        end: function(){\n\n        }\n    })\n})\n```\n- 子窗体\n```html\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <title>Title</title>\n</head>\n<body>\n   <div id=\"btn\">按钮</div>\n</body>\n</html>\n```\n```js\n$(function(){\n    $(\"#btn\").on(\"click\",function(){\n        $(parent.document.body.children[0]).html(\"传递的值\");\n        var index = parent.layer.getFrameIndex(window.name);\n        parent.layer.close(index);\n    })\n})\n```', 21, 2, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/3.jpg', 1, 1, 1, 1, 1, 1, NULL, '2022-12-28 15:11:55', NULL);
INSERT INTO `blog_info` VALUES (30, '身份证输入验证', '身份证号验证', '```html\n身份证号：<input type=\"text\" id=\"idCard\"><span class=\"id-tips\"></span>\n```\n```js\n$(function() {\n    $(\".idCard\").on(\"input\", function (e) {\n        var reg = /(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/;//身份证格式验证\n        var reg_Ch = /[\\u4E00-\\u9FA5]|[\\uFE30-\\uFFA0]/g;//汉字验证\n        e.target.value = e.target.value.replace(reg_Ch, \"\");\n        var val = e.target.value;\n        if (!val.length) {\n            $(this).next().html(\"身份证号不能为空！\");\n        } else if (!reg.test(val)) {\n            $(this).next().html(\"身份证号填写有误！\");\n        } else {\n            $(this).next().html(\"\");\n        }\n    })\n})\n```', 22, 1, 11, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/1.jpg', 0, 1, NULL, NULL, 1, 1, '2022-12-28 15:05:42', '2022-12-28 15:12:31', 1);
INSERT INTO `blog_info` VALUES (31, '判断浏览器的类型', '判断浏览器类型', '```js\n$(function() {\n            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串\n            var isOpera = userAgent.indexOf(\"Opera\") > -1;\n            //判断是否Opera浏览器\n            if (isOpera) {\n                console.log(\"Opera\");\n                return \"Opera\"\n            };\n            //判断是否Firefox浏览器\n            if (userAgent.indexOf(\"Firefox\") > -1) {\n                console.log(\"Firefox\");\n                return \"FF\";\n            }\n            //判断是否chorme浏览器\n            if (userAgent.indexOf(\"Chrome\") > -1) {\n                console.log(\"Chrome\");\n                return \"Chrome\";\n            }\n            //判断是否Safari浏览器\n            if (userAgent.indexOf(\"Safari\") > -1) {\n                console.log(\"Safari\");\n                return \"Safari\";\n            }\n            //判断是否IE浏览器\n            if (userAgent.indexOf(\"compatible\") > -1 && userAgent.indexOf(\"MSIE\") > -1 && !isOpera) {\n                console.log(\"IE\");\n                return \"IE\";\n            }\n            //判断是否Edge浏览器\n            if (userAgent.indexOf(\"Trident\") > -1) {\n                console.log(\"Edge\");\n                return \"Edge\";\n            };\n        })\n```', 21, 1, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=520548276,2227732353&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, NULL, 1, NULL, 1, 1, '2022-12-28 15:15:50', '2022-12-28 15:23:52', 1);
INSERT INTO `blog_info` VALUES (32, 'document.documentMode', '文档模式', '```js\n// documentMode 属性返回浏览器渲染文档的模式\n// documentMode 是IE浏览器特定属性，在IE8及之后的IE版本都支持该属性\n\n// element-ui源码中判断浏览器的类型\nexport const isIE = function() {\n  return !Vue.prototype.$isServer && !isNaN(Number(document.documentMode));\n};\n\nexport const isEdge = function() {\n  return !Vue.prototype.$isServer && navigator.userAgent.indexOf(\'Edge\') > -1;\n};\n\nexport const isFirefox = function() {\n  return !Vue.prototype.$isServer && !!window.navigator.userAgent.match(/firefox/i);\n};\n```', 21, 0, 11, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=1210945873,1884155602&fm=253&fmt=auto&app=120&f=JPEG (1).webp', 1, NULL, 1, NULL, 1, 1, '2022-12-28 15:25:23', '2022-12-28 15:25:23', 1);
INSERT INTO `blog_info` VALUES (33, '拖拽窗体', '拖拽窗体', '```html\n<div class=\"window\">\n    <div class=\"title\"></div>\n    <div class=\"content\">内容区域</div>\n</div>\n```\n\n```css\n.window{\n    position: absolute;\n    width: 600px;\n    height: 400px;\n    top: 50%;\n    left: 50%;\n    margin-left: -300px;\n    margin-top: -200px;\n}\n.window .title{\n    height: 50px;\n    background-color: #ccc;\n}\n```\n```js\n$(function(){\n    doDrag($(\".window\"),$(\".title\"));\n});\n/**\n * 拖动窗体\n * @param parentNode 窗体的标签\n * @param self 指定鼠标进入时可拖动的区域\n */\nfunction doDrag(parentNode,self){\n    var disX = 0;\n    var disY = 0;\n    var marginLeft = parseInt(parentNode.css(\"margin-left\"));\n    var marginTop = parseInt(parentNode.css(\"margin-top\"));\n    self.mousedown(function(e){\n        e = e || event;\n        e.preventDefault();\n        disX = e.clientX - self.offset().left;\n        disY = e.clientY - self.offset().top;\n        $(document).mousemove(function(e){\n            e = e || event;\n            var left = e.clientX - disX - marginLeft;\n            var top = e.clientY - disY - marginTop;\n            var minLeft = 0;\n            var minTop = 0;\n            var maxLeft = $(window).width() - parseInt(parentNode.css(\"width\")) / 2 - 10;\n            var maxTop = $(window).height() - parseInt(parentNode.css(\"height\")) / 2 - 60;\n            if(left < minLeft - marginLeft){\n                left = minLeft - marginLeft + 10;\n            }else if(left > maxLeft){\n                left = maxLeft;\n            }\n            if(top < minTop - marginTop){\n                top = minTop - marginTop + 10;\n            }else if(top > maxTop){\n                top = maxTop;\n            }\n            parentNode.css({\n                left: left,\n                top: top\n            });\n        });\n        $(document).mouseup(function(){\n            $(document).unbind(\"mousemove\");\n            $(document).unbind(\"mouseup\");\n        })\n    })\n}\n```', 21, 1, 11, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=3490381478,1327095451&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, NULL, NULL, 1, 1, 1, '2022-12-28 15:30:38', '2022-12-28 15:31:22', 1);
INSERT INTO `blog_info` VALUES (34, '框架中不同标签页之前的标签操作', '框架中的标签操作', '```html\n<!-- a.html -->\n<button id=\"button\">按钮</button>\n<!-- b.html -->\n<button id=\"test\">test</button>\n```\n```js\n$(\"#button\").on(\"click\",function(){\n    //其中\"iframe11\"是iframe标签的name值\n    $(parent.frames[\'iframe11\'].document.getElementById(\"test\")).trigger(\"click\");\n})\n```', 21, 1, 11, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=2930719993,2577592180&fm=253&fmt=auto&app=138&f=PNG.webp', 1, NULL, NULL, 1, 1, 1, NULL, '2022-12-28 15:34:20', NULL);
INSERT INTO `blog_info` VALUES (35, '不同标签的操作，其中一个还包含frameset结构时', '标签操作', '```html\n<!-- a.html -->\n<button id=\"button\">按钮</button>\n<!-- b.html -->\n<frameset id=\"StudyBottom\" name=\"StudyBottom\" border=\"0\" frameSpacing=\"0\" frameBorder=\"0\" cols=\"160,*\"\n            runat=\"server\">\n    <frame id=\"studytree\" name=\"studytree\" src=\"JoinStudyTree.aspx\" scrolling=\"auto\" runat=\"server\" noresize>\n    </frame>\n    <frame id=\"studymain\" name=\"studymain\" src=\"c.html\" scrolling=\"auto\" runat=\"server\">\n    </frame>\n    <noframes>\n        <body>\n            <p>此网页使用了框架，但您的浏览器不支持框架。</p>\n        </body>\n    </noframes>\n</frameset>\n<!-- c.html -->\n<span id=\"start\"></span>\n```\n```js\n//点击a中的按钮时，获取b页面中c页面的值\n$(\"#button\").on(\"click\",function(){\n    //\"iframe13\"是b页面的iframe的name值\n    var studymain = parent.frames[\"iframe13\"].document.getElementById(\"studymain\");\n    var start = studymain.contentWindow.document.getElementById(\"StartTime\");\n    $(start).html(\"修改成功\");\n})\n```', 21, 0, 11, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=2052860268,2113493852&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, NULL, 1, NULL, 1, 1, '2022-12-28 15:46:03', '2022-12-28 15:46:03', 1);
INSERT INTO `blog_info` VALUES (36, '固定表头，使表格内容出现滚动条', '表格相关', '```html\n<table class=\"table\">\n    <thead>\n    <tr>\n        <th>Id</th>\n        <th>Name</th>\n        <th>Category</th>\n        <th>MonthAmount</th>\n        <th>hasBackUp</th>\n        <th>score</th>\n    </tr>\n    </thead>\n    <tbody>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n    <tr>\n        <td>23</td>\n        <td>South Bearing</td>\n        <td>Glass/Material</td>\n        <td>1234444</td>\n        <td>TRUE</td>\n        <td>3456.00</td>\n    </tr>\n\n    </tbody>\n</table>\n```\n```css\n.table thead tr {\n    display:block;\n}\n.table tbody {\n    display: block;\n    height: 200px;\n    overflow: auto;\n}\n.table th {\n    width:20%;\n}\n.table td {\n    width:20%;\n}\n```', 21, 0, 11, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/1.jpg', 1, NULL, 1, NULL, 1, 1, '2022-12-28 15:48:21', '2022-12-28 15:48:21', 1);
INSERT INTO `blog_info` VALUES (37, '为动态添加的元素绑定laydate时间控件', 'laydate时间控件', '```html\n<div id=\"app\">\n    <div>\n        <p v-for=\"(item,index) in list\">\n            <span>{{item}}</span>\n            <input type=\"text\" class=\"ipt\">\n            <button ref=\"remove\" @click=\"remove\" id=\"remove\">remove</button>\n        </p>\n    </div>\n    <button @click=\"add\" id=\"add\">add</button>\n</div>\n```\n```js\nvar vm = new Vue({\n    el: \"#app\",\n    data: {\n        list: [\"m\"],\n        msg: \"123\"\n    },\n    methods: {\n        add(){\n            //在添加元素的时候绑定渲染\n            this.list.push(\"h\");\n            layui.use(\"laydate\",function(){\n                var laydate = layui.laydate;\n                lay(\'.ipt\').each(function() {\n                    laydate.render({\n                        elem: this\n                    });\n                });\n            })\n        }\n    },\n    created(){\n        layui.use(\"laydate\",function(){\n            var laydate = layui.laydate;\n            lay(\'.ipt\').each(function() {\n                laydate.render({\n                    elem: this\n                });\n            });\n        })\n    }\n});\n\n```', 21, 0, 7, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/2.jpg', 1, NULL, 1, NULL, 1, 1, NULL, '2022-12-28 15:52:28', NULL);
INSERT INTO `blog_info` VALUES (38, '将数组中具有相同值的对象取出，组成新的数组', '数组操作', '```js\n\nvar arr = [{name:2,id:3},{name:2,id:4},{name:3,id:5},{name:3,id:6},{name:1,id:1},{name:1,id:2}];\nconsole.log(sortArr(arr,\"name\"));\n// 传入一个数组\n// 按照特定方式格式化\nfunction sortArr(arr, str) {\n    var _arr = [],\n        _t = [],\n        // 临时的变量\n        _tmp;\n \n    // 按照特定的参数将数组排序将具有相同值得排在一起\n    arr = arr.sort(function(a, b) {\n        var s = a[str],\n            t = b[str];\n \n        return s < t ? -1 : 1;\n    });\n \n    if ( arr.length ){\n        _tmp = arr[0][str];\n    }\n    // console.log( arr );\n    // 将相同类别的对象添加到统一个数组\n    for (var i in arr) {\n        if ( arr[i][str] === _tmp ){\n            console.log(_tmp)\n            _t.push( arr[i] );\n        } else {\n            _tmp = arr[i][str];\n            _arr.push( _t );\n            _t = [arr[i]];\n        }\n    }\n    // 将最后的内容推出新数组\n    _arr.push( _t );\n    return _arr;\n}\n```', 20, 0, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/3.jpg', 1, NULL, 1, NULL, 1, 1, NULL, '2022-12-28 16:00:39', NULL);
INSERT INTO `blog_info` VALUES (39, 'layui的下拉框与vue的双向数据绑定问题', 'layui相关', '```html\n<select v-model=\"list.type\">\n    <option value=\"\">请选择</option>\n    <option v-for=\"item in typeList\" :value=\"item.typeValue\">{{item.typeName}}</option>\n</select>\n```\n```js\n//关键在于弹窗显示后，延迟50ms执行form.render(\"select\")\nnew Vue({\n    el: \"\",\n    data(){\n        return {\n            typeList: [\n                { typeName: \"保单\", typeValue: 0},\n                { typeName: \"保费\", typeValue: 1}\n            ],\n        }\n    },\n    methods: {\n\n    },\n    watch: {\n        show: funciton(newVal){\n            if(newVal){\n                setTimeout(funciton(){\n                    layui.use(\"form\",function(){\n                        var form = layui.form;\n                        form.render(\"select\");\n                    })\n                })\n            }\n        }\n    }\n})\n```', 20, 1, 7, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/4.jpg', 1, NULL, 1, NULL, 1, 1, '2022-12-28 16:04:08', '2022-12-28 16:06:36', 1);
INSERT INTO `blog_info` VALUES (40, '前端上传图片预览', '图片预览', '```html\n<input type=\"file\" id=\"chooseImage\">\n<img src=\"\" id=\"cropedBigImg\" alt=\"\">\n```\n```js\n$(\'#chooseImage\').on(\'change\', function() {//当chooseImage的值改变时，执行此函数\n    var filePath = $(this).val(), //获取到input的value，里面是文件的路径\n        src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式\n    $(\'#cropedBigImg\').css(\'display\',\'block\');\n    $(\'#cropedBigImg\').attr(\'src\', src);\n});\n\n```', 20, 1, 13, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=267767918,2886226332&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, NULL, 1, NULL, 1, 1, '2022-12-28 16:08:18', '2022-12-28 16:09:33', 1);
INSERT INTO `blog_info` VALUES (41, 'layui时间控件一闪而过问题', 'layui相关', '```html\n <input type=\"text\" id=\"ipt\">\n```\n```js\nlayui.use(\"laydate\", function(){\n        var laydate = layui.laydate;\n        laydate.render({\n            elem: \"#ipt\",\n            trigger: \"click\",//该属性可解决时间控件弹出框闪退的问题\n        })\n    })\n```', 20, 3, 13, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=202585375,853911840&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, NULL, 1, NULL, 1, 1, '2022-12-28 16:11:07', '2022-12-28 16:14:18', 1);
INSERT INTO `blog_info` VALUES (42, '在vue项目中使用layui', 'layui相关', '1. 将下载好的layui文件夹放在static文件下\n2. 在HTML中引入对应文件\n3. 通过`layui.use()`就可以使用相应的模块\n```html\n<!DOCTYPE html>\n<html>\n  <head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0\">\n    <title>new_website</title>\n    <link rel=\"stylesheet\" href=\"./static/layui/css/layui.css\">\n    <script src=\"./static/layui/layui.js\"></script>\n  </head>\n  <body>\n    <div id=\"app\"></div>\n    <!-- built files will be auto injected -->\n  </body>\n</html>\n```', 20, 0, 7, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=520548276,2227732353&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, NULL, 1, NULL, 1, 1, '2022-12-28 16:16:32', '2022-12-28 16:16:32', 1);
INSERT INTO `blog_info` VALUES (43, '将平面json格式转为json格式', '数据转换', '```js\n/**\n* 把平铺的数组结构转成树形结构\n*/\nconst arr = [\n    { \'id\': \'29\', \'pid\': \'\',     \'name\': \'总裁办\' },\n    { \'id\': \'2c\', \'pid\': \'\',     \'name\': \'财务部\' },\n    { \'id\': \'2d\', \'pid\': \'2c\', \'name\': \'财务核算部\'},\n    { \'id\': \'2f\', \'pid\': \'2c\', \'name\': \'薪资管理部\'},\n    { \'id\': \'d2\', \'pid\': \'\',     \'name\': \'技术部\'},\n    { \'id\': \'d3\', \'pid\': \'d2\', \'name\': \'Java研发部\'},\n    { \'id\': \'d4\', \'pid\': \'d3\', \'name\': \'Java研发部-1组\'}\n]\n\n// 在list找pid为第二次参数的元素，组成一个数组\nfunction findChildren(list, pid) {\n    // 在list中根据pid来找元素\n    let treeList = []\n    treeList = list.filter(it => it.pid === pid)\n    /**\n		* [\n		*   { \'id\': \'29\', \'pid\': \'\',     \'name\': \'总裁办\',children:[] },\n		*   { \'id\': \'2c\', \'pid\': \'\',     \'name\': \'财务部\' },\n		*   { \'id\': \'d2\', \'pid\': \'\',     \'name\': \'技术部\'}\n		* ]\n		* */\n\n    treeList.forEach(item => {\n        item.children = findChildren(list, item.id)\n    })\n    return treeList\n}\n\nconst treeList = findChildren(arr,\'\')\nconsole.log(\'转换之后的树\',treeList);\n```', 20, 1, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=529657752,2140000372&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 1, NULL, NULL, 1, 1, '2022-12-28 16:21:40', '2022-12-28 16:21:47', 1);
INSERT INTO `blog_info` VALUES (44, '限制输入框只能输入数字', '输入框只能输入数字', '```js\n$(\"#ipt\").on(\"input\", function(){\n    this.value = this.value.replace(/[^\\d\\.]/g, \'\');\n})\n```', 20, 0, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=1085418351,3301086122&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 1, NULL, NULL, 1, 1, '2022-12-28 16:23:41', '2022-12-28 16:23:41', 1);
INSERT INTO `blog_info` VALUES (45, 'react图片问题', '图片', 'img的图片要放在public里面才能正常显示，而background-img的URL要放在src的子目录下', 20, 0, 8, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=2052860268,2113493852&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 1, NULL, NULL, 1, 1, '2022-12-28 16:26:12', '2022-12-28 16:26:12', 1);
INSERT INTO `blog_info` VALUES (46, '不通过AppleStore下载ios安装包(ipa)', '安装ios', '```html\n<a href=\"itms-services://?action=download-manifest&url=https://raw.githubusercontent.com/Zard1991/JustCode/master/iChat.plist\">下载IOS版</a>\n```\n- `*.plist`的内容\n```html\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n<plist version=\"1.0\">\n    <dict>\n        <key>items</key>\n        <array>\n            <dict>\n                <key>assets</key>\n                <array>\n                    <dict>\n                        <key>kind</key>\n                        <string>software-package</string>\n                        <key>url</key>\n                        <string>http://10.128.8.15:8443/GFZQ/imos/move/ios/FinChat.ipa</string>\n                    </dict>\n                </array>\n                <key>metadata</key>\n                <dict>\n                    <key>bundle-identifier</key>\n                    <string>cn.com.gf.ichat</string>\n                    <key>bundle-version</key>\n                    <string>1240</string>\n                    <key>kind</key>\n                    <string>software</string>\n                    <key>title</key>\n                    <string>iChat</string>\n                </dict>\n            </dict>\n        </array>\n    </dict>\n</plist>\n\n```\n**注意：** a标签里面的URL需要是https的连接，地址为`*.plist`的文件地址，文件中的URL是安装包的全地址，可以不是https的地址(将`*.plist`文件上传到GitHub上，就是一个https的地址)', 20, 1, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=1987026386,2214862957&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 1, NULL, NULL, 1, 1, NULL, '2022-12-28 16:33:06', NULL);
INSERT INTO `blog_info` VALUES (47, '滚动条样式', '样式', '```css\n::-webkit-scrollbar-thumb {\n	height: 32px;\n	background: #d1d1d1;\n	outline-offset: 2px;\n	outline: 2px solid #fff;\n	border-radius: 6px;\n	border: 0px solid #fff;\n}\n\n::-webkit-scrollbar-thumb:HOVER {\n	background: #828282;\n}\n\n::-webkit-scrollbar-thumb:ACTIVE {\n	background: #747474;\n}\n\n::-webkit-scrollbar {\n	width: 6px;\n	height: 10px;\n}\n::-webkit-scrollbar-track-piece {\n	background-color: transparent;\n	border-radius: 0;\n}\n\n```', 20, 2, 12, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=1210945873,1884155602&fm=253&fmt=auto&app=120&f=JPEG (1).webp', 1, 1, NULL, NULL, 1, 1, '2022-12-28 16:35:17', '2022-12-28 16:39:21', 1);
INSERT INTO `blog_info` VALUES (48, '删除数组中指定的元素', '数组操作', '```js\narr.splice(arr.findIndex(item => item.id === data.id), 1);\n```', 20, 0, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=2052860268,2113493852&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 1, NULL, NULL, 1, 1, '2022-12-28 16:36:55', '2022-12-28 16:36:55', 1);
INSERT INTO `blog_info` VALUES (49, 'label和checkbox点击事件触发两次问题', 'label和checkbox', '**原因：** label和input标签嵌套，点击label的时候，事件冒泡一次，同时会触发关联的input的click事件，导致事件再次冒泡\n**解决方案：**\n\n - 对`e.target`进行判断\n ```html\n    <div class=\"plan-list\">\n        <label>\n            <input type=\"checkbox\" name=\"\">\n        </label>\n    </div>\n```\n```js\ntodayAchieve.on(\"click\", \".plan-list label\", function(e){\n    if($(e.target).is(\"input\")){\n        return;\n    }\n    if($(this).find(\"input\").attr(\"checked\") === \"checked\"){\n        $(this).parent().removeClass(\"completed\");\n    }else{\n        $(this).parent().addClass(\"completed\");\n\n    }\n});\n```', 20, 0, 10, NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20221228/u=2052860268,2113493852&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 1, NULL, NULL, 1, 1, '2022-12-28 17:00:56', '2022-12-28 17:00:56', 1);

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `number` int(11) NULL DEFAULT 0 COMMENT '博客数量',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (9, 'angular', 0, '2022-12-28 13:14:57', '2022-12-28 13:14:57', 1);
INSERT INTO `blog_tag` VALUES (8, 'react', 1, '2022-12-28 13:14:50', '2022-12-28 16:26:12', 1);
INSERT INTO `blog_tag` VALUES (7, 'vue', 3, '2022-12-28 13:12:54', '2022-12-28 16:16:32', 1);
INSERT INTO `blog_tag` VALUES (10, 'js', 9, '2022-12-28 13:19:02', '2022-12-28 17:00:56', 1);
INSERT INTO `blog_tag` VALUES (11, '其他', 6, '2022-12-28 14:59:19', '2022-12-28 15:48:21', 1);
INSERT INTO `blog_tag` VALUES (12, 'css', 1, '2022-12-28 15:49:02', '2022-12-28 16:35:17', 1);
INSERT INTO `blog_tag` VALUES (13, 'html', 2, '2022-12-28 15:49:06', '2022-12-28 16:11:07', 1);

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专栏名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专栏简介',
  `number` int(11) NULL DEFAULT 0 COMMENT '博客数量',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_type
-- ----------------------------
INSERT INTO `blog_type` VALUES (22, '其他', '其他的类型', 1, '2022-12-28 14:59:11', '2022-12-28 15:05:42', 1);
INSERT INTO `blog_type` VALUES (21, '浏览器', '浏览器相关', 8, '2022-12-28 13:14:32', '2022-12-28 15:52:28', 1);
INSERT INTO `blog_type` VALUES (20, '开发笔记', '开发过程中的笔记', 13, '2022-12-28 13:12:40', '2022-12-28 17:00:56', 1);

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '\'' COMMENT '头像图片',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES (3, 'admin', '123456', '192.168.1.15', '2022-12-28 13:09:44', 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20220620/lihezong.webp', '2022-06-20 16:11:43', '2022-06-23 10:01:03', 1);
INSERT INTO `ums_admin` VALUES (4, 'test', '123456', '', NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20220623/hw-logo.png', '2022-06-23 10:15:19', '2022-06-23 10:15:19', 1);

-- ----------------------------
-- Table structure for ums_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role`;
CREATE TABLE `ums_admin_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `admin_id` int(20) NULL DEFAULT NULL COMMENT '管理员id',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_admin_role
-- ----------------------------
INSERT INTO `ums_admin_role` VALUES (3, 3, 10);
INSERT INTO `ums_admin_role` VALUES (4, 4, 11);

-- ----------------------------
-- Table structure for ums_dict
-- ----------------------------
DROP TABLE IF EXISTS `ums_dict`;
CREATE TABLE `ums_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `data_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典键',
  `data_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_dict
-- ----------------------------
INSERT INTO `ums_dict` VALUES (1, 'express', 'ZTO', '中通快递', '快递公司', '2022-09-05 23:55:18', '2022-09-06 00:01:42', 1);
INSERT INTO `ums_dict` VALUES (2, 'express', 'YTO', '圆通速递', '快递公司', '2022-09-06 00:03:02', '2022-09-06 00:03:02', 1);
INSERT INTO `ums_dict` VALUES (3, 'express', 'YD', '韵达速递', '快递公司', '2022-09-06 00:03:20', '2022-09-06 00:03:20', 1);
INSERT INTO `ums_dict` VALUES (4, 'express', 'SF', '顺丰速运', '快递公司', '2022-09-06 00:04:01', '2022-09-06 18:12:36', 1);
INSERT INTO `ums_dict` VALUES (5, 'express', 'DBL', '德邦快递', '快递公司', '2022-09-06 00:04:17', '2022-09-06 00:04:17', 1);

-- ----------------------------
-- Table structure for ums_form
-- ----------------------------
DROP TABLE IF EXISTS `ums_form`;
CREATE TABLE `ums_form`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单名称',
  `form_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单key',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单备注',
  `config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单配置',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表单配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_form
-- ----------------------------
INSERT INTO `ums_form` VALUES (2, '用户表单', 'userFormKey', '用户的操作表单', '[{\"type\":\"input\",\"field\":\"username\",\"title\":\"用户名\",\"info\":\"\",\"props\":{\"placeholder\":\"请输入用户名称\",\"clearable\":true},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true},{\"type\":\"input\",\"field\":\"password\",\"title\":\"密码\",\"info\":\"\",\"props\":{\"placeholder\":\"请输入密码\",\"type\":\"password\"},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":false}]', '2022-11-16 17:51:10', '2022-11-16 17:51:10', 1);
INSERT INTO `ums_form` VALUES (6, '博客类型', 'BlogTypeKey', '博客类型的表单', '[{\"type\":\"input\",\"field\":\"name\",\"title\":\"专栏名称\",\"info\":\"\",\"props\":{\"clearable\":true,\"placeholder\":\"请输入专栏名称\"},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":\"请输入专栏名称\"},{\"type\":\"input\",\"field\":\"desc\",\"title\":\"专栏简介\",\"info\":\"\",\"props\":{\"type\":\"textarea\",\"showWordLimit\":true,\"placeholder\":\"请输入专栏名称\",\"clearable\":true,\"rows\":3},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":false}]', '2022-12-12 23:35:42', '2022-12-12 23:35:42', 1);
INSERT INTO `ums_form` VALUES (7, '博客标签', 'BlogTagKey', '博客标签的表单', '[{\"type\":\"input\",\"field\":\"name\",\"title\":\"标签名称\",\"info\":\"\",\"props\":{\"clearable\":true},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":\"请输入标签名称\"}]', '2022-12-14 13:17:20', '2022-12-14 13:17:20', 1);

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单地址',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组件名称',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组件地址',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `is_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '超链接地址',
  `is_hide` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏',
  `is_keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存组件',
  `is_affix` tinyint(1) NULL DEFAULT NULL COMMENT '是否固定',
  `is_iframe` tinyint(1) NULL DEFAULT NULL COMMENT '是否内嵌窗口',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (1, NULL, '/system', 'system', 'layout', '系统管理', '', 0, 1, 0, 0, 'iconfont icon-bolangneng', 1);
INSERT INTO `ums_menu` VALUES (2, 1, '/system/meun', 'systemMenu', '/system/menu', '菜单管理', '', 0, 1, 0, 0, 'iconfont icon--chaifenhang', 1);
INSERT INTO `ums_menu` VALUES (4, 1, '/system/user', 'systemUser', '/system/user', '用户管理', '', 0, 1, 0, 0, 'ele-Avatar', 2);
INSERT INTO `ums_menu` VALUES (5, 1, '/system/role', 'systemRole', '/system/role', '角色管理', '', 0, 1, 0, 0, 'ele-Briefcase', 3);
INSERT INTO `ums_menu` VALUES (24, 1, '/system/config', 'systemConfig', '/system/config', '配置管理', '', 1, 1, 0, 0, 'iconfont icon-wenducanshu-05', 4);
INSERT INTO `ums_menu` VALUES (29, 1, '/system/dict', 'systemDict', '/system/dict', '字典管理', '', 1, 1, 0, 0, 'iconfont icon-728bianjiqi_zitidaxiao', 5);
INSERT INTO `ums_menu` VALUES (33, 1, '/system/form-config', 'form-config', '/system/form-config', '表单配置', NULL, 0, 1, 0, NULL, 'iconfont icon-xitongshezhi', 6);
INSERT INTO `ums_menu` VALUES (34, 1, '/system/form-designer', 'form-designer', '/system/form-designer', '表单设计', NULL, 0, 1, 0, NULL, 'iconfont icon-tongzhi1', 7);
INSERT INTO `ums_menu` VALUES (35, NULL, '/blog', 'blog', 'layout', '博客管理', '', 0, 1, 0, 0, 'iconfont icon-diannao1', 2);
INSERT INTO `ums_menu` VALUES (36, 35, '/blog/type', 'blogType', '/blog/type', '博客类型', '', 0, 1, 0, 0, 'iconfont icon-wenducanshu-05', 1);
INSERT INTO `ums_menu` VALUES (37, 35, '/blog/tag', 'blogTag', '/blog/tag', '博客标签', '', 0, 1, 0, 0, 'iconfont icon-wenducanshu-05', 2);
INSERT INTO `ums_menu` VALUES (38, 35, '/blog/blogInfo', 'blogInfo', '/blog/blogInfo', '博客详情', '', 0, 1, 0, 0, 'iconfont icon-bolangneng', 3);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色key',
  `desc` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (10, '超级管理员', 'admin', '拥有所有权限', 1, '2022-06-20 11:13:09', '2022-11-14 16:15:11', NULL);
INSERT INTO `ums_role` VALUES (11, '普通管理员', 'common', '拥有部分权限', 1, '2022-06-20 11:27:15', '2022-06-21 11:50:05', 1);

-- ----------------------------
-- Table structure for ums_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu`;
CREATE TABLE `ums_role_menu`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(20) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 399 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role_menu
-- ----------------------------
INSERT INTO `ums_role_menu` VALUES (387, 10, 1);
INSERT INTO `ums_role_menu` VALUES (388, 10, 2);
INSERT INTO `ums_role_menu` VALUES (389, 10, 4);
INSERT INTO `ums_role_menu` VALUES (390, 10, 5);
INSERT INTO `ums_role_menu` VALUES (391, 10, 24);
INSERT INTO `ums_role_menu` VALUES (392, 10, 29);
INSERT INTO `ums_role_menu` VALUES (393, 10, 33);
INSERT INTO `ums_role_menu` VALUES (394, 10, 34);
INSERT INTO `ums_role_menu` VALUES (395, 10, 35);
INSERT INTO `ums_role_menu` VALUES (396, 10, 36);
INSERT INTO `ums_role_menu` VALUES (397, 10, 37);
INSERT INTO `ums_role_menu` VALUES (398, 10, 38);

-- ----------------------------
-- Table structure for ums_system
-- ----------------------------
DROP TABLE IF EXISTS `ums_system`;
CREATE TABLE `ums_system`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统配置名',
  `key_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统配置值',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_system
-- ----------------------------
INSERT INTO `ums_system` VALUES (21, 'yyx_express_freight_value', '15', '2022-08-04 17:58:58', '2022-08-05 13:27:19', 1);
INSERT INTO `ums_system` VALUES (22, 'yyx_order_unpaid', '30', '2022-08-04 17:58:58', '2022-08-19 17:35:35', 1);
INSERT INTO `ums_system` VALUES (23, 'yyx_order_comment', '7', '2022-08-04 17:58:58', '2022-08-19 17:35:35', 1);
INSERT INTO `ums_system` VALUES (24, 'yyx_express_freight_min', '88', '2022-08-04 17:58:58', '2022-08-05 13:27:19', 1);
INSERT INTO `ums_system` VALUES (25, 'yyx_order_un_confirm', '7', '2022-08-04 17:58:58', '2022-08-19 17:35:35', 1);

SET FOREIGN_KEY_CHECKS = 1;
