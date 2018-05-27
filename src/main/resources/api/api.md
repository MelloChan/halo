### 0. 声明
- API-URL: 123.207.121.122:8868/api/halo  
- 通信框架:axios
- Content-Type:json 默认形式
- Content-Type: application/x-www-form-urlencoded 需自定义请求头
```
// 参考:https://github.com/axios/axios/blob/master/README.md#using-applicationx-www-form-urlencoded-format
import qs from 'qs';
const data = { 'bar': 123 };
const options = {
  method: 'POST',
  headers: { 'content-type': 'application/x-www-form-urlencoded' },
  data: qs.stringify(data),
  url,
};
axios(options);
```  

### 1. 注册
/registers  
#### 1.1 校验手机号
GET /verifyPhone/{phone}      
##### 请求示例
GET /verifyPhone/{13192265851}  
GET /verifyPhone/{13192265855}   
GET /verifyPhone/{1}    
GET /verifyPhone/{1319226585541}    
<table>
        <tr>
            <th>参数名</th>
            <th>类型</th>
            <th>参数说明</th>       
        </tr>
        <tr>
                    <th>phone</th>
                    <th>String</th>
                    <th>用户手机号(11位)</th>
                </tr>
        <tr>
            <th>errorCode</th>
            <th>String</th>
            <th>业务状态码(参考error_code.properties)</th>
        </tr>
         <tr>
             <th>msg</th>
             <th>String</th>
             <th>出错时的消息(参考error_code.properties)</th>
         </tr>      
</table>    

##### 返回示例
```
{
  "errorCode": 40018,
  "msg": "账号已存在"
}

{
  "errorCode": 0,
  "data": {
    "phone": "13192265851"
  }
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```
#### 1.2 请求发送短信验证码
GET /requestSmsCode/{phone}
##### 请求示例
GET /requestSmsCode/{13192265851}    
GET /requestSmsCode/{13192265855}   
GET /requestSmsCode/{1}    
GET /requestSmsCode/{1319226585541}   
##### 返回示例
```
{
  "errorCode": 0,
  "data": {
    "phone": "13192265851"
  }
}  

{
  "errorCode": 50002,
  "msg": "与第三方通讯失败"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```  

#### 1.3 验证短信验证码
POST /verifyCode  
Content-Type: application/x-www-form-urlencoded 
##### 请求示例
POST /verifyCode   
```
{
    "phone":"13192265851",
    "code":"2581"
}

{
    "phone":"13192265851",
    "code":"2583"
}

{
    "phone":"1",
    "code":"2581"
}

{
    "phone":"13192265851",
    "code":"258"
} 
 ```  
 <table>
         <tr>
             <th>参数名</th>
             <th>类型</th>
             <th>参数说明</th>       
         </tr>
         <tr>
                     <th>phone</th>
                     <th>String</th>
                     <th>用户手机号(11位)</th>
                 </tr>
         <tr>
             <th>code</th>
             <th>String</th>
             <th>短信验证码(4位)</th>
         </tr>   
 </table>  
 
##### 返回示例
```
{
  "errorCode": 0,
  "data": {
    "phone": "13192265851"
  }
}  

{
  "errorCode": 40016,
  "msg": "验证码错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```
#### 1.4 补充用户名与密码  
POST /registerByPhone    
(默认)Content-Type: application/json  
##### 请求示例    
POST /registerByPhone 
```
{
    "phone":"13192265851",
    "username":"Mello",
    "pwd":"13192265851"
}

{
    "phone":"13192265855",
    "username":"DEMO",
    "pwd":"13192265855"
}

{
    "phone":"",
    "username":"Mello",
    "pwd":"13192265851"
}

{
    "phone":"131965851",
    "username":"",
    "pwd":"131"
}
```  
<table>
         <tr>
             <th>参数名</th>
             <th>类型</th>
             <th>参数说明</th>       
         </tr>
         <tr>
         <th>phone</th>
         <th>String</th>
         <th>用户手机号(11位)</th>
         </tr>
         <tr>
         <th>username</th>
         <th>String</th>
         <th>用户名(1位~32位)</th>
         </tr>
         <tr>
         <th>pwd</th>
         <th>String</th>
         <th>密码(8位~16位)</th>
         </tr>  
 </table> 

##### 返回实例  
```
{
  "errorCode": 40018,
  "msg": "账号已存在"
}  

{
    "errorCode":0,
    "data":{
        "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjEsImV4cCI6MTUyNzQwOTA1NywiaWF0IjoxNTI3NDA1NDU3fQ.kwEAwhmmGnATJxOHLVQ4XFw5Ow6GGRL8nV7apx13xEc"
    }
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```

### 2. 登录
/auths
#### 2.1 校验手机号
GET /verifyPhone/{phone}      
##### 请求示例
GET /verifyPhone/{13192265851}    
GET /verifyPhone/{13192265855}    
GET /verifyPhone/{1}    
GET /verifyPhone/{1319226585541}          
##### 返回示例
```
{
  "errorCode": 0,
  "data": {
    "phone": "13192265851"
  }
}

{
  "errorCode": 40014,
  "msg": "账号不存在"
} 

{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```
#### 2.2 手机+密码登录
POST /loginByPwd  
(默认)Content-Type: application/json  
##### 请求示例
POST /loginByPwd  
```
{
    "phone":"13192265851",
    "pwd":"13192265851"
}

{
    "phone":"13192265851",
    "pwd":"131922658"
} 

{
    "phone":"",
    "pwd":"13192265851"
} 

{
    "phone":"13192265851",
    "pwd":""
}
```

##### 返回示例
```
{
    "errorCode":0,
    "data":{
        "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjEsImV4cCI6MTUyNzQwOTA1NywiaWF0IjoxNTI3NDA1NDU3fQ.kwEAwhmmGnATJxOHLVQ4XFw5Ow6GGRL8nV7apx13xEc"
    }
}  

{
  "errorCode": 40015,
  "msg": "密码错误"
}
  
{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```
#### 2.3 请求发送短信验证码
GET /requestSmsCode/{phone}
##### 请求示例
类似1.2  
##### 返回示例
类似1.2,此时验证的是存在账户与否,刚好相反  
#### 2.4 手机号+短信验证码登录
POST /loginByCode  
Content-Type: application/x-www-form-urlencoded 
##### 请求示例
```
{
    "phone":"13192265851",
    "code":"2581"
}

{
    "phone":"13192265851",
    "code":"2583"
}

{
    "phone":"1",
    "code":"2581"
}

{
    "phone":"13192265851",
    "code":"258"
} 
 ```  
##### 返回示例
```
{
    "errorCode":0,
    "data":{
        "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjEsImV4cCI6MTUyNzQwOTA1NywiaWF0IjoxNTI3NDA1NDU3fQ.kwEAwhmmGnATJxOHLVQ4XFw5Ow6GGRL8nV7apx13xEc"
    }
}  

{
  "errorCode": 40016,
  "msg": "验证码错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}

{
  "errorCode": 40017,
  "msg": "参数错误"
}
```