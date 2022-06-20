class UndergroundSystem {
    public static void main(String[] args) {
        
    

    private Map<Integer, Pair<String, Integer>> checkedIn;  //Обозначаем данные:1)ID пассажира cкрепленная пара название станции, время
    private Map<Pair<String, String>, Pair<Integer, Integer>> times; // 2) Пара станция отправления, станция назначения, пара время и некоторое число

    public UndergroundSystem() {
        checkedIn = new HashMap<>();
        times = new HashMap<>();
        
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkedIn.put(id, new Pair<>(stationName, t));       
    }
    
    public void checkOut(int id, String stationName, int t) {
        // берём данные из checkedIn, удаляем checkedin, добавляем в times
        Pair<String, Integer> inData = checkedIn.get(id);
        String inStation = inData.getKey();
        Integer inTime = inData.getValue();
        checkedIn.remove(id);
        
        String outStation = stationName;
        Integer outTime = t;
        
        Pair<String, String> stationPair = new Pair<>(inStation, outStation);
        if (!times.containsKey(stationPair)) {
            times.put(stationPair, new Pair<>(outTime - inTime, 1));
        } else {
            Pair<Integer, Integer> oldTimes = times.get(stationPair);
            Integer sum = oldTimes.getKey();
            Integer quant = oldTimes.getValue();
            
            sum += outTime - inTime;
            quant++;
            
            times.put(stationPair, new Pair<>(sum, quant));
        }
    }
    
    
 public double getAverageTime(String startStation, String endStation) {
        Pair<String, String> searchKey = new Pair<>(startStation, endStation);
        Pair<Integer, Integer> timeData = times.get(searchKey);
        
        return timeData.getKey() * 1.0 / timeData.getValue();
    }
}
}