import java.math.BigDecimal;

public class RSA {

	private final long euler;
	private final long publicHalf1;
	private final long publicHalf2;
	
	private final long[] publicKey;
	private final long privateKey;

	public RSA(long prime1, long prime2) {
		System.out.println("Configuring RSA");
		this.euler = (1 - prime1) * (1 - prime2);
		System.out.println("Calculating the public key");
		this.publicHalf1 = ggtPrime();
		this.publicHalf2 = prime1 * prime2;
		System.out.println("Calculating the private key");
		this.privateKey = setPrivateKey();
		this.publicKey = setPublicKey();
		System.out.println("Keys ready");
	}

	private long ggtPrime() {
		long temp = euler - 1;
		long number = 0;
		while (number != 1) {
			number = ggt(temp, euler);
			temp--;
		}
		return temp + 1;
	}

	private long ggt(long a, long b) {

		while (b != 0) {
			long h = a % b;
			a = b;
			b = h;
		}

		return a;

	}

	private boolean divIsNat(long a, long b) {
		long c = a / b;
		return c * b == a;
	}

	private long[] setPublicKey() {
		long[] temp = { publicHalf1, publicHalf2 };
		return temp;
	}

	public long[] getPublicKey() {
		return publicKey;
	}



	private long setPrivateKey() {
		long s = 1;
		long temp = 0;
		while (true) {
			temp = (s * euler + 1) / publicHalf1;
			if (divIsNat(s * euler + 1, publicHalf1) && temp != publicHalf1) {
				return temp;
				}
			
			s++;
		}
		

	}

	public long getPrivateKey() {
		return privateKey;
	}

	private BigDecimal bigMath(BigDecimal a, long b, long c) {
		BigDecimal Bi = a;

		Bi = Bi.pow((int) b);
		Bi = Bi.remainder(new BigDecimal(c));
		return Bi;
	}
	
	public BigDecimal[] encrypt(String in) {
		char[] c = in.toCharArray();
		BigDecimal[] temp = new BigDecimal[c.length];
		for(int i = 0; i < c.length; i++) {
			temp[i] = encrypt(new BigDecimal(c[i]));
		}
		return temp;
	}
	
	public String decrypt(BigDecimal[] in) {
		long[] temp = new long[in.length];
		StringBuilder temp2 = new StringBuilder(in.length);
		for(int i = 0; i < in.length; i++) {
			temp[i] = decrypt((in[i])).longValue();
			temp2.append((char)temp[i]);
		}
		return temp2.toString();
	}
	
	
	public BigDecimal encrypt(BigDecimal input) {
		return bigMath(input, getPublicKey()[0], getPublicKey()[1]);
	}

	public BigDecimal decrypt(BigDecimal input) {
		return bigMath(input, getPrivateKey(), getPublicKey()[1]);
	}

	public BigDecimal encrypt(long input) {
		return bigMath(new BigDecimal(input), getPublicKey()[0], getPublicKey()[1]);

	}

	public BigDecimal decrypt(long input) {
		return bigMath(new BigDecimal(input), getPrivateKey(), getPublicKey()[1]);
	}


}
