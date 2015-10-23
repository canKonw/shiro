import com.hh.util.Md5Util;
import org.junit.Test;

/**
 * Created by hh on 15-10-23.
 */
public class Md5Test {

    @Test
    public  void getString(){
        String a = "123456";
        String b = Md5Util.md5Encode(a);
        System.out.println("---:"+b);
    }
}
