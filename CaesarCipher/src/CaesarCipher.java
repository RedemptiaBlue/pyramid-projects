import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CaesarCipher {
    static Scanner s = new Scanner(System.in);
    static final String encrypt = "encrypt"; static final String decrypt = "decrypt";
    static final ArrayList<String> alphabet = new ArrayList<String>() {{
            addAll(List.of(("abcdefghijklmnopqrstuvwxyz".repeat(3)).split("")));
    }};


    static String chooseMode() {
        String mode = null;
        boolean validReturn = false;
        while(!validReturn){
            System.out.println("Do you wish to encrypt or decrypt?\n");
            try {
                String i = s.nextLine();
                if (!i.equals(encrypt) && !i.equals(decrypt) && !i.equals("exit")) {
                    throw new Exception();
                }
                mode = i;
                validReturn = true;
            } catch (Exception e) {
                System.out.println("\nInvalid mode\n");
            }
        }
        return mode;
    }

    static String promptMessage() {
        String message = null;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("\nEnter your message:\n");
                String i = s.nextLine();
                if (i.equals("")) {
                    throw new Exception();
                }
                valid = true;
                message = i;
                System.out.println(message);
            }catch (Exception e) {
                System.out.println("Cannot be blank");
            }
        }
        return message;
    }

    static int promptKeyNumber() {
        int key = -1;
        boolean valid = false;
        while (!valid) {
            try{
                System.out.println("\nEnter the key number (1-52):\n");
                int i = Integer.parseInt(s.nextLine());
                if (i < 0 || i > 53){
                    throw new Exception();
                }
                valid = true;
                key = i;
            } catch (Exception e) {
                System.out.println("Invalid key");
            }
        }
        return key;
    }

    static String promptFileName(String mode) {
        String filename = "";
        boolean valid = false;
        while (!valid) {
            if (Objects.equals(mode, encrypt)) {
                System.out.println("Name the file the message will be stored in:");
            } else if (Objects.equals(mode, decrypt)) {
                System.out.println("What's the name of the file the message is in?");
            }
            try{
               filename = s.nextLine();
               valid = true;
            } catch (Exception e) {
                System.out.println("Invalid name");
            }
        }
        return filename;
    }

    static String encryptMessage(String m, int k) {
        StringBuilder encryptedMessage = new StringBuilder();
        for(String c : m.split("")) {
            if (alphabet.contains(c)) {
                encryptedMessage.append(alphabet.get(alphabet.indexOf(c) + k));
            } else {
                encryptedMessage.append(c);
            }
        }
        System.out.println("\n" + encryptedMessage + "\n");
        return encryptedMessage.toString();
    }

    static void printToFile(String message, int k, String file) throws IOException {
        Message m = new Message(message);
        FileOutputStream out = new FileOutputStream(String.valueOf(Paths.get(file + ".bin")));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.write(k);
        objectOutputStream.writeObject(m);
        out.close();
        objectOutputStream.close();
    }

    static ArrayList<String> readMessageFromFile(String file) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(file +".bin");
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        int k = objectInputStream.readByte();
        Message encodedMessage = (Message)objectInputStream.readObject();
        in.close();
        objectInputStream.close();
        return new ArrayList<String>(){{add(String.valueOf(k)); add(encodedMessage.toString());}};
    }

    static void decryptMessage(List<String> m) {
        Collections.reverse(alphabet);
        StringBuilder decryptedMessage = new StringBuilder();
        for(String c : m.get(1).split("")) {
            if (alphabet.contains(c)) {
                decryptedMessage.append(alphabet.get(alphabet.indexOf(c) + Integer.parseInt(m.get(0))));
            } else {
                decryptedMessage.append(c);
            }
        }
        System.out.println("\nDecrypted message: " + decryptedMessage + "\n");
        Collections.reverse(alphabet);
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit){
            String mode = chooseMode();
            if (!mode.equals("exit")) {
                switch (mode) {
                    case encrypt:
                        String newMessage = promptMessage();
                        int key = promptKeyNumber();
                        String encryptedMessage = encryptMessage(newMessage, key);
                        try {
                            printToFile(encryptedMessage, key, promptFileName(encrypt));
                        } catch (Exception e ) {
                            System.out.println("Failed to print to File");
                        }
                        break;
                    case decrypt:
                        try{
                            ArrayList<String> messageToDecode = readMessageFromFile(promptFileName(encrypt));
                            decryptMessage(messageToDecode);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                }
            } else {
                exit = true;
            }
        }
    }
}
