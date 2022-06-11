# kafkatest
Kafka Producer and Consumer

# Kafka Installation 

sudo adduser kafka

sudo adduser kafka sudo

su -l kafka

cd /tmp/

wget "https://dlcdn.apache.org/kafka/3.2.0/kafka_2.12-3.2.0.tgz"

mkdir /opt/kafka && cd /opt/kafka

tar -xvzf /tmp/kafka_2.12-3.2.0.tgz --strip 1

nano /opt/kafka/config/server.properties

# added :
	delete.topic.enable = true
# Modify;
  log.dirs=/opt/kafka/kafka-logs

  sudo nano /etc/systemd/system/zookeeper.service
	[Unit]
  Requires=network.target remote-fs.target
  After=network.target remote-fs.target

  [Service]
  Type=simple
  User=kafka
  ExecStart=/opt/kafka/bin/zookeeper-server-start.sh /opt/kafka/config/zookeeper.properties
  ExecStop=/opt/kafka/bin/zookeeper-server-stop.sh
  Restart=on-abnormal

  [Install]
  WantedBy=multi-user.target


sudo nano /etc/systemd/system/kafka.service

	[Unit]
  Requires=zookeeper.service
  After=zookeeper.service

  [Service]
  Type=simple
  User=kafka
  ExecStart=/bin/sh -c '/opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties > /opt/kafka/kafka.log 2>&1'
  ExecStop=/opt/kafka/bin/kafka-server-stop.sh
  Restart=on-abnormal

  [Install]
  WantedBy=multi-user.target

systemctl start zookeeper
systemctl status zookeeper

sudo systemctl start kafka
sudo systemctl status kafka


sudo systemctl enable zookeeper
sudo systemctl enable kafka


/opt/kafka/bin/kafka-topics.sh --create --topic time-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1


echo "Hello, World" | /opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic time-topic > /dev/null

/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic time-topic --from-beginning


# For remote connections: 
nano /opt/kafka/config/server.properties
	listeners=PLAINTEXT://192.168.0.100:9092
  advertised.listeners=PLAINTEXT://192.168.0.100:9092
