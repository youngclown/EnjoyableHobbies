package redisJsonMake.domain;

public class RedisDesktop {
    private String auth;
    private String host;
    private String name;
    private int port;
    private int ssh_port=22;
    private int timeout_connect = 60000;
    private int timeout_execute = 60000;

    public RedisDesktop(String auth, String host, String name, int port) {
        this.auth = auth;
        this.host = host;
        this.name = name;
        this.port = port;
    }

    @Override
    public String toString() {
        return "{" +
                "\"auth\":\"" + auth + "\"" +
                ", \"host\":\"" + host + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"port\":\"" + port + "\"" +
                ", \"ssh_port\":" + ssh_port +
                ", \"timeout_connect\":" + timeout_connect +
                ", \"timeout_execute\":" + timeout_execute +
                '}';
    }
}
