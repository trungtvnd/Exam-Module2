import java.io.*;
import java.util.ArrayList;

public class IOFile<E> {
    public void writeObjectToFile(ArrayList<E> arrayData, String pathname) {
        File file = new File(pathname);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (E e:arrayData) {
                oos.writeObject(e);
            }
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public ArrayList<E> readObjectFromFile(String pathname) {
        ArrayList<E> arrayList = new ArrayList<>();
        File file = new File(pathname);
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            E e;
            while (true){
                Object obj = ois.readObject();
                if(obj == null){
                    break;
                }else {
                    e = (E)obj;
                    arrayList.add(e);
                }
            }
            ois.close();

        } catch (Exception e) {
            System.out.println();
        }
        return arrayList;
    }
}
