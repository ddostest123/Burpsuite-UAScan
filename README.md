# Burpsuite UAScan 插件
Burpsuite插件：被动方式进行未授权访问漏洞的扫描
+ 被动扫描经过Burpsuite的每一个请求
+ 通过修改Cookie头重新访问判断是否存在未授权访问问题
+ 如果扫描到未授权访问的URL会显示到空白区域中
+ 后期考虑加入过滤器，否则会造成内容过多
### 简易教程
在Release中下载插件，安装后会多出一个页面：UAScan。
进入这个页面勾选开启被动扫描，然后不用做任何操作，正常使用Burpsuite即可。
自动检测所有的流量，然后将可能存在未授权访问漏洞的URL显示到UAScan的页面中。
一般建议在登录某系统后使用，是挖掘未授权访问漏洞的利器。
目前只排除了静态文件，后续可以自定义排除规则
### 开发者
- 小迪安全团队（许少，人走茶凉）
****
# Burpsuite UAScan Plugin
Burpsuite Plugin: Passive Scanning For Unauthorized Access Vulnerabilities
+ Passive scanning goes through every request from burpsuite
+ Determine whether there is unauthorized access by deleting the cookie header
+ If an unauthorized URL is scanned, it will be displayed in a blank area
### Developer
- XiaoDi Team(Xu,Man Go Tea Cool)
****
![ua.png](https://xuyiqing-1257927651.cos.ap-beijing.myqcloud.com/burpsuite/ua.png)
