import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {
    List<BigInteger> divisors;

    public Result() {
        divisors = new ArrayList<>();
    }

    public List<BigInteger> getList() {
        return divisors;
    }

    public void add(BigInteger bi) {
        divisors.add(bi);
    }
}
