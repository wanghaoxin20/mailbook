# **邮书接口文档**

## 一 . 用户接口
1. 用户注册

    1. **发送验证码**

        + **请求地址及方法**
            
            url:&nbsp;**/mailbook/verificationCode/send**

            method:&nbsp;**post**
        
        + **请求参数**

            |参数|意义|
            |:---|:---|
            |username|用户名(邮箱,最大长度60,采用正则验证格式)

            + 示例
                
                username: a706519033@qq.com

        + **返回**

            **格式为json**
            |参数|意义|
            |:--|:--|
            |code|状态码<br>200:成功发送<br>300:未登录<br>400:参数不符合规范<br>401:邮箱已存在<br>500:服务器内部错误|
            |msg|该次请求返回code的描述|
            |data|无

            + 示例
                ```javascrip
                    {
                        "code":200,
                        "msg":success,
                        "data":{}
                ```

    2. **验证验证码**

        + **请求地址及方法**
            
            url:&nbsp;**/mailbook/verificationCode/check**

            method:&nbsp;**post**
        
        + **请求参数**

            |参数|意义|
            |:---|:---|
            |verificationCode|验证码|

            + 示例
                
            verificationCode: 9375

        + **返回**

            **格式为json**
            |参数|意义|
            |:--|:--|
            |code|状态码<br>200:成功<br>300:未登录<br>404:还未发送过验证码<br>600:验证码超时<br>500:服务器内部错误|
            |msg|该次请求返回code的描述|
            |data|无|

            + 示例
                ```javascrip
                    {
                        "code": 200,
                        "msg": "success",
                        "data": {}
                    }
                ```

    3. 注册
        + **请求地址及方法**
            
            url:&nbsp;**/mailbook/signUp**

            method:&nbsp;**post**
        
        + **请求参数**
            
             **请求content-type为application/json**
            
            |参数|意义|
            |:---|:---|
            |user|注册信息<br>nickname:名字,2-22位 <br>password:密码,长度为6-22位<br>tel:电话号码,11位数字<br>qq:qq号码,6-11位数字<br>address:6-255位|

            + 示例
                ```javascrip
                    {
                        "nickname":"邮书",
                        "password":"666666",
                        "tel":"17766665555",
                        "qq":"12345678",
                        "address":"重庆市邮电大学"
                    }
                ```

        + **返回**

            **格式为json**
            |参数|意义|
            |:--|:--|
            |code|状态码<br>200:成功<br>300:未登录<br>404:还未发送过验证码<br>600:验证码超时<br>500:服务器内部错误|
            |msg|该次请求返回code的描述|
            |data|无|
            
            + 示例
                ```javascrip
                    {
                        "code": 200,
                        "msg": "success",
                        "data": {}
                    }
                ```
    
        