
import kotlin.math.abs
import java.util.PriorityQueue

class Solution {

    private companion object {
        const val NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER = -1
    }

    fun resultsArray(queries: Array<IntArray>, targetNumberOfObstaclesFromCenter: Int): IntArray {
        val maxHeapDistanceFromCenter = PriorityQueue<Int>() { first, second -> second - first }

        val targetDistanceFromCenterPerQuery = IntArray(queries.size)
        var index = 0

        for (query in queries) {
            maxHeapDistanceFromCenter.add(getDistanceFromCenter(query))

            if (maxHeapDistanceFromCenter.size > targetNumberOfObstaclesFromCenter) {
                maxHeapDistanceFromCenter.poll()
            }

            if (maxHeapDistanceFromCenter.size < targetNumberOfObstaclesFromCenter) {
                targetDistanceFromCenterPerQuery[index] = NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER
            } else {
                targetDistanceFromCenterPerQuery[index] = maxHeapDistanceFromCenter.peek()
            }

            ++index
        }
        return targetDistanceFromCenterPerQuery
    }

    private fun getDistanceFromCenter(coordinates: IntArray): Int {
        val x = coordinates[0]
        val y = coordinates[1]
        return abs(x) + abs(y)
    }
}
