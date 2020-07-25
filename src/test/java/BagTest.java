import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagTest {

    public static void main(String[] args) {
        bag();
    }

    private static void bag() {
        Map<Integer, List> map = new HashMap<>();
        int V = 20, N = 10;
        int v[] = new int[10000];
        int w[] = new int[10000];
        int f[] = new int[100000];
        //下面是初始化所有的无效状态
        for (int i = 0; i < 10000; i++) {
            f[i] = -100000;
        }
        //f[0]是有效状态
        f[0] = 0;
        //输入每个物体的体积和价值
        v[1] = 2;
        v[2] = 4;
        v[3] = 3;
        v[4] = 2;
        v[5] = 5;
        v[6] = 9;
        v[7] = 4;
        v[8] = 8;
        v[9] = 3;
        v[10] = 7;

        w[1] = 1;
        w[2] = 1;
        w[3] = 1;
        w[4] = 1;
        w[5] = 1;
        w[6] = 1;
        w[7] = 1;
        w[8] = 1;
        w[9] = 1;
        w[10] = 1;
        //动态规划过程
        a:for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                //	f[20] = max(f[20],f[17]+5);
                //	f[19] = max(f[19], f[16] + 5);
                //	f[j] = max(f[j], f[j - v[i]] + w[i]);
                if (f[j] < f[j - v[i]] + w[i]) {
                    f[j] = f[j - v[i]] + w[i];
                    if (f[j] > 0) {
                        List<Integer> ele = new ArrayList<>();
                        if (map.get(j - v[i]) != null) {
                            ele.addAll(map.get(j - v[i]));
                        }
                        ele.add(i-1);
                        map.remove(j);
                        map.put(j, ele);
                    }
                    if (f[j] > 0 && j == V) {
                        break a;
                    }
                }
                if (f[j] < 0) {
                    f[j] = -100000;
                }
            }
        }

        System.out.println(map.get(V));
        //判断能否恰好装满背包
//        if (f[V] > 0) {
//            for (int m = 0; m <= V; m++) {
//                System.out.println( f[m] );//背包恰好装满了，输出结果
//            }
//            System.out.println(map.get(V));
//
//        }
    }

}
