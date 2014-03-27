package controllers.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.entity.HolidayPayload;
import dao.entity.HolidayResult;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BaseQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sateeshampolu
 * Date: 27/03/14
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class HolidaysAccessor {

    // TODO need to add the correct index name
    private static final String INDEX_NAME ="inspireme";
    //TODO need to set the correct type
    private static final String TYPE = "holiday";

    public String getHolidays(HolidayPayload payload) throws JsonProcessingException {
        Client client = getClient();

        BaseQueryBuilder queryBuilder;


        queryBuilder = QueryBuilders.filteredQuery(QueryBuilders
                .matchAllQuery(), FilterBuilders
                .andFilter(buildFilters(payload)));

//        queryBuilder = QueryBuilders.matchAllQuery();

        SearchRequestBuilder requestBuilder = client.prepareSearch(INDEX_NAME);

        SearchResponse response = requestBuilder
                .setTypes(TYPE)
                .addFields("latitude","longitude","min_price","destination_name")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(queryBuilder)
                .execute()
                .actionGet();

        SearchHit[] results = response.getHits().getHits();

        List<HolidayResult> holidayResults = processHits(results);//asList(new HolidayResult());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(holidayResults);

    }

    private List<HolidayResult> processHits(SearchHit[] results){

        List<HolidayResult> holidays = new ArrayList<HolidayResult>();
        for (SearchHit hit : results) {
            Map<String, SearchHitField> fields = hit.getFields();
            String latitude = getNonNullFieldValue("latitude",fields);
            String longitude = getNonNullFieldValue("longitude",fields);
            String price = getNonNullFieldValue("min_price",fields);
            String info = getNonNullFieldValue("destination_name",fields);
            holidays.add(new HolidayResult(latitude,longitude,price,info));
        }
        return holidays;

    }

    private String getNonNullFieldValue(String key, Map<String, SearchHitField> fields){

        SearchHitField field  = fields.get(key);
        if(field != null){
            return field.getValue();
        }
        return null;
    }

    private FilterBuilder[] buildFilters(HolidayPayload holidayEnquiry){
        List<FilterBuilder> filters = new ArrayList<FilterBuilder>();

        filters.add(FilterBuilders.termFilter("departure_airport_code",holidayEnquiry.getFromAirport()));
        filters.add(FilterBuilders.termFilter("num_adult",holidayEnquiry.getNumAdults()));
        if(holidayEnquiry.getNumChildren()>0){
            filters.add(FilterBuilders.termFilter("num_child",holidayEnquiry.getNumAdults()));
        }
        if(holidayEnquiry.getNumInfants()>0){
            filters.add(FilterBuilders.termFilter("num_infant",holidayEnquiry.getNumInfants()));
        }
        filters.add(FilterBuilders.termFilter("duration",holidayEnquiry.getDuration()));
        filters.add(FilterBuilders.termFilter("ob_departure_date",holidayEnquiry.getDateFrom()));

        return filters.toArray(new FilterBuilder[filters.size()]);
    }

    private Client getClient(){
        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
        builder.put("name", "elasticsearch");
        Settings settings = builder.build();

        InetSocketTransportAddress[] addresses;



        List<InetSocketTransportAddress> inetSocketTransportAddresses = new ArrayList<InetSocketTransportAddress>();

        inetSocketTransportAddresses.add(new InetSocketTransportAddress("54.72.117.93", 9200));
//        inetSocketTransportAddresses.add(new InetSocketTransportAddress("2.elasticsearch.tsm", 9300));

        addresses = inetSocketTransportAddresses.toArray(new InetSocketTransportAddress[inetSocketTransportAddresses.size()]);

        Client client =  new TransportClient(settings).addTransportAddresses(addresses);

        return client;

    }
}
