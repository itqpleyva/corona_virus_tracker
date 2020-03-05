package corona_virus.Services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import corona_virus.Model.Case;

@Service
public class CoronaVirusDataService {

	public static String link = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	private List<Case> cases = new ArrayList<>();
	
	@Scheduled(cron = "* * 1 * * * ")
	@PostConstruct
	public List<Case> fetchVirusdata() throws Exception{
		
			List<Case> casesTemp = new ArrayList<>();

	      	URL yahoo = new URL(link);
	        URLConnection yc = yahoo.openConnection();
	        InputStream in = yc.getInputStream();

	        StringBuilder textBuilder = new StringBuilder();
	        
	        try (Reader reader = new BufferedReader(new InputStreamReader
	          (in, Charset.forName(StandardCharsets.UTF_8.name())))) {
	            int c = 0;
	            while ((c = reader.read()) != -1) {
	                textBuilder.append((char) c);
	            }
	        }  
	        	StringReader reader = new StringReader(textBuilder.toString());
	        	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
	        	for (CSVRecord record : records) {
	        		String state = record.get("Province/State");
	        		String country = record.get("Country/Region");
	        		Case c = new Case(state, country, Integer.parseInt(record.get(record.size() - 1)),Integer.parseInt(record.get(record.size() - 2)));
	        		casesTemp.add(c);   
	        		System.out.println(c.toString());
	        	}
	        	this.cases = casesTemp;
	        return cases;
	    }	
}
