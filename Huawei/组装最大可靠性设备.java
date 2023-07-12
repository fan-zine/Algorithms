package Huawei;

import java.util.*;
import java.util.stream.Collectors;

public class 组装最大可靠性设备 {
    public static class Device {
        int type;
        int reliability;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getReliability() {
            return reliability;
        }

        public void setReliability(int reliability) {
            this.reliability = reliability;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        int price;

        public Device(int type, int reliability, int price) {
            this.type = type;
            this.reliability = reliability;
            this.price = price;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> sAndN = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int s = sAndN.get(0);
        int n = sAndN.get(1);
        int total = Integer.parseInt(sc.nextLine());
        // key:type,value:deviceList
        HashMap<Integer, List<Device>> devices = new HashMap<>();
        TreeSet<Integer> reliabilities = new TreeSet<>();
        HashSet<Integer> typeSet = new HashSet<>();
        for (int i = 0; i < total; i++) {
            List<Integer> collect = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            Integer type = collect.get(0);
            Integer reliability = collect.get(1);
            Integer price = collect.get(2);
            Device device = new Device(type, reliability, price);
            reliabilities.add(reliability);
            typeSet.add(type);
            if (devices.containsKey(type)) {
                devices.get(type).add(device);
            } else {
                List<Device> deviceList = new ArrayList<>();
                deviceList.add(device);
                devices.put(type, deviceList);
            }
        }
        Iterator<Integer> iterator = typeSet.stream().iterator();
        while (iterator.hasNext()) {
            Integer type = iterator.next();
            devices.get(type).sort(Comparator.comparingInt(Device::getReliability));
        }
        Integer[] integers = reliabilities.toArray(new Integer[0]);
        int left = 0, right = integers.length-1,finalReliability = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int curCost = 0;
            boolean isValid = true;
            Iterator<Integer> iteratorInner = typeSet.stream().iterator();
            while (iteratorInner.hasNext()) {
                Integer next = iteratorInner.next();
                List<Device> deviceList = devices.get(next);
                Device firstSuitable = findFirstSuitable(integers[mid], deviceList);
                if (firstSuitable == null) {
                    isValid = false;
                    break;
                } else {
                    int price = firstSuitable.getPrice();
                    curCost += price;
                }
            }
            if(!isValid) {
                right = mid - 1;
                continue;
            }
            if (curCost > s) {
                right = mid - 1;
                continue;
            } else {
                left = mid + 1;
            }
            finalReliability = integers[mid];
        }
        System.out.println(finalReliability);
    }

    public static Device findFirstSuitable(Integer reliability, List<Device> devices) {
        for (Device device : devices) {
            if (device.reliability >= reliability) {
                return device;
            }
        }
        return null;
    }
}
