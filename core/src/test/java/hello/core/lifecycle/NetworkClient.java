package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// initializingBean : 얘가 초기화 bean임
public class NetworkClient {

    //접속할 서버의 url
    private String url;

    //default 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    //외부에서 setter로 넣을 수 있게 만듦
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + "message : " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    // bean 종료될 때 호출됨
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
