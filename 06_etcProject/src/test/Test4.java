package test;

public class Test4 {

	public void main() {
		Test2 t2 = new Test2();
		Test1 t1 = new Test1();
		Test3 t3 = new Test3();
		t2.main(t1);
		// 시점1 - t2 소멸시점
		t3.main();
		// 시점2 - t3 소멸시점
		// 코드 100만줄
	}
}
