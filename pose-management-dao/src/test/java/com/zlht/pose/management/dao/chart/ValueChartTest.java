package com.zlht.pose.management.dao.chart;


public class ValueChartTest {


/*    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        //value
        Map<String, ValueTypeChart> typeChartMap = new HashMap<>();
        typeChartMap.put("item111", new ValueTypeChart("111", "222"));
        typeChartMap.put("item222", new ValueTypeChart("222", "666"));


        //pie
        List<PieTypeChart> list = new ArrayList<>();
        list.add(new PieTypeChart("224", "支付宝"));
        list.add(new PieTypeChart("115", "支付宝2"));
        Map<String, List<PieTypeChart>> pieTypeChartMap = new HashMap<>();
        pieTypeChartMap.put("pie1", list);

        //line
        Map<String, LineTypeChart> lineTypeChartMap = new HashMap<>();
        List<String> xList = new ArrayList<>();
        xList.add("1");
        xList.add("2");
        xList.add("3");
        Map<String, List<String>> yData = new HashMap<>();
        yData.put("Y1", Arrays.asList("14", "16", "17"));
        yData.put("Y2", Arrays.asList("24", "26", "27"));
        yData.put("Y3", Arrays.asList("34", "36", "37"));

        lineTypeChartMap.put("line1", new LineTypeChart(xList, yData));
        lineTypeChartMap.put("line2", new LineTypeChart(xList, yData));

        ChartCollect resChart = new ChartCollect();
        resChart.setValueTypeChartMap(typeChartMap);
        resChart.setPieChartDataMap(pieTypeChartMap);
        resChart.setLineChartDataMap(lineTypeChartMap);

        System.out.println(objectMapper.writeValueAsString(resChart));

    }*/
}