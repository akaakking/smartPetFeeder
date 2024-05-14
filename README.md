### blog相关接口

| 接口                   | 地址                 | 请求方式 | 请求体                                                       | 返回参数                                                     |
| ---------------------- | -------------------- | -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 上传blog接口           | /api/blog            | post     | image (MultipartFile): 博客的图片文件，必须上传。<br/>title (String): 博客的标题，必填。<br/>titleSrc (String): 博客标题的来源或附加信息，必填。<br/>content (String): 博客的正文内容，必填。 | 访问博客的url                                                |
| 根据id获取blog         | /api/blog/{id}       | get      | 无请求体，id参数通过path上传                                 | {<br/>  "id": 123,<br/>  "userId": 456,<br/>  "title": "Example Blog Title",<br/>  "titleSrc": "Source URL",<br/>  "content": "This is an example blog content."<br/>} |
| 收藏/取消收藏 博客     | /api/blog/like       | post     | {      <br/>   "blogId": 456,        // 博客ID        <br/>   "likeStatus"         // 是否收藏 0：未收藏 1：已收藏 <br/>} |                                                              |
| 获取某人收藏的博客列表 | /api/user/like/blog  | get      | /api/user/like/blog                                          | list下边这个<br />{<br/>  "id": 123,<br/>  "userId": 456,<br/>  "title": "Example Blog Title",<br/>  "titleSrc": "Source URL",<br/>  "content": "This is an example blog content."<br/>} |
| 获取博客被收藏的个数   | /api/blog/like/count | get      | /api/blog/like/count?blogId=456                              | 数字                                                         |

### user相关接口

| 接口          | 地址                        | 请求方式 | 请求体                                                       | 返回参数                                                     |
| ------------- | --------------------------- | -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 登录          | /api/user/login/wechatLogin | post     | /api/user/login/wechatLogin?code=&openId=                    | token                                                        |
| 添加用户信息  | /api/user/info              | post     | {<br/>  "id": null,<br/>  "name": null,<br/>  "account": null,<br/>  "avatar": null,<br/>  "password": null,<br/>  "wechatId": "wechat_id_value"<br/>} | 无                                                           |
| 获取用户 信息 | /api/user/info              | get      | 空                                                           | {<br/>  "id": null,<br/>  "name": null,<br/>  "account": null,<br/>  "avatar": null,<br/>  "password": null,<br/>  "wechatId": "wechat_id_value"<br/>} |

### 宠物相关接口

| 接口               | 地址          | 请求方式 | 请求体                                                       | 返回参数                                                     |
| ------------------ | ------------- | -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 保存宠物           | /api/pet      | post     | {  "id": 1,  ,  "name": "Buddy",  "gender": true,  "age": 5,  "weight": 15000,  "petType": "Dog",  "avatar": "path/to/avatar.jpg",  "description": "A friendly and energetic dog who loves long walks.",  "feedTimeGap": "8 hours",  "breakfast": "08:00",  "lunch": "12:00",  "dinner": "20:00",  "deviceId": "device123",  "avatarFile": null } |                                                              |
| 获取宠物信息       | /api/pet      | get      |                                                              | {  "id": 1,  "userId": 1001,  "name": "Buddy",  "gender": true,  "age": 5,  "weight": 15000,  "petType": "Dog",  "avatar": "path/to/avatar.jpg",  "description": "A friendly and energetic dog who loves long walks.",  "feedTimeGap": "8 hours",  "breakfast": "08:00",  "lunch": "12:00",  "dinner": "20:00",  "deviceId": "device123",  "avatarFile": null } |
| 获取某人的宠物列表 | /api/user/pet | get      |                                                              |                                                              |
