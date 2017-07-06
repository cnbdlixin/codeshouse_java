# import module
    local json = require("cjson")
    --需要先将resty.rediscli 文件放到 openresty/lualib/resty 目录下
    local redis = require("resty.rediscli")
    --local logger = require "resty.logger.socket"
    --本机配置,不带密码认证	
    local red = redis.new({host = "127.0.0.1", port = 6379,password = -1})
    --测试环境机器 带密码认证	
   --local red = redis.new({host = "127.0.0.1", port = 26379,password = "tttt",database = 1})
	
    local res, err = red:exec(
        function(red)
            return red:zrevrange("person" ,0, -1)
        end
    )
	--ngx.say(res)
   	local data = {1, 2}
	local resd = json.encode(res)
	local resc =  table.concat(res,"|")
	ngx.say(resd..resc)
	--可以输出日志到ngx的文件
	ngx.log(ngx.ERR,"out",resd)
	--以前返回结果 cb({"value":[0,1,2]});
	--函数说明:inser:在数组里数据添加元素  encode:装程序中数据结果表现为json   concat:将数组里的元素合并成一个串,第二参数指名使用什么字符进行拼接
	output = {}
	value = {}
	outputinfo = {}
	table.insert(output,"cb")
	table.insert(value,0)
	table.insert(value,1)
	table.insert(value,2)
	table.insert(output,"(")
	outputinfo["value"] = value
	table.insert (output,json.encode(outputinfo))
	table.insert (output,");")
	ngx.say(table.concat(output,""))
	--以前返回结果 cb({"value":[0,1,2]});
