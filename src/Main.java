
public class Main {

	public static String displayArray(Object[]in){
		String temp = "";
		for(int i = 0; i < in.length; i++){
			temp = temp + "{" + in[i] + "}; ";
		}
		return temp;
	}


	public static void main(String[] args) {
		RSA rsa = new RSA(37,41);
		System.out.println(displayArray(rsa.encrypt("Hallo")));
		System.out.println(rsa.decrypt(rsa.encrypt("Hallo")));

		
	}

}
