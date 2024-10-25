import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MainClass {
    public static void main(String[] args) {
        List<PrivateEnterprise> enterprises = new ArrayList<>();
        enterprises.add(new PrivateEnterprise("Enterprise A", "Owner A", "IT", 5000, 100000));
        enterprises.add(new PrivateEnterprise("Enterprise B", "Owner B", "Finance", 7000, 150000));
        enterprises.add(new PrivateEnterprise("Enterprise C", "Owner C", "Retail", 3000, 90000));
        enterprises.add(new PrivateEnterprise("Enterprise D", "Owner D", "IT", 8000, 80000));
        enterprises.add(new PrivateEnterprise("Enterprise F", "Owner F", "Retail", 3000, 90000));
        enterprises.add(new PrivateEnterprise("Enterprise G", "Owner G", "IT", 3000, 170000));
        enterprises.add(new PrivateEnterprise("Enterprise H", "Owner H", "Finance", 5000, 9000));
        enterprises.add(new PrivateEnterprise("Enterprise U", "Owner U", "Finance", 1000, 70000));
        enterprises.add(new PrivateEnterprise("Enterprise L", "Owner L", "Retail", 2000, 190000));
        enterprises.add(new PrivateEnterprise("Enterprise X", "Owner X", "IT", 3000, 180000));
        
        // sorting
        {
            long startTime = System.currentTimeMillis();
            Collections.sort(enterprises, PrivateEnterpriseComparators.byName());
            System.out.println("Sorted by name: " + enterprises);
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }

        // Parallel sorting
        {
            long startTime = System.currentTimeMillis();
            ForkJoinPool.commonPool().submit(() -> enterprises.parallelStream()
                    .sorted(PrivateEnterpriseComparators.byMultipleCriteria())
                    .forEach(System.out::println)).join();
                    long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }
    }
}