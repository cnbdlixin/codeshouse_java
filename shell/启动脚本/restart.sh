echo kill test
pkill -f com.test.TestMain
pkill -f jstat
sleep 2

echo its ok?

ps -ef | grep com.test.TestMain

echo run...


#-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=9888
nohup java   -Dcom.sun.management.jmxremote  -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=192.168.10.1 -Dcom.sun.management.jmxremote.port=9998  -Xms1024m -Xmx1500m -cp log4j-1.2.8.jar:com.objectplanet.image.PngEncoder.jar:commons-codec-1.3.jar:commons-collections-3.1.jar:commons-digester.jar:commons-io-1.4.jar:commons-lang-2.4.jar:commons-logging.jar:quartz-1.6.0.jar:jta.jar:. com.test.TestMain & 


echo check it 

ps -ef | grep com.sohu.peak.stock.painter.Entry


sleep 2

pid=`ps -ef|grep 1500|awk '{print $2}'|head -1`

logpath='/opt/log/test/gc.log'
echo 'the picture data generation start at ' >> $logpath
#date >>  $logpath
#echo 'the process id:' >> $logpath
#echo $pid >> $logpath
nohup jstat -gc -t $pid  10000 >> $logpath &
