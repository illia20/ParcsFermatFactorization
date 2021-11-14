import parcs.*;

import java.math.BigInteger;

public class FermatFactorization implements AM {
    private final static BigInteger ONE = BigInteger.ONE;

    public static BigInteger sqrt(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Argument is negative");
        }

        if (x == BigInteger.ZERO || x == BigInteger.ONE) {
            return x;
        }

        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;

        for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y)).add(y)).divide(two));

        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(BigInteger.ONE);
        }
    }

    public BigInteger FermatFactor(BigInteger N)
    {
        BigInteger a = sqrt(N);
        BigInteger b2 = a.multiply(a).subtract(N);

        if(b2.compareTo(BigInteger.ZERO)==-1)
        {
            a = a.add(ONE);
            b2 = (a.multiply(a)).subtract(N);
        }

        while (!isSquare(b2))
        {
            a = a.add(ONE);
            b2 = a.multiply(a).subtract(N);
        }
        return a.subtract(sqrt(b2));
    }

    public boolean isSquare(BigInteger N)
    {
        BigInteger sqr = sqrt(N);
        if (sqr.multiply(sqr).equals(N) || (sqr.add(ONE)).multiply(sqr.add(ONE)).equals(N))
            return true;
        return false;
    }

    public void run(AMInfo amInfo) {
        Result result = new Result();
        String str = amInfo.parent.readObject().toString();
        BigInteger N = new BigInteger(str);

        if (N.isProbablePrime(1) || N.compareTo(ONE) == 0)
            result.add(N);

        else {
            BigInteger divisor = FermatFactor(N);
            point p = amInfo.createPoint();
            channel c = p.createChannel();
            p.execute("TheAlgorithm");
            c.write(divisor.toString());
            point p1 = amInfo.createPoint();
            channel c1 = p1.createChannel();
            p1.execute("TheAlgorithm");
            c1.write(N.divide(divisor).toString());
            Result result1 = (Result) (c.readObject());
            Result result2 = (Result) (c1.readObject());
            for (BigInteger div1 : result1.getList())
                result.add(div1);
            for (BigInteger div2 : result2.getList())
                result.add(div2);
        }

        amInfo.parent.write(result);
    }
}
