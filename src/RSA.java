
public class RSA {

	private final long prime1;
	private final long prime2;
	private final long euler;
	private final long publicHalf1;
	private final long publicHalf2;



	private final long[] publicKey;
	private final long privateKey;

	public RSA(long prime1, long prime2) {
		System.out.println("Configuring RSA");
		this.prime1 = prime1;
		this.prime2 = prime2;
		this.euler = (1 - prime1) * (1 - prime2);
		System.out.println("Calculating the public key");
		this.publicHalf1 = ggtPrime();
		this.publicHalf2 = prime1 * prime2;
		System.out.println("Calculating the private key");
		this.privateKey = setPrivateKey();
		this.publicKey = setPublicKey();
		System.out.println("Keys ready");
	}

	public long ggtPrime() {
		long temp = euler - 1;
		long number = 0;
		while (number != 1) {
			number = ggt(temp, euler);
			temp--;
		}
		return temp + 1;
	}

	public long ggt(long a, long b) {

		while (b != 0) {
			long h = a % b;
			a = b;
			b = h;
		}

		return a;

	}

	public boolean divIsNat(long a, long b) {
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
