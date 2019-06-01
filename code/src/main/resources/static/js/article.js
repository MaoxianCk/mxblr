// 页面初始化：填充数据
window.onload = function () {
    showAllArticleInfo();
};

// 显示全部文章信息
function showAllArticleInfo() {
    var articleId = getQueryVariable();
    $.ajax({
        type: "get",
        url: "http://localhost:8080/user/article/getArticleById?id="+articleId,
        dataType: "json",
        success: function (json) {
            var item = json.data;
            // 填充文章信息
            $("#headerImg").attr("src", item.articleInfo.imageUrl);
            document.querySelector("#title").innerHTML = item.articleInfo.title;
            document.querySelector("#content").innerHTML = item.content;
        }
    });
}

// 解析url获取当前id
function getQueryVariable() {
    var query = window.location.search.substring(1);
    var pair = query.split("=");
    for (var i = 0; i < pair.length; i++) {
        var ans = parseInt(pair[i]); //如果变量val是字符类型的数则转换为int类型 如果不是则ival为NaN
        if (!isNaN(ans)) {
            return ans;
        }
    }
    return (false);
}