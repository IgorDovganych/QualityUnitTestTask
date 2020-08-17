import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * main logic is here
 */
public class Analyser {

    /**
     * finds matching time lines
     *
     * @param list - list of Strings
     * @return needed time lines
     */
    public static List<String> findWaitingTimelines(List<String> list) {
        List<WaitingTimeline> waitingTimelines = new ArrayList<>();
        List<String> record;
        List<String> times = new ArrayList<>();
        for (String str : list) {
            record = Arrays.stream(str.split(" ")).collect(toList());
            if (record.get(0).equals("C")) {
                WaitingTimeline waitingTimeline = waitingTimelineBuilder(record);
                waitingTimelines.add(waitingTimeline);
            }
            if (record.get(0).equals("D")) {
                List<String> serviceString = Arrays.stream(record.get(1).split("\\.")).collect(toList());
                byte service = 0;
                byte variation = 0;
                if (!serviceString.get(0).equals("*")) {
                    service = Byte.parseByte(serviceString.get(0));
                    variation = serviceString.size() == 2 ? Byte.parseByte(serviceString.get(1)) : 0;
                }
                List<String> questionString = Arrays.asList(record.get(2).split("\\."));
                byte question = 0;
                byte category = 0;
                byte subcategory = 0;
                if (!questionString.get(0).equals("*")) {
                    question = Byte.parseByte(questionString.get(0));
                    category = questionString.size() > 1 ? Byte.parseByte(questionString.get(1)) : 0;
                    subcategory = questionString.size() == 3 ? Byte.parseByte(questionString.get(2)) : 0;
                }
                ResponseType responseType = ResponseType.valueOf(record.get(3));
                List<String> dateString = Arrays.asList(record.get(4).split("-"));
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateString.get(0));
                    if (dateString.size() > 1) {
                        endDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateString.get(1));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Problems parsing date");
                }

                int quantity = 0;
                int sum = 0;
                for (WaitingTimeline waitingTimeline : waitingTimelines) {
                    Date date = waitingTimeline.getDate();
                    ResponseType responseTypeWTL = waitingTimeline.getResponseType();

                    //when service ="*" and question ="*"
                    if (serviceString.get(0).equals("*") && questionString.get(0).equals("*")) {
                        if (endDate == null) {
                            if (startDate.compareTo(date) == 0 && responseType == responseTypeWTL) {
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        } else {
                            if (!startDate.after(date) && !endDate.before(date) && responseType == responseTypeWTL) {
                                /* startDate <= todayDate <= endDate */
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        }
                    }
                    //when service = "*" and question != "*"
                    if (serviceString.get(0).equals("*") && !questionString.get(0).equals("*")) {
                        byte questionWTL = waitingTimeline.getQuestion();
                        byte categoryWTL = waitingTimeline.getCategory();
                        byte subcategoryWTL = waitingTimeline.getSubcategory();
                        if (endDate == null) {
                            if ((startDate.compareTo(date) == 0 && responseType == responseTypeWTL) &&
                                    ((question == questionWTL && category == categoryWTL && subcategory == subcategoryWTL) ||
                                            (question == questionWTL && category == categoryWTL && subcategory == 0) ||
                                            (question == questionWTL && category == 0 && subcategory == 0))) {
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        } else {
                            if ((!startDate.after(date) && !endDate.before(date) && responseType == responseTypeWTL) &&
                                    ((question == questionWTL && category == categoryWTL && subcategory == subcategoryWTL) ||
                                            (question == questionWTL && category == categoryWTL && subcategory == 0) ||
                                            (question == questionWTL && category == 0 && subcategory == 0))) {
                                /* startDate <= todayDate <= endDate */
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        }
                    }
                    //when service != "*" and question = "*"
                    if (!serviceString.get(0).equals("*") && questionString.get(0).equals("*")) {
                        byte serviceWTL = waitingTimeline.getService();
                        byte variationWTL = waitingTimeline.getVariation();
                        if (endDate == null) {
                            if ((startDate.compareTo(date) == 0 && responseType == responseTypeWTL) &&
                                    ((service == serviceWTL && variation == variationWTL) ||
                                            (service == serviceWTL && variation == 0))) {
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        } else {
                            if ((!startDate.after(date) && !endDate.before(date) && responseType == responseTypeWTL) &&
                                    ((service == serviceWTL && variation == variationWTL) ||
                                            (service == serviceWTL && variation == 0))) {
                                /* startDate <= todayDate <= endDate */
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        }
                    }

                    //when service != "*" and question != "*"
                    if (!serviceString.get(0).equals("*") && !questionString.get(0).equals("*")) {
                        byte serviceWTL = waitingTimeline.getService();
                        byte variationWTL = waitingTimeline.getVariation();
                        byte questionWTL = waitingTimeline.getQuestion();
                        byte categoryWTL = waitingTimeline.getCategory();
                        byte subcategoryWTL = waitingTimeline.getSubcategory();
                        if (endDate == null) {
                            if ((startDate.compareTo(date) == 0 && responseType == responseTypeWTL) &&
                                    ((service == serviceWTL && variation == variationWTL) ||
                                            (service == serviceWTL && variation == 0)) &&
                                    ((question == questionWTL && category == categoryWTL && subcategory == subcategoryWTL) ||
                                            (question == questionWTL && category == categoryWTL && subcategory == 0) ||
                                            (question == questionWTL && category == 0 && subcategory == 0))) {
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        } else {
                            if ((!startDate.after(date) && !endDate.before(date) && responseType == responseTypeWTL) &&
                                    ((service == serviceWTL && variation == variationWTL) ||
                                            (service == serviceWTL && variation == 0)) &&
                                    ((question == questionWTL && category == categoryWTL && subcategory == subcategoryWTL) ||
                                            (question == questionWTL && category == categoryWTL && subcategory == 0) ||
                                            (question == questionWTL && category == 0 && subcategory == 0))) {
                                /* startDate <= todayDate <= endDate */
                                sum += waitingTimeline.getTime();
                                quantity++;
                            }
                        }
                    }
                }
                if (quantity != 0) {
                    int time = sum / quantity;
                    String waitingTime = String.valueOf(time);
                    times.add(waitingTime);
                } else {
                    times.add("-");
                }
            }
        }
        return times;
    }

    /**
     * parse a String and builds new WaitingTimeline object
     *
     * @param record - list of Strings
     * @return WaitingTimeline object
     */
    private static WaitingTimeline waitingTimelineBuilder(List<String> record) {
        List<String> serviceString = Arrays.stream(record.get(1).split("\\.")).collect(toList());
        byte service = Byte.parseByte(serviceString.get(0));
        byte variation = serviceString.size() == 2 ? Byte.parseByte(serviceString.get(1)) : 0;
        List<String> questionString = Arrays.asList(record.get(2).split("\\."));
        byte question = Byte.parseByte(questionString.get(0));
        byte category = questionString.size() > 1 ? Byte.parseByte(questionString.get(1)) : 0;
        byte subcategory = questionString.size() == 3 ? Byte.parseByte(questionString.get(2)) : 0;
        ResponseType responseType = ResponseType.valueOf(record.get(3));
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(record.get(4));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Problems parsing date");
        }
        int time = Integer.parseInt(record.get(5));
        return new WaitingTimeline(service, variation, question, category, subcategory, responseType, date, time);

    }
}
