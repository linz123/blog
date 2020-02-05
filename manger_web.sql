/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : manger_web

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-01-21 17:55:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `article_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '博文ID',
  `user_id` bigint(255) NOT NULL COMMENT '发表用户ID',
  `article_title` tinytext NOT NULL COMMENT '博文标题',
  `article_content` longtext NOT NULL COMMENT '博文内容',
  `article_views` bigint(20) DEFAULT '0' COMMENT '浏览量',
  `article_comment_count` bigint(20) DEFAULT '0' COMMENT '评论总数',
  `article_date` datetime DEFAULT NULL COMMENT '发表时间',
  `article_like_count` bigint(20) DEFAULT '0',
  PRIMARY KEY (`article_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `manger_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO `articles` VALUES ('1', '1', 'zigbee CC2530 系列教程 16 流水灯实验', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '151', '20', '2020-01-02 19:46:29', '44');
INSERT INTO `articles` VALUES ('2', '1', '漫话：什么是平衡(AVL)树？这应该是把AVL树讲的最好的文章了', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '32', '20', '2020-01-02 19:46:29', '24');
INSERT INTO `articles` VALUES ('3', '1', 'Python 中那些令人防不胜防的坑（一）', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '10', '20', '2020-01-02 19:46:29', '22');
INSERT INTO `articles` VALUES ('4', '1', '经典算法（5）杨辉三角', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '26', '20', '2020-01-02 19:46:29', '22');
INSERT INTO `articles` VALUES ('5', '1', '比特币原理详解', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '12', '20', '2020-01-02 19:46:29', '22');
INSERT INTO `articles` VALUES ('6', '1', '【图解算法面试】记一次面试：说说游戏中的敏感词过滤是如何实现的？', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '91', '20', '2020-01-02 19:46:29', '25');
INSERT INTO `articles` VALUES ('7', '1', '感悟生活，再来聊聊拼多多', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '10', '20', '2020-01-02 19:46:29', '22');
INSERT INTO `articles` VALUES ('8', '1', 'Centos7 Redis5.0.5 三主三从集群安装和集群遇到的错误解决', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '10', '20', '2020-01-02 19:46:29', '21');
INSERT INTO `articles` VALUES ('9', '1', 'MySQL数据表中有自增长主键时如何插入数据', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '10', '20', '2020-01-02 19:46:29', '21');
INSERT INTO `articles` VALUES ('10', '1', 'MySQL数据表中有自增长主键时如何插入数据', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '10', '20', '2020-01-02 19:46:29', '21');
INSERT INTO `articles` VALUES ('11', '1', 'MySQL数据表中有自增长主键时如何插入数据', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '12', '20', '2020-01-02 19:46:29', '21');
INSERT INTO `articles` VALUES ('12', '1', 'MySQL数据表中有自增长主键时如何插入数据', '当数据表中有自增长主键时，当用SQL插入语句中插入语句带有ID列值记录的时候；\n如果指定了该列的值，则新插入的值不能和已有的值重复，而且必须大于其中最大的一个值；\n也可以不指定该列的值，只将其他列的值插入，让ID还是按照MySQL自增自己填；\n这种情况在进行插入的时候，两种解决方法：\n①可以把id的值设置为null或者0，这样子mysql都会自己做处理\n②手动指定需要插入的列，不插入这一个字段的数据！\r\n————————————————\r\n版权声明：本文为CSDN博主「Weicleer」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/Weicleer/article/details/47608289', '10', '20', '2020-01-02 19:46:29', '21');
INSERT INTO `articles` VALUES ('14', '1', '这是一个测试的文章标题', '# 欢迎使用 Markdown在线编辑器 MdEditor\r\n\r\n**Markdown是一种轻量级的「标记语言」**\r\n\r\n\r\n![markdown](https://www.mdeditor.com/images/logos/markdown.png \"markdown\")\r\n\r\n\r\nMarkdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。它允许人们使用易读易写的纯文本格式编写文档，然后转换成格式丰富的HTML页面，Markdown文件的后缀名便是“.md”\r\n\r\n\r\n## MdEditor是一个在线编辑Markdown文档的编辑器\r\n\r\n*MdEditor扩展了Markdown的功能（如表格、脚注、内嵌HTML等等），以使让Markdown转换成更多的格式，和更丰富的展示效果，这些功能原初的Markdown尚不具备。*\r\n\r\n> Markdown增强版中比较有名的有Markdown Extra、MultiMarkdown、 Maruku等。这些衍生版本要么基于工具，如~~Pandoc~~，Pandao；要么基于网站，如GitHub和Wikipedia，在语法上基本兼容，但在一些语法和渲染效果上有改动。\r\n\r\nMdEditor源于Pandao的JavaScript开源项目，开源地址[Editor.md](https://github.com/pandao/editor.md \"Editor.md\")，并在MIT开源协议的许可范围内进行了优化，以适应广大用户群体的需求。向优秀的markdown开源编辑器原作者Pandao致敬。\r\n\r\n\r\n![Pandao editor.md](https://pandao.github.io/editor.md/images/logos/editormd-logo-180x180.png \"Pandao editor.md\")\r\n\r\n\r\n\r\n## MdEditor的功能列表演示\r\n\r\n# 标题H1\r\n\r\n## 标题H2\r\n\r\n### 标题H3\r\n\r\n#### 标题H4\r\n\r\n##### 标题H5\r\n\r\n###### 标题H5\r\n\r\n### 字符效果和横线等\r\n----\r\n\r\n~~删除线~~ <s>删除线（开启识别HTML标签时）</s>\r\n\r\n*斜体字*      _斜体字_\r\n\r\n**粗体**  __粗体__\r\n\r\n***粗斜体*** ___粗斜体___\r\n\r\n上标：X<sub>2</sub>，下标：O<sup>2</sup>\r\n\r\n**缩写(同HTML的abbr标签)**\r\n> 即更长的单词或短语的缩写形式，前提是开启识别HTML标签时，已默认开启\r\n\r\nThe <abbr title=\"Hyper Text Markup Language\">HTML</abbr> specification is maintained by the <abbr title=\"World Wide Web Consortium\">W3C</abbr>.\r\n### 引用 Blockquotes\r\n\r\n> 引用文本 Blockquotes\r\n\r\n引用的行内混合 Blockquotes\r\n\r\n> 引用：如果想要插入空白换行`即<br />标签`，在插入处先键入两个以上的空格然后回车即可，[普通链接](https://www.mdeditor.com/)。\r\n\r\n### 锚点与链接 Links\r\n[普通链接](https://www.mdeditor.com/)\r\n[普通链接带标题](https://www.mdeditor.com/ \"普通链接带标题\")\r\n直接链接：<https://www.mdeditor.com>\r\n[锚点链接][anchor-id]\r\n[anchor-id]: https://www.mdeditor.com/\r\n[mailto:test.test@gmail.com](mailto:test.test@gmail.com)\r\nGFM a-tail link @pandao\r\n邮箱地址自动链接 test.test@gmail.com  www@vip.qq.com\r\n> @pandao\r\n\r\n### 多语言代码高亮 Codes\r\n\r\n#### 行内代码 Inline code\r\n\r\n\r\n执行命令：`npm install marked`\r\n\r\n#### 缩进风格\r\n\r\n即缩进四个空格，也做为实现类似 `<pre>` 预格式化文本 ( Preformatted Text ) 的功能。\r\n\r\n    <?php\r\n        echo \"Hello world!\";\r\n    ?>\r\n预格式化文本：\r\n\r\n    | First Header  | Second Header |\r\n    | ------------- | ------------- |\r\n    | Content Cell  | Content Cell  |\r\n    | Content Cell  | Content Cell  |\r\n\r\n#### JS代码\r\n```javascript\r\nfunction test() {\r\n	console.log(\"Hello world!\");\r\n}\r\n```\r\n\r\n#### HTML 代码 HTML codes\r\n```html\r\n<!DOCTYPE html>\r\n<html>\r\n    <head>\r\n        <mate charest=\"utf-8\" />\r\n        <meta name=\"keywords\" content=\"Editor.md, Markdown, Editor\" />\r\n        <title>Hello world!</title>\r\n        <style type=\"text/css\">\r\n            body{font-size:14px;color:#444;font-family: \"Microsoft Yahei\", Tahoma, \"Hiragino Sans GB\", Arial;background:#fff;}\r\n            ul{list-style: none;}\r\n            img{border:none;vertical-align: middle;}\r\n        </style>\r\n    </head>\r\n    <body>\r\n        <h1 class=\"text-xxl\">Hello world!</h1>\r\n        <p class=\"text-green\">Plain text</p>\r\n    </body>\r\n</html>\r\n```\r\n### 图片 Images\r\n\r\n图片加链接 (Image   Link)：\r\n\r\n\r\n[![](https://www.mdeditor.com/images/logos/markdown.png)](https://www.mdeditor.com/images/logos/markdown.png \"markdown\")\r\n\r\n> Follow your heart.\r\n\r\n----\r\n### 列表 Lists\r\n\r\n#### 无序列表（减号）Unordered Lists (-)\r\n\r\n- 列表一\r\n- 列表二\r\n- 列表三\r\n\r\n#### 无序列表（星号）Unordered Lists (*)\r\n\r\n* 列表一\r\n* 列表二\r\n* 列表三\r\n\r\n#### 无序列表（加号和嵌套）Unordered Lists ( )\r\n  列表一\r\n  列表二\r\n      列表二-1\r\n      列表二-2\r\n      列表二-3\r\n  列表三\r\n    * 列表一\r\n    * 列表二\r\n    * 列表三\r\n\r\n#### 有序列表 Ordered Lists (-)\r\n\r\n1. 第一行\r\n2. 第二行\r\n3. 第三行\r\n\r\n#### GFM task list\r\n\r\n- [x] GFM task list 1\r\n- [x] GFM task list 2\r\n- [ ] GFM task list 3\r\n    - [ ] GFM task list 3-1\r\n    - [ ] GFM task list 3-2\r\n    - [ ] GFM task list 3-3\r\n- [ ] GFM task list 4\r\n    - [ ] GFM task list 4-1\r\n    - [ ] GFM task list 4-2\r\n\r\n----\r\n\r\n### 绘制表格 Tables\r\n\r\n| 项目        | 价格   |  数量  |\r\n| --------   | -----:  | :----:  |\r\n| 计算机      | $1600   |   5     |\r\n| 手机        |   $12   |   12   |\r\n| 管线        |    $1    |  234  |\r\n\r\nFirst Header  | Second Header\r\n------------- | -------------\r\nContent Cell  | Content Cell\r\nContent Cell  | Content Cell\r\n\r\n| First Header  | Second Header |\r\n| ------------- | ------------- |\r\n| Content Cell  | Content Cell  |\r\n| Content Cell  | Content Cell  |\r\n\r\n| Function name | Description                    |\r\n| ------------- | ------------------------------ |\r\n| `help()`      | Display the help window.       |\r\n| `destroy()`   | **Destroy your computer!**     |\r\n\r\n| Left-Aligned  | Center Aligned  | Right Aligned |\r\n| :------------ |:---------------:| -----:|\r\n| col 3 is      | some wordy text | $1600 |\r\n| col 2 is      | centered        |   $12 |\r\n| zebra stripes | are neat        |    $1 |\r\n\r\n| Item      | Value |\r\n| --------- | -----:|\r\n| Computer  | $1600 |\r\n| Phone     |   $12 |\r\n| Pipe      |    $1 |\r\n\r\n----\r\n\r\n#### 特殊符号 HTML Entities Codes\r\n\r\n&copy; &  &uml; &trade; &iexcl; &pound;\r\n&amp; &lt; &gt; &yen; &euro; &reg; &plusmn; &para; &sect; &brvbar; &macr; &laquo; &middot;\r\n\r\nX&sup2; Y&sup3; &frac34; &frac14;  &times;  &divide;   &raquo;\r\n\r\n18&ordm;C  &quot;  &apos;\r\n\r\n[========]\r\n\r\n### Emoji表情 :smiley:\r\n\r\n> Blockquotes :star:\r\n\r\n#### GFM task lists & Emoji & fontAwesome icon emoji & editormd logo emoji :editormd-logo-5x:\r\n\r\n- [x] :smiley: @mentions, :smiley: #refs, [links](), **formatting**, and <del>tags</del> supported :editormd-logo:;\r\n- [x] list syntax required (any unordered or ordered list supported) :editormd-logo-3x:;\r\n- [x] [ ] :smiley: this is a complete item :smiley:;\r\n- [ ] []this is an incomplete item [test link](#) :fa-star: @pandao;\r\n- [ ] [ ]this is an incomplete item :fa-star: :fa-gear:;\r\n    - [ ] :smiley: this is an incomplete item [test link](#) :fa-star: :fa-gear:;\r\n    - [ ] :smiley: this is  :fa-star: :fa-gear: an incomplete item [test link](#);\r\n\r\n#### 反斜杠 Escape\r\n\r\n\\*literal asterisks\\*\r\n\r\n[========]\r\n### 科学公式 TeX(KaTeX)\r\n\r\n$$E=mc^2$$\r\n\r\n行内的公式$$E=mc^2$$行内的公式，行内的$$E=mc^2$$公式。\r\n\r\n$$x > y$$\r\n\r\n$$\\(\\sqrt{3x-1} (1 x)^2\\)$$\r\n\r\n$$\\sin(\\alpha)^{\\theta}=\\sum_{i=0}^{n}(x^i   \\cos(f))$$\r\n\r\n多行公式：\r\n\r\n```math\r\n\\displaystyle\r\n\\left( \\sum\\_{k=1}^n a\\_k b\\_k \\right)^2\r\n\\leq\r\n\\left( \\sum\\_{k=1}^n a\\_k^2 \\right)\r\n\\left( \\sum\\_{k=1}^n b\\_k^2 \\right)\r\n```\r\n```katex\r\n\\displaystyle\r\n    \\frac{1}{\r\n        \\Bigl(\\sqrt{\\phi \\sqrt{5}}-\\phi\\Bigr) e^{\r\n        \\frac25 \\pi}} = 1 \\frac{e^{-2\\pi}} {1 \\frac{e^{-4\\pi}} {\r\n        1 \\frac{e^{-6\\pi}}\r\n        {1 \\frac{e^{-8\\pi}}\r\n         {1 \\cdots} }\r\n        }\r\n    }\r\n```\r\n```latex\r\nf(x) = \\int_{-\\infty}^\\infty\r\n    \\hat f(\\xi)\\,e^{2 \\pi i \\xi x}\r\n    \\,d\\xi\r\n```\r\n### 分页符 Page break\r\n\r\n> Print Test: Ctrl   P\r\n\r\n[========]\r\n\r\n### 绘制流程图 Flowchart\r\n\r\n```flow\r\nst=>start: 用户登陆\r\nop=>operation: 登陆操作\r\ncond=>condition: 登陆成功 Yes or No?\r\ne=>end: 进入后台\r\n\r\nst->op->cond\r\ncond(yes)->e\r\ncond(no)->op\r\n```\r\n[========]\r\n\r\n### 绘制序列图 Sequence Diagram\r\n\r\n```seq\r\nAndrew->China: Says Hello\r\nNote right of China: China thinks\\nabout it\r\nChina-->Andrew: How are you?\r\nAndrew->>China: I am good thanks!\r\n```\r\n### End', '19', '0', '2020-01-09 04:29:41', '3');
INSERT INTO `articles` VALUES ('15', '1', 'undefined', 'undefined', '0', '0', '2020-01-17 10:32:15', '0');
INSERT INTO `articles` VALUES ('16', '1', 'undefined', 'undefined', '0', '0', '2020-01-17 10:32:23', '0');
INSERT INTO `articles` VALUES ('17', '1', 'undefined', '### Markdown 语法介绍\n\n- Markdown 是一种轻量级标记语言，让写作者专注于写作而不用关注样式。Coding 的许多版块均采用了 Markdown 语法，比如冒泡、讨论、Pull Request 等。\n- 列表项目标记通常放在最左边，项目标记后面要接一个字符的空格。\n\n> Coding.net 为软件开发者提供基于云计算技术的软件开发平台，包括项目管理，代码托管，运行空间和质量控制等等。\n\n*  Coding.net有以下主要功能:\n    > 代码托管平台\n    > 在线运行环境    \n    > 代码质量监控    \n    > 项目管理平台\n\n```ruby\nrequire \'redcarpet\'\nmarkdown = Redcarpet.new(\"Hello World!\")\nputs markdown.to_html\n```\n\nFirst Header | Second Header | Third Header\n------------ | ------------- | ------------\nContent Cell | Content Cell  | Content Cell\nContent Cell | Content Cell  | Content Cell\n\n```graph\ngraph TD;\n    A-->B;\n    A-->C;\n    B-->D;\n    C-->E;\n    E-->F;\n    D-->F;\n    F-->G;\n```\n\n```graph\ngantt\n        dateFormat  YYYY-MM-DD\n        title Adding GANTT diagram functionality to mermaid\n        section A section\n        Completed task            :done,    des1, 2014-01-06,2014-01-08\n        Active task               :active,  des2, 2014-01-09, 3d\n        Future task               :         des3, after des2, 5d\n        Future task2               :         des4, after des3, 5d\n        section Critical tasks\n        Completed task in the critical line :crit, done, 2014-01-06,24h\n        Implement parser and jison          :crit, done, after des1, 2d\n        Create tests for parser             :crit, active, 3d\n        Future task in critical line        :crit, 5d\n        Create tests for renderer           :2d\n        Add to mermaid                      :1d\n```', '0', '0', '2020-01-17 10:46:28', '0');
INSERT INTO `articles` VALUES ('18', '1', 'undefined', '### Markdown 语法介绍\n\n- Markdown 是一种轻量级标记语言，让写作者专注于写作而不用关注样式。Coding 的许多版块均采用了 Markdown 语法，比如冒泡、讨论、Pull Request 等。\n- 列表项目标记通常放在最左边，项目标记后面要接一个字符的空格。\n\n> Coding.net 为软件开发者提供基于云计算技术的软件开发平台，包括项目管理，代码托管，运行空间和质量控制等等。\n\n*  Coding.net有以下主要功能:\n    > 代码托管平台\n    > 在线运行环境    \n    > 代码质量监控    \n    > 项目管理平台\n\n```ruby\nrequire \'redcarpet\'\nmarkdown = Redcarpet.new(\"Hello World!\")\nputs markdown.to_html\n```\n\nFirst Header | Second Header | Third Header\n------------ | ------------- | ------------\nContent Cell | Content Cell  | Content Cell\nContent Cell | Content Cell  | Content Cell\n\n```graph\ngraph TD;\n    A-->B;\n    A-->C;\n    B-->D;\n    C-->E;\n    E-->F;\n    D-->F;\n    F-->G;\n```\n\n```graph\ngantt\n        dateFormat  YYYY-MM-DD\n        title Adding GANTT diagram functionality to mermaid\n        section A section\n        Completed task            :done,    des1, 2014-01-06,2014-01-08\n        Active task               :active,  des2, 2014-01-09, 3d\n        Future task               :         des3, after des2, 5d\n        Future task2               :         des4, after des3, 5d\n        section Critical tasks\n        Completed task in the critical line :crit, done, 2014-01-06,24h\n        Implement parser and jison          :crit, done, after des1, 2d\n        Create tests for parser             :crit, active, 3d\n        Future task in critical line        :crit, 5d\n        Create tests for renderer           :2d\n        Add to mermaid                      :1d\n```', '15', '0', '2020-01-17 10:46:38', '0');
INSERT INTO `articles` VALUES ('19', '1', 'dfsfsdfsdf', 'undefined', '0', '0', '2020-01-21 04:46:47', '0');
INSERT INTO `articles` VALUES ('20', '1', '5456456', 'undefined', '0', '0', '2020-01-21 04:47:08', '0');
INSERT INTO `articles` VALUES ('21', '1', '如何', 'undefined', '0', '0', '2020-01-21 04:49:02', '0');
INSERT INTO `articles` VALUES ('22', '1', '如何', 'undefined', '0', '0', '2020-01-21 04:49:04', '0');
INSERT INTO `articles` VALUES ('23', '1', '如何', 'undefined', '0', '0', '2020-01-21 04:49:04', '0');
INSERT INTO `articles` VALUES ('24', '1', '如何', 'undefined', '0', '0', '2020-01-21 04:49:04', '0');
INSERT INTO `articles` VALUES ('25', '1', '如何', 'undefined', '1', '0', '2020-01-21 04:49:11', '0');
INSERT INTO `articles` VALUES ('26', '1', '如何', 'undefined', '0', '0', '2020-01-21 04:49:12', '0');
INSERT INTO `articles` VALUES ('27', '1', '如何', 'undefined', '0', '0', '2020-01-21 04:49:12', '0');

-- ----------------------------
-- Table structure for article_set_label
-- ----------------------------
DROP TABLE IF EXISTS `article_set_label`;
CREATE TABLE `article_set_label` (
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `label_id` bigint(20) NOT NULL COMMENT '标签id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_set_label
-- ----------------------------
INSERT INTO `article_set_label` VALUES ('1', '1');

-- ----------------------------
-- Table structure for commend
-- ----------------------------
DROP TABLE IF EXISTS `commend`;
CREATE TABLE `commend` (
  `commend_id` bigint(255) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(255) NOT NULL,
  `ip_address` varchar(15) DEFAULT NULL,
  `user_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`commend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commend
-- ----------------------------
INSERT INTO `commend` VALUES ('1', '1', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('2', '1', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('3', '2', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('4', '2', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('5', '3', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('6', '5', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('7', '3', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('8', '4', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('9', '4', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('10', '5', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('12', '8', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('13', '9', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('14', '11', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('16', '7', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('17', '7', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('18', '14', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('19', '14', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('20', '12', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('23', '6', '0:0:0:0:0:0:0:1', null);
INSERT INTO `commend` VALUES ('24', '10', '0:0:0:0:0:0:0:1', null);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `label_id` int(20) NOT NULL,
  `label_parent_name` varchar(20) DEFAULT NULL,
  `label_name` varchar(20) NOT NULL,
  `label_description` text,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES ('1', '前端', 'Angular', '前端Angular框架');

-- ----------------------------
-- Table structure for manger_user
-- ----------------------------
DROP TABLE IF EXISTS `manger_user`;
CREATE TABLE `manger_user` (
  `uid` bigint(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL,
  `generate_time` datetime NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `sex` tinyint(4) NOT NULL COMMENT '0 为女 1 为男',
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manger_user
-- ----------------------------
INSERT INTO `manger_user` VALUES ('1', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('2', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('3', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('4', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('5', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('6', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('7', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('8', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('9', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('10', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('11', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('12', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);
INSERT INTO `manger_user` VALUES ('13', 'linz03', 'hu132875', '2019-09-08 00:00:00', null, null, '0', null);

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
