package Utils.ServerPart.ServerClasses.SpecialCommands;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface MD2_encrypter {

    static String encryptThisString(String input) {

        if(input == null){
            return "0";
        }

        try {
            // метод getInstance () вызывается с алгоритмом MD2
            MessageDigest md = MessageDigest.getInstance("MD2");

            // вызывается метод digest ()
            // вычислить дайджест сообщения из входной строки
            // возвращается как массив байтов
            byte[] messageDigest = md.digest(input.getBytes());
            // Преобразование байтового массива в представление знака
            BigInteger no = new BigInteger(1, messageDigest);
            // Преобразуем дайджест сообщения в шестнадцатеричное значение
            String hashtext = no.toString(16);
            // Добавить предыдущие 0, чтобы сделать его 32-битным
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // возвращаем HashText
            return hashtext;
        }

        // Для указания неправильных алгоритмов дайджеста сообщений
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }

    }
}
