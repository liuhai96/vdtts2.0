这个目录存放的是后端数据传输对象

数据在Controller---Service---Dao---Mapper---Util中传输时
举例：前端登录时对Controller层传入一个User对象，需要进入Service层进行校验，但是Service层不要那么多参数，只要账号密码
这里寸的就是账号密码打包好之后的对象