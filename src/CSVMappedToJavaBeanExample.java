import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class CSVMappedToJavaBeanExample
{
    static Database database;
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws Exception
    {
        database = new Database();
        File folder = new File("/Users/gregnightingale/Downloads/quantquote_daily_sp500_83986/daily/");
        File[] listOfFiles = folder.listFiles();
//        database.connectToDatasource();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
//                processFile(listOfFiles[i]);
            } else {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        database.createDbUserTable("FU");
    }

    static private void processFile(File file) throws Exception {
        CsvToBean csv = new CsvToBean();
        CSVReader csvReader = new CSVReader(new FileReader(file));
        //Set column mapping strategy
        List list = csv.parse(setColumMapping(), csvReader);
        for (Object object : list) {
            EodQuants eod = (EodQuants) object;
            System.out.println(eod);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(EodQuants.class);
        String[] columns = new String[] {"date", "unknown", "open", "high", "low", "close", "volume"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}