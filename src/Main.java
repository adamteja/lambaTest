import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){


        List<String[]> data = new ArrayList<>();


        String[] testa = {"one","two","default"};
        data.add(testa);
        String[] testb = {"9212288798","9212288798","guidCompare"};
        data.add(testb);
        String[] testc = {"https://dpm.demdex.net/id?d_visid_ver=2.3.0&d_fieldgroup=AAM&d_rtbd=json","https://dpm.demdex.net/id?d_visid_ver=2.3.0&d_fieldgroup=AAM&d_rtbd=json","urlCompare"};
        data.add(testc);
        String[] testd = {"event20,event60,","event20,event60,","eventsCompare"};
        data.add(testd);
        String[] teste = {"application/json","application/text","ignore"};
        data.add(teste);

        TagCompare tc = new TagCompare();


        for(int i = 0; i < data.size(); i++){

            String source = data.get(i)[0];
            String target = data.get(i)[1];
            String type = data.get(i)[2];

            TagCompare.compareMethod mc =tc.getKeymap().get(type);

            System.out.print("compare "+source +" to "+ target +" by "+ type);
            if(mc == null){
                mc = TagCompare::defaultCompare;
            }
            if ( TagCompare.doCompare(source,target, mc ) ){
                System.out.println(" Test Passed!!! ");
            }else{
                System.out.println(" Test Failed!!! ");
            };

        }


    }




}
