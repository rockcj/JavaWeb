//使用异步请求，使用jQuery的ajax方法
async function insertItem() {
    //读取表格，相当于2维数组
    let rows = document.querySelectorAll("#TableBody tr");
    let items = [];

    for (let row of rows) {
        //获取每一行输入框的值，1维数组
        let inputs = row.querySelectorAll("input");
        //  创建一个对象，将输入框的值保存起来
        let item = {
            iid: inputs[0].value.trim(),
            iname: inputs[1].value.trim(),
            iflag: inputs[2].value.trim()
        };
        if (item.iid && item.iname && item.iflag) {
            items.push(item);
        }
    }

    if (items.length === 0) {
        alert("没有有效的数据可以提交！");
        return;
    }

    //showLoading();

    // 使用 fetch API 的异步实现
    // try {
    //     const response = await fetch('/insertItem', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(items)
    //     });
    //
    //     if (!response.ok) throw new Error('Network response was not ok');
    //
    //     const data = await response.json();
    //     console.log(data);
    //     hideLoading();
    //     alert("提交成功！");
    //     location.href = "/queryItemAll"; // 提交成功跳转
    // } catch (error) {
    //     hideLoading();
    //     alert("提交失败，请检查控制台！");
    //     console.error('Error:', error);
    // }

    $.ajax({
        type: "POST",
        url: "/insertItem",
        data: JSON.stringify(items),
        contentType: "application/json",

        success: function (data) {
            console.log("响应数据:", data);
            console.log("666");
            if(data.status)
            {
                alert("提交成功！");
                location.href = "/queryItemAll"; // 提交成功跳转至/queryItemAll页面
            }
           // hideLoading();

            //window.location.href = "https://www.tongyi.com/qianwen?spm=5176.28326591.0.0.40f73da2S9GOIK";
        },
        error: function (xhr, status, error) {
            hideLoading();
            alert("提交失败，请检查控制台！");
            console.error(xhr.responseText);
        }
    });

    //

    // 显示加载提示
    // function showLoading() {
    //     // 创建加载提示元素
    //     const loadingDiv = document.createElement('div');
    //     loadingDiv.id = 'loading';
    //     loadingDiv.style.position = 'fixed';
    //     loadingDiv.style.top = '0';
    //     loadingDiv.style.left = '0';
    //     loadingDiv.style.width = '100%';
    //     loadingDiv.style.height = '100%';
    //     loadingDiv.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
    //     loadingDiv.style.display = 'flex';
    //     loadingDiv.style.justifyContent = 'center';
    //     loadingDiv.style.alignItems = 'center';
    //     loadingDiv.style.color = 'white';
    //     loadingDiv.style.fontSize = '24px';
    //     loadingDiv.textContent = '正在处理，请稍候...';
    //
    //     // 将加载提示添加到页面
    //     document.body.appendChild(loadingDiv);
    // }

// 隐藏加载提示
//     function hideLoading() {
//         // 移除加载提示元素
//         const loadingDiv = document.getElementById('loading');
//         if (loadingDiv) {
//             document.body.removeChild(loadingDiv);
//         }
//     }
    //使用现代浏览器支持的 fetch API
    // 发送 POST 请求
    // fetch('/insertItem', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(items)// 将 items(item对象数组) 转成的 JSON 字符串 作为请求体发送给服务器
    // })
    //     //处理响应结果
    //     .then(response => {
    //         if (response.ok) {
    //             alert("提交成功！");
    //             location.reload(); // 提交成功后刷新页面
    //         } else {
    //             alert("提交失败，请重试！");
    //         }
    //     })
    //     .catch(error => {
    //         console.error('Error:', error);
    //         alert("网络错误，请检查控制台！");
    //     });
}
