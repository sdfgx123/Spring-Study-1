package hello.core.singleton;

public class SingletonService {

    // 자기자신을 내부에 static으로 하나 가짐 -> public static SingletonService 까지
    // 이렇게 하면 : 내부적으로 자기자신 객체를 하나 생성해서 instance에 참조로 넣어놓다가
    // public static SingletonService에서 조회만 함
    private static final SingletonService instance = new SingletonService();

    // 딱 1개의 객체 인스턴스만 존재 -> 생성자를 private으로 막아서 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다
    // 신입개발자든 누가 new 인스턴스를 만들 수 있기 때문에 private으로 막아버림
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
