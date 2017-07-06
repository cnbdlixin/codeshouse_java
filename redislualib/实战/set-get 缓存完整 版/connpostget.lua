nginx.conf# import module
	--说明:当使用get方法时为查询缓存方法,当使用post方法时为更新缓存方法,为了获取post请求参数需要在location中写入: lua_need_request_body on;
    local redis = require("resty.rediscli")
    local request_method = ngx.var.request_method
    local args = nil
    local red = redis.new({host = "127.0.0.1", port = 6379,password = "tttt",database = 0})
    ngx.say('处理htpp请求，get或post请求的参数如下')
 	if "GET" == request_method then
		ngx.say("get redids 值")
		local rdskey =nil
		args  = ngx.req.get_uri_args()
		for key,val in pairs(args) do
                	if "key" == key  then
                		rdskey = val
			end	
		end
		local res, err = red:exec(
			function(red)
                        return red:get(rdskey)
                        end
                        )
		ngx.say(res)
	elseif "POST" == request_method then
		ngx.say("post 更新redis值")
		local rdskey = nil
		local rdsvalue = nil
		args=ngx.req.get_post_args()
		for key,val in pairs(args) do
                	if "key" == key then
                		rdskey = val
                	elseif "value" == key then
                		rdsvalue = val
                	end
        	end
		local res, err = red:exec(
                        function(red)
                       red:set(rdskey,rdsvalue)
                        end
                        )
		ngx.say(err)
	end
	ngx.say('get or post request over')			
	--本机配置,不带密码认证	
    --local red = redis.new({host = "127.0.0.1", port = 6379,password = -1})
    --测试环境机器 带密码认证	

	
    --local res, err = red:exec(
   --     function(red)
-- 	    red:set("lixin","lixinppt")
  --          return red:get("lixin")
   --     end
  --  )
--	ngx.say(res)
