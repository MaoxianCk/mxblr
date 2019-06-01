// 页面初始化：填充数据
window.onload = function() {
    showAllArticleInfo();
};

// 显示全部文章信息
function showAllArticleInfo() {
    $.ajax({
        type: "get",
        url: "http://localhost:8080/user/article/getArticleInfoList",
        dataType: "json",
        success: function(json) {
            $.each(json.data, function(i, item) {
                // 填充文章信息
                var articleInfo = document.querySelector("#articleInfo1");
                articleInfo.content.querySelector("#articleHref").setAttribute("href","article?id="+item.articleId);
                articleInfo.content.querySelector("#title").innerHTML = item.title;
                articleInfo.content.querySelector("#articleImg").innerHTML = item.imageUrl;
                articleInfo.content.querySelector("#createdTime").innerHTML = item.createdTime;
                articleInfo.content.querySelector("#summary").innerHTML = item.summary;

                document.getElementById("articleInfos").appendChild(articleInfo.content.cloneNode(true));
            });
        }
    });
}