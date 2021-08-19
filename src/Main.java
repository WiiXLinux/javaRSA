
public class Main {

	public static String displayArray(long[]in){
		String temp = "";
		for(int i = 0; i < in.length; i++){
			temp = temp + "{" + in[i] + "}; ";
		}
		return temp;
	}


	public static void main(String[] args) {
		RSA rsa = new RSA(7,5);
		System.out.println(rsa.encrypt(11));
		System.out.println(rsa.decrypt(rsa.encrypt(11)));


	}

}
