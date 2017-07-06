# import module

    local redis = require("resty.rediscli")
    --本机配置,不带密码认证	
    --local red = redis.new({host = "127.0.0.1", port = 6379,password = -1})
    --测试环境机器 带密码认证	
   local red = redis.new({host = "127.0.0.1", port = 26379,password = "tttt",database = 1})
	
    local res, err = red:exec(
        function(red)
	    red:set("lixin","lixinppt")
            return red:get("lixin")
        end
    )
	ngx.say(res)
