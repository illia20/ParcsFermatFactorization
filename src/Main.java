import parcs.*;

import java.math.BigInteger;
import java.util.Scanner;

public class Main implements AM{
    public static void main(String[] args) {
        task curtask = new task();
        curtask.addJarFile("FermatFactorization.jar");
        (new Main()).run(new AMInfo(curtask, (channel) null));
        curtask.end();
    }

    public void run(AMInfo amInfo) {
        System.out.print("Enter N: ");
        Scanner sc = new Scanner(System.in);
        BigInteger n = sc.nextBigInteger();
        System.out.println("N is: " + n);
        if(n.compareTo(BigInteger.TWO) < 0 || !n.testBit(0)) {
            System.out.println("N should be not even and positive.");
            return;
        }
        long t_start = System.nanoTime();
        try {
            point p = amInfo.createPoint();
            channel c = p.createChannel();
            p.execute("FermatFactorization");
            c.write(n.toString());
            System.out.println("Working");
            Result result = (Result) (c.readObject());
            long t_finish = System.nanoTime();
            for (BigInteger divisor : result.getList()) {
                System.out.print(divisor + " ");
            }
            System.out.println(" ");
            long executedTime = t_finish - t_start;
            System.out.println(("Time: " + executedTime/1e9 +" sec."));

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
