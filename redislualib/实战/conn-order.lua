# import module

    local redis = require("resty.rediscli-order")

    --使用配置文件里的配置
   local red = redis.new()
	
    local res, err = red:exec(
        function(red)
	    red:set("lixin","lixinppt")
            return red:get("lixin")
        end
    )
	ngx.say(res)
