
using System;

public class Solution
{
    private static readonly int NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER = -1;

    public int[] ResultsArray(int[][] queries, int targetNumberOfObstaclesFromCenter)
    {
        PriorityQueue<int, int> maxHeapDistanceFromCenter =
            new PriorityQueue<int, int>(Comparer<int>.Create((first, second) => second - first));

        int[] targetDistanceFromCenterPerQuery = new int[queries.Length];
        int index = 0;

        foreach (int[] query in queries)
        {
            maxHeapDistanceFromCenter.Enqueue(GetDistanceFromCenter(query), GetDistanceFromCenter(query));

            if (maxHeapDistanceFromCenter.Count > targetNumberOfObstaclesFromCenter)
            {
                maxHeapDistanceFromCenter.Dequeue();
            }

            if (maxHeapDistanceFromCenter.Count < targetNumberOfObstaclesFromCenter)
            {
                targetDistanceFromCenterPerQuery[index] = NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER;
            }
            else
            {
                targetDistanceFromCenterPerQuery[index] = maxHeapDistanceFromCenter.Peek();
            }

            ++index;
        }
        return targetDistanceFromCenterPerQuery;
    }

    private int GetDistanceFromCenter(int[] coordinates)
    {
        int x = coordinates[0];
        int y = coordinates[1];
        return Math.Abs(x) + Math.Abs(y);
    }
}
