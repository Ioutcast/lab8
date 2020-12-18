package Utils.ServerPart.ServerClasses.Commands;


/**
 * Класс для сохранения коллекции в файл
 * (Не используется)
 */

public class SaveClass extends AbstractCommand {

    public SaveClass() {
        command = "save";
        TextInfo = ": Сохранение коллекции";
        NeedAnStr = false;
    }


//   @Override
//    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) throws IOException, InvalidCountOfArgumentException {
//        try {
//
//            //Writter.write(getworkerLinkedHashMap(), getXml());
//            //BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(getNewXml()));
//            //bufferedWriter1.write("");
//           // bufferedWriter1.flush();
//           // bufferedWriter1.close();
//        } catch (ParserConfigurationException | TransformerException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println( "Данные сохранены.");
//        return "Данные сохранены.";
//    }
}
