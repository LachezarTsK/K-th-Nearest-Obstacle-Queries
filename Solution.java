
import java.util.PriorityQueue;

public class Solution {

    private static final int NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER = -1;

    public int[] resultsArray(int[][] queries, int targetNumberOfObstaclesFromCenter) {
        PriorityQueue<Integer> maxHeapDistanceFromCenter = new PriorityQueue<>((first, second) -> second - first);

        int[] targetDistanceFromCenterPerQuery = new int[queries.length];
        int index = 0;

        for (int[] query : queries) {
            maxHeapDistanceFromCenter.add(getDistanceFromCenter(query));

            if (maxHeapDistanceFromCenter.size() > targetNumberOfObstaclesFromCenter) {
                maxHeapDistanceFromCenter.poll();
            }

            if (maxHeapDistanceFromCenter.size() < targetNumberOfObstaclesFromCenter) {
                targetDistanceFromCenterPerQuery[index] = NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER;
            } else {
                targetDistanceFromCenterPerQuery[index] = maxHeapDistanceFromCenter.peek();
            }

            ++index;
        }
        return targetDistanceFromCenterPerQuery;
    }

    private int getDistanceFromCenter(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        return Math.abs(x) + Math.abs(y);
    }
}
