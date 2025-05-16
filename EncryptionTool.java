package encryptDecrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class EncryptionTool {
	private static final String ALGORITHM = "AES";
	
	// Generate key
	private static SecretKeySpec getKey(String myKey) {
		byte[] key = myKey.getBytes();
		byte[] keyBytes = new byte[16];
		System.arraycopy(key, 0, keyBytes, 0, Math.min(key.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Encrypt plain text
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            SecretKeySpec secretKey = getKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    // Decrypt encrypted text
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = getKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            return new String(decrypted);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    // CLI Menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter secret key (min 1 character, max 16): ");
        String key = scanner.nextLine();

        System.out.println("\nChoose an option:\n1. Encrypt\n2. Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter text to encrypt: ");
            String text = scanner.nextLine();
            String encrypted = encrypt(text, key);
            System.out.println("Encrypted text: " + encrypted);
        } else if (choice == 2) {
            System.out.print("Enter text to decrypt: ");
            String text = scanner.nextLine();
            String decrypted = decrypt(text, key);
            System.out.println("Decrypted text: " + decrypted);
        } else {
            System.out.println("Invalid option.");
        }

        scanner.close();
    

	}
}
