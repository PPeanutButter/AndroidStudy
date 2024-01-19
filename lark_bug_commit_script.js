// ==UserScript==
// @name         一键复制缺陷Commit信息
// @namespace    http://tampermonkey.net/
// @version      2024-01-18
// @description  try to take over the world!
// @author       You
// @match        https://project.feishu.cn/weplay-*/issue*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=feishu.cn
// @grant        GM_getValue
// @grant        GM_setValue
// @grant        GM_addStyle
// ==/UserScript==

(function() {
    'use strict';

    // Your code here...
    // 复制commit按钮
    var button = document.createElement('button');
    button.textContent = 'BugFix Commit';
    button.style.position = 'fixed';
    button.style.bottom = '0px';
    button.style.right = '0px';
    button.style.zIndex = '9999';
    document.body.appendChild(button);
    button.addEventListener('click', function() {
        var element = document.querySelector('.work-object-detail-header-title');
        var titleContent = element.textContent;
        console.log(titleContent);
        var idName = element.id;
        var parts = idName.split('-');
        var bugID = parts[parts.length - 1];
        console.log(bugID);
        var currentUrl = window.location.href;
        var url = new URL(currentUrl);
        url.search = '';
        var urlWithoutQuery = url.toString();
        var regexPattern = /版本迭代$/;
        var mText = "";
        var spanTags = document.getElementsByTagName("span");
        for (var i = 0; i < spanTags.length; i++) {
            var spanContent = spanTags[i].textContent;
            if (regexPattern.test(spanContent)) {
                console.log("匹配到的内容:", spanContent);
                mText = spanContent;
            }
        }
        var mID = GM_getValue(mText);
        console.log(mText);
        var textToCopy = titleContent + " f-" + bugID + " m-" + mID + " " + urlWithoutQuery;
        console.log(textToCopy);
        var textarea = document.createElement('textarea');
        textarea.value = textToCopy;
        document.body.appendChild(textarea);
        textarea.select();
        document.execCommand('copy');
        document.body.removeChild(textarea);
    });
    // 保存迭代id按钮
    var button1 = document.createElement('button');
    button1.textContent = '保存迭代id';
    button1.style.position = 'fixed';
    button1.style.bottom = '0px';
    button1.style.right = '150px';
    button1.style.zIndex = '9999';
    document.body.appendChild(button1);
    button1.addEventListener('click', function() {
        var element = document.querySelector('.work-object-detail-header-title-text');
        var titleContent = element.textContent;
        console.log(titleContent);
        var idName = element.id;
        var parts = idName.split('-');
        var pID = parts[parts.length - 1];
        console.log(pID);
        GM_setValue(titleContent, pID);
    });
})();
