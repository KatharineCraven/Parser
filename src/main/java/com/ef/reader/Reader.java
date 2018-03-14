package com.ef.reader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.ef.datatypes.Record;
import com.ef.translator.Translator;

public class Reader {
	Translator translator;
	
	public Reader() {
		translator = new Translator();
	}

	/**
	 * Reads the file and then returns list of records.
	 * @param accesslog
	 * @return
	 */
	public ArrayList<Record> read(String accesslog) {
        BufferedReader br = null;
        ArrayList<Record> records = new ArrayList<Record>();
        String line;
        Date rdate;
        
        try {
            br = new BufferedReader(new FileReader(accesslog));
            line = br.readLine();
            while (line != null) {

                // use pipe as separator
                String[] record = line.split("\\|");
                
                rdate = translator.getDate(record[0]);
                
                records.add(new Record(rdate, record[1], record[2], record[3], record[4]));
                
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return records;
	}
}
