package redisJsonMake.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RedisSetting {


    public static void main(String[] args) {

        String redisNm = "";

        String[] tempRedis = redisNm.split(",");

        for (String tr: tempRedis) {
            String[] temp = tr.split(":");
            System.out.println(new RedisDesktop("",temp[0],"scList_"+temp[0],Integer.parseInt(temp[1])).toString()+",");
        }

    }
}
