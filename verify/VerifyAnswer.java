import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class VerifyAnswer {
    public static void main(String[] args) throws Exception {
        String file = args[0];
        String outPath = args[1];
        String correctPath = args[2];
        String inPath = args[3];
        File inFile = new File(inPath+file);
        File outFile = new File(outPath+file);
        File correctFile = new File(correctPath+file);
        if(inFile.exists() && outFile.exists() && correctFile.exists()){
            boolean notCorrect = false;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(outFile), Charset.forName("UTF-8")));
            ArrayList<String> arr = new ArrayList<String>();
            String text;
            while ((text = br.readLine()) != null) {
                for(String v : text.split(" ")){
                    arr.add(v);
                }
            }
            System.out.println(arr);
            int index = 0;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(correctFile), Charset.forName("UTF-8")));
            while ((text = br.readLine()) != null) {
                for(String v : text.split(" ")){
                    if(!v.equals(arr.get(index))){
                        notCorrect = true;
                        System.err.println("outFile = [" + outPath+file + "], correctFile = [" + correctPath+file + "], index = [" + index + "], correct = [" + v + "], output = [" + arr.get(index) + "]");
                    }
                    index++;
                }
            }
            if(notCorrect){
                try {
                    String newFile = ""+System.currentTimeMillis()+".txt";
                    Path sourcePath = Paths.get(inPath+file);
                    Path targetPath = Paths.get(inPath+newFile);
                    Files.copy(sourcePath, targetPath);
                    sourcePath = Paths.get(outPath+file);
                    targetPath = Paths.get(outPath+newFile);
                    Files.copy(sourcePath, targetPath);
                    sourcePath = Paths.get(correctPath+file);
                    targetPath = Paths.get(correctPath+newFile);
                    Files.copy(sourcePath, targetPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}