package com.covidtracker.covidtracker.services;

import com.covidtracker.covidtracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();
    private int totalGlobalCases;
    private int newGlobalCases;

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    public int getTotalGlobalCases() {
        return totalGlobalCases;
    }

    public int getNewGlobalCases() {
        return newGlobalCases;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        int totalCases = 0;
        int prevTotalCases = 0;

        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            newStats.add(locationStat);

            totalCases += latestCases;
            prevTotalCases += prevDayCases;
        }

        this.allStats = newStats;
        this.totalGlobalCases = totalCases;
        this.newGlobalCases = totalCases - prevTotalCases;
    }
}
