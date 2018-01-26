# emojiboard

一个简单的表情面板。

虽然名字叫 emoji board，但是使用的并不是 emoji 表情，而是一组自定义的表情。

这组自定义表情的相关资源放置在了 assets 目录之下；

其中 img 文件夹中放置的是表情的图片资源，共包含41个常用表情以及一个删除按钮的图片资源；

文件 emojiList.json 以 json 数组的形式描述了这组表情的 id、资源路径、以及文字含义，如：

```javascript
  {
    "id": "41",
    "emojiPath": "file:///android_asset/emoji/img/emoji_041.png",
    "emojiName": "[心碎了]"
  }
```