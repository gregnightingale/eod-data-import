import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.util.List;

public class CSVMappedToJavaBeanExample
{
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws Exception
    {
        CsvToBean csv = new CsvToBean();

        String csvFilename = "/Users/gregnightingale/Downloads/quantquote_daily_sp500_83986/daily/table_a.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));

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