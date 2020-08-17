import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitingTimeline {
    private byte service;
    private byte variation;
    private byte question;
    private byte category;
    private byte subcategory;
    private ResponseType responseType;
    private Date date;
    private int time;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public WaitingTimeline(byte service, byte variation, byte question, byte category, byte subcategory, ResponseType responseType, Date date, int time) {
        this.service = service;
        this.variation = variation;
        this.question = question;
        this.category = category;
        this.subcategory = subcategory;
        this.responseType = responseType;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "WaitingTimeline{" +
                "service=" + service +
                ", variation=" + variation +
                ", question=" + question +
                ", category=" + category +
                ", subcategory=" + subcategory +
                ", responseType=" + responseType +
                ", date=" + simpleDateFormat.format(date) +
                ", time=" + time +
                '}';
    }

    public byte getService() {
        return service;
    }

    public byte getVariation() {
        return variation;
    }

    public byte getQuestion() {
        return question;
    }

    public byte getCategory() {
        return category;
    }

    public byte getSubcategory() {
        return subcategory;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Date getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }
}
