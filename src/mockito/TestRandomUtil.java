package mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

public class TestRandomUtil {

	public void testSelect() {
		Random r = mock(Random.class);// 创建一个mock对象
		when(r.nextInt(5)).thenReturn(4);// 配置mock行为：当r.nextInde被调用，且参数为sum时，返回i
		System.out.println(r.nextInt(5));
	}
	public static void main(String args[]){
		new TestRandomUtil().testSelect();
	}
}
